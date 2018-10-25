import pandas as pd
import csv
import time

time = time.strftime('%Y_%m_%d')
url = 'http://www.jiannren.org.tw/jiannren/bed.php'
data = pd.read_html(url,encoding='utf8')[6]
table1 = data[:]
print(table1)
table1.to_csv('Janren_out'+time+'.csv', header= None)
