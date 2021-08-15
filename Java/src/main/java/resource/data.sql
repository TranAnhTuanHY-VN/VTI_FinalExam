-- create database
DROP DATABASE IF EXISTS TestingSystem;
CREATE DATABASE TestingSystem;
USE TestingSystem;

-- create table: User
DROP TABLE IF EXISTS 	`User`;
CREATE TABLE IF NOT EXISTS `User` ( 	
	id 				SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`email` 		CHAR(50) NOT NULL UNIQUE CHECK (LENGTH(`email`) >= 6 AND LENGTH(`email`) <= 50),
	`password` 		VARCHAR(800) NOT NULL,
	`fullName` 		NVARCHAR(50) NOT NULL
);

-- create table: Manager
DROP TABLE IF EXISTS 	`Manager`;
CREATE TABLE IF NOT EXISTS `Manager` ( 	
	id 				SMALLINT UNSIGNED NOT NULL,
	`expInYear` 	SMALLINT UNSIGNED NOT NULL,
    FOREIGN KEY (id) REFERENCES `User`(id)
);

-- create table: Employee
DROP TABLE IF EXISTS 	`Employee`;
CREATE TABLE IF NOT EXISTS `Employee` ( 	
	id 				SMALLINT UNSIGNED NOT NULL,
    proSkill 		ENUM('DEV', 'TEST', 'JAVA', 'SQL') NOT NULL,
    FOREIGN KEY (id) REFERENCES `User`(id)
);

-- create table: Project
DROP TABLE IF EXISTS 	`Project`;
CREATE TABLE IF NOT EXISTS `Project` ( 	
	id 				SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`teamSize`	 	SMALLINT UNSIGNED # Trigger TODO...
);

-- create table: Project & User
DROP TABLE IF EXISTS 	`ProjectAndUser`;
CREATE TABLE IF NOT EXISTS `ProjectAndUser` ( 	
	projectId 		SMALLINT UNSIGNED NOT NULL,
	userId	 		SMALLINT UNSIGNED NOT NULL,
    `Role`			ENUM ('MANAGER', 'EMPLOYEE'),
    FOREIGN KEY (projectId) REFERENCES `Project`(id),
    FOREIGN KEY (userId) REFERENCES `User`(id),
    PRIMARY KEY (projectId, userId)
);

-- Note: Manager của Project không liên quan gì tới Project Manager

/*============================== INSERT DATABASE =======================================*/
/*======================================================================================*/
-- Add data User
INSERT INTO `User` 	(`email`,						`password`,		`fullName`			)
VALUE				('hanhhanoi1999@gmail.com',		'123456A',		'Hà Văn Hanh'		), 
					('hung122112@gmail.com',		'123456A',		'Nguyễn Thanh Hưng'	), 
					('cananh.tuan12@vti.com',		'123456A',		'Cấn Tuấn Anh'		), 
					('toananh123@vti.com',			'123456A',		'Nguyễn Anh Toàn'	), 
					('duynguyen123@vti.com',		'123456A',		'Nguyễn Duy'		), 
					('manhhung123@vti.com',			'123456A',		'Nguyễn Mạnh Hùng'	),
					('maianhng@gmail.com', 			'123456A',		'Nguyễn Mai Anh'	),
					('tuan1234@gmail.com', 			'123456A',		'Nguyễn Văn Tuấn'	),
					('thuyhanoi@gmail.com', 		'123456A',		'Nguyễn Thị Thủy'	),
					('quanganh@gmail.com', 			'123456A',		'Nguyễn Quang Anh'	),
					('hunghoang@gmail.com', 		'123456A',  	'Vũ Hoàng Hưng'		),
					('quocanh12@gmail.com', 		'123456A',		'Nguyễn Quốc Anh'	),
					('vananhb1@gmail.com', 			'123456A',		'Nguyễn Vân Anh'	),
					('trinh123@gmail.com', 			'123456A',		'Nguyễn Thị Trinh'	),
					('tuanhung@gmail.com', 			'123456A',		'Vũ Tuấn Hưng'		),
					('xuanmai12@gmail.com', 		'123456A',		'Nguyễn Xuân Mai'	),
					('hungnguyen@gmail.com', 		'123456A',		'Phạm Quang Hưng'	);

-- Add data Manager
INSERT INTO Manager (id,	`expInYear`	)
VALUE				(1,			5		), 
					(3,			1		), 
					(5,			2		), 
					(7,			3		), 
					(9,			5		), 
					(11,		7		), 
					(13,		8		), 
					(15,		9		), 
					(17,		10		);

-- Add data Employee
INSERT INTO Employee 	(id,	`proSkill`	)
VALUE					(2,			'DEV'	), 
						(4,			'DEV'	), 
						(6,			'TEST'	), 
						(8,			'TEST'	), 
						(10,		'JAVA'	), 
						(12,		'DEV'	), 
						(14,		'JAVA'	), 
						(16,		'SQL'	);
                        
-- Add data Project
INSERT INTO Project 	(`teamSize`	)
VALUE					(	2		), 
						(	4		), 
						(	6		), 
						(	8		), 
						(	10		), 
						(	12		), 
						(	14		), 
						(	16		), 
						(	18		);
                        
-- Add data `ProjectAndUser`
INSERT INTO `ProjectAndUser` 	(projectId	,	`userId`,	`Role`		)
VALUE							(1			,		1	,	'MANAGER'	), 
								(1			,		2	,	'EMPLOYEE'	), 
								(1			,		3	,	'EMPLOYEE'	), 
								(2			,		4	,	'EMPLOYEE'	), 
								(2			,		5	,	'MANAGER'	), 
								(2			,		6	,	'EMPLOYEE'	), 
								(3			,		7	,	'EMPLOYEE'	), 
								(3			,		8	,	'MANAGER'	);
                                
-- Question 2: Viết function để user nhập vào id project, sau đó in ra tất cả các employee &
-- Manager trong project đó (in ra dạng table)
SET @project_id = 1;

SELECT 	pau.projectId, u.`fullName`, pau.`Role`	
FROM	`ProjectAndUser` pau
JOIN	`User` u	ON u.id = pau.userId
WHERE	pau.projectId = @project_id;

-- Question 3: Viết function để user có thể lấy ra tất cả Manager của các project (in ra dạng table)
SELECT 	pau.projectId, u.`fullName`, pau.`Role`	
FROM	`ProjectAndUser` pau
JOIN	`User` u	ON u.id = pau.userId
WHERE	`Role` = 'MANAGER';

-- Question 4: Viết chức năng login, User sẽ nhập Email và Password trên giao diện login
SET @email = 'hanhhanoi1999@gmail.com';
SET @password = '123456A';

SELECT 	u.`fullName`	
FROM	`User` u
WHERE	u.`email` = @email AND `password` = @password;