<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="insert-batch-group" class="modal" tabindex="-1">
	<form action="/batch/group/posts" method="post">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">배치그룹 추가</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      		<table style="width: 100%;">
	      			<tr>
	      				<td colspan="2">
	      					<h3>QUARTZ 설정</h3>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>
	      					<strong>jobId</strong>
	      				</td>
	      				<td>
							<input type="text" name="jobId">	      					
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>
	      					<strong>jobGroupId</strong>
	      				</td>
	      				<td>
	      					<input type="text" name="jobGroupId">		
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>
	      					<strong>triggerId</strong>
	      				</td>
	      				<td>
      			      		<input type="text" name="triggerId">
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>
	      					<strong>triggerGroupId</strong>
	      				</td>
	      				<td>
	      					<input type="text" name="triggerGroupId">
	      				</td>
	      			</tr>
					<tr>
						<td>
							<strong>cron</strong>
						</td>
						<td>
							<input type="text" name="cron">							
						</td>
					</tr>	
					<tr>
	      				<td colspan="2">
	      					<h3 style="margin-top:30px;">배치그룹 설정</h3>
	      				</td>
	      			</tr>    
	      			<tr>
	      				<td>
	      					<strong>그룹명</strong>
	      				</td>
	      				<td>
	      					<input type="text" name="jobGroupName">
	      				</td>
	      			</tr>  			
	      			<tr>
	      				<td>
	      					<strong>그룹설명</strong>
	      				</td>
	      				<td>
	      					<input type="text" name="description">
	      				</td>
	      			</tr>  			
	      			<tr>
	      				<td>
	      					<strong>호스트명</strong>
	      				</td>
	      				<td>
	      					<input type="text" name="host">
	      				</td>
	      			</tr>  			
	      			<tr>
	      				<td>
	      					<strong>아이피</strong>
	      				</td>
	      				<td>
	      					<input type="text" name="ip">
	      				</td>
	      			</tr>  			
	      			<tr>
	      				<td>
	      					<strong>포트</strong>
	      				</td>
	      				<td>
	      					<input type="text" name="port">
	      				</td>
	      			</tr>  			
	      			<tr>
	      				<td>
	      					<strong>시작일</strong>
	      				</td>
	      				<td>
	      					<input type="date" name="startDate">
	      				</td>
	      			</tr>  			
	      			<tr>
	      				<td>
	      					<strong>종료일</strong>
	      				</td>
	      				<td>
	      					<input type="date" name="endDate">
	      				</td>
	      			</tr>  			
	      		</table>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
	        <button type="submit" class="btn btn-primary">저장</button>
	      </div>
	    </div>
	  </div>
  </form>
</div>