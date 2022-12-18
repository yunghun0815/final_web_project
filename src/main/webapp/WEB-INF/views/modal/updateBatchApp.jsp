<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="update-batch-app" class="modal" tabindex="-1">
	<form action="/batch/app/update" method="post">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">배치프로그램 수정</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      		<table style="width: 100%;">
	      			<tr>
	      				<td>
	      					<strong>배치그룹</strong>
	      				</td>
	      				<td>
	      					<input id="update-app-batchGroupId" type="text" readonly>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>
	      					<strong>프로그램ID</strong>
	      				</td>
	      				<td>
	      					<input id="update-app-batchAppId" type="text" name="appId" readonly>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>
	      					<strong>프로그램명</strong>
	      				</td>
	      				<td>
							<input id="update-app-appName" type="text" name="appName">	      					
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>
	      					<strong>실행경로</strong>
	      				</td>
	      				<td>
	      					<input id="update-app-path" type="text" name="path">		
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