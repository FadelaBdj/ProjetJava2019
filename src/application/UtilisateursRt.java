package application;

public class UtilisateursRt {
	
	String nom;
	int nbRt;
	
	public UtilisateursRt(String nom, int nbRT) {
		this.nom = nom;
		this.nbRt = nbRt;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbRt() {
		return nbRt;
	}

	public void setNbRt(int nbRt) {
		this.nbRt = nbRt;
	}

	@Override
	public String toString() {
		return "Utilisateur =" + getNom() + "\n nbRt = " + getNbRt() ;
	}
	
	

}
