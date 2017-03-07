INSERT INTO user (ID, USERNAME, PASSWORD, EMAIL, ACTIVE, ROLE) VALUES (1, 'vendigo', 'pass', 'vendigo@gmail.com', 1, 'ROLE_USER');
INSERT INTO user (ID, USERNAME, PASSWORD, EMAIL, ACTIVE, ROLE) VALUES (2, 'petro', 'pass1', 'petya@meta.ua', 1, 'ROLE_USER');
INSERT INTO user (ID, USERNAME, PASSWORD, EMAIL, ACTIVE, ROLE) VALUES (3, 'galya', 'passw0rd', 'galka@in.ua', 0, 'ROLE_USER');

INSERT INTO verification (creation_date, token, user_id, verified) VALUES ('2017-03-07', 'secr3Tt0ken', 3, 0);

