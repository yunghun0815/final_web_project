package com.mycompany.webapp.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.webapp.model.BatchGroupVo;
import com.mycompany.webapp.service.IBatchService;
import com.mycompany.webapp.service.IJobService;
import com.mycompany.webapp.socket.BatchServer;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppListener  implements ServletContextListener {
	@Autowired
	BatchServer batchServer;
	
	@Autowired
	IBatchService batchService;
	
	@Autowired
	IJobService jobService;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("웹 애플리케이션 실행");
		batchServer.start();
		
		List<BatchGroupVo> batchGroupVo = batchService.getBatchGroupList();
		
		for(BatchGroupVo result :  batchGroupVo) {
			//자동실행이 'Y' 이고 배치 프로그램이 등록된 그룹만 실행 
			if(result.getActive().equals("Y") && batchService.getBatchAppListByBatchGroupId(result.getBatchGroupId()).size() > 0) {
				jobService.addJob(result);
			}
		}	
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("웹 애플리케이션 종료");
		batchServer.stop();
	}
	
}
