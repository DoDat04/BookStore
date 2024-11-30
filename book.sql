CREATE DATABASE SE1824

CREATE TABLE Registration (
	username varchar(20) primary key,
	password varchar(30),
	lastname nvarchar(100),
	isAdmin bit
)

CREATE TABLE Product (
	sku int primary key NOT NULL,
	name nvarchar(50),
	description nvarchar(100),
	quantity int,
	price float,
	status bit
)

CREATE TABLE [Order] (
	id varchar(5) primary key NOT NULL,
	date datetime,
	customer varchar(50) NOT NULL,
	address nvarchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	total float
)

CREATE TABLE OrderDetail (
	id int identity(1,1) primary key NOT NULL,
	productid int,
	unitprice float,
	quantity int,
	orderId varchar(5),
	total float,
	foreign key(productid) REFERENCES dbo.Product(sku),
	foreign key(orderId) REFERENCES dbo.[Order](id),
)

INSERT INTO Registration (username, password, lastname, isAdmin) VALUES
('dothanhdat', '12345', 'dat', 1),
('legiahan', '12345', 'han', 0),
('nguyenhuukhoan', '12345', 'khoan', 0),
('se1824', '1234567', 'abc', 0),
('trantrungvan', '12345', 'van', 0);


INSERT INTO Product (sku, name, description, quantity, price, status) VALUES
(1, 'Java', 'Sach ve Java', 50, 45, 1),
(2, 'JDK', 'Sach ve JDK', 30, 33, 1),
(3, 'Servlet', 'Sach ve Servlet', 50, 44, 1),
(4, 'Tomcat', 'Sach ve Tomcat', 45, 77, 1),
(5, 'Expression', 'Sach ve Expression', 38, 62, 1),
(6, 'JavaBean', 'Sach ve JavaBean', 60, 32, 1),
(7, 'Scripting Element', 'Sach ve SE', 56, 22, 1),
(8, 'JSP', 'Sach ve JSP', 33, 41, 1),
(9, 'Doraemon', 'Sach ve Doraemon', 13, 15, 1),
(10, 'Conan', 'Sach ve Conan', 23, 17, 1),
(11, 'Python', 'Sach ve Python', 27, 19, 1),
(12, 'JavaScript', 'Sach ve JavaScript', 40, 50, 1),
(13, 'HTML', 'Cach hoc HTML', 25, 30, 1),
(14, 'CSS', 'Sach ve CSS', 20, 25, 1),
(15, 'ReactJS', 'Sach ve ReactJS', 55, 60, 1)



