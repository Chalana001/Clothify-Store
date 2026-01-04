package Repository;

import modle.entity.UserEntity;

public interface LoginFormRepository {
    UserEntity checkUser(UserEntity userEntity);
}
