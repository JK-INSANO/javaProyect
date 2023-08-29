package logica;
import java.util.Scanner;
import java.io.*;
public class App {

	public static void main(String[] args) {
		Scanner read= null;
		Sistema sistema= new SistemaIMPL();
		leerArchivo(sistema,read);
	}

}
