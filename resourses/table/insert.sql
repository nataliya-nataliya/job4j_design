insert into "role"(name_role) values('user'), ('administrator');

insert into "rules"(rule) values('access'), ('no access');

insert into "role_rules"(rule_id, role_id) values(1, 2), (2, 1);

insert into "users"(name, role_id) values('Ivanov Fedor', 1), ('Sidorova Maria', 2);

insert into "category"(name_category) values('networks'), ('user_application'), ('hardware');

insert into "states"(state) values('open'), ('close');

insert into "item"(name_item, user_id, category_id, state_id) values('no network', 2, 1, 2),
                                                                    ('problem mail', 1, 2, 1), ('problem print', 1, 3, 2);

insert into "comments"(comment, item_id) values('note on screen: no network found', 1),
                                               ('does not open', 2), ('does not send a letter', 2), ('does not print', 3);

insert into "attachs"(attach, item_id) values('file1', 1), ('file2', 2),
                                             ('file3', 2), ('file4', 3);