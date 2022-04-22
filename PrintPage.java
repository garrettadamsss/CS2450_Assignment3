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
  // Variables for Check Setting Screen
  Label destinationLabel = new Label();
  Label pagesLabel = new Label();
  Label copiesLabel = new Label();
  Label layoutLabel = new Label();
  Label paperSizeLabel = new Label();
  Label pagesPerSheetLabel = new Label();
  Label marginsLabel = new Label();
  Label qualityLabel = new Label();
  Label scaleLabel = new Label();
  Label twoSidedLabel = new Label();
  Label optionsLabel = new Label();

  Scene printPageScene = new Scene(new Label());
  Scene savePresetScene = new Scene(new Label());
  Scene checkSettingsScene = new Scene(new Label());
  Scene printingFeedbackScene = new Scene(new Label());

  public static void main(String[] args) 
  {
    // Launch the application
    launch(args);
  }

  @Override
  public void start(Stage primaryStage)
  {
    // Print Screen

    // Destination option
    Label destinationOptionLabel = new Label("Destination");
    ComboBox<String> destinationComboBox = new ComboBox<>();
    destinationComboBox.getItems().addAll("Printer 1", "Printer 2", "Save as PDF");
    destinationComboBox.setValue("Printer 1");
    HBox destinationHBox = new HBox(15, destinationOptionLabel, destinationComboBox);

    // Pages option
    Label pagesOptionLabel = new Label("Pages");
    ComboBox<String> pagesComboBox = new ComboBox<>();
    pagesComboBox.getItems().addAll("All", "Odd pages Only", "Even pages Only", "Custom");
    pagesComboBox.setValue("All");
    HBox pagesHBox = new HBox(15, pagesOptionLabel, pagesComboBox);

    // Copies option
    Label copiesOptionLabel = new Label("Copies");
    Spinner<Integer> copiesSpinner = new Spinner<>(1, 100, 1);
    HBox copiesHBox = new HBox(15, copiesOptionLabel, copiesSpinner);

    // Layout option
    Label layoutOptionLabel = new Label("Layout");
    ComboBox<String> layoutComboBox = new ComboBox<>();
    layoutComboBox.getItems().addAll("Portrait", "Landscape");
    layoutComboBox.setValue("Portrait");
    HBox layoutHBox = new HBox(15, layoutOptionLabel, layoutComboBox);

    // Buttons
    Button printButton = new Button("Print");
    printButton.setOnAction(event -> {
      // Change to the confirm settings page
      // TODO: Add functionality to change the settings text based on user chosen settings
      destinationLabel.setText("Destination: ");
      pagesLabel.setText("Pages: ");
      copiesLabel.setText("Copies: ");
      layoutLabel.setText("Layout: ");
      paperSizeLabel.setText("Paper Size: ");
      pagesPerSheetLabel.setText("Pages per Sheet: ");
      marginsLabel.setText("Margins: ");
      qualityLabel.setText("Quality: ");
      scaleLabel.setText("Scale: ");
      twoSidedLabel.setText("Two-sided: ");
      optionsLabel.setText("Options: ");
      primaryStage.setScene(checkSettingsScene);
    });
    Button savePresetButton = new Button("Save Preset");
    savePresetButton.setOnAction(event -> {
      // Change to the save preset page
      primaryStage.setScene(savePresetScene);
    });
    Button printCancelButton = new Button("Cancel");
    printCancelButton.setOnAction(event -> {
      // Close the window
      primaryStage.close();
    });
    HBox buttonsHBox = new HBox(10, printButton, savePresetButton, printCancelButton);
    // Vertically align all of the options
    VBox optionsVBox = new VBox(10, destinationHBox, pagesHBox, copiesHBox, layoutHBox, buttonsHBox);
    // Create a scene for the Print Page
    printPageScene = new Scene(optionsVBox, 1700, 1000);
    // Set the scene of the stage
    primaryStage.setScene(printPageScene);
    // Set the title of the window
    primaryStage.setTitle("Print Page");
    // Show the print page's window
    primaryStage.show();


    // Check Settings Screen

    // Create Title Label
    Label checkSettingLabel = new Label("Are these settings correct?");
    // Create VBox for all the labels for the settings
    VBox settingsVBox = new VBox(5, destinationLabel, pagesLabel, copiesLabel, layoutLabel);
    // Create VBox for all the labels for more settings
    VBox moreSettingsVBox = new VBox(5, paperSizeLabel, pagesPerSheetLabel, marginsLabel, qualityLabel, scaleLabel,
        twoSidedLabel, optionsLabel);
    // Create buttons
    Button confirmButton = new Button("Confirm");
    confirmButton.setOnAction(event -> {
      // Change to the printing feedback page
      primaryStage.setScene(printingFeedbackScene);
    });
    Button confirmCancelButton = new Button("Cancel");
    confirmCancelButton.setOnAction(event -> {
      // Go back to the print page
      primaryStage.setScene(printPageScene);
    });
    // Create HBox for the buttons
    HBox confirmButtonsHBox = new HBox(10, confirmButton, confirmCancelButton);
    // Create the scene and VBox for the content of the scene
    VBox checkSettingsVBox = new VBox(10, checkSettingLabel, settingsVBox, moreSettingsVBox, confirmButtonsHBox);
    checkSettingsScene = new Scene(checkSettingsVBox, 1000, 500);

    // Save Preset Screen

    Label savePresetLabel = new Label("Save Preset Screen");
    // TODO: Add input for preset name
    // Buttons
    Button saveButton = new Button("Save");
    saveButton.setOnAction(event -> {
      // TODO: Add functionality to save the preset
      primaryStage.setScene(printPageScene);
    });
    Button cancelButton = new Button("Cancel");
    cancelButton.setOnAction(event -> {
      primaryStage.setScene(printPageScene);
    });
    HBox savePresetButtonsHBox = new HBox(5, saveButton, cancelButton);
    VBox savePresetVBox = new VBox(savePresetLabel, savePresetButtonsHBox);
    savePresetScene = new Scene(savePresetVBox, 500, 400);

    // Printing Feedback Screen

    Label printingFeedbackLabel = new Label("Printing Feedback Screen");
    // TODO: Add functionality with timer to show which page is currently being printer
    // Buttons
    Button doneButton = new Button("Done");
    doneButton.setOnAction(event -> {
      // Close the window
      primaryStage.close();
    });
    VBox printingFeedbackVBox = new VBox(10, printingFeedbackLabel, doneButton);
    printingFeedbackScene = new Scene(printingFeedbackVBox, 1000, 200);
  }
}