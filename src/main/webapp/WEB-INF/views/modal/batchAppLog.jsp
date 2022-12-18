<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#batch-app-log #log-table tr th:nth-child(1) {
		width: 20%;
	}
	#batch-app-log #log-table tr th:nth-child(2) {
		width: 20%;
	}
	#batch-app-log #log-table tr th:nth-child(3) {
		width: 60%;
	}
	#batch-app-log.modal{
		
	    --bs-modal-width: 800px;
	}
	#batch-app-log .modal-content{
		margin-top: 200px;
	}
	#batch-app-log .modal-body{
		height: 400px;
    	overflow: auto;
	}
	#log-table{
	    width: 100%;
	    text-align: center;
	}
	#log-table tr, #log-table td, #log-table th{
		border: 1px solid gray;
	}
</style>
<div id="batch-app-log" class="modal" tabindex="-1">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 id="log-app-id" class="modal-title"></h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      	<table id="log-table" class="table table-striped">
	      	</table>
	      </div>
	    </div>
	  </div>
</div>