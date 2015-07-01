DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- password
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');
-- admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES ('ROLE_USER', 100000);
INSERT INTO user_roles (role, user_id) VALUES ('ROLE_ADMIN', 100001);

DELETE FROM meals;

INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100000, '2015-01-01T09:39:52.178', 'Завтрак', 500);
INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100000, '2015-07-01T18:19:22.088', 'Ужин', 700);

INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100001, '2014-07-01T13:39:42.328', 'Обед', 520);