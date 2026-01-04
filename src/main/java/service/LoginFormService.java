package service;

import javafx.scene.control.Label;
import modle.dto.User;

public interface LoginFormService {
    String checkLogins(User user);
}
