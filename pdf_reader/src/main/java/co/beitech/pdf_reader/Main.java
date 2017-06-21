package co.beitech.pdf_reader;

import java.io.File;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

public class Main {

	public static void main(String[] args) throws Exception {
		PDFTextStripper stripper = new PDFTextStripper();
		
		
		PDDocument document = PDDocument.load(new File("rutBetty.pdf"));
		//PDPageTree pdPageTree = document.getPages().it;
			PDPage pdPage = document.getPages().iterator().next();
			Scanner input = new Scanner(pdPage.getContents());
			
			String NIT = "";
			String digito = "";
			String ciudad = "";
			String primerApellido ="";
			String segundoApellido ="";
			String primerNombre ="";
			String segundoNombre ="";
			String actividadEconomica = "";
			boolean responsabilidad = false;
			int count = 0;
			String par = "";
			while (input.hasNext()) {
				
				String line = input.nextLine();
				if (line.endsWith("Tj")) {
					count++;
					if (count > 4 && count < 19) {
						NIT += line;
					} else if (count == 19) {
						digito = line;
					} else if (count == 20) {
						ciudad += line.replaceAll("Tj", "");
					} else if (count == 64) {
						primerApellido = line.replaceAll("Tj", "");
					} else if (count == 65) {
						segundoApellido = line.replaceAll("Tj", "");
					} else if (count == 66) {
						primerNombre = line.replaceAll("Tj", "");
					} else if (count == 67) {
						segundoNombre = line.replaceAll("Tj", "");
					} else if (count > 125 && count < 130) {
						actividadEconomica += line;
					} else if (count > 164 && count < 217) {
						
						if (count %2 == 0) {
							 par = (par + line).replaceAll("[^0-9]", "");
							if (par.equals("5")) {
								responsabilidad = true;
								break;
							}
						}
						par = line;
						
					}
					 
				}
			}
			System.out.println("NIT = "+NIT.replaceAll("[^0-9]", ""));
			System.out.println("digito = "+digito.replaceAll("[^0-9]", ""));
			System.out.println("ciudad = "+ciudad.replaceAll("[^A-Za-záéíóúñÁÉÍÓÚÑ ]", ""));
			System.out.println("primer Apellido = "+primerApellido.replaceAll("[^A-Za-záéíóúñÁÉÍÓÚÑ ]", ""));
			System.out.println("Segundo Apellido = "+segundoApellido.replaceAll("[^A-Za-záéíóúñÁÉÍÓÚÑ ]", ""));
			System.out.println("Primer nombre = "+primerNombre.replaceAll("[^A-Za-záéíóúñÁÉÍÓÚÑ ]", ""));
			System.out.println("otros nombres = "+segundoNombre.replaceAll("[^A-Za-záéíóúñÁÉÍÓÚÑ ]", ""));
			System.out.println("actividad = "+actividadEconomica.replaceAll("[^0-9]", ""));
			System.out.println("responsabilidad "+responsabilidad);
			
			

	}

}
