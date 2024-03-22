<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>ARTMEE</title>
<link rel="icon" type="image/x-icon" href="../img/ARTMEE_PAGELOGO.png" />

<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i"
	rel="stylesheet" />
	
<!-- sweetalert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>		

<!-- Bootstrap core JS-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>

<!-- JQuery 자바스크립트-->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<!-- Carousel 자바스크립트-->
<script src='https://www.gmarwaha.com/script/lib/jquery-1.11.1.js'></script>
<script src="https://www.gmarwaha.com/script/lib/jquery.easing-1.3.js"></script>
<script
	src="https://www.gmarwaha.com/script/lib/jquery.easing.compatibility.js"></script>
<script
	src="https://www.gmarwaha.com/script/lib/jquery.mousewheel-3.1.12.js"></script>
<script
	src="https://www.gmarwaha.com/jquery/jcarousellite/script/jquery.jcarousellite.js"></script>

<style>
#jcl-demo {
	text-align: center;
}

.custom-container.main, .custom-container.main1, .custom-container.main2
	{
	display: flex;
	justify-content: center;
	align-items: center;
}

.prev, .next, .prev1, .next1, .prev2, .next2 {
	margin: 0 10px;
}

.carousel ul li, .carousel1 ul li {
	margin-right: 10px;
}

.carousel ul li:last-child, .carousel1 ul li:last-child {
	margin-right: 0;
}

.carousel img, .carousel1 img {
	max-width: 100%;
}

section#scroll *, section#scroll *::before, section#scroll *::after {
	box-sizing: content-box;
}

.divider:after, .divider:before {
	content: "";
	flex: 1;
	height: 1px;
	background: #eee;
}

.form-check-input[type="checkbox"] {
	background-color: #fff;
	border: 1px solid #000;
	border-radius: 4px;
}

.form-check-input[type="checkbox"]:checked {
	background-color: #000;
	border: 1px solid #000;
	color: #fff;
}

.age_btn {
	float: left;
	width: 100px; 
	height: 40px; 
	cursor: pointer;
	font-weight: 300;
	color: #757575;
	background-color: #fff;
	border: 1px solid #E7E7E7;
	height: 40px;
	cursor: pointer;
	font-weight: 300;
	color: #757575;
	border: 2px solid transparent;
	transition: background-color 0.3s ease-in-out;
	border-radius: 5px; 
}

.age_btn.on {
	background-color: #E7E7E7;
	border-radius: 5px; 
}

#readg {
	transform-origin: top;
}


.asdf tr {
	height: 50px;
}
td, tr{
	height: 80px;
}
input{
	margin-bottom: 10px;
}

span{
	position: absolute;
}


</style>

</head>
<script>
	function checkIdExist() {
		document.getElementById("idError").innerHTML = "";	
		var id = $("#id").val();

		if (id.length == 0) {
			Swal.fire({
				  icon: "error",
				  title: "아이디를 입력해주세요!",
				});
			return;
		}

		$.ajax({
			url : '${root}/user/checkIdExist/' + id,
			type : 'get',
			dataType : 'text',
			success : function(result) {
				if (result.trim() == 'true') {
					Swal.fire({
						  icon: "success",
						  title: "사용할 수 있는 아이디 입니다!",
						});
					$("#IdExist").val('true');
					
				} else if (result.trim() == 'false') {
					Swal.fire({
						  icon: "error",
						  title: "사용할 수 없는 아이디 입니다!",
						});
					$("#IdExist").val('false');
				}
			}
		});
	}

	function resetIdExist() {
		$("#IdExist").val('false');
	}

	function checkNickExist() {
		var nickname = $("#nickname").val();
		document.getElementById("nickError").innerHTML = "";
		if (nickname.length == 0) {
			Swal.fire({
				  icon: "error",
				  title: "닉네임을 입력해 주세요!",
				});
			return;
		}

		$.ajax({
			url : '${root}/user/checkNickExist/' + nickname,
			type : 'get',
			dataType : 'text',
			success : function(result) {
				if (result.trim() == 'true') {
					Swal.fire({
						  icon: "success",
						  title: "사용할 수 있는 닉네임 입니다",
						});
					$("#NickExist").val('true');
				} else if (result.trim() == 'false') {
					Swal.fire({
						  icon: "error",
						  title: "사용할 수 없는 닉네임 입니다",
						});
					$("#NickExist").val('false');
				}
			}
		});
	}

	function resetNickExist() {
		$("#NickExist").val('false');
	}
</script>

<body id="page-top">

	<!-- 메뉴바 -->
	<c:import url="/WEB-INF/views/include/header.jsp" />

	<!--로그인 부분-->
	<section id="readg" class="text-center" style="margin-top: 100px;">

		<h2>회원가입</h2>
		<hr style="margin: auto; margin-top: 50px; width: 1000px;" />

		<form:form action="${root}/user/signup_pro" method="post" id="signupForm"
			modelAttribute="joinUserBean">
			<form:hidden path="IdExist" />
			<form:hidden path="NickExist" />

			<div
				style="display: flex; justify-content: center; align-content: center; text-align: left;">
				<table class="asdf" style="margin-top: 50px;">
					<tr>
						<th style="width:200px;">
						<form:label path="name" style="font-size: 20px;">성함</form:label>
						</th>
						<td><form:input type="text" path="name" id="name" class="form-control" value="${tempLoginUserBean.name }" onkeyup="validateName()" style="margin-bottom: 10px;" />
						<span id="nameError" style="color:red;"></span></td>
					</tr>
					
					

					<tr>
						<th style="width:200px;">
						<form:label path="id" style="font-size: 20px;">아이디</form:label></th>
						<td>
							<form:input path="id" id="id" onkeypress="resetIdExist()" class="form-control" value="${tempLoginUserBean.id }" onkeyup="validateId()" style="margin-bottom: 10px;"/>
							<span id="idError" style="color:red;"></span>
						</td>
	
						<td>
							<button type="button" class="btn btn-dark" style="margin-left:15px;" onclick="checkIdExist()">중복확인</button>
						</td>
					</tr>
					
					<tr>
						<th style="width:200px;"><form:label path="email" style="font-size: 20px;">이메일</form:label></th>
						<td>
							<form:input path="email" class="form-control" required="required" value="${tempLoginUserBean.email }"/>
							<span id="emailError" style="color:red;"></span>
						</td>
					</tr>
					
					<tr>
						<th style="width:200px;">
							<form:label path="birth" style="font-size: 20px;">생년월일</form:label>
						</th>
						<td>
							<form:input type="date" id="birth" path="birth" value="${tempLoginUserBean.birth }" style="width:100%;" pattern="yyyy-MM-dd" required="required"/>
							<span id="birthError" style="color:red;"></span>
						</td>
					</tr>

					<tr>
						<th style="width:200px;">
							<form:label path="password" style="font-size: 20px;">비밀번호</form:label>
						</th>
						<td>
							<form:password path="password" class="form-control" id="pw1" oninput="pwCheck()" onkeyup="validatePw()" required="required"/>
							<span id="pwError" style="color:red;"></span>
						</td>
					</tr>

					<tr>
						<th style="width:200px;">
							
							<form:label path="password2" style="font-size: 20px;">비밀번호 확인</form:label>
						
						</th>
						<td>
						
							<form:password path="password2" class="form-control" id="pw2" oninput="pwCheck()" required="required" />
							<form:errors path="password2" style="color:red" />
							
						</td>
						<td>
						
							<span id="pwConfirm2" style="margin-left: 10px;"></span>
							
						</td>
						
						
					</tr>
					<tr>
						<th style="width:200px;">
							<form:label path="nickname" style="font-size: 20px;">닉네임</form:label>
						</th>
						<td>
							<form:input path="nickname" class="form-control" id="nickname" value="${tempLoginUserBean.nickname }" onkeypress="resetNickExist()" onkeyup="validateNickname()" style="margin-bottom: 10px;" />
							<span id="nickError" style="color:red;"></span>
						</td>
						<td>
							<button type="button" class="btn btn-dark" style="margin-left:15px;" onclick="checkNickExist()">중복확인</button>
						</td>
					</tr>
					<tr>
						<th style="width:200px;">
						<form:label path="gender" style="font-size: 20px;">성별</form:label>	</th>
						<td>
							<div style="display: flex; justify-content: center;">
								<div style="margin-right: 80px;">
									<form:radiobutton path="gender" value="male" id="male" />
									<form:label path="gender" for="male" style="margin-left:10px;">남</form:label>
								</div>
								<div>
									<form:radiobutton path="gender" value="female" id="female" />
									<form:label path="gender" for="female" style="margin-left:10px;">여</form:label>
								</div>
							</div>
							<span id="genderError" style="color:red" ></span>
						</td>
					</tr>
					<tr>
						<th style="width:200px;">
						<form:label path="telephone" style="font-size: 20px;">전화번호</form:label></th>
						<td>
							<form:input path="telephone"  class="form-control" placeholder="'-' 없이 입력" value="${tempLoginUserBean.telephone }"/>
							<span id="telephoneError" style="color:red;"></span>
						</td>
					</tr>
				</table>
			</div>
			<!-- 여기서 부터 전시관 관리자 영역 -->
			
			<hr style="margin: auto; margin-top: 50px; width: 600px;" />
			<div style="position: flex; margin:auto; justify-content: center;">
				
					<div class="d-flex flex-column flex-md-row p-4 gap-4 py-md-5 align-items-center justify-content-center">
						<div class="list-group">
						    <div class="form-check" style="display: flex; align-items: center; height: 20px;">
						        <input class="form-check-input" type="checkbox" value="" id="exhibition_person_apply_check" style="width:20px; height:20px; margin-right: 20px;">
						        <label class="form-check-label" for="exhibition_person_apply_check" style="font-size: 20px; line-height: 20px; margin-top:8px;">
						            전시관 관계자 이신가요?
						        </label>
						    </div>
						</div>
					</div>
					
					<section id="exhibition_person_apply_section" style="display: none;">
					    <div style="display: flex; justify-content: center; align-items: center; margin-top:10px;">
					        <div style="margin-right:50px;">
					            <form:label path="exhibition" style="font-size: 20px; font-weight:bold;">전시관 이름</form:label>
					        </div>
					        <div style="display: flex;">
					            <form:input path="exhibition" class="form-control"/>
					        </div>
					    </div>
					    
					    <div style="display: flex; justify-content: center; align-items: center; margin-top:30px;">
					        <div style="margin-right:50px;">
					            <form:label path="exhibition_address" style="font-size: 20px; font-weight:bold;">전시관 주소</form:label>
					        </div>
					        <div style="display: flex; align-items: center; position: relative;">
					            <form:input path="exhibition_address" id="exhibition_address" class="form-control"  readonly="true" disabled="true"/>
					            <input type="button" style="position: absolute; right: -70px; border: 0.5px solid lightgray;" class="btn btn-dark" onclick="sample4_execDaumPostcode()" value="검색">
					        </div>
					    </div>
					    
					    <div style="display: flex; justify-content: center; align-items: center; margin-top:30px;">
					        <div style="margin-right:50px;">
					            <form:label path="exhibition_employee" style="font-size: 20px; font-weight:bold;">전시관 직책</form:label>
					        </div>
					        <div style="display: flex;">
					            <form:input path="exhibition_employee" class="form-control"/>
					        </div>
					    </div>
					</section>
			</div>
			
			
			
			<!-- 확인 버튼 -->
			<div style="display:flex; justify-content: center; align-items: center;">
				<a href="${root}/view/index" class="btn btn-danger" style="margin-top:30px; margin-right:15px;">취소</a>
				<button type="button" onclick="submitForm();" class="btn btn-dark" style="margin-top:30px;">확인</button>
			</div>
		</form:form>
	</section>

	<!-- 주소 검색 -->
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
		    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
		    function sample4_execDaumPostcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
		                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var roadAddr = data.roadAddress; // 도로명 주소 변수
		                var extraRoadAddr = ''; // 참고 항목 변수
		
		                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                    extraRoadAddr += data.bname;
		                }
		                // 건물명이 있고, 공동주택일 경우 추가한다.
		                if(data.buildingName !== '' && data.apartment === 'Y'){
		                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                }
		                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                if(extraRoadAddr !== ''){
		                    extraRoadAddr = ' (' + extraRoadAddr + ')';
		                }
		
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById("exhibition_address").value = roadAddr;
		
		            }
		        }).open();
		    }
		</script>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	$(document).ready(function() {
	    $('#exhibition_person_apply_check').change(function() {
	        if (this.checked) {
	            $('#exhibition_person_apply_section').slideDown(); // 체크 되었으면 섹션 부드럽게 보여주기
	        } else {
	            $('#exhibition_person_apply_section').slideUp(); // 체크 해제되었으면 섹션 부드럽게 숨기기
	            // 체크 해제시 input 필드 초기화
	            $('#exhibition_address').val("");
	            $('input[name="exhibition"]').val("");
	            // 필요하다면 다른 input 필드도 초기화
	        }
	    });
	});


	</script>
<!-- 요기까지 전시관 관계자 -->
	<script>
		function age_chg(age) {
			var buttons = document.querySelectorAll('.age_btn');
			buttons.forEach(function(button) {
				button.classList.remove('on');
			});

			var selectedButton = document.getElementById('age_' + age);
			selectedButton.classList.add('on');
		}
	</script>



	<!-- 푸터-->

	<c:import url="/WEB-INF/views/include/footer.jsp" />
	</body>
	<script>
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
	        var name = document.getElementById("pw1").value;
	        var regex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{6,20}$/;
	
	        if (!regex.test(name)) {
	            document.getElementById("pwError").innerHTML = "사용자 비밀번호는 6~20자여야 하고 \r\n숫자, 영소문자, 영대문자, 특수기호가 하나씩 들어가야 합니다.";
	        } else {
	            document.getElementById("pwError").innerHTML = "";
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
	    	var birth = document.getElementById("birth").value;
	        var birthError = document.getElementById("birthError");

	        // 생년월일이 비어 있는지 확인
	        if (!birth) {
	            event.preventDefault(); // 폼 제출 방지
	            birthError.innerHTML = "생년월일을 입력해주세요."; // 에러 메시지 표시
	        } else {
	            birthError.innerHTML = ""; // 에러 메시지 초기화
	        }
	    }
	    
	</script>
<script>
    document.addEventListener("DOMContentLoaded", function(event) { 
        var today = new Date().toISOString().split('T')[0];
        document.getElementById("birth").setAttribute('max', today);
    });
</script>
<script>
    function submitForm() {
    	
    	event.preventDefault(); // 폼의 기본 제출 이벤트를 방지
    	
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
        validateNickname();
        validatePhone();
        validateGender();
        validateBirth();
        
        
       console.log("아이디 중복 : ",$("#IdExist").val());
       console.log("닉 중복 : ",$("#NickExist").val());
       
       console.log("생년월일 : ",$("#birth").val());
      
       
       console.log("nameError : ",nameError);
       console.log("idError : ",idError);
       console.log("pwError : ",pwError);
       console.log("nickError : ",nickError);
        
       
       var IdExist = $("#IdExist").val() === "true"; // jQuery를 사용하여 값을 가져옴
       var NickExist = $("#NickExist").val() === "true"; // jQuery를 사용하여 값을 가져옴

       var birth = $("#birth").val(); // jQuery를 사용하여 birth 값을 가져옴
       var phone = document.getElementById("telephone").value; // 순수 JavaScript 사용
       var genderValue = document.querySelector('input[name="gender"]:checked') ? document.querySelector('input[name="gender"]:checked').value : null;

       // 모든 검증이 통과되는 조건
       if (nameError === "" && idError === "" && pwError === "" && nickError === "" &&
           IdExist && NickExist && birth && phone && genderValue) {
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
</script>
<script>
         function pwCheck() {
             if ($('#pw1').val() === '' || $('#pw2').val() === '') {
                // $('#pwConfirm1').text('');
                 $('#pwConfirm2').text('');
             } else if ($('#pw1').val() === $('#pw2').val()) {
                 //$('#pwConfirm1').text( '✔').css('color', 'green');
                 $('#pwConfirm2').text('✔').css('color', 'green');
             } else {
                 //$('#pwConfirm1').text('✖').css('color', 'red');
                 $('#pwConfirm2').text('✖').css('color', 'red');
             }
         }
             </script>





</html>