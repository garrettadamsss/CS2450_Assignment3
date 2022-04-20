import javafx.application.Application;
import javafx.stage.Stage;

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
    // Set the title of the window
    primaryStage.setTitle("Print Page");

    // Show the print page's window
    primaryStage.show();
  }
}