USE [restomation]
GO

/****** Object:  StoredProcedure [dbo].[loginUser]    Script Date: 20-05-2020 11:59:56 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


--exec loginUser 'admin111@gmail.com','123456',5
CREATE PROCEDURE [dbo].[loginUser]
(
	 @email		varchar(150),
	@password	varchar(16)
)
AS
BEGIN
	
	SET NOCOUNT ON;

	BEGIN -- Variables

		DECLARE @_email		varchar(150) = @email	
		DECLARE @_password	varchar(64)	 = @password	

		SET @_password = CONVERT(VARCHAR(64),HASHBYTES('SHA2_256', @_password), 2)

		DECLARE @_userId int = NULL
		DECLARE @_deleted_date datetime = NULL
		DECLARE @_currentTime	datetime = GETDATE()
	END

	BEGIN -- login user
		
		SELECT @_userId = userid, @_deleted_date = deletedDate FROM users WHERE email = @_email AND password = @_password
		
		IF ISNULL(@_deleted_date, '') = '' 
		BEGIN
			IF EXISTS (select 1 from users where users.userId=@_userId) 
			
				BEGIN
					SELECT 
					concat(users.firstname, ' ', users.lastname) as 'Name',
					users.email as 'Email',
					users.userid as 'UserId',
					UserType.userType as 'UserType'
					FROM users Inner Join UserType on users.userTypeId = userType.userTypeID
					WHERE users.userId = @_userId
				END
				
			
			ELSE 
			BEGIN
				SELECT '002' AS 'ErrorCode', 'Email id or password is wrong' AS 'ErrorMessage'	
			END
		END
		ELSE IF ISNULL(@_deleted_date,'') != ''
			BEGIN
				SELECT '001' AS 'ErrorCode', 'Account Deleted' AS 'ErrorMessage'	
			END



		ELSE
		BEGIN
			SELECT '003' AS 'ErrorCode', 'Sorry, we didn''t find your email. Make sure you login with your registered email id' AS 'ErrorMessage'
		END

	END

END
GO