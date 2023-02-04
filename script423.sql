SELECT student.name, student.age, f.name
FROM student
         LEFT JOIN faculty f ON f.id = student.faculty_id;

select student.name
from student
         INNER JOIN avatar a ON student.id = a.student_id