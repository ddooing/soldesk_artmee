<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>메인 배너 캘린더</title>
	<link
		href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
		rel="stylesheet" />
	<link href="../css/styles_manager.css" rel="stylesheet" />
	<!--부트스트랩 아이콘 사용-->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
	
	<!-- sweet alert 2 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
	<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
	<!-- JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/6.1.11/locales/ko.js"></script>
	<script>
	
	var urlParams =new URLSearchParams(window.location.search);

	var typeParam = urlParams.get('type'); 
	
	var typeUrl="";
	if(typeParam ==='main')
	{
		typeUrl="?type=main";
	}else if(typeParam ==='sub')
	{
		typeUrl="?type=sub";
	}
	  document.addEventListener('DOMContentLoaded', function() {
	    var calendarEl = document.getElementById('calendar');
	    var request = $.ajax({
	      url: '${root}/admin/bannerCalendarEvent'+typeUrl,
	      method: "GET",
	    });
	    request.done(function(data) {
	      var calendar = new FullCalendar.Calendar(calendarEl, {
	        locale: 'ko',
	        initialView: 'dayGridMonth',
	        events: data
	      });
	      calendar.render(); 
	    });
	  });
	</script>
</head>
<body style="display: flex;justify-content: center;align-items: center;">
	<div style="display: flex; flex-direction: column; ">

		<div id='calendar' style="width: 800px; color:"black";></div>
		<div style="margin-left:auto; margin-top:20px; ">
			<i class="bi bi-arrow-clockwise" id="resetButton" style="font-size: 35px; cursor:pointer;"></i>
		</div>
		
		<script>
		    document.addEventListener('DOMContentLoaded', function() {
		      var resetButton = document.getElementById('resetButton');
		
		      resetButton.addEventListener('click', function() { 
		        window.location.href = '${root}/admin/bannerCalendar'+typeUrl;
		      });
		    });
		</script>
	</div>
	
	<script>
		console.log(data);
	</script>
	
</body>
</html>