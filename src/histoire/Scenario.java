package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class Scenario {

	public static void main(String[] args) {
		Village village = new Village("le village des irr�ductibles", 10, 5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		
		//pour derniere question
		village.afficheVillageoisApp();
		
		village.setChef(abraracourcix);
		Druide druide = new Druide("Panoramix", 2, 5, 10);
		Gaulois obelix = new Gaulois("Ob�lix", 25);
		Gaulois asterix = new Gaulois("Ast�rix", 8);
		Gaulois assurancetourix = new Gaulois("Assurancetourix", 2);
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(assurancetourix);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(obelix);
		village.ajouterHabitant(druide);
		village.ajouterHabitant(abraracourcix);
		village.afficherVillageois();

		System.out.println(village.installerVendeur(bonemine, "fleurs", 20));
		System.out
				.println(village.installerVendeur(assurancetourix, "lyres", 5));
		System.out.println(village.installerVendeur(obelix, "menhirs", 2));
		System.out.println(village.installerVendeur(druide, "fleurs", 10));

		System.out.println(village.rechercherVendeursProduit("fleurs"));
		Etal etalFleur = village.rechercherEtal(bonemine);
		
		
		//pour avant derniere partie
		etalFleur.acheterProduitApp(10, abraracourcix);
		etalFleur.acheterProduitApp(15, obelix);
		etalFleur.acheterProduitApp(-3, assurancetourix);
		etalFleur.acheterProduitApp(12, null);

		System.out.println(village.partirVendeur(abraracourcix));
		System.out.println(village.afficherMarche());
		
		village.afficheVillageoisApp();
		
		
	}

}
