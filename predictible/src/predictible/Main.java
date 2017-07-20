package predictible;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

	// desempeño y modificabilidad
	// tácticas, patrones y como se podrían implementar
	static String[] opciones = { "1", "X", "2", "1X", "12", "2X", "OV", "UN", "GG", "NG" };

	static int CANTIDAD_APUESTAS = 10;

	public static void main(String[] args) throws FileNotFoundException {

		Scanner input = new Scanner(new File("datos.txt"));
		Apuesta apuesta = new Apuesta();

		List<Partido> partidosAll = new ArrayList<Partido>();
		while (input.hasNextLine()) {
			Partido partido = new Partido();

			args = input.nextLine().split("[,]");
			partido.setEquipoA(args[1]);
			partido.setEquipoB(args[3]);
			partido.setGolesA(Integer.valueOf(args[2]));
			partido.setGolesB(Integer.valueOf(args[4]));

			double[] cuotas = new double[10];
			for (int i = 5; i < 15; i++) {
				cuotas[i - 5] = Double.valueOf(args[i]);
			}
			partido.setCuotas(cuotas);
			partidosAll.add(partido);
		}

		List<Partido> partidos = new ArrayList<Partido>();
		for (int i = 0 ; i< 5; i++) {
			int r = getRandom(partidosAll.size());
			partidos.add(partidosAll.get(r));
			partidosAll.remove(r);
		}
		
		// generan aleatoriamente
		for (Partido partido : partidos) {
			double[] valorOpcion = new double[CANTIDAD_APUESTAS];
			String[] opcion = new String[CANTIDAD_APUESTAS];
			for (int i = 0; i < CANTIDAD_APUESTAS; i++) {
				int r = getRandom(opciones.length);
				opcion[i] = opciones[r];
				valorOpcion[i] = partido.getCuotas()[r];
			}
			partido.setOpciones(opcion);
			partido.setValorOpcion(valorOpcion);
		}

		// evaluar
		double cuotaTotal[] = new double[CANTIDAD_APUESTAS];
		int resultados[] = new int[CANTIDAD_APUESTAS];
		
		for (int i = 0; i < CANTIDAD_APUESTAS; i++) {
			cuotaTotal[i] = 1;
			resultados[i] = 1;
			for (Partido partido : partidos) {
				cuotaTotal[i] = cuotaTotal[i] * partido.getValorOpcion()[i];
			}
		}

		for (Partido partido : partidos) {
			System.out.printf("%s,%d,%s,%d,\n", partido.getEquipoA(), partido.getGolesA(), partido.getEquipoB(),
					partido.getGolesB());
			
			for (int i = 0; i < CANTIDAD_APUESTAS; i++) {
				int evaluar = evaluar(partido.getGolesA(), partido.getGolesB(), partido.getOpciones()[i]);
				
				System.out.printf("\t%s,%d,", partido.getOpciones()[i],
						evaluar);
				
				resultados[i] *= evaluar;
			}
			System.out.println("\n");
		}
		for (int i =0; i< CANTIDAD_APUESTAS; i++) {
			if (cuotaTotal[i] < 33 && cuotaTotal[i] > 12) {
				System.err.printf("\t%.2f", cuotaTotal[i]);
			} else {
				System.out.printf("\t%.2f", cuotaTotal[i]);
			}
				
		}
		
		System.out.println("\n");
		for (int i =0; i< CANTIDAD_APUESTAS; i++) {
			System.out.printf("\t%.2f", cuotaTotal[i]*resultados[i]);
		}

	}

	private static int evaluar(int golesA, int golesB, String opcion) {
		switch (opcion) {
		case "1":
			if (golesA > golesB) {
				return 1;
			}
			break;
			
		case "X":
			if (golesA == golesB) {
				return 1;
			}
			break;
			
		case "2":
			if (golesA < golesB) {
				return 1;
			}
			break;
			
		case "1X":
			if (golesA > golesB || golesA == golesB) {
				return 1;
			}
			break;
			
		case "12":
			if (golesA > golesB || golesA < golesB) {
				return 1;
			}
			break;
			
		case "2X":
			if (golesA < golesB || golesA == golesB) {
				return 1;
			}
			break;
			
		case "OV":
			if ((golesA + golesB) > 2) {
				return 1;
			}
			break;
			
		case "UN":
			if ((golesA + golesB) < 3) {
				return 1;
			}
			break;
			
		case "GG":
			if (golesA > 0 && golesB > 0) {
				return 1;
			}
			break;
			
		case "NG":
			if (golesA == 0 && golesB == 0) {
				return 1;
			}
			break;

		}
		return 0;
	}

	private static int getRandom(int size) {
		return new Random().nextInt(size);
	}

}
