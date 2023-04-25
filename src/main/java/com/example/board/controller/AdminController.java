package com.example.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.board.service.UsersService;
import com.example.board.util.paging.Criteria;
import com.example.board.util.paging.PageMaker;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {
	
	private final UsersService usersService;
	
	@GetMapping("admin/main")
	public String adminForm() {
        return "admin/main";
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
