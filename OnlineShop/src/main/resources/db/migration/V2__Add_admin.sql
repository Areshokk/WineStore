
insert into usr (id, activation_code, active, email, password, username)
values (100000, null, true , 'yrameda0404@gmail.com', '$2a$08$eApn9x3qPiwp6cBVRYaDXed3J/usFEkcZbuc3FDa74bKOpUzHR.S.', 'admin');

insert into user_role (user_id, roles)
values (100000, 'ADMIN');