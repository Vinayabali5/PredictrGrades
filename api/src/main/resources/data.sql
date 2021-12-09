INSERT INTO predictedgrade.qualification_type (id, type) 
VALUES (1, 'GCSE'), 
(2, 'BTEC L2 Award'), 
(3, 'BTEC L2 Certificate');

INSERT INTO predictedgrade.qualification (id, qualification_type_id, title) 
VALUES 
(1, 1, 'Maths'),
(2, 1, 'English Language'),
(3, 1, 'English Literature'),
(4, 1, 'Science (Single)'),
(5, 1, 'Science (Double - 1st Grade)'),
(6, 1, 'Science (Double - 2nd Grade)'),
(7, 1, 'History'),
(8, 1, 'Geography'),
(9, 1, 'French'),
(10, 1, 'Spanish'),
(11, 1, 'German'),
(12, 1, 'Drama'),
(13, 2, 'Computing'),
(14, 2, 'Health and Social Care'),
(15, 2, 'Engineering'),
(16, 2, 'ICT'),
(17, 3, 'Computing'),
(18, 3, 'Health and Social Care'),
(19, 3, 'Engineering'),
(20, 3, 'ICT');

INSERT INTO predictedgrade.exam_board(id, name, board_identifier)
VALUES
(1, 'AQA', ''),
(2, 'OCR', ''),
(3, 'Pearson/Edexcel', ''),
(4, 'WJEC', '');

INSERT INTO predictedgrade.student (id, surname, first_name, dob, link_id) 
VALUES
(1, 'Horgan', 'Michael', '1980-07-06', 'abcd'),
(2, 'Doe', 'John', '2003-08-19', 'efgh'),
(3, 'Smith', 'Jane', '2002-09-14', 'ijkl');

INSERT INTO predictedgrade.student_predicted_grade(student_id, qualification_id, grade, exam_board_id) 
VALUES 
(1, 1, '8', 1),
(1, 2, '4', 2),
(1, 5, '8', 1),
(1, 6, '7', 3),
(1, 9, 'D', 2),
(1, 7, 'C', 1),
(1, 12, 'C', 2);

