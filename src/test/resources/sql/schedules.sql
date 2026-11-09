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

INSERT INTO schedules (id, group_id, teacher_id, course_id, start_date, end_date, created_at, updated_at)
VALUES ('66666666-6666-6666-6666-666666666666',
        '33333333-3333-3333-3333-333333333333',
        '11111111-1111-1111-1111-111111111111',
        '22222222-2222-2222-2222-222222222222',
        '2026-01-01', '2026-06-01', now(), now());