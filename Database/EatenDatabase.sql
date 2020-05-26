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
	AvatarURL ntext,
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
	PictureURL ntext,

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
values ('1751012086yen@ou.edu.vn', '1', N'Trịnh Hoàng Yến', 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/IMG_1872.jpg?alt=media&token=80dc13ea-ad78-4450-902b-38a7ccc52810', N'Nữ', 1999),
		('1751010177tu@ou.edu.vn', '1', N'Nguyễn Thanh Tú', 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/IMG_1125.jpg?alt=media&token=ffafca3b-4896-429a-b09d-66f7200a71b5', N'Nam', 1999),
		('1751010085minh@ou.edu.vn', '1', N'Lưu Hoàng Minh', 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/IMG_1140_Original.jpg?alt=media&token=d53edcc6-6554-4458-8bc0-916ba991bd6a', N'Nam', 1999),
		('1751010046huy@ou.edu.vn', '1', N'Nguyễn Hoàng Huy', 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/IMG_0680.jpg?alt=media&token=f45ecfb2-1de4-4e81-9c35-5bfc7caba17d', N'Nam', 1999),
		('tu', '1', N'noname', 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/IMG_1125.jpg?alt=media&token=ffafca3b-4896-429a-b09d-66f7200a71b5', N'Nam', 2000),
		('tu', '1', N'vodanh001', 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/avatar.jpg?alt=media&token=4c6945d4-33a1-4c2c-8f03-27d20219a4ca', N'Nam', 1998),
		('ssa123@gmail.com', '1', 'k001', 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/IMG_1140_Original.jpg?alt=media&token=d53edcc6-6554-4458-8bc0-916ba991bd6a', 'female', 1996)

INSERT Posts
values (1, N'Belgo Belgian Craft Beer Brewery - Lê Ngô Cát', 
			N'Belgo mới mở ở số 2 Lê Ngô Cát, ngay trung tâm quận 3 không gian thoáng hơn, trang trí nội thất cũng theo phong cách cũ, có khoảng ngoài trời khá mát mẻ.
			Mình đi được 2 lần, khá hài lòng. Beer ngon (lạnh) ly cũng đẹp, đồ ăn ngon. Dịp cuối năm ráng sắp xếp hội họp với bạn bè ở đây khá là lý tưởng', 
			N'2 Lê Ngô Cát, P. 7, Quận 3, TP. HCM'),
		(2, N'The Long coffee',
			N'Uống từ 2018 tới nay mới vô review cho quán. Món uống nhiều nhất của mình là mocha nóng, rất ngon. Dạo này có thử thêm trà ổi cũng thơm không kém. Điểm trừ chút xíuu là các món đá nhưng quán không có coaster lót ly nên nước chảy ra bàn hơi khó chịu xíuuuuu xiu.',
			N'Số 3 Phổ Quang, P.2, Q.Tân Bình'),
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
			N'333 Lê Văn Sỹ, P. 13, Quận 3, TP. HCM'),
		(2, N'RuNam Bistro - Mạc Thị Bưởi', 
			N'Đây là 1 điểm hèn họ có lẽ là khá lý tưởng vì không gian nhẹ nhàng ấm cúng và lịch sự, décor theo gam màu trầm nên mình rất thích, sang trọng. Từ không gian cho đến cách phục vụ mình đều cảm thấy thoải mái, nên ấn tượng từ khi bước vào rất tốt!',
			N'96 Mạc Thị Bưởi, Quận 1, TP. HCM'),
		(4, N'The Open Space - Bakery & Coffee - Võ Thị Sáu', 
			N'Coffee nhẹ nhàng, có không gian làm bánh khá thú vị được mình thêm vào danh sách quán ừa thích của mình (*Ngoại trừ đường vào lần đầu hơi khó tìm',
			N'232/13 Võ Thị Sáu, P. 7,  Quận 3, TP. HCM'),
		(7, N'Phúc Long Crescent Mall',
			N'Lần đầu đến đây rất ấn tượng, các món nước đa dạng, cafe thơm mùi đặc trưng mình rất thích, nhân viên phục vụ chu đáo tận răng',
			N'số 101 Tôn Dật Tiên, Phú Mỹ Hưng, quận 7, Hồ Chí Minh'),
		(7, N'HighLands coffee',
			N'Địa điểm ưa thích nhưng hôm nay mới lên app review được. View quán đẹp, thức uống khá ngon, mình rất thích các món desert ở đây. Sẽ quay lại',
			N'172 Nguyễn Văn Khối, Phường 10, Gò Vấp, Hồ Chí Minh')
			-- delete Pictures
INSERT Pictures
values (1, 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/foody-upload-api-foody-mobile-6-jpg-181115113413.jpg?alt=media&token=493aec28-38e7-4851-9586-14b517b0919b'),
		(2, 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/foody-upload-api-foody-mobile-9-190306142316.jpg?alt=media&token=9212b882-a8fe-4c5f-99f4-479f12ee25f8&fbclid=IwAR22dGyH_XA7Pr1pZh7-DoqD62KVCR4MI9IICmZnDc8d7IOyXdeQERqUumU'),
		(3, 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/foody-mobile-img_0954-jpg-344-635947742274439596.jpg?alt=media&token=d131f7e4-0146-4812-a7d4-4d07723e0c28'),
		(4, 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/foody-upload-api-foody-mobile-20-190620104031.jpg?alt=media&token=6a4c7a0b-c5a3-4161-aac8-04fdb0a7b534'),
		(5, 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/foody-upload-api-foody-mobile-20-190620104031.jpg?alt=media&token=6a4c7a0b-c5a3-4161-aac8-04fdb0a7b534'),
		(6, 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/unnamed.jpg?alt=media&token=0002b199-25b5-4803-809a-a93454dc7954&fbclid=IwAR0E_24lbieVQGOg7kTW1Y7PBnnSt6gAHa3b2s9RSiLnSI35mKEXcjM74q4'),
		(7, 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/L1117508.jpg?alt=media&token=05d3d20d-32fd-4e77-bde4-915d4d7ac320&fbclid=IwAR0rtS6m2FpZ2MR_12uctEEphbNqMQODqfi3UqHZsLgyCMCZjnQZox44hV0'),
		(8, 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/4687824921934776476368563682900723646857216n-min-15785681307161988536441-crop-15785681375501813632109.png?alt=media&token=6cb629ec-483a-4bde-89e5-314c4c0a0547&fbclid=IwAR0JRb7kAtMWpy0b3cB97j-JmqDD9Mxgp-54zSWi3IeWhae8cPq255kFJj4'),
		(9, 'https://firebasestorage.googleapis.com/v0/b/eaten-9a975.appspot.com/o/highland-coffee-nha-trang-2.jpg?alt=media&token=8875dde2-7a1d-45d3-a438-48e9066d33c1&fbclid=IwAR2oyRaUhk1JSQgS8V3za51Wb2wxQ74bRcrAACs7ll605oUMS-LUB2oSHMQ')

INSERT Comments
values (1, 4, N'Theo mình món này những bạn sợ béo sẽ không thích', 0, null),
		(2, 3, N'Cùng quan điểm với chủ thớt', 0, null),
		(3, 1, N'Mình thấy quán này ngon ở món lẩu Sundubu, nhưng đông quá chắc là do ngon :)))', 1, null),
		(3, 2, N'Phù hợp cho nhóm bạn bè đi đông', 0, null),
		(4, 1, null, 1, null),
		(2, 1, N'Mình với người yêu rất thích đến đây ăn', 1, 5),
		(3, 1, N'Chờ quá lâu, đông và ồn ào', 0, 1),
		(4, 1, null, 1, null),
		(1, 4, null, 0, 1),
		(5, 1, N'Ngon vãi chưởng', 0, null),
		(6, 2, N'Review có tâm', 0, null),
		(7, 3, N'Mlem mlem', 0, null),
		(8, 7, N'nai xừ', 0, null),
		(9, 7, N'mi tu', 1, null),
		(4, 7, N'Mình sẽ thử', 0, null),
		(3, 7, N'huhu đói bụng quá đi à', 0, null),
		(8, 4, null, 1, null),
		(7, 7, null, 0, 3),
		(6, 7, null, 1, 4),
		(2, 3, null, 0, 5)
-- delete Comments

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
values (1, 10), (1, 7), (1, 8),
		(2, 9), (2, 5), (2, 7),
		(3, 2), (3, 5),
		(4, 3), (4, 4),
		(5, 1), (5, 4), (5, 9), (5, 3),
		(6, 6), (6, 1), (6, 9),
		(7, 3), (7, 8),
		(8, 8), (8, 7), (8, 5),
		(9, 1), (9, 2), (9, 3), (9, 10)
		-- DELETE Post_FoodCategory