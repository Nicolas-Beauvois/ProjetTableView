package application.service;

import java.util.List;


import application.model.Etudiant;

public interface IEtudiantService {
public List<Etudiant> listEtudiant();
	
	public void ajouterEtudiant(Etudiant e);
	
	public Etudiant modifierEtudiant(Etudiant e);
	
	public void supprimerEtudiant(Etudiant e);
}
