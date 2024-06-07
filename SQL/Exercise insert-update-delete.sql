#1
INSERT INTO Suppliers (
CompanyName, 
ContactName, 
ContactTitle, 
Address, 
City, 
Region, 
PostalCode, 
Country, 
Phone, 
Fax, 
HomePage)
VALUES ('New Supplier', 
'John Doe', 
'Sales Representative', 
'123 New St', 
'New York City', 
'NY', 
'12345', 
'USA', 
'123-456-7890', 
'123-456-7891', 
'http://www.newsupplier.com');

#2
INSERT INTO Products (
ProductName, 
SupplierID, 
CategoryID, 
QuantityPerUnit, 
UnitPrice, 
UnitsInStock, 
UnitsOnOrder, 
ReorderLevel, 
Discontinued)
VALUES ('New Product', 
(SELECT SupplierID 
FROM Suppliers 
WHERE CompanyName = 'New Supplier'),
1, '10 boxes x 20 bags', 20.00, 50, 0, 10, 0);

#3
SELECT Products.ProductName, Suppliers.CompanyName
FROM Products
JOIN Suppliers ON Products.SupplierID = Suppliers.SupplierID;

#4
UPDATE Products
SET UnitPrice = UnitPrice * 1.15
WHERE ProductName = 'New Product';

#5
SELECT Products.ProductName, Products.UnitPrice
FROM Products
JOIN Suppliers ON Products.SupplierID = Suppliers.SupplierID
WHERE Suppliers.CompanyName = 'New Supplier';

#6
DELETE FROM Products
WHERE ProductName = 'New Product';

#7
DELETE FROM Suppliers
WHERE CompanyName = 'New Supplier';

#8
SELECT ProductName
FROM Products;

#9
SELECT CompanyName
FROM Suppliers;
