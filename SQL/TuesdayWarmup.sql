#1. Write a SQL query to select all columns from the Customers table.
select * 
From Customers;
#2. Write a SQL query to select only the CompanyName and ContactName from the Customers table.
select CompanyName, ContactName 
From Customers;
#3. Write a SQL query to find all distinct Country values in the Customers table.
select Distinct Country 
From Customers;
#4. Write a SQL query to find all customers from the Customers table who are from the UK.
select * 
from Customers 
WHERE Country = 'UK';
#5. Write a SQL query to list all orders from the Orders table where the Freight is between 50 and 100.
SELECT * 
FROM Orders 
WHERE Freight BETWEEN 50 and 100;
#6. Write a SQL query to find all orders from the Orders table where the OrderDate is after '1997-01-01' AND the ShipCountry is either 'USA' or 'Canada'.
SELECT * 
FROM Orders 
WHERE OrderDate > '1997-01-01' AND ShipCountry IN ('USA', 'Canada');
#7. Write a SQL query to display orders from the Orders table where the ShipCountry is in ('France', 'Belgium', 'Germany').
SELECT * 
FROM Orders 
WHERE ShipCountry IN ('France', 'Belgium', 'Germany');
#8. Write a SQL query to select all products from the Products table with a UnitPrice between 10 and 20.
SELECT * 
FROM Products 
WHERE UnitPrice BETWEEN 10 AND 20;
#9. Write a SQL query to select all suppliers from the Suppliers table and order them by Country in ascending order.
SELECT * 
FROM Suppliers 
ORDER BY Country ASC;
#10. Write a SQL query to list all customers from the Customers table and sort them by ContactName in descending order.
SELECT * 
FROM Customers 
ORDER BY ContactName DESC;
#11. Ask students to identify the data types of columns in the Employees table.
#varchar"string"  
#12. Write a SQL query to find all orders in the Orders table where the OrderDate is '1996-07-04'.
SELECT * 
FROM Orders 
WHERE OrderDate = '1996-07-04';
#
SELECT CustomerID FROM Orders WHERE OrderID = 10248;
SELECT CompanyName FROM Customers WHERE CustomerID = 'VINET';