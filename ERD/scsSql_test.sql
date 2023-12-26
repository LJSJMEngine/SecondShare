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



SELECT * FROM s1_user_authority;



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
        JOIN s1_user u ON p.user_id = u.id
        WHERE u.id = #{userid}
		AND p.status = 1


        LEFT JOIN  s1_user u
        ON p.user_id = u.id
        LEFT JOIN  s1_category c
        ON p.category_id = c.id
        LEFT JOIN  s1_attachment a
        ON a.post_id =  p.post_id

        SELECT
        p.post_id "p_post_id"
        , p.user_id "p_user_id"
        , p.subject "p_subject"
        , p.contents "p_contents"
        , p.price "p_price"
        , p.viewCnt "p_viewCnt"
        , p.regDate "p_regDate"
        , p.status "p_status"
        , p.category_id "p_category_id"
        , u.id "u_id"
        , u.username "u_username"
        , u.name "u_name"
        , u.email "u_email"
        , COALESCE(a.sourcename, '이미지없음') "a_sourcename"
        , COALESCE(a.filename, '이미지없음') "a_filename"
        , COALESCE(a.id, 0) "a_id"
        , c.id "c_id"
        , c.name "c_name"
        FROM s1_post p
        LEFT JOIN  s1_user u
        ON p.user_id = u.id
        LEFT JOIN  s1_category c
        ON p.category_id = c.id
        LEFT JOIN  s1_attachment a
        ON a.post_id =  p.post_id
        ORDER BY p.post_id DESC
        ;



SELECT
        p.post_id "p_post_id"
        , p.subject "p_subject"
        , p.contents "p_contents"
        , p.price "p_price"
        , p.viewCnt "p_viewCnt"
        , p.regDate "p_regDate"
        , p.status "p_status"
        , u.id "u_id"
        , u.username "u_username"
        , u.name "u_name"
        , u.email "u_email"
        FROM s1_post p, s1_user u
        WHERE p.user_id = u.id
        AND post_id = 8
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
