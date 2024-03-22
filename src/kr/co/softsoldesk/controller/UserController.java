package kr.co.softsoldesk.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.softsoldesk.Beans.SubBannerBean;
import kr.co.softsoldesk.Beans.UserBean;
import kr.co.softsoldesk.Service.AdminService;
import kr.co.softsoldesk.Service.UserService;
import kr.co.softsoldesk.validator.UserValidator;



@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@GetMapping("/review_editor")
	public String review_editor() {
		return "user/review_editor";
	}
	//----------------------------------------------
	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") UserBean temploginUserBean,
						@RequestParam(value = "fail", defaultValue = "false")boolean fail,
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
        	redirectAttributes.addFlashAttribute("loginFail", true);
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
	
	
	@GetMapping("/paymentpage_error")
	public String paymentpage_error() {
		
		return "user/paymentpage_error";
	}
	
	
	//-----------------------------------------------------------
	
	@GetMapping("/signup")
	public String signup(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		
		
	    return "user/signup";
	}
	
	
	@PostMapping("/signup_pro")
	public String signup_pro(@Valid @ModelAttribute("joinUserBean")UserBean joinUserBean, BindingResult result,Model model) {

		
		userService.addUserInfo(joinUserBean);
		
    	
    	return "user/join_success";
	}
	//-----------------------------------------------------
	
	@GetMapping("/InfoChange")
	   public String InfoChange(@ModelAttribute("modifyUserBean")UserBean modifyUserBean,
	                     @RequestParam("user_id")int user_id, Model model) {
	      
	      
	      UserBean IC = userService.getLoginUserAllInfo(user_id);
	      
	      model.addAttribute("IC", IC);

	      
	      return "user/InfoChange";
	      
	   }
	   
	   @PostMapping("/InfoChange_pro")
	   public String InfoChange_pro(@Valid @ModelAttribute("modifyUserBean")UserBean modifyUserBean,
	         BindingResult result, Model model) {
	      if(result.hasErrors()) {
	      
	    	  
	         return "user/InfoChange";
	      }
	      
	      userService.ModifyUserInfo(modifyUserBean);
	      model.addAttribute("user_id", modifyUserBean.getUser_id());
	      return "user/InfoChange_success";
	   }
	
	
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
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		UserValidator validator1 = new UserValidator(loginUserBean);
		
		binder.addValidators(validator1);
		
	}
	
	
	
}
