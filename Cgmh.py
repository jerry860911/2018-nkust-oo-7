from selenium import webdriver
from selenium.webdriver.support.ui import Select
import time
import pandas as pd
from pandas import Series, DataFrame

Browser = webdriver.Chrome("C:\專題\chromedriver_win32\chromedriver.exe")
Browser.get('https://www.cgmh.org.tw/bed/view/domain/bed/ACS350233.aspx')

select = Select(Browser.find_element_by_name('ctl00$ctl00$cp_Content$ContentPlaceHolder1$DDL_LOC'))
select.select_by_value('8')
time.sleep(2)
time = time.strftime('%Y_%m_%d')
elem = Browser.find_element_by_css_selector('#UpdatePanel1')
url = elem.get_attribute('innerHTML')
data = pd.read_html(url,encoding='Big5')[0]
table1 = data[2:24]
print(table1)
table2 = table1.iloc[:, 0:5]
table2.to_csv('Cgmh_out'+time+'.csv', header= None)
print(table2)
