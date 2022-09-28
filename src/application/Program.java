package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
			
			Map<String, Double> totalSold = new LinkedHashMap<>(); 
			
			for(Sale s : list) {				
				if(totalSold.containsKey(s.getSeller())) {
					Double sales = totalSold.get(s.getSeller());
					totalSold.put(s.getSeller(), s.getTotal() + sales);
				}
				else {
					totalSold.put(s.getSeller(), s.getTotal());
				}
			}			
			
			for (String key : totalSold.keySet()) {
				System.out.println(key + " - " + String.format("%.2f", totalSold.get(key)));
			}
			
			


		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		sc.close();
	}

}
