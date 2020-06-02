USE [restomation]
GO
/****** Object:  StoredProcedure [dbo].[orderCartItem]    Script Date: 01-06-2020 02:54:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO





ALTER procedure [dbo].[orderCartItem]
(
	@orderBy int, 
	@isDiningIn bit,
	@isCardPayment bit
)
AS
BEGIN
	SET NOCOUNT ON;
	BEGIN
		DECLARE @_cartId int = null
		DECLARE @_menuItemId int = null
		DECLARE @_quantity int = null
		DECLARE @_orderId int = null
		DECLARE @_reservationId int = null
		DECLARE @_billAmount decimal(6, 2) =null
	END

	BEGIN
		Select @_reservationId = Reservation.reservationId from Reservation where (endTime = null or endTime = '') and reservedBy = @orderBy;
		if(@_reservationId is not null)
		begin
			Insert into Orders(orderDate, reservationId, isDiningIn) values (CURRENT_TIMESTAMP, @_reservationId, @isDiningIn);
			Select @_orderId = Orders.orderId from Orders where Orders.reservationId = @_reservationId;
			Insert into OrderHistory(orderId, statusId, createdTime) values (@_orderId, 1, CURRENT_TIMESTAMP);
			if(@@ROWCOUNT > 0)
			begin
				Select * into #tempCart from Cart where Cart.isOrdered = 'false' and Cart.addedBy = @orderBy
				WHILE EXISTS(Select * from #tempCart)
				BEGIN
					SELECT TOP 1 @_cartId =  cartId, @_menuItemId = menuItemId, @_quantity = quantity FROM  #tempCart;
					Insert into OrderDetails(orderId, itemId, itemQty) values(@_orderId, @_menuItemId, @_quantity);
					Update Cart Set isOrdered = 'true' where cartId = @_cartId;
					delete #tempCart where cartId = @_cartId;
				END

				Select @_billAmount = SUM(MenuItems.price * OrderDetails.itemQty) from OrderDetails Inner Join MenuItems on MenuItems.itemId = OrderDetails.itemId where OrderDetails.orderId = @_orderId
				Insert into Bills(orderId, billDate, billingAmount, isCardPayment) values(@_orderId, CURRENT_TIMESTAMP, @_billAmount, @isCardPayment);
				Select Bills.BillId as billId, Bills.BillingAmount as billAmount, Bills.BillDate as billDate from Bills where orderId = @_orderId;

			end
			else
			begin
				Delete from Orders where orderId = @_orderId;
				Select '405' as 'ErrorCode', 'Failed to add Order' as 'ErrorMessage'
			end
		end
		else
		begin
			Select '404' as 'ErrorCode', 'No reservation found for this user' as 'ErrorMessage';
		end
	END
END
