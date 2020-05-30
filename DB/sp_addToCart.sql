USE [restomation]
GO
/****** Object:  StoredProcedure [dbo].[addToCart]    Script Date: 2020-05-29 10:14:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



ALTER procedure [dbo].[addToCart]
(
	@itemId int,
	@addedBy int,
	@qty int
)
AS
BEGIN
	SET NOCOUNT ON;
	BEGIN
		DECLARE @_cartId int = null
	END

	BEGIN
		Select @_cartId = Cart.cartId from Cart where Cart.menuItemId = @itemId and Cart.isOrdered = 'false' and Cart.addedBy = @addedBy;
		if(@_cartId is null)
		begin
			Insert into Cart(menuItemId, addedBy, quantity, isOrdered) values (@itemId, @addedBy, @qty, 'false');
			if(@@ROWCOUNT > 0)
			begin
				Select '1' as 'StatusCode', 'Added to cart successfully' as 'StatusMessage';
			end
			else
			begin
				Select '0' as 'StatusCode', 'Failed to add to Cart' as 'StatusMessage';
			end
		end
		else
		begin
			Update Cart set quantity = (quantity + @qty), addedBy = @addedBy where cartId = @_cartId;
			if(@@ROWCOUNT > 0)
			begin
				Select '1' as 'StatusCode', 'Added to cart successfully' as 'StatusMessage';
			end
			else
			begin
				Select '0' as 'StatusCode', 'Failed to add to Cart' as 'StatusMessage';
			end
		end
	END
END
