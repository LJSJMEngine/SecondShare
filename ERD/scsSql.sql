SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS s1_attachment;
DROP TABLE IF EXISTS s1_user_authority;
DROP TABLE IF EXISTS s1_authority;
DROP TABLE IF EXISTS s1_category;
DROP TABLE IF EXISTS s1_comment;
DROP TABLE IF EXISTS s1_notice;
DROP TABLE IF EXISTS s1_post_tag;
DROP TABLE IF EXISTS s1_review;
DROP TABLE IF EXISTS s1_post;
DROP TABLE IF EXISTS s1_tag;
DROP TABLE IF EXISTS s1_user;




/* Create Tables */

CREATE TABLE s1_attachment
(
	id int NOT NULL AUTO_INCREMENT,
	post_id int NOT NULL,
	sourcename varchar(100) NOT NULL,
	filename varchar(100) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE s1_authority
(
	id int NOT NULL AUTO_INCREMENT,
	name varchar(40),
	PRIMARY KEY (id)
);


CREATE TABLE s1_category
(
	c_id int NOT NULL AUTO_INCREMENT,
	c_name varchar(50),
	c_class varchar(50),
	c_type varchar(50),
	c_price int,
	PRIMARY KEY (c_id),
	UNIQUE (c_class),
	UNIQUE (c_type)
);


CREATE TABLE s1_comment
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	post_id int NOT NULL,
	content longtext NOT NULL,
	regdate datetime,
	soldCheck boolean,
	PRIMARY KEY (id)
);


CREATE TABLE s1_notice
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	status longtext,
	contents longtext,
	PRIMARY KEY (id)
);


CREATE TABLE s1_post
(
	post_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	subject varchar(200) NOT NULL,
	contents longtext,
	price int,
	viewCnt int,
	regDate datetime,
	status boolean,
	goodCnt int,
	tags longtext,
	PRIMARY KEY (post_id)
);


CREATE TABLE s1_post_tag
(
	post_id int NOT NULL,
	tag_id int NOT NULL
);


CREATE TABLE s1_review
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	post_id int NOT NULL,
	reviewChk boolean NOT NULL,
	content longtext,
	PRIMARY KEY (id)
);


CREATE TABLE s1_tag
(
	id int NOT NULL AUTO_INCREMENT,
	name varchar(40) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE s1_user
(
	id int NOT NULL AUTO_INCREMENT,
	username varchar(100) NOT NULL,
	password varchar(22) NOT NULL,
	name varchar(20) NOT NULL,
	phoneNM varchar(20) NOT NULL,
	email varchar(80) NOT NULL,
	age int,
	registDate datetime,
	location longtext,
	PRIMARY KEY (id),
	UNIQUE (username),
	UNIQUE (email)
);


CREATE TABLE s1_user_authority
(
	user_id int NOT NULL,
	authority_id int NOT NULL
);



/* Create Foreign Keys */

ALTER TABLE s1_user_authority
	ADD FOREIGN KEY (authority_id)
	REFERENCES s1_authority (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE s1_attachment
	ADD FOREIGN KEY (post_id)
	REFERENCES s1_post (post_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE s1_comment
	ADD FOREIGN KEY (post_id)
	REFERENCES s1_post (post_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE s1_post_tag
	ADD FOREIGN KEY (post_id)
	REFERENCES s1_post (post_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE s1_review
	ADD FOREIGN KEY (post_id)
	REFERENCES s1_post (post_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE s1_post_tag
	ADD FOREIGN KEY (tag_id)
	REFERENCES s1_tag (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE s1_comment
	ADD FOREIGN KEY (user_id)
	REFERENCES s1_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE s1_notice
	ADD FOREIGN KEY (user_id)
	REFERENCES s1_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE s1_post
	ADD FOREIGN KEY (user_id)
	REFERENCES s1_user (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE s1_review
	ADD FOREIGN KEY (user_id)
	REFERENCES s1_user (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE s1_user_authority
	ADD FOREIGN KEY (user_id)
	REFERENCES s1_user (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;



