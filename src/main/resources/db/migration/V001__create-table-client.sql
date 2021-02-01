CREATE TABLE client (
    id bigint not null auto_increment,
    name varchar(60) not null,
    email varchar(60) not null,
    fone varchar(60) not null,

    primary key (id)
);