package dominio;

import java.util.ArrayList;
import java.util.List;

public class Machine {
	private String TipeMachine;
	private String category;
	private String model;
	private String patente;
	private int kilometers;
	private List<Register>ListRegister;
	
	public Machine(String tipeMachine, String category, String model, String patente, int kilometers) {
		this.TipeMachine = tipeMachine;
		this.category = category;
		this.model = model;
		this.patente = patente;
		this.kilometers = kilometers;
		this.ListRegister= new ArrayList<Register>();
	}

	public String getTipeMachine() {
		return TipeMachine;
	}

	public void setTipeMachine(String tipeMachine) {
		TipeMachine = tipeMachine;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public int getKilometers() {
		return kilometers;
	}

	public void setKilometers(int kilometers) {
		this.kilometers = kilometers;
	}

	public List<Register> getListRegister() {
		return ListRegister;
	}

	public void setListRegister(List<Register> listRegister) {
		ListRegister = listRegister;
	}

	@Override
	public String toString() {
		return "Machine [TipeMachine=" + TipeMachine + ", category=" + category + ", model=" + model + ", patente="
				+ patente + ", kilometers=" + kilometers + ", ListRegister=" + ListRegister + "]";
	}
	
}
