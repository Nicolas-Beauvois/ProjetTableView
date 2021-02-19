package application.model;

import java.io.Serializable;

public class Etudiant implements Serializable {
	private String nom;
	private String prenom;
	private String dateNaissance;
	// private static int ID = 0;

	// {ID++;}
	private int IDetudiant;
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public int getIDetudiant() {
		return IDetudiant;
	}

	public void setIDetudiant(int iDetudiant) {
		IDetudiant = iDetudiant;
	}

	private int ID;

	public Etudiant() {
		// TODO Auto-generated constructor stub
		this.IDetudiant = ID;
	}

	public Etudiant(String nom, String prenom, String dateNaissance, int ID) {
		// TODO Auto-generated constructor stub
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.IDetudiant = ID;
	}

	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", IDetudiant="
				+ IDetudiant + "]";
	}

}
