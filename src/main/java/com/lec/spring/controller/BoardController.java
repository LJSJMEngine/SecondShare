package com.lec.spring.controller;

import com.lec.spring.domain.*;
import com.lec.spring.service.BoardService;
import com.lec.spring.service.HeartService;
import com.lec.spring.util.U;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
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
    private HeartService heartService;


    @GetMapping("/list")
    public void list(@RequestParam(required = false) String type,
                       @RequestParam(required = false) String keyword,
                       Integer page,
                       Model model) {
        boardService.list(page, model, type, keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("type", type);

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
            redirectAttrs.addFlashAttribute("category_id", post.getCategory_id());
            redirectAttrs.addFlashAttribute("contents", post.getContents());
            redirectAttrs.addFlashAttribute("price", post.getPrice());

            List<FieldError> errList = result.getFieldErrors();
            for (FieldError err : errList){
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/board/write";
        }

        model.addAttribute("result", boardService.write(post, files));
        return "board/writeOk";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        model.addAttribute("post" , boardService.detail(id));

        // 좋아요 수 조회
        int likeCount = heartService.countHeartsByPostId(Math.toIntExact(id));
        model.addAttribute("likeCount", likeCount);

        return "board/detail";
    }

    @GetMapping("/modify/{post_id}")
    public String modify(@PathVariable Long post_id, Model model){
        Post post = boardService.selectByPostId(post_id);
        model.addAttribute("post", post);
        return "board/modify";
    }

    @PostMapping("/modify")
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
            redirectAttrs.addFlashAttribute("category_id", post.getCategory_id());
            redirectAttrs.addFlashAttribute("contents", post.getContents());
            redirectAttrs.addFlashAttribute("price", post.getPrice());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList){
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/board/modify/" + post.getPost_id();
        }

        model.addAttribute("result", boardService.modify(post, files, delfile));
        return "board/modifyOk";
    }

    @PostMapping("/chkTrade")
    public String chkTradeOk(Long ch_post_id, Model model){
        int result = boardService.chStatus(ch_post_id);
        model.addAttribute("result", result);
        return "/board/chkTradeOk";
    }

    @PostMapping("/delete")
    public String deleteOk(Long post_id, Model model) {
        int result = boardService.delStatus(post_id);
        model.addAttribute("result", result);
        return "board/deleteOk";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        System.out.println("initBinder() 호출");
        binder.setValidator(new PostValidator());
    }

    @PostMapping("/pageRows")
    public String pageRows(Integer page, Integer pageRows){
        U.getSession().setAttribute("pageRows", pageRows);
        return "redirect:/board/list?page=" + page;
    }

    // 관리자 페이지 게시글


}
