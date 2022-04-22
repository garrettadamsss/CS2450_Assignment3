import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

/**
 * Print Page
 */
public class PrintPage extends Application 
{
  public static void main(String[] args) 
  {
    // Launch the application
    launch(args);
  }

  @Override
  public void start(Stage primaryStage)
  { 
    // Destination option
    Label destinationLabel = new Label("Destination");
    ComboBox<String> destinationComboBox = new ComboBox<>();
    destinationComboBox.getItems().addAll("Printer 1", "Printer 2", "Save as PDF");
    destinationComboBox.setValue("Printer 1");
    HBox destinationHBox = new HBox(15, destinationLabel, destinationComboBox);

    // Pages option
    Label pagesLabel = new Label("Pages");
    ComboBox<String> pagesComboBox = new ComboBox<>();
    pagesComboBox.getItems().addAll("All", "Odd pages Only", "Even pages Only", "Custom");
    pagesComboBox.setValue("All");
    HBox pagesHBox = new HBox(15, pagesLabel, pagesComboBox);

    // Copies option
    Label copiesLabel = new Label("Copies");
    Spinner<Integer> copiesSpinner = new Spinner<>(1, 100, 1);
    HBox copiesHBox = new HBox(15, copiesLabel, copiesSpinner);

    // Layout option
    Label layoutLabel = new Label("Layout");
    ComboBox<String> layoutComboBox = new ComboBox<>();
    layoutComboBox.getItems().addAll("Portrait", "Landscape");
    layoutComboBox.setValue("Portrait");
    HBox layoutHBox = new HBox(15, layoutLabel, layoutComboBox);

    // Buttons
    Button printButton = new Button("Print");
    printButton.setOnAction(event -> {
      Label checkSettingLabel = new Label("Are these settings correct?");
      Scene checkSettingsScene = new Scene(checkSettingLabel);
      primaryStage.setScene(checkSettingsScene);
    });
    Button cancelButton = new Button("Cancel");
    cancelButton.setOnAction(event -> {
      primaryStage.close();
    });
    HBox buttonsHBox = new HBox(10, printButton, cancelButton);

    // Vertically align all of the options
    VBox optionsVBox = new VBox(10, destinationHBox, pagesHBox, copiesHBox, layoutHBox, buttonsHBox);

    // Create a scene
    Scene printPageScene = new Scene(optionsVBox);

    // Set the scene of the stage
    primaryStage.setScene(printPageScene);

    // Set the title of the window
    primaryStage.setTitle("Print Page");

    // Show the print page's window
    primaryStage.show();
  }
}