import pandas as pd
import csv
import time

time = time.strftime('%Y_%m_%d')
url = 'https://www.vghks.gov.tw/Json_Read.aspx?type=2&url=http%3A%2F%2Fwwwmgr.vghks.gov.tw%2FUpload%2Fvghksbed%2FList_Beds.json'
data = pd.read_html(url,encoding='utf8')
table1 = data[0]
table2 = table1.iloc[:, 0:7]
print(table2)
table2.to_csv('Vghks_out'+time+'.csv', header= None)
