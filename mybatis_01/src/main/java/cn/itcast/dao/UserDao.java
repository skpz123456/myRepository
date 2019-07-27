package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;

public interface UserDao {

    /**
     * 向User表中添加一条数据
     * @param user 记录对应的实体类
     */
    void addUser(User user);

    /**
     * 修改User表中的数据信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 通过id删除用户
     * @param id
     */
    void delUser(Integer id);

    /**
     * 查询user表的所有的记录
     * @return
     */
    List<User> findAll();

    /**
     * 根据name查询用户记录
     * @param username
     * @return
     */
    List<User> findUser(String username);
}
