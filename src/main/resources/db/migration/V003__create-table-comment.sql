CREATE TABLE comment (
  id bigint not null auto_increment,
  order_service_id bigint not null,
  description text not null,
  send_date datetime not null,

  primary key (id)
);

ALTER TABLE comment ADD CONSTRAINT fk_comment_order_service FOREIGN KEY (order_service_id) REFERENCES order_service (id);