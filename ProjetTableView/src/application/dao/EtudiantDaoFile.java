package application.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import application.model.Etudiant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EtudiantDaoFile implements IEtudiantDao{

	private String pathName = "liste.txt";

	public ObservableList<Etudiant> getAll() {
		List<Etudiant> list = new ArrayList<Etudiant>();
		try {

			File fich = new File(pathName);
			if (!fich.exists()) {
				fich.createNewFile();
			} else {
				if (!(fich.length() == 0)) {
					InputStream os = new FileInputStream(pathName);
					ObjectInputStream oos = new ObjectInputStream(os);

					list = (List<Etudiant>) oos.readObject();

					oos.close();
				}
				return FXCollections.observableList(list);

			}
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return FXCollections.observableList(list);

	}

	public void add(Etudiant e) {
		ObservableList<Etudiant> list = getAll();
		try {
			OutputStream os = new FileOutputStream(pathName);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			list.add(e);
			System.out.println("Le fichier a bien été créé" + list);
			oos.writeObject(new ArrayList<Etudiant>(list));

			oos.close();

		} catch (FileNotFoundException err) {
			// TODO Auto-generated catch block
			err.printStackTrace();
		} catch (IOException err) {
			// TODO Auto-generated catch block
			err.printStackTrace();
		}

	}

	public Etudiant update(Etudiant e) {
System.out.println("entrer dans uptdate" + e);
		List<Etudiant> list = getAll();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Vérification ID..." + list.get(i).getIDetudiant());
			System.out.println("Vérification de la liste..." + e.getIDetudiant());
			if(e.getIDetudiant() == list.get(i).getIDetudiant()) {
				list.remove(i);
				list.add(e);
			}
		}
		
		

		try {
			OutputStream os = new FileOutputStream(pathName);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(new ArrayList<Etudiant>(list));
			oos.close();
			System.out.println("La liste a bien été mise à  jour " + list);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return e;

	}


	public void removal(Etudiant e) {
		System.out.println("Nous avons bien exécuté le cancre.");
		List<Etudiant> list = getAll();
		//int emplacement = e.getUID() - 1;

		
		for (int i = 0; i < list.size(); i++) {
			if(e.getIDetudiant() == list.get(i).getIDetudiant()) {
				list.remove(i);
			}
		}
		

		try {
			OutputStream os = new FileOutputStream(pathName);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(new ArrayList<Etudiant>(list));
			oos.close();
			System.out.println("La liste a bien été mise à  jour " + list);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		
	}
}
