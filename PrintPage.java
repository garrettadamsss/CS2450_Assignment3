import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

/**
 * Print Page
 */
public class PrintPage extends Application 
{
  // All of the pages of the interface
  Scene printPageScene = new Scene(new Label());
  Scene savePresetScene = new Scene(new Label());
  Scene checkSettingsScene = new Scene(new Label());
  Scene printingFeedbackScene = new Scene(new Label());

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

  // Variables used for presets
  TextField presetTextField = new TextField();

  // Variables used in the printing feedback screen
  Label fileLabel = new Label();
  Label statusLabel = new Label();
  Label pageLabel = new Label();

  public static void main(String[] args) 
  {
    // Launch the application
    launch(args);
  }

  @Override
  public void start(Stage primaryStage)
  {
    // Print Screen

    // Preset option
    Label presetOptionLabel = new Label("Preset");
    ComboBox<String> presetComboBox = new ComboBox<>();
    presetComboBox.getItems().addAll("None", "Preset 1");
    presetComboBox.setValue("None");
    HBox presetHBox = new HBox(15, presetOptionLabel, presetComboBox);

    // Destination option
    // TODO: Show whether the selected option is connected or not
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

    // Two-sided option
    Label twoSidedOptionLabel = new Label("Two-Sided");
    CheckBox printOnBothSidesCheckBox = new CheckBox("Print on both sides");
    HBox twoSidedHBox = new HBox(15, twoSidedOptionLabel, printOnBothSidesCheckBox);

    // Paper Size option
    Label paperSizeOptionLabel = new Label("Paper Size");
    ComboBox<String> paperSizeComboBox = new ComboBox<>();
    paperSizeComboBox.getItems().addAll("Letter", "Legal", "Executive");
    paperSizeComboBox.setValue("Letter");
    HBox paperSizeHBox = new HBox(15, paperSizeOptionLabel, paperSizeComboBox);

    // Pages per Sheet option
    Label pagesPerSheetOptionLabel = new Label("Pages per Sheet");
    ComboBox<String> pagesPerSheetComboBox = new ComboBox<>();
    pagesPerSheetComboBox.getItems().addAll("1", "2", "4", "9", "16");
    pagesPerSheetComboBox.setValue("1");
    HBox pagesPerSheetHBox = new HBox(15, pagesPerSheetOptionLabel, pagesPerSheetComboBox);
    
    // Margins option
    Label marginsOptionLabel = new Label("Margins");
    ComboBox<String> marginsComboBox = new ComboBox<>();
    marginsComboBox.getItems().addAll("Default", "None", "Minimum", "Custom");
    marginsComboBox.setValue("Default");
    HBox marginsHBox = new HBox(15, marginsOptionLabel, marginsComboBox);
    
    // Quality option
    Label qualityOptionLabel = new Label("Quality");
    ComboBox<String> qualityComboBox = new ComboBox<>();
    qualityComboBox.getItems().addAll("300 dpi", "600 dpi", "1,200 dpi");
    qualityComboBox.setValue("600 dpi");
    HBox qualityHBox = new HBox(15, qualityOptionLabel, qualityComboBox);

    // Scale option
    Label scaleOptionLabel = new Label("Scale");
    ComboBox<String> scaleComboBox = new ComboBox<>();
    scaleComboBox.getItems().addAll("Default", "Custom");
    scaleComboBox.setValue("Default");
    HBox scaleHBox = new HBox(15, scaleOptionLabel, scaleComboBox);

    // Options option
    Label optionsOptionLabel = new Label("Options");
    CheckBox headersAndFootersCheckBox = new CheckBox("Headers and footers");
    CheckBox backgroundGraphicsCheckBox = new CheckBox("Background graphics");
    VBox checkBoxVBox = new VBox(5, headersAndFootersCheckBox, backgroundGraphicsCheckBox);
    HBox optionsHBox = new HBox(15, optionsOptionLabel, checkBoxVBox);

    // Buttons
    Button printButton = new Button("Print");
    printButton.setOnAction(event -> {
      // Change to the confirm settings page based on user selected options
      // Destination
      destinationLabel.setText("Destination: " + marginsComboBox.getValue());
      // Pages
      pagesLabel.setText("Pages: " + pagesComboBox.getValue());
      // Copies
      copiesLabel.setText("Copies: " + copiesSpinner.getValue());
      // Layout
      layoutLabel.setText("Layout: " + layoutComboBox.getValue());
      // Two-sided
      String twoSidedText = printOnBothSidesCheckBox.isSelected() ? "Print on both sides" : "None";
      twoSidedLabel.setText("Two-sided: " + twoSidedText);
      // Paper Size
      paperSizeLabel.setText("Paper Size: " + paperSizeComboBox.getValue());
      // Pages per Sheet
      pagesPerSheetLabel.setText("Pages per Sheet: " + pagesPerSheetComboBox.getValue());
      // Margins
      marginsLabel.setText("Margins: " + marginsComboBox.getValue());
      // Quality
      qualityLabel.setText("Quality: " + qualityComboBox.getValue());
      // Scale
      scaleLabel.setText("Scale: " + scaleComboBox.getValue());
      // Options
      String optionsText = "None";
      if (headersAndFootersCheckBox.isSelected() && backgroundGraphicsCheckBox.isSelected()) {
        optionsText = "Headers and footers + Background graphics";
      } else if (headersAndFootersCheckBox.isSelected()) {
        optionsText = "Headers and footers";
      } else if (backgroundGraphicsCheckBox.isSelected()) {
        optionsText = "Background graphics";
      }
      optionsLabel.setText("Options: " + optionsText);
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
    VBox optionsVBox = new VBox(10, presetHBox, destinationHBox, pagesHBox, copiesHBox, layoutHBox, twoSidedHBox, paperSizeHBox, pagesPerSheetHBox, marginsHBox, qualityHBox, scaleHBox, optionsHBox, buttonsHBox);
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

      // Update the printing feedback screen based on which page is being printed
      PauseTransition pauseTransition1 = new PauseTransition(Duration.seconds(1.5));
      pauseTransition1.setOnFinished(e -> {
        pageLabel.setText("1/2");
      });
      pauseTransition1.play();
      PauseTransition pauseTransition2 = new PauseTransition(Duration.seconds(3));
      pauseTransition2.setOnFinished(e -> {
        pageLabel.setText("2/2");
        statusLabel.setText("completed");
      });
      pauseTransition2.play();
      
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

    Label savePresetLabel = new Label("Preset Name:");
    HBox savePresetHBox = new HBox(10, savePresetLabel, presetTextField);
    // Buttons
    Button saveButton = new Button("Save");
    saveButton.setOnAction(event -> {
      if (!presetTextField.getText().equals("")) {
        presetComboBox.getItems().addAll(presetTextField.getText());
      }
      primaryStage.setScene(printPageScene);
    });
    Button cancelButton = new Button("Cancel");
    cancelButton.setOnAction(event -> {
      primaryStage.setScene(printPageScene);
    });
    HBox savePresetButtonsHBox = new HBox(5, saveButton, cancelButton);
    VBox savePresetVBox = new VBox(savePresetHBox, savePresetButtonsHBox);
    savePresetScene = new Scene(savePresetVBox, 500, 400);

    // Printing Feedback Screen

    // Labels for the printing feedback screen
    Label fileHeadingLabel = new Label("File");
    fileLabel = new Label("lorum ipsum.pdf");
    VBox fileVBox = new VBox(10, fileHeadingLabel, fileLabel);
    Label statusHeadingLabel = new Label("Status");
    statusLabel = new Label("printing");
    VBox statusVBox = new VBox(10, statusHeadingLabel, statusLabel);
    Label pageHeadingLabel = new Label("Page");
    pageLabel = new Label("0/2");
    VBox pageVBox = new VBox(10, pageHeadingLabel, pageLabel);
    HBox printFeedBackHBox = new HBox(5, fileVBox, statusVBox, pageVBox);

    // Buttons
    Button doneButton = new Button("Done");
    doneButton.setOnAction(event -> {
      // Close the window
      primaryStage.close();
    });
    VBox printingFeedbackVBox = new VBox(10, printFeedBackHBox, doneButton);
    printingFeedbackScene = new Scene(printingFeedbackVBox, 1000, 200);
  }
}