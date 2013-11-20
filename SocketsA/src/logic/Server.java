package logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import logic.Connection;

public class Server implements Runnable{
	
	private static ServerSocket server;
	public static final int PUERTO = 3456;
	public static final String NAME = "localhost";
	private ArrayList<Connection> connectionList;
	private Socket incomingConnection;

	private boolean pause;
	private boolean stop;
	private Thread thread;
	
	/**
	 * 
	 * Constructor de la clase Servidor.java.
	 */
	public Server() {
		initServer();
		connectionList = new ArrayList<>();
		pause = false;
		stop = false;
		thread = new Thread(this);
		start();
	}

	public void initServer() {
		if (server == null) {
			try {
				server = new ServerSocket(PUERTO);
			} catch (IOException e) {
				System.out.println("Error al crear el servidor.");
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void run() {

		while (!stop) {
			System.out.println("S: esperando conexion...");
			try {
				incomingConnection = server.accept();
				System.out.println("Nueva conexion establecida");
				connectionList.add(new Connection(incomingConnection));
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			synchronized (this) {
				while (pause)
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				if (stop)
					break;
			}
		}
	}

	public void closeConnection() {
		System.out.println("Se ha cerrado la conexion del servidor.");
		stop();
	}


	public void start() {
		thread.start();
	}

	synchronized void stop() {
		stop = true;
		pause = false;
		notify();
	}

	synchronized void suspend() {
		pause = true;
	}

	synchronized void resume() {
		pause = false;
		notify();
	}

}
