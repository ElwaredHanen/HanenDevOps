--liquibase formatted sql

--changeset coeurious:1

create table cgu (
    id bigserial not null,
    content text,
    primary key (id)
);
create table users (
    id bigserial not null, 
    email varchar(255) unique,
    password varchar(255), 
    enabled boolean,
    role varchar(255) default 'USER',
    creation_date timestamp, 
    modification_date timestamp,
    cgu_consent boolean,
    cgu_id bigint,
    primary key (id),
    constraint fk_cgu foreign key (cgu_id) references cgu(id)
);

insert into users values(default, 'toto@toto.net', '12345', 'true', 'USER', '2024-01-25 09:01:15.0', '2024-01-25 09:01:15.0');


