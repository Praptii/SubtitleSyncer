package subtitleSyncer;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Subtitle Syncer");
    GridPane grid = new GridPane();
    Scene scene = new Scene(grid, 600, 500);
    primaryStage.setScene(scene);
    scene.getStylesheets().add(Main.class.getResource("main.css").toExternalForm());
    grid.setHgap(15);
    grid.setVgap(15);
    grid.setPadding(new Insets(25));
    Text welcomeText = new Text();
    welcomeText.setId("welcome-text");
    welcomeText.setText("Welcome!");
    grid.add(welcomeText, 0, 2);
    Label subtitleFilePathLabel = new Label("Open a subtitle file: ");
    grid.add(subtitleFilePathLabel, 0, 8);
    TextField subtitleFilePath = new TextField();
    subtitleFilePath.setPrefWidth(300);
    grid.add(subtitleFilePath, 1, 8);
    Button openSubtitleFile = new Button("...");
    grid.add(openSubtitleFile, 2, 8);
    openSubtitleFile.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        FileChooser subtitleFileChooser = new FileChooser();
        FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("SRT files (*.srt)", "*.srt");
        subtitleFileChooser.getExtensionFilters().add(textFilter);
        File subtitleFile = subtitleFileChooser.showOpenDialog(primaryStage);
        if (subtitleFile != null) {
          subtitleFilePath.appendText(subtitleFile.getPath());
        }

      }
    });
    Label adjustmentValueLabel = new Label("Value in seconds:");
    grid.add(adjustmentValueLabel, 0, 9);
    TextField adjustmentValue = new TextField();
    adjustmentValue.setMaxWidth(80);
    grid.add(adjustmentValue, 1, 9);
    Button adjustSubtitleFile = new Button("Apply the adjustment!");
    grid.add(adjustSubtitleFile, 0, 11);
    adjustSubtitleFile.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        Base b1 = new Base();
        Text validateAdjustmentValue = new Text();
        if(adjustmentValue.getText().length() == 0){
          validateAdjustmentValue.setText("You must specify a number");
          validateAdjustmentValue.setFill(Color.RED);
          grid.add(validateAdjustmentValue, 0, 12);
          FadeTransition ft = new FadeTransition(Duration.millis(7000), validateAdjustmentValue);
          ft.setFromValue(1.0);
          ft.setToValue(0);
          ft.play();
        }
        String filePath = subtitleFilePath.getText();
        int adjuster = Integer.parseInt(adjustmentValue.getText());
        if(filePath.length() != 0){
          try {
            b1.process(filePath, adjuster);
          } catch (IOException e1) {
            e1.printStackTrace();
          } catch (ParseException e1) {
            e1.printStackTrace();
          }
          Text successText = new Text();
          successText.setFill(Color.AQUA);
          successText.setText("Adjustment Successful!");
          grid.add(successText, 0, 4);
          FadeTransition ft = new FadeTransition(Duration.millis(3000), successText);
          ft.setFromValue(1.0);
          ft.setToValue(0);
          ft.play();
        }
      }
    });
    Button closeWindow = new Button("Exit");
    closeWindow.setId("exit-button");
    grid.add(closeWindow, 1, 17);
    closeWindow.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        primaryStage.close();
      }
      });
      
    primaryStage.show();
    
  }

  public static void main(String[] args) {
    launch(args);
  }
}
