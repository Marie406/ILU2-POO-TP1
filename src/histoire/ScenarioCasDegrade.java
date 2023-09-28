package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal(); 
		System.out.println("Fin du test");
		
		Gaulois obelix = new Gaulois("Obélix", 25);
		System.out.println(etal.acheterProduit(3, null));
		System.out.println("Fin du 2eme test");
		
		Gaulois asterix = new Gaulois("Ast�rix", 8);
		etal.occuperEtal(asterix, "patates", 12);
		try {
			System.out.println(etal.acheterProduit(0, obelix));
		}catch (IllegalArgumentException e) {
			System.out.println("Le produit n'a pas été acheté.\n");
		}
		System.out.println("Fin du 3eme test");
		
		
		Etal etalVide = new Etal();
		try {
			System.out.println(etalVide.acheterProduit(4, obelix));
		}catch (IllegalStateException e) {
			System.out.println("L'étal est vide.\n");
		}
		System.out.println("Fin du 4eme test");
		
	}

}
