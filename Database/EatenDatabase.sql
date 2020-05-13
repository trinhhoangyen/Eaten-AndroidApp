/*
=== Person: Trịnh Hoàng Yến
=== Time: 13/5/2020
=== Description: create 6 tables, insert data
*/
CREATE DATABASE EatenDatabase
GO
USE EatenDatabase
GO

CREATE TABLE Accounts
(
	AccountID int identity(1,1) primary key,
	Email varchar(50),
	Password char(20),
	DisplayName nvarchar(50),
	Avatar image,
	Gender nvarchar(10),
	YearOfBirth int
)

CREATE TABLE Posts
(
	PostID int identity(1,1) primary key,
	AccountID int,
	PostName nvarchar(50),
	Content nText,
	Address nvarchar(100),

	foreign key (AccountID) references Accounts (AccountID)
)

CREATE TABLE Pictures
(
	PictureID int identity(1,1) primary key,
	PostID int,
	Picture image,

	foreign key (PostID) references Posts (PostID)
)

CREATE TABLE Comments
(
	CommentID int identity(1,1),
	PostID int,
	AccountID int,
	Content ntext,
	CamXuc int default 0,	--- 0 và 1
	DanhGia int, --- 1 tới 5

	primary key(CommentID, PostID, AccountID),
	foreign key (PostID) references Posts (PostID),
	foreign key (AccountID) references Accounts (AccountID)
)

CREATE TABLE FoodCategories
(
	CategoryID int identity(1,1) primary key,
	CategoryName nvarchar(20)
)

CREATE TABLE Post_FoodCategory
(
	PostID int,
	CategoryID int,
	
	primary key(PostID, CategoryID),
	foreign key (PostID) references Posts (PostID),
	foreign key (CategoryID) references FoodCategories (CategoryID)
)

--- 
INSERT Accounts
values ('1751012086yen@ou.edu.vn', '1', N'Trịnh Hoàng Yến', null, N'Nữ', 1999),
		('1751010177tu@ou.edu.vn', '1', N'Nguyễn Thanh Tú', null, N'Nam', 1999),
		('1751010085minh@ou.edu.vn', '1', N'Lưu Hoàng Minh', null, N'Nam', 1999),
		('1751010046huy@ou.edu.vn', '1', N'Nguyễn Hoàng Huy', null, N'Nam', 1999)

INSERT Posts
values (1, N'', N'', N''),
		(2, N'', N'', N''),
		(3, N'', N'', N''),
		(4, N'', N'', N'')

INSERT Pictures
values (1, null),
		(2, null),
		(3, null),
		(4, null)

INSERT Comments
values (1, 4, N'', 0, null),
		(2, 3, N'', 0, null),
		(3, 2, null, 1, null),
		(4, 1, null, 1, null),
		(1, 2, null, 0, 1),
		(2, 3, null, 0, 5)

INSERT FoodCategories
values (N'Ăn vặt'),
		(N'Salad'),
		(N'Tráng miệng'),
		(N'Bánh ngọt'),
		(N'Ăn kiêng'),
		(N'Đồ uống'),
		(N'Món chính'),
		(N'Chay'),
		(N'Nước'),
		(N'Khô')

INSERT Post_FoodCategory
values (1, 10),
		(2, 9),
		(3, 8),
		(4, 7),
		(1, 6),
		(2, 5),
		(3, 4),
		(4, 3),
		(1, 2),
		(2, 1)