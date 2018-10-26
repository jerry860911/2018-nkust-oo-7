#coding=Big5
import pandas as pd
import csv
import time

time = time.strftime('%Y_%m_%d')
url='http://www.kmuh.org.tw/intro/bedused.asp?noframe=Y'
data = pd.read_html(url)[0]
table1 = data[4:13]
print(table1)
table2 = table1.iloc[:, 0:4]
table2.to_csv('Kumh_out'+time+'.csv', header= None)
print(table2)
