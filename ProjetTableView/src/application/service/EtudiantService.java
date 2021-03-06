package application.service;

import java.util.List;

import application.service.IEtudiantService;
import application.dao.EtudiantDaoFile;
import application.dao.IEtudiantDao;
import application.model.Etudiant;

public class EtudiantService implements IEtudiantService {
	private IEtudiantDao dao = new EtudiantDaoFile();

	public List<Etudiant> listEtudiant() {
		return dao.getAll();
	}

	public void ajouterEtudiant(Etudiant e) {
		dao.add(e);
	}

	public Etudiant modifierEtudiant(Etudiant e) {
		return dao.update(e);
	}

	@Override
	public void supprimerEtudiant(Etudiant e) {
		dao.removal(e);
	
	}
}
