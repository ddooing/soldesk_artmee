package kr.co.softsoldesk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
	public String signup_pro(@ModelAttribute("joinUserBean")UserBean joinUserBean, 
							RedirectAttributes redirectAttributes
							,Model model)
	{
		userService.addUserInfo(joinUserBean,1);
		
		alertMap.put("title", "성공!");
        alertMap.put("text", "회원가입에 성공했습니다!");
        alertMap.put("icon", "success");
        redirectAttributes.addFlashAttribute("alertMap", alertMap);
    	return "redirect:login";
	}
	
	// 전시관 회원가입
	@GetMapping("/signup_gallery")
	public String signup_gallery(@ModelAttribute("joinUserGalleyBean") GalleySignFormBean joinUserGalleyBean,Model model) {

	    return "user/signup_gallery";
	}
	
	
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
