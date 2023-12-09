package com.lec.spring.service;

import com.lec.spring.domain.Post;
import org.springframework.ui.Model;

import java.util.List;

public interface BoardService {
//    List<Post> list();

    List<Post> list(Model model);

}
