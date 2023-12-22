package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import com.lec.spring.domain.Heart;

import java.util.List;
@Mapper
public interface PostRepository {

    int save(Post post);

    Post findByPostId(Long post_id);

    int incViewCnt(Long post_id);

    List<Post> findAll();

    int modify(Post post);

    int chPostStatus(Long id);

    int delete(Post post);



    // 페이징 from 부터 rows 개 만큼
    int countAll();

    // 검색 결과의 전체 개수
    int countSearchResults(@Param("keyword") String keyword, @Param("type") String type);

    // 검색 결과를 페이징하여 가져오기
    List<Post> searchWithPagination(@Param("type") String type, @Param("keyword") String keyword,
                                    @Param("from") int from, @Param("rows") int rows);


    // 마이페이지 - 최신 판매글
    // 메인페이지 - 최신 판매글
    List<Map<String, Object>> findLatestPostsWithUsername();

    // 메인페이지 - 관심 판매글


}
