package com.mycompany.webapp.socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BatchServer {
	// 필드
	ServerSocket serverSocket;
	ExecutorService threadPool;

	// 서버 시작
	public void start() {
		log.info("[서버] 시작");
		threadPool = Executors.newFixedThreadPool(10);
	}
	
	public void addTask(Runnable runnable) {
		threadPool.execute(runnable);
	}
	
	public String sendMessage(String ip, int port, String path) {
		String response = null;
		
		try {
			Socket socket = new Socket(ip, port);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			dos.writeUTF(path);
			dos.flush();
			
			response = dis.readUTF();
			
			log.info(response);
			
			dos.close();
			dis.close();
		} catch (Exception e) {
			log.info("=====접속 실패=====");
			response = "{response: 서버연결 실패}";
			e.printStackTrace();
		} 
		
		return response;
	}
	
	public JSONObject receiveMessage(String message) {
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// * 서버 종료
	public void stop() {
		log.info("[서버] 종료");
		threadPool.shutdownNow();
	}
}
