package com.mycompany.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.webapp.model.BatchAppVo;
import com.mycompany.webapp.model.BatchGroupVo;
import com.mycompany.webapp.service.IBatchService;
import com.mycompany.webapp.service.IJobService;

@RequestMapping("/batch")
@Controller
public class BatchController {
	private static String response = "success";
	
	@Autowired
	IBatchService batchService;
	
	@Autowired
	IJobService jobService;
	
	@ResponseBody
	@GetMapping("/group/list")
	public List<BatchGroupVo> getBatchGroupList() {
		
		return batchService.getBatchGroupList(); 
	}
	/**
	 * 배치그룹 등록 및 jobStore에 job 등록하는 컨트롤러
	 * @param jobId  잡 아이디
	 * @param jobGroupId 잡 그룹명
	 * @param triggerId 트리거 아이디
	 * @param triggerGroupId 트리거 그룹 아이디
	 * @param jobGroupName 배치 그룹의 이름
	 * @param cron 크론으로 주기 설정
	 * @param description 배치 그룹 설명
	 * @param host 호스트 이름
	 * @param ip 배치 프로그램 아이피
	 * @param port 배치 프로그램 포트
	 * @param startDate 시작일자
	 * @param endDate 종료일자
	 */
	@ResponseBody
	@PostMapping("/group/posts")
	public void insertBatchGroup(BatchGroupVo vo) {
		
		//배치그룹 등록
		batchService.insertBatchGroup(vo);
		//jobStore에 job 등록
		jobService.addJob(vo);
	}
	
	/**
	 * 배치 그룹 수정 및 등록된 트리거 수정하는 컨트롤러
	 * @param batchGroupId 수정할 배치그룹 아이디
	 * @param jobId  잡 아이디
	 * @param jobGroupId 잡 그룹명
	 * @param triggerId 트리거 아이디
	 * @param triggerGroupId 트리거 그룹 아이디
	 * @param jobGroupName 배치 그룹의 이름
	 * @param cron 크론으로 주기 설정
	 * @param description 배치 그룹 설명
	 * @param host 호스트 이름
	 * @param ip 배치 프로그램 아이피
	 * @param port 배치 프로그램 포트
	 * @param startDate 시작일자
	 * @param active 자동실행 여부(Y/N)
	 * @param endDate 종료일자
	 */
	@ResponseBody
	@PostMapping("/group/update")
	public String updateBatchGroup(BatchGroupVo vo) {
		try {
			//배치그룹 수정
			batchService.updateBatchGroup(vo);
			//jobStore에 등록된 트리거 수정
			jobService.updateJob(vo);			
		}catch(Exception e) {
			response = e.getMessage();
			
		}
		return response;
	}
	/**
	 * 배치그룹 + 배치프로그램 + 등록된 잡과 트리거 삭제하는 컨트롤러
	 * @param batchGroupId 배치그룹 아이디
	 * @param jobId 잡 아이디
	 * @param jobGroupId 잡 그룹 아이디
	 * @param triggerId 트리거 아이디
	 * @param triggerGroupId 트리거 그룹 아이디
	 * */
	@ResponseBody
	@PostMapping("/group/delete")
	public String deleteBatchGroup(BatchGroupVo vo) {
		try {
			//배치 앱 삭제
			batchService.deleteBatchAppByGroupId(vo.getBatchGroupId());
			//배치그룹 삭제
			batchService.deleteBatchGroup(vo.getBatchGroupId());
			//jobStore에 등록된 잡, 트리거 정지 및 삭제
			jobService.removeJob(vo);
		}catch(Exception e) {
			response = e.getMessage();
		}
		
		return response;
	}
	/**
	 *	등록된 배치 프로그램 전체 조회하는 컨트롤러 
	 */
	@ResponseBody
	@GetMapping("/app/list")
	public List<BatchAppVo> getBatchAppList() {
		
		return batchService.getBatchAppList(); 
	}
	
	/**
	 * 배치 프로그램 등록하는 컨트롤러
	 * @param 
	 */
	@ResponseBody
	@PostMapping("/app/posts")
	public String insertBatchApp(BatchAppVo vo) {
		//배치 앱 등록
		try {
			batchService.insertBatchApp(vo);
		}catch(Exception e) {
			response = e.getMessage();
		}
		return response;
	}
	/**
	 * 배치 프로그램 수정하는 컨트롤러 
	 * @param appId 
	 * @param appName
	 * @param path
	 * @param executionOrder
	 */
	@ResponseBody
	@PostMapping("/app/update")
	public String updateBatchApp(BatchAppVo vo) {
		
		//배치 앱 수정
		try {
			batchService.updateBatchApp(vo);			
		}catch(Exception e) {
			response = e.getMessage();
		}
		return response;
	}
	/**
	 * 배치 프로그램 삭제하는 컨트롤러 
	 * @param batchAppId 
	 */	
	@ResponseBody
	@PostMapping("/app/delete")
	public String deleteBatchApp(BatchAppVo vo) {
		try {
			batchService.deleteBatchApp(vo.getAppId());
		}catch(Exception e) {
			response = e.getMessage();
		}
		return response;
	}
}
