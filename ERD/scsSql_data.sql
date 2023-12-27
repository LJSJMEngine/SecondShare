DELETE FROM s1_attachment;
ALTER TABLE s1_attachment AUTO_INCREMENT = 1;
DELETE FROM s1_user_authority ;
ALTER TABLE s1_user_authority AUTO_INCREMENT = 1;
DELETE FROM s1_authority;
ALTER TABLE s1_authority AUTO_INCREMENT = 1;
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
DELETE FROM s1_heart;
ALTER TABLE s1_heart AUTO_INCREMENT = 1;
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
('ADMIN1', '$2a$10$6gVaMy7.lbezp8bGRlV2fOArmA3WAk2EHxSKxncnzs28/m3DXPyA2', '관리자1', '01011112222', 'admin1@gmail.com', NOW(), 0),
('USER1', '$2a$10$7LTnvLaczZbEL0gabgqgfezQPr.xOtTab2NAF/Yt4FrvTSi0Y29Xa', '회원1', '01033334444', 'user1@gmail.com', NOW(), 0),
('USER2', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원2', '01055556666', 'user2@gmail.com', NOW(), 0)
;



INSERT INTO s1_notice (user_id, status, status_name, subject, contents, readChk) VALUES
(1,0,"읽음","제목","내용",false);

INSERT INTO s1_user_authority VALUES
(1, 1),
(2, 2),
(3, 2)
;

INSERT INTO s1_category (name) VALUES
('생활용품'),
('패션의류'),
('가전제품'),
('스포츠용품'),
('도서/음반/DVD'),
('문구/오피스')
;

/* 게시물 정보 영역 */

INSERT INTO s1_post (user_id, subject, contents, price, status, regDate, category_id) VALUES
(1, '제목1', '내용1', 20000, 1, NOW(), 1),
(1, '제목2', '내용2', 20000, 0, NOW(), 1),
(2, '제목3', '내용3', 20000, 1, NOW(), 2),
(2, '제목4', '내용4', 20000, 0, NOW(), 2),
(3, '제목5', '내용5', 20000, 1, NOW(), 3)
;

INSERT INTO s1_comment (user_id, post_id, content) VALUES
(1, 1, '댓글1'),
(1, 2, '댓글2'),
(2, 1, '댓글3'),
(2, 2, '댓글4')
;

INSERT INTO s1_review (user_id, post_id, reviewChk, reviewRate, content) VALUES
(2, 2, 2, 3, '설명이 상세해요'),
(2, 3, 2, 2, '상태가 좋아요'),
(2, 4, 1, 2, '친절해요')
;

INSERT INTO s1_attachment (post_id, sourcename, filename, isImage) VALUES
(1, 'phone1.jpg', 'phone1.jpg', TRUE),
(1, 'phone2.jpg', 'phone2.jpg', TRUE),
(2, 'phone2.jpg', 'phone2.jpg', TRUE),
(3, 'phone3.jpg', 'phone3.jpg', TRUE),
(4, 'img_avatar1.png', 'img_avatar1.png', TRUE),
(5, 'img_avatar2.png', 'img_avatar2.png', TRUE)
;

INSERT INTO s1_notice (status, user_id, status_name, subject, contents,readChk) VALUES
(1, 1, '공지', '환영인사', '어서오세요!',false),
(1, 1, '공지', '공지1', '공지입니다',true),
(2, 1, '알림', '알림1', '알림입니다',false)
;

UPDATE s1_post
SET sampleImg = 1
WHERE post_id IN (SELECT post_id FROM s1_attachment WHERE isImage = TRUE)
;

/*INSERT INTO s1_heart (user_id, post_id, is_active, created_at)
VALUES
    (2, 1, TRUE, NOW()),
    (2, 7, TRUE, NOW()),
    (3, 1, TRUE, NOW()),
    (3, 2, TRUE, NOW()),
    (3, 3, TRUE, NOW()),
    (3, 4, TRUE, NOW()),
    (3, 5, TRUE, NOW()),
    (3, 6, TRUE, NOW())
   ;*/


/*UPDATE s1_post
SET heart_count = (
    SELECT COUNT(*)
    FROM s1_heart
    WHERE s1_heart.post_id = s1_post.post_id
    AND s1_heart.is_active = TRUE
)
WHERE s1_post.post_id IN (
    SELECT post_id
    FROM s1_heart
    WHERE is_active = TRUE
);*/
