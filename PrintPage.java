import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
  private Scene printPageScene = new Scene(new Label());
  private Scene savePresetScene = new Scene(new Label());
  private Scene checkSettingsScene = new Scene(new Label());
  private Scene printingFeedbackScene = new Scene(new Label());

  // Variables for Print Page
  private ComboBox<String> destinationComboBox = new ComboBox<>();
  private Circle printerConnectionCircle = new Circle(5);
  private Label printerConnectionLabel = new Label("Not connected");
  private ComboBox<String> presetComboBox = new ComboBox<>();
  private ComboBox<String> pagesComboBox = new ComboBox<>();
  private Spinner<Integer> copiesSpinner = new Spinner<>(1, 100, 1);
  private ComboBox<String> layoutComboBox = new ComboBox<>();
  private CheckBox printOnBothSidesCheckBox = new CheckBox("Print on both sides");
  private ComboBox<String> paperSizeComboBox = new ComboBox<>();
  private ComboBox<String> pagesPerSheetComboBox = new ComboBox<>();
  private ComboBox<String> marginsComboBox = new ComboBox<>();
  private ComboBox<String> qualityComboBox = new ComboBox<>();
  private ComboBox<String> scaleComboBox = new ComboBox<>();
  private CheckBox headersAndFootersCheckBox = new CheckBox("Headers and footers");
  private CheckBox backgroundGraphicsCheckBox = new CheckBox("Background graphics");
  private Button printButton;

  // Variables for Check Setting Screen
  private Label destinationLabel = new Label();
  private Label pagesLabel = new Label();
  private Label copiesLabel = new Label();
  private Label layoutLabel = new Label();
  private Label paperSizeLabel = new Label();
  private Label pagesPerSheetLabel = new Label();
  private Label marginsLabel = new Label();
  private Label qualityLabel = new Label();
  private Label scaleLabel = new Label();
  private Label twoSidedLabel = new Label();
  private Label optionsLabel = new Label();

  // Variables used for presets
  private TextField presetTextField = new TextField();
  private HashMap<String, String> destinationMap = new HashMap<>();
  private HashMap<String, String> pagesMap = new HashMap<>();
  private HashMap<String, Integer> copiesMap = new HashMap<>();
  private HashMap<String, String> layoutMap = new HashMap<>();
  private HashMap<String, Boolean> twoSidedMap = new HashMap<>();
  private HashMap<String, String> paperSizeMap = new HashMap<>();
  private HashMap<String, String> pagesPerSheetMap = new HashMap<>();
  private HashMap<String, String> marginsMap = new HashMap<>();
  private HashMap<String, String> qualityMap = new HashMap<>();
  private HashMap<String, String> scaleMap = new HashMap<>();
  private HashMap<String, Boolean> headersAndFootersMap = new HashMap<>();
  private HashMap<String, Boolean> backgroundGraphicsMap = new HashMap<>();

  // Variables used in the printing feedback screen
  private Label fileLabel = new Label();
  private Label statusLabel = new Label();
  private Label pageLabel = new Label();

  public static void main(String[] args) 
  {
    // Launch the application
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
    // Print Screen
    createPrintScreen(primaryStage);

    // Check Settings Screen
    createCheckSettingsPage(primaryStage);
    

    // Save Preset Screen
    createSavePresetScreen(primaryStage);
    
    // Printing Feedback Screen
    createPrintingFeedbackScreen(primaryStage);
    
  }
  
  // Helper function to create the Print Screen
  public void createPrintScreen(Stage primaryStage) throws FileNotFoundException {
    // Print Screen

    // Create test preset
    destinationMap.put("Test", "Printer 2");
    pagesMap.put("Test", "Odd pages Only");
    copiesMap.put("Test", 5);
    layoutMap.put("Test", "Landscape");
    twoSidedMap.put("Test", true);
    paperSizeMap.put("Test", "Executive");
    pagesPerSheetMap.put("Test", "9");
    marginsMap.put("Test", "Minimum");
    qualityMap.put("Test", "1,150 dpi");
    scaleMap.put("Test", "Custom");
    headersAndFootersMap.put("Test", true);
    backgroundGraphicsMap.put("Test", true);

    // Preset option
    Label presetOptionLabel = new Label("Preset");
    presetComboBox.getItems().addAll("None", "Test");
    presetComboBox.setValue("None");
    presetComboBox.setOnAction(event -> {
      // Change settings based on preset name
      String presetName = presetComboBox.getValue();
      if (presetName != "None") {
        destinationComboBox.setValue(destinationMap.get(presetName));
        pagesComboBox.setValue(pagesMap.get(presetName));
        copiesSpinner.getValueFactory().setValue(copiesMap.get(presetName));
        layoutComboBox.setValue(layoutMap.get(presetName));
        printOnBothSidesCheckBox.setSelected(twoSidedMap.get(presetName));
        paperSizeComboBox.setValue(paperSizeMap.get(presetName));
        pagesPerSheetComboBox.setValue(pagesPerSheetMap.get(presetName));
        marginsComboBox.setValue(marginsMap.get(presetName));
        qualityComboBox.setValue(qualityMap.get(presetName));
        scaleComboBox.setValue(scaleMap.get(presetName));
        headersAndFootersCheckBox.setSelected(headersAndFootersMap.get(presetName));
        backgroundGraphicsCheckBox.setSelected(backgroundGraphicsMap.get(presetName));
      }
    });
    presetComboBox.setPrefWidth(150);
    HBox presetHBox = new HBox(77, presetOptionLabel, presetComboBox);
    presetHBox.setAlignment(Pos.CENTER);

    // Destination option
    Label destinationOptionLabel = new Label("Destination");
    destinationComboBox.getItems().addAll("Printer 1", "Printer 2", "Save as PDF");
    destinationComboBox.setOnAction(event -> {
      if (destinationComboBox.getValue() == "Printer 1") {
        printerConnectionLabel.setText("Not connected");
        printerConnectionCircle.setRadius(5);
        printerConnectionCircle.setFill(Color.RED);
        printButton.setId("");
      } else if (destinationComboBox.getValue() == "Printer 2") {
        printerConnectionLabel.setText("Connected");
        printerConnectionCircle.setRadius(5);
        printerConnectionCircle.setFill(Color.GREEN);
        printButton.setId("print-button");
      } else if (destinationComboBox.getValue() == "Save as PDF") {
        printerConnectionLabel.setText("");
        printerConnectionCircle.setRadius(0);
      }
    });
    destinationComboBox.setValue("Printer 1");
    destinationComboBox.setPrefWidth(150);
    printerConnectionCircle.setFill(Color.RED);
    HBox printerConnectionHBox = new HBox(5, printerConnectionCircle, printerConnectionLabel);
    printerConnectionHBox.setAlignment(Pos.CENTER_LEFT);
    VBox printerVBox = new VBox(5, destinationComboBox, printerConnectionHBox);
    HBox destinationHBox = new HBox(50, destinationOptionLabel, printerVBox);
    destinationHBox.setAlignment(Pos.CENTER);
    
    

    //Print preview
    FileInputStream inputStream = new FileInputStream("images/Capture.PNG");
    Image image = new Image(inputStream);
    ImageView printPreview = new ImageView(image);
    //printPreview.setPreserveRatio(true);
    printPreview.setFitHeight(700);
    printPreview.setFitWidth(600);
    // Pages option
    Label pagesOptionLabel = new Label("Pages");
    pagesComboBox.getItems().addAll("All", "Odd pages Only", "Even pages Only", "Custom");
    pagesComboBox.setValue("All");
    pagesComboBox.setPrefWidth(150);
    HBox pagesHBox = new HBox(80, pagesOptionLabel, pagesComboBox);
    pagesHBox.setAlignment(Pos.CENTER);

    // Copies option
    Label copiesOptionLabel = new Label("Copies");
    copiesSpinner.setPrefWidth(150);
    HBox copiesHBox = new HBox(78, copiesOptionLabel, copiesSpinner);
    copiesHBox.setAlignment(Pos.CENTER);

    // Layout option
    Label layoutOptionLabel = new Label("Layout");
    layoutComboBox.getItems().addAll("Portrait", "Landscape");
    layoutComboBox.setValue("Portrait");
    layoutComboBox.setPrefWidth(150);
    HBox layoutHBox = new HBox(77, layoutOptionLabel, layoutComboBox);
    layoutHBox.setAlignment(Pos.CENTER);

    // Two-sided option
    Label twoSidedOptionLabel = new Label("Two-Sided");
    printOnBothSidesCheckBox.setPrefWidth(200);
    printOnBothSidesCheckBox.setPadding(new Insets(0, 0, 0, 49));
    HBox twoSidedHBox = new HBox(8, twoSidedOptionLabel, printOnBothSidesCheckBox);
    twoSidedHBox.setAlignment(Pos.CENTER);

    // Paper Size option
    Label paperSizeOptionLabel = new Label("Paper Size");
    paperSizeComboBox.getItems().addAll("Letter", "Legal", "Executive");
    paperSizeComboBox.setValue("Letter");
    paperSizeComboBox.setPrefWidth(150);
    HBox paperSizeHBox = new HBox(58, paperSizeOptionLabel, paperSizeComboBox);
    paperSizeHBox.setAlignment(Pos.CENTER);

    // Pages per Sheet option
    Label pagesPerSheetOptionLabel = new Label("Pages per Sheet");
    pagesPerSheetComboBox.getItems().addAll("1", "2", "4", "9", "16");
    pagesPerSheetComboBox.setValue("1");
    pagesPerSheetComboBox.setPrefWidth(150);
    HBox pagesPerSheetHBox = new HBox(28, pagesPerSheetOptionLabel, pagesPerSheetComboBox);
    pagesPerSheetHBox.setAlignment(Pos.CENTER);

    // Margins option
    Label marginsOptionLabel = new Label("Margins");
    marginsComboBox.getItems().addAll("Default", "None", "Minimum", "Custom");
    marginsComboBox.setValue("Default");
    marginsComboBox.setPrefWidth(150);
    HBox marginsHBox = new HBox(72, marginsOptionLabel, marginsComboBox);
    marginsHBox.setAlignment(Pos.CENTER);

    // Quality option
    Label qualityOptionLabel = new Label("Quality");
    qualityComboBox.getItems().addAll("300 dpi", "600 dpi", "1,150 dpi");
    qualityComboBox.setValue("600 dpi");
    qualityComboBox.setPrefWidth(150);
    HBox qualityHBox = new HBox(76, qualityOptionLabel, qualityComboBox);
    qualityHBox.setAlignment(Pos.CENTER);

    // Scale option
    Label scaleOptionLabel = new Label("Scale");
    scaleComboBox.getItems().addAll("Default", "Custom");
    scaleComboBox.setValue("Default");
    scaleComboBox.setPrefWidth(150);
    HBox scaleHBox = new HBox(86, scaleOptionLabel, scaleComboBox);
    scaleHBox.setAlignment(Pos.CENTER);

    // Options option
    Label optionsOptionLabel = new Label("Options");
    VBox checkBoxVBox = new VBox(5, headersAndFootersCheckBox, backgroundGraphicsCheckBox);
    checkBoxVBox.setPrefWidth(200);
    checkBoxVBox.setPadding(new Insets(0, 0, 0, 47));
    HBox optionsHBox = new HBox(28, optionsOptionLabel, checkBoxVBox);
    optionsHBox.setAlignment(Pos.CENTER);

    // Buttons
    printButton = new Button("Print");
    printButton.setOnAction(event -> {
      // Only go to next page if printer is connected
      if (printerConnectionLabel.getText() == "Connected") {
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
      }

    });
    Button savePresetButton = new Button("Save Preset");
    savePresetButton.getStyleClass().addAll("save-button");
    savePresetButton.setOnAction(event -> {
      // Change to the save preset page
      primaryStage.setScene(savePresetScene);
    });
    Button printCancelButton = new Button("Cancel");
    printCancelButton.setOnAction(event -> {
      // Close the window
      primaryStage.close();
    });
    //Create HBox to hold lower buttons
    HBox buttonsHBox = new HBox(10, printButton, savePresetButton, printCancelButton);
    buttonsHBox.setAlignment(Pos.CENTER);

    //Create VBox to contain all buttons
    VBox optionsVBox = new VBox(20, presetHBox, destinationHBox, pagesHBox, copiesHBox, layoutHBox, twoSidedHBox,
        paperSizeHBox, pagesPerSheetHBox, marginsHBox, qualityHBox, scaleHBox, optionsHBox, buttonsHBox);
    optionsVBox.setAlignment(Pos.CENTER);
    optionsVBox.setId("optionsVBox");
    optionsVBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    //optionsVBox.setMinWidth(600);
    //Create BorderPane to hold all layouts
    BorderPane root = new BorderPane();
    root.setLeft(printPreview);
    root.setCenter(optionsVBox);
    root.setAlignment(printPreview, Pos.CENTER);
    root.setAlignment(optionsVBox, Pos.CENTER);
    // Create a scene for the Print Page
    printPageScene = new Scene(root, 1000, 700);
    printPageScene.getStylesheets().add("styles.css");
    // Set the scene of the stage
    primaryStage.setScene(printPageScene);
    // Set the title of the window
    primaryStage.setTitle("Print Page");
    // Show the print page's window
    primaryStage.show();
  }
  
  // Helper function to create the check settings page
  public void createCheckSettingsPage(Stage primaryStage) {
    // Create Title Label
    Label checkSettingLabel = new Label("Are these settings correct?");
    // Create VBox for all the labels for the settings
    VBox settingsVBox = new VBox(5, destinationLabel, pagesLabel, copiesLabel, layoutLabel);
    // Create VBox for all the labels for more settings
    VBox moreSettingsVBox = new VBox(5, paperSizeLabel, pagesPerSheetLabel, marginsLabel, qualityLabel, scaleLabel,
        twoSidedLabel, optionsLabel);
    // Create buttons
    Button confirmButton = new Button("Confirm");
    confirmButton.setId("confirm-button");
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
    checkSettingsScene.getStylesheets().add("styles.css");
  }

  // Helper function to create the save preset screen
  public void createSavePresetScreen(Stage primaryStage) {
    Label savePresetLabel = new Label("Preset Name:");
    HBox savePresetHBox = new HBox(10, savePresetLabel, presetTextField);
    // Buttons
    Button saveButton = new Button("Save");
    saveButton.getStyleClass().addAll("save-button");
    saveButton.setOnAction(event -> {
      if (!presetTextField.getText().equals("")) {
        // Add the preset name to the combo box and save the settings in the hashmaps
        String presetName = presetTextField.getText();
        presetComboBox.getItems().addAll(presetName);
        destinationMap.put(presetName, destinationComboBox.getValue());
        pagesMap.put(presetName, pagesComboBox.getValue());
        copiesMap.put(presetName, copiesSpinner.getValue());
        layoutMap.put(presetName, layoutComboBox.getValue());
        twoSidedMap.put(presetName, printOnBothSidesCheckBox.isSelected());
        paperSizeMap.put(presetName, paperSizeComboBox.getValue());
        pagesPerSheetMap.put(presetName, pagesPerSheetComboBox.getValue());
        marginsMap.put(presetName, marginsComboBox.getValue());
        qualityMap.put(presetName, qualityComboBox.getValue());
        scaleMap.put(presetName, scaleComboBox.getValue());
        headersAndFootersMap.put(presetName, headersAndFootersCheckBox.isSelected());
        backgroundGraphicsMap.put(presetName, backgroundGraphicsCheckBox.isSelected());
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
    savePresetScene.getStylesheets().addAll("styles.css");
  }

  // Helper function to create the printing feedback screen
  public void createPrintingFeedbackScreen(Stage primaryStage) {
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
    printingFeedbackScene = new Scene(printingFeedbackVBox, 1000, 150);
    printingFeedbackScene.getStylesheets().addAll("styles.css");
  }
}