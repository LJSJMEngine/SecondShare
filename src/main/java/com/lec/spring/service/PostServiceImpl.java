package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.repository.PostRepository;
import com.lec.spring.util.U;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;
    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;

    private final PostRepository postRepository;



    @Autowired
    public PostServiceImpl(SqlSession sqlss){
        this.postRepository = sqlss.getMapper(PostRepository.class);
        System.out.println("[SERVICEIMPL] PostServiceImpl Init");
    }


    // 마이페이지 - 내 판매글 삭제하기
    @Override
    public Post getPostByPostId(Long post_id) {
        return postRepository.findByPostId(post_id);
    }

    @Override
    @Transactional
    public void deleteMyPosts(List<Long> selectedPostIds) {
        postRepository.updatePostStatus(selectedPostIds);
    }

    // 어드민 용
    @Override
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @Override
    public List<Post> getLatestPosts(){
        return postRepository.findLatest();
    }

    // 마이페이지 - 최신 판매글
    @Override
    public List<Map<String, Object>> getLatestPostsWithUsernameAndImgPath() {
        List<Map<String, Object>> latestPosts = postRepository.findLatestPostsWithUsernameAndSampleImg();

        // 중복된 post_id를 제거
        latestPosts = latestPosts.stream()
                .collect(Collectors.toMap(post -> post.get("post_id"), post -> post, (existing, replacement) -> existing))
                .values().stream()
                .collect(Collectors.toList());

        for (Map<String, Object> post : latestPosts) {
            Integer postId = (Integer) post.get("post_id");
             String filename = (String) post.get("filename");

            // 이미지 경로 생성 및 추가
            String imgPath = getFirstImgPathByPostId(postId);
            post.put("img_path", imgPath);

        }

        return latestPosts;
    }

    private String getFirstImgPathByPostId(Integer postId) {
        List<String> imagePaths = getImagePathsByPostId(postId);
        if (!imagePaths.isEmpty()) {
            return imagePaths.get(0);
        }
        return "/sample.jpg";
    }

    public List<String> getImagePathsByPostId(Integer postId) {
        return postRepository.getImagePathsByPostId(postId);
    }

    // 마이페이지 - 관심 판매글
    @Override
    public List<Map<String, Object>> findLikedPostsByUserId(Long userId) {
        List<Map<String, Object>> likedPosts = postRepository.findLikedPostsByUserId(userId);

        // 중복된 post_id를 제거
        likedPosts = likedPosts.stream()
                .collect(Collectors.toMap(post -> post.get("post_id"), post -> post, (existing, replacement) -> existing))
                .values().stream()
                .collect(Collectors.toList());

        for (Map<String, Object> post : likedPosts) {
            Integer postId = (Integer) post.get("post_id");
            String attachmentFilename = (String) post.get("attachment_filename");

            // 이미지 경로 생성 및 추가
            String imgPath = getFirstImgPathByPostId(postId);
            post.put("img_path", imgPath);
        }

        return likedPosts;
    }

    @Override
    public List<Post> findPost(Integer page, Model model, String type, String keyword) {
        if (page == null) page = 1;   // 디폴트 1 page
        if (page < 1) page = 1;


        // paging
        HttpSession session = U.getSession();
        Integer writePages = (Integer) session.getAttribute("writePages");
        if (writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer) session.getAttribute("pageRows");
        if (pageRows == null) pageRows = PAGE_ROWS; // 만약 session 에 없으면 기본값으로 동작
        session.setAttribute("page", page);  // 현재 페이지 번호 -> session 에 저장

        long cnt;
        int totalPage;
        int startPage;
        int endPage;
        pageRows = 5;
        List<Post> adminlist;

        // 검색 결과 목록 조회
        cnt = postRepository.admincountSearchResults(keyword,type);
        adminlist = postRepository.adminsearchWithPagination(type, keyword, (page - 1) * pageRows, pageRows);


        totalPage = (int) Math.ceil(cnt / (double) pageRows);
        startPage = (((page - 1) / writePages) * writePages) + 1;
        endPage = startPage + writePages - 1;


        if (endPage >= totalPage) endPage = totalPage;

        model.addAttribute("adminlist", adminlist);
        model.addAttribute("cnt", cnt);  // 전체 글 개수
        model.addAttribute("page", page); // 현재 페이지
        model.addAttribute("totalPage", totalPage);  // 총 '페이지' 수
        model.addAttribute("pageRows", pageRows);  // 한 '페이지' 에 표시할 글 개수

        model.addAttribute("url", U.getRequest().getRequestURI());  // 목록 url
        model.addAttribute("writePages", writePages); // [페이징] 에 표시할 숫자 개수
        model.addAttribute("startPage", startPage);  // [페이징] 에 표시할 시작 페이지
        model.addAttribute("endPage", endPage);   // [페이징] 에 표시할 마지막 페이지


        return adminlist;

    }

    // 관리자페이지 게시글 삭제
    @Override
    @Transactional
    public void deletePosts(List<Long> selectedPostIds) {
        postRepository.adminupdatePostStatus(selectedPostIds);
    }

    @Override
    @Transactional
    public void changeStatus(List<Long> selectedPostIds, Integer selectedStatus) {
        postRepository.adminChangeStatus(selectedPostIds, selectedStatus);
    }



}