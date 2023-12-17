package com.lec.spring.service;

    import com.lec.spring.domain.Post;
    import org.springframework.security.core.parameters.P;
    import org.springframework.ui.Model;
    import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface BoardService {

    List<Post> list();

        List<Post> list(Integer page, Model model, String keyword , String type);



        List<Post> search(String keyword);

    int write(Post post, Map<String, MultipartFile> files);

    Post detail(Long id);

    Post selectByPostId(Long id);

    int modify(Post post, Map<String, MultipartFile> files, Long [] delfile);

    int deleteByPostId(Long id);

}