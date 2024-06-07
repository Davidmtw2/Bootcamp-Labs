#1. Retrieve all the columns from the Customers table for customers who are from the UK.
SELECT * FROM Customers
WHERE Country = 'UK';

#2. Using the Products table, list the product names and their unit prices where the unit price is greater than 30.
SELECT ProductName, UnitPrice FROM Products
WHERE UnitPrice > 30;

#3. Count the number of products in the Products table that have been discontinued.
SELECT COUNT(*) as DiscontinuedItems FROM Products
WHERE Discontinued = 1;

#4. Find the average, maximum, and minimum unit prices from the Products table.
SELECT AVG(UnitPrice) AS AveragePrice,
       MAX(UnitPrice) AS MaxPrice,
       MIN(UnitPrice) AS MinPrice
FROM Products;

#5. Retrieve the names of categories and the count of products in each category from the Categories and Products tables.
SELECT Categories.CategoryName, COUNT(Products.ProductID) AS ProductCount
FROM Categories
JOIN Products ON Categories.CategoryID = Products.CategoryID
GROUP BY Categories.CategoryName;

#6. List the suppliers (SupplierID and CompanyName) from the Suppliers table whoare not from USA, Germany, or UK.
SELECT SupplierID, CompanyName FROM Suppliers
WHERE Country NOT IN ('USA', 'Germany', 'UK');

#7. Retrieve all the distinct countries from the Customers table.
SELECT DISTINCT Country FROM Customers;

#8. Find the top 5 most expensive products (Product name and Unit price) from the Products table.
SELECT ProductName, UnitPrice FROM Products
ORDER BY UnitPrice DESC
LIMIT 5;

#9. Using the Orders and Order Details tables, calculate the total revenue for each order (OrderID).
SELECT Orders.OrderID, SUM(`Order Details`.Quantity * `Order Details`.UnitPrice) AS TotalRevenue
FROM Orders
JOIN `Order Details` ON Orders.OrderID = `Order Details`.OrderID
GROUP BY Orders.OrderID;

#10. List all employees (FirstName and LastName) and the count of orders they have taken from the Employees and Orders tables.
SELECT Employees.FirstName, Employees.LastName, COUNT(Orders.OrderID) AS OrderCount
FROM Employees
JOIN Orders ON Employees.EmployeeID = Orders.EmployeeID
GROUP BY Employees.FirstName, Employees.LastName;

#11. Retrieve customers (CustomerID and CompanyName) who have placed more than 10 orders using the Customers and Orders tables.
SELECT Customers.CustomerID, Customers.CompanyName
FROM Customers
JOIN Orders ON Customers.CustomerID = Orders.CustomerID
GROUP BY Customers.CustomerID, Customers.CompanyName
HAVING COUNT(Orders.OrderID) > 10;

#12. From the Products table, retrieve the names of products that are out of stock (units in stock is 0).
SELECT ProductName FROM Products
WHERE UnitsInStock = 0;

#13. Using the Products and Categories tables, list the names of products and their respective categories where the category is either 'Beverage' or 'Confectionery'.
select products.productname, categories.CategoryName
from products
join categories on products.categoryID = categories.categoryID
where categories.categoryName in ('Beverage', 'Confectionery');


#14. Identify which supplier (SupplierID and CompanyName from Suppliers table) has the highest number of products in the Products table.
SELECT Suppliers.SupplierID, Suppliers.CompanyName, COUNT(Products.ProductID) AS ProductCount
FROM Suppliers
JOIN Products ON Suppliers.SupplierID = Products.SupplierID
GROUP BY Suppliers.SupplierID, Suppliers.CompanyName
ORDER BY ProductCount DESC
LIMIT 1;

#15. List all the products (ProductID and ProductName) which have never been ordered. Use the Products and Order Details tables.
SELECT ProductID, ProductName FROM Products
WHERE ProductID NOT IN (SELECT DISTINCT ProductID FROM `Order Details`);

#16. Retrieve all orders (OrderID from Orders table) that were placed in December 1997.
SELECT OrderID FROM Orders
WHERE OrderDate BETWEEN '1997-12-01' AND '1997-12-31';

#17. Using the Employees and Orders tables, find out which employee has processed the most number of orders in 1998.
SELECT Employees.EmployeeID, Employees.FirstName, Employees.LastName, COUNT(Orders.OrderID) AS OrderCount
FROM Employees
JOIN Orders ON Employees.EmployeeID = Orders.EmployeeID
WHERE YEAR(Orders.OrderDate) = 1998
GROUP BY Employees.EmployeeID, Employees.FirstName, Employees.LastName
ORDER BY OrderCount DESC
LIMIT 1;

#18. Find the customer (CustomerID and CompanyName from Customers table) who has purchased the most by quantity in the Order Details table.
SELECT Customers.CustomerID, Customers.CompanyName, SUM(`Order Details`.Quantity) AS TotalQuantity
FROM Customers
JOIN Orders ON Customers.CustomerID = Orders.CustomerID
JOIN `Order Details` ON Orders.OrderID = `Order Details`.OrderID
GROUP BY Customers.CustomerID, Customers.CompanyName
ORDER BY TotalQuantity DESC
LIMIT 1;

#19. From the Shippers and Orders tables, determine which shipper has delivered the most number of orders.
SELECT Shippers.ShipperID, Shippers.CompanyName, COUNT(Orders.OrderID) AS OrderCount
FROM Shippers
JOIN Orders ON Shippers.ShipperID = Orders.ShipperID
GROUP BY Shippers.ShipperID, Shippers.CompanyName
ORDER BY OrderCount DESC
LIMIT 1;

#20. Identify the top 3 categories in terms of the number of products they have using the Categories and Products tables.
SELECT Categories.CategoryID, Categories.CategoryName, COUNT(Products.ProductID) AS ProductCount
FROM Categories
JOIN Products ON Categories.CategoryID = Products.CategoryID
GROUP BY Categories.CategoryID, Categories.CategoryName
ORDER BY ProductCount DESC
LIMIT 3;
