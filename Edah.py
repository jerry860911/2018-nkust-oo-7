#coding=Big5
import pandas as pd
import csv
import time

time = time.strftime('%Y_%m_%d')
url = 'http://www3.edah.org.tw/E-DA/WebRegister/ProcessByCnt_show.jsp'
data = pd.read_html(url,encoding='Big5')[1]
table1 = data[1:11]
print(table1)
table2 = table1.iloc[:, 0:4]
table2.to_csv('Edah_out'+time+'.csv', header= None)
print(table2)
