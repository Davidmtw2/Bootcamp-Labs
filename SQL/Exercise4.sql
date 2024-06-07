#1
SELECT Products.ProductName
FROM Products
WHERE Products.UnitPrice = (SELECT MAX(Products.UnitPrice) FROM Products);

#2
SELECT Orders.OrderID, Orders.ShipName, Orders.ShipAddress
FROM Orders
JOIN Shippers ON Orders.ShipVia = Shippers.ShipperID
WHERE Shippers.CompanyName = 'Federal Shipping'
ORDER BY orderID;

SELECT Orders.OrderID, Orders.ShipName, Orders.ShipAddress
FROM Orders
WHERE Orders.ShipVia = 
(SELECT Shippers.ShipperID FROM Shippers 
WHERE Shippers.CompanyName = 'Federal Shipping');

#3
SELECT `Order Details`.OrderID
FROM `Order Details`
JOIN Products ON `Order Details`.ProductID = Products.ProductID
WHERE Products.ProductName = 'Sasquatch Ale';

SELECT `Order Details`.OrderID
FROM `Order Details`
WHERE `Order Details`.ProductID = 
(SELECT Products.ProductID FROM Products 
WHERE Products.ProductName = 'Sasquatch Ale');

#4
SELECT Employees.FirstName, Employees.LastName
FROM Employees
JOIN Orders ON Employees.EmployeeID = Orders.EmployeeID
WHERE Orders.OrderID = 10266;

SELECT Employees.FirstName, Employees.LastName
FROM Employees
WHERE Employees.EmployeeID = 
(SELECT Orders.EmployeeID FROM Orders WHERE Orders.OrderID = 10266);

#5
SELECT Customers.CompanyName
FROM Customers
JOIN Orders ON Customers.CustomerID = Orders.CustomerID
WHERE Orders.OrderID = 10266;

SELECT Customers.CompanyName
FROM Customers
WHERE Customers.CustomerID = 
(SELECT Orders.CustomerID FROM Orders WHERE Orders.OrderID = 10266);