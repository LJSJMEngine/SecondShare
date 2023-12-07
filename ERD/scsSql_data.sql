DELETE FROM s1_attachment;
ALTER TABLE s1_attachment AUTO_INCREMENT = 1;
DELETE FROM s1_comment;
ALTER TABLE s1_comment AUTO_INCREMENT = 1;
DELETE FROM s1_post;
ALTER TABLE s1_post AUTO_INCREMENT = 1;
DELETE FROM s1_user_authority;
ALTER TABLE s1_user_authority AUTO_INCREMENT = 1;
DELETE FROM s1_authority;
ALTER TABLE s1_authority AUTO_INCREMENT = 1;
DELETE FROM s1_user;
ALTER TABLE s1_user AUTO_INCREMENT = 1;
DELETE FROM s1_notice ;
ALTER TABLE s1_notice AUTO_INCREMENT = 1;

-- authority 설정
INSERT INTO s1_authority (name) VALUES
	('ROLE_ADMIN'),('ROLE_MEMBER')
;

-- sample user
INSERT INTO s1_user (username, password, name, phoneNM, email) VALUES
	('Admin','1234','어드민','010-1234-1234','admin@email.com'),
	('User1','1234','테스트','010-1111-2222', 'test@email.com')
;

-- sample authority
INSERT INTO  s1_user_authority VALUES
(1, 1),
(1, 2),
(2, 2)
;

-- sample post
INSERT INTO s1_post (user_id, subject, contents) VALUES
(1, '공지입니다1', '테스트입니다'),
(1, '제목입니다1', '내용입니다1'),
(2, '제목입니다2', '내용입니다2')
;

-- sample comment
INSERT INTO s1_comment (user_id, post_id, content) VALUES
(1, 1, '테스트 진행중 -admin'),
(2, 1, '테스트 진행중 -user1')
;

-- sample attachment
INSERT INTO s1_attachment (post_id, sourcename, filename) VALUES
(1, 'face01.png', 'face01.png'),
(1, 'face02.png', 'face02.png')
;

-- sample notice
INSERT INTO s1_notice (user_id, status, contents) VALUES
(2, 'NOTICE', '공지사항입니다! '),
(2, 'ORDER_SUCCESS', '거래가 완료되었습니다!')
;
