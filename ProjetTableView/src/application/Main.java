package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/tabEtu.fxml"));

			primaryStage.setTitle("Gestion des étudiants");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void ajout(Stage stageAjout) {
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/application/FormulaireAjout.fxml"));
//			stageAjout.setTitle("Ajout des étudiants");
//			stageAjout.setScene(new Scene(root));
//			stageAjout.show();
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//	}
//
//	public void modif(Stage stageModif) {
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/application/FormulaireModification.fxml"));
//			stageModif.setTitle("Modification des étudiants");
//			stageModif.setScene(new Scene(root));
//			
//			stageModif.show();
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) {
		launch(args);
		
	}
}
