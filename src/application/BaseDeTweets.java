package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		
		while (it.hasNext())
		{
			Tweets tweet = (Tweets)(it.next());
			System.out.println(tweet);
		}

	}
	
	//Supprimer un tweet de la base
	public void supprimer(double num) {
		
		Iterator it = t.iterator();
		double i = 1;
		while (it.hasNext() && i != -1)
		{
			Tweets tweet = (Tweets)(it.next());
			if (i == num) {
				t.remove(tweet);
				i = -1;
			} else {
				i++;
			}
		}
	}
	
	//Chargement du fichier dans un objet BaseDeTweets
	public void lire(String file) {
		
		BaseDeTweets bdt = new BaseDeTweets();
		
		FileReader r;
		try {
			r = new FileReader(file);
			int c;
			while ((c = r.read()) != -1) {
				//double idTweet = r.toString()
				//Tweets tweet = new Tweets();
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
