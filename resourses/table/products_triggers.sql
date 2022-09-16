create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);


create or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5 AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();
	
	

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted) and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();
	
	

alter table products disable trigger discount_trigger;

create or replace function tax_add_statement()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_add_trigger_statement
		after insert on products
		referencing new table as inserted
        for each statement
		execute procedure tax_add_statement();


alter table products disable trigger tax_add_trigger_statement;
	

create or replace function tax_add_row()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = new.id;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_add_trigger_row
		before insert
		on products
		for each row
		execute procedure tax_add_row(); 

alter table products disable trigger tax_add_trigger_row;

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function add()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(
		name, price, date)
        values(new.name, new.price, current_date);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_trigger
		after insert
		on products
		for each row
		execute procedure add();

--insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 100);

--select *from products;
select *from history_of_price