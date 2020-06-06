USE [restomation]
GO
 -- select * from cart
--exec deleteorModifyCartItems '1' ,'14','0'
alter PROCEDURE deleteorModifyCartItems
(
	 @isDelete bit,
	--@userId	int,
	@cartId int,
	@quantity int
)
AS
BEGIN
	
	SET NOCOUNT ON;

	BEGIN -- Variables

		DECLARE @_isdelete		bit = @isDelete	
		--DECLARE @_userId	int	 = @userId	
		DECLARE @_cartId	int	 = @cartId
		DECLARE @_quantity	int	 = @quantity	

		--DECLARE @_deleted_date datetime = NULL
		--DECLARE @_currentTime	datetime = GETDATE()
	END

	/*if(@quantity=0)
	begin
		set @_quantity = null;
	end*/

	BEGIN 
		
		--SELECT * FROM cart WHERE cartId=@_cartId
		 
		IF @_isdelete=0
			BEGIN
				UPDATE Cart 
				SET quantity = @_quantity
				where cartId=@_cartId

				if(@@ROWCOUNT > 0)
			begin
				Select '1' as 'StatusCode', 'Quantity updated' as 'StatusMessage';
			end
			else
			begin
				Select '0' as 'StatusCode', 'Failed to update' as 'StatusMessage';
			end

				/*SELECT 
					
				Cart.cartId as 'cartId'
				,Cart.quantity as 'quantity'
				,(MenuItems.price*Cart.quantity ) as 'price'
						
				FROM Cart JOIN MenuItems
				ON Cart.menuItemId=MenuItems.itemId
				WHERE cartId = @_cartId*/
			END

		ELSE IF @_isdelete=1
			BEGIN
				DELETE FROM Cart
				where 
				cartId=@_cartId

				if(@@ROWCOUNT > 0)
			begin
				Select '1' as 'StatusCode', 'Deleted from Cart' as 'StatusMessage';
			end
			else
			begin
				Select '0' as 'StatusCode', 'Failed to delete from Cart' as 'StatusMessage';
			end

				/*SELECT cartId from
				Cart 
				where addedBy=@_userId*/



			END
			
			 
			
		ELSE
		BEGIN
			SELECT '003' AS 'ErrorCode', 'Couldnt delete' AS 'ErrorMessage'
		END

	END

END
GO