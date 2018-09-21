from selenium import webdriver
from selenium.webdriver.support.ui import Select
import time
import pandas as pd
from pandas import Series, DataFrame

Browser = webdriver.Chrome("D:\專題\專題\chromedriver_win32\chromedriver.exe")
Browser.get('https://www.cgmh.org.tw/bed/view/domain/bed/ACS350233.aspx')

select = Select(Browser.find_element_by_name('ctl00$ctl00$cp_Content$ContentPlaceHolder1$DDL_LOC'))
select.select_by_value('8')
time.sleep(2)
elem = Browser.find_element_by_css_selector('#UpdatePanel1')
cgmh = elem.get_attribute('innerHTML')
df= pd.read_html(cgmh)[0]
df = df.drop([0,1,24,25,26,27,28,29],axis=0)

print(df)
