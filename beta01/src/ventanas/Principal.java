package ventanas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Principal extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
    private JProgressBar progressBar;
    private JButton btnCancelar; // Botón Cancelar
    private SwingWorker<Void, Integer> worker; // Referencia al SwingWorker

    public Principal() {
        setLayout(null);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setBounds(90, 292, 301, 14);
        add(progressBar);

        JLabel lblNewLabel = new JLabel("Nombre del Archivo:");
        lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblNewLabel.setBounds(90, 190, 110, 14);
        add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 12));
        textField.setBounds(210, 187, 180, 20);
        add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Abrir");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameField = textField.getText();
                if(nameField=="cordenadas") {
                	CruzMilenioPanel p2= new CruzMilenioPanel();
                	loadPage(p2);
                }else {
                	cargarArchivoEnSegundoPlano(nameField);
                }
            }
        });
        btnNewButton.setBounds(197, 218, 89, 23);
        add(btnNewButton);

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
        btnCancelar.setBounds(197, 312, 89, 23);
        add(btnCancelar);
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
                // No es necesario llamar a dispose() aquí
                btnCancelar.setVisible(false); // Ocultar el botón Cancelar al finalizar el proceso
                if (isCancelled()) {
                    JOptionPane.showMessageDialog(null, "Carga Cancelada", "",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cargado Exitosamente", "",
                            JOptionPane.INFORMATION_MESSAGE);
                    // Aquí puedes realizar alguna acción o notificar al JFrame que el proceso ha terminado.
                    String nameField = textField.getText();
                    if(nameField.equalsIgnoreCase("cordenadas")) {
                    	CruzMilenioPanel p2= new CruzMilenioPanel();
                    	loadPage(p2);
                    }else if(nameField.equalsIgnoreCase("nodes")){
                    	//CARGAR EL ARCHIVO DE NODOS-----------------------------------------------------------------------
                    	/*
                    	 ----LA IDEA ES CREAR PUROS JPanel PARA INVOCARLOS ACÁ Y ERA, LA RESOLUCIÓN ES DE 470x569
                    	 NuevoPanel nombrePanel= new NuevoPanel();
                    	 loadPage(nombrePanel);
                    	*/
                    }
                    
                }
            }
            
        };

        worker.execute(); // Iniciar el trabajo en segundo plano
    }
    
    private void loadPage(JPanel page) {
    	page.setSize(470,536);
    	page.setLocation(0,0);
    	removeAll();
    	add(page, BorderLayout.CENTER);
    	revalidate();
    	repaint();
    }
}
