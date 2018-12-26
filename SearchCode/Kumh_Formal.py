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

sql = "SELECT datetimes FROM Kumh WHERE datetimes = '{}'".format(datetime.date.today())
cursor.execute(sql)
row = cursor.fetchall()

if len(row) == 0:
    Hos='高醫醫院'
    url='http://www.kmuh.org.tw/intro/bedused.asp?noframe=Y' #醫院網址
    response = urllib.request.urlopen(url)
    html = response.read()
    sp = BeautifulSoup(html.decode('utf-8'),"html.parser")
    table=sp.find_all('table')[1]
    tr = table.find_all('tr',attrs={'align':"center" })
    for t in tr:
        data=[]
        td = t.find_all('td')
        Bed = td[0].get_text()
        TotBed = int(td[1].get_text())
        DifBed = int(td[2].get_text())
        InsBed = int(td[3].get_text())
        InsBedUs = int(td[4].get_text())
        InsBedUnUs = int(td[5].get_text())
        DifBedUnUs = int(td[6].get_text())
        InsBedUsp = float(td[7].get_text().replace("%",""))
        Bed = Bed.lstrip()

        Ins_Bed ='健保病床 '+Bed
        Ins_TotBed = InsBed
        Ins_BedUs = round((InsBed * InsBedUsp)/100,0)
        Ins_BedUnUs = Ins_TotBed - Ins_BedUs
        Ins_BedUsp = round((Ins_BedUs / Ins_TotBed)*100,2)

        Dif_Bed ='差額病床 '+Bed
        Dif_TotBed = DifBed
        Dif_BedUs = DifBed-DifBedUnUs
        Dif_BedUnUs = DifBedUnUs
        if Dif_TotBed != 0:
            Dif_BedUsp = round((Dif_BedUs / Dif_TotBed)*100,2)
        else:
            Dif_BedUsp = 0

        datetimes = datetime.date.today()

        data.append([Ins_Bed,Ins_TotBed,Ins_BedUs,Ins_BedUnUs,Ins_BedUsp,datetimes])
        data.append([Dif_Bed,Dif_TotBed,Dif_BedUs,Dif_BedUnUs,Dif_BedUsp,datetimes])
        for i in data:
            sql= "SELECT Type FROM BedTypeAnalysis WHERE Bed = '{}'".format(i[0])
            cursor.execute(sql)
            row = cursor.fetchone()
            Type = row[0]
            sql = "INSERT INTO Kumh (Bed,TotBed,BedUs,BedUnUs,TotBedUsp,Type,Hos,datetimes) VALUES('{}',{},{},{},{},'{}','{}','{}')".format(i[0],i[1],i[2],i[3],i[4],Type,Hos,i[5])
            cursor.execute(sql)
    cnxn.commit()
    print('{}資料已經成功匯入資料庫！！！'.format(datetime.date.today()))
else:
    print('{}資料已經存在！！！'.format(datetime.date.today()))
    exit()
