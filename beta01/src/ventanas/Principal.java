package ventanas;
import javax.swing.*;

import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Principal extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textField;
    private JProgressBar progressBar;
    private JButton btnCancelar; // Botón Cancelar
    private SwingWorker<Void, Integer> worker; // Referencia al SwingWorker

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

    public Principal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 185);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setBounds(61, 92, 301, 14);
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

        JButton btnNewButton = new JButton("Abrir");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameField = textField.getText();
                cargarArchivoEnSegundoPlano(nameField);
            }
        });
        btnNewButton.setBounds(273, 58, 89, 23);
        contentPane.add(btnNewButton);

        btnCancelar = new JButton("Cancelar"); // Inicialmente invisible
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (worker != null && !worker.isDone()) {
                    worker.cancel(true); // Cancelar el trabajo en segundo plano
                    progressBar.setValue(0); // Restablecer la barra de progreso
                    btnCancelar.setVisible(false); // Ocultar el botón Cancelar
                }
            }
        });
        btnCancelar.setBounds(168, 112, 89, 23);
        contentPane.add(btnCancelar);
        btnCancelar.setVisible(false); // Inicialmente oculto
    }

    private void cargarArchivoEnSegundoPlano(String fileName) {
        worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                btnCancelar.setVisible(true); // Mostrar el botón Cancelar al comenzar el proceso
                int blockSize = 4096; // Tamaño del bloque en bytes

                File field = new File(fileName + ".txt");
                long fileSize = field.length();
                FileInputStream fileInputStream = new FileInputStream(field);
                byte[] buffer = new byte[blockSize];
                int bytesRead;
                long totalBytesRead = 0;

                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    if (isCancelled()) {
                        break; // Salir del ciclo si se solicita la cancelación
                    }

                    totalBytesRead += bytesRead;
                    int charger = (int) ((totalBytesRead * 100) / fileSize);
                    publish(charger);
                }

                fileInputStream.close();
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                progressBar.setValue(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                dispose();
                btnCancelar.setVisible(false); // Ocultar el botón Cancelar al finalizar el proceso
                if (isCancelled()) {
                    JOptionPane.showMessageDialog(null, "Carga Cancelada", "",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cargado Exitosamente", "",
                            JOptionPane.INFORMATION_MESSAGE);
                    MostarArchivo p = new MostarArchivo();
                    p.setVisible(true);
                }
            }
        };

        worker.execute(); // Iniciar el trabajo en segundo plano
    }
}
