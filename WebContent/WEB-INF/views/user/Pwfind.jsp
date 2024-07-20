<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>ARTMEE</title>
	<link rel="icon" type="image/x-icon" href="../img/ARTMEE_PAGELOGO.png" />

	<!-- Font Awesome icons (free version)-->
	<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

	<!-- Google fonts-->
	<link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900"
		rel="stylesheet" />
	<link href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i"
		rel="stylesheet" />

	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>

	<!-- JQuery 자바스크립트-->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>


	<!-- CSS -->
	<link href="css/styles.css" rel="stylesheet" />

	<!-- Carousel 자바스크립트-->
	<script src='https://www.gmarwaha.com/script/lib/jquery-1.11.1.js'></script>
	<script src="https://www.gmarwaha.com/script/lib/jquery.easing-1.3.js"></script>
	<script src="https://www.gmarwaha.com/script/lib/jquery.easing.compatibility.js"></script>
	<script src="https://www.gmarwaha.com/script/lib/jquery.mousewheel-3.1.12.js"></script>
	<script src="https://www.gmarwaha.com/jquery/jcarousellite/script/jquery.jcarousellite.js"></script>

	<style>
		#jcl-demo {
			text-align: center;
		}

		.custom-container.main,
		.custom-container.main1,
		.custom-container.main2 {
			display: flex;
			justify-content: center;
			align-items: center;
		}

		.prev,
		.next,
		.prev1,
		.next1,
		.prev2,
		.next2 {
			margin: 0 10px;
		}

		.carousel ul li,
		.carousel1 ul li {
			margin-right: 10px;
		}

		.carousel ul li:last-child,
		.carousel1 ul li:last-child {
			margin-right: 0;
		}

		.carousel img,
		.carousel1 img {
			max-width: 100%;
		}

		section#scroll *,
		section#scroll *::before,
		section#scroll *::after {
			box-sizing: content-box;
		}

		.divider:after,
		.divider:before {
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
	</style>

</head>

<body id="page-top">
	<!-- 메뉴바 -->
	<c:import url="/WEB-INF/views/include/header.jsp"/>


	<!--로그인 부분-->
	<section>
	    <div class="container py-5 h-100" style="margin-top:100px;">
	        <div class="row d-flex align-items-center justify-content-center h-100">
	            <div class="col-md-12 col-lg-5 col-xl-5">
						<div style="position: flex; margin:auto; justify-content: center;">
				
					<h2>비밀번호 찾기</h2>
					<hr style="margin: auto; margin-top: 50px; margin-bottom: 50px; width: 600px;" />
					<section>
					<form:form action="${root }/user/Pwfind_go" method="post" modelAttribute="findUserBean">
					    <div style="display: flex; justify-content: center; align-items: center; margin-top:10px;">
					        <div style="margin-right:50px;">
					            <label path="name1" style="font-size: 20px; font-weight:bold; width:90px;">이름</label>
					        </div>
					        <div style="display: flex;">
					            <form:input path="name1" class="form-control"/>
					        </div>
					    </div>
					    
					    <div style="display: flex; justify-content: center; align-items: center; margin-top:30px;">
					        <div style="margin-right:50px;">
					            <label path="id1" style="font-size: 20px; font-weight:bold; width:90px;">아이디</label>
					        </div>
					        <div style="display: flex;">
					            <form:input path="id1" class="form-control"/>
					        </div>
					    </div>
					    
					    <div style="display: flex; justify-content: center; align-items: center; margin-top:30px;">
					        <div style="margin-right:50px;">
					            <label path="email1" style="font-size: 20px; font-weight:bold; width:90px;">이메일</label>
					        </div>
					        <div style="display: flex; align-items: center; position: relative;">
					            <form:input path="email1" id="email1" class="form-control" />
					            <input type="button" style="position: absolute; right: -150px; border: 0.5px solid lightgray;" class="btn btn-dark" id="verificationCodeBtn" value="인증번호 받기">
					        </div>
					    </div>
					    
					    <div style="display: flex; justify-content: center; align-items: center; margin-top:30px; margin-left:80px;">
					        <div style="margin-right:50px;">
					            <label path="verification_code" style="font-size: 20px; font-weight:bold; width:90px;">인증번호</label>
					        </div>
					        <div style="display: flex; align-items: center;">
					            <input name="verification_num" class="form-control" style="flex-grow: 1; width:240px; margin-right:30px;"/>
					            <div id="timer" style="position: relative; width:50px;">00:00</div>
					        </div>
					    </div>
					    
					    <div style="display:flex; margin-top:50px; justify-content: center; align-items: center;">
							<a href="${root}/user/login" class="btn btn-danger" style=" margin-right:15px;">로그인</a>
							<button type="submit" class="btn btn-dark">확인</button>
						</div>
						</form:form>
					</section>
			</div>
					
					
					
					
					

				</div>
			</div>
		</div>
	</section>

					<!--밑부분 배너-->
					<!-- 배너 캐러셀 -->
					<section style="margin-top: 100px;">
					    <div class="container px-1" style="width:1100px;">
					        <div id="bannerCarousel" class="carousel slide" data-bs-ride="carousel">
					            <!-- 캐러셀 인디케이터 -->
					            <div class="carousel-indicators">
					                <c:forEach items="${AllSubBannerInfo}" var="subBanner" varStatus="status">
					                    <button type="button" data-bs-target="#bannerCarousel" data-bs-slide-to="${status.index}" 
					                            class="${status.index == 0 ? 'active' : ''}" aria-label="Slide ${status.index + 1}">
					                    </button>
					                </c:forEach>
					            </div>
					
					            <!-- 캐러셀 슬라이드 -->
					            <div class="carousel-inner">
					                <c:forEach items="${AllSubBannerInfo}" var="subBanner" varStatus="status">
					                    <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
					                        <a href='${root}/exhibition/exhibition_click?exhibition_id=${subBanner.exhibition_id}'>
					                            <img src="${subBanner.sub_banner_path}${subBanner.sub_banner_name}" class="d-block w-100" alt="Banner ${status.index + 1}" style="height:150px;">
					                        </a>
					                    </div>
					                </c:forEach>
					            </div>
					
					            <!-- 캐러셀 컨트롤 -->
					            <button class="carousel-control-prev" type="button" data-bs-target="#bannerCarousel" data-bs-slide="prev">
					                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
					                <span class="visually-hidden">Previous</span>
					            </button>
					            <button class="carousel-control-next" type="button" data-bs-target="#bannerCarousel" data-bs-slide="next">
					                <span class="carousel-control-next-icon" aria-hidden="true"></span>
					                <span class="visually-hidden">Next</span>
					            </button>
					        </div>
					    </div>
					</section>
	<!-- 푸터-->
	<c:import url="/WEB-INF/views/include/footer.jsp"/>

<script>
$(document).ready(function() {
    var timerInterval; // 타이머 변수 선언

    $("#verificationCodeBtn").click(function() {
        var name1 = $("#name1").val();
        var id1 = $("#id1").val();
        var email1 = $("#email1").val();

        // 입력값 검증 (이름, 아이디, 이메일)
        if (!name1 || !id1 || !email1) {
            Swal.fire('입력 오류', '이름, 아이디, 이메일을 모두 입력해주세요.', 'error');
            return;
        }

        clearInterval(timerInterval); // 이전 타이머 중지

        // AJAX POST 요청
        $.ajax({
            url: "${root}/user/Pwfind_pro", // 비밀번호 찾기 처리 URL
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({name1: name1, id1: id1, email1: email1}),
            success: function(response) {
                // 인증번호 발송 성공 알림
                Swal.fire('인증번호 발송 완료', '이메일로 인증번호가 발송되었습니다.', 'success');
                startTimer(); // 타이머 시작
            },
            error: function(xhr, status, error) {
                // 오류 처리
                Swal.fire('오류 발생', '인증번호 발송 중 오류가 발생했습니다.', 'error');
            }
        });
    });

    // 타이머 함수
    function startTimer() {
        var time = 5 * 60; // 타이머 시간 설정 (5분)
        var timerElement = document.getElementById('timer');

        timerInterval = setInterval(function() {
            var minutes = Math.floor(time / 60);
            var seconds = time % 60;
            minutes = minutes < 10 ? '0' + minutes : minutes;
            seconds = seconds < 10 ? '0' + seconds : seconds;
            timerElement.textContent = minutes + ":" + seconds;
            if (--time < 0) {
                timerElement.textContent = "00:00";
                clearInterval(timerInterval); // 타이머 종료
            }
        }, 1000);
    }
});
</script>

</body>

</html>
