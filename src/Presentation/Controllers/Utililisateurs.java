package Presentation.Controllers;

import DAO.Entities.PC;
import DAO.Entities.Utilisateur;
import DAO.IPC;
import DAO.IUtilisateur;
import Service.GestionUtilisateurs;
import Service.GestiondesRessources;
import Service.IGestionUtilisateurs;
import Service.IGestiondesRessources;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class Utililisateurs implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom ;
    @FXML
    private TextField email ;
    @FXML
    private TextField telephone;
    @FXML
    private TextField motdepasse ;
    @FXML
    private ComboBox role ;
    @FXML
    private ComboBox PC ;
    @FXML
    private Button ajouterUtilisateur ;
    @FXML
    private Button bModifierRessources;
    @FXML
    private TableView<Utilisateur> tableUtilisateur;
    @FXML
    private TableColumn<Utilisateur,String> colNom;
    @FXML
    private TableColumn<Utilisateur,String> colPrenom;
    @FXML
    private TableColumn<Utilisateur,String> colTelephone;
    @FXML
    private TableColumn<Utilisateur,String> colEmail;
    @FXML
    private TableColumn<Utilisateur,String> colPC;
    @FXML
    private TableColumn<Utilisateur,String> colRole;
    @FXML
    private TableColumn<Utilisateur,String> colMotdepasse;
    @FXML
    private  TableColumn colActions = new TableColumn<>("Actions");
    private int id ;


    private ObservableList<Utilisateur> utilisateurs = FXCollections.observableArrayList();
    private GestionUtilisateurs gestionUtilisateurs = new IGestionUtilisateurs(new IUtilisateur());
    private GestiondesRessources gestiondesRessources = new IGestiondesRessources(new IPC());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colMotdepasse.setCellValueFactory(new PropertyValueFactory<>("motdepasse"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("typeUtilisateur"));
        colPC.setCellValueFactory(data -> {
            Utilisateur utilisateur  = data.getValue();
            PC pc = utilisateur.getPC();
            String value = pc.getNum()+"-"+pc.getNom()+"-"+pc.getSale();
            return new ReadOnlyStringWrapper(value);
        });
        /////
        colActions.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Utilisateur, Boolean>,
                        ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Utilisateur, Boolean> p) {
                        return new SimpleBooleanProperty(p.getValue() != null);
                    }
                });

        //Adding the Button to the cell
        colActions.setCellFactory(
                new Callback<TableColumn<Utilisateur, Boolean>, TableCell<Utilisateur, Boolean>>() {


                    @Override
                    public TableCell<Utilisateur, Boolean> call(TableColumn<Utilisateur, Boolean> utilisateurBooleanTableColumn) {
                        return new ButtonCell();
                    }
                });
        colActions.setCellFactory(new Callback<TableColumn<Utilisateur, Boolean>, TableCell<Utilisateur, Boolean>>() {


                                      @Override
                                      public TableCell<Utilisateur, Boolean> call(TableColumn<Utilisateur, Boolean> utilisateurBooleanTableColumn) {
                                          return new ButtonCell();
                                      }
                                  }

        );
        /////
        load();
        tableUtilisateur.setItems(utilisateurs);
        //
        tableUtilisateur.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    try {
                         Utilisateur utilisateur= utilisateurs.get(tableUtilisateur.getSelectionModel().getSelectedIndex());
                        nom.setText(utilisateur.getNom());
                        prenom.setText(utilisateur.getPrenom());
                        email.setText(utilisateur.getEmail());
                        telephone.setText(utilisateur.getTelephone());
                        motdepasse.setText(utilisateur.getMotdepasse());
                        role.setValue(utilisateur.getTypeUtilisateur());
                        PC.setValue(utilisateur.getPC().getNum()+"-"+utilisateur.getPC().getNom()+"-"
                                +utilisateur.getPC().getSale());
                        id = utilisateur.getId();
                        System.out.println(id);


                    }
                    catch (Exception e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("selectionner un element");
                    }
                    //use this to do whatever you want to. Open Link etc.
                }
            }
        });
        ////
        bModifierRessources.setOnAction(event -> {

            try {
                Stage primaryStage = new Stage();
                AnchorPane root = FXMLLoader.load(getClass().getResource("/Presentation/View/XML/GestiondesRessources.fxml"));

                Scene scene=new Scene(root);
                primaryStage.setTitle("");
                primaryStage.setScene(scene);
                primaryStage.show();

            }
            catch ( Exception exception){
                exception.printStackTrace();
            }
        });
        ////
        ObservableList<String> observableArrayList = FXCollections.observableArrayList();
        for(PC pc : gestiondesRessources.getListePCs()){
            observableArrayList.add(pc.getNum()+"-"+pc.getNom()+"-"+pc.getSale());
        }
        PC.setItems(observableArrayList);
        ////
        ////
        ObservableList<String> observableArrayList2 = FXCollections.observableArrayList();
        observableArrayList2.addAll("Responsable", "Intervenant");
        role.setItems(observableArrayList2);
    }
    private class ButtonCell extends TableCell<Utilisateur, Boolean> {
        final Button cellButton = new Button("Delete");

        ButtonCell(){

            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    int index= ButtonCell.this.getIndex();
                    if(index>=0){
                        gestionUtilisateurs.SupprimerUtilisateur(utilisateurs.get(index));
                        load();
                    }else{
                        Alert alert=new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Veuillez sélectionner un élément !!");
                        alert.show();
                    }
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }else{
                setGraphic(null);
            }
        }
    }

    private void load(){
        utilisateurs.clear();
        try {
            utilisateurs.addAll(gestionUtilisateurs.getListeUtilisateurs());
        }
        catch (Exception e){
          e.printStackTrace();
        }
    }
    @FXML
    private void Ajouter(){
        if(nom.getText().isEmpty() ||
                prenom.getText().isEmpty()||
                email.getText().isEmpty()||
                telephone.getText().isEmpty()||
                motdepasse.getText().isEmpty()||
                role.getValue().toString().isEmpty()||
                PC.getValue().toString().isEmpty()

        ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Remplir les champs avant d'ajouter ");
            alert.show();
        }
        else {
            id = generateUniqueId();
            String value = PC.getValue().toString();
            System.out.println(value);
            value = value.substring(0, value.indexOf("-"));
            System.out.println(value);
            int idE = Integer.parseInt(value);
            System.out.println(idE);

            Utilisateur utilisateur = new Utilisateur(id , nom.getText(), prenom.getText(), email.getText(),  telephone.getText(), motdepasse.getText(),role.getValue().toString(),
                    gestiondesRessources.getPC(idE));

             try {
                gestionUtilisateurs.AjouterUtilisatuer(utilisateur);
                load();
                vider();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }
    @FXML
    private void Modifier(){
        if(nom.getText().isEmpty() ||
               prenom.getText().isEmpty()||
                email.getText().isEmpty()||
                telephone.getText().isEmpty()||
                motdepasse.getText().isEmpty()||
                role.getValue().toString().isEmpty()||
                PC.getValue().toString().isEmpty()

        ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Remplir les champs");
            alert.show();
        }
        else {
            String value = PC.getValue().toString();
            value = value.substring(0, value.indexOf("-"));
            int idE = Integer.parseInt(value);
            Utilisateur utilisateur = new Utilisateur( id , nom.getText(), prenom.getText(), email.getText(),  telephone.getText(), motdepasse.getText(),role.getValue().toString(),
                    gestiondesRessources.getPC(idE));
            try {

                utilisateurs.set(tableUtilisateur.getSelectionModel().getSelectedIndex(), utilisateur);
                gestionUtilisateurs.ModifierUtilisateur(utilisateur);
                load();
                vider();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void vider(){
        nom.clear();
        prenom.clear();
        email.clear();
        telephone.clear();
        motdepasse.clear();
        role.setValue(null);
        PC.setValue(null);
    }
}
