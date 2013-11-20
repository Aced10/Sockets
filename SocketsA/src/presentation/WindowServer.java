package presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logic.Concert;
      

public class WindowServer extends JFrame{

	private Event event;//Esta es una instancia de la clase privada Event y la usaremos para darle eventos a los botones
	private JPanel pButtons;//Este panel lo utilizamos para organizar los diferentes conponentes de la ventana
	private JButton btnInitServer;//Boton para iniciar servidor
	private JButton btnAddConcert;//Boton para agregar concierto
	private JButton btnEstatistics;//Boton para ver estadisticas
	
	public WindowServer(){
			
		
		super("SERVIDOR CONCIERTOS");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(2,2));
		setLocation(new Point(200,20));
		setResizable(false);	
		setSize(600, 700);
		
		event = new Event();
		pButtons = new JPanel();
		btnInitServer = new JButton("Iniciar Servidor");
		btnAddConcert = new JButton("Agregar Concierto");
		btnEstatistics = new JButton("Recomendar Unica");
		
		btnInitServer.setActionCommand("Init");
		btnInitServer.addActionListener(event);
		btnAddConcert.setActionCommand("RecommendGender");
		btnAddConcert.addActionListener(event);
		btnEstatistics.setActionCommand("RecommendOnly");
		btnEstatistics.addActionListener(event);
		
		pButtons.setLayout(new GridLayout(7,1));;
		pButtons.setBorder(BorderFactory.createTitledBorder("OPCIONES"));
		
		pButtons.add(btnAddConcert);
		pButtons.add(btnInitServer);
		pButtons.add(btnEstatistics);
		
		add(pButtons, BorderLayout.WEST);
		
		setVisible(true);
	}
	
	private class Event implements ActionListener{

		/**
		 * Este es el un constructor sin parametros de la clase Event, en el no hacemos uso de los atributos de la
		 * clase ya que lo usaremos posteriormente para crear instancias de nuestra clase.
		 */
		public Event(){
			super();
		}
		
		/**
		 * @author Alfonso Calero Espitia
		 * Esta es el metodo implementado de para definir cada uno de los eventos de los diferentes botones.
		 */
		public void actionPerformed(ActionEvent actionEvent) {
			if(actionEvent.getActionCommand().equals(btnInitServer.getActionCommand())){
				JOptionPane.showMessageDialog(null, "opcion iniciar servidor");
			}
			if(actionEvent.getActionCommand().equals(btnAddConcert.getActionCommand())){
				JOptionPane.showMessageDialog(null, "opcion agregar concierto");
			}
			if(actionEvent.getActionCommand().equals(btnEstatistics.getActionCommand())){
				JOptionPane.showMessageDialog(null, "Estadisticas");
			}
		}
		
	}
}
