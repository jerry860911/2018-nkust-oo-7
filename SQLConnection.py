import sqlalchemy
import pymssql

cur = pymssql.connect(server='163.18.42.29', user='test', password='54321', database='Demo')
cursor = cur.cursor()
if not cursor:
    print("连接数据库失败")
else:
    print("连接数据库成功")

cur.close()
