
SELECT
    p.PersonID,
    p.FirstName,
    p.LastName,
    p.Birthdate,
    p.Address,
    L.Email,
    L.Password,
    p.Gender,
    Pho.Phone,
    DATEDIFF(MONTH,Birthdate,GETDATE())/12 age,
    CASE
        WHEN s.StudentID IS NOT NULL THEN 'Student'
        WHEN pr.ProfID IS NOT NULL THEN 'Professor'
        END AS Role

FROM
    Person p
        LEFT JOIN
    Student s ON p.PersonID = s.StudentID
        LEFT JOIN
    Professor pr ON p.PersonID = pr.ProfID
        LEFT JOIN
    Login L on p.PersonID = L.PersonID
        LEFT JOIN
    Phone Pho on p.PersonID = pho.PersonID
where p.PersonID in (
    select PersonID from Login where StudentID = 2
)



select p1.UniversityID, AVG(p2.GPA_Value) as GPA from Student p1
                                                          left join Enrollment p2 on p1.StudentID = p2.StudentID
where p1.StudentID = 5
GROUP By p1.StudentID,p1.UniversityID;



select * from Student;
select * from Professor;
select * from Person;
select * from Login;
select * from Phone;
SELECT * from Course;
select * from Enrollment;

