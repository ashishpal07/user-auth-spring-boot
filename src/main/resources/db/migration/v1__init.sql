CREATE TABLE `role` (
  id BIGINT NOT NULL,
   name VARCHAR(255) NULL,
   CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE user (
   id BIGINT NOT NULL,
   name VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   password VARCHAR(255) NULL,
   is_email_verified BIT(1) NOT NULL,
   CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE token (
  id BIGINT NOT NULL,
  token VARCHAR(255) NULL,
  user_id BIGINT NULL,
  expiry_at datetime NULL,
  CONSTRAINT pk_token PRIMARY KEY (id)
);

ALTER TABLE token ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);
CREATE TABLE user_roles (
  user_id BIGINT NOT NULL,
  roles_id BIGINT NOT NULL
);

ALTER TABLE user_roles ADD CONSTRAINT fk_user_roles_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE user_roles ADD CONSTRAINT fk_user_roles_on_user FOREIGN KEY (user_id) REFERENCES user (id);