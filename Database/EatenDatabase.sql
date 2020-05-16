/*
=== Person: Trịnh Hoàng Yến
=== Time: 13/5/2020
=== Description: create 6 tables, insert data
*/
CREATE DATABASE EatenDatabase
GO
USE EatenDatabase
GO

--- Create table
CREATE TABLE Accounts
(
	AccountID int identity(1,1) primary key,
	Email varchar(50) not null,
	Password char(20) not null,
	DisplayName nvarchar(50) not null,
	Avatar varchar(200),
	Gender nvarchar(10),
	YearOfBirth int
)

CREATE TABLE Posts
(
	PostID int identity(1,1) primary key,
	AccountID int not null,
	PostName nvarchar(50)  not null,
	Content nText  not null,
	Address nvarchar(100),

	foreign key (AccountID) references Accounts (AccountID)
)

CREATE TABLE Pictures
(
	PictureID int identity(1,1) primary key,
	PostID int,
	Picture varchar(200),

	foreign key (PostID) references Posts (PostID)
)

CREATE TABLE Comments
(
	CommentID int identity(1,1),
	PostID int,
	AccountID int,
	Content ntext,
	React int default 0,	--- 0: no like và 1: like
	Rate int, --- 1 tới 5

	primary key(CommentID, PostID, AccountID),
	foreign key (PostID) references Posts (PostID),
	foreign key (AccountID) references Accounts (AccountID)
)

CREATE TABLE FoodCategories
(
	CategoryID int identity(1,1) primary key,
	CategoryName nvarchar(20)  not null
)

CREATE TABLE Post_FoodCategory
(
	PostID int,
	CategoryID int,
	
	primary key(PostID, CategoryID),
	foreign key (PostID) references Posts (PostID),
	foreign key (CategoryID) references FoodCategories (CategoryID)
)

--- Insert data
INSERT Accounts
values ('1751012086yen@ou.edu.vn', '1', N'Trịnh Hoàng Yến', null, N'Nữ', 1999),
		('1751010177tu@ou.edu.vn', '1', N'Nguyễn Thanh Tú', null, N'Nam', 1999),
		('1751010085minh@ou.edu.vn', '1', N'Lưu Hoàng Minh', null, N'Nam', 1999),
		('1751010046huy@ou.edu.vn', '1', N'Nguyễn Hoàng Huy', null, N'Nam', 1999)

INSERT Posts
values (1, N'Belgo Belgian Craft Beer Brewery - Lê Ngô Cát', 
		N'Belgo mới mở ở số 2 Lê Ngô Cát, ngay trung tâm quận 3 không gian thoáng hơn, trang trí nội thất cũng theo phong cách cũ, có khoảng ngoài trời khá mát mẻ.
			Mình đi được 2 lần, khá hài lòng. Beer ngon (lạnh) ly cũng đẹp, đồ ăn ngon. Dịp cuối năm ráng sắp xếp hội họp với bạn bè ở đây khá là lý tưởng', 
			N'2 Lê Ngô Cát, P. 7, Quận 3, TP. HCM'),
		(2, N'Mì Ý Double B - Trần Hưng Đạo', 
			N'Quán mì ý ngon giá phải chăng tụi mình hay ghé. Mì vừa đủ ăn, sốt đậm đà hài hoà, nhiều sự lựa chọn trong tầm giá 40-70k nhé.
			Tuy nhiên salad rau héo quá, cần cải thiện.', 
			N'Hẻm 391 TK40/24 Trần Hưng Đạo, P. Cầu Kho, Quận 1, TP. HCM'),
		(3, N'Hanuri - Quán Ăn Hàn Quốc - Sư Vạn Hạnh', 
			N'Quán vô cùng đông.Đến giờ cao điểm la fai sếp hàng dài.Các bạn lưu ý đi sớm tí để sếp hàng nha.Đồ ăn fai nói cực ngon chất lượng giá cả lại vô cung hợp lí. 
			2 người 300k ăn gần chết.Nhân viên rất chuyên nghiệp trong viec sap xep cho ngoi và rat thân thiện.Rất thích quán', 
			N'736 Sư Vạn Hạnh P.12, Quận 10, TP. HCM'),
		(4, N'The Pizza Company - Lê Văn Sỹ', 
			N'So với domido thì hơi mắc,nhưng đồ ăn cũng ngon. Do thấy có khuyến mãi nên mình đến chứ cũng chưa biết có quay lại hay ko do giá hơi mắc.', 
			N'333 Lê Văn Sỹ, P. 13, Quận 3, TP. HCM')

INSERT Pictures
values (1, null),
		(2, null),
		(3, null),
		(4, null)

INSERT Comments
values (1, 4, N'Theo mình món này những bạn sợ béo sẽ không thích', 0, null),
		(2, 3, N'Cùng quan điểm với chủ thớt', 0, null),
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