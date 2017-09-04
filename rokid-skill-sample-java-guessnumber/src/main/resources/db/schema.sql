create table numbers (
  id INT4 AUTO_INCREMENT PRIMARY KEY,
  user_id varchar(36) NOT NULL,
  num INT4 NOT NULL,
  UNIQUE (user_id)
);