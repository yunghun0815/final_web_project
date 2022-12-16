package com.mycompany.webapp.socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

	public void sendMessage(String ip, int port, String path) {
		log.info("메시지 전송");
		log.info("ip : " + ip);
		log.info("port : " + port);
		log.info("path : " + path);
		
		try {
			Socket socket = new Socket(ip, port);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			dos.writeUTF(path);
			dos.flush();
			
			dos.close();
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	
	// * 서버 종료
	public void stop() {
		log.info("[서버] 종료");
		threadPool.shutdownNow();
	}
}
