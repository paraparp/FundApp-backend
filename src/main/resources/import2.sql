drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 100 increment by 1;


drop table if exists oauth_client_details;
create table oauth_client_details (client_id VARCHAR(255) PRIMARY KEY, resource_ids VARCHAR(255), client_secret VARCHAR(255), scope VARCHAR(255), authorized_grant_types VARCHAR(255), web_server_redirect_uri VARCHAR(255), authorities VARCHAR(255), access_token_validity INTEGER, refresh_token_validity INTEGER, additional_information VARCHAR(4096), autoapprove VARCHAR(255));

drop table if exists oauth_client_token;
create table oauth_client_token (token_id VARCHAR(255), token LONGVARBINARY, authentication_id VARCHAR(255) PRIMARY KEY, user_name VARCHAR(255), client_id VARCHAR(255));

drop table if exists oauth_access_token;
create table oauth_access_token (token_id VARCHAR(255), token LONGVARBINARY, authentication_id VARCHAR(255) PRIMARY KEY, user_name VARCHAR(255), client_id VARCHAR(255), authentication LONGVARBINARY, refresh_token VARCHAR(255));
 
drop table if exists oauth_refresh_token;
create table oauth_refresh_token (token_id VARCHAR(255), token LONGVARBINARY, authentication LONGVARBINARY);

drop table if exists oauth_code;
create table oauth_code (code VARCHAR(255), authentication LONGVARBINARY);
 
drop table if exists oauth_approvals;
create table oauth_approvals (userId VARCHAR(255), clientId VARCHAR(255), scope VARCHAR(255), status VARCHAR(10), expiresAt TIMESTAMP, lastModifiedAt TIMESTAMP);

drop table if exists ClientDetails;
create table ClientDetails (appId VARCHAR(255) PRIMARY KEY, resourceIds VARCHAR(255), appSecret VARCHAR(255), scope VARCHAR(255), grantTypes VARCHAR(255), redirectUrl VARCHAR(255), authorities VARCHAR(255), access_token_validity INTEGER, refresh_token_validity INTEGER, additionalInformation VARCHAR(4096), autoApproveScopes VARCHAR(255));




insert into users (first_name, last_name, username, password, email, enabled) values ('Rodrigo','Parapar','dobarqueiro', '$2a$10$1fI7b7iULrwzuHCNeLPfD.sTieqfA4asBu7VZJNxBpty1maoU.Byq', 'dobarqueiro@gmail.com', true);
insert into users (first_name, last_name, username, password, email, enabled) values ('Pepe','Jons','pepe12', '$2a$10$ZYIh6aLdLpwWzf22ExiZjuygpZVVprm9gLDntiPgRKSLic.zPp6jm','test@gmail.com', true);
insert into users (first_name, last_name, username, password, email, enabled) values ('Juan','Parapar','juna34', '$2a$10$KosOZ0T9bw0ztF7jpXz6ZuILIV6dFB/8EzGJe.PbJTK5LMA20t0Ku','test3@gmail.com', true);
insert into users (first_name, last_name, username, password, email, enabled) values ('Marta','Lunes','mar456', '$2a$10$bl6zMQbKkb6a14vZDRQ8n.NVKUCz0XF47AkDW04ZLI/AblO8uttPe','est4@gmail.com', true);

insert into users_entity (username, password, email) values ('dobarqueiro', '$2a$10$1fI7b7iULrwzuHCNeLPfD.sTieqfA4asBu7VZJNxBpty1maoU.Byq', 'dobarqueiro@gmail.com');


INSERT INTO portfolios (name,description, user_id) VALUES ('Fondos', 'Lista con los primeros fondos', 1);
INSERT INTO portfolios (name,description, user_id) VALUES ('Otros fondos ', 'Lista con los otros fondos', 1);
INSERT INTO portfolios (name,description, user_id) VALUES ('Otros fondos 2', 'Lista con los otros fondos', 1);


INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (1,'Vanguard Global Stock Index Fund','IE00B03HCZ61', 'VANGLVI:ID',  'https://www.bloomberg.com/quote/VANGLVI:ID');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (2 ,'Vanguard European Stock Index Fund','IE0007987690', 'VANEIEI:ID',  'https://www.bloomberg.com/quote/VANEIEI:ID');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (3, 'Vanguard Emerging Markets Stock Index Fund','IE0031786142', 'VANEMSI:ID', 'https://www.bloomberg.com/quote/VANEMSI:ID');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (4, 'iShares Japan Index Fund (IE) D Acc EUR','IE00BDRK7T12','VANEMSI:ID' ,'https://www.bloomberg.com/quote/BGIJDEA:ID');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (5, 'Amundi Index MSCI World - AE (C)','LU0996182563', 'AMIEAEC:LX', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (6, 'Amundi Index MSCI EMU - AE (C)','LU0389811372', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (7, 'Amundi Funds Index Equity Emerging Markets - AE (C)','LU0996177134', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (8, 'Vanguard Global Bond Index Fund - Investor Hedged','IE00BGCZ0933', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (9, 'Amundi Index J.P. Morgan EMU Govies IG - AE (C)','LU1050470373', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (10, 'Vanguard Eurozone Inflation-Linked Bond Index Fund (Inv)','IE00B04GQQ17', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (11, '','IE00B18GC888', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (12, '','LU1328852659', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (13, '','IE00B83YJG36', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (14, '','LU0261959422', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (15, '','LU0690374615', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (16, '','LU0187079347', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (17, '','LU0171310443', '', ''); 
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (18, '','IE00B579F325', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (19, '','DE000A1DCTL3', '', ''); 
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (20, '','FR0011170182', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (21, '','IE00B4XCK338', '', '');
INSERT INTO symbols ( id, name,isin, morningstar, url) VALUES (22, '','DE000A0N62F2', '', '');



INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 1, 42.920, 23.2943, 'MyInvestor', 1, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 1, 49.080, 20.3736,  'MyInvestor', 1, '2020-03-17');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 1, 46.450, 21.5280, 'MyInvestor', 1, '2020-03-18');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 1, 47.38, 21.105, 'MyInvestor', 1, '2020-03-20');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 4, 67.87, 8.84, 'MyInvestor', 1, '2020-03-23');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2, 65.18, 15.3402, 'MyInvestor', 1, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 3, 7.300, 136.9574, 'MyInvestor', 1, '2020-03-13');

INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2, 65.18, 15.3402, 'MyInvestor', 2, '2020-07-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 3, 7.300, 136.9574, 'MyInvestor', 2, '2020-07-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 1, 47.38, 21.105, 'MyInvestor', 2, '2020-07-20');

INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2, 65.18, 15.3402, 'MyInvestor', 3, '2020-03-13');

--REAL
INSERT INTO portfolios (id, name,description, user_id) VALUES (4, 'Mis Fondos Indexados (REAL)', 'Lista con los fonods indexados desde 2020', 1);

--Vanguard Global Stock Index Fund
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1,  42.9200, 23.2943, 'MyInvestor', 4, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1,  49.0800, 20.3736, 'MyInvestor', 4, '2020-03-17');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1,  46.4500, 21.5280, 'MyInvestor', 4, '2020-03-18');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1,  47.3800, 21.1050, 'MyInvestor', 4, '2020-03-20');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, -22.0800, 21.7639, 'MyInvestor', 4, '2020-04-03');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, -20.8600, 24.0068, 'MyInvestor', 4, '2020-04-15'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, -41.6500, 24.7468, 'MyInvestor', 4, '2020-04-17'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, -20.2000, 23.7743, 'MyInvestor', 4, '2020-04-21'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, -20.3600, 24.9602, 'MyInvestor', 4, '2020-04-28'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, -20.0300, 25.0471, 'MyInvestor', 4, '2020-04-30'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1, -37.1200, 27.1091, 'MyInvestor', 4, '2020-07-21'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1,  14.5000, 27.5850, 'MyInvestor', 4, '2020-08-01'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date) VALUES ( 1,  14.6500, 27.2897, 'MyInvestor', 4, '2020-09-01'); 

--Vanguard European Stock Index Fund
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2, 65.1800,15.3402 , 'MyInvestor', 4, '2020-03-14');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2, 64.3500,15.5380 , 'MyInvestor', 4, '2020-03-20');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2,-27.7900,17.4847 , 'MyInvestor', 4, '2020-04-21'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2,-42.2800,19.4664 , 'MyInvestor', 4, '2020-06-02'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 2,-30.8200,19.8198 , 'MyInvestor', 4, '2020-06-04'); 

--Vanguard Emerging Markets Stock Index Fund
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 3, 7.3000 ,136.9574,  'MyInvestor', 4, '2020-03-31');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 3, 1.1900 ,167.3056,  'MyInvestor', 4, '2020-07-03');

--iShares Japan Index Fund (IE) D Acc EUR
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 4, 67.8700, 8.8400, 'MyInvestor', 4, '2020-03-31');

--Amundi Index MSCI World - AE (C)
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 5, 18.6850, 160.5400, 'OPENBANK', 4, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 5, 14.2500, 140.3500, 'OPENBANK', 4, '2020-03-18');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 5,  6.8740, 145.4600, 'OPENBANK', 4, '2020-03-19');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 5, -1.3330, 149.9600, 'OPENBANK', 4, '2020-04-01');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 5, -6.0200, 166.7200, 'OPENBANK', 4, '2020-04-17');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 5,  1.7680, 163.8200, 'OPENBANK', 4, '2020-04-21'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 5,  2.8150, 179.5100, 'OPENBANK', 4, '2020-05-25'); 


--Amundi Index MSCI EMU - AE (C)
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 6, 5.1630, 145.2390, 'OPENBANK', 4, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 6, 2.0390, 122.5700, 'OPENBANK', 4, '2020-03-31');

--Amundi Funds Index Equity Emerging Markets - AE (C)
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 7,  8.3700, 119.6500, 'OPENBANK', 4, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 7, -4.3750, 114.2600, 'OPENBANK', 4, '2020-03-31');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 7, -3.9950, 126.5200, 'OPENBANK', 4, '2020-04-22');


--Vanguard Global Bond Index Fund - Investor Hedged
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 8, 11.2400, 106.6661 , 'MyInvestor', 4, '2020-03-17');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 8,  9.3300, 107.0727 , 'MyInvestor', 4, '2020-03-27');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 8,  4.6600, 109.1294 , 'MyInvestor', 4, '2020-04-28'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 8,  4.5800, 109.3254 , 'MyInvestor', 4, '2020-04-30'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 8, 22.9100, 109.1060 , 'MyInvestor', 4, '2020-05-20'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 8, 22.8100, 109.5683 , 'MyInvestor', 4, '2020-05-22'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 8,-75.5300, 109.7200 , 'MyInvestor', 4, '2020-06-01'); 

--Amundi JP Morgan Bond
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 9,  2.4300,123.4280 , 'OPENBANK', 4, '2020-03-13');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 9, -2.4300,119.2000 , 'OPENBANK', 4, '2020-04-17'); 

--Vanguard Eurozone Inflation-Linked Bond Index Fund (Inv)
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 10,  3.5900, 139.3511, 'MyInvestor', 4, '2020-04-15'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 10,  7.3800, 139.7363, 'MyInvestor', 4, '2020-04-17'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 10,  3.4900, 137.6760, 'MyInvestor', 4, '2020-04-21'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 10,  3.5300, 137.6760, 'MyInvestor', 4, '2020-04-21'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 10, 17.8300, 140.1687, 'MyInvestor', 4, '2020-05-20');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 10, 17.6100, 141.9355, 'MyInvestor', 4, '2020-05-29');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 10, -20.2100, 148.5805, 'MyInvestor', 4, '2020-07-30');

--iShares Developed Real Estate Index (IE) Instl Acc €
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 13, 15.3800, 12.9980, 'MyInvestor', 4, '2020-05-16');


--Vanguard Global Bond  Eur Ins
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 11, 75.6400 ,109.5532 ,  'MyInvestor', 4, '2020-06-01'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 11,  7.5200 ,109.4883 ,  'MyInvestor', 4, '2020-06-02'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 11,  5.6000 ,109.1972 ,  'MyInvestor', 4, '2020-06-04'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 11,  9.0600 ,111.0373 ,  'MyInvestor', 4, '2020-07-21'); 

--- ROBECO GLOBAL CONSUMER TRENDS EQ D EUR -
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 16, 1.3584 , 294.46,  'MyInvestor', 4, '2020-07-01'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 16, 0.6207 , 322.19,  'MyInvestor', 4, '2020-08-01'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 16, 0.6174 , 323.90,  'MyInvestor', 4, '2020-09-01');

-- WisdomTree Physical Swiss Gold
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 19, 3 , 153.300,  'DEGIRO', 4, '2020-07-13'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 19, 2 , 153.950,  'DEGIRO', 4, '2020-08-25'); 

--WisdomTree Physical Silver | PHAG
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 22, 10 , 20.30,  'DEGIRO', 4, '2020-08-12'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 22, 10 , 19.40,  'DEGIRO', 4, '2020-08-21');
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 22, 11 , 17.78,  'DEGIRO', 4, '2020-08-24');


---  iShares Ultra High Quality Euro Government Bond Index Fund
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 21, 150.8700, 13.2560,  'MyInvestor', 4, '2020-07-30'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 21, 225.6300, 13.3080,  'MyInvestor', 4, '2020-07-31'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 21, 226.3200, 13.2550,  'MyInvestor', 4, '2020-08-06'); 
INSERT INTO lots (symbol_id, volume, price, broker,portfolio_id, date)  VALUES ( 21, 190.3200, 13.1350,  'MyInvestor', 4, '2020-09-01'); 
