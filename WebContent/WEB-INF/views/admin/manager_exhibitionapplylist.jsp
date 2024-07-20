<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<link rel="icon" type="image/x-icon" href="../img/ARTMEE_PAGELOGO.png" />
<title>관리자 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
	rel="stylesheet" />
<link href="../css/styles_manager.css" rel="stylesheet" />
<!--부트스트랩 아이콘 사용-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css"
	rel="stylesheet">

<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
	
<!-- sweet alert 2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
<!-- JQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#allcheck").click(function() {
			if ($(this).is(":checked")) {
				$("tbody tr th input[type='checkbox']").prop("checked", true);
			} else {
				$("tbody tr th input[type='checkbox']").prop("checked", false);
			}
		});
	});
</script>


</head>

<body class="sb-nav-fixed">
	<c:import url="/WEB-INF/views/include/admin_header.jsp" />

	<!--실제 내용(대시보드) 부분-->
	<div id="layoutSidenav_content">
		<main style="background-color: ivory;">
			<div class="container-fluid px-4">
				<div style="margin-top: 30px;">
					<h3>전시회 신청내역</h3>
				</div>
				<div style="position: relative; display: flex; justify-content: start; height: 80px; align-items: center; border: 0.2px solid black; background-color: white; margin-top: 20px;">
					<div style="display:flex;  margin-right:60px; width: 450px; float: left;">
						<span class="badge text-bg-danger rounded-pill" style="font-size: 15px; margin-right: 10px; margin-left: 50px; padding:10px;">등록대기 ${countBean.stay_exhibition_eroll_count }건</span> 
						<span class="badge text-bg-success rounded-pill" style="font-size: 15px; margin-right: 10px; padding:10px;">등록완료 ${countBean.complete_exhibition_eroll_count }건</span> 
						<span class="badge bg-success-subtle text-success-emphasis rounded-pill" style="background-color: black; font-size: 15px; padding:10px;">등록신청 총 ${countBean.total_exhibition_eroll_count}건</span>
					</div>
					
					<form action="${root }/admin/manager_exhibitionapplylist" style="margin-bottom:20px;" method="get">
						<c:choose>
							<c:when test="${exhibitioncombo == null }">
								<select name="exhibitioncombo" id="exhibitioncombo"
									style="width: 150px; height: 40px; margin-right: 30px;">
									<option value="" disabled selected>검색조건선택</option>
									<option value="title">제목</option>
									<option value="apply_person">신청인</option>
									<option value="author">작가</option>
									
								</select>
							</c:when>
							<c:when test="${exhibitioncombo == 'title' }">
								<select name="exhibitioncombo" id="exhibitioncombo"
									style="width: 150px; height: 40px; margin-right: 30px;">
									<option value="" disabled>검색조건선택</option>
									<option value="title" selected>제목</option>
									<option value="apply_person">신청인</option>
									<option value="author">작가</option>
									
								</select>
							</c:when>
							<c:when test="${exhibitioncombo == 'apply_person' }">
								<select name="exhibitioncombo" id="exhibitioncombo"
									style="width: 150px; height: 40px; margin-right: 30px;">
									<option value="" disabled>검색조건선택</option>
									<option value="title">제목</option>
									<option value="apply_person" selected>신청인</option>
									<option value="author">작가</option>
									<option value="enroll_state">상태</option>
								</select>
							</c:when>
							<c:when test="${exhibitioncombo == 'author' }">
								<select name="exhibitioncombo" id="exhibitioncombo"
									style="width: 150px; height: 40px; margin-right: 30px;">
									<option value="" disabled>검색조건선택</option>
									<option value="title">제목</option>
									<option value="apply_person">신청인</option>
									<option value="author" selected>작가</option>
									<option value="enroll_state">상태</option>
								</select>
							</c:when>

						</c:choose>

						<c:choose>
							<c:when test="${exhibitionsearch != null }">
								<input type="text" name="exhibitionsearch" id="exhibitionsearch"
									style="width: 500px; height: 40px; margin-right: 30px;"
									value="${exhibitionsearch }" placeholder="검색어를 입력해주세요" />
							</c:when>
							<c:otherwise>
								<input type="text" name="exhibitionsearch" id="exhibitionsearch"
									style="width: 500px; height: 40px; margin-right: 30px;"
									placeholder="검색어를 입력해주세요" />
							</c:otherwise>
						</c:choose>

						<button class="btn btn-dark" style="width: 80px; height: 40px;">검색</button>
						<button class="button-39" id="resetButton" role="button" style="width: 80px; height: 44px; margin-top: 22px; margin-left: 20px;">초기화</button>
                  
	                  <script>
	                      document.addEventListener('DOMContentLoaded', function() {
	                          // 검색 조건 콤보박스와 검색어 입력 필드, 검색 버튼 요소를 가져옵니다.
	                          var resetButton = document.getElementById('resetButton');
	
	                          // 검색 버튼 클릭 이벤트 리스너를 추가합니다.
	                          resetButton.addEventListener('click', function() {
	                              window.location.href = '${root}/admin/manager_exhibitionapplylist';
	                          });
	                      });
	                  </script>
					</form>
				</div>


				<div style="background-color: white; margin-top: 30px;">



					<table class="table table-striped" style="text-align: center;">
						<thead>
							<tr style="vertical-align: middle;">
								<th scope="col" style="width: 50px;"><input type="checkbox"
									id="allcheck" /></th>
								<th scope="col" style="width: 50px;">No</th>
								<th scope="col">신청인</th>
								<th scope="col">제목</th>
								<th scope="col">전시기간</th>
								<th scope="col">작가</th>
								<th scope="col">티켓 가격</th>
								<th scope="col">상태</th>
								<th scope="col">관리</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${enrollAllBean}" var="enrolllist">
								<tr style="vertical-align: middle;">
									<th scope="row"><input type="checkbox"
										style="width: 50px;" /></th>
									<td style="width: 50px;">${enrolllist.exhibition_enroll_id }</td>
									<td style="width: 150px;">${enrolllist.apply_name }</td>
									<td style="width: 500px; text-align: left;">${enrolllist.title }</td>
									<td style="width: 300px;">${enrolllist.exhibition_start }~${enrolllist.exhibition_end }</td>
									<c:choose>
										<c:when test="${enrolllist.author == null}">
											<td style="width: 150px;">없음</td>
										</c:when>
										<c:otherwise>
											<td style="width: 150px;">${enrolllist.author }</td>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${enrolllist.price == 0}">
											<td style="width: 100px;">무료</td>
										</c:when>
										<c:otherwise>
											<td style="width:100px;">
										    	<fmt:formatNumber value="${enrolllist.price}" groupingUsed="true" type="number"/> 원
											</td>
										</c:otherwise>
									</c:choose>

									<c:choose>
										<c:when test="${enrolllist.enroll_state == 1 }">
											<td>대기</td>
										</c:when>
										<c:when test="${enrolllist.enroll_state == 2 }">
											<td>등록완료</td>
										</c:when>
										<c:when test="${enrolllist.enroll_state == 3 }">
											<td style="color: red;">거절</td>
										</c:when>
									</c:choose>

									<td><c:choose>
											<c:when test="${enrolllist.enroll_state == 1 }">
												<button class="btn btn-dark"
													onclick="location.href='${root}/admin/manager_exhibitionenrolladd?exhibition_enroll_id=${enrolllist.exhibition_enroll_id}'">상세</button>
												<button class="btn btn-danger" onclick="confirmRejection('${enrolllist.exhibition_enroll_id}')">거절</button>
											</c:when>
											<c:otherwise>
												<button class="btn btn-dark"
													onclick="location.href='${root}/admin/manager_exhibitionenrolladd?exhibition_enroll_id=${enrolllist.exhibition_enroll_id}'">상세</button>
											</c:otherwise>
										</c:choose></td>
								</tr>
								<script>	// 거절 버튼 클릭시 sweetalert
								    function confirmRejection(exhibitionEnrollId) {
								        Swal.fire({
								            title: '거절하시겠습니까?',
								            text: '거절하면 복구 할 수 없습니다',
								            icon: 'warning',
								            showCancelButton: true,
								            confirmButtonColor: '#d33',
								            cancelButtonColor: '#3085d6',
								            confirmButtonText: '거절',
								            cancelButtonText: '취소'
								        }).then((result) => {
								            if (result.isConfirmed) {
								                Swal.fire(
								                    '거절되었습니다!',
								                    '전시회 신청이 거절되었습니다.',
								                    'success'
								                ).then((result) => {
								                    if (result.isConfirmed) {
								                        // 거절 처리 URL로 리디렉션
								                        window.location.href = '${root}/admin/manager_enroll_reject?exhibition_enroll_id=' + exhibitionEnrollId;
								                    }
								                });
								            }
								        });
								    }
								</script>
								
							</c:forEach>

						</tbody>
					</table>


					<div class="d-none d-md-block" style="margin-top: 50px;">
						<ul class="pagination justify-content-center">
							<c:choose>
								<c:when test="${pageBean.prevPage <= 0 }">
									<li class="page-item disabled">
										<a href="#" class="page-link">이전</a></li>
								</c:when>
								<c:otherwise>
									<li class="pre-page">
                                    	<a href="#" class="page-link">이전</a>
                                    </li>
								</c:otherwise>
							</c:choose>
							<script>
                                document.addEventListener('DOMContentLoaded', function () {
                                     var pageLinks = document.querySelectorAll('.pre-page');
                             
                                     pageLinks.forEach(function(link) {
                                         link.addEventListener('click', function(event) {
                                             event.preventDefault();
                                             var pageNum = ${pageBean.prevPage};
                                             var urlParams = new URLSearchParams(window.location.search);
                                             urlParams.set('page', pageNum);
                                             window.location.href = window.location.pathname + '?' + urlParams.toString();
                                         });
                                     });
                                 });
                             </script>

							<c:forEach var="idx" begin="${pageBean.min}"
								end="${pageBean.max}">
								<c:choose>
									<c:when test="${idx == pageBean.currentPage}">
										<li class="page-item active"><a
											href="${root}/admin/manager_exhibitionapplylist?page=${idx}"
											class="page-link">${idx}</a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
                                            		<a href="#" class="page-link page-link-number" data-page="${idx}">${idx}</a>
                                        		</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<script>
                                 document.addEventListener('DOMContentLoaded', function () {
                                     var pageLinks = document.querySelectorAll('.page-link-number');
                             
                                     pageLinks.forEach(function(link) {
                                         link.addEventListener('click', function(event) {
                                             event.preventDefault();
                                             var pageNum = this.getAttribute('data-page');
                                             var urlParams = new URLSearchParams(window.location.search);
                                             urlParams.set('page', pageNum);
                                             window.location.href = window.location.pathname + '?' + urlParams.toString();
                                         });
                                     });
                                 });
                             </script>
							<c:choose>
								<c:when test="${pageBean.max >= pageBean.pageCnt}">
									<li class="page-item disabled"><a href="#"
										class="page-link">다음</a></li>
								</c:when>
								<c:otherwise>
									<li class="next-page">
                                       <a href="#"class="page-link">다음</a>
                                    </li>
								</c:otherwise>
							</c:choose>
							<script>
                                document.addEventListener('DOMContentLoaded', function () {
                                     var pageLinks = document.querySelectorAll('.next-page');
                             
                                     pageLinks.forEach(function(link) {
                                         link.addEventListener('click', function(event) {
                                             event.preventDefault();
                                             var pageNum = ${pageBean.nextPage};
                                             var urlParams = new URLSearchParams(window.location.search);
                                             urlParams.set('page', pageNum);
                                             window.location.href = window.location.pathname + '?' + urlParams.toString();
                                         });
                                     });
                                 });
                             </script>
						</ul>
					</div>

				</div>
			</div>
		</main>


	</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>

</body>

</html>