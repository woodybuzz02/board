package com.example.board.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.board.config.auth.PrincipalDetails;
import com.example.board.domain.Posts;
import com.example.board.dto.UploadPostDto;
import com.example.board.service.PostsService;
import com.example.board.util.paging.Criteria;
import com.example.board.util.paging.PageMaker;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {
	
	private final PostsService postsService;
	
    @GetMapping({ "/", "post/main" })
    public String mainPage(Model model, Criteria cri) {
    	
    	int total = postsService.countAllPost().intValue();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(total);
		pageMaker.setPostNum(cri, total);
		
		List<Map<String,Object>> list = postsService.selectPostList(cri);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker); 	
    	
        return "post/main";
    }	
    
    @GetMapping("post/upload")
    public String uploadPage() {
        return "post/upload";
    }
    
    @PostMapping("/post")
    public String postUpload(@Valid UploadPostDto uploadPostDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails) {
    	Posts post = uploadPostDto.toEntity(principalDetails.getUser());
        postsService.uploadPost(post);
        return "redirect:/post/main";
    }
    
    @GetMapping("/post/{id}")
    public String postDetail(@PathVariable int id, Model model) {
    	model.addAttribute("post", postsService.postDetail(id));
    	return "post/detail";
    }
    
    @GetMapping("/post/{postid}/modify")
    public String postmodify(@PathVariable int postid, Model model) {
        Posts post = postsService.postDetail(postid);
        model.addAttribute("post", post);
        return "post/modify";
    }

}
