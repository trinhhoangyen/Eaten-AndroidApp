/*
- 26/5/2020
*/
CREATE PROC SearchPost
(
	@kw nvarchar(100)
)
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
EXEC SearchPost N'Hồ Chí Minh'