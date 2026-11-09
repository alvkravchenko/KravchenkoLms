DELETE FROM student_group;
DELETE FROM students;
DELETE FROM groups;

INSERT INTO students (id, first_name, last_name, created_at, updated_at, deleted, deleted_at)
VALUES ('44444444-4444-4444-4444-444444444444', 'Иван', 'Иванов', now(), now(), false, null);