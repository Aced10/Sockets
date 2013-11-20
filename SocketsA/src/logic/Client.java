package logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import logic.Server;

public class Client implements Runnable{

	private Socket connection;
	private DataInputStream inputChannel;
	private DataOutputStream outputChannel;

	private boolean pause;
	private boolean stop;
	private Thread thread;
	private int option;
	
	/**
	 * 
	 * Constructor de la clase Conexion.java.
	 */
	public Client() {

		try {
			this.connection = new Socket(Server.NAME, Server.PUERTO);
		} catch (UnknownHostException e1) {
			System.out
			.println("Error #1 al crear la conexion con el servidor.");
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out
			.println("Error #1 al crear la conexion con el servidor.");
			e1.printStackTrace();
		}

		try {
			inputChannel = new DataInputStream(connection.getInputStream());
		} catch (IOException e) {
			System.out
			.println("Error al crear canal de entrada en el servidor.");
			e.printStackTrace();
		}

		try {
			outputChannel = new DataOutputStream(connection.getOutputStream());
		} catch (IOException e) {
			System.out.println("Error al crear canal de salida en el servidor.");
			e.printStackTrace();
		}
		//github
		pause = false;
		stop = false;
		thread = new Thread(this);
		start();
		sendOption(2);
		sendMessage("Hola servidor");
	}

	public void sendMessage(String message) {

		try {
			outputChannel.writeUTF(message);
		} catch (IOException e) {
			System.out
			.println("Error al enviar un mensaje por el canal de salida del servidor.");
			e.printStackTrace();
		}
	}

	public void sendOption(int option) {

		try {
			outputChannel.writeInt(option);
		} catch (IOException e) {
			System.out
			.println("Error al enviar un comando u opcion por el canal de salida del servidor.");
			e.printStackTrace();
		}
	}

	public String receiveMessage() {

		try {
			return inputChannel.readUTF();
		} catch (IOException e) {
			System.out
			.println("Error al recibir un mensaje por canal de salida del servidor.");
			e.printStackTrace();
		}

		return "Error al leer el mensaje.";
	}

	public void closeConnection() {
		try {
			inputChannel.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar el canal de entrada.");
			e.printStackTrace();
		}
		try {
			outputChannel.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar el canal de entrada.");
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar el canal de entrada.");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (!stop) {

			try {
				option = inputChannel.readInt();
			} catch (IOException e) {
				System.out.println("Error al recibir comando" + e.getMessage());
			}

			switch (option) {

				case 1:
					System.out.println(receiveMessage());
					break;
				
				default:
					
					break;
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

	

	/**
	 * Metodo que retorna el atributo pause de tipo boolean.
	 * 
	 * @return the pause
	 */
	public boolean isPause() {
		return this.pause;
	}

	/**
	 * Metodo que asigna el valor pause a attributo Conexion.java.
	 * 
	 * @param pause
	 */
	public void setPause(boolean pause) {
		this.pause = pause;
	}

	/**
	 * Metodo que retorna el atributo stop de tipo boolean.
	 * 
	 * @return the stop
	 */
	public boolean isStop() {
		return this.stop;
	}

	/**
	 * Metodo que asigna el valor stop a attributo Conexion.java.
	 * 
	 * @param stop
	 */
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	/**
	 * Metodo que retorna el atributo thread de tipo Thread.
	 * 
	 * @return the thread
	 */
	public Thread getThread() {
		return this.thread;
	}

	/**
	 * Metodo que asigna el valor thread a attributo Conexion.java.
	 * 
	 * @param thread
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}
}
