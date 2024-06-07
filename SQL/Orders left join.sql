#Orders table is the left column
Select orders.OrderID, employees.EmployeeID, employees.FirstName, employees.LastName,
customers.CompanyName
From orders
JOIN employees ON Orders.EmployeeID = Employees.EmployeeId
JOIN Customers ON Orders.CustomerID = Customers.CustomerId;

Select Orders.OrderId, CustomerID.CustomerID
From orders
Join Customers ON Orders.CustomerID = CustomerID
WHERE customers.CustomerID = 'ZODRA';

#Which Customers do we have that dont have any orders:
Select Orders.OrderID, Orders.CustomerID, Customers.CustomerID
From Customers
Left Join Orders ON Customers.customerID = Orders.CustomerID
WHERE Orders.OrderID IS NULL;

