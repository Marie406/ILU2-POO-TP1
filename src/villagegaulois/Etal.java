package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}


	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		etalOccupe = false;
		StringBuilder chaine = new StringBuilder();
		try {
			chaine.append(
					"Le vendeur " + vendeur.getNom() + " quitte son étal, ");
			int produitVendu = quantiteDebutMarche - quantite;
			if (produitVendu > 0) {
				chaine.append(
						"il a vendu " + produitVendu + " " + produit + " parmi les " + quantiteDebutMarche+ " qu'il devait vendre.\n");
			} else {
				chaine.append("il n'a malheureusement rien vendu.\n");
			}
		} catch(NullPointerException e) {
			chaine.append("L'étal est déjà libre.\n");
		}
		
		return chaine.toString();
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'�tal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'�tal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) throws 
	IllegalArgumentException, IllegalStateException  {
		try {
			acheteur.getNom();
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "";
		}
		if(quantiteAcheter<1) {
			System.out.println(acheteur.getNom() + " veut acheter " + quantiteAcheter + " " + produit + " à " + vendeur.getNom());
			throw new IllegalArgumentException("La quantité à acheter doit être strictement supérieure à 0.\n");
		}
		if(!this.etalOccupe) {
			throw new IllegalStateException("L'étal doit être occupé.\n");
		}
		StringBuilder chaine = new StringBuilder();
		chaine.append(acheteur.getNom() + " veut acheter " + quantiteAcheter
				+ " " + produit + " à " + vendeur.getNom());
		
		if (quantite == 0) {
			chaine.append(", malheureusement il n'y en a plus !");
			quantiteAcheter = 0;
		}
		if (quantiteAcheter > quantite) {
			chaine.append(", comme il n'y en a plus que " + quantite + ", "
					+ acheteur.getNom() + " vide l'étal de "
					+ vendeur.getNom() + ".\n");
			quantiteAcheter = quantite;
			quantite = 0;
		}
		if (quantite != 0) {
			quantite -= quantiteAcheter;
			chaine.append(". " + acheteur.getNom()
					+ ", est ravi de tout trouver sur l'étal de "
					+ vendeur.getNom() + "\n");
		}
		return chaine.toString();
	}

	//facto en methode appelante pour faciliter appels dans Scenario
	public void acheterProduitApp(int qteAchetee, Gaulois acheteur) {
		try {
			System.out.println(acheterProduit(qteAchetee, acheteur));
		} catch (IllegalArgumentException e) {
			System.out.println("Le produit n'a pas été acheté.\n");
		} catch (IllegalStateException e) {
			System.out.println("L'étal est vide.\n");
		}
	}
	
	public boolean contientProduit(String produit) {
		return this.produit.equals(produit);
	}

}
