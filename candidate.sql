use candidate;

CREATE TABLE Candidate (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    lastName VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    birthDate VARCHAR(4) NOT NULL,
    address VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    candidateType ENUM('0', '1', '2') NOT NULL
);

CREATE TABLE Skill (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL UNIQUE
);

CREATE TABLE CandidateSkill (
    id INT AUTO_INCREMENT PRIMARY KEY,
    candidateId INT NOT NULL,
    skillId INT NOT NULL,
    FOREIGN KEY (candidateId) REFERENCES Candidate(id) ON DELETE CASCADE,
    FOREIGN KEY (skillId) REFERENCES Skill(id) ON DELETE CASCADE
);

CREATE TABLE University (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
);

INSERT INTO University (name) VALUES 
('Đại học Quốc gia Hà Nội'),
('Đại học Bách khoa Hà Nội'),
('Học viện Công nghệ Bưu chính Viễn thông'),
('Đại học Kinh tế Quốc dân'),
('Đại học Ngoại thương');


INSERT INTO Candidate (firstName, lastName, birthDate, address, phone, email, candidateType)
VALUES 
-- Experience
('Nguyễn Văn', 'An', 1985, 'Hà Nội', '0123456789', 'annv@gmail.com', '0'),
('Trần Đức', 'Bình', 1987, 'TP. Hồ Chí Minh', '0123456790', 'binhtd@gmail.com', '0'),
('Lê Quốc', 'Chí', 1983, 'Đà Nẵng', '0123456781', 'chilq@gmail.com', '0'),
('Phạm Minh', 'Đức', 1984, 'Huế', '0123456782', 'ducpm@gmail.com', '0'),
('Đỗ Văn', 'Dũng', 1988, 'Cần Thơ', '0123456783', 'dungdv@gmail.com', '0'),
-- Fresher
('Nguyễn Thanh', 'Diệu', 1999, 'Hải Phòng', '0123456784', 'dieunt@gmail.com', '1'),
('Trần Thị', 'Hồng', 2000, 'Nha Trang', '0123456785', 'hongtt@gmail.com', '1'),
('Lê Ngọc', 'Hạnh', 1998, 'Quảng Ninh', '0123456786', 'hanhlq@gmail.com', '1'),
('Phạm Hoàng', 'Khang', 2001, 'Bắc Ninh', '0123456787', 'khangph@gmail.com', '1'),
('Đỗ Khánh', 'Lâm', 2000, 'Vinh', '0123456788', 'lamdk@gmail.com', '1'),
-- Intern
('Nguyễn Thị', 'Mai', 2003, 'Thanh Hóa', '0123456789', 'maint@gmail.com', '2'),
('Trần Văn', 'Nam', 2004, 'Hà Tĩnh', '0123456790', 'namtv@gmail.com', '2'),
('Lê Minh', 'Phúc', 2005, 'Quảng Nam', '0123456781', 'phuclm@gmail.com', '2'),
('Phạm Bảo', 'Quân', 2002, 'Lào Cai', '0123456782', 'quanpb@gmail.com', '2'),
('Đỗ Ngọc', 'Quyên', 2003, 'Thái Bình', '0123456783', 'quyendn@gmail.com', '2'),
-- Experience
('Nguyễn Xuân', 'Sơn', 1986, 'Bình Định', '0123456784', 'sonnx@gmail.com', '0'),
('Trần Văn', 'Tâm', 1989, 'Quảng Ngãi', '0123456785', 'tamvt@gmail.com', '0'),
('Lê Thị', 'Uyên', 1984, 'Nghệ An', '0123456786', 'uyenlt@gmail.com', '0'),
('Phạm Khánh', 'Vĩnh', 1985, 'Tây Ninh', '0123456787', 'vinhpk@gmail.com', '0'),
('Đỗ Hữu', 'Xuân', 1987, 'Điện Biên', '0123456788', 'xuandh@gmail.com', '0'),
-- Fresher
('Nguyễn Mai', 'Yến', 1998, 'Phú Thọ', '0123456789', 'yenmn@gmail.com', '1'),
('Trần Thị', 'Anh', 1999, 'Sơn La', '0123456790', 'anhtt@gmail.com', '1'),
('Lê Ngọc', 'Bảo', 2001, 'Lạng Sơn', '0123456781', 'baoln@gmail.com', '1'),
('Phạm Văn', 'Chí', 2000, 'Bắc Giang', '0123456782', 'chipv@gmail.com', '1'),
('Đỗ Huy', 'Dũng', 1999, 'Hà Giang', '0123456783', 'dungdh@gmail.com', '1'),
-- Intern
('Nguyễn Thành', 'Giang', 2005, 'Nam Định', '0123456784', 'giangnt@gmail.com', '2'),
('Trần Hải', 'Hà', 2004, 'Ninh Bình', '0123456785', 'haith@gmail.com', '2'),
('Lê Đức', 'Hùng', 2003, 'Tiền Giang', '0123456786', 'hungld@gmail.com', '2'),
('Phạm Thế', 'Khoa', 2002, 'Bến Tre', '0123456787', 'khoapt@gmail.com', '2'),
('Đỗ Hoài', 'Linh', 2005, 'Vũng Tàu', '0123456788', 'linhdh@gmail.com', '2');

CREATE TABLE FresherCandidate (
    id INT AUTO_INCREMENT PRIMARY KEY,
    candidateId INT NOT NULL,
    universityId INT NOT NULL,
    graduationTime varchar(4) NOT NULL,
    graduationRank VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    FOREIGN KEY (candidateId) REFERENCES Candidate(id),
    FOREIGN KEY (universityId) REFERENCES University(id)
);
INSERT INTO FresherCandidate (candidateId, universityId, graduationTime, graduationRank) VALUES
(6, 1, 2021, 'Giỏi'),
(7, 2, 2020, 'Khá'),
(8, 3, 2021, 'Khá'),
(9, 4, 2022, 'Giỏi'),
(10, 5, 2020, 'Khá'),
(21, 1, 2019, 'Giỏi'),
(22, 2, 2020, 'Khá'),
(23, 3, 2021, 'Khá'),
(24, 4, 2020, 'Khá'),
(25, 5, 2019, 'Khá');


DESCRIBE Candidate;
ALTER TABLE Candidate MODIFY candidateType TINYINT;
ALTER TABLE Candidate MODIFY candidateType ENUM('0', '1', '2');

INSERT INTO Candidate (firstName, lastName, birthDate, address, phone, email, candidateType)
-- Chỉ dùng các giá trị sau cho cột candidateType
VALUES ('Nguyễn Văn', 'An', 1985, 'Hà Nội', '0123456789', 'annv@gmail.com', 0);

INSERT INTO Skill (name)
VALUES 
('Java'),
('Python'),
('SQL'),
('JavaScript'),
('C++');

INSERT INTO Candidateskill (candidateId, skillId)
VALUES
-- Experience Candidates
(1, 1),  -- Candidate 1 (Nguyễn Văn An) has skill Java
(1, 2),  -- Candidate 1 (Nguyễn Văn An) has skill Python
(2, 3),  -- Candidate 2 (Trần Đức Bình) has skill SQL
(2, 4),  -- Candidate 2 (Trần Đức Bình) has skill JavaScript
(3, 5),  -- Candidate 3 (Lê Quốc Chí) has skill C++
(3, 1),  -- Candidate 3 (Lê Quốc Chí) has skill Java
(4, 2),  -- Candidate 4 (Phạm Minh Đức) has skill Python
(4, 3),  -- Candidate 4 (Phạm Minh Đức) has skill SQL
(5, 4),  -- Candidate 5 (Đỗ Văn Dũng) has skill JavaScript
(5, 5),  -- Candidate 5 (Đỗ Văn Dũng) has skill C++
(6, 1),  -- Candidate 6 (Nguyễn Xuân Sơn) has skill Java
(6, 2),  -- Candidate 6 (Nguyễn Xuân Sơn) has skill Python
(7, 3),  -- Candidate 7 (Trần Văn Tâm) has skill SQL
(7, 4),  -- Candidate 7 (Trần Văn Tâm) has skill JavaScript
(8, 5),  -- Candidate 8 (Lê Thị Uyên) has skill C++
(8, 1),  -- Candidate 8 (Lê Thị Uyên) has skill Java
(9, 2),  -- Candidate 9 (Phạm Khánh Vĩnh) has skill Python
(9, 3),  -- Candidate 9 (Phạm Khánh Vĩnh) has skill SQL
(10, 4), -- Candidate 10 (Đỗ Hữu Xuân) has skill JavaScript
(10, 5); -- Candidate 10 (Đỗ Hữu Xuân) has skill C++

use candidate;
select * from candidate
where birthDate LIKE "%1989%";

SELECT * FROM candidate 
WHERE firstName LIKE "%%" 
AND lastName LIKE "%%" 
AND birthDate LIKE "%%" 
AND address LIKE "%%"  
AND candidateType Like "%%"
LIMIT 10 OFFSET 30;

SELECT * FROM candidate WHERE firstName LIKE ? 
AND lastName LIKE "" 
AND birthDate LIKE ? 
AND address LIKE ? 
AND candidateType = ? 
LIMIT 10 OFFSET ?;


CREATE TABLE Majors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    majorName VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
);

CREATE TABLE InternCandidate (
    id INT PRIMARY KEY AUTO_INCREMENT,
    candidateId INT NOT NULL,
    universityId INT NOT NULL,
    majorId INT NOT NULL,
    semester int NOT NULL,
    FOREIGN KEY (candidateId) REFERENCES Candidate(id),
    FOREIGN KEY (universityId) REFERENCES University(id),
    FOREIGN KEY (majorId) REFERENCES Majors(id)
);

INSERT INTO Majors (majorName)
VALUES 
('Hệ thống thông tin'),
('Công nghệ phần mềm'),
('An toàn thông tin'),
('Khoa học máy tính'),
('Kỹ thuật máy tính');

INSERT INTO InternCandidate (candidateId, universityId, majorId, semester)
VALUES 
(1, 1, 1, '1'),
(2, 2, 2, '2'),
(3, 3, 3, '1'),
(4, 4, 4, '2'),
(5, 5, 5, '1'),
(6, 1, 1, '2'),
(7, 2, 2, '1'),
(8, 3, 3, '2'),
(9, 4, 4, '1'),
(10, 5, 5, '2');

UPDATE candidate SET FirstName = "Trần Minh", LastName = "An", BirthDate = 2003,Address = "Hà Nội", Phone = "0912345678", Email = "anhtm@gmail.com",  CandidateType = "1" WHERE id = 33
select * from fresherCandidate;