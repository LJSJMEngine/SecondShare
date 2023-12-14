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

INSERT INTO s1_user (username, password,repassword, name, phoneNM, email) VALUES
('ADMIN1', '1234','1234', '관리자1', '010-1111-2222', 'admin1@gmail.com'),
('USER1', '1234','1234', '회원1', '010-3333-4444', 'user1@gmail.com')
;

INSERT INTO s1_user_authority VALUES
(1, 1),
(1, 2),
(2, 2)
;

/* 게시물 정보 영역 */

INSERT INTO s1_post (user_id, subject, contents,price,status) VALUES
(1, '제목1', '내용1',5000,1),
(2, '제목2', '내용2',6000,2),
(1, '제목3', '내용3',26100,1),
(2, '제목4', '내용4',46400,1),
(2, '제목5', '내용5',52000,2),
(1, '제목6', '내용5',52000,2),
(1, '제목7', '내용5',52000,2),
(2, '제목8', '내용5',52000,2),
(2, '제목9', '내용5',52000,2),
(1, '제목10', '내용5',52000,1),
(1, '제목11', '내용5',52000,1),
(1, '제목12', '내용5',52000,1),
(2, '제목13', '내용5',52000,1),
(1, '제목14', '내용5',52000,1),
(2, '제목15', '내용5',52000,1),
(1, '제목16', '내용5',52000,2),
(2, '제목17', '내용5',52000,2),
(1, '제목18', '내용5',52000,2),
(2, '제목19', '내용5',52000,1),
(1, '제목20', '내용5',52000,2),
(2, '제목21', '내용5',52000,1),
(1, '제목21', '내용5',52000,2),
(2, '제목22', '내용5',52000,1),
(2, '제목23', '내용5',52000,2),
(1, '제목24', '내용5',52000,2)

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
('패션의류'),
('생활용품'),
('가전제품'),
('스포츠용품'),
('도서/음반/DVD'),
('문구/오피스')
;

/* 채팅 정보 영역 */

