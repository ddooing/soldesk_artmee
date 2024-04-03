<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
				
					<h2>아이디 찾기</h2>
					<hr style="margin: auto; margin-top: 50px; margin-bottom: 50px; width: 500px;" />
					<section>
					    <div style="display: flex; justify-content: center; align-items: center; margin-top:10px;">
					        <div style="margin-right:50px;">
					            <label style="font-size: 20px; font-weight:bold; width:90px;">이름</label>
					        </div>
					        <div style="display: flex; justify-content: center; align-items: center; width:100px; height: 100%;">
					            <p style="margin: auto; text-align: center;">${findUserBean.name1 }</p>
					        </div>
					    </div>
					    
					    <div style="display: flex; justify-content: center; align-items: center; margin-top:30px;">
					        <div style="margin-right:50px;">
					            <label style="font-size: 20px; font-weight:bold; width:90px;">아이디</label>
					        </div>
					        <c:choose>
							    <c:when test="${not empty findUserBean.id and fn:length(findUserBean.id) >= 3}">
							        <div style="display: flex; justify-content: center; align-items: center; width:100px; height: 100%;">
							            <p style="margin: auto; text-align: center;">
							                ${fn:substring(findUserBean.id, 0, fn:length(findUserBean.id)-3)}***
							            </p>
							        </div>
							    </c:when>
							    <c:otherwise>
							        <div style="display: flex; justify-content: center; align-items: center; width:100px; height: 100%;">
							            <p style="margin: auto; text-align: center;">${findUserBean.id}</p>
							        </div>
							    </c:otherwise>
							</c:choose>
					    </div>
					
					    <div style="display:flex; margin-top:50px; justify-content: center; align-items: center;">
					        <a href="${root}/view/index" class="btn btn-danger" style="margin-right:15px;">홈</a>
					        <button type="button" onclick="location.href='${root}/user/login'" class="btn btn-dark">로그인</button>
					    </div>
					    
					</section>


			</div>
				</div>
			</div>
		</div>
	</section>

				
	<!-- 푸터-->
	<c:import url="/WEB-INF/views/include/footer.jsp"/>


</body>

</html>
