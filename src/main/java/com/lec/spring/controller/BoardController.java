package com.lec.spring.controller;

import com.lec.spring.domain.Category;
import com.lec.spring.domain.Post;
import com.lec.spring.service.BoardService;
import com.lec.spring.service.CategoryService;
import com.lec.spring.util.U;
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
    @Autowired
    private BoardService boardService;


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String type,
                       @RequestParam(required = false) String keyword,
                       Integer page,
                       Model model) {



        if ("subject".equals(type)) {
            boardService.search(keyword);
            boardService.list(page, model,type,keyword);
        }
        else if ("id".equals(type)) {
            boardService.search(keyword);
            boardService.list(page, model,type,keyword);

        } else {

            boardService.list(page, model,type,keyword);
        }

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

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        model.addAttribute("post" ,boardService.detail(id));
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

    @PostMapping("/delete")
    public String deleteOk(Long post_id, Model model){
        model.addAttribute("result", boardService.deleteByPostId(post_id));
        return "board/deleteOk";
    }

//    @GetMapping("review")

    @PostMapping("/pageRows")
    public String pageRows(Integer page, Integer pageRows){
        U.getSession().setAttribute("pageRows", pageRows);
        return "redirect:/board/list?page=" + page;
    }


}
