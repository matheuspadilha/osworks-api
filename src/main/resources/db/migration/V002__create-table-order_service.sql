CREATE TABLE order_service (
    id bigint not null auto_increment,
    client_id bigint not null,
    description text not null,
    price decimal(10, 2) not null,
    status varchar(20) not null,
    opening_date datetime not null,
    closing_date datetime,

    primary key (id)
);

ALTER TABLE order_service ADD CONSTRAINT fk_order_service_client FOREIGN KEY (client_id) REFERENCES client (id);
