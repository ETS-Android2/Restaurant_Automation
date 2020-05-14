/*
EXEC getFriends 0,0,5,1,10,'89a0bdc2-9696-407a-8c34-1fc6da8e4e0a', 2, NULL
*/

use RestaurantAutomation
Go

CREATE PROCEDURE getUsers
(
	 @userTypeId	INT				= NULL
	--,@email		VARCHAR(150)		= NULL
)
AS
BEGIN

	SET NOCOUNT ON;

	BEGIN -- Decalre Variables
		
		DECLARE @_userType		INT				= @userTypeId
		
		/*DECLARE @_email			VARCHAR(150)	= @email

	
		IF @_email = ''
		BEGIN
			SET @_email = NULL
		END*/

	END
	
	BEGIN
		
		SELECT
		*
		FROM
		(
		SELECT 
			 userId AS 'Id'
			,FirstName AS 'FirstName'
			,lastName AS 'LastName'
			,email AS 'Email'
			,phoneNo AS 'Phone'
			,profileImage AS 'Image'
			,userTypeId As 'UserType'
			,gender AS 'Gender'
			
		FROM USERS
		WHERE 
			--CONVERT(VARCHAR(100),id) != @_loggedInUser 
			--AND users.id NOT IN (select from_user from friends WHERE to_user = @_loggedInUser)
			--AND users.id NOT IN (select to_user from friends WHERE from_user = @_loggedInUser)
		 (@_userType IS NULL OR (userTypeId=@_userType))
		--AND (email=@_email)
		) AS t
		
		END

END