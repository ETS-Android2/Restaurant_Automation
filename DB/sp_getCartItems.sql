USE [restomation]
GO
--select * from cart;
--exec getCartItems '22' 
Alter procedure getCartItems
(
	@userId INT	
)
AS
BEGIN

	SET NOCOUNT ON;

	BEGIN -- Declare Variables
		DECLARE @_userId    INT = @userId

	
	END
	
	BEGIN
		
		SELECT
		*
		FROM
		(
		SELECT 
			cart.cartId,cart.menuItemId,cart.quantity,cart.addedBy,MenuItems.itemName,MenuItems.itemDescription,(MenuItems.price*Cart.quantity ) as 'Price',MenuItems.itemImage
			,menuitems.itemStatusTitle,sum(cart.quantity*menuitems.price) as 'TotalPrice'
		FROM 
		Cart JOIN MenuItems 
		On 
		Cart.menuItemId=menuItems.itemId


		

		WHERE 
		cart.addedBy=@_userId AND
		cart.isOrdered=0
		GROUP BY
		cart.cartId,cart.menuItemId,cart.quantity,cart.addedBy,MenuItems.itemName,MenuItems.itemDescription,Price,MenuItems.itemImage
			,menuitems.itemStatusTitle
		) AS t
		
		END
END
