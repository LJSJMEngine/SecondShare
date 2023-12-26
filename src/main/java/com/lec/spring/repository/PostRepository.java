package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import com.lec.spring.domain.Heart;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.util.List;
@Mapper
public interface PostRepository {

    int save(Post post);

    Post findByPostId(Long id);

    int incViewCnt(Long id);

    List<Post> findAll();

    int modify(Post post);

    int chPostStatus(Long id);

    int delStatus(Long id);

    int delete(Post post);

    // 마이페이지 - 내 판매글 삭제하기
    void updatePostStatus(@Param("postIds") List<Long> postIds);

    // 페이징 from 부터 rows 개 만큼
    int countAll();

    // 검색 결과의 전체 개수
    int countSearchResults(@Param("keyword") String keyword, @Param("type") String type);

    // 검색 결과를 페이징하여 가져오기
    List<Post> searchWithPagination(@Param("type") String type, @Param("keyword") String keyword,
                                    @Param("from") int from, @Param("rows") int rows);


    // 메인페이지 - 최신 판매글
    List<Map<String, Object>> findLatestPostsWithUsernameAndSampleImg();
    List<String> getImagePathsByPostId(Integer postId);

    // 메인페이지 - 관심 판매글
    List<Map<String, Object>> findLikedPostsByUserId(@Param("userId") Long userId);


    //관리자 페이지 게시글
    // 검색 결과의 전체 개수
    int admincountSearchResults(@Param("keyword") String keyword, @Param("type") String type);

    // 검색 결과를 페이징하여 가져오기
    List<Post> adminsearchWithPagination(@Param("type") String type, @Param("keyword") String keyword,
                                    @Param("from") int from, @Param("rows") int rows);

    // 게시글 삭제 하기
    void adminupdatePostStatus(@Param("postIds") List<Long> postIds);

    // 게시글 변경 하기
    void adminChangeStatus(@Param("selectedPostIds") List<Long> selectedPostIds, @Param("selectedStatus") Integer selectedStatus);

}
