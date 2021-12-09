USE [CID]
GO

CREATE TABLE [dbo].[student_predicted_grade] (
    [student_predicted_grade_id] [int] IDENTITY(1, 1) NOT NULL,
    [student_id] [int] NOT NULL,
    [predicted_grade_id] [int] NOT NULL,
    [grade] [varchar](10) NULL,
    [exam_board_id] [int] NULL,
    CONSTRAINT [PK_student_predicted_grade]
        PRIMARY KEY CLUSTERED ([student_predicted_grade_id] ASC)
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON
        ) ON [PRIMARY],
    CONSTRAINT [UQ_student_predicted_grade]
        UNIQUE NONCLUSTERED (
            [student_id] ASC,
            [predicted_grade_id] ASC
        )
        WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON,
            ALLOW_PAGE_LOCKS = ON
        ) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[student_predicted_grade] WITH CHECK
ADD
    CONSTRAINT [FK_student_predicted_grade__predicted_grade]
    FOREIGN KEY ([predicted_grade_id])
    REFERENCES [dbo].[entry_qualification] ([entry_qualification_id])
GO

ALTER TABLE [dbo].[student_predicted_grade] CHECK CONSTRAINT [FK_student_predicted_grade__predicted_grade]
GO

ALTER TABLE [dbo].[student_predicted_grade] WITH CHECK
ADD
    CONSTRAINT [FK_student_predicted_grade__exam_board]
    FOREIGN KEY ([exam_board_id])
    REFERENCES [Exams].[exam_board] ([exam_board_id])
GO

ALTER TABLE [dbo].[student_predicted_grade] CHECK CONSTRAINT [FK_student_predicted_grade__exam_board]
GO

ALTER TABLE [dbo].[student_predicted_grade] WITH CHECK
ADD
    CONSTRAINT [FK_student_predicted_grade__student]
    FOREIGN KEY ([student_id])
    REFERENCES [dbo].[student] ([student_id])
GO

ALTER TABLE [dbo].[student_predicted_grade] CHECK CONSTRAINT [FK_student_predicted_grade__student]
GO

EXEC [sys].[sp_addextendedproperty]
    @name = N'MS_Description',
    @value = N'This is a UNIQUE constraint that ensure that each student can only have one of each entry qualification per date.',
    @level0type = N'SCHEMA',
    @level0name = N'dbo',
    @level1type = N'TABLE',
    @level1name = N'student_predicted_grade',
    @level2type = N'CONSTRAINT',
    @level2name = N'UQ_student_predicted_grade'
GO

CREATE SCHEMA [PredictedGrade]
GO

CREATE VIEW [PredictedGrade].[exam_board]
AS
SELECT
    [eb].[exam_board_id] AS [id],
    [eb].[board_identifier],
    [eb].[name]
FROM
    [Exams].[exam_board] AS [eb]
GO

CREATE VIEW [PredictedGrade].[qualification]
AS
SELECT
    [eq].[entry_qualification_id] AS [id],
    [eq].[title],
    [eq].[entry_qualification_type_id] AS [qualification_type_id]
FROM
    [dbo].[entry_qualification] AS [eq]
    INNER JOIN [dbo].[entry_qualification_type] AS [eqt]
    ON [eqt].[entry_qualification_type_id] = [eq].[entry_qualification_type_id]
WHERE
    [eq].[basic_list] = 1
    AND [eqt].[use_for_roe] = 1
GO

CREATE VIEW [PredictedGrade].[qualification_type]
AS
SELECT
    [eqt].[entry_qualification_type_id] AS [id],
    [eqt].[description] AS [type]
FROM
    [dbo].[entry_qualification_type] AS [eqt]
WHERE
    [eqt].[use_for_roe] = 1
GO

CREATE VIEW [PredictedGrade].[student_qualification]
AS
SELECT
    [seq].[student_predicted_grade_id] AS [id],
    [seq].[student_id],
    [seq].[predicted_grade_id] AS [qualification_id],
    [seq].[grade],
    [seq].[exam_board_id]
FROM
    [dbo].[student_predicted_grade] AS [seq]

GO

CREATE VIEW [PredictedGrade].[student]
AS
SELECT
    [s].[student_id] AS [id],
    CAST([p].[dob] AS date) AS [dob],
    COALESCE([p].[preferred_name], [p].[first_name]) AS [first_name],
    [p].[surname],
    CONCAT(
        SUBSTRING(CONVERT(varchar(32), HASHBYTES('MD5', [p].[surname]), 2), 1, 5),
        [s].[student_id],
        '-',
        [p].[person_id],
        '-',
        [s].[school_id],
        SUBSTRING(CONVERT(varchar(32), HASHBYTES('MD5', [p].[surname]), 2), 20, 5)
    ) AS [link_id]
FROM
    [dbo].[student] AS [s]
    INNER JOIN [dbo].[person] AS [p]
    ON [p].[person_id] = [s].[person_id]
GO

CREATE VIEW [PredictedGrade].[student_predicted_grade]
AS
SELECT
    [spg].[student_predicted_grade_id] AS [id],
    [spg].[student_id],
    [spg].[predicted_grade_id] AS [qualification_id],
    [spg].[grade],
    [spg].[exam_board_id]
FROM
    [dbo].[student_predicted_grade] AS [spg]

GO

