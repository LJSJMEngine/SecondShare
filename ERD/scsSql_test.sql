SELECT * FROM s1_user ORDER BY id ASC;
SELECT * FROM s1_attachment ORDER BY id DESC;
SELECT * FROM s1_post ORDER BY post_id ASC;
SELECT * FROM s1_comment ORDER BY id DESC;
SELECT * FROM s1_review ORDER BY id DESC;
SELECT * FROM s1_authority ORDER BY id DESC;
SELECT * FROM s1_notice ORDER BY id DESC;
SELECT * FROM s1_chatroom  ORDER BY room_id DESC;
SELECT * FROM s1_chatMessage  ORDER BY room_id DESC;
SELECT * FROM s1_heart ORDER BY id DESC;

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



SELECT * FROM s1_user_authority;


   SELECT COUNT(p.status)
        FROM s1_post p
        JOIN s1_user u ON p.user_id = u.id
        WHERE u.id = #{userid}
		AND p.status = 1

