--liquibase formatted sql

--changeset coeurious:1 
create table role (
    id bigserial not null, 
    role varchar(255), 
    primary key (id)
);

create table users (
    id bigserial not null, 
    mail varchar(255) unique, 
    password varchar(255), 
    enabled boolean, 
    creation_date timestamp, 
    modification_date timestamp, 
    primary key (id)
);

create table users_roles (
    role_id bigint not null, 
    users_id bigint not null
);

alter table if exists users_roles add constraint FK_role foreign key (role_id) references role;
alter table if exists users_roles add constraint FK_users foreign key (users_id) references users;

insert into role values(default, 'reader');
insert into users values(default, 'toto@laposte.net', '12345', 'true', '16/02/2023', '16/02/2023');
insert into users_roles values(1, 1);

--create table users(
--    id bigserial not null,
--    username varchar(50) not null,
--    password varchar(500) not null,
--    enabled boolean not null,
--    primary key (id)
--);
--create table authorities (
--    id bigserial not null,
--    username varchar(50) not null,
--    authority varchar(50) not null,
--    primary key (id)
--);
--create unique index ix_auth_username on authorities (username,authority);
--
--insert into users values(default, 'happy', '12345', 'true');
--insert into authorities values(default, 'happy', 'write');