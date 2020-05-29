/*
- 29/5/2020
*/
--- Get Posts by AccountID (has list Post of this Account)
CREATE PROC GetPostsByAccountID ( @AccountID int )
AS
BEGIN
	SELECT * FROM Posts p, Pictures pic
	WHERE p.AccountID = @AccountID and p.PostID = pic.PostID
END
GO

--- Get Info Account by AccountID
CREATE PROC GetAccountByID ( @AccountID int )
AS
BEGIN
	select * from Accounts
	where AccountID = @AccountID
END
GO

--- Get detail post by id
CREATE PROC GetDetailPostByID ( @PostID int )
AS
BEGIN
	SELECT * FROM Posts p, Pictures pic
	WHERE p.PostID = pic.PostID and p.PostID = @PostID
END
GO

/*
- 26/5/2020
*/
--- Search post by keyword with PostName, Address, CategoryName
CREATE PROC SearchPost ( @kw nvarchar(100) )
AS
BEGIN
	with tam as (
		select p.PostID from Posts p, FoodCategories c, Post_FoodCategory pc
		where p.PostID = pc.PostID and c.CategoryID = pc.CategoryID and
				PostName like N'%' + @kw + '%' or
				CategoryName like N'%' + @kw + '%' or
				Address like N'%' + @kw + '%'
		group by p.PostID)

	select * from Posts where PostID in ( select PostID from tam)
END
GO

--- Add post with Picture
CREATE PROC AddPost(@AccountId int,@PostName nvarchar(50),@Content ntext, @Address nvarchar(100), @PictureURL ntext)
AS
BEGIN
	INSERT into Posts values(@AccountId, @PostName, @Content, @Address)
	declare @postId int
	select @postId = PostID from Posts where AccountId = @AccountId and PostName = @PostName and Content like @Content and Address = @Address

	INSERT Pictures values(@postId, @PictureURL)
END
GO