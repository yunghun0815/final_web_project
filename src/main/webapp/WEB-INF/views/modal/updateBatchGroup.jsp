<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="update-batch-group" class="modal" tabindex="-1">
	<form action="/batch/group/update" method="post">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">배치그룹 수정</h5>
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
	      					<input id="update-batchGroupId" type="hidden" name="batchGroupId">
							<input id="update-jobId" type="text" name="jobId" readonly>	      					
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>
	      					<strong>jobGroupId</strong>
	      				</td>
	      				<td>
	      					<input id="update-jobGroupId" type="text" name="jobGroupId" readonly>		
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>
	      					<strong>triggerId</strong>
	      				</td>
	      				<td>
      			      		<input id="update-triggerId" type="text" name="triggerId" readonly>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>
	      					<strong>triggerGroupId</strong>
	      				</td>
	      				<td>
	      					<input id="update-triggerGroupId" type="text" name="triggerGroupId" readonly>
	      				</td>
	      			</tr>
					<tr>
						<td>
							<strong>cron</strong>
						</td>
						<td>
							<input id="update-cron" type="text" name="cron">							
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
	      					<input id="update-jobGroupName" type="text" name="jobGroupName">
	      				</td>
	      			</tr>  			
	      			<tr>
	      				<td>
	      					<strong>그룹설명</strong>
	      				</td>
	      				<td>
	      					<input id="update-description" type="text" name="description">
	      				</td>
	      			</tr>  			
	      			<tr>
	      				<td>
	      					<strong>호스트명</strong>
	      				</td>
	      				<td>
	      					<input id="update-host" type="text" name="host">
	      				</td>
	      			</tr>  			
	      			<tr>
	      				<td>
	      					<strong>아이피</strong>
	      				</td>
	      				<td>
	      					<input id="update-ip" type="text" name="ip">
	      				</td>
	      			</tr>  			
	      			<tr>
	      				<td>
	      					<strong>포트</strong>
	      				</td>
	      				<td>
	      					<input id="update-port" type="text" name="port">
	      				</td>
	      			</tr>  			
	      			<tr>
	      				<td>
	      					<strong>자동실행</strong>
	      				</td>
	      				<td>
	      					<select name="active">
	      						<option id="active-option-Y" value="Y">Y</option>
	      						<option id="active-option-N" value="N">N</option>
	      					</select>
	      				</td>
	      			</tr>  			
	      			<tr>
	      				<td>
	      					<strong>시작일</strong>
	      				</td>
	      				<td>
	      					<input id="update-startDate" type="date" name="startDate">
	      				</td>
	      			</tr>  			
	      			<tr>
	      				<td>
	      					<strong>종료일</strong>
	      				</td>
	      				<td>
	      					<input id="update-endDate" type="date" name="endDate">
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