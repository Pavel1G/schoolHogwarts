select * from student where age > 10 and age < 13;

select name from student;

select * from student where name like '%%';

select * from student where age < student.id;

select * from student order by age DESC;