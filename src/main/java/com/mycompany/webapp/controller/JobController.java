package com.mycompany.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.webapp.model.BatchGroupVo;
import com.mycompany.webapp.service.IJobService;

@Controller
public class JobController {
	
	@Autowired
	IJobService jobService;

	/** 
	 * Scheudler 시작
	 */
	@ResponseBody
	@PostMapping("/scheduler/start")
	public void startJob(){
		jobService.startSchedule();
	}	
	
	/** 
	 * Scheudler 종료
	 */
	@ResponseBody
	@PostMapping("/scheduler/shutdown")
	public void shutdownScheduler(){
		jobService.shutdownSchedule();
	}	
	
	/** 
	 * JobKey를 통해 정지된 Job을 재시작 하는 컨트롤러
	 * @param jobId
	 * @param jobGroupId
	 */
	@ResponseBody
	@PostMapping("/job/start")
	public void startJob(BatchGroupVo vo){
		
		jobService.startJob(vo);
	}
	
	/** 
	 * JobKey를 통해 해당 Job을 정지시키는 컨트롤러
	 * @param jobId
	 * @param jobGroupId
	 */
	@ResponseBody
	@PostMapping("/job/pause")
	public void pauseJob(BatchGroupVo vo){
		jobService.pauseJob(vo);
	}
	
	/** 
	 * 등록된 그룹아이디로 Job을 실행하는 컨트롤러
	 * @param batchGroupId
	 */
	@ResponseBody
	@PostMapping("/job/add")
	public void addJob(int batchGroupId){
		
		jobService.addJob(batchGroupId);
		
	}

	/** 
	 * Job을 삭제하는 컨트롤러
	 * @param batchGroupId
	 */
	@ResponseBody
	@PostMapping("/job/remove")
	public void removeJob(int batchGroupId){
		jobService.removeJob(batchGroupId);
	}

//	/** 
//	 * Trigger의 cron을 수정하는 컨트롤러
//	 * @param triggerId
//	 * @param triggerGroupId
//	 * @param cron
//	 */
//	@ResponseBody
//	@PostMapping("/job/update")
//	public void updateJob(BatchGroupVo vo){
//		jobService.updateJob(vo);
//	}

}
