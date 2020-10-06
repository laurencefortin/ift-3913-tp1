import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Methode {
		private String signature;
		private String ligne;
		private List<String>  fichier;
		private List<String> contenuMethode;
		private List<String> commentaireAvantMethode;
		//CONSTRUCTEUR
		public Methode(String nom, String ligne, List<String> contenuFichier)
		{
			this.signature = nom;
			this.ligne = ligne;
			this.fichier = contenuFichier;
			this.contenuMethode = new ArrayList<String>();
			trouverContenuMethode();
			this.commentaireAvantMethode = new ArrayList<String>();
			trouverCommentaireAvantMethode();
		}
		
		/**
		 * Trouve le contenu dune methode en comptant les { }*/
		public void trouverContenuMethode()
		{
			int compteurAccolade = 0;
			boolean dansMethode = false;
			for (String temp : this.fichier)
			{
				if(temp.equals(ligne))
				{
					this.contenuMethode.add(temp);
					dansMethode = true;
					if(temp.contains("{"))
					{
						compteurAccolade++;
					}
				}
				
				else if (dansMethode == true)
				{
					if(temp != null && !temp.trim().isEmpty())
					{		
						this.contenuMethode.add(temp);
						if(!temp.contains("}") && temp.contains("{"))
						{
							compteurAccolade++;
						}
						if(temp.contains("}") && !temp.contains("{"))
						{
							compteurAccolade--;
						}
						if(compteurAccolade == 0)
						{
							dansMethode = false;
							return;
						}
					}
				}
			}
		}
		/**
		 * @return le nombre de ligne de code de la methode selectionner*/
		public int methode_LOC()
		{
			return contenuMethode.size();
		}
		/**
		 * @return le nombre de ligne de commentaire dans la methode selectionnee  y compris les commentaire avant la declaration de la methode*/
		public int methode_CLOC()
		{
			int compteur = 0;
			boolean dansCommentaire = false;
			for (String temp : contenuMethode) 
			{
				if(!dansCommentaire)
				{
					if(temp.contains("//")) 
					{
						compteur++;
					}	
					if(temp.contains("/*") || temp.contains("/**"))
					{
						compteur++;
						dansCommentaire = true;
					}
					if(temp.contains("*/"))
					{
						dansCommentaire = false;
					}
				}
				else
				{
					if(temp != null && !temp.trim().isEmpty())
					{
						compteur++;
					}
					if(temp.contains("*/"))
					{
						dansCommentaire = false;
					}
				}
	        }
			return compteur + commentaireAvantMethode.size();
		}
		
		
		/**
		 * @return la densité de commentaires pour une méthode*/
		public float methode_DC() {
				return ((float)methode_CLOC() / (float)methode_LOC());
		}
		/**
		 * @return le degré selon lequel une méthode est bien commentée */
		public float methode_BC() {
			return ((float)methode_DC() / (float)CC());
		}
		/**
		 * Trouve les commentaires qui se trouvent avant le debut d'une methode*/
		public void trouverCommentaireAvantMethode()
		{
			boolean enCommentaite = false;
			boolean avantMethode = false;
			boolean videSeulement = true;
			ListIterator<String> iterateurList = fichier.listIterator(fichier.size());
			String courant = iterateurList.previous();
			while (iterateurList.hasPrevious()) 
			{
				if(courant.equals(ligne))
				{
					courant = iterateurList.previous();
					avantMethode = true;
				}

				if(avantMethode)
				{
					if(!enCommentaite)
					{
						if(courant == null || courant.trim().isEmpty())
						{
							courant = iterateurList.previous();
						}
						
						else if(courant.contains("//"))
						{
							if(videSeulement = true)
							{
								videSeulement = false;
							}
							commentaireAvantMethode.add(courant);
							courant = iterateurList.previous();
						}
						
						else if(courant.contains("*/"))
						{
							if(videSeulement = true)
							{
								videSeulement = false;
							}
							enCommentaite = true;
							commentaireAvantMethode.add(courant);
							courant = iterateurList.previous();
						}
						else
						{
							if(videSeulement)
							{
								commentaireAvantMethode.clear();
							}
							return;
						}
					}
					else
					{
						if(courant.contains("/**") || courant.contains("/*"))
						{
							commentaireAvantMethode.add(courant);
							return;
						}
						commentaireAvantMethode.add(courant);
						courant = iterateurList.previous();
					}
		
				}
				else {
					courant = iterateurList.previous();
				}
				
			}
		}

		/**
		 * @return la complexité cyclomatique de McCabe d'une methode*/
		public int CC()
		{
			String regex = ".*(if|else|else if|do-while|while|switch)\\s*\\(((?:[^\\(\\)]|\\(\\))*)\\)\\s*.*";
			int compteur = 1;
			for (String temp : contenuMethode) 
			{
			
				if(temp.matches(regex))
				{
					compteur++;
					if(temp.contains("&&"))
					{
						compteur++;
					}
					if(temp.contains("||"))
					{
						compteur++;
					}
				}

			}
			return compteur;
		}
		//GETTER
		public String getSignature() {
			return signature;
		}

		public String getLine() {
			return ligne;
		}

		public List<String> getFile() {
			return fichier;
		}

		public List<String> getContentMethod() {
			return contenuMethode;
		}

		public List<String> getCommentsBeforeMethod() {
			return commentaireAvantMethode;
		}

		
}
