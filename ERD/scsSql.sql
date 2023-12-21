SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS s1_attachment;
DROP TABLE IF EXISTS s1_user_authority;
DROP TABLE IF EXISTS s1_authority;
DROP TABLE IF EXISTS s1_chatMessage;
DROP TABLE IF EXISTS s1_chatroom;
DROP TABLE IF EXISTS s1_comment;
DROP TABLE IF EXISTS s1_heart;
DROP TABLE IF EXISTS s1_review;
DROP TABLE IF EXISTS s1_post;
DROP TABLE IF EXISTS s1_category;
DROP TABLE IF EXISTS s1_location;
DROP TABLE IF EXISTS s1_notice;
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
	id int NOT NULL AUTO_INCREMENT,
	name varchar(40) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE s1_chatMessage
(
	chat_id int NOT NULL AUTO_INCREMENT,
	sender_id int NOT NULL,
	room_id int NOT NULL,
	content text,
	checkedContent boolean,
	createDate datetime,
	PRIMARY KEY (chat_id)
);


CREATE TABLE s1_chatroom
(
	room_id int NOT NULL AUTO_INCREMENT,
	post_id int NOT NULL,
	buyer_id int NOT NULL,
	seller_id int,
	createDate datetime,
	lastUpdateDate datetime,
	subject varchar(50) NOT NULL,
	roomState int,
	PRIMARY KEY (room_id)
);


CREATE TABLE s1_comment
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	post_id int NOT NULL,
	content longtext NOT NULL,
	regdate datetime,
	soldCheck int,
	PRIMARY KEY (id)
);


CREATE TABLE s1_heart
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	post_id int NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE s1_location
(
	user_id int NOT NULL,
	arr1 text,
	arr2 text,
	zipcode int
);


CREATE TABLE s1_notice
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	status int,
	status_name varchar(50),
	contents text,
	readChk boolean,
	PRIMARY KEY (id)
);


CREATE TABLE s1_post
(
	post_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	category_id int NOT NULL,
	subject varchar(200) NOT NULL,
	contents longtext,
	price int,
	viewCnt int DEFAULT 0,
	status int DEFAULT 0,
	regDate datetime,
	sampleImg int,
	PRIMARY KEY (post_id)
);


CREATE TABLE s1_review
(
	id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	post_id int NOT NULL,
	reviewChk int NOT NULL,
	content longtext NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE s1_user
(
	id int NOT NULL AUTO_INCREMENT,
	username varchar(100) NOT NULL,
	password varchar(200) NOT NULL,
	name varchar(20) NOT NULL,
	phoneNM varchar(20) NOT NULL,
	email varchar(100) NOT NULL,
	age int,
	registDate datetime,
	status int,
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

ALTER TABLE s1_post
ADD CONSTRAINT s1_post_ibfk_1
FOREIGN KEY (category_id) REFERENCES s1_category(id)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE s1_post
DROP FOREIGN KEY s1_post_ibfk_1;

ALTER TABLE s1_post
	ADD FOREIGN KEY (category_id)
	REFERENCES s1_category (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE s1_chatMessage
	ADD FOREIGN KEY (room_id)
	REFERENCES s1_chatroom (room_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE s1_attachment
	ADD FOREIGN KEY (post_id)
	REFERENCES s1_post (post_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE s1_chatroom
	ADD FOREIGN KEY (post_id)
	REFERENCES s1_post (post_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE s1_comment
	ADD FOREIGN KEY (post_id)
	REFERENCES s1_post (post_id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE s1_heart
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


ALTER TABLE s1_chatMessage
	ADD FOREIGN KEY (sender_id)
	REFERENCES s1_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE s1_chatroom
	ADD FOREIGN KEY (buyer_id)
	REFERENCES s1_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE s1_comment
	ADD FOREIGN KEY (user_id)
	REFERENCES s1_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE s1_heart
	ADD FOREIGN KEY (user_id)
	REFERENCES s1_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE s1_location
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
	ON DELETE RESTRICT
;


ALTER TABLE s1_review
	ADD FOREIGN KEY (user_id)
	REFERENCES s1_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE s1_user_authority
	ADD FOREIGN KEY (user_id)
	REFERENCES s1_user (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;



