INSERT INTO roles (name, description)
VALUES ('role_user', 'Role for the normal users (e.g. patients)');

INSERT INTO permission_role (role_id, permission_id)
VALUES ((SELECT id FROM roles WHERE name = 'role_user'),
        (SELECT id FROM permissions WHERE name = 'users:read'));


INSERT INTO roles (name, description)
VALUES ('role_doctor', 'Role for the doctors');

INSERT INTO permission_role (role_id, permission_id)
VALUES ((SELECT id FROM roles WHERE name = 'role_doctor'),
        (SELECT id FROM permissions WHERE name = 'users:read'));