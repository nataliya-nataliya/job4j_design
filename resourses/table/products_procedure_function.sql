create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 100);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 0, 100);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 20);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 0, 20);

create or replace procedure delete_data(i_id integer)
language 'plpgsql'
as $$
    BEGIN
    delete from products 
	where i_id = id and count = 0 and price < 50;
    END
$$;
-- call delete_data(2);
-- call delete_data(4);

create or replace function f_delete_data(i_id integer)
returns void
language 'plpgsql'
as
$$
    begin
        delete from products 
		where i_id = id and count = 0 and price < 50;
    end;
$$;

select f_delete_data(8);
select * from products;
