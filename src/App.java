
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    private Scene scene;
    private HBox passwordArea;
    private HBox emailArea;
    private VBox container;
    private Button submit;
    private EventHandler<ActionEvent> eventHandler = action -> validate();
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.container = new VBox(new Text("Registration Form"));
        this.passwordArea = createField("Password");
        this.emailArea  = createField("Email");
        this.container.getChildren().add(emailArea);
        this.container.getChildren().add(passwordArea);

        this.submit = new Button("Register");
        this.submit.setOnAction(eventHandler);
        this.container.getChildren().add(submit);
        this.scene = new Scene(container, 300, 250);
        primaryStage.setScene(scene);   
        primaryStage.show();

    }

    private static HBox createField(String labelName) {
        TextField field = new TextField();
        Label fieldLabel  = new Label(labelName + ":");
        HBox fieldArea = new HBox(fieldLabel);
        fieldArea.getChildren().add(field);
        return fieldArea;
    }

    private void validate() {
        var emailField = (TextField) this.emailArea.lookup("");
        var passwordField = (TextField) this.emailArea.lookup("");
        var text = emailField.getText();
        if(text == "" || text == null){
            this.showError("Email is missing");
            return;
        }
        if(!text.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")){
            this.showError("Email is in incorrect form");
            return;
        }

        text = passwordField.getText();
        if(text == "" || text == null){
            this.showError("Password is missing");
            return;
        }
        if(text.length() < 7){
            this.showError("Password is in incorrect form: Should be at least 7 letters long");
            return;
        }
        if(!text.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\*\\^\\&\\@\\!])")){
            this.showError("Password is in incorrect form: Does not contain the following - at least 1 letter and number; at least 1 character from *^&@!");
            return;
        }
        this.showSuccess();
    }

    private void showError(String value){
        this.container.getChildren().add(new Text(value));
    }
    private void showSuccess(){
        this.container.getChildren().add(new Text("Successfully registered"));
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}