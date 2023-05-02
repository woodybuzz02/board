package com.example.board.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService{
	
	private final JavaMailSender javaMailSender;

    private String ePw; // 인증번호

    public MimeMessage createMessage(String to)throws MessagingException, UnsupportedEncodingException {
    	
    	ePw = createKey();
    	
        log.info("보내는 대상 : "+ to);
        log.info("인증 번호 : " + ePw);
        
        MimeMessage  message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to); // to 보내는 대상
        message.setSubject("yw게시판 회원가입 인증 코드: "); //메일 제목

        String msg="";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 회원가입 화면에서 입력해주세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += ePw; // 메일에 인증번호 넣기
        msg += "</td></tr></tbody></table></div>";

        message.setText(msg, "utf-8", "html"); //내용, charset타입, subtype
        message.setFrom("ajdanddl6321@naver.com"); //보내는 사람의 메일 주소, 보내는 사람 이름

        return message;
    }

    // 인증코드 만들기
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤
 
            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        
        return key.toString();
    }

    /*
        메일 발송
        sendSimpleMessage의 매개변수로 들어온 to는 인증번호를 받을 메일주소
        MimeMessage 객체 안에 내가 전송할 메일의 내용을 담아준다.
        bean으로 등록해둔 javaMailSender 객체를 사용하여 이메일 send
     */
    public String sendSimpleMessage(String to)throws Exception {
        MimeMessage message = createMessage(to);
        try{
            javaMailSender.send(message); // 메일 발송
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw; // 메일로 보냈던 인증 코드를 서버로 리턴
    }
    
    // 욕설 필터 게시글 메일
    
    public MimeMessage createSlangFilterMessage(String to, String title, String replyContent)throws MessagingException, UnsupportedEncodingException {
    	
        log.info("보내는 대상 : "+ to);
        log.info("블라인드처리된 게시글의 제목 : "+ title);
        log.info("블라인드처리된 댓글 내용 : "+ replyContent);
        
        MimeMessage  message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to); // to 보내는 대상
        
        if(title != null) {
        	
        	 message.setSubject("게시글 블라인드 처리 안내"); //메일 제목

             String msg="";
             msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">게시글 블라인드 처리</h1>";
             msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">게시글이 비속어 사용으로 인해 블라인드 처리되었습니다. 많은 회원님들께 불편함을 주는 내용의 글이나 각 게시판 성격에 맞지 않는 글은 등록시 주의해 주시기 바랍니다.</p>";
             msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
             msg += "해당 게시글 제목 : "+title;
             msg += "</td></tr></tbody></table></div>";

             message.setText(msg, "utf-8", "html"); //내용, charset타입, subtype
             message.setFrom("ajdanddl6321@naver.com"); //보내는 사람의 메일 주소, 보내는 사람 이름

             return message;
        	
        }else {
        	
        	message.setSubject("댓글 블라인드 처리 안내"); //메일 제목

            String msg="";
            msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">댓글 블라인드 처리</h1>";
            msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">댓글이 비속어 사용으로 인해 블라인드 처리되었습니다. 많은 회원님들께 불편함을 주는 내용의 댓글이나 각 게시판 성격에 맞지 않는 댓글은 등록시 주의해 주시기 바랍니다.</p>";
            msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
            msg += "해당 댓글 : "+replyContent;
            msg += "</td></tr></tbody></table></div>";

            message.setText(msg, "utf-8", "html"); //내용, charset타입, subtype
            message.setFrom("ajdanddl6321@naver.com"); //보내는 사람의 메일 주소, 보내는 사람 이름

            return message;
        	
        }
        
       
    }
    
    public String sendSlangFilterMessage(String to, String title, String replyContent)throws Exception {
        MimeMessage message = createSlangFilterMessage(to, title, replyContent);
        try{
            javaMailSender.send(message); // 메일 발송
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw; // 메일로 보냈던 인증 코드를 서버로 리턴
    }
    
    
    
}
