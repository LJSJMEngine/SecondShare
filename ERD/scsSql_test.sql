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
SELECT * FROM s1_review;
SELECT * FROM s1_notice;
SELECT * FROM s1_category;
SELECT * FROM s1_user_authority;

SELECT * FROM s1_comment;

SELECT * FROM s1_post WHERE category_id = 1;

SELECT
        p.post_id AS p_post_id,
        ANY_VALUE(p.user_id) AS p_user_id,
        ANY_VALUE(p.subject) AS p_subject,
        ANY_VALUE(p.contents) AS p_contents,
        ANY_VALUE(p.price) AS p_price,
        ANY_VALUE(p.viewCnt) AS p_viewCnt,
        ANY_VALUE(p.regDate) AS p_regDate,
        ANY_VALUE(p.status) AS p_status,
        ANY_VALUE(p.category_id) AS p_category_id,
        ANY_VALUE(u.id) AS u_id,
        ANY_VALUE(u.username) AS u_username,
        ANY_VALUE(u.name) AS u_name,
        ANY_VALUE(u.email) AS u_email,
        COALESCE(ANY_VALUE(a.sourcename), '이미지없음') AS a_sourcename,
        ANY_VALUE(c.id) AS c_id,
        ANY_VALUE(c.name) AS c_name
        FROM s1_post p
        LEFT JOIN s1_user u ON p.user_id = u.id
        LEFT JOIN s1_category c ON p.category_id = c.id
        LEFT JOIN s1_attachment a ON a.post_id = p.post_id
        WHERE p.post_id = 2
        AND p.status < 2
        GROUP BY p.post_id;
        
       SELECT *
        FROM s1_user u
        JOIN s1_user_authority a ON u.id = a.user_id
        WHERE a.authority_id != 1
        ORDER BY u.registDate DESC;
        
       SELECT
        id,
        user_id,
        status,
        status_name,
        subject,
        contents,
        readChk
        FROM
        s1_notice
        WHERE
        1 = 1
        GROUP BY id
        ORDER BY id DESC;