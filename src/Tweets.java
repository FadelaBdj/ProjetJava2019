import java.time.LocalDate;

public class Tweets {

	
	private double idTweet; //Identifiant du tweet
	private String idUser; //Identifiant de l'utilisateur 
	private LocalDate dateTweet; //Date du tweet
	private String content; //Contenu du tweet
	private boolean rt; //Indique si le tweet est un retweet
	
	public Tweets(double idTweet, String idUser, LocalDate dateTweet, String content, boolean rt) {
		
		setIdTweet(idTweet);
		setIdUser(idUser);
		setDateTweet(dateTweet);
		setContent(content);
		
	}

	//Accesseurs et mutateurs pour avoir accès aux attributs 
	
	private double getIdTweet() {
		return idTweet;
	}

	private void setIdTweet(double idTweet) {
		this.idTweet = idTweet;
	}

	private String getIdUser() {
		return idUser;
	}

	private void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	private LocalDate getDateTweet() {
		return dateTweet;
	}

	private void setDateTweet(LocalDate dateTweet) {
		this.dateTweet = dateTweet;
	}

	private String getContent() {
		return content;
	}

	private void setContent(String content) {
		this.content = content;
	}

	private boolean isRt() {
		return rt;
	}

	private void setRt(boolean rt) {
		this.rt = rt;
	}
	
	
	
}
