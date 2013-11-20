package logic;

import java.util.ArrayList;

public class Song {
	
	private int duracion;
	private ArrayList <String> letter;
	private String name;
	
	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public ArrayList<String> getLetter() {
		return letter;
	}
	public void setLetter(ArrayList<String> letter) {
		this.letter = letter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
