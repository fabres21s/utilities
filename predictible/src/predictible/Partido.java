package predictible;

import java.util.ArrayList;
import java.util.List;

public class Partido {

	private String equipoA;
	private String equipoB;
	
	private int golesA;
	private int golesB;
	
	private double[] cuotas; //que pagan
	private String [] opciones; //generadas aleatoriamente
	private double [] valorOpcion;
	
	private List<String> optionsGenerated;
	private List<String> apuestaIntermedia;
	private List<String> apuestaDefinitiva;
	
	public Partido() {
		this.apuestaIntermedia = new ArrayList<>();
		this.apuestaDefinitiva = new ArrayList<>();
	}
	
	public String getEquipoA() {
		return equipoA;
	}

	public void setEquipoA(String equipoA) {
		this.equipoA = equipoA;
	}

	public String getEquipoB() {
		return equipoB;
	}

	public void setEquipoB(String equipoB) {
		this.equipoB = equipoB;
	}

	public int getGolesA() {
		return golesA;
	}

	public void setGolesA(int golesA) {
		this.golesA = golesA;
	}

	public int getGolesB() {
		return golesB;
	}

	public void setGolesB(int golesB) {
		this.golesB = golesB;
	}

	public double[] getCuotas() {
		return cuotas;
	}

	public void setCuotas(double[] cuotas) {
		this.cuotas = cuotas;
	}

	public String[] getOpciones() {
		return opciones;
	}

	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}

	public double[] getValorOpcion() {
		return valorOpcion;
	}

	public void setValorOpcion(double[] valorOpcion) {
		this.valorOpcion = valorOpcion;
	}

	public List<String> getOptionsGenerated() {
		return optionsGenerated;
	}

	public void setOptionsGenerated(List<String> optionsGenerated) {
		this.optionsGenerated = optionsGenerated;
	}

	public List<String> getApuestaIntermedia() {
		return apuestaIntermedia;
	}

	public void setApuestaIntermedia(List<String> apuestaIntermedia) {
		this.apuestaIntermedia = apuestaIntermedia;
	}

	public List<String> getApuestaDefinitiva() {
		return apuestaDefinitiva;
	}

	public void setApuestaDefinitiva(List<String> apuestaDefinitiva) {
		this.apuestaDefinitiva = apuestaDefinitiva;
	}
}
