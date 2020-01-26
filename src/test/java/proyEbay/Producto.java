package proyEbay;

public class Producto implements Comparable<Producto> {
	
	private String nombre;
	private Double precio;

	public Producto(String nombre, Double precio ) {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "PRODUCTO [" + nombre + "] PRECIO [S/." + precio + "]" ;
	}

	public int compareTo(Producto p) {
		return precio.compareTo(p.getPrecio());
	}
	


}
