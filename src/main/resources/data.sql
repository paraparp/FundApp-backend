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
INSERT INTO portfolios (name,description, user_id) VALUES ('Otros fondos 2', 'Lista con los otros fondos', 1);


INSERT INTO symbols (id, name,isin, bloomberg,url) VALUES (1,'Vanguard Global Stock Index Fund','IE00B03HCZ61', 'VANGLVI:ID',  'https://www.bloomberg.com/quote/VANGLVI:ID');
INSERT INTO symbols (id, name,isin, bloomberg,url) VALUES ( 2,'Vanguard European Stock Index Fund','IE0007987690', 'VANEIEI:ID',  'https://www.bloomberg.com/quote/VANEIEI:ID');
INSERT INTO symbols (id, name,isin, bloomberg,url) VALUES ( 3, 'Vanguard Emerging Markets Stock Index Fund','IE0031786142', 'VANEMSI:ID', 'https://www.bloomberg.com/quote/VANEMSI:ID');
INSERT INTO symbols (id, name,isin, bloomberg,url) VALUES (4, 'iShares Japan Index Fund (IE) D Acc EUR','IE00BDRK7T12','VANEMSI:ID' ,'https://www.bloomberg.com/quote/BGIJDEA:ID');
INSERT INTO symbols (id, name,isin, bloomberg,url) VALUES (5,'Amundi Index MSCI World - AE (C)','LU0996182563', 'AMIEAEC:LX', '');
INSERT INTO symbols (id, name,isin, bloomberg,url) VALUES (6, 'Amundi Index MSCI EMU - AE (C)','LU0389811372', '', '');
INSERT INTO symbols (id, name,isin, bloomberg,url) VALUES ( 7,'Amundi Funds Index Equity Emerging Markets - AE (C)','LU0996177134', '', '');
INSERT INTO symbols (id, name,isin, bloomberg,url) VALUES ( 8,'Vanguard Global Bond Index Fund - Investor Hedged','IE00BGCZ0933', '', '');
INSERT INTO symbols (id, name,isin, bloomberg,url) VALUES ( 9,'Amundi Index J.P. Morgan EMU Govies IG - AE (C)','LU1050470373', '', '');
INSERT INTO symbols (id, name,isin, bloomberg,url) VALUES ( 10,'Vanguard Eurozone Inflation-Linked Bond Index Fund (Inv)','IE00B04GQQ17', '', '');
INSERT INTO symbols (id, name,isin, bloomberg,url) VALUES ( 11,'','IE00B18GC888', '', '');
INSERT INTO symbols (id, name,isin, bloomberg,url) VALUES ( 12,'','LU1328852659', '', '');
INSERT INTO symbols (id, name,isin, bloomberg,url) VALUES ( 13,'','IE00B83YJG36', '', '');


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

INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2, 65.18, 15.3402, 'MyInvestor', 3, '2020-03-13');

--REAL
INSERT INTO portfolios (id, name,description, user_id) VALUES (4, 'Mis Fondos Indexados (REAL)', 'Lista con los fonods indexados desde 2020', 1);

--Vanguard Global Stock Index Fund
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, 42.920, 23.2943, 'MyInvestor', 4, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, 49.080, 20.3736, 'MyInvestor', 4, '2020-03-17');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, 46.450, 21.5280, 'MyInvestor', 4, '2020-03-18');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, 47.380, 21.1050, 'MyInvestor', 4, '2020-03-20');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, -22.080, 21.7639, 'MyInvestor', 4, '2020-04-03');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, -20.860, 24.0068, 'MyInvestor', 4, '2020-04-15');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, -41.6500, 24.7468, 'MyInvestor', 4, '2020-04-17');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, -20.2000, 23.7743, 'MyInvestor', 4, '2020-04-21');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, -20.3600, 24.9602, 'MyInvestor', 4, '2020-04-28');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, -20.0300, 25.0471, 'MyInvestor', 4, '2020-04-30');

--Vanguard European Stock Index Fund
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2, 65.1800,15.3402 , 'MyInvestor', 4, '2020-03-14');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2, 64.3500,15.5380 , 'MyInvestor', 4, '2020-03-20');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2, -27.7900,17.4847 , 'MyInvestor', 4, '2020-04-21');

--Vanguard Emerging Markets Stock Index Fund
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 3,7.300 ,136.9574,  'MyInvestor', 4, '2020-03-31');

--iShares Japan Index Fund (IE) D Acc EUR
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 4, 67.87, 8.84, 'MyInvestor', 4, '2020-03-31');

--Amundi Index MSCI World - AE (C)
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 5, 18.685, 160.5400, 'OPENBANK', 4, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 5, 14.250, 140.3500, 'OPENBANK', 4, '2020-03-18');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 5, 6.874, 145.4600, 'OPENBANK', 4, '2020-03-19');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 5, -1.333, 149.9600, 'OPENBANK', 4, '2020-04-01');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 5, -6.0200, 166.72, 'OPENBANK', 4, '2020-04-17');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 5, 1.7680, 163.82, 'OPENBANK', 4, '2020-04-21');

--Amundi Index MSCI EMU - AE (C)
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 6, 5.163, 145.2390, 'OPENBANK', 4, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 6, 2.0390, 122.5700, 'OPENBANK', 4, '2020-03-31');

--Amundi Funds Index Equity Emerging Markets - AE (C)
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 7, 8.3700, 119.6500, 'OPENBANK', 4, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 7, -4.3750, 114.2600, 'OPENBANK', 4, '2020-03-31');

--Vanguard Global Bond Index Fund - Investor Hedged
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 8, 11.2400,106.6661 , 'MyInvestor', 4, '2020-03-17');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 8, 9.3300,107.0727 , 'MyInvestor', 4, '2020-03-27');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 8, 4.6600,109.1294 , 'MyInvestor', 4, '2020-04-28');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 8, 4.5800,109.3254 ,  'MyInvestor', 4, '2020-04-30'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 8, 22.91,109.1060 ,  'MyInvestor', 4, '2020-05-20'); 

--Amundi Global Bond Index Fund - Investor Hedged
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 9, 2.4300,123.4280 , 'OPENBANK', 4, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 9, -2.4300,119.2000 , 'OPENBANK', 4, '2020-04-17');

--Vanguard Eurozone Inflation-Linked Bond Index Fund (Inv)
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 10, 3.59, 139.3511, 'MyInvestor', 4, '2020-04-15');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 10, 7.38, 139.7363, 'MyInvestor', 4, '2020-04-17');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 10, 3.49, 137.6760, 'MyInvestor', 4, '2020-04-21');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 10, 3.53, 137.6760, 'MyInvestor', 4, '2020-04-21');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 10, 17.83, 140.1687, 'MyInvestor', 4, '2020-05-20');

--iShares Developed Real Estate Index (IE) Instl Acc €
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 13, 16, 13.6760, 'MyInvestor', 4, '2020-05-16');



