<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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

	<!-- sweetalert2 알림 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

	<!-- Google fonts-->
	<link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900"
		rel="stylesheet" />
	<link href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i"
		rel="stylesheet" />

	<!-- Bootstrap core JS-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="../js/scripts.js"></script>

	<!-- JQuery 자바스크립트-->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<!-- sweetalert2 알림 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

	<!-- CSS -->
	<link href="../css/styles.css" rel="stylesheet" />

	<!-- Carousel 자바스크립트-->
	<script src='https://www.gmarwaha.com/script/lib/jquery-1.11.1.js'></script>
	<script src="https://www.gmarwaha.com/script/lib/jquery.easing-1.3.js"></script>
	<script src="https://www.gmarwaha.com/script/lib/jquery.easing.compatibility.js"></script>
	<script src="https://www.gmarwaha.com/script/lib/jquery.mousewheel-3.1.12.js"></script>
	<script src="https://www.gmarwaha.com/jquery/jcarousellite/script/jquery.jcarousellite.js"></script>


	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet">
	<style>
		button {
			border: none;
			background: transparent;
		}

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


		#right-side-menu {
			position: absolute;
			top: 500px;
			right: 10px;
			width: 150px;
			transition: top 0.3s;

		}

		#sidebar_menu:hover {
			cursor: pointer;
			background-color: rgb(192, 192, 192);
		}
	</style>

</head>

<body id="page-top">
	<!-- 메뉴바 -->

	 <c:import url="/WEB-INF/views/include/header.jsp"/> 

	<!--전시회 -->
	<section id="scroll">
		<div class="container px-5">
			<div class="d-flex justify-content-between" style="margin-top: 180px;align-items: center;">	
				<div id="popularExhibitionLabel" class="form-check-label" for="popularExhibition"
					style="cursor: pointer; font-size: 30px;">전시회</div>
					
				<label id="morelook" class="form-check-label" for="morelook"
					style="cursor: pointer; font-size: 20px;" onclick="window.location.href='${root}/exhibition/exhibition_search?keyword=${param.allKeyword}'">더보기</label>
			</div>

		    <div style="display: flex;justify-content: center;">
		        <c:forEach items="${getSearchExhibitionInfo}" var="exhibition" varStatus="status">
		            <div style="padding: 10px; width: calc(25%-30px); box-sizing: border-box;">
		                <a href="${root}/exhibition/exhibition_click?exhibition_id=${exhibition.exhibition_id}&user_id=${loginUserBean.user_id}">
		                    <img style="width:255px; height:375px;" src="${exhibition.main_poster_path}${exhibition.main_poster_name}" alt="포스터"
		                        style="width: 100%; height: auto; margin-bottom: 30px;" />
		                </a>
		               <p style="font-size: 20px; width:255px; text-align: center; margin-top:30px; word-wrap: break-word;">
						    ${exhibition.title }
						</p>
		                
		                
		            </div>
		            
		            <c:if test="${status.count lt 4}">
		                <c:forEach begin="1" end="${4-status.count}" var="dummy">
		                    <div style="width:275px; height: 301px; ">
		                        <!-- 더미 콘텐츠, 필요 없으면 비워둠 -->
		                    </div>
		                </c:forEach>
		            </c:if>
		        </c:forEach>
		</div>
	</section>


	<!--공지사항 -->
	<section id="scroll">
		<div class="container px-5">
			<div class="d-flex justify-content-between" style="margin-top: 80px;align-items: center;">	
				<div id="popularExhibitionLabel" class="form-check-label" for="popularExhibition"
					style="cursor: pointer; font-size: 30px;">공지사항</div>
					
				<label id="morelook" class="form-check-label" for="morelook"
					style="cursor: pointer; font-size: 20px;" onclick="window.location.href='${root}/notice/main?type=title&keyword=${param.allKeyword}'">더보기</label>
			</div>

		    <div style="display: flex; justify-content: center;align-items: center;width:100%;">
                  <table class="tbl_list text_c" style="margin-left: auto; margin-right: auto; width:80%; font-size: 21px;">
                     <colgroup>
                        
                        <col style="width: auto;">
                        <col style="width: 20%;">
                        <col style="width: 15%;">
                     </colgroup>
                     <thead style="height:70px; text-align: center;">
                        <tr>
                           <th scope="col" style="text-align:left;">제목</th>
                           <th scope="col">상태</th>
                           <th scope="col">등록일</th>
                        </tr>
                     </thead>
                     <tbody >
                     
                        <c:forEach var="noticeList" items="${noticeList }">
                           <tr style="height:55px; text-align: center;">
                              <c:choose>
                              		<c:when test="${noticeList.state == 2 }">
                              			<td style="text-align: left; background-color: #F8DAD5;">
                              				<a href="${root }/notice/read?notice_id=${noticeList.notice_id}" style="color: black; text-decoration: none;">${noticeList.title}</a>
                             			 </td>
                              		</c:when>
                              		<c:otherwise>
                              			<td style="text-align: left;">
                              				<a href="${root }/notice/read?notice_id=${noticeList.notice_id}" style="color: black; text-decoration: none;">${noticeList.title}</a>
                             			 </td>
                              		</c:otherwise>
                              </c:choose>
                              
                              <c:choose>
                                  <c:when test="${noticeList.state == 2 }">
                                     <td style="background-color: #F8DAD5;">중요</td>
                                  </c:when>
                                  <c:when test="${noticeList.state == 1 }">
                                     <td style="background-color: white;">일반</td>
                                  </c:when>
                                  <c:otherwise>
                                  </c:otherwise>
                               </c:choose>
                               
                              <c:choose>
                              	<c:when test="${noticeList.state == 2 }">
                              		<td style="background-color: #F8DAD5;">${noticeList.create_date}</td>
                              	</c:when>
                              	<c:otherwise>
                              		<td>${noticeList.create_date}</td>
                              	</c:otherwise>
                              </c:choose>
 
                           </tr>
                        </c:forEach>
                        
                     </tbody>
                  </table>
               </div>
	</section>
	
	<!--게시글 -->
	<section id="scroll">
		<div class="container px-5">
			<div class="d-flex justify-content-between" style="margin-top: 80px;align-items: center;">	
				<div id="popularExhibitionLabel" class="form-check-label" for="popularExhibition"
					style="cursor: pointer; font-size: 30px;">게시판</div>
					
				<label id="morelook" class="form-check-label" for="morelook"
					style="cursor: pointer; font-size: 20px;" onclick="window.location.href='${root}/board/main?searchType=title&searchText=${param.allKeyword}'">더보기</label>
			</div>

		    <div style="display: flex; justify-content: center;align-items: center;width:100%;">
                  <table class="tbl_list text_c" style="margin-left: auto; margin-right: auto; width:80%; font-size: 21px;">
                     <colgroup>
                        
                        <col style="width: auto;">
                        <col style="width: 20%;">
                        <col style="width: 15%;">
                     </colgroup>
                     <thead style="height:70px; text-align: center;">
                        <tr >
	                        <th scope="col" style="text-align: left;">제목</th>
	                        <th scope="col">작성자</th>
	                        <th scope="col">등록일</th>
                        </tr>
                     </thead>
                     <tbody>
	                     <c:forEach items="${boardList}" var="board">
	                        <tr style="height:55px; text-align: center;">
	                           <td class="text_l" style="text-align:left">
	                              <a href="${root}/board/read?board_id=${board.board_id}" style="text-align:left; color: black; text-decoration: none">${board.title}</a>
	                           </td>
	                           <td >${board.nickname}</td>
	                           <td>${board.update_date}</td>
	                        </tr>
	                     </c:forEach>
                  	</tbody>
                  </table>
               </div>
	</section>
	<!-- 푸터-->
	 <c:import url="/WEB-INF/views/include/footer.jsp"/> 


	

</body>

</html>