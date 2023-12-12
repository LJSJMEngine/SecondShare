DELETE FROM s1_attachment;
ALTER TABLE s1_attachment AUTO_INCREMENT = 1;
DELETE FROM s1_user_authority ;
ALTER TABLE s1_user_authority AUTO_INCREMENT = 1;
DELETE FROM s1_authority;
ALTER TABLE s1_authority AUTO_INCREMENT = 1;
DELETE FROM s1_post_category;
ALTER TABLE s1_post_category AUTO_INCREMENT = 1;
DELETE FROM s1_category;
ALTER TABLE s1_category AUTO_INCREMENT = 1;
DELETE FROM s1_chatmessage;
ALTER TABLE s1_chatmessage AUTO_INCREMENT = 1;
DELETE FROM s1_chatroom_user;
ALTER TABLE s1_chatroom_user AUTO_INCREMENT = 1;
DELETE FROM s1_chatroom;
ALTER TABLE s1_chatroom AUTO_INCREMENT = 1;
DELETE FROM s1_comment;
ALTER TABLE s1_comment AUTO_INCREMENT = 1;
DELETE FROM s1_like;
ALTER TABLE s1_like AUTO_INCREMENT = 1;
DELETE FROM s1_location;
ALTER TABLE s1_location AUTO_INCREMENT = 1;
DELETE FROM s1_notice;
ALTER TABLE s1_notice AUTO_INCREMENT = 1;
DELETE FROM s1_review;
ALTER TABLE s1_review AUTO_INCREMENT = 1;
DELETE FROM s1_post;
ALTER TABLE s1_post AUTO_INCREMENT = 1;
DELETE FROM s1_user;
ALTER TABLE s1_user AUTO_INCREMENT = 1;

/* 유저 정보 영역 */

INSERT INTO s1_authority (name) VALUES
('ROLE_ADMIN'), ('ROLE_MEMBER')
;

INSERT INTO s1_user (username, password, name, phoneNM, email, status) VALUES
('ADMIN1', '1234', '관리자1', '010-1111-2222', 'admin1@gmail.com', 0),
('USER1', '1234', '회원1', '010-3333-4444', 'user1@gmail.com', 0)
;

INSERT INTO s1_user_authority VALUES
(1, 1),
(1, 2),
(2, 2)
;

/* 게시물 정보 영역 */

INSERT INTO s1_post (user_id, subject, contents) VALUES
(1, '제목1', '내용1'),
(2, '제목2', '내용2')
;

INSERT INTO s1_comment (user_id, post_id, content) VALUES
(1, 1, '댓글1'),
(1, 2, '댓글2'),
(2, 1, '댓글3'),
(2, 2, '댓글4')
;

INSERT INTO s1_attachment (post_id, sourcename, filename) VALUES
(1, 'face01.png', 'face01.png')
;

/* 카테고리 설정 */

INSERT INTO s1_category (name) VALUES
('생활용품'),
('가전제품')
;

/* 채팅 정보 영역 */


INSERT INTO s1_chatroom (subject,post_id ,buyer_id, seller_id) VALUES
('채팅방 이름', 3,1,2)
;


DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$
 
CREATE PROCEDURE loopInsert()
BEGIN
    DECLARE i INT DEFAULT 1;        
    WHILE i <= 500 DO
     
    INSERT INTO s1_user(username, password, name, phoneNM, email, status) 
      VALUES (concat('id',i), concat('1234qwer',i), concat('이름',i), concat('010-1234-', i), concat(i, '@naver.com'), 0);
      SET i = i + 1;
     
     INSERT INTO s1_post (user_id, subject, contents) 
     VALUES (i, concat('제목',i), concat('내용',i))
;
    END WHILE;
END$$
DELIMITER $$

CALL loopInsert;
