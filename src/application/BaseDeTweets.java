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
import javafx.scene.chart.XYChart.Series;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class BaseDeTweets {

	private TreeSet<Tweets> t; //Declaration du TreeSet qui va stocker les Tweets
	private ArrayList<Tweets> tw;
	
	//Initialisation du TreeSet
	public void initialise() {
		
		t = new TreeSet<Tweets>();
	}
	
	//Mutateur 
	public void setTreeSet(TreeSet<Tweets> ts) {
		this.t = ts;
	}
	
	//Accesseur
	public TreeSet<Tweets> getT() {
		return t;
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
			System.out.println(i + " : " + t);
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
	public ArrayList<UtilisateursRt> populaires() {
		
		ArrayList<String> userRtTab = new ArrayList<String>(); //Stock tous les utilisateurs retweet�s (On utilise un ArrayList car la taille n'est pas fixe)
		ArrayList<UtilisateursRt> populaires = new ArrayList<UtilisateursRt>(); //Stock les utilisateurs avec leur nombre de RT
		Iterator it = t.iterator();
	
		//On parcourt le TreeSet pour r�cup�rer les utilisateurs retweet� et les stocker dans un ArrayList
		while (it.hasNext()) {
			Tweets t = (Tweets)(it.next());
			if(t.isRt()) {
				userRtTab.add(t.getIdUserRt().toUpperCase());
			}
		}
		
		int k = 0;
		boolean trouve = false;
		while((k < userRtTab.size())) {
			//On v�rifie si l'utilisateur est d�ja dans la liste des populaires
			for(int i = 0; i < populaires.size(); i++) { 
				UtilisateursRt u = (UtilisateursRt)populaires.get(i);
				if((userRtTab.get(k).toUpperCase()).compareTo(u.getNom().toUpperCase()) == 0) {
					//userRtTab.remove(j);
					trouve = true;
				}
			}
			
			//On parcourt l' ArrayList userRtTab afin de remplir l'ArrayList de UtilisateursRt contenant les utilisateurs et leurs nombre de RT
			int o = 0;
			if(!trouve) {
				o = nb_occurrence(userRtTab, userRtTab.get(k));
				UtilisateursRt user = new UtilisateursRt(userRtTab.get(k), o);
				//System.out.println(user.getNom() + " " + o);
				populaires.add(user);
			}
			//On remet trouve � false
			trouve = false;

			k++;
		}
		//On retourne un ArrayList contenat les utilisateurs du plus populaire au moins populaire
		 return populaires;
	}
	
	//Compte le nombre de fois qu'une cha�ne de caract�res apparra�t dans un ArrayList de Strings
	public int nb_occurrence(ArrayList<String> s, String mot) {
		
		int occurrence = 0; //occurrence du mot pass� en param�tre dans la cha�ne pass�e en param�tre
		
		//On parcourt l'ArrayList
		for(int i = 0; i < s.size(); i++) { 
			//Si l'�lement de la liste est �gal au mot pass� en param�tre
			if(s.get(i).compareTo(mot) == 0) {
				//On incr�mente occurrence
				occurrence += 1;
			}
		}
		//On retourne l'occurence de mot dans la liste s
		return occurrence;
	}
	
	//Fr�quence des hashtag
	//Retourne un ArrayList de Hashtags (hashtag et sa fr�quence)
	public ArrayList<Hashtags> frequence_hashtag() {
		
		ArrayList<String> contenu = new ArrayList<String>(); //Stock tous les utilisateurs retweet�s (On utilise un ArrayList car la taille n'est pas fixe)
		String[] mots;
		ArrayList<String> hashtags = new ArrayList<String>(); //Stock les hashtags
		ArrayList<Hashtags> listeHt = new ArrayList<Hashtags>(); //Stock les hashtags avec leur fr�quence
		
		Iterator it = t.iterator();
	
		//On r�cup�re les contenus des tweets 
		while (it.hasNext()) {
			Tweets t = (Tweets)(it.next());
			if(t.isRt()) {
				contenu.add(t.getContent());
			}
		}
		
		int i;
		//On rempli la liste des hashtags pr�sents dans la base de tweet
		//On parcourt la liste des contenus des tweets
		for(i = 0; i < contenu.size(); i++) {
			//On s�pare le contenu en mots
			mots = contenu.get(i).split(" ");
			//On ajoute le mot � la liste des hashtags si celui-ci commence par "#"
	        for(int j = 0; j < mots.length; j++) {
				if(mots[j].startsWith("#")) {
					hashtags.add(mots[j].toUpperCase());
				}
			}
		}
		System.out.println(hashtags);
		int occHt;
		float frequenceHt;
		boolean trouve = false;
		//Pour chaque hashtag on cr�er un objet Hashtag afin d'y stocker le hashtag et sa fr�quence
		for(int j = 0; j < hashtags.size(); j++) {
			
			for(int k = 0; k < listeHt.size(); k++) {
				Hashtags h = listeHt.get(k);
				if((h.getHashtag().toUpperCase()).compareTo(hashtags.get(j).toUpperCase()) == 0) {
					trouve = true;
				}
			}
			//Si le hashtag a d�j� �t� ajout� � la liste des Hashtags avec leurs fr�quences on ne l'ajoute pas
			if(!trouve) {
				occHt = nb_occurrence(hashtags, hashtags.get(j).toUpperCase());
				System.out.println(occHt);
				frequenceHt = (float) occHt / hashtags.size(); 
				System.out.println(frequenceHt);
				Hashtags ht = new Hashtags(hashtags.get(j).toUpperCase(), frequenceHt);				
				listeHt.add(ht);
				System.out.println(ht);
			}
			
			trouve = false;
		}
		return listeHt;
	}
	
	//Chargement du fichier texte dans un objet BaseDeTweets
	public BaseDeTweets lire(String file) {
		
		BaseDeTweets bdt = new BaseDeTweets(); //Base qui contiendra les tweets du fichier
		String line; //Pour parcourir les lignes du fichier
		String[] elt;
		String idTweet;
		String idUser;
        String date;
        String[] date_sansdecimale;
        String d;
        String content;
        String idUserRt = "";
		
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
	        	
	        	elt = line.split("\t");
	        	
	            //On stock chaque �l�ment de elt dans les variables correspondantes pour pouvoir cr�er l'objet Tweets
	        	if(elt[0] != null) {
	        		idTweet = elt[0];
	        	} else {
	        		idTweet = "inconnu";
	        	}
	        	if(elt[1] != null) {
	        		idUser = elt[1];
	        	} else {
	        		idUser = "inconnu";
	        	}
	                        
	            //On stock la date dans un autre tableau de la m�me mani�re que pr�c�demment pour pouvoir supprimer les d�cimales des secondes
        		date = elt[2];
	            date_sansdecimale = date.split("\\.");
	            d = date_sansdecimale[0];
	            //On converti la cha�ne de caract�res en LocalDateTime au bon format
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            LocalDateTime formatDateTime = LocalDateTime.parse(d, formatter);
	        	
	        	
	        	if(elt[3] != null) {
	        		content = elt[3];
	        	} else {
	        		content = "-";
	        	}
	            
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
	
	//Pour pouvoir afficher les donn�es dans un TableView
	public ObservableList<Tweets> getTweets(){
		
		//On utilise ObservableList
		ObservableList<Tweets> tweets = FXCollections.observableArrayList();
		Iterator it = t.iterator();
		//On parcourt le TreeSet
		while (it.hasNext()) {
			Tweets tweet = (Tweets)(it.next());
			tweets.add(tweet);
		}
		
		return tweets;
	}
	
	//Fonction permettant de g�n�rer la liste des tweets dans un TableView
	//On utilise une ObservableList
	public ObservableList<UtilisateursRt> getUsersRt(){
		
		ArrayList usersList = populaires();
		int i;
		
		ObservableList<UtilisateursRt> users = FXCollections.observableArrayList();
		
		//On parcourt le TreeSet
		for(i = 0; i < usersList.size(); i++) {
			users.add((UtilisateursRt)usersList.get(i));
		}
		
		return users;
	}
	
	//Fonction permettant de g�n�rer la liste des utilisateurs avec leur nombre de RT dans un graphe nuage de points (Scatter Chart)
	//On utilise une ObservableList
	//Source : http://www.java2s.com/Code/Java/JavaFX/ScatterChartfromObservableListXYChartSeriesStringDouble.htm
	public ObservableList<XYChart.Series<String, Integer>> getChartData() {
		
	    String nom;
	    Integer nbRt;
		ArrayList<UtilisateursRt> usersList = populaires();
		int i;
	      
		ObservableList<XYChart.Series<String, Integer>> answer = FXCollections.observableArrayList();
		Series<String, Integer> serie = new Series<String, Integer>();
		serie.setName("Utilisateurs retweet�s");
	  
	  
		for(i = 0; i < usersList.size(); i++) {
			UtilisateursRt u = (UtilisateursRt)usersList.get(i);
			nom = u.getNom();
			nbRt = u.getNbRt();
			serie.getData().add(new XYChart.Data<String, Integer>(nom, nbRt));
		}
		answer.addAll(serie);
		return answer;
	    }
	
}
