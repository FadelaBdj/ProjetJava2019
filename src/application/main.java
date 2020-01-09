package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class main {
	
	private static BaseDeTweets t;
	
	public static void main(String[] args) {
		
		/*Scanner scan = new Scanner(System.in);
		System.out.print("Fichier texte contenant les tweets : ");
		String fic = scan.nextLine();*/
		t = new BaseDeTweets();
		t = t.lire("climat2.txt");
		//t.sauvegarder_bdt("save_climat2.bat");	
		//TreeSet<Tweets> ts = t.lire_bdt("save_climat2.bat");
		//t.setTreeSet(ts);
		//t.afficherAL();
		t.afficher_num();
		/*System.out.print("Fichier bat dans lequel sauvegarder la base de tweets : ");
		String fic2 = scan.nextLine();

		t = new BaseDeTweets();
		t.lire(fic1, fic2);
		TreeSet<Tweets> ts = t.lire_bdt(fic2);
		t.lire_bdt("base_de_tweets.bat");*/
		//t.afficher();
		//System.out.println("---------- RECHERCHE -----------");
		//t.recherche("_miss_ives_");
		//System.out.println("---------- NB DE TWEETS -----------");
		//System.out.println("Il y a " + t.nb_tweetsAL() + " tweets dans la base");
		//System.out.println("---------- POPULARITE -----------");
		//System.out.println(t.populaires());
		System.out.println("---------- FREQUENCE HASHTAG -----------");
		t.frequence_hashtag();;
		/*
		int[] occ = {6,7,2,5,3};
		Arrays.sort(occ);
		for(int i = 1; i < occ.length; i++) {
			System.out.println(occ[i]);
		}*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// TESTS CREATION OBJET TWEET ET BASE DE NEWS AVEC BON FORMAT DE DATE
		/*
		String tweet = "1625634634	user_id	2019-09-02 09:40:46.497980	contenu du tweet	user_rt";
	    String[] data = tweet.split("\t");
        System.out.println(Arrays.toString(data));
        System.out.println(data[4]);
        
        Double idTweet = Double.parseDouble(data[0]);
        String idUser = data[1];
        String date = data[2];
        System.out.println(date);
        String[] date_sansdecimale = date.split("\\.");
        System.out.println(Arrays.toString(date_sansdecimale));
        String d = date_sansdecimale[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formatDateTime = LocalDateTime.parse(d, formatter);
        String content = data[3];
        String idUserRt = data[4];
        
       
        Tweets t = new Tweets(idTweet, idUser, formatDateTime, content, idUserRt);
        System.out.println(t.isRt());
        System.out.println(t.toString());
        System.out.println("----------------------------");
        BaseDeTweets bdt = new BaseDeTweets();
        bdt.initialise();
        bdt.ajoute(t);
        bdt.afficher();
		*/
	}
	
	
}
