-- Fill table roles --
INSERT INTO roles (role_id, name) VALUES (1, 'Admin');
INSERT INTO roles (role_id, name) VALUES (2, 'User');
-- Fill table users --
INSERT INTO users (login, password, fullname, role_id, blocked)
VALUES ("Igor", "3e0860e9cb01afd49c8885ce21f4f569", "Igor Zhazhky", 1, FALSE);
INSERT INTO users (login, password, fullname, role_id, blocked)
VALUES ("Ivan", "8d4646eb2d7067126eb08adb0672f7bb", "Ivan Gladush", 1, FALSE);
