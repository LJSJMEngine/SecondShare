DELETE FROM s1_attachment;
ALTER TABLE s1_attachment AUTO_INCREMENT = 1;
DELETE FROM s1_user_authority ;
ALTER TABLE s1_user_authority AUTO_INCREMENT = 1;
DELETE FROM s1_authority;
ALTER TABLE s1_authority AUTO_INCREMENT =	 1;
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

INSERT INTO s1_user (username, password, name, phoneNM, email, registDate, status) VALUES
('ADMIN1', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '관리자1', '010-1111-2222', 'admin1@gmail.com', NOW(), 0),
('USER1', '$2a$10$6gVaMy7.lbezp8bGRlV2fOArmA3WAk2EHxSKxncnzs28/m3DXPyA2', '회원1', '010-3333-4444', 'user1@gmail.com', NOW(), 0),
('USER2', '$2a$10$7LTnvLaczZbEL0gabgqgfezQPr.xOtTab2NAF/Yt4FrvTSi0Y29Xa', '회원2', '010-5555-6666', 'user2@gmail.com', NOW(), 0),
('USER3', '1234', '회원3', '010-7777-8888', 'user3@gmail.com', NOW(), 0),
('USER4', '1234', '회원4', '010-9999-0000', 'user4@gmail.com', NOW(), 0),
('USER5', '1234', '회원5', '010-9999-0000', 'user5@gmail.com', NOW(), 0),
('USER6', '1234', '회원6', '010-9999-0000', 'user6@gmail.com', NOW(), 0),
('USER7', '1234', '회원7', '010-9999-0000', 'user7@gmail.com', NOW(), 0),
('USER8', '1234', '회원8', '010-9999-0000', 'user8@gmail.com', NOW(), 0),
('USER9', '1234', '회원9', '010-9999-0000', 'user9@gmail.com', NOW(), 0),
('USER10', '1234', '회원10', '010-9999-0000', 'user10@gmail.com', NOW(), 0),
('USER11', '1234', '회원11', '010-9999-0000', 'user11@gmail.com', NOW(), 0),
('USER12', '1234', '회원12', '010-9999-0000', 'user12@gmail.com', NOW(), 0),
('USER13', '1234', '회원13', '010-9999-0000', 'user13@gmail.com', NOW(), 0),
('USER14', '1234', '회원14', '010-9999-0000', 'user14@gmail.com', NOW(), 0),
('USER15', '1234', '회원15', '010-9999-0000', 'user15@gmail.com', NOW(), 0),
('USER16', '1234', '회원16', '010-9999-0000', 'user16@gmail.com', NOW(), 0),
('USER17', '1234', '회원17', '010-9999-0000', 'user17@gmail.com', NOW(), 0),
('USER18', '1234', '회원18', '010-9999-0000', 'user18@gmail.com', NOW(), 0),
('USER19', '1234', '회원19', '010-9999-0000', 'user19@gmail.com', NOW(), 0),
('USER20', '1234', '회원20', '010-9999-0000', 'user20@gmail.com', NOW(), 0),
('USER21', '1234', '회원21', '010-9999-0000', 'user21@gmail.com', NOW(), 0),
('USER22', '1234', '회원22', '010-9999-0000', 'user22@gmail.com', NOW(), 0),
('USER23', '1234', '회원23', '010-9999-0000', 'user23@gmail.com', NOW(), 0),
('USER24', '1234', '회원24', '010-9999-0000', 'user24@gmail.com', NOW(), 0),
('USER25', '1234', '회원25', '010-9999-0000', 'user25@gmail.com', NOW(), 0),
('USER26', '1234', '회원26', '010-9999-0000', 'user26@gmail.com', NOW(), 0),
('USER27', '1234', '회원27', '010-9999-0000', 'user27@gmail.com', NOW(), 0),
('USER28', '1234', '회원28', '010-9999-0000', 'user28@gmail.com', NOW(), 0),
('USER29', '1234', '회원29', '010-9999-0000', 'user29@gmail.com', NOW(), 0),
('USER30', '1234', '회원30', '010-9999-0000', 'user30@gmail.com', NOW(), 0),
('USER31', '1234', '회원31', '010-9999-0000', 'user31@gmail.com', NOW(), 0),
('USER32', '1234', '회원32', '010-9999-0000', 'user32@gmail.com', NOW(), 0),
('USER33', '1234', '회원33', '010-9999-0000', 'user33@gmail.com', NOW(), 0),
('USER34', '1234', '회원34', '010-9999-0000', 'user34@gmail.com', NOW(), 0),
('USER35', '1234', '회원35', '010-9999-0000', 'user35@gmail.com', NOW(), 0),
('USER36', '1234', '회원36', '010-9999-0000', 'user36@gmail.com', NOW(), 0),
('USER37', '1234', '회원37', '010-9999-0000', 'user37@gmail.com', NOW(), 0),
('USER38', '1234', '회원38', '010-9999-0000', 'user38@gmail.com', NOW(), 0),
('USER39', '1234', '회원39', '010-9999-0000', 'user39@gmail.com', NOW(), 0),
('USER40', '1234', '회원40', '010-9999-0000', 'user40@gmail.com', NOW(), 0),
('USER41', '1234', '회원41', '010-9999-0000', 'user41@gmail.com', NOW(), 0),
('USER42', '1234', '회원42', '010-9999-0000', 'user42@gmail.com', NOW(), 0),
('USER43', '1234', '회원43', '010-9999-0000', 'user43@gmail.com', NOW(), 0),
('USER44', '1234', '회원44', '010-9999-0000', 'user44@gmail.com', NOW(), 0),
('USER45', '1234', '회원45', '010-9999-0000', 'user45@gmail.com', NOW(), 0),
('USER46', '1234', '회원46', '010-9999-0000', 'user46@gmail.com', NOW(), 0),
('USER47', '1234', '회원47', '010-9999-0000', 'user47@gmail.com', NOW(), 0),
('USER48', '1234', '회원48', '010-9999-0000', 'user48@gmail.com', NOW(), 0),
('USER59', '1234', '회원49', '010-9999-0000', 'user49@gmail.com', NOW(), 0),
('USER50', '1234', '회원50', '010-9999-0000', 'user50@gmail.com', NOW(), 0)
;
INSERT INTO s1_user_authority VALUES
(1, 1),
(1, 2),
(2, 2)
;

/* 게시물 정보 영역 */

INSERT INTO s1_post (user_id, subject, contents, price, status, regDate, category_id) VALUES
(1, '제목1', '내용1', 20000, 0, NOW(), 1),
(2, '제목2', '내용2', 15000, 1, NOW(), 2),
(3, '제목3', '내용3', 20000, 1, NOW(), 3),
(4, '제목4', '내용4', 15000, 0, NOW(), 4),
(5, '제목5', '내용5', 20000, 1, NOW(), 5),
(6, '제목6', '내용6', 15000, 1, NOW(), 6),
(7, '제목7', '내용7', 15000, 0, NOW(), 1),
(8, '제목8', '내용7', 15000, 0, NOW(), 2),
(9, '제목9', '내용7', 15000, 0, NOW(), 3),
(10, '제목10', '내용7', 15000, 0, NOW(), 4),
(11, '제목11', '내용7', 15000, 0, NOW(), 5),
(12, '제목12', '내용7', 15000, 0, NOW(), 6),
(13, '제목13', '내용7', 15000, 0, NOW(), 1),
(14, '제목14', '내용7', 15000, 0, NOW(), 2),
(15, '제목15', '내용7', 15000, 1, NOW(), 3),
(16, '제목16', '내용7', 15000, 1, NOW(), 4),
(17, '제목17', '내용7', 15000, 0, NOW(), 5),
(18, '제목18', '내용7', 15000, 0, NOW(), 6),
(19, '제목19', '내용7', 15000, 0, NOW(), 1),
(20, '제목20', '내용7', 15000, 0, NOW(), 2),
(21, '제목21', '내용7', 15000, 1, NOW(), 3),
(22, '제목22', '내용7', 15000, 1, NOW(), 4),
(23, '제목23', '내용7', 15000, 0, NOW(), 5),
(24, '제목24', '내용7', 15000, 0, NOW(), 6),
(25, '제목25', '내용7', 15000, 0, NOW(), 1),
(26, '제목26', '내용7', 15000, 0, NOW(), 2),
(27, '제목27', '내용7', 15000, 0, NOW(), 3),
(28, '제목28', '내용7', 15000, 0, NOW(), 4),
(29, '제목29', '내용7', 15000, 0, NOW(), 5),
(30, '제목30', '내용7', 15000, 0, NOW(), 6),
(31, '제목31', '내용7', 15000, 1, NOW(), 1),
(32, '제목32', '내용7', 15000, 1, NOW(), 2),
(33, '제목33', '내용7', 15000, 0, NOW(), 3),
(34, '제목34', '내용7', 15000, 0, NOW(), 4),
(35, '제목35', '내용7', 15000, 1, NOW(), 5),
(36, '제목36', '내용7', 15000, 1, NOW(), 6),
(37, '제목37', '내용7', 15000, 0, NOW(), 1),
(38, '제목38', '내용7', 15000, 0, NOW(), 2),
(39, '제목39', '내용7', 15000, 0, NOW(), 3),
(40, '제목40', '내용7', 15000, 0, NOW(), 4),
(41, '제목41', '내용7', 15000, 0, NOW(), 5),
(42, '제목42', '내용7', 15000, 0, NOW(), 6),
(43, '제목43', '내용7', 15000, 0, NOW(), 1),
(44, '제목44', '내용7', 15000, 0, NOW(), 2),
(45, '제목45', '내용7', 15000, 1, NOW(), 3),
(46, '제목46', '내용7', 15000, 1, NOW(), 4),
(47, '제목47', '내용7', 15000, 0, NOW(), 5),
(48, '제목48', '내용7', 15000, 0, NOW(), 6),
(49, '제목49', '내용7', 15000, 0, NOW(), 1),
(50, '제목50', '내용7', 15000, 0, NOW(), 2)
;

INSERT INTO s1_comment (user_id, post_id, content) VALUES
(1, 1, '댓글1'),
(1, 2, '댓글2'),
(2, 1, '댓글3'),
(2, 2, '댓글4')
;

INSERT INTO s1_review (user_id, post_id, reviewChk, content) VALUES
(2, 2, 2, '설명이 상세해요'),
(2, 3, 2, '상태가 좋아요'),
(2, 4, 1, '친절해요'),
(2, 5, 0, '대답이 느려요'),
(2, 6, 1, '저렴해요')
;

INSERT INTO s1_attachment (post_id, sourcename, filename) VALUES
(1, 'face01.png', 'face01.png')
;

/* 카테고리 설정 */

INSERT INTO s1_category (name) VALUES
('생활용품'),
('패션의류'),
('가전제품'),
('스포츠용품'),
('도서/음반/DVD'),
('문구/오피스')
;

/* 채팅 정보 영역 */





