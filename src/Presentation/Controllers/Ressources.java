package Presentation.Controllers;

import DAO.Entities.PC;
import DAO.Entities.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Ressources  implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField sale ;

    @FXML
    private Button AjouterEquipement ;
    @FXML
    private Button bModifierRessources;
    @FXML
    private TableView<PC> tablePCs;
    @FXML
    private TableColumn<PC,String> colNom;
    @FXML
    private TableColumn<PC,String> colSale;

    @FXML
    private TableColumn<Utilisateur,String> colMotdepasse;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void Ajouter(){

    }
}
