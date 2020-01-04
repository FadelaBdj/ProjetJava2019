package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseDeTweets {

	private TreeSet<Tweets> t; //Declaration du TreeSet qui va stocker les Tweets
	
	//Initialisation du TreeSet
	public void initialise() {
		
		t = new TreeSet<Tweets>();
	}
	
	//Mutateur 
	public void setTreeSet(TreeSet<Tweets> ts) {
		this.t = ts;
	}
	
	
	public TreeSet<Tweets> getT() {
		return t;
	}

	//Methode d'ajout d'un Tweets dans la base
	public void ajoute(Tweets n) {
			
		t.add(n);
	}
	
	//Affichage de la base de Tweets
	public void afficher() {
		
		Iterator it = t.iterator();
		//On parcourt le TreeSet tant qu'il y'a un �l�ment
		while (it.hasNext())
		{
			Tweets tweet = (Tweets)(it.next());
			//Affichage du Tweet via la m�thode toString() appel�e automatiquement
			System.out.println(tweet);
		}

	}
	
	//Rechercher dans la base
	//Entr�e : une cha�ne de caract�res
	//Sortie : Tous les tweets contenants cette cha�ne
	public void recherche(String research) {
		
		//On passe la recherche en majuscule
		research = research.toUpperCase();
		//On r�cup�re les mots qui constituent la recherche dans un tableau
		String[] mots = research.split(" "); 
		Pattern p;
		Matcher m;
		int n; 
		int trouve; //Stock le nombre de mots de la recherche qui sont trouv�s dans un tweet
		int len = mots.length; //Nombre de mots de la recherche
		int res = 0; //Compte les tweets qui ne contiennent pas la recherche
		
		Iterator it = t.iterator();
		
		//On parcourt le TreeSet afin de regarder pour chaque Tweet si un mot de la recherche est pr�sent
		while (it.hasNext())
		{
			Tweets t = (Tweets)(it.next());
			trouve = 0; //Initialisation de trouve � 0
			//Pour tous les mots de la recherche (du tableau mots)
			for(int i = 0; i < len; i++) {  
				p = Pattern.compile(mots[i]); 
				m = p.matcher(t.toString().toUpperCase()); 
				//Si on trouve le mot on incr�mente trouve
				if (m.find()) {
					trouve += 1; 
				}
			}
			//Si tous les mots de la recherche sont pr�sents dans le tweet
			if (trouve == len) { 
				//On affiche le tweet comme r�sultat de la recherche
				System.out.println(t);
				//Sinon on incr�mente res
			} else {
				res++;
			}
		}
		//Si aucun mot n'a �t� trouv� cad si res est �gal au nombre de tweets du TreeSet
		if(res == t.size()) {
			//On affiche "0 r�sultats"
			System.out.println("O r�sultat");
		}	
	}
	
	//Affiche les tweets avec leurs num�ros pour pouvoir les supprimer par exemple
	public void afficher_num() {
		
		Iterator it = t.iterator();
		int i = 1;
		
		while (it.hasNext())
		{
			Tweets t = (Tweets)(it.next());
			System.out.println(i + " : " + t.getContent());
			i++;
		}

	}
	
	//Supprimer un tweet de la base
	//Entr�e : num�ro du tweet � supprimer 
	public void supprimer(double num) {
		
		Iterator it = t.iterator();
		double i = 1;
		//On parcourt le TreeSet tant qu'il y'a un �l�ment
		//Si i vaut -1 cela veut dire que l'�l�ment a �t� supprim� donc on sort de la boucle
		while (it.hasNext() && i != -1)
		{
			Tweets tweet = (Tweets)(it.next());
			//On suprime le tweet qui porte le num�ro fournit en param�tre
			if (i == num) {
				//0n supprime le tweet du TreeSet
				t.remove(tweet);
				//i prend la valeur -1 pour pouvoir sortir de la bocule
				i = -1;
			} else {
				//Incr�mentation
				i++;
			}
		}
	}
	
	//Retourne nombre de tweets de la base
	public int nb_tweets() {
		return t.size();
	}
	
	//Retourne le tableau des 3 utilisateurs les plus populaires (qui sont le plus retweet�s)
	public String populaires() {
		
		String idUserRt = ""; //Stock tous les utilisateurs retweet�s
		int[] occ = {}; //Tableau qui stock des occurrence de chaque utilisateurs 
		
		Iterator it = t.iterator();
	
		//On parcourt le TreeSet pour r�cup�rer les utilisateurs retweet� et les stocker dans une cha�ne de caract�res
		while (it.hasNext()) {
			Tweets t = (Tweets)(it.next());
			if(t.isRt()) {
				idUserRt += t.getIdUserRt();
				idUserRt += " ";
			}
			
		}
		//On passe la cha�ne des utilisateurs retweet�s en majuscule
		idUserRt = idUserRt.toUpperCase();
		//On cr�er un tableau contenant les utilisateurs 
		String[] userRtTab = idUserRt.split(" ");
		String max = "";
		int k = 0;
		String[] troisPopulaires = {};
		max = userRtTab[0];
		while(k < 3) {
			for(int i = 0; i < userRtTab.length; i++) { 
				//Si l'�lement du tableau est �gal au mot pass� en param�tre
				//occ[i] = nb_occurrence(userRtTab, userRtTab[i]);
				if((nb_occurrence(userRtTab, max) < nb_occurrence(userRtTab, userRtTab[i])) && (userRtTab[i].compareTo(troisPopulaires[k]) != 0)) {
					max = userRtTab[i];
				}
			}
			troisPopulaires[k] = max;
		}
		
		
		/*int maxx = occ[1];
		int indice = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < occ.length; j++) { 
				if(occ[j] > maxx) {
					maxx = occ[j];
				}
			}
		}*/
		
		//Tri tableau
		 Arrays.sort(occ);
		 //on retourne les 3 premi�re
		/* int[] troisPopulaires = {};
		 troisPopulaires[0] = occ[0];
		 troisPopulaires[1] = occ[2];
		 troisPopulaires[2] = occ[3]; */
		 return max;
	}
	
	//Compte le nombre de fois qu'une cha�ne de caract�res apparra�t dans un tableau de cha�nes de caract�res
	public int nb_occurrence(String[] s, String mot) {
		
		int occurrence = 0; //occurrence du mot pass� en param�tre dans la cha�ne pass�e en param�tre
		
		//On parcourt le tableau
		for(int i = 0; i < s.length; i++) { 
			//Si l'�lement du tableau est �gal au mot pass� en param�tre
			if(s[i].compareTo(mot) == 0) {
				//On incr�mente occurrence
				occurrence += 1;
			}
		}
		//On retourne l'occurence de mot dans le tableau s
		return occurrence;
	}
	
	//Chargement du fichier texte dans un objet BaseDeTweets
	public BaseDeTweets lire(String file) {
		
		BaseDeTweets bdt = new BaseDeTweets(); //Base qui contiendra les tweets du fichier
		String line; //Pour parcourir les lignes du fichier
		
		//Initialisation de la base
		bdt.initialise();
		try {
			FileReader r = new FileReader(file); 
			BufferedReader bufferreader = new BufferedReader(r);
			//Boucle pour parcourir chaque ligne
			//Cahque ligne sera le contenu d'un nouvel objet Tweets
			//On ajoute chaque objet cr�� � la base bdt
			//Ligne 84 : https://stackoverflow.com/questions/33892453/how-to-read-line-by-line-by-using-filereader 
	        while ((line = bufferreader.readLine()) != null) {
	        	//System.out.println(line);
	        	//On s�pare les diff�rents �l�ments du Tweet et on les stocke dans le tableau elt
	        	
	        	String[] elt = line.split("\t");
	        	
	            //On stock chaque �l�ment de elt dans les variables correspondantes pour pouvoir cr�er l'objet Tweets
	        	String idTweet = elt[0];
	            String idUser = elt[1];
	            
	            //On stock la date dans un autre tableau de la m�me mani�re que pr�c�demment pour pouvoir supprimer les d�cimales des secondes
	            String date = elt[2];
	            String[] date_sansdecimale = date.split("\\.");
	            String d = date_sansdecimale[0];
	            //On converti la cha�ne de caract�res en LocalDateTime au bon format
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            LocalDateTime formatDateTime = LocalDateTime.parse(d, formatter);
	            
	            String content = elt[3];
	            String idUserRt = "";
	            //Si le tweet est un retweet alors on r�cup�re l'id de l'utilisateur retweet�
	            if(elt.length > 4) {
	            	idUserRt = elt[4];
	            }
	            
	            
	            //On instancie un nouvel objet Tweets avec les attributs que l'on vient de r�cup�rer
	            Tweets t = new Tweets(idTweet, idUser, formatDateTime, content, idUserRt);
	            //System.out.println(t);
	            //On l'ajoute � la base de tweets
	            bdt.ajoute(t);
	            
	        }
	        return bdt;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return bdt;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return bdt;
		}
		
		
	}
		
	//Enregistrer la base dans un fichier objet
	public void sauvegarder_bdt(String file) {
		
		try {
				FileOutputStream w = new FileOutputStream(file);
				ObjectOutputStream o = new ObjectOutputStream(w);
				o.writeObject(t);
				o.close();
				w.close();
			} catch (Exception e)
			{ 
				System.out.println("Erreur d�IO");
			}
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}
	
	
	//R�cup�rer la base de Tweets d'un fichier objet
	public TreeSet<Tweets> lire_bdt(String file) {
		TreeSet<Tweets> bdt = null;
		try {
				FileInputStream r = new FileInputStream(file);
				ObjectInputStream o = new ObjectInputStream(r);
				Object t = o.readObject();
				bdt = (TreeSet<Tweets>)t;
				o.close();
				r.close();
				return bdt;
			} catch (Exception e) {
				System.out.println("Erreur d'IO");
				return bdt;
			}
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
	}
	
	
	
	
	
}
