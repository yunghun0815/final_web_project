package com.mycompany.webapp.scheduler;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.webapp.model.BatchGroupVo;
import com.mycompany.webapp.model.BatchLogVo;
import com.mycompany.webapp.service.IBatchService;
import com.mycompany.webapp.socket.BatchServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AgentJob implements Job {

	@Autowired
	IBatchService batchService;
	
	@Autowired
	BatchServer batchServer;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 잡키로 찾기 path 여러개 + 아이피 + 포트
		log.info("키" + context.getJobDetail().getKey());
		List<BatchGroupVo> batchGroupVo = batchService
				.getBatchGroupByJobKey(context.getJobDetail().getKey().toString());
		
		// 배치그룹에 등록된 앱 수만큼 실행
		for(BatchGroupVo vo : batchGroupVo) {
			log.info("");
			log.info("-----------------------------------");
			log.info(batchGroupVo.get(0).getIp() + ":" + batchGroupVo.get(0).getPort() + " 배치 프로그램 실행");
			String ip = vo.getIp();
			int port = vo.getPort();
			String path = vo.getPath();
			int appId = vo.getAppId();

			//Socket 통신 해 배치 프로그램 실행시킨 후 값 가져옴
			String receiveMessage = batchServer.sendMessage(ip, port, path);
			JSONObject json = new JSONObject(receiveMessage);
			
			//실행결과 로그에 저장
			BatchLogVo batchLogVo = new BatchLogVo();
			batchLogVo.setAppId(vo.getAppId());
			batchLogVo.setResponse(json.get("response").toString());
			long date = System.currentTimeMillis();
			batchLogVo.setLogDate(new Timestamp(date));
			batchService.insertBatchLog(batchLogVo);
			
			log.info("-----------------------------------");
		}
	}
}
