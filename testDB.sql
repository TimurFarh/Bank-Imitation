
create database bank_imitation;

use bank_imitation;

create table clients(
    id int auto_increment,
    first_name varchar(55) not null,
    last_name varchar(55) not null,
    age smallint not null,
    address varchar(255) not null,
        constraint client_pk
                    primary key (id)
);

create table accounts (
    id int auto_increment,
    name varchar(50) not null,
    balance bigint not null,
    client_id int,
        constraint PRIMARY KEY (id),
        constraint client_fk
            foreign key (client_id) references clients(id)
                      on update cascade on delete cascade 
);

create table transactions (
    id int auto_increment,
    operation ENUM('Deposit','Withdraw','Close'),
    account_from varchar(50) not null,
    account_to varchar(50) not null,
    amount bigint not null,
    date_time TIMESTAMP default CURRENT_TIMESTAMP,
    account_id int,
    client_id int,
        constraint PRIMARY KEY (id),
        constraint client__fk
            foreign key (client_id) references clients(id)
                      on delete set null,
        constraint account__fk 
            foreign key (account_id) references accounts(id)
                          on delete set null
);

insert into clients (first_name, last_name, age, address) 
values ('Ivan','Ivanov', 23, '9, Petrogradskay, Saint-Petesburg'),
       ('Sharov', 'Hariton', 32, '87, Tekstilshikov, Uglich'),
       ('Clavdiy', 'Gavrilov', 41, '857, Proektiruemaya, Talpaki'),
       ('Fedosiya', 'Trifonova', 37, '57, Ozernaya, Karabulak'),
       ('Anastasya', 'Sidorova', 27, '77, Ladogskaya, Moscow'),
       ('Emil', 'Fedorov', 55, '52, Krasnogorskay, Arti	'),
       ('Salomeya', 'Tarskaya', 33, '34, Egora Abakumova, Solncevo'),
       ('Timur', 'Sokolov', 47, '85, Zarechnaya, Korocha'),
       ('Vera', 'Ichetkina', 40, '67, Shkolnaya, Baksan	'),
       ('Nina', 'Gavrilovna', 37, '24, Vesenya, Vzmorie');

insert into accounts (name, balance, client_id)
values ('House', 43034, 1),
       ('New car', 32000, 2),
       ('Gift for daughter', 2000, 1),
       ('Equipment for hiking', 2000, 3),
       ('Financial stock', 9045, 1),
       ('For future travel', 15000, 2),
       ('Motorcycle', 9000, 3),
       ('Apartment repair', 5000, 2),
       ('Test', 20030, 4),
       ('Test', 20030, 5),
       ('Test', 20030, 6),
       ('Test', 20030, 7),
       ('Test', 20030, 8),
       ('Test', 20030, 9),
       ('Test', 20030, 10);
