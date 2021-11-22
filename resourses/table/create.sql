create table "role"(
id serial primary key,
name_role varchar(255)
);

create table "users"(
id serial primary key,
name varchar(255),
role_id int references "role"(id)
);

create table "rules"(
id serial primary key,
rule varchar(255)
);

create table "role_rules"(
id serial primary key,
rule_id int references "rules"(id),
role_id int references "role"(id)
);

create table "category"(
id serial primary key,
name_category varchar(255)
);

create table "states"(
id serial primary key,
state varchar(255)
);

create table "item"(
id serial primary key,
name_item varchar(255),
user_id int references "users"(id),
category_id int references "category"(id),
state_id int references "states"(id)
);

create table "comments"(
id serial primary key,
comment varchar(255),
item_id int references "item"(id)
);

create table "attachs"(
id serial primary key,
attach varchar(255),
item_id int references "item"(id)
);