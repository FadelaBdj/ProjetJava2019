package application;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tweets implements Comparable, Serializable {

	
	private String idTweet; //Identifiant du tweet
	private String idUser, idUserRt; //Identifiant de l'utilisateur et de l'utilisateur retweeté
	private LocalDateTime dateTweet; //Date du tweet
	private String content; //Contenu du tweet
	private boolean rt = false; //Indique si le tweet est un retweet
	private static int cpt = 0;
	
	public Tweets(String idTweet, String idUser, LocalDateTime dateTweet, String content, String idUserRt) {
		
		setIdTweet(idTweet);
		setIdUser(idUser);
		setDateTweet(dateTweet);
		setContent(content);
		setIdUserRt(idUserRt);
		//On défini la variable rt seulement si on trouve un id d'utilisateur retweeté
		if(idUserRt != "") {
			rt = true;
		}
		cpt = cpt;
	}

	//Accesseurs et mutateurs pour avoir accès aux attributs 
	
	public String getIdTweet() {
		return idTweet;
	}

	public void setIdTweet(String idTweet) {
		this.idTweet = idTweet;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	

	public String getIdUserRt() {
		return idUserRt;
	}

	public void setIdUserRt(String idUserRt) {
		this.idUserRt = idUserRt;
	}

	public LocalDateTime getDateTweet() {
		return dateTweet;
	}

	public void setDateTweet(LocalDateTime dateTweet) {
		this.dateTweet = dateTweet;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	public boolean isRt() {
		return rt;
	}

	public void setRt(boolean rt) {
		this.rt = rt;
	}

	//Methode toString qui affiche les attribut d'un objet Tweet
	@Override
	public String toString() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = getDateTweet().format(formatter);
        cpt++;
        
        String result =  "Num : " + cpt + "\n";
        result += "Id Tweet : " + getIdTweet() + "\n";
        result +=  "Utilisateur : " + getIdUser() + "\n";
        result +=  "Date du tweet : " + formattedDateTime + "\n";
        result +=  "Contenu du tweet : " + getContent() + "\n";
        if(rt) {
        	result +=  "Utilisateur retweeté : " + getIdUserRt() + "\n";
        }
        
		return result;
	}
	
	
	//Interface comparable
	//Implémentation de la methode compareTo pour pouvoir ranger les tweets dans le treeSet
	public int compareTo(Object o) {
		
		Tweets a_comparer = (Tweets)o;
		if(getIdTweet().compareTo(getIdTweet()) < 0)
			return -1;
		else if (getIdTweet().compareTo(getIdTweet()) > 0)
			return 1;
		else
			if (getDateTweet().compareTo(a_comparer.getDateTweet())<0) return -1;
            else if (getDateTweet().compareTo(a_comparer.getDateTweet())>0) return 1;
            else return 0;
	}
	
	
}
