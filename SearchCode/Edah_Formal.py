# coding = utf8
import pandas as pd #套件
import datetime
import numpy as np
from bs4 import BeautifulSoup
import urllib.request
import json
import lxml
import pyodbc

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

sql = "SELECT datetimes FROM Edah WHERE datetimes = '{}'".format(datetime.date.today())
cursor.execute(sql)
row = cursor.fetchall()

if len(row) == 0:
    Hos='義大醫院'
    url = 'http://www3.edah.org.tw/E-DA/WebRegister/ProcessByCnt_show.jsp'
    response = urllib.request.urlopen(url)
    html = response.read()
    sp = BeautifulSoup(html.decode('Big5'),"html.parser")
    table=sp.find('table',attrs={'border':"1"})
    tr = table.find_all('tr')
    for t in tr:
        if len(t.find_all('font',attrs={'color':"white"})) >= 1 or len(t.find_all('td',attrs={'colspan':"5"})) == 1 or t.find_all('td')[0].get_text() == '合計':
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
        datetimes = datetime.date.today()

        sql= "SELECT Type FROM BedTypeAnalysis WHERE Bed = '{}'".format(Bed)
        cursor.execute(sql)
        row = cursor.fetchone()
        Type = row[0]
        sql = "INSERT INTO Edah(Bed,TotBed,BedUs,BedUnUs,TotBedUsp,Type,Hos,datetimes) VALUES('{}',{},{},{},{},'{}','{}','{}')".format(Bed,TotBed,BedUs,BedUnUs,TotBedUsp,Type,Hos,datetimes)
        cursor.execute(sql)
    cnxn.commit()
    print('{}資料已經成功匯入資料庫！！！'.format(datetime.date.today()))
else:
    print('{}資料已經存在！！！'.format(datetime.date.today()))
    exit()
