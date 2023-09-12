package ventanas;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JPanel;

public class CruzMilenioPanel extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CruzMilenioPanel() {
        // Configura el panel si es necesario
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        File arch = new File("cordenadas.txt");
        Point actual = null;
        try (Scanner read = new Scanner(arch)) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] parts = line.split(",");
                int x2 = Integer.valueOf(parts[0]);
                int y2 = Integer.valueOf(parts[1]);
                Point next = new Point(x2, y2);

                if (actual == null) {
                    actual = next;
                } else {
                    g.drawLine((int) (actual.getX()) + 10, (int) (actual.getY() + 40), x2 + 10, y2 + 40);
                    actual = next;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
