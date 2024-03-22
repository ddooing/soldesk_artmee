<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="root" value="${pageContext.request.contextPath }" />
    
<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="utf-8" />
   <meta http-equiv="X-UA-Compatible" content="IE=edge" />
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
   <meta name="description" content="" />
   <meta name="author" content="" />
   <link rel="icon" type="image/x-icon" href="../img/ARTMEE_PAGELOGO.png" />
   <title>관리자 페이지</title>
   <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
   <link href="../css/styles_manager.css" rel="stylesheet" />
   <!--부트스트랩 아이콘 사용-->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">

   <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

   
</head>
<style>
.equal-height {
        display: flex;
        flex-wrap: wrap;
    }

    .equal-height > [class*='col-'] {
        display: flex;
        flex-direction: column;
    }

    .equal-height .card {
        flex: 1; /* Flex 컨테이너 내에서 카드를 동일한 높이로 확장합니다. */
    }

/* 기본 폰트 사이즈 */
/* 기본 폰트 사이즈 */
.table .card-body {
    font-size: 1rem; /* 대형 화면 기준 */
}

/* 중형 화면 (가로 너비가 992px 미만인 경우) */
@media (max-width: 991.98px) {
    .table .card-body {
        font-size: 0.9rem; /* 중형 화면 폰트 사이즈 조정 */
    }
}

/* 소형 화면 (가로 너비가 768px 미만인 경우) */
@media (max-width: 767.98px) {
    .table .card-body {
        font-size: 0.8rem; /* 소형 화면 폰트 사이즈 조정 */
    }
}

/* 엑스트라 소형 화면 (가로 너비가 576px 미만인 경우) */
@media (max-width: 575.98px) {
    .table .card-body {
        font-size: 0.7rem; /* 엑스트라 소형 화면 폰트 사이즈 조정 */
    }
}


</style>


<body class="sb-nav-fixed">

   <c:import url="/WEB-INF/views/include/admin_header.jsp" />
      <!--실제 내용(대시보드) 부분-->
      <div id="layoutSidenav_content">
         <main>
            <div class="container-fluid px-4">
               <h1 class="mt-4 mb-4">대시보드</h1>
               
               <!-- 문의사항이 없을 때 안보이게-->
               <c:if test="${getprocesscntBean.exhibition_processcnt == 0 || getprocesscntBean.banner_processcnt == 0 || getprocesscntBean.qna_processcnt != 0}">
                  <div class="row">
                     <div class="col-xl-12">
                        <div class="card mb-4">
                           <div class="card-header">
                              <i class="fa-solid fa-check"></i>
                              미처리 현황
                           </div>
                           <div class="card-body" style="display: flex; margin-right: -50px;">
                              <c:if test="${getprocesscntBean.exhibition_processcnt != 0}"> 
                               <a href="${root }/admin/manager_exhibitionapplylist" style="margin-right: 50px; margin-left:50px; color:black; text-decoration: none;">전시회 신청 문의 ${getprocesscntBean.exhibition_processcnt}</a>
                               </c:if>
                               <c:if test="${getprocesscntBean.banner_processcnt != 0}">
                               <a href="${root }/admin/manager_mainbannerapplylist" style="margin-right: 50px; color:black; text-decoration: none;">배너 신청 문의 ${getprocesscntBean.banner_processcnt}</a>
                               </c:if>
                               <c:if test="${getprocesscntBean.qna_processcnt != 0}">
                               <a href="${root }/admin/manager_QnAlist" style="margin-right: 50px; color:black; text-decoration: none;">QnA 문의 ${getprocesscntBean.qna_processcnt}</a>
                               </c:if>
                           </div>
                        </div>
                     </div>
                  </div>
               </c:if>
               
               <div class="row d-flex">
                  <div class="col-xl-6 d-flex">
                     <div class="card mb-4 flex-grow-1">
                        <div class="card-header">
                           <i class="fas fa-chart-area me-1"></i>
                           최근 14일 판매 금액
                        </div>
                        <div class="card-body"><canvas id="myAreaChart" width="100%" height="45"></canvas></div>
                     </div>
                  </div>
                  <div class="col-xl-6 d-flex">
                     <div class="card mb-4 flex-grow-1">
                        <div class="card-header">
                           <i class="fa-solid fa-list"></i>
                           최근 7일 일자별 요약
                        </div>
                        <div class="card-body">
                           <table class="table table-striped">
                             <thead style="text-align:center;">
                                 <tr>
                                     <th>일자</th>
                                     <th>주문수</th>
                                     <th>매출액</th>
                                     <th>취소</th>
                                     <th>가입</th>
                                     <th>문의</th>
                                     <th>후기</th>
                                 </tr>
                             </thead>
                             <tbody style="text-align:center;">
                               <c:forEach items="${getdailysummaryBean}" var="dailysummary">
                                  <tr>
                                     <td>${dailysummary.order_date}</td>
                                     <td>${dailysummary.order_count}</td>
                                     <td>${dailysummary.total_sales} 원</td>
                                     <td>${dailysummary.canceled_count}</td>
                                     <td>${dailysummary.sign_up_count}</td>
                                     <td>${dailysummary.inquiry_count}</td>
                                     <td>${dailysummary.review_count}</td>
                                 </tr>
                               </c:forEach> 
                             </tbody>
                         </table>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            
            <div class="container-fluid px-4">
               
               <div class="row equal-height">
                  <div class="col-xl-6">
                     <div class="card mb-4">
                        <div class="card-header">
                           <i class="fas fa-chart-bar me-1"></i>
                           최근 7일 예매 차트
                        </div>
                        <div class="card-body"><canvas id="myBarChart" width="100%" height="50"></canvas></div>
                     </div>
                  </div>
                  <div class="col-xl-6">
                     <div class="card mb-4">
                        <div class="card-header">
                           <i class="fas fa-chart-bar me-1"></i>
                           최근 7일 게시판 인기순
                        </div>
                        <div class="card-body">
                           <div class="d-flex flex-column flex-md-row gap-4 align-items-center justify-content-center">
                             <div class="list-group w-100">
                                  <c:forEach items="${getboardpopularBean}" var="boardpop" varStatus="status">
                                  <a href="${root }/board/read?board_id=${boardpop.board_id}" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
                                    <div class="d-flex gap-2 w-100 justify-content-between">
                                      <div>
                                        <!-- 여기서 status.index에 1을 더해 순번을 표시합니다 -->
                                        <h6 class="mb-0">${status.index + 1}. ${boardpop.title}</h6>
                                        <p class="mb-0 opacity-75">${boardpop.contents}</p>
                                      </div>
                                      <small class="opacity-50 text-nowrap">조회수 : ${boardpop.views} &nbsp;&nbsp;&nbsp;작성일 : ${boardpop.create_date}</small>
                                    </div>
                                  </a>
                              </c:forEach>

                             </div>
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
      var getsumpriceData = [
          <c:forEach var="item" items="${getsumpriceBean}" varStatus="status">
          {"approved_date": "${item.approved_date}", "daily_total_price": ${item.daily_total_price}}<c:if test="${!status.last}">,</c:if>
          </c:forEach>
      ];
   </script>
   

   <script>   /* 일별 판매량 차트 */
   // 서버에서 받은 데이터를 기반으로 labels와 data 배열 생성
   var labels = getsumpriceData.map(function(item) {
       return item.approved_date;
   });

   var data = getsumpriceData.map(function(item) {
       return item.daily_total_price;
   });

   // 최댓값 계산
   var maxValue = Math.max(...data);

   // 최댓값보다 10% 높게 설정 (또는 원하는 비율로 조정)
   var yAxisMax = Math.ceil(maxValue * 1.5);

   // 차트 인스턴스 생성 코드
   var ctx = document.getElementById("myAreaChart");
   var myLineChart = new Chart(ctx, {
       type: 'line',
       data: {
           labels: labels, // 업데이트된 labels 사용
           datasets: [{
               label: "판매금액",
               lineTension: 0.3,
               backgroundColor: "rgba(2,117,216,0.2)",
               borderColor: "rgba(2,117,216,1)",
               pointRadius: 5,
               pointBackgroundColor: "rgba(2,117,216,1)",
               pointBorderColor: "rgba(255,255,255,0.8)",
               pointHoverRadius: 5,
               pointHoverBackgroundColor: "rgba(2,117,216,1)",
               pointHitRadius: 50,
               pointBorderWidth: 2,
               data: data, // 업데이트된 data 사용
           }]
       },
       options: {
           tooltips: {
               callbacks: {
                   label: function(tooltipItem, data) {
                       var label = data.datasets[tooltipItem.datasetIndex].label || '';
                       if (label) {
                           label += ': ';
                       }
                       label += '₩' + tooltipItem.yLabel.toLocaleString();
                       return label;
                   }
               }
           },
           scales: {
               xAxes: [{
                   time: {
                       unit: 'date'
                   },
                   gridLines: {
                       display: false
                   },
                   ticks: {
                       maxTicksLimit: 7
                   }
               }],
               yAxes: [{
                   ticks: {
                       min: 0,
                       max: yAxisMax, // 계산된 최댓값을 할당
                       maxTicksLimit: 7,
                       callback: function(value, index, values) {
                           return '₩' + value.toLocaleString();
                       }
                   },
                   gridLines: {
                       color: "rgba(0, 0, 0, .125)",
                   }
               }],
           },
           legend: {
               display: false
           }
       }
   });
   </script>


   <script>
      var getsumpriceData = [
          <c:forEach var="item" items="${getsumpriceBean}" varStatus="status">
          {"approved_date": "${item.approved_date}", "daily_total_price": "${item.daily_total_price}"}<c:if test="${!status.last}">,</c:if>
          </c:forEach>
      ];
   </script>
   
   <script>
      var getpricedailycountData = [
          <c:forEach var="item" items="${getreserveBean}" varStatus="status">
          {"booking_date": "${item.booking_date}", "booking_count": "${item.booking_count}"}<c:if test="${!status.last}">,</c:if>
          </c:forEach>
      ];
   </script>
   
   
   <script>   /* 일별 이용자 차트 */
      Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
      Chart.defaults.global.defaultFontColor = '#292b2c';
      
      var labels = getpricedailycountData.map(function(item) {
          return item.booking_date;
      });

      var data = getpricedailycountData.map(function(item) {
          return parseInt(item.booking_count, 10); // 문자열을 정수로 변환
      });

   
      var ctx = document.getElementById("myBarChart");
      var myBarChart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: labels,
          datasets: [{
            label: "이용자",
            backgroundColor: "rgba(2,117,216,1)",
            borderColor: "rgba(2,117,216,1)",
            data: data,
          }],
        },
        options: {
          tooltips: {
            callbacks: {
              label: function(tooltipItem, data) {
                var label = data.datasets[tooltipItem.datasetIndex].label || '';
                if (label) {
                  label += ': ';
                }
                label += tooltipItem.yLabel + ' 명';
                return label;
              }
            }
          },
          scales: {
            xAxes: [{
              time: {
                unit: 'day'
              },
              gridLines: {
                display: false
              }
            }],
            yAxes: [{
              ticks: {
                min: 0,
                // Use suggestedMax to give a hint for the max value
                suggestedMax: Math.ceil(Math.max(...data) * 1.5),
                // Set the step size to an integer value to avoid decimal steps
                stepSize: 1,
                // Use a callback to display only integer values
                callback: function(value) {
                  if (value % 1 === 0) {
                    return value;
                  }
                }
              },
              gridLines: {
                display: true
              }
            }],
          },
          legend: {
            display: false
          }
        }
      });

   </script>
   
   
</body>

</html>