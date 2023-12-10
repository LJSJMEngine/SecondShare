SELECT * FROM s1_user ORDER BY id DESC;
SELECT * FROM s1_attachment ORDER BY id DESC;
SELECT * FROM s1_post ORDER BY post_id DESC;
SELECT * FROM s1_comment ORDER BY id DESC;
SELECT * FROM s1_authority ORDER BY id DESC;
SELECT * FROM s1_notice ORDER BY id DESC;

SELECT * FROM s1_chatroom  ORDER BY room_id DESC;

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

