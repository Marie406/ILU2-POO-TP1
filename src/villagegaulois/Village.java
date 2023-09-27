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
			//System.out.println("mon marche possede " + Integer.toString(etals.length));
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, 
				String produit, int nbProduit) {
			if(etals[indiceEtal]== null)
				etals[indiceEtal] = new Etal(); //pour la tte premiere fois ou on utilise un etal
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		

		private int trouverEtalLibre() {
			for(int i = 0; i<etals.length; i++) {
				if(etals[i]==null || !etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
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
			StringBuilder chaine = new StringBuilder();
			int etalsLibres = 0;
			while(i<etals.length) {
				if(etals[i]!=null && etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				}
				else 
					etalsLibres++;
				i++;
			}
			if(etalsLibres>0) {
				String mesEtalsLibres = "Il reste " + Integer.toString(etalsLibres) + " étals non utilisés dans le marché.\n"; 
				chaine.append(mesEtalsLibres);
			}
			return chaine.toString();
		}
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		int indEtal;
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + Integer.toString(nbProduit) + " " + produit + ".\n");
		indEtal = marche.trouverEtalLibre();
		if(indEtal!=-1) {
			//System.out.println("L'étal libre est à l'indice " + Integer.toString(indEtal));
			marche.utiliserEtal(indEtal, vendeur, produit, nbProduit);
			chaine.append("Le vendeur " + vendeur.getNom() + " vend des fleurs � l'�tal n�" + Integer.toString(indEtal+1) + ".\n");
		}
		else {
			chaine.append("Le vendeur " + vendeur.getNom() + "n'a pas trouv� d'�tal libre.\n");
		}
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		for(int i = 0; i<marche.etals.length; i++) {
			if(marche.etals[i]!=null && marche.etals[i].isEtalOccupe() && marche.etals[i].contientProduit(produit)) {
				if(i==0) {
					chaine.append("Les vendeurs qui proposent des " + produit + " sont :\n");
				}
				chaine.append("- " + marche.etals[i].getVendeur().getNom() + "\n");
			}
		}
		String mesVendeurs = chaine.toString();
		if(mesVendeurs.equals(""))
			return ("Il n'y a aucun vendeur qui propose des " + produit + ".\n");
		else 
			return mesVendeurs;
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		for(int i = 0; i<marche.etals.length; i++) {
			if(marche.etals[i]!=null && marche.etals[i].isEtalOccupe() && marche.etals[i].getVendeur().getNom().equals(vendeur.getNom())) {
				return marche.etals[i];
			}
		}
		return null; 
	}
	
	public String partirVendeur(Gaulois vendeur) {
		Etal etalQuitte = rechercherEtal(vendeur);
		if(etalQuitte!=null) {
			return etalQuitte.libererEtal();
		}
		else 
			return("Le gaulois " + vendeur.getNom() + " ne possède deja aucun etal.\n");
	}
	
	public String afficherMarche() {
		return marche.afficherMarche();
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
					+ " vivent les l�gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
}