# InventoryManagement
Postman collection in the files.
 
Database(Postgres) inserts for manual testing:

insert into warehouse (name, region, city)
values ('warehouse a','akdeniz','antalya'),
       ('warehouse b','karadeniz','zonguldak'),
       ('warehouse c','ege','izmir'),
       ('warehouse d','iç anadolu','ankara'),
       ('warehouse e','doğu anadolu','erzincan');

insert into product_category(category) 
values ('Kitap'),('Defter'),('Kağıt'),('Kalem');

insert into product (name,product_category_id) 
VALUES ('Roman 1',1),
       ('A4 Kağıt',3),
       ('Faber castle',4),
       ('Rotring',4),
       ('Not Defteri',2),
       ('Roman 2',1),
       ('Roman 3',1);
