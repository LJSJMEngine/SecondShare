package com.lec.spring.service;

import java.util.List;
import java.util.Map;

public interface PostService {

    // 메인페이지 - 최신 판매글
    List<Map<String, Object>> getLatestPostsWithUsernameAndImgPath();

    // 메인페이지 - 관심 판매글
}
