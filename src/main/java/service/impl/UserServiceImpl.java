package service.impl;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by shilc on 2016/2/22.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    public final static org.apache.log4j.Logger log= org.apache.log4j.Logger.getLogger(UserServiceImpl.class);

    @Override
    public List<User> GetUser()
    {
        log.error("error-------测试");
        log.info("info-------测试");
        log.debug("debug-------测试");
        return userDao.GetUser();
    }
    @Override
    public int InsertUser(User user)
    {
        return userDao.InsertUser(user);
    }
    @Override
    public int UpdateUser(User user)
    {
        return userDao.UpdateUser(user);
    }
    @Override
    public int DeleteUser(String code)
    {
        return userDao.DeleteUser(code);
    }

}
