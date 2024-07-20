package kr.co.softsoldesk.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.softsoldesk.Beans.ExhibitionBean;
import kr.co.softsoldesk.Beans.PointDetailBean;
import kr.co.softsoldesk.Beans.ReserveBean;
import kr.co.softsoldesk.Beans.UserBean;
import kr.co.softsoldesk.Service.ExhibitionService;
import kr.co.softsoldesk.Service.PointDetailService;
import kr.co.softsoldesk.Service.ReserveService;
import kr.co.softsoldesk.Service.ReviewService;
import kr.co.softsoldesk.Service.UserService;


@Controller
@RequestMapping("/toss")
public class TossController {

	@Resource(name="loginUserBean") // 로그인한 유저 알기위함
	private UserBean loginUserBean;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReserveService reserveService;
	
	@Autowired
	private ExhibitionService exhibitionService;
	
	@Autowired
	PointDetailService pointDetailService;
	
	@Autowired
	private ReviewService reviewService;
	
	String confirmUrl ="https://api.tosspayments.com/v1/payments/confirm";
	
	String failmsg="";
	

	private int exhibitionId=0;// fail 시 다시 전시회 정보 페이지 가기 위함
	

	String testCode = "INVALID_REQUEST"; // 에러 테스트용 코드
	
	// toss api 기준 결제금액이 0이면 결제 sdk 에러하기에 
	@PostMapping("/checkout_pro") 
	public String checkout_pro(@ModelAttribute("tempReserveBean")ReserveBean tempReserveBean,
				Model model,RedirectAttributes redirectAttributes) {

		//결제할 금액 확인
		int payment = tempReserveBean.getPayment();

		//결제 금액이 0 이면 바로 예매 완료 페이지로 이동
		if(payment == 0)
		{
			//1. 예매 정보 저장 
			//reserve_id, user_id, exhibition_id, reserve_date, total_price, point_deduction,payment, ticket_count, order_id 저장함
			//pay_state(결제 상태),pay_approval_state(결제 승인 상태) 기본으로 0(false)로 .. 결제 요청하지 않기때문
			// state 값을 1(예매 되었음)로
		reserveService.paymentZeroReserveInfo(tempReserveBean); 
		
		/*
			//저장된 예매 정보 부르기 .. reserve_id를 가져오기 위함 
		ReserveBean reserveInfoBean =reserveService.validcheckOrderId(tempReserveBean.getOrder_id());
		
			// 2. 나머지 저장 
		//addService(reserveInfoBean);
		addService(tempReserveBean);
		
		ExhibitionBean exhibitionBean = exhibitionService.getExhibitionDetailInfo(reserveInfoBean.getExhibition_id());

		redirectAttributes.addFlashAttribute("exhibitionBean", exhibitionBean);
	    redirectAttributes.addFlashAttribute("tempReserveBean", reserveInfoBean);
	    */
	    
	    addService(tempReserveBean);
	    ExhibitionBean exhibitionBean = exhibitionService.getExhibitionDetailInfo(tempReserveBean.getExhibition_id());

		redirectAttributes.addFlashAttribute("exhibitionBean", exhibitionBean);
	    redirectAttributes.addFlashAttribute("tempReserveBean", tempReserveBean);
	    
		return "redirect:/exhibition/payment_complete";
		
		
		}
		// 결제 금액이 0이 아니면 ckeckout page로 이동
		else {
			//tempReserveBean,exhibition_id 정보 넘기기
			redirectAttributes.addFlashAttribute("tempReserveBean", tempReserveBean);
	        return "redirect:/toss/checkout";
		}
		
	}
	
	
	@GetMapping("/checkout")
	//@PostMapping("/checkout")
	public String checkout(@ModelAttribute("tempReserveBean") ReserveBean tempReserveBean,
            				HttpServletRequest request, Model model) throws Exception  {
		
		//예매하려는 유저 아이디 찾기
		UserBean loginUserDetailBean = userService.getLoginUserAllInfo(tempReserveBean.getUser_id());
		
		//예매하려는 전시회 제목=> orderName 찾기
		String title = exhibitionService.getExhibitionTitle(tempReserveBean.getExhibition_id());
		//System.out.println(" /checkout - tempReserveBean pluspoint  : "+tempReserveBean.getPoint_plus());
		
		//결제 요청 전에 예매정보 데이터 저장
		//checkout 지점 db 저장                                  *후에 임시 저장하는 방식으로 바꾸기 
			//reserve_id, user_id, exhibition_id, reserve_date, total_price, point_deduction,payment, ticket_count, order_id 저장함
			//pay_state(결제 상태),pay_approval_state(결제 승인 상태) 기본으로 0(false)로 저장함 
		reserveService.checkoutReserveInfo(tempReserveBean);
		

		model.addAttribute("orderid", tempReserveBean.getOrder_id()); 
		model.addAttribute("tempReserveBean", tempReserveBean.getOrder_id()); 
	    model.addAttribute("tempReserveBean", tempReserveBean);
	    model.addAttribute("loginUserDetailBean",loginUserDetailBean);
	    model.addAttribute("title",title);
	    
	    
		return "toss/checkout";
	}
	
	//[결제 요청 성공이였을 때]
	//@RequestParam String paymentType, 
	@GetMapping("/success") 
    public String successPage(@RequestParam String orderId, 
            					@RequestParam String paymentKey, 
            					@RequestParam int amount,HttpServletRequest request, Model model,
            					RedirectAttributes redirectAttributes) throws Exception  {
		
		// [1].결제 승인 요청 전에 예매정보 데이터(/checkout 에서 저장한 정보)와 요청 결과(orderId,amount)로 검증
			
			//요청 결과 파라미터 orderId로 orderId인 예매 내역 찾기 
		ReserveBean validReserveBean = reserveService.validcheckOrderId(orderId); 
		String isOrderIdValid=validReserveBean.getOrder_id();
		
	
			// (1 결과 : false): 예매 내역에서 파라미터 orderId 와 같은 내역을 못찾을 경우, 메인 페이지로 이동 
		if(isOrderIdValid==null) {
			failmsg="주문번호 오류가 발생했습니다.";
            redirectAttributes.addFlashAttribute("failmsg", failmsg);    
            return "redirect:/view/index"; 
		}

			//(1 결과  : true)
		//[2].결제 요청 전의 결제 금액인 payment 와 결제 요청 결과 파라미터의 amount 같은지 체크		
		int reqBeforePayment = validReserveBean.getPayment();

			//(2 결과 : false): 실패 페이지로 이동
		if(reqBeforePayment!=amount)//pay_approval_state : 승인 거부 0 인 상태
		{
			failmsg="결제금액 오류가 발생했습니다.";
            redirectAttributes.addFlashAttribute("failmsg", failmsg);    
            return "redirect:/exhibition/exhibition_click?exhibition_id="+validReserveBean.getExhibition_id();  
		}
		
			//(2 결과: true) 

		//[3]. 결제 승인 
        ResponseEntity<String> paymentConfirmationResponse = completePayment(paymentKey, orderId, amount);

        //(3 결과 : 승인 실패 )
        if (!paymentConfirmationResponse.getStatusCode().is2xxSuccessful()) {
        	
        	// Response 바디에서 JSON 파싱
            String responseBody = paymentConfirmationResponse.getBody();
            JSONObject jsonObject = new JSONObject(responseBody);
            String code = jsonObject.optString("code", "Unknown"); // 기본값 설정
            String message = jsonObject.optString("message", "No message provided");

            // 만약 이미 처리된 결제건이라면, 해당 사용자의 예매 내역 페이지로 이동
            if ("ALREADY_PROCESSED_PAYMENT".equals(code)) {
                failmsg="이미 처리된 결제입니다.";
                redirectAttributes.addFlashAttribute("failmsg", failmsg);
                return "redirect:/mypage/reservelist?user_id="+validReserveBean.getUser_id();
            }
            
            failmsg=message;
            System.out.println("failmsg : "+failmsg);
            redirectAttributes.addFlashAttribute("failmsg", failmsg);
            
            return "redirect:/exhibition/exhibition_click?exhibition_id="+validReserveBean.getExhibition_id();   
        }
        
        
        //(3 결과 : 승인 성공 )=> 핸드폰에서 '@@ 원 결제'  알림 뜨는거
        
        
        //응답 본문 가져오기
        String responseBody = paymentConfirmationResponse.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);
        
        	//응답(payment 객체)에서 requestedAt(주문 날짜+시간), approvedAt(결제 승인 날짜+시간) 추출하기 
        String approvedAt = jsonObject.getString("approvedAt");
        String requestedAt = jsonObject.getString("requestedAt");
       // String method  = jsonObject.getString("method");
        
        String method = jsonObject.getString("method");
        byte[] bytes = method.getBytes(StandardCharsets.ISO_8859_1);
        method = new String(bytes, StandardCharsets.UTF_8);
        
    	//#DB 저장 - orderId인 pay_approval_state : 승인 상태 true로 update &  paymentKey 저장 
      	reserveService.approvalBefore(orderId,paymentKey); 

      	
    	// #DB 저장 
	        // 1.orderId인 예매가 정말로 되었음 
        		// 결제 방법 저장 
    			//pay_state 결제 상태 :true 로 update &  state(0:예매,1: 예매 취소) 예매가 되었음을 0으로 저장,예매한 날짜
	     		//requestAt 주문 날짜 + 시간 저장 
        		// approvedAt 결제 승인 날짜+시간 저장
        
        reserveService.realReserveState(orderId,requestedAt,approvedAt,method); 

        	// 2.나머지 db 처리
        addService(validReserveBean);
        
        //결제된 예매 정보 가져오기 
        ReserveBean reserveInfoBean =reserveService.validcheckOrderId(orderId);
		
        
        ExhibitionBean exhibitionBean = exhibitionService.getExhibitionDetailInfo(reserveInfoBean.getExhibition_id());
       
        model.addAttribute("exhibitionBean", exhibitionBean);
        model.addAttribute("tempReserveBean",reserveInfoBean);
             
        return "toss/success";
   
    }

	private void addService(ReserveBean reserveBean) {
		
		 //user_id 값
        int userid = reserveBean.getUser_id();
        int totalPrice =reserveBean.getTotal_price();
        
		// 2.사용자 포인트 내역 저장 
        
	    		// 2-1.무조건 포인트 적립
			    // 포인트 적립 : 유저 등급의 적립율에 따른 포인트 지급 
       
        PointDetailBean pointDetailBean =new PointDetailBean(); 
        pointDetailBean.setPoint(reserveBean.getPoint_plus());
        pointDetailBean.setUser_id(userid);
        pointDetailBean.setPoint_state_code(1);	// 포인트 적립 : 1
        pointDetailBean.setPoint_type_code(1);	// 포인트 사용처 예매 : 1 
        
        
		// 포인트 내역 추가
		pointDetailService.PointList(pointDetailBean);
        
        		// 2-2. point_deduction(=포인트 사용금액) >0 이면 사용 내역 추가 
		
		if(reserveBean.getPoint_deduction() > 0)
		{
			 PointDetailBean pointUseDetailBean =new PointDetailBean();
		     
			 pointUseDetailBean.setPoint(reserveBean.getPoint_deduction());
			 pointUseDetailBean.setUser_id(userid);
			 pointUseDetailBean.setPoint_state_code(2);	// 포인트 차감 : 2
			 pointUseDetailBean.setPoint_type_code(1);	// 포인트 사용처 예매 : 1 
			 
			// 포인트 내역 추가
			pointDetailService.PointList(pointUseDetailBean);
		}

	        // 3. 사용자 포인트와 경험치 exp 적립 update 
			// 경험치 ) 예매 시 + 100
        	// 포인트 )최종적으로 사용자의 현재 포인트에 추가 혹은 감소 할 포인트 금액 = 예매 시 받는 포인트 - 포인트 사용 금액
		int point = reserveBean.getPoint_plus() - reserveBean.getPoint_deduction();
        
        userService.point_expIncrease(userid,point);
        
        	// 4. 전시회에 대한 소감문 생성 
        reviewService.reserve_review_create(reserveBean.getReserve_id());
        
    		// 5.전시회 티켓수를 사용자가 구매한 티켓수만큼 증가												// 예매한 전시회 id			       			//예매한 티켓 수 int 값
        exhibitionService.increase_exhibitionTotalTicket(reserveBean.getExhibition_id(),reserveBean.getTicket_count());
 
	}
	
	private ResponseEntity<String> completePayment(String paymentKey, String orderId, int amount) {
        try {
            HttpHeaders headers = new HttpHeaders();

            String apiKey = "test_sk_mBZ1gQ4YVX9wG14ZXOLarl2KPoqN";
            // 기본 인증을 위한 API 키를 Base64로 인코딩
            String encodedApiKey = Base64.getEncoder().encodeToString((apiKey + ":").getBytes("UTF-8"));
            headers.set("Authorization", "Basic " + encodedApiKey);
            headers.set("Content-Type", "application/json");  
            
            //에러 재현하기 
            //headers.set("TossPayments-Test-Code",testCode);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("paymentKey", paymentKey);
            requestBody.put("orderId", orderId);
            requestBody.put("amount", amount);
            
            
            // 설정한 Header와 Body를 가진 HttpEntity 객체 생성
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            // HTTP POST 요청하기
            RestTemplate restTemplate = new RestTemplate();
            //confirmUrl : 결제 승인 api 주소 
            return restTemplate.exchange(confirmUrl, HttpMethod.POST, requestEntity, String.class);
        } catch (Exception e) {
        	 return ResponseEntity
        	            .status(((HttpStatusCodeException) e).getStatusCode())
        	            .body(((RestClientResponseException) e).getResponseBodyAsString());
        }
	}
	
	
	
}