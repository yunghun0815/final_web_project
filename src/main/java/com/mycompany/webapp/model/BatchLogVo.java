package com.mycompany.webapp.model;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BatchLogVo {
	private int no;
	private int appId;
	private String response;
	private Timestamp logDate;
}
