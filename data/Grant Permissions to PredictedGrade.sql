USE [master]
GO

-- Create SQL Server Login
CREATE LOGIN [Student-PredictedGrade] WITH PASSWORD=N'wKQZzL0uhhYzJ9YC5VtbaygQIXiUewHHCQWPXvBqpdY=', DEFAULT_DATABASE=[CID], DEFAULT_LANGUAGE=[us_english], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF
GO

USE [CID]
GO

-- Create Database User
CREATE USER [Student-PredictedGrade] FOR LOGIN [Student-PredictedGrade] WITH DEFAULT_SCHEMA=[PredictedGrade]
GO

-- Create Database Role and Add User
CREATE ROLE [PredictedGrade]
GO
ALTER ROLE [PredictedGrade] ADD MEMBER [Student-PredictedGrade]
GO

-- Grant Permissions to required objects
GRANT SELECT ON [dbo].[person]([surname])TO [PredictedGrade]
GO
GRANT SELECT ON [dbo].[person]([first_name])TO [PredictedGrade]
GO
GRANT SELECT ON [dbo].[student]([person_id])TO [PredictedGrade]
GO
GRANT SELECT ON [dbo].[person]([preferred_name])TO [PredictedGrade]
GO
GRANT SELECT ON [dbo].[person]([dob])TO [PredictedGrade]
GO
GRANT SELECT ON [dbo].[entry_qualification_type] TO [PredictedGrade]
GO
GRANT SELECT ON [dbo].[student]([student_id])TO [PredictedGrade]
GO
GRANT SELECT ON [dbo].[person]([person_id])TO [PredictedGrade]
GO
GRANT SELECT ON [Exams].[exam_board] TO [PredictedGrade]
GO
GRANT SELECT ON SCHEMA::[PredictedGrade] TO [PredictedGrade]
GO
GRANT SELECT ON [dbo].[entry_qualification] TO [PredictedGrade]
GO
GRANT DELETE ON [dbo].[student_predicted_grade] TO [PredictedGrade]
GO
GRANT INSERT ON [dbo].[student_predicted_grade] TO [PredictedGrade]
GO
GRANT SELECT ON [dbo].[student_predicted_grade] TO [PredictedGrade]
GO
GRANT UPDATE ON [dbo].[student_predicted_grade] TO [PredictedGrade]
GO
GRANT DELETE ON [PredictedGrade].[student_predicted_grade] TO [PredictedGrade]
GO
GRANT INSERT ON [PredictedGrade].[student_predicted_grade] TO [PredictedGrade]
GO
GRANT SELECT ON [PredictedGrade].[student_predicted_grade] TO [PredictedGrade]
GO
GRANT UPDATE ON [PredictedGrade].[student_predicted_grade] TO [PredictedGrade]
GO
