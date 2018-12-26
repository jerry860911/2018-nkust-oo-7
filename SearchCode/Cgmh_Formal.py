# coding = Big5
from selenium import webdriver
from selenium.webdriver.support.ui import Select
# coding = utf8
import pandas as pd #套件
import datetime
import numpy as np
from bs4 import BeautifulSoup
import urllib.request
import json
import lxml
import pyodbc
import time

server = '163.18.42.32'
database = 'test'
username = 'test'
password = '54321'
driver= '{ODBC Driver 13 for SQL Server}'
cnxn = pyodbc.connect('DRIVER='+driver+';SERVER='+server+';PORT=1433;DATABASE='+database+';UID='+username+';PWD='+ password)
cursor = cnxn.cursor()
if not cnxn:
    print("連結資料庫失敗")
else:
    print("連結資料庫成功")

sql = "SELECT datetimes FROM Cgmh WHERE datetimes = '{}'".format(datetime.date.today())
cursor.execute(sql)
row = cursor.fetchall()

if len(row) == 0:
    Hos='長庚醫院'
    Browser = webdriver.Chrome("C:\專題搜尋\chromedriver_win32\chromedriver.exe")
    Browser.get('https://www.cgmh.org.tw/bed/view/domain/bed/ACS350233.aspx')
    select = Select(Browser.find_element_by_name('ctl00$ctl00$cp_Content$ContentPlaceHolder1$DDL_LOC'))
    select.select_by_value('8')
    time.sleep(2)
    time = time.strftime('%Y_%m_%d')
    html = Browser.find_element_by_css_selector('#UpdatePanel1').get_attribute('innerHTML')
    sp = BeautifulSoup(html,"html.parser")
    table=sp.find('table')
    tr = table.find_all('tr')
    for t in tr:
        if len(t.find_all('td',attrs={'colspan':"5"})) == 1 or len(t.find_all('td',attrs={'colspan':"4"})) == 1 or t.find_all('td')[0].get_text() == '病床別' or t.find_all('td')[0].get_text() == '總床數':
            continue
        td = t.find_all('td')
        Bed = td[0].get_text()
        TotBed = int(td[1].get_text())
        BedUs = int(td[2].get_text())
        BedUnUs = int(td[3].get_text())
        if TotBed != 0:
            TotBedUsp = float(round((BedUs/TotBed)*100,2))
        else:
            TotBedUsp = 0
        Bed = Bed.lstrip()
        Bed = Bed.replace("非健保床","差額病床")
        Bed = Bed.replace("健保床","健保病床")
        datetimes = datetime.date.today()
        sql= "SELECT Type FROM BedTypeAnalysis WHERE Bed = '{}'".format(Bed)
        cursor.execute(sql)
        row = cursor.fetchone()
        Type = row[0]
        sql = "INSERT INTO Cgmh(Bed,TotBed,BedUs,BedUnUs,TotBedUsp,Type,Hos,datetimes) VALUES('{}',{},{},{},{},'{}','{}','{}')".format(Bed,TotBed,BedUs,BedUnUs,TotBedUsp,Type,Hos,datetimes)
        cursor.execute(sql)
    cnxn.commit()
    print('{}資料已經成功匯入資料庫！！！'.format(datetime.date.today()))
else:
    print('{}資料已經存在！！！'.format(datetime.date.today()))
    exit()
