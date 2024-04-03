package kr.co.softsoldesk.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.softsoldesk.Beans.UserBean;
import kr.co.softsoldesk.Service.EmailService;
import kr.co.softsoldesk.Service.UserService;

@Controller
@RequestMapping("/user")
public class FindController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;

	@GetMapping("/Idfind")
	public String idfind(@ModelAttribute("findUserBean") UserBean findUserBean, Model model) {
		
		return "user/Idfind";
	}
	
	@PostMapping("/Idfind_pro")
    @ResponseBody
    public ResponseEntity<?> idfindPro(@RequestBody Map<String, String> userData, HttpServletResponse response) throws MessagingException {
	        String name1 = userData.get("name1");
	        String email1 = userData.get("email1");

	        int user = userService.findUserByNameAndEmail(name1, email1);
	        
	        if (user == 1) {
	        	int verificationCode = generateVerificationCode();
	        	 // 쿠키 생성
	            Cookie verificationCookie = new Cookie("verificationCode", String.valueOf(verificationCode));
	            verificationCookie.setHttpOnly(true); // 쿠키를 HTTP 전용으로 설정
	            verificationCookie.setMaxAge(5 * 60); // 쿠키 유효 시간 설정 (예: 5분)
	            response.addCookie(verificationCookie); // 응답에 쿠키 추가
	            
	            System.out.println("인증번호  : " + verificationCode);
	            
	            // 이메일 전송 메소드
	            emailService.sendSimpleMessage(email1, "ARTMEE 인증번호", "귀하의 인증번호는 [" + verificationCode + "] 입니다.");
	            /*String imagePath = "http://timtory.synology.me:8082/img/ARTMEE.png"; // 이미지 URL 예시
	            emailService.sendEmailWithImage(email1, "ARTMEE 인증번호", "귀하의 인증번호는 " + verificationCode + " 입니다.", imagePath);*/
	            
	        	System.out.println("인증번호 메일 보내기 완료!");
	            Map<String, String> response1 = new HashMap<>();
	            response1.put("message", "인증 코드가 이메일로 전송되었습니다.");
	            return ResponseEntity.ok(response1);
	        } else {
	        	// 사용자가 존재하지 않는 경우, 오류 응답을 반환합니다.
	            Map<String, String> errorResponse = new HashMap<>();
	            errorResponse.put("message", "사용자가 존재하지 않습니다.");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	        }
	  }

	@PostMapping("/Idfind_go")
	public String idfind_go(@RequestParam("verification_num") int verification_num, @ModelAttribute("findUserBean") UserBean findUserBean,HttpServletRequest request,Model model) {
		
		System.out.println("유저빈에 넣은거" + findUserBean.getVerification_code());
		System.out.println("param으로 받은거  " + verification_num);
		Cookie[] cookies = request.getCookies();
	    Integer storedVerificationCode = null;
	    
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if ("verificationCode".equals(cookie.getName())) {
	                try {
	                    storedVerificationCode = Integer.parseInt(cookie.getValue());
	                } catch (NumberFormatException e) {
	                    // 쿠키 값이 정수로 변환할 수 없는 경우
	                    storedVerificationCode = null;
	                }
	                break;
	            }
	        }
	    }

	    if (storedVerificationCode != null && storedVerificationCode.equals(verification_num)) {
			String id = userService.findUserId(findUserBean.getName1(),findUserBean.getEmail1());
			findUserBean.setId(id);
			model.addAttribute("findUserBean",findUserBean);
			
			return "user/Idfind_complete";
		} else {
			return "redirect:/user/Idfind";
		}
		
	}
	
	@GetMapping("/Pwfind")
	public String pwfind(@ModelAttribute("findUserBean") UserBean findUserBean, Model model) {
		
		
		return "user/Pwfind";
	}
	
	@PostMapping("/Pwfind_pro")
    @ResponseBody
    public ResponseEntity<?> pwfindPro(@RequestBody Map<String, String> userData, HttpServletResponse response) throws MessagingException {
	        String name1 = userData.get("name1");
	        String id1 = userData.get("id1");
	        String email1 = userData.get("email1");

	        int user = userService.findUserByNameAndEmailAndId(name1, email1, id1);
	        
	        if (user == 1) {
	        	int verificationCode = generateVerificationCode();
	        	 // 쿠키 생성
	            Cookie verificationCookie = new Cookie("verificationCode", String.valueOf(verificationCode));
	            verificationCookie.setHttpOnly(true); // 쿠키를 HTTP 전용으로 설정
	            verificationCookie.setMaxAge(5 * 60); // 쿠키 유효 시간 설정 (예: 5분)
	            response.addCookie(verificationCookie); // 응답에 쿠키 추가
	            
	            System.out.println("pw인증번호  : " + verificationCode);
	            
	        	// 이메일 전송 메소드
	            emailService.sendSimpleMessage(email1, "ARTMEE 인증번호", "귀하의 인증번호는 [" + verificationCode + "] 입니다.");
	            
	            /*String imagePath = "http://timtory.synology.me:8082/img/ARTMEE.png"; // 이미지 URL 예시
	            emailService.sendEmailWithImage(email1, "ARTMEE 인증번호", "귀하의 인증번호는 " + verificationCode + " 입니다.", imagePath);*/
	            
	            Map<String, String> response1 = new HashMap<>();
	            response1.put("message", "인증 코드가 이메일로 전송되었습니다.");
	            return ResponseEntity.ok(response1);
	        } else {
	        	// 사용자가 존재하지 않는 경우, 오류 응답을 반환합니다.
	            Map<String, String> errorResponse = new HashMap<>();
	            errorResponse.put("message", "사용자가 존재하지 않습니다.");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	        }
	  }
	
	@PostMapping("/Pwfind_go")
	public String pwfind_go(@RequestParam("verification_num") int verification_num, @ModelAttribute("findUserBean") UserBean findUserBean,HttpServletRequest request,Model model) {
		
		System.out.println("유저빈에 넣은거" + findUserBean.getVerification_code());
		System.out.println("param으로 받은거  " + verification_num);
		Cookie[] cookies = request.getCookies();
	    Integer storedVerificationCode = null;
	    
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if ("verificationCode".equals(cookie.getName())) {
	                try {
	                    storedVerificationCode = Integer.parseInt(cookie.getValue());
	                } catch (NumberFormatException e) {
	                    // 쿠키 값이 정수로 변환할 수 없는 경우
	                    storedVerificationCode = null;
	                }
	                break;
	            }
	        }
	    }

	    if (storedVerificationCode != null && storedVerificationCode.equals(verification_num)) {
			int user_id = userService.finduser_id(findUserBean.getName1(),findUserBean.getEmail1(),findUserBean.getId1());
	    	findUserBean.setUser_id(user_id);
			model.addAttribute("findUserBean",findUserBean);
			
			return "user/Pwfind_change";
		} else {
			return "redirect:/user/Pwfind";
		}
		
	}
	
	@PostMapping("/Pwfind_done")
	public String pwfind_done(@ModelAttribute("findUserBean") UserBean findUserBean) {
		userService.changepw(findUserBean.getNew_pw1(), findUserBean.getUser_id());
		
		return "user/Pwfind_done";
	}
	
	// 6자리 숫자로 이루어진 인증 코드 생성
		private int generateVerificationCode() {
		    Random random = new Random();
		    int code = 100000 + random.nextInt(900000); // 100000 이상 999999 이하의 랜덤한 숫자 생성
		    return code;
		}
}
