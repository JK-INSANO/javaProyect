package dominio;

public class Register {
	private String rut;
	private String date;
	private boolean state;//terminado?
	private String tipeRepair;//tipo de reparacion
	private String detailsRepair;
	
	public Register(String rut, String date, boolean state, String tipeRepair, String detailsRepair) {
		this.rut = rut;
		this.date = date;
		this.state = state;
		this.tipeRepair = tipeRepair;
		this.detailsRepair = detailsRepair;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getTipeRepair() {
		return tipeRepair;
	}

	public void setTipeRepair(String tipeRepair) {
		this.tipeRepair = tipeRepair;
	}

	public String getDetailsRepair() {
		return detailsRepair;
	}

	public void setDetailsRepair(String detailsRepair) {
		this.detailsRepair = detailsRepair;
	}

	@Override
	public String toString() {
		return "Register [rut=" + rut + ", date=" + date + ", state=" + state + ", tipeRepair=" + tipeRepair
				+ ", detailsRepair=" + detailsRepair + "]";
	}
	
}
