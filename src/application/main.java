package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class main {
	
	private static Tweets t;
	
	public static void main(String[] args) {
		
		/*BaseDeTweets t = new BaseDeTweets();
		t.initialise();
		t.lire("climat.txt");*/
		//t.afficher();
		
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
        
       
        //Tweets ts = new Tweets(1625634634, "user_id", formatDateTime, "contenu du tweet", "user_rt");
        Tweets t = new Tweets(idTweet, idUser, formatDateTime, content, idUserRt);
        System.out.println(t.isRt());
        System.out.println(t.toString());
        System.out.println("----------------------------");
        BaseDeTweets bdt = new BaseDeTweets();
        bdt.initialise();
        bdt.ajoute(t);
        bdt.afficher();

	}
	
	
}
