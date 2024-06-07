/*
< > <= >= = <> !=
*/
SELECT * FROM northwind.products
WHERE UnitsInStock < 30;

SELECT * 
FROM prducts
WHERE CategoryID <> 1;

#Compund Where clauses AND/OR
#I want products that are dairy products or beverages
SELECT *
FROM products
WHERE CategoryID = 1 OR CategoryID = 4 
#If you have a list of options
#I want tall products that are beverages, dairy products, or produce
SELECT * 
FROM products
WHERE CategoryID IN (1,4,7)

SELECT * FROM orders
WHERE ShipCountry ='France';

SELECT *
FROM orders
WHERE ShippedDate > '1996-09-01';

#I want orders shipped between September and October of 1996
SELECT *
From orders
where ShippedDate between '1996-09-01' and '1996--10-01';

#I want  orders where the sipping name starts with release savepoint
SElECT *
FROM orders
WHERE ShipName LIKE 'R%';

#I Want orders where the ship name contains the word Supper
SELECT *
From orders
WHERE ShipName LIKE '%Super%';
