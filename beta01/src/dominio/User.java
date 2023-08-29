package dominio;

public class User {
	private String rut;
	private String name;
	private String lastName;
	private int age;
	private String rol;
	
	public User(String rut, String name, String lastName, int age, String rol) {
		this.rut = rut;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.rol = rol;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "User [rut=" + rut + ", name=" + name + ", lastName=" + lastName + ", age=" + age + ", rol=" + rol + "]";
	}

}
