create sequence book_seq start with 1 increment by 50;
create table book (price numeric(38,2), publish_date date, id bigint not null, title varchar(255), primary key (id));
create table testtables (id bigint not null, name varchar(255), primary key (id));
create sequence book_seq start with 1 increment by 50;
create sequence polls_seq start with 1 increment by 50;
create sequence users_seq start with 1 increment by 50;
create table book (price numeric(38,2), publish_date date, id bigint not null, title varchar(255), primary key (id));
create table polls (
    poll_id bigint not null,
    ring_id bigint not null,
    tech_id bigint not null,
    user_id bigint,
    time varchar(255),
    primary key (poll_id));
create table testtable (id bigint not null, name varchar(255), primary key (id));
create table users (
    role_id bigint,
    user_id bigint not null,
    login varchar(255),
    password varchar(255),
    refresh_token varchar(255),
    primary key (user_id));
alter table if exists polls add constraint FKrmn4rau93pxxyqgi57dqng2rl
    foreign key (user_id) references users;
