insert into users (first_name, last_name, username, password, email, creation_date)
values('Rodrigo','Parapar','dobarqueiro', '$2a$10$1fI7b7iULrwzuHCNeLPfD.sTieqfA4asBu7VZJNxBpty1maoU.Byq', 'dobarqueiro@gmail.com', NOW());
insert into users (first_name, last_name, username, password, email, creation_date)
values('Pepe','Jons','pepe12', '$2a$10$ZYIh6aLdLpwWzf22ExiZjuygpZVVprm9gLDntiPgRKSLic.zPp6jm','test@gmail.com', NOW());
insert into users (first_name, last_name, username, password, email, creation_date)
values('Juan','Parapar','juna34', '$2a$10$KosOZ0T9bw0ztF7jpXz6ZuILIV6dFB/8EzGJe.PbJTK5LMA20t0Ku','test3@gmail.com',NOW());
insert into users (first_name, last_name, username, password, email, creation_date)
values('Marta','Lunes','mar456', '$2a$10$bl6zMQbKkb6a14vZDRQ8n.NVKUCz0XF47AkDW04ZLI/AblO8uttPe','est4@gmail.com', NOW());


INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');


INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);

INSERT INTO portfolios (name,description, user_id) VALUES ('Fondos', 'Lista con los primeros fondos', 1);
INSERT INTO portfolios (name,description, user_id) VALUES ('Otros fondos ', 'Lista con los otros fondos', 1);

INSERT INTO symbols (name,isin, bloomberg,url) VALUES ('Vanguard Global Stock Index Fund','IE00B03HCZ61', 'VANGLVI:ID',  'https://www.bloomberg.com/quote/VANGLVI:ID');
INSERT INTO symbols(name,isin, bloomberg,url)  VALUES ( 'Vanguard European Stock Index Fund','IE0007987690', 'VANEIEI:ID',  'https://www.bloomberg.com/quote/VANEIEI:ID');
INSERT INTO symbols (name,isin, bloomberg,url)  VALUES ( 'Vanguard Emerging Markets Stock Index Fund','IE0031786142', 'VANEMSI:ID',  'https://www.bloomberg.com/quote/VANEMSI:ID');
INSERT INTO symbols (name,isin, bloomberg,url)  VALUES ( 'iShares Japan Index Fund (IE) D Acc EUR', 'VANEMSI:ID','IE00BDRK7T12',  ' https://www.bloomberg.com/quote/BGIJDEA:ID');



INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, 42.920, 23.2943, 'MyInvestor', 1, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 1, 49.080, 20.3736,  'MyInvestor', 1, '2020-03-17');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 1, 46.450, 21.5280, 'MyInvestor', 1, '2020-03-18');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2, 65.18, 15.3402, 'MyInvestor', 1, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 3, 7.300, 136.9574, 'MyInvestor', 1, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 1, 47.38, 21.105, 'MyInvestor', 1, '2020-03-20');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 4, 67.87, 8.84, 'MyInvestor', 1, '2020-03-23');


INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2, 65.18, 15.3402, 'MyInvestor', 2, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 3, 7.300, 136.9574, 'MyInvestor', 2, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 1, 47.38, 21.105, 'MyInvestor', 2, '2020-03-20');
