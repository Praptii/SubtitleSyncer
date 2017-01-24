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
        Scene scene = new Scene(grid, 600, 500);
        primaryStage.setScene(scene);
	    scene.getStylesheets().add
	    (Main.class.getResource("main.css").toExternalForm());
		grid.setHgap(15);
		grid.setVgap(15);
		grid.setPadding(new Insets(25));
		Text welcomeText = new Text();
		welcomeText.setId("welcome-text");
		welcomeText.setText("Welcome!");
		grid.add(welcomeText, 0, 2);
		Label subtitleFilePathLabel = new Label("Open a subtitle file: ");
		grid.add(subtitleFilePathLabel, 0, 7);
		TextField subtitleFilePath = new TextField();
		subtitleFilePath.setPrefWidth(300);
		grid.add(subtitleFilePath, 1, 7);
		Button openSubtitleFile = new Button("...");
		grid.add(openSubtitleFile, 2, 7);
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
		grid.add(adjustmentValueLabel, 0, 8);
		TextField adjustmentValue = new TextField();
        adjustmentValue.setMaxWidth(80);
		grid.add(adjustmentValue, 1, 8);
		Button adjustSubtitleFile = new Button("Apply the adjustment!");
		grid.add(adjustSubtitleFile, 0, 10);
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
