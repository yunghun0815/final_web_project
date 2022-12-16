package com.mycompany.webapp.service;

import java.util.List;

import com.mycompany.webapp.model.BatchAppVo;
import com.mycompany.webapp.model.BatchGroupVo;

public interface IBatchService {
	List<BatchGroupVo> getBatchGroupList();

	void insertBatchGroup(BatchGroupVo vo);

	void updateBatchGroup(BatchGroupVo vo);

	void deleteBatchGroup(int batchGroupId);

	List<BatchAppVo> getBatchAppList();

	void insertBatchApp(BatchAppVo vo);

	void updateBatchApp(BatchAppVo vo);

	void deleteBatchApp(int batchAppId);

	void deleteBatchAppByGroupId(int batchGroupId);

	List<BatchGroupVo> getBatchGroupByJobKey(String key);

	List<BatchAppVo> getBatchAppListByBatchGroupId(int batchGroupId);
	
}
