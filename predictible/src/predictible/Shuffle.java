package predictible;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Shuffle {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner input = new Scanner(new File("shuffle.txt"));
		
		while (input.hasNext()) {
			List<String> list = new ArrayList<String>();
			args = input.nextLine().split("\t");
			for (String s: args) {
				list.add(s);
			}
			
			Collections.shuffle(list);
			
			System.out.println(list);
		}
		
	}

}
