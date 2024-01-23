--liquibase formatted sql

--changeset coeurious:2
create table test (
    id bigserial not null, 
    role varchar(255), 
    primary key (id)
);
