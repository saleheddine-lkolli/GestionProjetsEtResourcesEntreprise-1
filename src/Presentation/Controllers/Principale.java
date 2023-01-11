package Presentation.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Principale implements Initializable {
    @FXML
    private Button bConnexion;
    @FXML
    private Button bMenu;
    @FXML
    private Button bGesionDesProjests;
    @FXML
    private Button bGestionDesUtilisateurs;
    @FXML
    private Button bGestionMesInfos;
    @FXML
    private Button bImExporter;
    @FXML
    private Button bMissions;
    @FXML
    private Pane pButtoms;
    @FXML
    private Pane pFonctions;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pButtoms.setPrefWidth(160);
        bMenu.setOnAction(event -> {
            if (pButtoms.getPrefWidth() == 160) {
                pButtoms.setPrefWidth(20);
                bMenu.setPrefWidth(20);
                bMissions.setPrefWidth(20);
                bGestionDesUtilisateurs.setPrefWidth(20);
                bGestionMesInfos.setPrefWidth(20);
                bGesionDesProjests.setPrefWidth(20);
                bImExporter.setPrefWidth(20);
            } else if (pButtoms.getPrefWidth() == 20) {
                pFonctions.setPrefWidth(pFonctions.getPrefWidth()-140);
                pButtoms.setPrefWidth(160);
                pButtoms.setPrefWidth(160);
                bMenu.setPrefWidth(160);
                bMissions.setPrefWidth(160);
                bGestionDesUtilisateurs.setPrefWidth(160);
                bGestionMesInfos.setPrefWidth(160);
                bGesionDesProjests.setPrefWidth(160);
                bImExporter.setPrefWidth(160);
            }

        });
        bGesionDesProjests.setOnAction(event -> {
            pFonctions.getChildren().clear();
            try {
                AnchorPane change = FXMLLoader.load(getClass().getResource("/Presentation/View/XML/Projets.fxml"));
                pFonctions.getChildren().add( change );
            }
            catch ( Exception exception){
                exception.printStackTrace();
            }
        });
        bGestionDesUtilisateurs.setOnAction(event -> {
            pFonctions.getChildren().clear();
            try {
                AnchorPane change = FXMLLoader.load(getClass().getResource("/Presentation/View/XML/GestiondesUtilisateurs.fxml"));
                pFonctions.getChildren().add( change );
            }
            catch ( Exception exception){
                exception.printStackTrace();
            }
        });
        bMissions.setOnAction(event -> {
            pFonctions.getChildren().clear();
            try {
                AnchorPane change = FXMLLoader.load(getClass().getResource("/Presentation/View/XML/Missions.fxml"));
                pFonctions.getChildren().add( change );
                System.out.println(pButtoms.getPrefWidth());
            }
            catch ( Exception exception){
            }
        });
        bImExporter.setOnAction(event -> {
            pFonctions.getChildren().clear();
            try {
                AnchorPane change = FXMLLoader.load(getClass().getResource("/Presentation/View/XML/ImExporter.fxml"));
                pFonctions.getChildren().add( change );
                System.out.println(pButtoms.getPrefWidth());
            }
            catch ( Exception exception){
            }
        });
        bGestionMesInfos.setOnAction(event -> {
            pFonctions.getChildren().clear();
            try {
                AnchorPane change = FXMLLoader.load(getClass().getResource("/Presentation/View/XML/GestionMesInformations.fxml"));
                pFonctions.getChildren().add( change );
                System.out.println(pButtoms.getPrefWidth());
            }
            catch ( Exception exception){
            }
        });

    }
}