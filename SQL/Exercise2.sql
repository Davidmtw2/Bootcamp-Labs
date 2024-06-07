SELECT * from employees;
#1
SELECT COUNT(*) AS Total_Suppliers From Suppliers;
#2
SELECT SUM(Salary) As Total_Salaries From employees;
#3
SELECT MIN(UnitPrice) AS Cheapest_Item From Products;
#4
SELECT AVG(UnitPrice) AS Avg_Price From Products;
#5
SELECT MAX(UnitPrice) AS MostExpensive_Item From Products;
#6
SELECT COUNT(*) AS num_of_units, SupplierID
FROM products
GROUP BY SupplierID;

SELECT AVG(UnitPrice) As Avg_Category_price, CategoryID
From products
GROUP BY CategoryID;


SELECT ProductID, Product Name, UnitPrice * UnitsInStock
