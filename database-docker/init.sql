create database hotel_db;

use hotel_db;

create table if not exists users
(
  user_id      int not null,
  account_type varchar(255),
  name         varchar(255),
  surname      varchar(255),
  card_number  int,
  PRIMARY KEY (user_id)
);

insert into users value
  (1, 'Normal', 'Damian', 'Damian', 111111111), (2, 'Normal', 'Andrzej', 'Andrzej', 222222222), (3, 'Premium', 'Stanislaw', 'Stanislaw', 333333333);


create table if not exists orders
(
    order_id serial primary key  not null,
    user_id int not null,
    data DATETIME,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

insert into orders value
  (1, 1, '2019-04-23 01:01:01'), (2, 1, '2019-04-23 01:01:02'), (3, 2, '2019-04-23 02:02:02'),
  (4, 2, '2019-04-23 02:02:03'), (5, 3, '2019-04-23 03:03:03'), (6, 3, '2019-04-23 03:03:04');

create table if not exists services
(
  service_id int not null,
  name       varchar(255),
  price      DOUBLE,
  PRIMARY KEY (service_id)
);

insert into services value
  (1, 'Service1', 1.23), (2, 'Service2', 2.23), (3, 'Service3', 4.12), (4, 'Service4', 5.12);

create table if not exists order_service
(
  order_service_id serial primary key,
  order_id         int not null,
  service_id       int not null,
  FOREIGN KEY (order_id) REFERENCES orders (order_id),
  FOREIGN KEY (service_id) REFERENCES services (service_id)
);

insert into order_service value
  (1, 1, 1), (2, 1, 2), (3, 2, 3), (4, 2, 4), (5, 3, 1), (6, 3, 2), (7, 4, 3),
  (8, 4, 4), (9, 5, 1), (10, 5, 2), (11, 6, 3), (12, 6, 4);