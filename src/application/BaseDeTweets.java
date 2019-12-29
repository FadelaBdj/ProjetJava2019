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
		//On parcourt le TreeSet tant qu'il y'a un �l�ment
		while (it.hasNext())
		{
			Tweets tweet = (Tweets)(it.next());
			//Affichage du Tweet via la m�thode toString() appel�e automatiquement
			System.out.println(tweet);
		}

	}
	
	//Affichage d'un tweet en particulier
	
	//Supprimer un tweet de la base
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
	
	//Chargement du fichier dans un objet BaseDeTweets
	public void read(String file) {
		
		BaseDeTweets bdt = new BaseDeTweets(); //Base qui contiendra les tweets du fichier
		String line; //Pour parcourir les lignes du fichier
		try {
			FileReader r = new FileReader(file); 
			BufferedReader bufferreader = new BufferedReader(r);
			//Boucle pour parcourir chaque ligne
			//Cahque ligne sera le contenu d'un nouvel objet Tweets
			//On ajoute chaque objet cr�� � la base bdt
			//Ligne 84 : https://stackoverflow.com/questions/33892453/how-to-read-line-by-line-by-using-filereader 
	        while ((line = bufferreader.readLine()) != null) {
	        	
	        	//On s�pare les diff�rents �l�ments du Tweet et on les stocke dans le tableau elt
	        	
	        	String[] elt = line.split("\t");
	        	
	            //On stock chaque �l�ment de elt dans les variables correspondantes pour pouvoir cr�er l'objet Tweets
	        	Double idTweet = Double.parseDouble(elt[0]);
	            String idUser = elt[1];
	            
	            //On stock la date dans un autre tableau de la m�me mani�re que pr�c�demment pour pouvoir supprimer les d�cimales des secondes
	            String date = elt[2];
	            String[] date_sansdecimale = date.split("\\.");
	            String d = date_sansdecimale[0];
	            //On converti la cha�ne de caract�res en LocalDateTime au bon format
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            LocalDateTime formatDateTime = LocalDateTime.parse(d, formatter);
	            
	            String content = elt[3];
	            String idUserRt = elt[4];
	            
	            //On instancie un nouvel objet Tweets avec les attributs que l'on vient de r�cup�rer
	            Tweets t = new Tweets(idTweet, idUser, formatDateTime, content, idUserRt);
	           
	            //On l'ajoute � la base de tweets
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
