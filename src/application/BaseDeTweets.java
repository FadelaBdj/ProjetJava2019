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
	
	//Methode d'ajout d'un Tweets dans la base
	public void ajoute(Tweets n) {
			
		t.add(n);
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
	
	//Rechercher dans la base
	//Entrée : une chaîne de caractères
	//Sortie : Tous les tweets contenants cette chaîne
	public void recherche(String research) {
		
		String[] researchSplit = research.split(" "); //"false"
		Pattern p;
		Matcher m;
		int trouve;
		int len = researchSplit.length; //len = 1
		int res = 0;
		
		Iterator it = t.iterator();
		
		while (it.hasNext())
		{
			News n = (News)(it.next());
			trouve = 0; //initialisation de trouve à 0
			for(int i = 0; i < len; i++) { // de 0 à 1 exclu : 1 fois
				p = Pattern.compile(researchSplit[i]); //chaine à chercher : "false"
				m = p.matcher(n.toString()); //recherche de "false" dans la News
				if (m.find()) {
					trouve += 1; //trouve = 1
				} else {
					res += 1; //res = 0
				}
			}
			if (trouve == len) { //trouve = 1 = len
				System.out.println(n);
			}
		}
		
		if(res == t.size()) {
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
			System.out.println(i + " : " + t.getContent());
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
	
	//Chargement du fichier dans un objet BaseDeTweets
	public void lire(String file) {
		
		BaseDeTweets bdt = new BaseDeTweets(); //Base qui contiendra les tweets du fichier
		String line; //Pour parcourir les lignes du fichier
		try {
			FileReader r = new FileReader(file); 
			BufferedReader bufferreader = new BufferedReader(r);
			//Boucle pour parcourir chaque ligne
			//Cahque ligne sera le contenu d'un nouvel objet Tweets
			//On ajoute chaque objet créé à la base bdt
			//Ligne 84 : https://stackoverflow.com/questions/33892453/how-to-read-line-by-line-by-using-filereader 
	        while ((line = bufferreader.readLine()) != null) {
	        	
	        	//On sépare les différents éléments du Tweet et on les stocke dans le tableau elt
	        	
	        	String[] elt = line.split("\t");
	        	
	            //On stock chaque élément de elt dans les variables correspondantes pour pouvoir créer l'objet Tweets
	        	Double idTweet = Double.parseDouble(elt[0]);
	            String idUser = elt[1];
	            
	            //On stock la date dans un autre tableau de la même manière que précédemment pour pouvoir supprimer les décimales des secondes
	            String date = elt[2];
	            String[] date_sansdecimale = date.split("\\.");
	            String d = date_sansdecimale[0];
	            //On converti la chaîne de caractères en LocalDateTime au bon format
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            LocalDateTime formatDateTime = LocalDateTime.parse(d, formatter);
	            
	            String content = elt[3];
	            String idUserRt = elt[4];
	            
	            //On instancie un nouvel objet Tweets avec les attributs que l'on vient de récupérer
	            Tweets t = new Tweets(idTweet, idUser, formatDateTime, content, idUserRt);
	           
	            //On l'ajoute à la base de tweets
	            bdt.initialise();
	            bdt.ajoute(t);
	            bdt.afficher();
	        	
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		
	
	
	
	
	
	
}
