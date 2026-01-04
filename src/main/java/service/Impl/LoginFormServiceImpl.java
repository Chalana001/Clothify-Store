package service.Impl;

import Repository.Impl.LoginFormRepositoryImpl;
import Repository.LoginFormRepository;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modle.dto.User;
import modle.entity.UserEntity;
import service.LoginFormService;

import java.io.IOException;

public class LoginFormServiceImpl implements LoginFormService {

    private LoginFormRepository loginFormRepository = new LoginFormRepositoryImpl();
    private UserEntity userfromdb;

    Stage home = new Stage();

    @Override
    public String checkLogins(User user) {

        UserEntity userEntity = new UserEntity(user.getUserName(), user.getPassword());
        userfromdb = loginFormRepository.checkUser(userEntity);

        if (userfromdb!=null){
            if (userEntity.getPassword().equals(userfromdb.getPassword())) {
                return "log in Successfull..";

            } else {
                System.out.println("user entity - "+userEntity.getPassword());
                System.out.println("user db - "+userfromdb.getPassword());
                return "Wrong Password..";
            }
        }
        return "User Not Exits..";
    }
}
