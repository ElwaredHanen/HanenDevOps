--liquibase formatted sql

--changeset coeurious:1 
create table role (
    id bigserial not null, 
    role varchar(255), 
    primary key (id)
);

create table cgu (
    id bigserial not null,
    content text,
    primary key (id)
);

create table users (
    id bigserial not null, 
    mail varchar(255) unique, 
    password varchar(255), 
    enabled boolean, 
    creation_date timestamp, 
    modification_date timestamp, 
    cgu_consent boolean,
    cgu_id bigint,
    primary key (id),
    constraint fk_cgu foreign key (cgu_id) references cgu(id)
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

