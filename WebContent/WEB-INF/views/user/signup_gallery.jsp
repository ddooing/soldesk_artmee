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


</script>

<body id="page-top">

	<!-- 메뉴바 -->
	<c:import url="/WEB-INF/views/include/header.jsp" />

	<!--로그인 부분-->
	<section id="readg" class="text-center" style="margin-top: 100px;">

		<h2>전시관 회원가입</h2>
		<hr style="margin: auto; margin-top: 50px; width: 1000px;" />

		<form:form action="${root}/user/signupGallery_pro" method="post" id="signupForm"
			modelAttribute="joinUserGalleyBean">
			<form:hidden path="IdExist" />
			
			<div style="display: flex; justify-content: center; align-content: center; text-align: left;">
				<table class="asdf" style="margin-top: 50px;">
					<tr>
						<th style="width:200px;">
						<form:label path="name" style="font-size: 20px;">전시관 이름 </form:label>
						</th>
						<td><form:input type="text" path="name" id="name" class="form-control"  onkeyup="validateName()" style="margin-bottom: 10px;" />
						<span id="nameError" style="color:red;"></span></td>
					</tr>

					<tr>
						<th style="width:200px;">
						<form:label path="id" style="font-size: 20px;">아이디</form:label></th>
						<td>
							<form:input path="id" id="id" onkeypress="resetIdExist()" class="form-control"  onkeyup="validateId()" style="margin-bottom: 10px;"/>
							<span id="idError" style="color:red;"></span>
						</td>
						<td>
							<button type="button" class="btn btn-dark" style="margin-left:15px;" onclick="checkIdExist()">중복확인</button>
						</td>
					</tr>
					
					<tr>
						<th style="width:200px;"><form:label path="email" style="font-size: 20px;">전시관 이메일</form:label></th>
						<td>
							<form:input path="email" class="form-control" required="required" />
							<span id="emailError" style="color:red;"></span>
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
							<form:label path="telephone" style="font-size: 20px;">전시관 전화번호</form:label>
						</th>
						<td>
							<form:input path="telephone"  class="form-control" placeholder="'-' 없이 입력" />
							<span id="telephoneError" style="color:red;"></span>
						</td>
					</tr>
					
					<tr>
						<th  style="width: 150px; font-size: 20px;">
							<form:label path="address" style="font-size: 20px;">주소</form:label>
						</th>
						<td>
							<form:input path="address" id="address" style="width: 400px;" readonly="true" class="form-control"/>
							<span id="addressError" style="color:red;"></span>
						</td>
						<td>
							<input type="button" style="margin-left:10px; border: 0.5px solid lightgray; margin-bottom:10px;" class="btn btn-light" onclick="sample4_execDaumPostcode()" value="검색">
						</td>
					</tr>
					
					<tr>
						<th style="width: 150px;  font-size: 20px;">
							운영시간
						</th>
						<td style="justify-content: space-between;align-items: center;display: flex;flex-direction: row;">
							<form:input path="open_time" id="open_time" type="time" class="form-control" 
										style="margin-right: 10px; width=45%;" required="required" />
							~
							<form:input path="close_time" id="close_time" type="time" class="form-control" 
										style="margin-left: 10px; width=45%;" required="required" />
							<span id="timeError" style="color:red; bottom: -80px;"></span>
						</td>
					</tr>

					<tr>
						<th for="holiday" style="width: 150px; font-size: 20px;">
							휴무일
						</th>
						<td>
							<form:input path="holiday" id="holiday"  class="form-control"  />
						</td>
					</tr>
					
					<tr >
						<th style="width: 150px;font-size: 20px;">사이트</th>
						<td>
							<form:input path="site" id="site" class="form-control" style="width: 400px;" />
							<span id="siteError" style="color:red;"></span>
						</td>
					</tr>
				</table>
			</div>
			<!-- 여기서 부터 전시관 관리자 영역 -->
			
			
			
			
			
			<!-- 확인 버튼 -->
			<div style="display:flex; justify-content: center; align-items: center;">
				<a href="${root}/view/index" class="btn btn-danger" style="margin-top:30px; margin-right:15px;">취소</a>
				<button type="button" onclick="submitForm();" class="btn btn-dark" style="margin-top:30px;">확인</button>
			</div>
		</form:form>
	</section>
	<script src="../js/gallery_signup.js"></script>
	<!-- 주소 검색 -->
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
			// 시간 validation
			var start_time = document.getElementById('open_time');
			var close_time = document.getElementById('close_time');
			
			close_time.addEventListener('change', function() {
				if(close_time.value < start_time.value) {
					Swal.fire({
						  icon: "error",
						  title: "시간 선택 오류",
						  text: "종료 시간은 시작 시간보다 이전일 수 없습니다!",
						});
					close_time.value = start_time.value;
				}
			});
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
		                document.getElementById("address").value = roadAddr;
		
		            }
		        }).open();
		    }
		</script>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
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


</html>