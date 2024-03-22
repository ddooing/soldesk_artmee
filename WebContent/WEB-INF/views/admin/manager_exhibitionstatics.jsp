<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="root" value="${pageContext.request.contextPath }" />
    
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<link rel="icon" type="image/x-icon" href="assets/ARTMEE_PAGELOGO.png" />
	<title>관리자 페이지</title>
	<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
	<link href="../css/styles_manager.css" rel="stylesheet" />
	<!--부트스트랩 아이콘 사용-->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">

	<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

	<!-- sweetalert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
</head>

<body class="sb-nav-fixed">

	<c:import url="/WEB-INF/views/include/admin_header.jsp" />
		<!--실제 내용(대시보드) 부분-->
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
				    <h1 class="mt-4 mb-4">전시회 통계</h1>
				
				    <div class="row d-flex h-100">
				        <div class="col-xl-8 d-flex flex-column">
						    <div class="card mb-4 shadow flex-grow-1">
						        <div class="card-header d-flex align-items-center justify-content-between">
						            <div>
						                <i class="fas fa-chart-area me-1"></i> 기간별 전시회 통계
						            </div>
						            <div class="d-flex align-items-center">
						                <div class="input-group">
						                   <input type="date" id="start_date" class="form-control" name="start_date" value="${start_date}" />
											<div class="input-group-append">
											    <span class="input-group-text" style="background-color: transparent; border: none;">~</span>
											</div>
											<input type="date" id="end_date" class="form-control" name="end_date" value="${end_date}" />
											<button id="applyDateChanges" class="btn btn-dark">적용</button>
										</div>
						            </div>
						        </div>
						        <div class="card-body"><canvas id="myAreaChart" width="100%" height="35"></canvas></div>
						    </div>
						</div>
					
				       <div class="col-xl-4 d-flex flex-column">
						    <!-- 첫 번째 카드 -->
						    <div class="card shadow mb-2">
						        <div class="card-header bg-primary text-white">
						            <i class="fas fa-calendar me-1"></i> 총 방문자 수 (${start_date} ~ ${end_date})
						        </div>
						        <div class="card-body d-flex justify-content-between align-items-center" style="height: 11.3rem;"> <!-- 높이 수정 -->
						            <h4 class="text-primary" style="margin-left: 20px;">${getexhibitionstaticscntBean.total_tickets_sold}</h4>
						            <i class="fas fa-user fa-2x text-gray-300" style="opacity: 0.5; margin-right:20px;"></i>
						        </div>
						    </div>
						    <!-- 두 번째 카드 -->
						    <div class="card shadow mb-2">
						        <div class="card-header bg-success text-white">
						            <i class="fas fa-won-sign me-1"></i> 총 수입 (${start_date} ~ ${end_date})
						        </div>
						        <div class="card-body d-flex justify-content-between align-items-center" style="height: 11.3rem;"> <!-- 높이 수정 -->
						            <h4 class="text-success" style="margin-left: 20px;">${getexhibitionstaticscntBean.total_sales_amount}</h4>
						            <i class="fas fa-won-sign fa-2x text-gray-300" style="opacity: 0.5; margin-right:20px;"></i>
						        </div>
						    </div>
						</div>


				    	
				    	<div class="col-xl-12 d-flex">
				    		<div class="card mb-4 shadow flex-grow-1">
						        <div class="card-header d-flex align-items-center justify-content-between">
						            <div>
						                <i class="fa-solid fa-list"></i> 기간별 리스트
						            </div>
						        </div>
						        <div class="card-body">
						        	<table class="table table-striped" style="text-align: center;">
										<thead>
											<tr style="vertical-align: middle;">
												<th scope="col" style="width: 50px;">NO</th>
												<th scope="col" style="width: 200px;">구매자명</th>
												<th scope="col" style="width: 200px;">결제일시</th>
												<th scope="col" style="width: 200px;">예매날짜</th>
												<th scope="col" style="width: 200px;">티켓수</th>
												<th scope="col" style="width: 200px;">결제액</th>
											</tr>
										</thead>
										<tbody>
														<c:forEach items="${getexhbitionstaticslistBean }" var="listbean">
															<tr style="vertical-align: middle;">
																	<th scope="row" style="width: 50px;">
																	${listbean.reserve_id }
																	</th>
																	<td style="width: 50px;">
																	${listbean.name }
																	</td>
																	<td style="width: 200px;"> 
																	${listbean.approved_at }
																	</td>
																	<td style="width: 200px;">
																	${listbean.reserve_date }
																	</td>		
																	<td style="width: 200px;">
																	${listbean.ticket_count }
																	</td>		
																	<td style="width: 200px;">
																	${listbean.total_price }
																	</td>
															</tr>
														</c:forEach>		
										</tbody>
									</table>
						        
						        
										
											<div class="d-none d-md-block" style="margin-top: 50px;">
												<ul class="pagination justify-content-center">
													<c:choose>
														<c:when test="${pageBean.prevPage <= 0 }">
															<li class="page-item disabled">
																<!-- 1페이지에 있으면 이전 버튼 비활성화 --> <a href="#" class="page-link">이전</a>
															</li>
														</c:when>
														<c:otherwise>
															<li class="page-item"><a
																href="${root }/admin/manager_exhibitionstatics?exhibition_id=${exhibition_id }&start_date=${start_date}&end_date=${end_date }&page=${pageBean.prevPage}"
																class="page-link">이전</a></li>
														</c:otherwise>
													</c:choose>
				
													<c:forEach var="idx" begin="${pageBean.min}"
														end="${pageBean.max}">
														<!-- model로 가져온 pageBean의 최소페이지부터 최대페이지까지 반복 : idx 는 현재페이지-->
														<c:choose>
															<c:when test="${idx == pageBean.currentPage }">
																<li class="page-item active"><a
																	href="${root }/admin/manager_exhibitionstatics?exhibition_id=${exhibition_id }&start_date=${start_date}&end_date=${end_date }&page=${idx}"
																	class="page-link"> ${idx } </a></li>
															</c:when>
															<c:otherwise>
																<li class="page-item"><a
																	href="${root }/admin/manager_exhibitionstatics?exhibition_id=${exhibition_id }&start_date=${start_date}&end_date=${end_date }&page=${idx}"
																	class="page-link"> ${idx } </a></li>
															</c:otherwise>
														</c:choose>
													</c:forEach>
				
				
													<c:choose>
														<c:when test="${pageBean.max >= pageBean.pageCnt  }">
															<!-- max페이지 > 전체페이지개수 일때  -->
															<li class="page-item disabled">
																<!-- 1페이지에 있으면 이전 버튼 비활성화 --> <a href="#" class="page-link">다음</a>
															</li>
														</c:when>
														<c:otherwise>
															<li class="page-item"><a
																href="${root }/admin/manager_exhibitionstatics?exhibition_id=${exhibition_id }&start_date=${start_date}&end_date=${end_date }&page=${pageBean.nextPage}"
																class="page-link">다음</a></li>
														</c:otherwise>
													</c:choose>
												</ul>
											</div>
				
											<div class="d-block d-md-none">
												<ul class="pagination justify-content-center">
													<li class="page-item"><a href="#" class="page-link">이전</a>
													</li>
													<li class="page-item"><a href="#" class="page-link">다음</a>
													</li>
												</ul>
											</div>
										
						
						        </div>
						    </div>
				    	</div>
				    </div>
				</div>
			</main>

			
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>

	<!--차트 라이브러리-->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
	
<script>
    const startDateInput = document.getElementById('start_date');
    const endDateInput = document.getElementById('end_date');

    // 오늘 날짜 설정 (YYYY-MM-DD 형식)
    const today = new Date().toISOString().split('T')[0];
    endDateInput.setAttribute('max', today);

    function validateDate() {
        const startDate = startDateInput.value ? new Date(startDateInput.value) : null;
        const endDate = endDateInput.value ? new Date(endDateInput.value) : null;

        // Ensure end date is not after today
        if (endDate && endDate.toISOString().split('T')[0] > today) {
            Swal.fire('오류', '종료 날짜는 오늘까지만 선택 가능합니다.', 'error');
            endDateInput.value = today;
            return false; // Prevent form submission
        }

        // Ensure start date is not after end date
        if (startDate && endDate && startDate > endDate) {
            Swal.fire('오류', '시작일은 종료일보다 느릴 수 없습니다.', 'error');
            startDateInput.value = endDateInput.value;
            return false; // Prevent form submission
        }

        // Check if the difference between start date and end date is more than 10 days
        if (startDate && endDate) {
            const dayDifference = Math.round((endDate - startDate) / (1000 * 60 * 60 * 24));
            if (dayDifference > 10) {
                Swal.fire('오류', '시작 날짜와 종료 날짜는 최대 10일 차이나게 선택해주세요.', 'error');
                return false; // Prevent form submission
            }
        }

        return true; // Validation passed
    }

    startDateInput.addEventListener('change', validateDate);
    endDateInput.addEventListener('change', validateDate);
</script>
	
<script>
    var exhibitionId = ${exhibition_id}; // 서버 사이드에서 exhibition_id 값을 가져옵니다. 

 // Apply date changes button click listener
    document.getElementById('applyDateChanges').addEventListener('click', function(event) {
        const isDateValid = validateDate(); // Call the validateDate function
        if (!isDateValid) {
            event.preventDefault(); // Prevent the form submission / redirect if validation fails
        } else {
            // Dates are valid, build the URL and redirect
            const startDate = startDateInput.value;
            const endDate = endDateInput.value;
            const urlParams = new URLSearchParams(window.location.search);
            urlParams.set('exhibition_id', exhibitionId);
            urlParams.set('start_date', startDate);
            urlParams.set('end_date', endDate);
            urlParams.set('page', 1);
            window.location.href = window.location.pathname + '?' + urlParams.toString();
        }
    });
</script>





	<script>
	    var getsumpriceData = [
	        <c:forEach var="item" items="${getexhibitionStaticsBean}" varStatus="status">
	        {"reservation_date": "${item.reservation_date}", "daily_reservations": ${item.daily_reservations}}<c:if test="${!status.last}">,</c:if>
	        </c:forEach>
	    ];
	</script>
	
	<script>
	    // 서버에서 받은 데이터를 기반으로 labels와 data 배열 생성
	    var labels = getsumpriceData.map(function(item) {
	        return item.reservation_date;
	    });
	
	    var data = getsumpriceData.map(function(item) {
	        return item.daily_reservations;
	    });
	
	    // Find the maximum value in the data and increase it for the y-axis maximum
	    var maxValue = Math.max(...data);
	    var yAxisMax = maxValue > 1 ? maxValue * 1.1 : 10; // If maxValue is greater than 1, increase it by 10%, otherwise set yAxisMax to 10
	
	    // 차트 인스턴스 생성 코드에서 labels와 data 업데이트
	    var ctx = document.getElementById("myAreaChart");
	    var myBarChart = new Chart(ctx, {
	        type: 'bar',
	        data: {
	            labels: labels,
	            datasets: [{
	                label: "일별 예약 수",
	                backgroundColor: "rgba(2,117,216,1)",
	                borderColor: "rgba(2,117,216,1)",
	                borderWidth: 1,
	                data: data,
	            }]
	        },
	        options: {
	            tooltips: {
	                callbacks: {
	                    label: function(tooltipItem) {
	                        return tooltipItem.yLabel.toLocaleString();
	                    }
	                }
	            },
	            scales: {
	                xAxes: [{
	                    gridLines: {
	                        display: false
	                    },
	                    ticks: {
	                        autoSkip: false,
	                        maxRotation: 0, // Adjust for horizontal display
	                        minRotation: 0
	                    }
	                }],
	                yAxes: [{
	                    ticks: {
	                        beginAtZero: true,
	                        max: yAxisMax, // Set the calculated maximum value for the y-axis
	                        callback: function(value) {
	                            return value.toLocaleString();
	                        }
	                    },
	                    gridLines: {
	                        display: true
	                    }
	                }]
	            },
	            legend: {
	                display: false
	            }
	        }
	    });
	</script>




</body>

</html>