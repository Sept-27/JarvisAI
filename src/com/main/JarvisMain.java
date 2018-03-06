package com.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.apk.jarvisai.*;

public class JarvisMain {
	private static Socket socket;
	private static ServerSocket serverSocket;
	private static final int PORT = 25000;

	public static void main(String[] argv) {
		System.out.println("Hi, I am Jarvis");
		AILogic logic = new AILogic();
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("\nServer Started and listening to the port: " + PORT);

			while (true) {
				socket = serverSocket.accept();
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String command = br.readLine();
				System.out.println(command);
				logic.checkCommand(command);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				serverSocket.close();
			} catch (Exception e) {
			}
		}
	}

}
