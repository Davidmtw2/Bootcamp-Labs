#Aggregate Functions
#SUM, COUNT, AVG, MIN, MAX


Select * from Products;
#How much inventory do we have on hand?alter
select SUM(UnitsInStocks) AS Inventory_On_Hand 
From products;
select AVG(UnitePrice) AS Avg_Price from products;

SELECT MAX(UnitPrice) AS Most_Expensive from products;

Select MIN(UnitPrice) AS Least_Expensive from products;

Select COUNT(ProductID) From products where categoryID = 1;

select * from Orders;

SELECT AVG(Freight) AS Avg_Freight, ShipCountry
from Orders
WHERE ShipCountry IN ('UK', 'Germany', 'Belgium', 'Sweden')
GROUP BY ShipCountry
HAVING Avg_Freight > 50
ORDER BY Avg_Freight;