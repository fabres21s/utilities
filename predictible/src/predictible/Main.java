package predictible;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {


	static int CANTIDAD_APUESTAS = 10;

	public static void main(String[] args) throws FileNotFoundException {

		Map<String, List<String>> map = new HashMap<String, List<String>>();

		Scanner input = new Scanner(new File("datos.txt"));
		
		String equipoA, equipoB, valorA, valorB, evento;
		List<Partido> partidosAll = new ArrayList<Partido>();
		while (input.hasNextLine()) {
			equipoA = input.nextLine();
			equipoB = input.nextLine();
			//TODO consultar a base de datos los equipos
			for (int i = 0; i < 12; i++) {
				valorA = input.nextLine();
				evento = input.nextLine();
				valorB = input.nextLine();
				
				System.out.println(evento +">>> "+valorA+" - "+valorB);
			}
			
		}
		//fin evaluacion
			

	}


}
