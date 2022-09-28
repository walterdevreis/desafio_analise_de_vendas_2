package application;

import java.util.Comparator;
import entities.Sale;

public class MyComparator implements Comparator<Sale> {
	
	@Override
	public int compare(Sale s1, Sale s2) {		
		return s1.getSeller().compareTo(s2.getSeller());
	}	
}