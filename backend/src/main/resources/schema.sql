drop table if exists currency CASCADE;
create table currency (id  bigserial not null, rate numeric(19, 2), symbol varchar(255), primary key (id));