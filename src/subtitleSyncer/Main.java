package subtitleSyncer;
	
import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
	    primaryStage.setTitle("Subtitle Syncer");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		Text welcomeText = new Text();
		welcomeText.setText("Welcome!" + "\n" + "Please choose a subtitle file");
		Label subtitleFilePathLabel = new Label("Open a subtitle file: ");
		grid.add(subtitleFilePathLabel, 0, 0);
		TextField subtitleFilePath = new TextField();
		subtitleFilePath.setPrefWidth(300);
		grid.add(subtitleFilePath, 1, 0);
		Button openSubtitleFile = new Button("Browse");
		grid.add(openSubtitleFile, 2, 0);
		openSubtitleFile.setOnAction(
		    new EventHandler<ActionEvent>(){
		      @Override
		      public void handle(ActionEvent e){
		        FileChooser subtitleFileChooser = new FileChooser();
		        FileChooser.ExtensionFilter textFilter = 
                    new FileChooser.ExtensionFilter("SRT files (*.srt)", "*.srt");
		        subtitleFileChooser.getExtensionFilters().add(textFilter);
		        File subtitleFile = subtitleFileChooser.showOpenDialog(primaryStage);
		        if (subtitleFile != null){
		          subtitleFilePath.appendText(subtitleFile.getPath());
		        }
		        
		      }
		    }
		    );
		Label adjustmentValueLabel = new Label("Value in seconds:");
		grid.add(adjustmentValueLabel, 0, 1);
		TextField adjustmentValue = new TextField();
        adjustmentValue.setMaxWidth(80);
		grid.add(adjustmentValue, 1, 1);
		Scene scene = new Scene(grid, 600, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
