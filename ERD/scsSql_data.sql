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
('USER2', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원2', '01055556666', 'user2@gmail.com', NOW(), 0),
('USER20', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원20', '01087185552', 'user20@gmail.com', NOW(), 0),
('USER21', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원21', '01083561074', 'user21@gmail.com', NOW(), 1),
('USER22', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원22', '01035164649', 'user22@gmail.com', NOW(), 0),
('USER23', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원23', '01026354637', 'user23@gmail.com', NOW(), 1),
('USER24', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원24', '01057975998', 'user24@gmail.com', NOW(), 0),
('USER25', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원25', '01089582337', 'user25@gmail.com', NOW(), 0),
('USER26', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원26', '01032054314', 'user26@gmail.com', NOW(), 1),
('USER27', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원27', '01065859608', 'user27@gmail.com', NOW(), 1),
('USER28', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원28', '01020989876', 'user28@gmail.com', NOW(), 1),
('USER29', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원29', '01082814122', 'user29@gmail.com', NOW(), 0),
('USER30', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원30', '01075301568', 'user30@gmail.com', NOW(), 0);
('swxujz', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원31', '01074962201', 'swxujz@gmail.com', NOW(), 0),
('pdeywu', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원32', '01056079672', 'pdeywu@gmail.com', NOW(), 0),
('rnzqfu', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원33', '01041992476', 'rnzqfu@gmail.com', NOW(), 0),
('cncnxc', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원34', '01032855153', 'cncnxc@gmail.com', NOW(), 0),
('plwblz', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원35', '01020236826', 'plwblz@gmail.com', NOW(), 0),
('tghcvm', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원36', '01042884960', 'tghcvm@gmail.com', NOW(), 0),
('wklkhi', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원37', '01042409638', 'wklkhi@gmail.com', NOW(), 0),
('fxbohg', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원38', '01081368238', 'fxbohg@gmail.com', NOW(), 1),
('vxwzes', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원39', '01062763611', 'vxwzes@gmail.com', NOW(), 0),
('uzpqfd', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원40', '01021305890', 'uzpqfd@gmail.com', NOW(), 0),
('agnmln', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원41', '01014716501', 'agnmln@gmail.com', NOW(), 1),
('xdiuux', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원42', '01031257048', 'xdiuux@gmail.com', NOW(), 0),
('peziqt', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '회원43', '01055159434', 'peziqt@gmail.com', NOW(), 0)
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
(3, '제목5', '내용5', 20000, 1, NOW(), 3),
(3, '제목8', '내용8', 20000, 1, NOW(), 2),
(1, '제목9', '내용9', 20000, 1, NOW(), 2),
(2, '제목10', '내용10', 15000, 0, NOW(), 1),
(1, '제목11', '내용11', 20000, 1, NOW(), 1),
(1, '제목12', '내용12', 15000, 0, NOW(), 5),
(1, '제목13', '내용13', 15000, 1, NOW(), 3),
(3, '제목14', '내용14', 20000, 0, NOW(), 3),
(3, '제목15', '내용15', 15000, 0, NOW(), 2),
(3, '제목16', '내용16', 15000, 0, NOW(), 5),
(3, '제목17', '내용17', 20000, 0, NOW(), 4),
(1, '제목18', '내용18', 15000, 1, NOW(), 1),
(3, '제목19', '내용19', 15000, 0, NOW(), 2),
(1, '제목20', '내용20', 20000, 0, NOW(), 1)
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
