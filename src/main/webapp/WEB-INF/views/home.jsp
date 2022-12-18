<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.2.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" ></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>배치 프로그램</title>
</head>
<style>
	body{
		padding: 100px 0;
	}
	.main{
		width: 1200px;
		margin: 0 auto;
	}
	.main table{
		width: 100%;
	}
	.main table, .main tr, .main td, .main th{
		border: 1px solid #dfdfdf;
		text-align: center;
	}
	.main h2{
		display: flex;
		justify-content: space-between;
	}
	.batch-group{
		min-height: 300px;
		border: 1px solid gray;
	}
	.batch-app{
		min-height: 200px;
		border: 1px solid gray;
	}
	.title{
		margin-top: 20px;
	}
	.title>span{
		font-size: 16px;
	    font-weight: bold;
	    color: #126386;
	}
	.title button{
		background-color: white;
	    font-size: 13px;
	    border: 2px solid #126386;
	    color: #126386;
	    font-weight: 600;
	    border-radius: 5px;
	    width: 60px;
	    height: 25px;
	}
	.group-tr, .app-tr{
		cursor: pointer;
	}
</style>
<script type="text/javascript">
	const startBtn = `<button onclick="batchStart(this)">실행</button>`;
	const stopBtn = `<button onclick="batchStop(this)">중지</button>`;
	
	function batchStart(btn){
		
		const obj = $(btn);
		const id = obj.closest(".group-tr").attr("id");

	  	 $.ajax({
			url: "/job/add",
			method: "POST",
			data: {
				batchGroupId: id 
			},
			success: function(result){
				//실행여부 바꾸기
				obj.closest(".group-tr").find(".fire").html('Y');
				//버튼 바꾸기
				obj.parent().html(stopBtn);
			},
			error: function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});   
	}
	
	function batchStop(btn){
		const obj = $(btn);
		const id = obj.closest(".group-tr").attr("id");
	 	 $.ajax({
			url: "/job/remove",
			method: "POST",
			data: {
				batchGroupId: id 
			},
			success: function(result){
				//실행여부 바꾸기
				obj.closest(".group-tr").find(".fire").html('N');
				//버튼 바꾸기
				obj.parent().html(startBtn);
			},
			error: function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});  
	}
	
	function showLog(btn){
		const obj = $(btn);
		const id = obj.closest(".app-tr").attr("id");
		$.ajax({
			url: "/batch/log/show?appId=" + id,
			method: "GET",
			success: function(result){
				let data = '';
				
				data += `
					<tr>
						<th>프로그램ID</th>
						<th>배치결과</th>
						<th>완료시간</th>
					</tr>
				`;
				
				for(var i=0; i<result.length; i++){
					data += `
						<tr>
							<td>` + result[i]['appId'] + `</td>
							<td>` + result[i]['response'] + `</td>
							<td>` + result[i]['logDate'] + `</td>
						</tr>
					`;
				}
				$("#log-table").html(data);
			},
			error: function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			} 
		});
		
	}
	
	function groupDetail(tr){
		const obj = $(tr);
		const id = obj.attr("id");
		
	}
	$(function(){
		//그룹 행 삭제
		$("#group-delete-btn").click(function(){
			$("#batchGroupCheckBox:checked").each(function(idx){
				const id = $(this).val();
				$.ajax({
					url: "/batch/group/delete",
					method: "POST",
					data: {
						batchGroupId: id
					},
					success: function(result){
						location.reload();
					}
				});
			});
		});
		
		//프로그램 행 삭제
		$("#app-delete-btn").click(function(){
			$("#batchAppCheckBox:checked").each(function(idx){
				const id = $(this).val();
				$.ajax({
					url: "/batch/app/delete",
					method: "POST",
					data: {
						appId: id
					},
					success: function(result){
						location.reload();
					}
				});
			});
		});
	});
</script>
<body>
	<section class="main">
		<h2 class="title">
			<span>배치 그룹</span>
			<div>
				<button data-bs-toggle="modal" data-bs-target="#insert-batch-group">행추가</button>
				<button id="group-delete-btn">행삭제</button>
			</div>
		</h2>
		<div class="batch-group">
			<table class="table table-striped">
				<tr>
					<th></th>
					<th>그룹ID</th>
					<th>그룹명</th>
					<th>Cron값</th>
					<th>그룹설명</th>
					<th>Host 명</th>
					<th>Host IP</th>
					<th>Port</th>
					<th>자동실행</th>
					<th>시작일자</th>
					<th>종료일자</th>
					<th>실행여부</th>
					<th></th>					
				</tr>
				<c:forEach items="${batchGroupList}" var="group">
					<tr id="${group.batchGroupId}" class="group-tr" onclick="groupDetail(this)">
						<td><input id="batchGroupCheckBox" type="checkbox" value="${group.batchGroupId}"></td>
						<td>${group.batchGroupId}</td>
						<td>${group.jobGroupName}</td>
						<td>${group.cron}</td>
						<td>${group.description}</td>
						<td>${group.host}</td>
						<td>${group.ip}</td>
						<td>${group.port}</td>
						<td>${group.active}</td>
						<td>${group.startDate}</td>
						<td>${group.endDate}</td>
						<td class="fire">${group.fire}</td>
						<td><c:if test="${group.fire == 'Y'}">
								<button onclick="batchStop(this)">중지</button>
							</c:if> <c:if test="${group.fire == 'N'}">
								<button onclick="batchStart(this)">실행</button>
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<h2 class="title">
			<span>배치 프로그램</span>
			<div>
				<button data-bs-toggle="modal" data-bs-target="#insert-batch-app">행추가</button>
				<button id="app-delete-btn">행삭제</button>
			</div>
		</h2>
		<div class="batch-app">
			<table class="table table-striped">
				<tr>
					<th></th>
					<th>그룹ID</th>
					<th>프로그램ID</th>
					<th>프로그램명</th>
					<th>실행경로</th>
					<th></th>
				</tr>
				<c:forEach items="${batchAppList}" var="app">
					<tr id="${app.appId}" class="app-tr">
						<td><input id="batchAppCheckBox" type="checkbox" value="${app.appId}"></td>
						<td>${app.batchGroupId}</td>
						<td>${app.appId}</td>
						<td>${app.appName}</td>
						<td>${app.path}</td> 
						<td><button onclick="showLog(this)" data-bs-toggle="modal" data-bs-target="#batch-app-log">로그</button></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>
	<jsp:include page="modal/insertBatchGroup.jsp" /> <!-- 배치그룹 추가 모달창 -->
	<jsp:include page="modal/insertBatchApp.jsp" /> <!-- 배치프로그램 추가 모달창 -->
	<jsp:include page="modal/batchAppLog.jsp" /> <!-- 배치프로그램 로그 모달창 -->
</body>
</html>