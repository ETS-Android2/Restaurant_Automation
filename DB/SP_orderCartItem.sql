USE [restomation]
GO

/****** Object:  StoredProcedure [dbo].[addToCart]    Script Date: 30-05-2020 01:54:52 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO




CREATE procedure [dbo].[orderCartItem]
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
DECLARE @_billAmount int = null
END

BEGIN
Select @_reservationId = Reservation.reservationId from Reservation where (endTime = null or endTime = '') and reservedBy = @orderBy;
if(@@ROWCOUNT > 0)
begin
Insert into Orders(orderDate, reservationId, isDiningIn) values (CURRENT_TIMESTAMP, @_reservationId, @isDiningIn);
Select @_orderId = Orders.orderId from Orders where Orders.reservationId = @_reservationId;
Insert into OrderHistory(statusId, createdTime) values (1, CURRENT_TIMESTAMP);


WHILE EXISTS(Select * from Cart where Cart.isOrdered = 'false' and Cart.addedBy = @orderBy)
BEGIN
SELECT TOP 1 @_cartId =  Cart.cartId, @_menuItemId = Cart.menuItemId, @_quantity = Cart.quantity FROM  Cart;
Insert into OrderDetails(itemId, itemQty) values(@_menuItemId, @_quantity);
Update Cart Set isOrdered = 'true' where cartId = @_cartId;
END

Select @_billAmount = SUM(MenuItems.price * OrderDetails.itemQty) from OrderDetails Inner Join MenuItems on MenuItems.itemId = OrderDetails.itemId where OrderDetails.orderId = @_orderId
Insert into Bills(orderId, billDate, billingAmount, isCardPayment) values(@_orderId, CURRENT_TIMESTAMP, @_billAmount, @isCardPayment);
Select Bills.BillId as billId, Bills.BillingAmount as billAmount, Bills.BillDate from Bills where orderId = @_orderId;
end
else
begin
Select '404' as 'ErrorCode', 'No reservation found for this user' as 'Error Message';
end
END
END
GO