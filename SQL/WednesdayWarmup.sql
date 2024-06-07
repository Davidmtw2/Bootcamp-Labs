#1
Select Orders.OrderID, Orders.OrderDate, Customers.CompanyName
From Orders
JOIN Customers
ON Customers.CustomerID = Customers.CustomerID;

#2
Select Products.ProductName, Suppliers.CompanyName
From products
JOIN Suppliers
ON Products.supplierID = Suppliers.SupplierID;

#3
SELECT Employees.EmployeeID, Employees.LastName, Orders.OrderID
FROM Employees
JOIN Orders ON Employees.EmployeeID = Orders.EmployeeID
WHERE Orders.Freight > 100;