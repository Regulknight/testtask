create table app_user(
	id serial primary key,
	first_name text,
	last_name text
);

create table user_request(
	id serial primary key,
	title text,
	description text,
	request_date date,
	app_user_id integer references app_user(id)
);

insert into app_user(first_name, last_name) values ('Lev', 'Tolstoi');
insert into app_user(first_name, last_name) values ('Maksim', 'Gorky');
insert into app_user(first_name, last_name) values ('Fedor', 'Dostoevskii');
insert into app_user(first_name, last_name) values ('Ivan', 'Bunin');
insert into app_user(first_name, last_name) values ('Anton', 'Chehov');

insert into user_request(title, description, request_date, app_user_id) values ('Trains problem', '', current_date, 1);
insert into user_request(title, description, request_date, app_user_id) values ('The biggest oak', '', current_date, 1);
insert into user_request(title, description, request_date, app_user_id) values ('Birds with cool name', '', current_date, 2);
insert into user_request(title, description, request_date, app_user_id) values ('Incidents with axe', '', current_date, 3);
insert into user_request(title, description, request_date, app_user_id) values ('How to write like Gorky', '', current_date, 4);
insert into user_request(title, description, request_date, app_user_id) values ('What should i do with The Cherry Orchard', '', current_date, 5);