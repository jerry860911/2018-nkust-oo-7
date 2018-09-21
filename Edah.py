#coding=Big5
import pandas as pd
import csv

url = 'http://www3.edah.org.tw/E-DA/WebRegister/ProcessByCnt_show.jsp'
table = pd.read_html(url,encoding='Big5')[1]
table = table.drop([0,11,12])

print(table)
