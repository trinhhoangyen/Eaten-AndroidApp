/*
=== Person: Trịnh Hoàng Yến
=== Time: 12/5/2020
=== Description: create 6 tables, insert data
*/
CREATE DATABASE Eaten
GO
USE Eaten
GO

CREATE TABLE TaiKhoan
(
	MaTaiKhoan int identity(1,1) primary key,
	Email varchar(50),
	MatKhau char(20),
	TenHienThi nvarchar(50),
	Avatar image,
	GioiTinh nvarchar(10),
	NamSinh int
)

CREATE TABLE BaiDang
(
	MaBai int identity(1,1) primary key,
	MaTaiKhoan int,
	TenBai nvarchar(50),
	NoiDung nText,
	DiaChi nvarchar(100),

	foreign key (MaTaiKhoan) references TaiKhoan (MaTaiKhoan)
)

CREATE TABLE HinhAnh
(
	MaHinh int identity(1,1) primary key,
	MaBai int,
	Hinh image,

	foreign key (MaBai) references BaiDang (MaBai)
)

CREATE TABLE TuongTac
(
	MaBinhLuan int identity(1,1),
	MaBai int,
	MaTaiKhoan int,
	NoiDung ntext,
	CamXuc int default 0,	--- 0 và 1
	DanhGia int, --- 1 tới 5

	primary key(MaBinhLuan, MaBai, MaTaiKhoan),
	foreign key (MaBai) references BaiDang (MaBai),
	foreign key (MaTaiKhoan) references TaiKhoan (MaTaiKhoan)
)

CREATE TABLE TheLoai
(
	MaLoai int identity(1,1) primary key,
	TenLoai nvarchar(20)
)

CREATE TABLE BaiDang_TheLoai
(
	MaBai int,
	MaLoai int,
	
	primary key(MaBai, MaLoai),
	foreign key (MaBai) references BaiDang (MaBai),
	foreign key (MaLoai) references TheLoai (MaLoai)
)

--- 
INSERT TaiKhoan
values ('1751012086yen@ou.edu.vn', '1', N'Trịnh Hoàng Yến', null, N'Nữ', 1999),
		('1751010177tu@ou.edu.vn', '1', N'Nguyễn Thanh Tú', null, N'Nam', 1999),
		('1751010085minh@ou.edu.vn', '1', N'Lưu Hoàng Minh', null, N'Nam', 1999),
		('1751010046huy@ou.edu.vn', '1', N'Nguyễn Hoàng Huy', null, N'Nam', 1999)

INSERT BaiDang
values (1, N'', N'', N''),
		(2, N'', N'', N''),
		(3, N'', N'', N''),
		(4, N'', N'', N'')

INSERT HinhAnh
values (1, null),
		(2, null),
		(3, null),
		(4, null)

INSERT TuongTac
values (1, 4, N'', 0, null),
		(2, 3, N'', 0, null),
		(3, 2, null, 1, null),
		(4, 1, null, 1, null),
		(1, 2, null, 0, 1),
		(2, 3, null, 0, 5)

INSERT TheLoai
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

INSERT BaiDang_TheLoai
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