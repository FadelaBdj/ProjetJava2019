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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BaseDeTweets {

	private TreeSet<Tweets> t; //Declaration du TreeSet qui va stocker les Tweets
	private ArrayList<Tweets> tw;
	
	//Initialisation du TreeSet
	public void initialise() {
		
		t = new TreeSet<Tweets>();
	}
	
	//Initialisation de l'ArrayList
		public void initialiseAL() {

			tw = new ArrayList<Tweets>();
		}
	
	//Mutateur 
	public void setTreeSet(TreeSet<Tweets> ts) {
		this.t = ts;
	}
	
	//Accesseur
	public TreeSet<Tweets> getT() {
		return t;
	}
	
	//Mutateur 
	public void setArrayList(ArrayList<Tweets> al) {
		this.tw = al;
	}
	
	//Accesseur
	public ArrayList<Tweets> getArrayList() {
		return tw;
	}

	//Methode d'ajout d'un Tweets dans la base
	public void ajoute(Tweets n) {
			
		t.add(n);
	}
	
	//Methode d'ajout d'un Tweets dans la base
		public void ajouteAL(Tweets n) {

			tw.add(n);
		}
	
	//Affichage de la base de Tweets
	public void afficher() {
		
		Iterator it = t.iterator();
		//On parcourt le TreeSet tant qu'il y'a un élément
		while (it.hasNext())
		{
			Tweets tweet = (Tweets)(it.next());
			//Affichage du Tweet via la méthode toString() appelée automatiquement
			System.out.println(tweet);
		}

	}
	
	//Affichage de la base de Tweets
		public void afficherAL() {
			
			int i;
			//On parcourt le TreeSet tant qu'il y'a un élément
			for(i = 0; i < tw.size(); i++)
			{
				Tweets tweet = (Tweets)(tw.get(i));
				//Affichage du Tweet via la méthode toString() appelée automatiquement
				System.out.println(tweet);
			}

		}
	
	//Rechercher dans la base
	//Entrée : une chaîne de caractères
	//Sortie : Tous les tweets contenants cette chaîne
	public void recherche(String research) {
		
		//On passe la recherche en majuscule
		research = research.toUpperCase();
		//On récupère les mots qui constituent la recherche dans un tableau
		String[] mots = research.split(" "); 
		Pattern p;
		Matcher m;
		int n; 
		int trouve; //Stock le nombre de mots de la recherche qui sont trouvés dans un tweet
		int len = mots.length; //Nombre de mots de la recherche
		int res = 0; //Compte les tweets qui ne contiennent pas la recherche
		
		Iterator it = t.iterator();
		
		//On parcourt le TreeSet afin de regarder pour chaque Tweet si un mot de la recherche est présent
		while (it.hasNext())
		{
			Tweets t = (Tweets)(it.next());
			trouve = 0; //Initialisation de trouve à 0
			//Pour tous les mots de la recherche (du tableau mots)
			for(int i = 0; i < len; i++) {  
				p = Pattern.compile(mots[i]); 
				m = p.matcher(t.toString().toUpperCase()); 
				//Si on trouve le mot on incrémente trouve
				if (m.find()) {
					trouve += 1; 
				}
			}
			//Si tous les mots de la recherche sont présents dans le tweet
			if (trouve == len) { 
				//On affiche le tweet comme résultat de la recherche
				System.out.println(t);
				//Sinon on incrémente res
			} else {
				res++;
			}
		}
		//Si aucun mot n'a été trouvé cad si res est égal au nombre de tweets du TreeSet
		if(res == t.size()) {
			//On affiche "0 résultats"
			System.out.println("O résultat");
		}	
	}
	
	//Rechercher dans la base
		//Entrée : une chaîne de caractères
		//Sortie : Tous les tweets contenants cette chaîne
		public void rechercheAL(String research) {
			
			//On passe la recherche en majuscule
			research = research.toUpperCase();
			//On récupère les mots qui constituent la recherche dans un tableau
			String[] mots = research.split(" "); 
			Pattern p;
			Matcher m;
			int n; 
			int trouve; //Stock le nombre de mots de la recherche qui sont trouvés dans un tweet
			int len = mots.length; //Nombre de mots de la recherche
			int res = 0; //Compte les tweets qui ne contiennent pas la recherche
			int i;
			
			//On parcourt le TreeSet afin de regarder pour chaque Tweet si un mot de la recherche est présent
			for(i = 0; i < tw.size(); i++)
			{
				Tweets tweet = (Tweets)(tw.get(i));
				trouve = 0; //Initialisation de trouve à 0
				//Pour tous les mots de la recherche (du tableau mots)
				for(int j = 0; j < len; j++) {  
					p = Pattern.compile(mots[j]); 
					m = p.matcher(tweet.toString().toUpperCase()); 
					//Si on trouve le mot on incrémente trouve
					if (m.find()) {
						trouve += 1; 
					}
				}
				//Si tous les mots de la recherche sont présents dans le tweet
				if (trouve == len) { 
					//On affiche le tweet comme résultat de la recherche
					System.out.println(tweet);
					//Sinon on incrémente res
				} else {
					res++;
				}
			}
			//Si aucun mot n'a été trouvé cad si res est égal au nombre de tweets de l'ArrayList
			if(res == tw.size()) {
				//On affiche "0 résultats"
				System.out.println("O résultat");
			}	
		}
	
	//Affiche les tweets avec leurs numéros pour pouvoir les supprimer par exemple
	public void afficher_num() {
		
		Iterator it = t.iterator();
		int i = 1;
		
		while (it.hasNext())
		{
			Tweets t = (Tweets)(it.next());
			System.out.println(i + " : " + t);
			i++;
		}

	}
	
	//Affiche les tweets avec leurs numéros pour pouvoir les supprimer par exemple
	public void afficher_numAL() {

		int i = 1;
		int j;
		
		for(j = 0; j < tw.size(); j++)
		{
			Tweets tweet = (Tweets)(tw.get(j));
			System.out.println(i + " : " + tweet);
			i++;
		}

	}
	
	//Supprimer un tweet de la base
	//Entrée : numéro du tweet à supprimer 
	public void supprimer(double num) {
		
		Iterator it = t.iterator();
		double i = 1;
		//On parcourt le TreeSet tant qu'il y'a un élément
		//Si i vaut -1 cela veut dire que l'élément a été supprimé donc on sort de la boucle
		while (it.hasNext() && i != -1)
		{
			Tweets tweet = (Tweets)(it.next());
			//On suprime le tweet qui porte le numéro fournit en paramètre
			if (i == num) {
				//0n supprime le tweet du TreeSet
				t.remove(tweet);
				//i prend la valeur -1 pour pouvoir sortir de la bocule
				i = -1;
			} else {
				//Incrémentation
				i++;
			}
		}
	}
	
	
	//Supprimer un tweet de la base
	//Entrée : numéro du tweet à supprimer 
	public void supprimerAL(int num) {
		
		int i = 1;
		int j = 0;
		//On parcourt l'ArrayList tant qu'il y'a un élément
		//Si i vaut -1 cela veut dire que l'élément a déjà été supprimé donc on sort de la boucle
		while (j < tw.size() && i != -1)
		{
			Tweets tweet = (Tweets)(tw.get(j));
			//On suprime le tweet qui porte le numéro fournit en paramètre
			if (i == num) {
				//0n supprime le tweet du TreeSet
				tw.remove(tweet);
				//i prend la valeur -1 pour pouvoir sortir de la bocule
				i = -1;
			} else {
				//Incrémentation
				i++;
			}
			j++;
		}
	}
	
	//Retourne nombre de tweets de la base
	public int nb_tweets() {
		return t.size();
	}
	
	//Retourne nombre de tweets de la base
		public int nb_tweetsAL() {
			return tw.size();
		}
	
	//Retourne le tableau des 3 utilisateurs les plus populaires (qui sont le plus retweetés)
	public ArrayList populaires() {
		
		ArrayList<String> userRtTab = new ArrayList<String>(); //Stock tous les utilisateurs retweetés (On utilise un ArrayList car la taille n'est pas fixe)
		
		Iterator it = t.iterator();
	
		//On parcourt le TreeSet pour récupérer les utilisateurs retweeté et les stocker dans une chaîne de caractères
		while (it.hasNext()) {
			Tweets t = (Tweets)(it.next());
			if(t.isRt()) {
				userRtTab.add(t.getIdUserRt().toUpperCase());
			}
		}
		System.out.println(userRtTab);
		String max; //Chaîne de caractète qui stockera l'utilisateur qui apparaît le plus de fois dans le tableau UserRtTab
		ArrayList<String> populaires = new ArrayList<String>(); //Stock les trois utilisateurs les plus populaires
		//Pour éviter les erreurs de liste nulle on remplie la liste de vide
		for(int c = 0; c < userRtTab.size(); c++) {
			populaires.add("");
		}
		System.out.println(populaires);
		int k = 0;
		while((k < userRtTab.size())) {
			//On supprime l'utilisateur du tableau si il est déja compté parmi les plus populaires (présent dans troisPopulaires)
			for(int i = 0; i < populaires.size(); i++) { 
				for(int j = 0; j < userRtTab.size(); j++){
					//System.out.println(userRtTab);
					//System.out.println(populaires);
					if((userRtTab.get(j).toUpperCase()).compareTo(populaires.get(i).toUpperCase()) == 0) {
						userRtTab.remove(j);
					}
				}
			}
			//System.out.println(userRtTab);
			
			max = userRtTab.get(0); //max prend la valeur tu premier utilisateur stocké dans le tableau
			//On parcourt le tableau pour trouver les trois utilisateurs retweetés qui apparaissent le plus 
			for(int l = 0; l < userRtTab.size(); l++) { 
				//Si l'utilisateur i a une occurrence supérieure à max 
				if(nb_occurrence(userRtTab, max) < nb_occurrence(userRtTab, userRtTab.get(l))) {
					//System.out.println(nb_occurrence(userRtTab, max));
					//System.out.println(nb_occurrence(userRtTab, userRtTab.get(i)));
					//max prend la valeur de l'utilisateur i
					max = userRtTab.get(l);
				}
			}
			populaires.set(k, max);
			System.out.println("k = "+ k + " populaires = " + populaires);
			k++;
		}
		//On retourne un ArrayList contenat les utilisateurs du plus populaire au moins populaire
		 return populaires;
	}
	
	//Compte le nombre de fois qu'une chaîne de caractères apparraît dans un ArrayList de Strings
	public int nb_occurrence(ArrayList<String> s, String mot) {
		
		int occurrence = 0; //occurrence du mot passé en paramètre dans la chaîne passée en paramètre
		
		//On parcourt l'ArrayList
		for(int i = 0; i < s.size(); i++) { 
			//Si l'élement de la liste est égal au mot passé en paramètre
			if(s.get(i).compareTo(mot) == 0) {
				//On incrémente occurrence
				occurrence += 1;
			}
		}
		//On retourne l'occurence de mot dans la liste s
		return occurrence;
	}
	
	//Fréquence des hashtag
	public void frequence_hashtag() {
		
		ArrayList<String> tweet = new ArrayList<String>(); //Stock tous les utilisateurs retweetés (On utilise un ArrayList car la taille n'est pas fixe)
		
		Iterator it = t.iterator();
	
		//On parcourt le TreeSet pour récupérer les utilisateurs retweeté et les stocker dans une chaîne de caractères
		while (it.hasNext()) {
			Tweets t = (Tweets)(it.next());
			if(t.isRt()) {
				tweet.add(t.getContent());
			}
		}
		
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
			//On ajoute chaque objet créé à la base bdt
			//Ligne 84 : https://stackoverflow.com/questions/33892453/how-to-read-line-by-line-by-using-filereader 
	        while ((line = bufferreader.readLine()) != null) {
	        	//System.out.println(line);
	        	//On sépare les différents éléments du Tweet et on les stocke dans le tableau elt
	        	
	        	String[] elt = line.split("\t");
	        	
	            //On stock chaque élément de elt dans les variables correspondantes pour pouvoir créer l'objet Tweets
	        	String idTweet = elt[0];
	            String idUser = elt[1];
	            
	            //On stock la date dans un autre tableau de la même manière que précédemment pour pouvoir supprimer les décimales des secondes
	            String date = elt[2];
	            String[] date_sansdecimale = date.split("\\.");
	            String d = date_sansdecimale[0];
	            //On converti la chaîne de caractères en LocalDateTime au bon format
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            LocalDateTime formatDateTime = LocalDateTime.parse(d, formatter);
	            
	            String content = elt[3];
	            String idUserRt = "";
	            //Si le tweet est un retweet alors on récupère l'id de l'utilisateur retweeté
	            if(elt.length > 4) {
	            	idUserRt = elt[4];
	            }
	            
	            
	            //On instancie un nouvel objet Tweets avec les attributs que l'on vient de récupérer
	            Tweets t = new Tweets(idTweet, idUser, formatDateTime, content, idUserRt);
	            //System.out.println(t);
	            //On l'ajoute à la base de tweets
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
	
	
	//Chargement du fichier texte dans un objet BaseDeTweets
		public BaseDeTweets lireAL(String file) {
			
			BaseDeTweets bdt = new BaseDeTweets(); //Base qui contiendra les tweets du fichier
			String line; //Pour parcourir les lignes du fichier
			
			//Initialisation de la base
			bdt.initialiseAL();
			try {
				FileReader r = new FileReader(file); 
				BufferedReader bufferreader = new BufferedReader(r);
				//Boucle pour parcourir chaque ligne
				//Cahque ligne sera le contenu d'un nouvel objet Tweets
				//On ajoute chaque objet créé à la base bdt
				//Ligne 84 : https://stackoverflow.com/questions/33892453/how-to-read-line-by-line-by-using-filereader 
		        while ((line = bufferreader.readLine()) != null) {
		        	//System.out.println(line);
		        	//On sépare les différents éléments du Tweet et on les stocke dans le tableau elt
		        	
		        	String[] elt = line.split("\t");
		        	
		            //On stock chaque élément de elt dans les variables correspondantes pour pouvoir créer l'objet Tweets
		        	String idTweet = elt[0];
		            String idUser = elt[1];
		            
		            //On stock la date dans un autre tableau de la même manière que précédemment pour pouvoir supprimer les décimales des secondes
		            String date = elt[2];
		            String[] date_sansdecimale = date.split("\\.");
		            String d = date_sansdecimale[0];
		            //On converti la chaîne de caractères en LocalDateTime au bon format
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		            LocalDateTime formatDateTime = LocalDateTime.parse(d, formatter);
		            
		            String content = elt[3];
		            String idUserRt = "";
		            //Si le tweet est un retweet alors on récupère l'id de l'utilisateur retweeté
		            if(elt.length > 4) {
		            	idUserRt = elt[4];
		            }
		            
		            
		            //On instancie un nouvel objet Tweets avec les attributs que l'on vient de récupérer
		            Tweets t = new Tweets(idTweet, idUser, formatDateTime, content, idUserRt);
		            //System.out.println(t);
		            //On l'ajoute à la base de tweets
		            bdt.ajouteAL(t);
		            
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
				System.out.println("Erreur d’IO");
			}
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}
	
	
	//Récupérer la base de Tweets d'un fichier objet
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
	
	//Récupérer la base de Tweets d'un fichier objet
	public ArrayList<Tweets> lire_bdtAL(String file) {
		ArrayList<Tweets> bdt = null;
		try {
				FileInputStream r = new FileInputStream(file);
				ObjectInputStream o = new ObjectInputStream(r);
				Object tw = o.readObject();
				bdt = (ArrayList<Tweets>)tw;
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
	
	public ObservableList<Tweets> getTweets(){
		
		ObservableList<Tweets> tweets = FXCollections.observableArrayList();
		Iterator it = t.iterator();
		//On parcourt le TreeSet
		while (it.hasNext()) {
			Tweets tweet = (Tweets)(it.next());
			tweets.add(tweet);
		}
		
		return tweets;
	}
	
	public ObservableList<Tweets> getTweetsAL(){
		
		
		ObservableList<Tweets> tweets = FXCollections.observableArrayList();
		int i;
		//On parcourt 'ArrayList
		for(i = 0; i < tw.size(); i++){
			Tweets tweet = (Tweets)(tw.get(i));
			tweets.add(tweet);
		}
		
		return tweets;
	}
	
	
}
