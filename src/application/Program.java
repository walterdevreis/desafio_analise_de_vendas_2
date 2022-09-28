package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import entities.Sale;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {			

			List<Sale> list = new ArrayList<>();
			
			System.out.println("\nTotal de vendas por vendedor:");

			String line = br.readLine();
			while (line != null) {
				String[] file = line.split(",");
				Integer month = Integer.parseInt(file[0]);
				Integer year = Integer.parseInt(file[1]);
				String seller = file[2];
				Integer items = Integer.parseInt(file[3]);
				Double total = Double.parseDouble(file[4]);

				list.add(new Sale(month, year, seller, items, total));

				line = br.readLine();
			}
			
			Set<String> name = new HashSet<>();
			
			for(Sale s : list) {				
				name.add(s.getSeller()); 
			}			

			for(String n : name) {
				double sum = list.stream()
						.filter(s -> (s.getSeller().equals(n)))
						.mapToDouble(s -> s.getTotal())
						.reduce(0.0, (x, y) -> x + y);
				
				System.out.printf("%s - R$ %.2f%n", n, sum);				
			}

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		sc.close();
	}
}
