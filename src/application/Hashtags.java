package application;

public class Hashtags {
	
	String hashtag;
	float frequence;
	
	public Hashtags(String hashtag, float frequence) {
		this.hashtag = hashtag;
		this.frequence = frequence;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public float getFrequence() {
		return frequence;
	}

	public void setFrequence(float frequence) {
		this.frequence = frequence;
	}

	@Override
	public String toString() {
		return "hashtag= " + hashtag + ", frequence = " + frequence;
	}
	
	
}
