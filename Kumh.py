import pandas as pd
import csv
url='http://www.kmuh.org.tw/intro/bedused.asp?noframe=Y'
table = pd.read_html(url)[0]
table = table.drop([0,1,2,13],axis=0)
table = table.drop(table.columns[8:296],axis=1)
print(table)
table.to_csv('kumh_out.csv')
