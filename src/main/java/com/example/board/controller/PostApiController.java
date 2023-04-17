package com.example.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.board.service.PostsService;
import com.example.board.util.paging.Criteria;
import com.example.board.util.paging.PageMaker;

@RestController
public class PostApiController {

//	@RequestMapping(value="/post/postList")
//	public ModelAndView postsList(Criteria cri) throws Exception {
//		
//		private final PostsService postsService;
//		
//		ModelAndView mav = new ModelAndView("/post/postList");
//		
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCri(cri);
//		pageMaker.setTotalCount(100);
//		
//	    List<Map<String,Object>> list = postsService.selectBoardList(cri);
//	    mav.addObject("list", list);
//	    mav.addObject("pageMaker", pageMaker);
//		
//		return mav;
//		
//	}
	
}
