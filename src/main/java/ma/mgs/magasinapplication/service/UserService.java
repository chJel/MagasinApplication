package ma.mgs.magasinapplication.service;

import ma.mgs.magasinapplication.dao.Impl.UserDaoImpl;
import ma.mgs.magasinapplication.dao.Impl.UserDaoImplImpl;
import ma.mgs.magasinapplication.entities.User;

public class UserService {
    UserDaoImpl userDao = new UserDaoImplImpl();

    public boolean auth(User user){
        return userDao.auth(user);
    }
}
