use UniversityDB;
insert into Course values('programming',1);
insert into Course values('os',2);
insert into Course values('dsa',3);
INSERT INTO Person (FirstName, LastName, Address, Gender, Birthdate) VALUES
                                                                         ('John', 'Doe', '123 Main St, Springfield', 'Male', '1995-05-14'),
                                                                         ('Jane', 'Smith', '456 Elm St, Shelbyville', 'Female', '1997-03-22'),
                                                                         ('Alice', 'Brown', '789 Maple St, Ogdenville', 'Female', '1999-11-01'),
                                                                         ('Bob', 'Johnson', '321 Oak St, Capital City', 'Male', '1993-07-19'),
                                                                         ('Emily', 'Davis', '654 Pine St, North Haverbrook', 'Female', '1998-06-12'),
                                                                         ('Michael', 'Wilson', '987 Birch St, Springfield', 'Male', '1996-02-15'),
                                                                         ('Emma', 'Moore', '159 Cedar St, Shelbyville', 'Female', '1994-09-25'),
                                                                         ('Liam', 'Taylor', '753 Willow St, Ogdenville', 'Male', '1995-12-03'),
                                                                         ('Olivia', 'Anderson', '852 Poplar St, Capital City', 'Female', '1993-08-10'),
                                                                         ('Noah', 'Thomas', '963 Sycamore St, North Haverbrook', 'Male', '1999-01-20'),
                                                                         ('Sophia', 'Harris', '741 Spruce St, Springfield', 'Female', '1996-04-08'),
                                                                         ('Lucas', 'Martin', '369 Redwood St, Shelbyville', 'Male', '1997-05-06'),
                                                                         ('Isabella', 'Thompson', '258 Aspen St, Ogdenville', 'Female', '1995-10-30'),
                                                                         ('Mason', 'White', '147 Palm St, Capital City', 'Male', '1994-11-15'),
                                                                         ('Mia', 'Lopez', '123 Cypress St, North Haverbrook', 'Female', '1998-07-07'),
                                                                         ('Ethan', 'Clark', '987 Chestnut St, Springfield', 'Male', '1999-02-02'),
                                                                         ('Ava', 'Lewis', '654 Walnut St, Shelbyville', 'Female', '1993-12-11'),
                                                                         ('James', 'Hall', '321 Mahogany St, Ogdenville', 'Male', '1997-09-01'),
                                                                         ('Charlotte', 'Young', '852 Teak St, Capital City', 'Female', '1995-06-30'),
                                                                         ('Benjamin', 'Allen', '753 Bamboo St, North Haverbrook', 'Male', '1996-03-18'),
                                                                         ('Evelyn', 'Parker', '567 Elm St, Springfield', 'Female', '1994-03-28'),
                                                                         ('Henry', 'Mitchell', '789 Maple St, Shelbyville', 'Male', '1995-05-14'),
                                                                         ('Victoria', 'Collins', '123 Oak St, Ogdenville', 'Female', '1993-07-20'),
                                                                         ('Daniel', 'Baker', '456 Pine St, Capital City', 'Male', '1997-10-03'),
                                                                         ('Natalie', 'Hill', '321 Cedar St, North Haverbrook', 'Female', '1999-08-22'),
                                                                         ('Andrew', 'Scott', '654 Birch St, Springfield', 'Male', '1996-06-15'),
                                                                         ('Chloe', 'Adams', '987 Willow St, Shelbyville', 'Female', '1995-02-18'),
-- Professors (3 professors)
                                                                         ('John', 'Smith', '123 College Ave, Springfield', 'Male', '1980-11-01'),
                                                                         ('Martell', 'Johnson', '456 College Ave, Shelbyville', 'Female', '1982-03-15'),
                                                                         ('Nikcy', 'Brown', '789 College Ave, Ogdenville', 'Male', '1985-07-09');

INSERT INTO Student (StudentID, UniversityID) VALUES
                                                  (1, 101234), (2, 102345), (3, 103456), (4, 104567), (5, 105678),
                                                  (6, 106789), (7, 107890), (8, 108901), (9, 109012), (10, 110123),
                                                  (11, 111234), (12, 112345), (13, 113456), (14, 114567), (15, 115678),
                                                  (16, 116789), (17, 117890), (18, 118901), (19, 119012), (20, 120123),
                                                  (21, 121234), (22, 122345), (23, 123456), (24, 124567), (25, 125678),
                                                  (26, 126789), (27, 127890);

INSERT INTO Professor (ProfID, Salary, CourseID) VALUES
                                                     (28, 75000, 1), (29, 80000, 2), (30, 85000, 3);

INSERT INTO Phone (Phone, PersonID) VALUES
                                        (' +1 1234567890', 1), (' +1 9876543210', 2), (' +44 7555555555', 3), (' +33 4444444444', 4),
                                        (' +1 3333333333', 5), (' +49 2222222222', 6), (' +61 1111111111', 7), (' +81 9999999999', 8),
                                        (' +86 8888888888', 9), (' +91 7777777777', 10), (' +7 6666666666', 11), (' +34 1231231234', 12),
                                        (' +39 4564564567', 13), (' +55 7897897890', 14), (' +61 2342342345', 15), (' +91 8908908901', 16),
                                        (' +33 2345678901', 17), (' +44 3456789012', 18), (' +49 4567890123', 19), (' +81 5678901234', 20),
                                        (' +86 6789012345', 21), (' +1 7890123456', 22), (' +7 8901234567', 23), (' +91 9012345678', 24),
                                        (' +49 0123456789', 25), (' +1 1234567890', 26), (' +44 2345678901', 27),
                                        (' +1 5551234567', 28), (' +1 5552345678', 29), (' +1 5553456789', 30);

INSERT INTO Login (Email, Password, PersonID) VALUES
                                                  ('johndoe@example.com', '$2b$12$uV1h1HkU5jsz2MbKEB.45O', 1),
                                                  ('janesmith@example.com', '$2b$12$2E5S9kV.kIv55o1PDoUOY.', 2),
                                                  ('alicebrown@example.com', '$2b$12$XMvVhPpzrfOvvTztx.65uO', 3),
                                                  ('bobjohnson@example.com', '$2b$12$1Uv5hk32k1VOz22V4QvP4q', 4),
                                                  ('emilydavis@example.com', '$2b$12$6Mx4hOP2Vv1kOVp1kv5uV.', 5),
                                                  ('michaelwilson@example.com', '$2b$12$XmP5hxUzO5kjRPo1kU.45m', 6),
                                                  ('emmamoore@example.com', '$2b$12$OMk3VvzU5k4h2Vk5hk.55P', 7),
                                                  ('liamtaylor@example.com', '$2b$12$PV5h45Vkk2O35h2OvmQvU.', 8),
                                                  ('oliviaanderson@example.com', '$2b$12$25hMxPU2OVP45k5RPOVk.', 9),
                                                  ('noahthomas@example.com', '$2b$12$V5h45kPO5h2V4VhUUVvO5.', 10),
                                                  ('sophiaharris@example.com', '$2b$12$4Vk2PO45hMxU5RV5h3OMk.', 11),
                                                  ('lucasmartin@example.com', '$2b$12$XmP5hxUzO5kjRPo1kU.45m', 12),
                                                  ('isabellathompson@example.com', '$2b$12$OMk3VvzU5k4h2Vk5hk.55P', 13),
                                                  ('masonwhite@example.com', '$2b$12$PV5h45Vkk2O35h2OvmQvU.', 14),
                                                  ('mia.lopez@example.com', '$2b$12$25hMxPU2OVP45k5RPOVk.', 15),
                                                  ('ethanclark@example.com', '$2b$12$V5h45kPO5h2V4VhUUVvO5.', 16),
                                                  ('avalevis@example.com', '$2b$12$4Vk2PO45hMxU5RV5h3OMk.', 17),
                                                  ('jameshall@example.com', '$2b$12$V5h45kPO5h2V4VhUUVvO5.', 18),
                                                  ('charlotteyoung@example.com', '$2b$12$XmP5hxUzO5kjRPo1kU.45m', 19),
                                                  ('benjaminallen@example.com', '$2b$12$25hMxPU2OVP45k5RPOVk.', 20),
                                                  ('evelynparker@example.com', '$2b$12$uV1h1HkU5jsz2MbKEB.45O', 21),
                                                  ('henrymitchell@example.com', '$2b$12$2E5S9kV.kIv55o1PDoUOY.', 22),
                                                  ('victoriacollins@example.com', '$2b$12$XMvVhPpzrfOvvTztx.65uO', 23),
                                                  ('danielbaker@example.com', '$2b$12$1Uv5hk32k1VOz22V4QvP4q', 24),
                                                  ('nataliehill@example.com', '$2b$12$6Mx4hOP2Vv1kOVp1kv5uV.', 25),
                                                  ('andrewscott@example.com', '$2b$12$XmP5hxUzO5kjRPo1kU.45m', 26),
                                                  ('chloeadams@example.com', '$2b$12$OMk3VvzU5k4h2Vk5hk.55P', 27),
                                                  ('profsmith@example.com', '$2b$12$PV5h45Vkk2O35h2OvmQvU.', 28),
                                                  ('profjohnson@example.com', '$2b$12$25hMxPU2OVP45k5RPOVk.', 29),
                                                  ('profbrown@example.com', '$2b$12$V5h45kPO5h2V4VhUUVvO5.', 30);

INSERT INTO Enrollment (GPA_Value, GPA_Percentage, EnrollDate, CourseID, StudentID) VALUES
                                                                                        (3.5, 87.50, '2021-09-01', 1, 1), (3.8, 95.00, '2022-01-15', 2, 2), (3.6, 90.00, '2020-08-20', 3, 3),
                                                                                        (3.7, 92.50, '2019-05-10', 1, 4), (3.9, 97.50, '2023-03-12', 2, 5),
                                                                                        (3.4, 85.00, '2021-11-25', 3, 6), (3.2, 80.00, '2020-07-18', 1, 7),
                                                                                        (3.6, 90.00, '2019-10-09', 2, 8), (3.8, 95.00, '2023-02-01', 3, 9), (3.7, 92.50, '2022-09-13', 1, 10),
                                                                                        (3.5, 87.50, '2021-12-05', 2, 11), (3.6, 90.00, '2020-06-30', 3, 12), (3.7, 92.50, '2021-04-15', 1, 13),
                                                                                        (3.8, 95.00, '2022-01-10', 2, 14), (3.5, 87.50, '2020-03-19', 3, 15), (3.6, 90.00, '2019-05-25', 1, 16),
                                                                                        (3.7, 92.50, '2023-07-15', 2, 17), (3.8, 95.00, '2022-10-30', 3, 18), (3.5, 87.50, '2021-09-08', 1, 19),
                                                                                        (3.6, 90.00, '2020-08-18', 2, 20), (3.4, 85.00, '2023-01-10', 1, 21), (3.5, 87.50, '2020-12-05', 2, 22),
                                                                                        (3.6, 90.00, '2019-11-18', 3, 23), (3.8, 95.00, '2021-06-25', 1, 24), (3.9, 97.50, '2022-07-13', 2, 25),
                                                                                        (3.7, 92.50, '2023-02-18', 3, 26), (3.6, 90.00, '2020-03-29', 1, 27);


