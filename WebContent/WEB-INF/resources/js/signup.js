	// '출생 연도' 
	const birthYearEl = document.querySelector('#birth-year')
	// option 목록 생성 여부 확인
	isYearOptionExisted = false;
	birthYearEl.addEventListener('focus', function () {
	  // year 목록 생성되지 않았을 때 (최초 클릭 시)
	  if(!isYearOptionExisted) {
	    isYearOptionExisted = true
	    for(var i = 2023; i >= 1940; i--) {
	      // option element 생성
	      const YearOption = document.createElement('option')
	      YearOption.setAttribute('value', i)
	      YearOption.innerText = i
	      // birthYearEl의 자식 요소로 추가
	      this.appendChild(YearOption);
	    }
	  }
	});
	
	// '출생 월' 
	const birthMonthEl = document.querySelector('#birth-month')
	// option 목록 생성 여부 확인
	isMonthOptionExisted = false;
	birthMonthEl.addEventListener('focus', function () {
	  // year 목록 생성되지 않았을 때 (최초 클릭 시)
	  if(!isMonthOptionExisted) {
		  isMonthOptionExisted = true
	    for(var i = 1; i <= 12; i++) {
	      // option element 생성
	      const MonthOption = document.createElement('option')
	      MonthOption.setAttribute('value', i)
	      MonthOption.innerText = i
	      // birthYearEl의 자식 요소로 추가
	      this.appendChild(MonthOption);
	    }
	  }
	});
	
	// '출생 일' 
	const birthDayEl = document.querySelector('#birth-day')

	birthDayEl.addEventListener('focus', function () {
		
		//선택한 월 값 
		 const selectedMonth = parseInt(document.querySelector('#birth-month').value);
		
		// 이전에 생성된 '출생 일' 옵션들을 제거
	    while(birthDayEl.firstChild) {
	        birthDayEl.removeChild(birthDayEl.firstChild);
	    }
		
		let days;
	    if (selectedMonth === 2) { // 2월
	        days = 28;
	    } else if ([4, 6, 9, 11].includes(selectedMonth)) { 
	        days = 30;
	    } else { // 31일까지 있는 월
	        days = 31;
	    }

	    for(var i = 1; i <= days; i++) {
	      // option element 생성
	      const DayOption = document.createElement('option')
	      DayOption.setAttribute('value', i)
	      DayOption.innerText = i
	      this.appendChild(DayOption);
	    }
	  
	});

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
	    
	    function validateNickname() {
	        var name = document.getElementById("nickname").value;
	        var regex = /^.{2,8}$/;
	
	        if (!regex.test(name)) {
	            document.getElementById("nickError").innerHTML = "사용자 닉네임은 2~8자리여야 합니다.";
	        } 
	        else 
	        {
	        	//닉네임 중복체크
	        	if($("#NickExist").val()=='false')
		       	{
	        		document.getElementById("nickError").innerHTML = "닉네임 중복확인 해주세요";
		       	}
	        	else{
	        		document.getElementById("nickError").innerHTML = "";	
	        	}
	            
	        }
	    }
	    // 전화번호
	    function validatePhone() {
	        var phone = document.getElementById("telephone").value;
	        var telephoneError = document.getElementById("telephoneError");
	        var regex = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
	
	        console.log("번호 : ",phone);
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
	    
	 // 성별
	    function validateGender() {
	    	//var genderValue = document.querySelector('input[name="gender"]:checked').value;
	    	var genderValue = document.querySelector('input[name="gender"]:checked') ? document.querySelector('input[name="gender"]:checked').value : null;
       
	    	console.log("성별 : ",genderValue);
	    	var genderError = document.getElementById("genderError");
	     
	    	if (!genderValue) {
	            genderError.innerHTML = "성별을 선택해주세요.";
	        } else {
	            genderError.innerHTML = ""; // 에러 메시지 초기화

	        }
	    }
	    
	    function validateBirth() {
	    	var year = document.getElementById('birth-year').value;
	        var month = document.getElementById('birth-month').value;
	        var day = document.getElementById('birth-day').value;
	        
	        var birthError = document.getElementById("birthError");

	        // 생년월일이 비어 있는지 확인
	        if (!year|| !month || !day) {
	            event.preventDefault(); // 폼 제출 방지
	            birthError.innerHTML = "생년월일을 입력해주세요."; // 에러 메시지 표시
	        }else {
	            birthError.innerHTML = ""; // 에러 메시지 초기화
	        }
	    }
	
	
	
	function submitForm() {
    	
    	event.preventDefault(); // 폼 제출 방지
    	
    	var nameError = document.getElementById("nameError").innerHTML;
        var idError = document.getElementById("idError").innerHTML;
        var pwError = document.getElementById("pwError").innerHTML;
        var nickError = document.getElementById("nickError").innerHTML;
        
        var telephoneError = document.getElementById("telephoneError").innerHTML;
        var genderError = document.getElementById("genderError").innerHTML;
        var birthError = document.getElementById("birthError").innerHTML;
        
    	validateName();
        validateId();
    	 
        validateEmail()
        validatePw();
        validatePw2();
        validateNickname();
        validatePhone();
        validateGender();
        validateBirth();
        
        
       console.log("아이디 중복 : ",$("#IdExist").val());
      
       
       console.log(" : ",$("#NickExist").val());
    	// 연도, 월, 일 값을 가져옵니다.
       const year = document.getElementById('birth-year').value;
       const month = document.getElementById('birth-month').value;
       const day = document.getElementById('birth-day').value;
	
       console.log("year",year);
       console.log("month",month);
       console.log("day",day);
       // 월과 일이 한 자리 숫자일 경우 앞에 0을 붙여줍니다.
       const formattedMonth = month.padStart(2, '0');
       const formattedDay = day.padStart(2, '0');

       console.log("formattedMonth",formattedMonth);
       console.log("formattedDay",formattedDay);
       
       // "yyyy-mm-dd" 형식으로 날짜를 조합합니다.
       const formattedDate = [year, formattedMonth, formattedDay].join('-');
	   console.log("생년월일: ", formattedDate);

       // 조합된 날짜 값을 숨겨진 입력 필드에 설정합니다.
       document.getElementById('formatted-birth-date').value = formattedDate;

       console.log("생년월일 : ",$("#formatted-birth-date").val());
              
       console.log("nameError : ",nameError);
       console.log("idError : ",idError);
       console.log("pwError : ",pwError);
       console.log("nickError : ",nickError);
       console.log($('#pw1').val() === $('#pw2').val());
       
       var IdExist = $("#IdExist").val() === "true"; // jQuery를 사용하여 값을 가져옴
       var NickExist = $("#NickExist").val() === "true"; // jQuery를 사용하여 값을 가져옴

       var birth = $("#formatted-birth-date").val(); // jQuery를 사용하여 birth 값을 가져옴
       var phone = document.getElementById("telephone").value; // 순수 JavaScript 사용
       var genderValue = document.querySelector('input[name="gender"]:checked') ? document.querySelector('input[name="gender"]:checked').value : null;

       // 모든 검증이 통과되는 조건
       if (nameError === "" && idError === "" && pwError === "" && nickError === "" &&
           IdExist && NickExist && birth && phone && genderValue
           &&($('#pw1').val() === $('#pw2').val())) {
           // 조건을 만족할 때의 로직
           
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
           // 조건을 만족하지 않을 때의 로직
           console.log("조건을 만족하지 않습니다.");
       }

    }
    
    
