CREATE SCHEMA ers;

CREATE TABLE ers.ers_user_roles (
	ers_user_role_id serial NOT NULL DEFAULT nextval('ers.ers_user_roles_ers_user_role_id_seq'::regclass),
	user_role varchar(30) NOT NULL,
	CONSTRAINT ers_user_roles_pkey PRIMARY KEY (ers_user_role_id)
);

INSERT INTO ers.ers_user_roles (user_role)
VALUES ('Financial Manager');

CREATE TABLE ers.ers_users (
	ers_users_id serial NOT NULL DEFAULT nextval('ers.ers_users_ers_users_id_seq'::regclass),
	ers_username varchar(50) NOT NULL,
	ers_password varchar NOT NULL,
	user_first_name varchar(100) NOT NULL,
	user_last_name varchar(100) NOT NULL,
	user_email varchar(150) NOT NULL,
	user_role_id int4 NOT NULL,
	CONSTRAINT ers_users_ers_username_key UNIQUE (ers_username),
	CONSTRAINT ers_users_pkey PRIMARY KEY (ers_users_id),
	CONSTRAINT ers_users_user_email_key UNIQUE (user_email),
	CONSTRAINT user_roles_fk FOREIGN KEY (user_role_id) REFERENCES ers.ers_user_roles(ers_user_role_id)
);

CREATE TABLE ers.ers_reimbursement_type (
	reimb_type_id serial NOT NULL DEFAULT nextval('ers.ers_reimbursement_type_reimb_type_id_seq'::regclass),
	reimb_type varchar(30) NOT NULL,
	CONSTRAINT ers_reimbursement_type_pkey PRIMARY KEY (reimb_type_id)
);

CREATE TABLE ers.ers_reimbursement_status (
	reimb_status_id serial NOT NULL DEFAULT nextval('ers.ers_reimbursement_status_reimb_status_id_seq'::regclass),
	reimb_status varchar(10) NOT NULL,
	CONSTRAINT ers_reimbursement_status_pkey PRIMARY KEY (reimb_status_id)
);

INSERT INTO ers.ers_reimbursement_status (reimb_status)
VALUES ('pending', 'approved', 'denied');

CREATE TABLE ers.ers_reimbursement (
	reimb_id serial NOT NULL DEFAULT nextval('ers.ers_reimbursement_reimb_id_seq'::regclass),
	reimb_amount numeric(17,2) NOT NULL,
	reimb_submitted timestamp NOT NULL,
	reimb_resolved timestamp NULL,
	reimb_description varchar(250) NULL,
	reimb_reciept bytea NULL,
	reimb_author int4 NOT NULL,
	reimb_resolver int4 NULL,
	reimb_status_id int4 NOT NULL,
	reimb_type_id int4 NOT NULL,
	CONSTRAINT ers_reimbursement_pkey PRIMARY KEY (reimb_id),
	CONSTRAINT ers_reimbursement_status_fk FOREIGN KEY (reimb_status_id) REFERENCES ers.ers_reimbursement_status(reimb_status_id),
	CONSTRAINT ers_reimbursement_type_fk FOREIGN KEY (reimb_type_id) REFERENCES ers.ers_reimbursement_type(reimb_type_id),
	CONSTRAINT ers_users_fk_auth FOREIGN KEY (reimb_author) REFERENCES ers.ers_users(ers_users_id),
	CONSTRAINT ers_users_fk_reslvr FOREIGN KEY (reimb_resolver) REFERENCES ers.ers_users(user_role_id)
);
