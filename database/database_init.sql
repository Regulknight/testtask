create table app_user(
	id serial primary key,
	username text,
	password text,
	first_name text,
	last_name text,
	is_active boolean
);

create table user_request(
	id serial primary key,
	title text,
	description text,
	request_date date,
	app_user_id integer references app_user(id)
);

create table app_role(
    id serial primary key,
    name text
);

create table app_user_roles(
    user_id integer references app_user(id),
    roles_id integer references app_role(id)
);

insert into app_user(first_name, last_name, is_active, username, password) values ('Lev', 'Tolstoi', true, 'LT', '12');
insert into app_user(first_name, last_name, is_active, username, password) values ('Maksim', 'Gorky', true, 'MG', '13');
insert into app_user(first_name, last_name, is_active, username, password) values ('Fedor', 'Dostoevskii', true, 'FD', '14');
insert into app_user(first_name, last_name, is_active, username, password) values ('Ivan', 'Bunin', true, 'IB', '15');
insert into app_user(first_name, last_name, is_active, username, password) values ('Anton', 'Chehov', true, 'AC', '16');
insert into app_user(first_name, last_name, is_active, username, password) values ('Admin', '', true, 'admin', 'admin');

insert into user_request(title, description, request_date, app_user_id) values ('Trains problem', '', current_date, 1);
insert into user_request(title, description, request_date, app_user_id) values ('The biggest oak', '', current_date, 1);
insert into user_request(title, description, request_date, app_user_id) values ('Birds with cool name', '', current_date, 2);
insert into user_request(title, description, request_date, app_user_id) values ('Incidents with axe', '', current_date, 3);
insert into user_request(title, description, request_date, app_user_id) values ('How to write like Gorky', '', current_date, 4);
insert into user_request(title, description, request_date, app_user_id) values ('What should i do with The Cherry Orchard', '', current_date, 5);

insert into app_role(name) values ('ROLE_USER');
insert into app_role(name) values ('ROLE_ADMIN');

insert into app_user_roles(user_id, roles_id) values (1, 1);
insert into app_user_roles(user_id, roles_id) values (2, 1);
insert into app_user_roles(user_id, roles_id) values (3, 1);
insert into app_user_roles(user_id, roles_id) values (4, 1);
insert into app_user_roles(user_id, roles_id) values (5, 1);
insert into app_user_roles(user_id, roles_id) values (6, 1);
insert into app_user_roles(user_id, roles_id) values (6, 2);