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
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField UserField;
	private JPasswordField passwordField;
	private JButton btnIngresar;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagenes/elitrasLogo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnIngresar = new JButton("Entrar");
		btnIngresar.setForeground(new Color(0, 0, 0));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[]clave= passwordField.getPassword();
				String claveFinal= new String(clave);
				
				if(UserField.getText().equalsIgnoreCase("perkin") && claveFinal.equals("123")) {
					dispose();
					JOptionPane.showMessageDialog(null, "Bienvenido","Acceso Válido",
							JOptionPane.INFORMATION_MESSAGE);
					Principal p= new Principal();
					p.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrecta","Error",
							JOptionPane.ERROR_MESSAGE);
					UserField.setText("");
					passwordField.setText("");
					UserField.requestFocus();
				}
			}
		});
		btnIngresar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnIngresar.setBackground(new Color(128, 128, 255));
		btnIngresar.setBounds(141, 376, 89, 23);
		contentPane.add(btnIngresar);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		passwordField.setBounds(104, 324, 181, 20);
		contentPane.add(passwordField);
		
		UserField = new JTextField();
		UserField.setFont(new Font("SansSerif", Font.PLAIN, 12));
		UserField.setBounds(104, 290, 181, 20);
		contentPane.add(UserField);
		UserField.setColumns(10);
		
		JLabel textoContraseña = new JLabel("Contraseña:");
		textoContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		textoContraseña.setForeground(Color.WHITE);
		textoContraseña.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textoContraseña.setBounds(10, 325, 89, 14);
		contentPane.add(textoContraseña);
		
		JLabel lblNewLabel_1_1 = new JLabel("Usuario:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(22, 291, 89, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("¡Bienvenido!");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(124, 201, 106, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel jLabelPerfil = new JLabel("");
		jLabelPerfil.setIcon(new ImageIcon(Login.class.getResource("/imagenes/perfil.png")));
		jLabelPerfil.setBounds(104, 56, 151, 134);
		contentPane.add(jLabelPerfil);
		
		JLabel jLabelFondo = new JLabel("");
		jLabelFondo.setBounds(0, 0, 384, 536);
		jLabelFondo.setIcon(new ImageIcon("C:\\Eclipse Proyecto\\eclipse-workspace\\beta01\\src\\imagenes\\fondoa.png"));
		contentPane.add(jLabelFondo);
	}
}
