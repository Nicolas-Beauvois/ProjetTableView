package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.model.Etudiant;
import application.service.EtudiantService;
import application.service.IEtudiantService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ControllerProjet implements Initializable {

	static Etudiant myVariable;
	@FXML
	private MenuBar menu;
	@FXML
	private Menu file;
	@FXML
	private Menu etudiant;
	@FXML
	private Menu aide;
	@FXML
	private MenuItem ajouterEtu;
	@FXML
	private MenuItem listeEtu;
	@FXML
	private MenuItem iAide;
	@FXML
	private MenuItem exit;
	@FXML
	private TableView<Etudiant> tab;
	@FXML
	private TableColumn<Etudiant, String> nom;
	@FXML
	private TableColumn<Etudiant, String> prenom;
	@FXML
	private TableColumn<Etudiant, String> dateNaissance;
	@FXML
	private TableColumn<Etudiant, String> id;
	@FXML
	private AnchorPane fenetreAccueil;

	private IEtudiantService service = new EtudiantService();
//	Etudiant e1 = new Etudiant("Petit", "Nicolas", "26/11/1991", 1);
//
//	Etudiant e2 = new Etudiant("Paddle", "Kid", "02/12/1997", 2);
//
//	Etudiant e3 = new Etudiant("Ducobu", "Raoul", "03/04/1992", 3);

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		tab.setVisible(false);

		nom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
		dateNaissance.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("dateNaissance"));
		id.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("IDetudiant"));

//		tab.getItems().add(e1);
//		tab.getItems().add(e2);
//		tab.getItems().add(e3);
		addButtonToTable();
		removeButtonToTable();

	}

	// Fermeture de la l'application
	public void getExit(ActionEvent e) {
		Platform.exit();
	}

	// Initialisation fenêtre pour ajouter un nouvel étudiant
	public void getAjout(ActionEvent e) {
		System.out.println("Ajouter Etudiant a été selectionné");
		AnchorPane ajetudiant = null;
		try {
			ajetudiant = FXMLLoader.load(getClass().getResource("/application/FormulaireAjout.fxml"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		fenetreAccueil.getChildren().setAll(ajetudiant);
	}

	// Initialisation fenêtre pour modifier un étudiant
	public void getModif(ActionEvent e) {
		System.out.println("Modifier Etudiant a été selectionné");

		AnchorPane fxmlLoader = null;
		try {
			fxmlLoader = FXMLLoader.load(getClass().getResource("/application/FormulaireModification.fxml"));
			fenetreAccueil.getChildren().setAll(fxmlLoader);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	// Affichage de la liste des étudiants
	public void getListe(ActionEvent e) {
		System.out.println("Liste Etudiant a été selectionné");
		List<Etudiant> etudiants = service.listEtudiant();

		getPeople(etudiants);
		tab.setVisible(true);
	}

	// Affichage de la documentation
	public void getAide(ActionEvent e) {
		System.out.println("Aide a été selectionné");
	}

	// Méthode pour ajouter une colonne "action" à la table view avec les boutons
	// pour modifier les étudiants
	private void addButtonToTable() {

		TableColumn<Etudiant, Void> action = new TableColumn("Modifier");
		Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>> cellFactory = new Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>>() {
			@Override
			public TableCell<Etudiant, Void> call(final TableColumn<Etudiant, Void> param) {
				final TableCell<Etudiant, Void> cell = new TableCell<Etudiant, Void>() {

					private final Button btn = new Button("Modifier étudiant");

					{
						btn.setOnAction((ActionEvent event) -> {
							AnchorPane modEtudiant = null;
							try {
								Etudiant etudiant = getTableView().getItems().get(getIndex());
								List<Etudiant> etudiants = service.listEtudiant();

								System.out.println(etudiant);
								setMyVariable(etudiant);
								modEtudiant = FXMLLoader
										.load(getClass().getResource("/application/FormulaireModification.fxml"));
								fenetreAccueil.getChildren().setAll(modEtudiant);

							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);

						}
					}
				};
				return cell;
			}
		};
		action.setCellFactory(cellFactory);

		tab.getColumns().add(action);

	}

	private void removeButtonToTable() {

		TableColumn<Etudiant, Void> action = new TableColumn("Supprimer");
		Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>> cellFactory = new Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>>() {
			@Override
			public TableCell<Etudiant, Void> call(final TableColumn<Etudiant, Void> param) {
				final TableCell<Etudiant, Void> cell = new TableCell<Etudiant, Void>() {

					private final Button etuSuppr = new Button("Supprimer étudiant");

					{
						etuSuppr.setOnAction((ActionEvent event) -> {
							Etudiant etudiant = getTableView().getItems().get(getIndex());
							System.out.println("Etudiant à exécuter : " + etudiant);
							service.supprimerEtudiant(etudiant);

							System.out.println("gg wp");
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(etuSuppr);

						}
					}
				};
				return cell;
			}
		};
		action.setCellFactory(cellFactory);

		tab.getColumns().add(action);

	}

	public void getPeople(List<Etudiant> listEtu) {
		System.out.println("liste bien convertie d'arraylist à observable list");
		ObservableList<Etudiant> data = FXCollections.<Etudiant>observableArrayList();
		data.addAll(listEtu);
		tab.setItems(data);
	}

	public static void setMyVariable(Etudiant myVariable) {
		ControllerProjet.myVariable = myVariable;
	}

	public static Etudiant getMyVariable() {
		return myVariable;
	}

}
