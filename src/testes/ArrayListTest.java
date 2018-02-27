package testes;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListTest {

	public static void main(String[] args) {
		
		ArrayList<String> nomes = new ArrayList<String>();
		
		nomes.add("Wadd");
		nomes.add("Sam");
		nomes.add("Amanda");
		
		//System.out.printf("%s", nomes.get(1));
		String lista = Arrays.toString(nomes.toArray());
		
		System.out.println(lista);
		System.out.println(nomes.size());
		nomes.remove(1);
		lista = Arrays.toString(nomes.toArray());
		System.out.println(lista);
		nomes.remove("Amanda");
		System.out.println(Arrays.toString(nomes.toArray()));
	}

}
