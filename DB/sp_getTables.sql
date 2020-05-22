USE [restomation]
GO

/****** Object:  StoredProcedure [dbo].[getMenuItems]    Script Date: 22-05-2020 11:30:54 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[getTables]
(
	@tableId INT	
)
AS
BEGIN

	SET NOCOUNT ON;

	BEGIN -- Declare Variables
		DECLARE @_tableId    INT = @tableId
	END
	
	BEGIN
		
		SELECT
		*
		FROM
		(
		SELECT 
			 Tables.tableId, Tables.capacity, Tables.isTableAvailable as availability			
		FROM 
		Tables 

		WHERE 
		-- (@_userId IS NULL OR (u1.userId=@_userId)) AND
		 (@_tableId = 0 OR (Tables.tableID=@_tableId))
		) AS t
		
		END
END
GO


