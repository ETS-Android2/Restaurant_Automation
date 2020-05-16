use RestaurantAutomation
GO

--exec loginUser 'admin111@gmail.com','123456',5
ALTER PROCEDURE loginUser
(
	 @email		varchar(150)
	,@password	varchar(16)
	--,@userType	int
)
AS
BEGIN
	
	SET NOCOUNT ON;

	BEGIN -- Variables

		DECLARE @_email		varchar(150) = @email	
		DECLARE @_password	varchar(64)	 = @password	
		--DECLARE @_userType	int			 = @userType

		SET @_password = CONVERT(VARCHAR(64),HASHBYTES('SHA2_256', @_password), 2)

		DECLARE @_userId int = NULL
		DECLARE @_deleted_date datetime = NULL
		DECLARE @_currentTime	datetime = GETDATE()
	END

	BEGIN -- login user
		
		SELECT @_userId = userid, @_deleted_date = deletedDate FROM users WHERE email = @_email AND password = @_password AND userTypeId = @_userType
		
		IF ISNULL(@_deleted_date, '') = '' 
		BEGIN
			IF EXISTS (select 1 from users where userId=@_userId) 
		 
			BEGIN
			
				DECLARE @_token UNIQUEIDENTIFIER = NEWID();
				IF NOT EXISTS (SELECT 1 FROM tokens WHERE userid = @_userId)
				BEGIN
					INSERT INTO tokens ( token, createdDate,userId,EXPIREdATE)
					VALUES ( @_token, @_currentTime,@_userId,DATEADD(DAY,30,@_currentTime))

					SELECT 
					firstname as 'Name'
					,email as 'Email'
					,userid as 'UserId'
					,@_token as 'Token'
					,userTypeId as 'UserType'
					FROM users
					WHERE userid = @_userId
				END
				ELSE 
				BEGIN
					UPDATE tokens 
					SET token = @_token
					, createdDate = @_currentTime
					where userid = @_userId

					SELECT 
					firstname as 'Name'
					,email as 'Email'
					,userid as 'UserId'
					,@_token as 'Token'
					,userTypeId as 'UserType'
					FROM users
					WHERE userid = @_userId
				END
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