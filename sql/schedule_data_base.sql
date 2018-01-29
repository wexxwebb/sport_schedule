create table if not exists admin_data
(
	id serial not null
		constraint pk_admins
		primary key,
	user_id integer not null
		constraint user_id_unique
		unique
)
;

create table if not exists exercise
(
	id serial not null
		constraint pk_exercises
		primary key,
	exercise_id integer not null,
	training_id integer not null,
	approach integer not null,
	repetition integer not null,
	weigth integer not null
)
;

create table if not exists exercise_data
(
	id serial not null
		constraint pk_s_exercises
		primary key,
	name varchar(100) not null
		constraint name_unique
		unique
)
;

alter table exercise
	add constraint fk_exercises_s_exercises
foreign key (exercise_id) references exercise_data
on update cascade on delete restrict
;

create table if not exists person
(
	id serial not null
		constraint pk_persons
		primary key,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	birthday date not null,
	sex_id integer not null
)
;

create table if not exists sex
(
	id serial not null
		constraint pk_s_sex
		primary key,
	sex varchar(50) not null
		constraint index_sex
		unique
)
;

alter table person
	add constraint fk_person_s_sex
foreign key (sex_id) references sex
on update cascade on delete restrict
;

create table if not exists state
(
	id serial not null
		constraint pk_s_state
		primary key,
	state varchar(50) not null
		constraint state_unique
		unique,
	enabled boolean default true not null,
	role varchar(50) default 'role_user'::character varying not null
)
;

create table if not exists training
(
	id serial not null
		constraint pk_trainings
		primary key,
	user_id integer not null,
	create_date date default now() not null,
	training_date date not null
)
;

alter table exercise
	add constraint fk_exercises_trainings
foreign key (training_id) references training
on update cascade on delete restrict
;

create table if not exists user_data
(
	id serial not null
		constraint pk_users
		primary key,
	person_id integer not null
		constraint fk_users_persons
		references person
		on update cascade on delete restrict,
	user_login varchar(50) not null
		constraint login_unique
		unique,
	user_password varchar(50) not null,
	state_id integer default 1 not null
		constraint fk_users_s_state
		references state
		on update cascade on delete restrict,
	date_reg date default now() not null
)
;

alter table admin_data
	add constraint fk_admins_users
foreign key (user_id) references user_data
on update cascade on delete restrict
;

alter table training
	add constraint fk_trainings_users
foreign key (user_id) references user_data
on update cascade on delete restrict
;

