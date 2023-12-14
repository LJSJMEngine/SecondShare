SELECT * FROM s1_user ORDER BY id DESC;
SELECT * FROM s1_attachment ORDER BY id DESC;
SELECT * FROM s1_post ORDER BY post_id DESC;
SELECT * FROM s1_comment ORDER BY id DESC;
SELECT * FROM s1_review ORDER BY id DESC;
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

SELECT * FROM s1_post;

SELECT * FROM s1

        SELECT
        p.post_id "p_post_id"
        , p.user_id "p_user_id"
        , p.subject "p_subject"
        , p.contents "p_contents"
        , p.price "p_price"
        , p.viewCnt "p_viewCnt"
        , p.regDate "p_regDate"
        , p.status "p_status"
        , p.goodCnt "p_goodCnt"
        , p.tags "p_tags"
        , u.id "u_id"
        , u.username "u_username"
        , u.location "u_location"
        , c.id "c_id"
        , c.name "c_name"
        FROM s1_post p, s1_user u, s1_category c 
        WHERE   p.user_id = u.id
        ORDER BY p.post_id DESC;


            
            
            
            
            
            