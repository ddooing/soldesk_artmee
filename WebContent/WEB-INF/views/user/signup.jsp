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
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">

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

.form-control {
    margin-bottom: 10px;
}

td span {
    display: inline-block; /* 또는 display: block; */
    margin-bottom: 10px;
}
td button {
    margin-bottom: 10px;
}


select.box {
  width: 100%;
  height: 50px;
  box-sizing: border-box;
  margin-left: 5px;
  padding: 5px 0 5px 10px;
  border-radius: 4px;
  border: 1px solid #d9d6d6;
  color: #383838;
  background-color: #ffffff;
  font-family: 'Montserrat', 'Pretendard', sans-serif;
}

option {
  font-size: 16px;
}

.info .box#domain-list option {
  font-size: 14px;
  background-color: #ffffff;
}
/* SECTION - BIRTH */
.info#info__birth {
  display: flex;
}

.info#info__birth select {
  margin-left : 7px;
}

.info#info__birth select:first-child {
  margin-left : 0px;
}
.info#info__birth select::-webkit-scrollbar {
  width: 10px;
}

.info#info__birth select::-webkit-scrollbar-thumb {
  background-color: #b6b6b6;
  border-radius: 3px;
}

.info#info__birth select::-webkit-scrollbar-track {
  background-color: #ebe9e9;
  border-radius: 6px;
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
						<td>
							<button type="button" class="btn btn-dark" id="verificationCodeBtn" style="margin-left:15px;" >인증번호 받기</button>
						</td>
					</tr>
					
					<tr>
						<th style="width:200px;"><form:label path="email" style="font-size: 20px;">인증번호</form:label></th>
						<td>
							<input name="verification_num" class="form-control" />
						</td>
						<td>
							<div id="timer" style="position: relative; width:50px; margin-left:15px;">00:00</div>
						</td>
					</tr>
					
					<tr>
						<th style="width:200px;">
							<form:label path="birth" style="font-size: 20px;">생년월일</form:label>
						</th>
						<td>
							<form:hidden path="birth" id="formatted-birth-date"/>
							<div class="info" id="info__birth">
							  <select class="box" id="birth-year" style="width: 125px;">
							    <option disabled selected>출생 연도</option>
							  </select>
							  <select class="box" id="birth-month">
							    <option disabled selected>월</option>
							  </select>
							  <select class="box" id="birth-day">
							    <option disabled selected>일</option>
							  </select>
							</div>
							<span id="birthError" style="color:red;"></span>
						</td>
					</tr>
					<script>
					
						//비밀번호 체크
						function pwCheck() 
						{
					       if ($('#pw1').val() === '' || $('#pw2').val() === '') {
					       		
					           $('#pwConfirm2').html('');
					       } else if ($('#pw1').val() === $('#pw2').val()) {
					           $('#pwConfirm2').html('<i class="bi bi-check-circle-fill" style="font-size: 30px; color:green;"></i>');
					       		
					       } else {
					           $('#pwConfirm2').html('<i class="bi bi-x-circle-fill" style="font-size: 30px; color:#e33d3d;"></i>');
					       		
					       }
					   	}
					
					</script>

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
							<span id="pwError2" style="color:red;"></span>
						</td>
						<td style="display: flex; align-items: center; ">
							<span id="pwConfirm2" style="margin-left: 10px; margin-bottom:0px;"></span>
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
			<!--  
			<hr style="margin: auto; margin-top: 50px; width: 600px;" />
			<div style="position: flex; margin:auto; justify-content: center;">
				
					<div class="d-flex flex-column flex-md-row p-4 gap-4 py-md-5 align-items-center justify-content-center">
						<div class="list-group">
						    <div onclick="gallerySignForm()" style="display: flex; cursor:pointer; font-size: 20px;">
					            전시관 회원가입
					            <i class="bi bi-chevron-right"></i>
						    </div>
						</div>
					</div>
					<script>
						function gallerySignForm() {
							window.location.href = '${root}/user/signup_gallery';
						}
					</script>
					
			</div>
			-->
			
			
			<!-- 확인 버튼 -->
			<div style="display:flex; justify-content: center; align-items: center;">
				<a href="${root}/view/index" class="btn btn-danger" style="margin-top:30px; margin-right:15px;">취소</a>
				<button type="button" onclick="submitForm();" class="btn btn-dark" style="margin-top:30px;">확인</button>
			</div>
		</form:form>
	</section>
	<script src="../js/signup.js"></script>
	

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


	<!-- 푸터-->
	<c:import url="/WEB-INF/views/include/footer.jsp" />
	
	<script>
	$(document).ready(function() {
	    $("#verificationCodeBtn").click(function() {
	        var email = $("#email").val();

	        // AJAX POST 요청 수행
	        $.ajax({
	            url: "${root}/user/email_verification", // 필요에 따라 URL 조정
	            type: "POST",
	            contentType: "application/json", // Content-Type 설정 추가
	            data: JSON.stringify({
	                email: email
	            }),
	            success: function(response) {
	                // 서버의 응답 처리
	                Swal.fire({
	                    icon: 'success',
	                    title: '인증번호 발송 완료',
	                    text: '이메일로 인증번호가 발송되었습니다.',
	                    confirmButtonText: '확인'
	                });
	             // 타이머 추가
	                var time = 5 * 60; // 5분을 초 단위로 변환
	                var timerElement = document.getElementById('timer');

	                var interval = setInterval(function() {
	                    var minutes = parseInt(time / 60, 10);
	                    var seconds = parseInt(time % 60, 10);

	                    minutes = minutes < 10 ? "0" + minutes : minutes;
	                    seconds = seconds < 10 ? "0" + seconds : seconds;

	                    timerElement.textContent = minutes + ":" + seconds;

	                    if (--time < 0) {
	                        timerElement.textContent = "";
	                        clearInterval(interval);
	                    }
	                }, 1000);
	            },
	            error: function(xhr, status, error) {
	                if (xhr.status !== 200) {
	                    console.log("Error: Status code is not 200");
	                }
	                if (xhr.getResponseHeader("Content-Type").indexOf("application/json") === -1) {
	                    console.log("Error: Response is not JSON");
	                    // 여기서 추가 처리를 할 수 있습니다.
	                } else {
	                    // JSON으로 안전하게 파싱할 수 있음
	                    var response = JSON.parse(xhr.responseText);
	                }
	            

	                // 사용자에게 오류 메시지 표시
	                Swal.fire({
	                    icon: 'error',
	                    title: '오류 발생',
	                    text: '인증번호 발송 중 오류가 발생했습니다.',
	                    confirmButtonText: '닫기'
	                });
	            }
	        });
	    });
	});

</script>


<c:if test="${verificationError == '인증번호가 일치하지 않습니다.'}">
        <script>
        Swal.fire({
            title: '오류',
            text: '인증번호가 일치하지 않습니다.',
            icon: 'error',
            confirmButtonText: '확인'
        });
    </script>
    </c:if>
	
	</body>


</html>