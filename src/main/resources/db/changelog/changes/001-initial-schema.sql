create table role (id bigserial not null, role varchar(255), primary key (id));
create table users (enabled boolean, id bigserial not null, password varchar(255), username varchar(255) unique, primary key (id));
create table users_roles (role_id bigint not null, user_id bigint not null);
alter table if exists users_roles add constraint FKt4v0rrweyk393bdgt107vdx0x foreign key (role_id) references role;
alter table if exists users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users;