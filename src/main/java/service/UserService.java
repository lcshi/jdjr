package service;
import model.User;

import java.util.List;

/**
 * Created by shilc on 2016/2/22.
 */
public interface UserService {
    /**
     * 查询用户
     * */
    public List<User> GetUser();
    /**
     * 添加新用户
     * */
    public int InsertUser(User user);
    /**
     * 更新用户
     * */
    public int UpdateUser(User user);
    /**
     * 删除用户
     * */
    public int DeleteUser(String code);
}
