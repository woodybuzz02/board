package com.example.board.handler.AOP;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.board.domain.Posts;
import com.example.board.domain.Replys;
import com.example.board.service.MailService;
import com.example.board.service.SlangFilteringService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BatchScheduler {
	
	private final SlangFilteringService slangFilteringService;
	private final MailService mailService;
	
	@Scheduled(cron = "0 0/10 * * * *") // 10분마다!
	public void filteringSlangSchedule() {
		
		List<Posts> SlangPostList = slangFilteringService.filteringAllPost();
		
		for(Posts post : SlangPostList) {
			
			String email = post.getUser().getEmail();
			String title = post.getTitle();
			
			try {
				mailService.sendSlangFilterMessage(email, title, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}
		
		List<Replys> SlangReplyList = slangFilteringService.filteringAllReply();
		
		for(Replys reply : SlangReplyList) {
			
			String email = reply.getUser().getEmail();
			String content = reply.getContent();
			
			try {
				mailService.sendSlangFilterMessage(email, null, content);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}
		
	}

}
