package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	

	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtal);
	}

	private static class Marche {
		private Etal [] etals;
		
		private Marche(int nbEtalMarche) {
			etals = new Etal[nbEtalMarche];
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, 
				String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			int i = 0;
			while(i < etals.length && etals[i].isEtalOccupe()) {
				i++;
			}
			if(i==etals.length)
				return -1;
			else 
				return i;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			int i = 0;
			while(i<etals.length && etals[i].getVendeur()!=gaulois) {
				i++;
			}
			if(i!=etals.length)
				return etals[i];
			else 
				return null;
		}
		
		private String afficherMarche() {
			int i = 0;
			String monMarche ="";
			int etalsLibres = 0;
			while(i<etals.length) {
				i++;
				if(etals[i].isEtalOccupe()) {
					String.format(monMarche, etals[i].afficherEtal());
				}
				else 
					etalsLibres++;
			}
			if(etalsLibres>0) {
				String mesEtalsLibres = "Il reste " + Integer.toString(etalsLibres) + " étals non utilisés dans le marché."; 
				String.format(monMarche, mesEtalsLibres);
			}
			return monMarche;
		}
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		int indEtal;
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + "cherche un endroit pour vendre" + Integer.toString(nbProduit) + produit);
		indEtal = marche.trouverEtalLibre();
		if(indEtal!=-1) {
			marche.utiliserEtal(indEtal, vendeur, produit, nbProduit);
			chaine.append("Le vendeur " + vendeur.getNom() + " vend des fleurs à l'étal n°" + Integer.toString(indEtal) + ".");
		}
		else {
			chaine.append("Le vendeur " + vendeur.getNom() + "n'a pas trouvé d'étal libre.");
		}
		return chaine.toString();
	}
	
	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
}