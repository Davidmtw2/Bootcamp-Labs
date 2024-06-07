#1
Select Products.ProductID, Products.ProductName, Products.UnitPrice, Categories.categoryName
From Products
Join Categories ON Products.CategoryID = Categories.CategoryID
ORDER BY Categories.CategoryName, Products.ProductName;

#2
Select products.ProductID, products.ProductName, Products.UnitPrice, Suppliers.CompanyName
As SupplierName 
From products
Join Suppliers ON Products.SupplierID = Suppliers.SupplierID
WHERE Products.UnitPrice > 75
ORDER BY Products.ProductName;

#3
Select products.ProductID, products.ProductName, products.UnitPrice, categories.CategoryName,
Suppliers.CompanyName As SupplierName
From products
Join categories on products.CategoryID = categories.CategoryID
Join suppliers on products.SupplierID = suppliers.SupplierID
Order by products.ProductName;

#4


#5
Select Orders.orderID, orders.shipName, orders.ShipAddress, shippers.CompanyName 
From orders
Join Shippers on Orders.ShipVia =Shippers.shipperId
Where Orders.shipCountry = 'Germany';

#6
Select Orders.OrderID, Orders.OrderDate, Orders.ShipName, Orders.ShipAddress, Products.ProductName
From Orders
Join `order details` On Orders.orderId = `order details`.OrderID
Join Products on `order details`.ProductID = products.productID
Where Products.productName = 'Sasquatch Ale';


Select * From Products;
Select * From Suppliers;
Select * From Shippers;
Select * From Categories;
Select * From Orders;
select * From `order Details`;