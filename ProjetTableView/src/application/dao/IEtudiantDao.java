package application.dao;

import java.util.List;

import application.model.Etudiant;

public interface IEtudiantDao {
public List<Etudiant> getAll();
	
	public void add(Etudiant e);
	
	public Etudiant update(Etudiant e);
	
	public void removal(Etudiant e);
}
