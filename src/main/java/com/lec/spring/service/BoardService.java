package com.lec.spring.service;

    import com.lec.spring.domain.Post;
    import org.springframework.security.core.parameters.P;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface BoardService {

    List<Post> list();

    List<Post> list(Integer page, Model model, String keyword , String type);

    int write(Post post, Map<String, MultipartFile> files);

    Post detail(Long id);

    Post selectByPostId(Long post_id);

    int modify(Post post, Map<String, MultipartFile> files, Long [] delfile);

    int chStatus(Long id);

    int delStatus(Long id);

    int deleteByPostId(Long post_id);


}