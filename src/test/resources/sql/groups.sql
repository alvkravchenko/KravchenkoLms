DELETE FROM student_group;
DELETE FROM students;
DELETE FROM groups;

INSERT INTO groups (id, name, created_at, updated_at, deleted, deleted_at)
VALUES ('33333333-3333-3333-3333-333333333333', 'Падаваны', now(), now(), false, null);