INSERT INTO predicted_grade.qualification_type (id, type) 
VALUES (1, 'GCSE'), 
(2, 'BTEC L2 Award'), 
(3, 'BTEC L2 Certificate');

INSERT INTO predicted_grade.qualification (qualification_type_id, title) 
VALUES 
(1, 'Maths'),
(1, 'English Language'),
(1, 'English Literature'),
(1, 'Science (Single)'),
(1, 'Science (Double - 1st Grade)'),
(1, 'Science (Double - 2nd Grade)'),
(1, 'History'),
(1, 'Geography'),
(1, 'French'),
(1, 'Spanish'),
(1, 'German'),
(1, 'Drama'),
(2, 'Computing'),
(2, 'Health and Social Care'),
(2, 'Engineering'),
(2, 'ICT'),
(3, 'Computing'),
(3, 'Health and Social Care'),
(3, 'Engineering'),
(3, 'ICT');

INSERT INTO predicted_grade.exam_board(id, name, board_identifier)
VALUES
(1, 'AQA', ''),
(2, 'OCR', ''),
(3, 'Pearson/Edexcel', ''),
(4, 'WJEC', '');

INSERT INTO predicted_grade.student (id, surname, first_name, dob, link_id) 
VALUES
(1, 'Horgan', 'Michael', '1980-07-06', 'abcd'),
(2, 'Doe', 'John', '2003-08-19', 'efgh'),
(3, 'Smith', 'Jane', '2002-09-14', 'ijkl');

INSERT INTO predicted_grade.student_predicted_grade(student_id, qualification_id, grade, exam_board_id) 
VALUES 
(1, 1, '8', 1),
(1, 2, '4', 2),
(1, 5, '8', 1),
(1, 6, '7', 3),
(1, 9, 'D', 2),
(1, 7, 'C', 1),
(1, 12, 'C', 2);

