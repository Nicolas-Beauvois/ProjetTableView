package application;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.model.Etudiant;
import application.service.EtudiantService;
import application.service.IEtudiantService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ControllerAjout implements Initializable{

	@FXML
	private AnchorPane fenetreAccueil;
	@FXML
	private AnchorPane fenetreAjout;
	@FXML
	private TextField nomText;
	@FXML
	private TextField prenomText;
	@FXML
	private TextField naissanceText;
	@FXML
	private ImageView photoImg;
	@FXML
	private Button btnImportPhoto;
	@FXML
	private Button btnAjoutEtu;
	

	
	
	private IEtudiantService service = new EtudiantService();
	public void nouveauEtu() {
		int n;
		
		List<Etudiant> promo = new ArrayList<Etudiant>();
		promo = service.listEtudiant();

		if (promo.size() == 0) {
			n = 1;
		} else {
			Etudiant IDetudiant = promo.get(promo.size() - 1);
			n = ((Etudiant) IDetudiant).getIDetudiant() + 1;
		}
	Etudiant nouveauEtu = new Etudiant(nomText.getText(), prenomText.getText(), naissanceText.getText(), n);
	System.out.println("ID actuel : " + n);
		
		service.ajouterEtudiant(nouveauEtu);
		
		AnchorPane retourAccueil = null;
		try {
			retourAccueil = FXMLLoader.load(getClass().getResource("/application/tabEtu.fxml"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		fenetreAjout.getChildren().setAll(retourAccueil);
	}
	
	
	public void nouvellePhoto(ActionEvent e) {
		FileChooser file = new FileChooser();
		Stage stageFile = new Stage();
		// filtrer les fichiers
		ExtensionFilter filter = new ExtensionFilter("Image", ".jpg", ".png");
		file.setSelectedExtensionFilter(filter);
		
		// si l'utilisateur clique sur enregistrer dans Jfilechooser
	
			File selFile = file.showOpenDialog(stageFile);
			
			Image photoProfil = new Image(selFile.toURI().toString());
			photoImg.setImage(photoProfil);
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
	}

}
