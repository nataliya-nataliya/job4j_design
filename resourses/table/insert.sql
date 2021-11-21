insert into "users"(name) values('Ivanov Fedor'), ('Sidorova Maria');

insert into "role"(name_role, user_id) values('user', 1), ('administrator', 2);

insert into "rules"(rule) values('access', 'no access');

insert into "role_rules"(rule_id, role_id) values(1, 2), (2, 1);

insert into "item"(name_item, user_id) values('no network', 2),
 ('problem mail', 1), ('problem print', 1 );

insert into "comments"(comment, item_id) values('note on screen: no network found', 1),
 ('does not open', 2), ('does not send a letter', 2), ('does not print', 3);

insert into "attachs"(attach, item_id) values('file1', 1), ('file2', 2), 
('file3', 2), ('file4', 3);

insert into "category"(name_category, item_id) values('networks', 1), ('user_application', 2), ('hardware', 3);

insert into "states"(state, item_id) values('open', 1), ('close', 2), ('open', 3);