insert into users (first_name, last_name, username, password, email, creation_date)
values('Rodrigo','Parapar','dobarqueiro', '$2a$10$1fI7b7iULrwzuHCNeLPfD.sTieqfA4asBu7VZJNxBpty1maoU.Byq', 'dobarqueiro@gmail.com', NOW());
insert into users (first_name, last_name, username, password, email, creation_date)
values('Pepe','Jons','pepe12', '$2a$10$ZYIh6aLdLpwWzf22ExiZjuygpZVVprm9gLDntiPgRKSLic.zPp6jm','test@gmail.com', NOW());
insert into users (first_name, last_name, username, password, email, creation_date)
values('Juan','Parapar','juna34', '$2a$10$KosOZ0T9bw0ztF7jpXz6ZuILIV6dFB/8EzGJe.PbJTK5LMA20t0Ku','test3@gmail.com',NOW());
insert into users (first_name, last_name, username, password, email, creation_date)
values('Marta','Lunes','mar456', '$2a$10$bl6zMQbKkb6a14vZDRQ8n.NVKUCz0XF47AkDW04ZLI/AblO8uttPe','est4@gmail.com', NOW());

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id, role_id) VALUES (1, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 2);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 1);

 
