package com.example.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.config.auth.PrincipalDetails;
import com.example.board.dto.CMRespDto;
import com.example.board.dto.SwiftCodeDto;
import com.example.board.service.SlangFilteringService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SlangFilteringController {
	
	private final SlangFilteringService slangFilteringService;

    @PostMapping("/slang-filter")
    public String addSlang(@Valid SwiftCodeDto swiftCodeDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails) {
    	slangFilteringService.addSlang(swiftCodeDto, principalDetails.getUser().getUsername());
        return "redirect:admin/slang";
    }
    
    @DeleteMapping("/slang-filter")
    @ResponseBody
	public ResponseEntity<?> deleteSlang(@RequestBody List<SwiftCodeDto> swiftCodeDtos){
		
		for(int i=0; i<swiftCodeDtos.size(); i++) {
			slangFilteringService.deleteSlang(swiftCodeDtos.get(i));
		}
		return new ResponseEntity<>(new CMRespDto<>(1, "욕 삭제 성공!", null), HttpStatus.OK);
	}

}
