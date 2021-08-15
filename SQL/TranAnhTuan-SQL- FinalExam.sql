DROP DATABASE IF EXISTS FinalTest;
CREATE DATABASE FinalTest;

USE FinalTest;

-- Question 1:
CREATE TABLE  Student(
	ID 			TINYINT PRIMARY KEY,
    Name 		VARCHAR(30) NOT NULL,
    Age			TINYINT,
    Gender		ENUM('0','1')
);

CREATE TABLE  Subject(
	ID 			TINYINT PRIMARY KEY,
    Name		VARCHAR(30) NOT NULL
);

CREATE TABLE  StudentSubject(
	StudentID	TINYINT,
    SubjectID 	TINYINT,
    Mark		FLOAT,
    Date		DATETIME,
    PRIMARY KEY(StudentID,SubjectID)
);

-- Insert Data
-- Student Table
INSERT INTO Student
VALUES		(1,'Tuan',20,'0'),
			(2,'Hang',20,'1');
INSERT INTO Student(ID,Name,Age)
VALUES		(3,'Bo',30);

-- Subject Table
INSERT INTO Subject
VALUES		(1,'SQL'),
			(2,'Java Core'),
            (3,'ReacJS'),
            (4,'Python');      
        
-- Student Subject Table
INSERT INTO StudentSubject
VALUES 		(1,2,8.9,'2020-11-20'),
			(1,1,7,'2020-12-20'),
            (1,3,8,'2021-1-20'),
            (2,1,9,'2021-2-20'),
            (2,2,6,'2020-12-20'),
            (2,3,7,'2021-5-20'),
            (3,1,8,'2021-5-10');

-- Question2:
-- a:
SELECT *
FROM 
	Subject
WHERE
	ID NOT IN 	(SELECT SubjectID FROM StudentSubject);

-- b:
SELECT a.ID, a.Name 
FROM 
	Subject a
    INNER JOIN StudentSubject b ON a.ID = b.SubjectID
GROUP BY 
	b.SubjectID
HAVING
	COUNT(Mark) >= 2;
	

-- Question 3:
CREATE OR REPLACE VIEW StudentInfo
AS
	SELECT 
		b.ID AS StudentID, c.ID AS SubjectID, b.Name AS StudentName, b.Age AS StudentAge, 
        CASE 
			WHEN b.Gender = '0' THEN 'Male'
            WHEN b.Gender = '1' THEN 'Female'
			ELSE 'Unknown'
        END , c.Name AS SubjectName, a.Mark, a.Date
	FROM 
		(StudentSubject a
        RIGHT JOIN Student b ON a.StudentID = b.ID)
        LEFT JOIN Subject c ON a.SubjectID = c.ID;

-- Show Result
SELECT * FROM StudentInfo;

-- Question 4:
-- a
DROP TRIGGER IF EXISTS SubjectUpdateID;
DELIMITER $$
CREATE TRIGGER SubjectUpdateID
BEFORE UPDATE ON `Subject`
FOR EACH ROW
BEGIN
	UPDATE 
		StudentSubject
	SET 
		SubjectID = NEW.ID
	WHERE 
		SubjectID = OLD.ID;
END$$
DELIMITER ; 


-- TEST:
SELECT * FROM Subject;
SELECT * FROM StudentSubject;
UPDATE 
	Subject
SET 
	ID = 5
WHERE 
	ID = 3;
    
-- b:
DROP TRIGGER IF EXISTS StudentDeleteID;
DELIMITER $$
CREATE TRIGGER StudentDeleteID
AFTER DELETE ON Student
FOR EACH ROW
BEGIN
	DELETE FROM
		StudentSubject
	WHERE 
		StudentID = OLD.ID;
END$$
DELIMITER ; 

-- TEST
SELECT * FROM StudentSubject;
DELETE FROM Student
WHERE ID =2 ;
    


-- Question 5:
DROP PROCEDURE IF EXISTS Del_Student;
DELIMITER $$
CREATE PROCEDURE Del_Student(IN in_StudentName VARCHAR(30), OUT out_Result VARCHAR(50))
	BEGIN
		SET out_Result = '';
		IF in_StudentName = '*'
		THEN
			DELETE FROM Student;
			SELECT CONCAT('Đã xóa tất cả sinh viên!') INTO out_Result;
		ELSE
			DELETE FROM Student
            WHERE `Name` = in_StudentName;
            SELECT CONCAT('Đã xóa học sinh có tên ',in_StudentName,' khỏi bảng Student') INTO out_Result;
        END IF;
    END$$
DELIMITER ;

-- TEST 
SELECT * FROM Student;
SET @Result = '';
CALL Del_Student('Tuan',@Result);
SELECT @Result;
