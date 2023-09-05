package ventanas;

import java.awt.EventQueue;

import java.util.Scanner;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
//hola
public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(61, 108, 301, 14);
		contentPane.add(progressBar);
		
		JLabel lblNewLabel = new JLabel("Nombre del Archivo:");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNewLabel.setBounds(61, 29, 110, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField.setBounds(182, 27, 180, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//EL BOTON DE CARGAR ARCHIVO GUARDA LAS LINEAS DE TEXTO
		JButton btnNewButton = new JButton("Abrir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameField= textField.getText();
				String[]cadenaText= new String[5];
				int cont=1;
				try {
					File field= new File(nameField+".txt");
					Scanner read= new Scanner(field);
					while(read.hasNextLine()) {
						String line= read.nextLine();
						cadenaText[cont-1]=line;
						int charger= (int) ((cont/5)*100);
						progressBar.setValue(charger);
						cont++;
					}read.close();
					
					if(progressBar.getValue()==100) {
						dispose();
						JOptionPane.showMessageDialog(null, "Cargado Exitosamente","Archivo Cargado",
								JOptionPane.INFORMATION_MESSAGE);
						MostarArchivo p= new MostarArchivo();
						p.setVisible(true);
					}
				//si no se encuentra el archivo, muestro el error y pido que vuelva a rellenar el espacio solicitado
				}catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Archivo no encontrado","Error",
							JOptionPane.ERROR_MESSAGE);
					textField.setText("");
					textField.requestFocus();
				}
				
			}
		});
		btnNewButton.setBounds(273, 58, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
