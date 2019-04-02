drop table T_BOOK if exists;

create table T_BOOK (ID bigint identity primary key, NUMBER varchar(9),
                        AUTHOR varchar(50), TITLE varchar(90), unique(NUMBER));
                        
drop table T_CART if exists;

create table T_CART (ID bigint identity primary key, BOOK_NUMBER varchar(9),
                        USER_NUMBER varchar(50), QUANTITY varchar(5), unique(BOOK_NUMBER, USER_NUMBER));                        

drop table T_USER if exists;

create table T_USER (ID bigint identity primary key, NUMBER varchar(9),
                        NAME varchar(50), PASSWORD varchar(10), unique(NUMBER));                        

