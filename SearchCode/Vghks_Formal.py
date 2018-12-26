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

sql = "SELECT datetimes FROM Vghks WHERE datetimes = '{}'".format(datetime.date.today())
cursor.execute(sql)
row = cursor.fetchall()

if len(row) == 0:
    Hos='榮總醫院'
    url='https://www.vghks.gov.tw/Json_Read.aspx?type=2&url=http%3A%2F%2Fwwwmgr.vghks.gov.tw%2FUpload%2Fvghksbed%2FList_Beds.json'
    response = urllib.request.urlopen(url)
    html = response.read()
    sp = BeautifulSoup(html.decode('utf8'),"html.parser")
    tbody=sp.find('tbody')
    tr = tbody.find_all('tr')
    for t in tr:
        data=[]
        td = t.find_all('td')
        Bed = td[0].get_text()
        TotBed = int(td[1].find_all('span')[1].get_text())
        InsBed = int(td[2].find_all('span')[1].get_text())
        DifBedUnUs = int(td[3].find_all('span')[1].get_text())
        TotBedUsp = float(td[4].find_all('span')[1].get_text().replace("%",""))
        InsBedUsp = float(td[5].find_all('span')[1].get_text().replace("%",""))
        DifBedUsp = float(td[6].find_all('span')[1].get_text().replace("%",""))
        Bed = Bed.lstrip()
        Ins_Bed ='健保病床 '+Bed
        Ins_TotBed = InsBed
        Ins_BedUs = round((InsBed * InsBedUsp)/100,0)
        Ins_BedUnUs = Ins_TotBed - Ins_BedUs
        Ins_BedUsp = InsBedUsp

        Dif_Bed ='差額病床 '+Bed
        Dif_TotBed = TotBed-InsBed
        Dif_BedUs = round((Dif_TotBed * DifBedUsp)/100,0)
        Dif_BedUnUs = DifBedUnUs
        Dif_BedUsp = DifBedUsp

        datetimes = datetime.date.today()

        data.append([Ins_Bed,Ins_TotBed,Ins_BedUs,Ins_BedUnUs,Ins_BedUsp,datetimes])
        data.append([Dif_Bed,Dif_TotBed,Dif_BedUs,Dif_BedUnUs,Dif_BedUsp,datetimes])
        for i in data:
            sql= "SELECT Type FROM BedTypeAnalysis WHERE Bed = '{}'".format(i[0])
            cursor.execute(sql)
            row = cursor.fetchone()
            Type = row[0]
            sql = "INSERT INTO Vghks (Bed,TotBed,BedUs,BedUnUs,TotBedUsp,Type,Hos,datetimes) VALUES('{}',{},{},{},{},'{}','{}','{}')".format(i[0],i[1],i[2],i[3],i[4],Type,Hos,i[5])
            cursor.execute(sql)
    cnxn.commit()
    print('{}資料已經成功匯入資料庫！！！'.format(datetime.date.today()))
else:
    print('{}資料已經存在！！！'.format(datetime.date.today()))
    exit()
