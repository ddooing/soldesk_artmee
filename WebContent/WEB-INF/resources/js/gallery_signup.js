
	 function validateName() {
	        var name = document.getElementById("name").value;
	        var regex = /^[가-힣]{2,4}$/;
	
	        if (!regex.test(name)) {
	            document.getElementById("nameError").innerHTML = "성함은 2~4자 사이의 한글이어야 합니다.";
	        } else {
	            document.getElementById("nameError").innerHTML = "";
	        }
	    }
	    
	    function validateId() {
	        var name = document.getElementById("id").value;
	        var regex = /^[a-zA-Z0-9]{6,20}$/;
	
	        if (!regex.test(name)) {
	            document.getElementById("idError").innerHTML = "사용자 아이디는 6~20자여야 하고 영대문자, 영소문자, 숫자만 허용합니다.";
	        } else {
	        	
	        	// 중복체크 
	        	if($("#IdExist").val()=='false')
	           	{
	        		document.getElementById("idError").innerHTML =  "아이디 중복확인 해주세요";
	           	}
	        	else{
	        		document.getElementById("idError").innerHTML = "";	
	        	}
	        }
	    }
	    
	    function validateEmail() {
	        var email = document.getElementById("email").value;
	        var emailError = document.getElementById("emailError");

	        // 이메일 형식을 검사하는 정규 표현식
	        var regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

	        // 이메일 필드가 비어있는지 확인
	        if (!email) {
	            emailError.innerHTML = "이메일은 필수 항목입니다.";
	            return;
	        }
	        else{
		        // 이메일 형식이 유효한지 확인
		        if (!regex.test(email)) {
		            emailError.innerHTML = "잘못된 이메일 형식입니다.";
		            //return;
		        }else{
		        	emailError.innerHTML = "";
		        }
	        }

	    }
	    
	    function validatePw() {
	        var pw = document.getElementById("pw1").value;
	        var regex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{6,20}$/;
	
	        if (!regex.test(pw)) {
	            document.getElementById("pwError").innerHTML = "비밀번호는 6~20자 길이여야 하며, 최소 하나의 숫자, 영어 소문자, 영어 대문자, 그리고 특수 기호를 포함해야 합니다.";
	        } else {
	            document.getElementById("pwError").innerHTML = "";
	        }
	    }
	    
	   
	    function validatePw2() {
	        var pw2 = document.getElementById("pw2").value;
	        
	        if (!pw2) {
	            document.getElementById("pwError2").innerHTML = "비밀번호를 다시 한번 입력해주세요.";
	        }
	    }
	    

	    // 전화번호
	    function validatePhone() {
	        var phone = document.getElementById("telephone").value;
	        var telephoneError = document.getElementById("telephoneError");
	        var regex = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
	
	        if (!phone) {
	        	telephoneError.innerHTML = "번호를 입력해주세요.";    
	        }
	        else{
	        if (!regex.test(phone)) {
	        	telephoneError.innerHTML = "올바른 핸드폰 번호 형식이 아닙니다.";
	        } else {
	        	telephoneError.innerHTML = "";
	        }
	        }
	    }
	    
	 	// 주소
	    function validateAddress() {
	   
	    	var addressValue = document.getElementById('address').value;

	    	var addressError = document.getElementById("addressError");
	     
	    	if (!addressValue) {
	            addressError.innerHTML = "주소를 입력해주세요.";
	        } else {
	            addressError.innerHTML = ""; 

	        }
	    }
	    //운영 시간
	    function validateTime() {
	    	var open_time = document.getElementById('open_time').value;
	        var close_time = document.getElementById('close_time').value;

	        var timeError = document.getElementById("timeError");

	        if (!open_time|| !close_time ) {
	            timeError.innerHTML = "운영시간을 입력해주세요."; // 에러 메시지 표시
	        }else {
	            timeError.innerHTML = ""; // 에러 메시지 초기화
	        }
	    }
	    
	    // 사이트
	    function validateSite() {
	   
	    	var siteValue = document.getElementById('site').value;

	    	var siteError = document.getElementById("siteError");
	     
	    	if (!siteValue) {
	            siteError.innerHTML = "사이트 주소를 입력해주세요.";
	        } else {
	            siteError.innerHTML = ""; 

	        }
	    }
	
	
	
	function submitForm() {
    	
    	event.preventDefault(); // 폼 제출 방지
    	
    	var nameError = document.getElementById("nameError").innerHTML;
        var idError = document.getElementById("idError").innerHTML;
        var pwError = document.getElementById("pwError").innerHTML;
        var telephoneError = document.getElementById("telephoneError").innerHTML;
        var addressError = document.getElementById("addressError").innerHTML;
        var timeError = document.getElementById("timeError").innerHTML;
        var siteError = document.getElementById("siteError").innerHTML;
        
        
    	validateName();
        validateId();
    	 
        validateEmail()
        validatePw();
        validatePw2();
        validatePhone();
        validateAddress();
		validateTime();
        validateSite();
        
       console.log("아이디 중복 : ",$("#IdExist").val());
      

       console.log("nameError : ",nameError);
       console.log("idError : ",idError);
       console.log("pwError : ",pwError);
       console.log($('#pw1').val() === $('#pw2').val());
       
       var IdExist = $("#IdExist").val() === "true"; // jQuery를 사용하여 값을 가져옴
       var NickExist = $("#NickExist").val() === "true"; // jQuery를 사용하여 값을 가져옴

       var phone = document.getElementById("telephone").value; // 순수 JavaScript 사용

       // 모든 검증이 통과되는 조건
       if (nameError === "" && idError === "" && pwError === "" &&
       		addressError==="" && timeError==="" &&siteError==="" && 
            IdExist && phone &&
            ($('#pw1').val() === $('#pw2').val())) {

    	   Swal.fire({
               title: '회원가입 하시겠습니까?',
               icon: 'question',
               showCancelButton: true,
               confirmButtonColor: '#3085d6',
               cancelButtonColor: '#d33',
               confirmButtonText: '확인',
               cancelButtonText: '취소'
           }).then((result) => {
               if (result.isConfirmed) {
                   document.getElementById("signupForm").submit();
               }
           });
       } else {
           console.log("조건을 만족하지 않습니다.");
       }

    }
    
    
