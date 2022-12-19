<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

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
				
				
				$("#log-app-id").html(result[0]['appId'] + '번 프로그램 로그');
				let data = '';
				
				data += `
					<tr>
						<th>번호</th>
						<th>배치결과</th>
						<th>완료시간</th>
					</tr>
				`;
				
				for(var i=0; i<result.length; i++){
					data += `
						<tr>
							<td>` + result[i]['no'] + `</td>
							<td>` + result[i]['response'] + `</td>
							<td>` + new Date(result[i]['logDate']) + `</td>
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
	
	// 그룹 상세보기
	function groupDetail(td){
		const obj = $(td);
		const id = obj.attr("id");
		
		$.ajax({
			url: "/batch/group/detail?batchGroupId=" + id,
			method: "GET",
			success: function(result){
				$("#update-batchGroupId").val(id);
				$("#update-jobId").val(result['jobId']);
				$("#update-jobGroupId").val(result['jobGroupId']);
				$("#update-triggerId").val(result['triggerId']);
				$("#update-triggerGroupId").val(result['triggerGroupId']);
				$("#update-cron").val(result['cron']);
				$("#update-jobGroupName").val(result['jobGroupName']);
				$("#update-description").val(result['description']);
				$("#update-host").val(result['host']);
				$("#update-ip").val(result['ip']);
				$("#update-port").val(result['port']);
				$("#update-startDate").val(result['startDate']);
				$("#update-endDate").val(result['endDate']);
			}
		})
	}
	
	// 프로그램 상세보기
	function appDetail(td){
		const obj = $(td);
		const id = obj.attr("id");
		
		$.ajax({
			url: "/batch/app/detail?appId=" + id,
			method: "GET",
			success: function(result){
				$("#update-app-batchGroupId").val(result['batchGroupId']);
				$("#update-app-batchAppId").val(result['appId']);
				$("#update-app-appName").val(result['appName']);
				$("#update-app-path").val(result['path']);
			}
		})
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

<div class="card m-2">
	<div class="card-header">
		프로토타입
	</div>
	<div class="card-body">
		<section class="main">
			<h2 class="title">
				<span>배치 그룹</span>
				<div>
					<button class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#insert-batch-group">행추가</button>
					<button class="btn btn-success btn-sm" id="group-delete-btn">행삭제</button>
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
						<tr id="${group.batchGroupId}" class="group-tr">
							<td ><input id="batchGroupCheckBox" type="checkbox" value="${group.batchGroupId}"></td>
							<td>${group.batchGroupId}</td>
							<td onclick="groupDetail(this)" data-bs-toggle="modal" id="${group.batchGroupId}" 
								data-bs-target="#update-batch-group" style="cursor: pointer;">${group.jobGroupName}</td>
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
					<button class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#insert-batch-app">행추가</button>
					<button class="btn btn-success btn-sm" id="app-delete-btn">행삭제</button>
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
							<td id="${app.appId}" data-bs-toggle="modal" style="cursor: pointer;" 
								data-bs-target="#update-batch-app" onclick="appDetail(this)">${app.appName}</td>
							<td>${app.path}</td> 
							<td><button onclick="showLog(this)" data-bs-toggle="modal" data-bs-target="#batch-app-log">로그</button></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</section>
		<jsp:include page="/WEB-INF/views/modal/insertBatchGroup.jsp" /> <!-- 배치그룹 추가 모달창 -->
		<jsp:include page="/WEB-INF/views/modal/insertBatchApp.jsp" /> <!-- 배치프로그램 추가 모달창 -->
		<jsp:include page="/WEB-INF/views/modal/batchAppLog.jsp" /> <!-- 배치프로그램 로그 모달창 -->
		<jsp:include page="/WEB-INF/views/modal/updateBatchGroup.jsp" /> <!-- 배치그룹 수정 모달창 -->
		<jsp:include page="/WEB-INF/views/modal/updateBatchApp.jsp" /> <!-- 배치프로그램 수정 모달창 -->
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
