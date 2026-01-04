package Repository.Impl;

import Repository.LoginFormRepository;
import modle.entity.UserEntity;
import org.hibernate.Session;
import utill.HibernateUtill;

public class LoginFormRepositoryImpl implements LoginFormRepository {

    Session session = HibernateUtill.getSession();

    @Override
    public UserEntity checkUser(UserEntity userEntity) {
        UserEntity user = session.find(UserEntity.class, userEntity.getUserName());
        return user;
    }
}
