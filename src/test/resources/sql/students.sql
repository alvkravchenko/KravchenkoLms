DELETE FROM student_group;
DELETE FROM students;
DELETE FROM groups;

INSERT INTO groups (id, name, created_at, updated_at, deleted, deleted_at)
VALUES ('33333333-3333-3333-3333-333333333333', 'Падаваны', now(), now(), false, null);

INSERT INTO students (id, first_name, last_name, created_at, updated_at, deleted, deleted_at)
VALUES ('44444444-4444-4444-4444-444444444444', 'Иван', 'Иванов', now(), now(), false, null);

INSERT INTO student_group (student_id, group_id)
VALUES ('44444444-4444-4444-4444-444444444444', '33333333-3333-3333-3333-333333333333');