import csv
import pymysql

conn = pymysql.connect(host='localhost', user='insun', password='dlstjs06!', db='library', charset='utf8')

cursor = conn.cursor()

sql = "INSERT INTO book (book_id, author, content, now_stock_quantity, page, rating, stock_quantity, title, image, publisher)\
     VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"

 
f = open('booklist.csv', 'r', encoding='utf-8')
rdr = csv.DictReader(f)
for line in rdr:
    img_path = f"/images/{line['id']}.jpg"
    print(line)
    cursor.execute(sql, (line['id'],line['author'],line['description'],'10',line['pages'],'0','10',line['book_name'],img_path,line['publisher']))
conn.commit()
conn.close()
f.close()   