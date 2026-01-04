package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modle.dto.User;
import service.Impl.LoginFormServiceImpl;
import service.LoginFormService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInFormController implements Initializable{

    private LoginFormService loginFormService = new LoginFormServiceImpl();

    @FXML
    private Button btnLogIn;

    @FXML
    private Label lblErrorMsg;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void LogInButtonOnAction(ActionEvent event) {
        String result = loginFormService.checkLogins(new User(txtUserName.getText(),txtPassword.getText()));
        lblErrorMsg.setText(result);
        Stage homeStage = new Stage();

        if ("log in Successfull..".equals(result)) {

            // get current stage
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            stage.close();

            try {
                homeStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/vBoxForm.fxml"))));
                homeStage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> rootPane.requestFocus());
    }
}
