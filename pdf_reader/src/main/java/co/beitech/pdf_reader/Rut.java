package co.beitech.pdf_reader;

public class Rut {

	private String NIT;
	private String digitoVerificacion;
	private String direccionSeccional;
	
	private String primerApellido;
	private String segundoApellido;
	private String primerNombre;
	private String otrosNombres;
	
	private String actividadEconomica;
	
	private boolean responsabilidad;
	
//	@Override
//	public String toString() {
//		return 
//	}

	public String getNIT() {
		return NIT;
	}

	public void setNIT(String nIT) {
		NIT = nIT;
	}

	public String getDigitoVerificacion() {
		return digitoVerificacion;
	}

	public void setDigitoVerificacion(String digitoVerificacion) {
		this.digitoVerificacion = digitoVerificacion;
	}

	public String getDireccionSeccional() {
		return direccionSeccional;
	}

	public void setDireccionSeccional(String direccionSeccional) {
		this.direccionSeccional = direccionSeccional;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getOtrosNombres() {
		return otrosNombres;
	}

	public void setOtrosNombres(String otrosNombres) {
		this.otrosNombres = otrosNombres;
	}

	public String getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	public boolean isResponsabilidad() {
		return responsabilidad;
	}

	public void setResponsabilidad(boolean responsabilidad) {
		this.responsabilidad = responsabilidad;
	}
	
	
}
