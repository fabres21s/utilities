package predictible;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {

	// desempeño y modificabilidad
	// tácticas, patrones y como se podrían implementar
	static String[] opciones = { "1", "X", "2", "1X", "12", "2X", "OV", "UN", "GG", "NG" };

	static int CANTIDAD_APUESTAS = 10;

	public static void main(String[] args) throws FileNotFoundException {

		Map<String, List<String>> map = new HashMap<String, List<String>>();

		Scanner input = new Scanner(new File("datos3.txt"));
		Apuesta apuesta = new Apuesta();

		int seqFibonacci = 1;
		int anterior = 1;
		List<Partido> partidosAll = new ArrayList<Partido>();
		while (input.hasNextLine()) {
			Partido partido = new Partido();

			args = input.nextLine().split("[,]");
			partido.setEquipoA(args[1]);
			partido.setEquipoB(args[3]);
			partido.setGolesA(Integer.valueOf(args[2]));
			partido.setGolesB(Integer.valueOf(args[4]));

			double sumCuotas = 0;
			double[] cuotas = new double[10];
			for (int i = 5; i < 15; i++) {
				cuotas[i - 5] = Double.valueOf(args[i]);
				sumCuotas += cuotas[i - 5];
			}
			partido.setCuotas(cuotas);
			partidosAll.add(partido);

			double[] cuotasDiv = new double[10];
			double sumCuotasDiv = 0;
			for (int i = 0; i < 10; i++) {
				if (cuotas[i] > 0) {
					cuotasDiv[i] = sumCuotas / cuotas[i];
					sumCuotasDiv += cuotasDiv[i];
				}
			}

			double[] numGen = new double[10];
			for (int i = 0; i < 10; i++) {
				numGen[i] = cuotasDiv[i] * 20 / sumCuotasDiv;
			}

			List<String> optionsGenerated = new ArrayList<String>();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < numGen[i]; j++) {
					optionsGenerated.add(opciones[i]);
				}
			}

			partido.setOptionsGenerated(optionsGenerated);

		}

		List<Partido> partidos = new ArrayList<Partido>();
		for (int i = 0; i < 6; i++) {
			int r = getRandom(partidosAll.size()); // TODO este es el
													// problema
			partidos.add(partidosAll.get(r));
			partidosAll.remove(r);
		}

		// generan aleatoriamente

		boolean continuar = true, continuarDefinitivo = true;
		while (continuarDefinitivo) {
			continuarDefinitivo = false;
			continuar = true;
			while (continuar) {
				for (Partido partido : partidos) {
					double[] valorOpcion = new double[CANTIDAD_APUESTAS];
					String[] opcion = new String[CANTIDAD_APUESTAS];
					for (int i = 0; i < CANTIDAD_APUESTAS; i++) {
						int r = getRandom(partido.getOptionsGenerated().size());
						opcion[i] = partido.getOptionsGenerated().get(r);
						int pos = 0;
						for (int j = 0; j < 10; j++) {
							if (opciones[j].equals(opcion[i])) {
								pos = j;
								break;
							}
						}

						valorOpcion[i] = partido.getCuotas()[pos];
						// optionsGenerated.remove(r);
						// con repeticion o sin repeticion
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
					//System.out.printf("%s,%d,%s,%d,\n", partido.getEquipoA(), partido.getGolesA(), partido.getEquipoB(),
						//	partido.getGolesB());

					int sumCuota = 0;
					for (int i = 0; i < CANTIDAD_APUESTAS; i++) {
						int evaluar = evaluar(partido.getGolesA(), partido.getGolesB(), partido.getOpciones()[i]);

						//System.out.printf("\t%s,%d,", partido.getOpciones()[i], evaluar);

						resultados[i] *= evaluar;

						sumCuota += evaluar;
					}
					//System.out.printf("\t%d\n\n", sumCuota);
				}
				//fin evaluacion
				
				for (int i = 0; i < CANTIDAD_APUESTAS; i++) {
					if (cuotaTotal[i] < 33 && cuotaTotal[i] > 16) {
						//System.out.printf("\t%.2f", cuotaTotal[i]);
						for (Partido partido : partidos) {
							if (partido.getApuestaIntermedia().size() < 10) {
								partido.getApuestaIntermedia().add(partido.getOpciones()[i]);
							} else {
								continuar = false;
							}
						}

					} else {
						//System.out.print("\t");
					}

				}

				//System.out.println("\n\n\n");
				// for (int i = 0; i < CANTIDAD_APUESTAS; i++) {
				// System.out.printf("\t%.2f", cuotaTotal[i] * resultados[i]);
				// }
			}

			for (Partido partido : partidos) {
				int repeticiones[] = new int[10];

				for (String o : partido.getApuestaIntermedia()) {
					for (int j = 0; j < 10; j++) {
						if (opciones[j].equals(o)) {
							repeticiones[j]++;
							break;
						}
					}
				}
				for (int i = 0; i < 10; i++) {
					int r = repeticiones[i] / 3;
					for (int x = 0; x < r; x++) {
						partido.getApuestaDefinitiva().add(opciones[i]);
					}
				}
				if (partido.getApuestaDefinitiva().size() < 100) {
					continuarDefinitivo = true;
				}
			}
			
			System.out.println("\n\napuestas generadas al momento ::: "+new Date() );
			for (Partido partido : partidos){
				System.out.print(partido.getEquipoA() + " ::: "+partido.getEquipoB() +" \t");
				System.out.println(partido.getApuestaDefinitiva());
				
				partido.setApuestaIntermedia(new ArrayList<>());
			}
		}
		
		//evaluacion
		double cuotaTotal[] = new double[CANTIDAD_APUESTAS];
		int resultados[] = new int[CANTIDAD_APUESTAS];



		for (Partido partido : partidos) {
			System.out.printf("%s,%d,%s,%d,\n", partido.getEquipoA(), partido.getGolesA(), partido.getEquipoB(),
					partido.getGolesB());

			int sumCuota = 0;
			int repeticiones[] = new int[10];
			for (String o : partido.getApuestaDefinitiva()) {
				int evaluar = evaluar(partido.getGolesA(), partido.getGolesB(), o);

				//System.out.printf("\t%s,%d,",o, evaluar);

				//resultados[i] *= evaluar;

				sumCuota += evaluar;
				
				for (int j = 0; j < 10; j++) {
					if (opciones[j].equals(o)) {
						repeticiones[j]++;
						break;
					}
				}
			}
			//System.out.printf("\t%d\n", sumCuota);
			for (int i = 0 ; i < 10; i++) {
				System.out.println(opciones[i] + " = "+repeticiones[i]);
			}
			System.out.println("\n");
		}
		//fin evaluacion
			

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
