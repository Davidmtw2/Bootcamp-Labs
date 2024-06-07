Select * From northwind.products;

Update products
set unitsinstock = 46, unitsonorder = 33
where productID = 1;

#Example of a hard delete
Delete 
From Customers
where CustomerID = 'ZODRA';

select * From customers;

#Making Your Own Tables
Create Table pokemon(
	PokemonID INT auto_increment,
    MoveSet VARCHAR(100) Not Null,
    PokemonType Varchar(10),
    NAME Varchar(50) Not null,
    HP INT,
    IsEvolved bit,
    Primary key (PokemonID)
);

#To put data into a table
INSERT INTO pokemon(MoveSet, PokemonType, HP, IsEvolved, pokemonName)
Values ('Blasting things with lightning', 'Electric', 50, 1, 'Pikachu'),
('Hits things with water', 'Water', 65, 0, 'Squirtle'),
('Creeped me out as a kid', 'Psychic', 1000, 0, 'MewTwo');

ALTer Table pokemon
Drop Column name;

Alter table pokemon
ADD pokemonName Varchar(50);

Alter table pokemon
add trainername varchar(100) not null;

#Foreign keys
Create table authors(
AuthorID INT auto_increment,
AuthorName varchar(100),
Primary key (AuthorID)
);

Create table books(
	BookID Int auto_increment,
	BookTitle Varchar(225),
	AuthorID int,
	Primary key (BookID),
	foreign Key (AuthorID) REFERENCES authors(authorID)
);

Insert into authors(authorName)
values('Isaac Asimov'), ('CiXin Liu'), ('Terry Pratchett');

Insert into books(Booktitle, AuthorID)
Values('The Dark Knight Rises', 2);