import DAO.Entities.Mission;
import DAO.Entities.Projet;
import DAO.Entities.Tache;
import com.flexganttfx.model.Activity;
import com.flexganttfx.model.Layer;
import com.flexganttfx.model.Row;
import com.flexganttfx.view.GanttChart;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.flexganttfx.view.GanttChart;

public class RUN_JAVAFX extends Application{

    public static void main(String[] args) {

        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = FXMLLoader.load(getClass().getResource("/Presentation/View/XML/Principale.fxml"));

        Scene scene=new Scene(root,1200,720);
        scene.getStylesheets().add("/Presentation/View/CSS/principale.css");
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
