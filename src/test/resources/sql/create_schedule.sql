DELETE FROM schedules;
DELETE FROM courses;
DELETE FROM groups;
DELETE FROM teachers;

INSERT INTO teachers (id, first_name, last_name, created_at, updated_at, deleted, deleted_at)
VALUES ('11111111-1111-1111-1111-111111111111', 'Пётр', 'Петров', now(), now(), false, null);

INSERT INTO courses (id, name, description, teacher_id, created_at, updated_at, deleted, deleted_at)
VALUES ('22222222-2222-2222-2222-222222222222', 'Java', 'Java course',
        '11111111-1111-1111-1111-111111111111', now(), now(), false, null);

INSERT INTO groups (id, name, created_at, updated_at, deleted, deleted_at)
VALUES ('33333333-3333-3333-3333-333333333333', 'Падаваны', now(), now(), false, null);