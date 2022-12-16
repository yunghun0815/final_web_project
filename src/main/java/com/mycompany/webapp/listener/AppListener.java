package com.mycompany.webapp.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.webapp.dao.BatchDao;
import com.mycompany.webapp.model.BatchGroupVo;
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
	BatchDao batchDao;
	
	@Autowired
	IJobService jobService;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("웹 애플리케이션 실행");
		batchServer.start();
		
		List<BatchGroupVo> batchGroupVo = batchDao.getBatchGroupList();
		
		for(BatchGroupVo result :  batchGroupVo) {
			
			jobService.addJob(result);
		}	
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("웹 애플리케이션 종료");
		batchServer.stop();
	}
	
}
