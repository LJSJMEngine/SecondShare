package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostRepository {

    int save(Post post);

    Post findByPostId(Long id);

    int incViewCnt(Long id);

    List<Post> findAll();

    int update(Post post);

    int delete(Post post);

    List<Post> search(@Param("keyword") String keyword);

    List<Post> searchByCategory(@Param("keyword") String keyword);

    int countAll();

    // 마이페이지 - 최신 판매글
    List<Map<String, Object>> findLatestPostsWithUsername();

    // 마이페이지 - 관심 판매글 (사용자 아이디로 가져오기)
    List<Long> findLikedPostIdsByUsername(@Param("username") String username);

    // 마이페이지 - 관심 판매글 (판매글 아이디로 가져오기)
    List<Map<String, Object>> findPostsByIds(List<Long> postIds);

    // 마이페이지 - 관심 판매글
    Long findUserIdByUsername(@Param("username") String username);


}
