package com.example.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.board.domain.SwiftCode;
import com.example.board.service.SlangFilteringService;
import com.example.board.service.UsersService;
import com.example.board.util.paging.Criteria;
import com.example.board.util.paging.PageMaker;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {
	
	private final UsersService usersService;
	private final SlangFilteringService slangFilteringService;
	
	@GetMapping("admin/slang")
	public String adminSlangManagementForm(Model model, Criteria cri) {
		
		cri.setPerPageNum(10);
		
		List<SwiftCode> slangList = slangFilteringService.findAllSlang(cri);
		
		int total = slangFilteringService.countAllSlang();
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(total);
		pageMaker.setPostNum(cri, total);
		
		model.addAttribute("list", slangList);
		model.addAttribute("pageMaker", pageMaker);
		
        return "admin/slang";
    }
	
	@GetMapping("admin/user")
	public String adminUserManagementPage(Model model, Criteria cri) {
		
		int total = usersService.countAllUser().intValue();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(total);
		pageMaker.setPostNum(cri, total);
		
		List<Map<String,Object>> list = usersService.selectUserList(cri);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
        return "admin/user";
    }
	
}
