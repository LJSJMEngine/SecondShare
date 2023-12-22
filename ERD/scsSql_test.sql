SELECT * FROM s1_user ORDER BY id DESC;
SELECT * FROM s1_attachment ORDER BY id DESC;
SELECT * FROM s1_post ORDER BY post_id DESC;
SELECT * FROM s1_comment ORDER BY id DESC;
SELECT * FROM s1_review ORDER BY id DESC;
SELECT * FROM s1_authority ORDER BY id DESC;
SELECT * FROM s1_notice ORDER BY id DESC;
SELECT * FROM s1_chatroom  ORDER BY room_id DESC;
SELECT * FROM s1_chatMessage  ORDER BY room_id DESC;
SELECT * FROM s1_category  ORDER BY id DESC;

SELECT
	id,
	username,
	name,
	phoneNM,
	email
FROM 
	s1_user
WHERE
	1 = 1
ORDER BY id DESC;


SELECT * FROM s1_user;
SELECT * FROM s1_post;
SELECT * FROM s1_attachment;
SELECT * FROM s1_heart;
SELECT * FROM s1_category;
SELECT * FROM s1_comment;

SELECT * FROM s1_post WHERE category_id = 1;

SELECT
            c.id AS "c_id",
            c.content AS "c_content",
            c.regdate AS "c_regdate",
            c.post_id AS "c_post_id",
            u.id AS "u_id",
            u.username AS "u_username",
            u.name AS "u_name",
            u.email AS "u_email",
            u.registDate AS "u_registDate"
        FROM s1_comment c
        LEFT JOIN s1_user u ON c.user_id = u.id
        LEFT JOIN s1_post p ON c.post_id = p.post_id
        WHERE c.post_id = 1
        ORDER BY c.id DESC
        ;