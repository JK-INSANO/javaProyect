package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel PanelLogin;
	private JTextField UserField;
	private JPasswordField passwordField;
	private JButton btnIngresar;
	private ArrayList<Usuario>listaUsuarios;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		listaUsuarios = new ArrayList<Usuario>();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagenes/elitrasLogo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 575);
		PanelLogin = new JPanel();
		PanelLogin.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(PanelLogin);
		PanelLogin.setLayout(null);
		
		btnIngresar = new JButton("Entrar");
		btnIngresar.setForeground(new Color(0, 0, 0));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    leerUsuarios();
			    String nombreUsuario = UserField.getText();
			    char[] clave = passwordField.getPassword();
			    String claveFinal = new String(clave);

			    if (autenticarUsuario(nombreUsuario, claveFinal)) {
			        JOptionPane.showMessageDialog(null, "Bienvenido", "Acceso Válido",
			                JOptionPane.INFORMATION_MESSAGE);

			        // Crear y mostrar el nuevo panel después de iniciar sesión
			        Principal p1 = new Principal();
			        p1.setSize(470, 536);
			        p1.setLocation(0, 0);
			        PanelLogin.removeAll();
			        PanelLogin.add(p1, BorderLayout.CENTER);
			        PanelLogin.revalidate();
			        PanelLogin.repaint();
			    } else {
			        JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrecta", "Error",
			                JOptionPane.ERROR_MESSAGE);
			        UserField.setText("");
			        passwordField.setText("");
			        UserField.requestFocus();
			    }
			}
		});
		btnIngresar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnIngresar.setBackground(new Color(128, 128, 255));
		btnIngresar.setBounds(199, 376, 89, 23);
		PanelLogin.add(btnIngresar);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		passwordField.setBounds(162, 324, 181, 20);
		PanelLogin.add(passwordField);
		
		UserField = new JTextField();
		UserField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		UserField.setBounds(162, 290, 181, 20);
		PanelLogin.add(UserField);
		UserField.setColumns(10);
		
		JLabel textoContraseña = new JLabel("Contraseña:");
		textoContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		textoContraseña.setForeground(Color.WHITE);
		textoContraseña.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textoContraseña.setBounds(68, 325, 89, 14);
		PanelLogin.add(textoContraseña);
		
		JLabel lblNewLabel_1_1 = new JLabel("Usuario:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(80, 291, 89, 14);
		PanelLogin.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("¡Bienvenido!");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(182, 201, 106, 14);
		PanelLogin.add(lblNewLabel_1);
		
		JLabel jLabelPerfil = new JLabel("");
		jLabelPerfil.setIcon(new ImageIcon(Login.class.getResource("/imagenes/perfil.png")));
		jLabelPerfil.setBounds(162, 56, 151, 134);
		PanelLogin.add(jLabelPerfil);
		
		JLabel jLabelFondo = new JLabel("");
		jLabelFondo.setBounds(0, 0, 470, 536);
		jLabelFondo.setIcon(new ImageIcon(Login.class.getResource("/imagenes/fondoa.png")));
		PanelLogin.add(jLabelFondo);
	}
	
	private void leerUsuarios() {
		try {
			Scanner read= new Scanner(new File("usuarios.txt"));
			while(read.hasNextLine()) {
				String line= read.nextLine();
				String[]parts= line.split(",");
				String name= parts[0];
				String pasword= parts[1];
				Usuario user= new Usuario(name,pasword);
				listaUsuarios.add(user);
			}read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean autenticarUsuario(String nombreUsuario, String contraseña) {
	    for (Usuario user : listaUsuarios) {
	        if (nombreUsuario.equalsIgnoreCase(user.getNombre()) && contraseña.equals(user.getContraseña())) {
	            return true; // Autenticación exitosa
	        }
	    }
	    return false; // Autenticación fallida
	}
}
