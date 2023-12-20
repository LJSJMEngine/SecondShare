package com.lec.spring.controller;

import com.lec.spring.domain.Category;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.service.BoardService;
import com.lec.spring.service.CategoryService;
import com.lec.spring.service.PostService;
import com.lec.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    private UserService userService;
    private BoardService boardService;
    private PostService postService;
    private CategoryService categoryService;

    @Autowired
    public BoardController(UserService userService, BoardService boardService, PostService postService, CategoryService categoryService){
        this.userService = userService;
        this.boardService = boardService;
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String type,
                       @RequestParam(required = false) String keyword,
                       Model model) {
        List<Post> list;

        if ("subject".equals(type)) {
            list = boardService.search(keyword);
        } else if ("category".equals(type)) {
            // Assuming category names are unique
            Category category = categoryService.getCategoryByName(keyword);
            if (category != null) {
                list = boardService.searchByCategory(category.getName());
            } else {
                list = boardService.list();
            }
        } else {
            list = boardService.list();
        }

        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("list", list);
        model.addAttribute("categories", categories);
        return "board/list";
    }


    @GetMapping("/write")
    public void write(){}

    @PostMapping("/write")
    public String writeOk(
            @RequestParam Map<String, MultipartFile> files,
            @Valid Post post
            , BindingResult result
            , Model model
            , RedirectAttributes redirectAttrs
    ) {

        if (result.hasErrors()){
            redirectAttrs.addFlashAttribute("user", post.getUser());
            redirectAttrs.addFlashAttribute("subject", post.getSubject());
            redirectAttrs.addFlashAttribute("content", post.getContents());

            List<FieldError> errList = result.getFieldErrors();
            for (FieldError err : errList){
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/board/write";
        }

        model.addAttribute("result", boardService.write(post, files));
        return "/writeOk";
    }

//    @GetMapping("/detail/{id}")
//    public String detail(@PathVariable Long id, Model model){
//        model.addAttribute("post" ,boardService.detail(id));
//        return "board/detail";
//    }
    @RequestMapping("/detail")
    public String detail(Model model){
        Long currenUserId = 1L;
        Long currenPostId = 1L;

        User userProfile = userService.getUserById(currenUserId);


        model.addAttribute("post" ,boardService.detail(currenPostId));
        return "board/detail";
    }
    @GetMapping("modify/{id}")
    public String  modify(@PathVariable Long id, Model model){
        Post post = boardService.selectByPostId(id);
        model.addAttribute("post", post);
        return "board/modify";
    }
    @PostMapping("modify")
    public String modifyOk(
            @RequestParam Map<String, MultipartFile> files
            , Long [] delfile
            , @Valid Post post
            , BindingResult result
            , Model model
            , RedirectAttributes redirectAttrs
    ){

        if (result.hasErrors()){

            redirectAttrs.addFlashAttribute("subject", post.getSubject());
            redirectAttrs.addFlashAttribute("content", post.getContents());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList){
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/board/update/" + post.getPost_id();
        }

        model.addAttribute("result", boardService.modify(post, files, delfile));
        return "/modifyOk";
    }

    @GetMapping("/review")
    public void review(){}

    @PostMapping("/review")
    public String reviewOk(
            @RequestParam("post_id") Long post_id,
            @RequestParam("user_id") Long user_id,
            String content
            ){
        return "/board/review";
    }

    @PostMapping("/delete")
    public String deleteOk(Long post_id, Model model){
        model.addAttribute("result", boardService.deleteByPostId(post_id));
        return "board/deleteOk";
    }

}
