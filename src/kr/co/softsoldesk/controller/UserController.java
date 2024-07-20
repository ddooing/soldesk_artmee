package kr.co.softsoldesk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.softsoldesk.Beans.GalleySignFormBean;
import kr.co.softsoldesk.Beans.SubBannerBean;
import kr.co.softsoldesk.Beans.UserBean;
import kr.co.softsoldesk.Service.AdminService;
import kr.co.softsoldesk.Service.GalleryService;
import kr.co.softsoldesk.Service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private AdminService adminService;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	
	Map<String, String> alertMap = new HashMap<>();
	
	@GetMapping("/review_editor")
	public String review_editor() {
		return "user/review_editor";
	}
	//----------------------------------------------
	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, @RequestParam(value = "fail", defaultValue = "false")boolean fail,
						Model model) {
		model.addAttribute("fail", fail);
		
		// 서브 캐러셀
		List<SubBannerBean> AllSubBannerInfo = adminService.IndexSubBannerBeanList();
		model.addAttribute("AllSubBannerInfo", AllSubBannerInfo);
		
		return "user/login";
	}
	
	@PostMapping("/login_pro")
    public String login_pro(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, 
    		BindingResult result, Model model,RedirectAttributes redirectAttributes ) {

		userService.getLoginUserInfo(tempLoginUserBean);
        
        if(loginUserBean.isUserLogin() == true) {
        	
        	
        	return "redirect:/view/index";
        } else {
        	alertMap.put("title", "에러!");
            alertMap.put("text", "로그인 실패하였습니다!");
            alertMap.put("icon", "error");
            redirectAttributes.addFlashAttribute("alertMap", alertMap);
        	return "redirect:/user/login";
        }
        
    }
	
	@GetMapping("/logout")
	public String logout(Model model) {
		loginUserBean.setUser_id(0);
		loginUserBean.setId(null);
		loginUserBean.setPassword(null);
		loginUserBean.setState(0);
		loginUserBean.setUserLogin(false);
		

		return "redirect:/view/index";
	}
	
	@GetMapping("/not_login")
	public String not_login() {
	      return "user/not_login";
	}
	
	@GetMapping("/not_admin")
	public String not_admin() {
		return "user/not_admin";
	}
	

	//일반 회원가입
	@GetMapping("/signup")
	public String signup(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
	    return "user/signup";
	}
	
	
	@PostMapping("/signup_pro")
	   public String signup_pro(@ModelAttribute("joinUserBean") UserBean joinUserBean, 
	                            RedirectAttributes redirectAttributes, 
	                            HttpServletRequest request, HttpServletResponse response, Model model) {

	       // 폼에서 입력받은 인증번호. 폼 데이터로 전송되어야 함
	       String inputVerificationCode = request.getParameter("verification_num");

	       // 쿠키에서 인증번호 읽기
	       Integer storedVerificationCode = null;
	       Cookie[] cookies = request.getCookies();
	       if (cookies != null) {
	           for (Cookie cookie : cookies) {
	               if ("verificationCode".equals(cookie.getName())) {
	                   try {
	                       storedVerificationCode = Integer.parseInt(cookie.getValue());
	                   } catch (NumberFormatException e) {
	                       // 쿠키 값이 정수로 변환될 수 없는 경우
	                   }
	                   break;
	               }
	           }
	       }

	       // 인증번호 검증
	       if (storedVerificationCode != null && String.valueOf(storedVerificationCode).equals(inputVerificationCode)) {
	           // 인증번호 일치, 회원가입 로직 계속 진행
	           userService.addUserInfo(joinUserBean,1);
	           
	           // 성공 메시지 설정
	           alertMap.put("title", "성공!");
	           alertMap.put("text", "회원가입에 성공했습니다!");
	           alertMap.put("icon", "success");
	           redirectAttributes.addFlashAttribute("alertMap", alertMap);
	           
	           // 쿠키 삭제 로직 추가 (인증번호 사용 후 삭제)
	           Cookie deleteCookie = new Cookie("verificationCode", null);
	           deleteCookie.setMaxAge(0);
	           response.addCookie(deleteCookie);
	           
	           return "redirect:login";
	       } else {
	           // 인증번호 불일치, 오류 메시지 설정
	           model.addAttribute("verificationError", "인증번호가 일치하지 않습니다.");
	           return "user/signup";
	       }
	   }
	
	// 전시관 회원가입
	@GetMapping("/signup_gallery")
	public String signup_gallery(@ModelAttribute("joinUserGalleyBean") GalleySignFormBean joinUserGalleyBean,Model model) {

	    return "user/signup_gallery";
	}
	
	/*
	@PostMapping("/signupGallery_pro")
	public String signupGallery_pro(@ModelAttribute("joinUserGalleyBean")GalleySignFormBean joinUserGalleyBean, 
							RedirectAttributes redirectAttributes
							,Model model)
	{
		UserBean joinUserBean = new UserBean();
		joinUserBean.setName(joinUserGalleyBean.getName());
		joinUserBean.setId(joinUserGalleyBean.getId());
		joinUserBean.setPassword(joinUserGalleyBean.getPassword());
		joinUserBean.setTelephone(joinUserGalleyBean.getTelephone());
		joinUserBean.setEmail(joinUserGalleyBean.getEmail());
		
		userService.addUserInfo(joinUserBean,4);
		
		
		//user_table 등록 시키고 해당 user_id를 전시관 user_id와 잇기 위함
		int get_userId = userService.getUserId(joinUserGalleyBean.getId());
		joinUserGalleyBean.setUser_id(get_userId);
		galleryService.addUserGalleyInfo(joinUserGalleyBean);
		
		alertMap.put("title", "성공!");
        alertMap.put("text", "회원가입에 성공했습니다!");
        alertMap.put("icon", "success");
        redirectAttributes.addFlashAttribute("alertMap", alertMap);
    	return "redirect:login";
	}
	*/
	@PostMapping("/signupGallery_pro")
	   public String signupGallery_pro(@ModelAttribute("joinUserGalleyBean") GalleySignFormBean joinUserGalleyBean,
	                                   RedirectAttributes redirectAttributes,
	                                   HttpServletRequest request, HttpServletResponse response,
	                                   Model model) {

	       // 폼에서 입력받은 인증번호
	       String inputVerificationCode = request.getParameter("verification_num");

	       // 쿠키에서 인증번호 읽기
	       Integer storedVerificationCode = null;
	       Cookie[] cookies = request.getCookies();
	       if (cookies != null) {
	           for (Cookie cookie : cookies) {
	               if ("verificationCode".equals(cookie.getName())) {
	                   try {
	                       storedVerificationCode = Integer.parseInt(cookie.getValue());
	                   } catch (NumberFormatException e) {
	                       // Handle the exception, maybe log it?
	                   }
	                   break;
	               }
	           }
	       }

	       // 인증번호 검증
	       if (storedVerificationCode != null && storedVerificationCode.equals(Integer.parseInt(inputVerificationCode))) {
	           // 인증번호 일치, 회원가입 로직 계속 진행

	           UserBean joinUserBean = new UserBean();
	           joinUserBean.setName(joinUserGalleyBean.getName());
	           joinUserBean.setId(joinUserGalleyBean.getId());
	           joinUserBean.setPassword(joinUserGalleyBean.getPassword());
	           joinUserBean.setTelephone(joinUserGalleyBean.getTelephone());
	           joinUserBean.setEmail(joinUserGalleyBean.getEmail());

	           // userService를 사용하여 정보 추가
	           userService.addUserInfo(joinUserBean, 4);

	           // user_table 등록 후, 해당 user_id를 gallery user_id와 연결
	           int getUserId = userService.getUserId(joinUserGalleyBean.getId());
	           joinUserGalleyBean.setUser_id(getUserId);
	           galleryService.addUserGalleyInfo(joinUserGalleyBean);

	           // 성공 메시지 설정
	           alertMap.put("title", "성공!");
	           alertMap.put("text", "갤러리 회원가입에 성공했습니다!");
	           alertMap.put("icon", "success");
	           redirectAttributes.addFlashAttribute("alertMap", alertMap);

	           // 쿠키 삭제 로직 추가 (인증번호 사용 후 삭제)
	           Cookie deleteCookie = new Cookie("verificationCode", null);
	           deleteCookie.setMaxAge(0);
	           response.addCookie(deleteCookie);

	           // 로그인 페이지로 리다이렉션
	           return "redirect:login";
	       } else {
	           // 인증번호 불일치, 오류 메시지 설정
	           model.addAttribute("verificationError", "인증번호가 일치하지 않습니다.");
	           return "user/signup_gallery";
	       }
	   }
	
	//사용자 - 정보 수정
	
	@GetMapping("/InfoChange")
	   public String InfoChange(@ModelAttribute("modifyUserBean")UserBean modifyUserBean,
	                     @RequestParam("user_id")int user_id, Model model) {
	      
	      
	      UserBean IC = userService.getLoginUserAllInfo(user_id);
	      
	      model.addAttribute("IC", IC);

	      
	      return "user/InfoChange";
	      
	   }
	   
	   @PostMapping("/InfoChange_pro")
	   public String InfoChange_pro(@Valid @ModelAttribute("modifyUserBean")UserBean modifyUserBean,
	         BindingResult result,RedirectAttributes redirectAttributes, Model model) {
	      if(result.hasErrors()) {
	      
	    	  
	         return "user/InfoChange";
	      }
	      
	      userService.ModifyUserInfo(modifyUserBean);
	      
	      boolean infoSuccessMsg= true;
	      redirectAttributes.addFlashAttribute("infoSuccessMsg", infoSuccessMsg);
	      return "redirect:/mypage/reservelist";
	      
	   }
	
	// 사용자 - 탈퇴
	@PostMapping("/delete_pro")
	public String delete_pro(@Valid @ModelAttribute("deleteUserBean")UserBean deleteUserBean, BindingResult result,
							@RequestParam("user_id")int user_id, Model model) {
		
		UserBean DU = userService.getLoginUserAllInfo(user_id);
		model.addAttribute("DU", DU);
		
		if(deleteUserBean.getPassword().equals(DU.getPassword())) {
			
			if(deleteUserBean.getPassword().equals(deleteUserBean.getPassword2())) {
				
				userService.DeleteUserInfo(deleteUserBean);
				
				loginUserBean.setUser_id(0);
				loginUserBean.setId(null);
				loginUserBean.setPassword(null);
				loginUserBean.setState(0);
		        
				loginUserBean.setUserLogin(false);
				
				return "redirect:/view/index";
				
			} else {
				return "user/delete_fail_1";		//비밀번호1, 2 불일치
			}
			
			
			
		} else {
			return "user/delete_fail_2";			//비밀번호 부정확
		}
		
		
		
	}
	
	

	
	
	
}
