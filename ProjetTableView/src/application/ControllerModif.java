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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ControllerModif implements Initializable {

	@FXML
	private AnchorPane fenetreAccueil;
	@FXML
	private AnchorPane fenetreModif;
	@FXML
	private TextField modifNom;
	@FXML
	private TextField modifPrenom;
	@FXML
	private TextField modifNaissance;
	@FXML
	private ImageView modifPhotoImg;
	@FXML
	private Button btnModifPhoto;
	@FXML
	private Button btnModifEtudiant;
	@FXML
	private Button btnEdit;

	private IEtudiantService service = new EtudiantService();

	public void modification(ActionEvent e) {

		Etudiant majEtu = new Etudiant(modifNom.getText(), modifPrenom.getText(), modifNaissance.getText(),
				ControllerProjet.getMyVariable().getIDetudiant());
		System.out.println("ID modifié : " + ControllerProjet.getMyVariable().getIDetudiant());
		service.modifierEtudiant(majEtu);
		System.out.println("Modification faite avec succès, vous pas devenir fou.");
		
		AnchorPane retourAccueil = null;
		try {
			retourAccueil = FXMLLoader.load(getClass().getResource("/application/tabEtu.fxml"));
			fenetreModif.getChildren().setAll(retourAccueil);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void getEdit(ActionEvent e) {
		modifNom.setEditable(true);
		modifPrenom.setEditable(true);
		modifNaissance.setEditable(true);
		btnModifEtudiant.setVisible(true);
		btnModifPhoto.setVisible(true);
		btnEdit.setVisible(false);
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
		modifPhotoImg.setImage(photoProfil);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(ControllerProjet.getMyVariable().getIDetudiant());
		modifPrenom.setText(ControllerProjet.getMyVariable().getPrenom());
		modifNom.setText(ControllerProjet.getMyVariable().getNom());
		modifNaissance.setText(ControllerProjet.getMyVariable().getDateNaissance());

		// tphoto.setText(McListe.getMyVariable().getPhoto());

		modifNom.setEditable(false);
		modifPrenom.setEditable(false);
		modifNaissance.setEditable(false);
		btnModifEtudiant.setVisible(false);
		btnModifPhoto.setVisible(false);
	}

}
