package cn.itcast.dao;


import cn.itcast.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserTest {
    InputStream is = null;
    SqlSession ss = null;
    UserDao userDao = null;

    // before注解表示在方法执行之前执行
    @Before
    public void init() throws IOException {
        // 加载核心配置文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactoryBuilder构建者对象
        SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
        // 使用创建者创建工厂对象SqlSessionFactory
        SqlSessionFactory sf = sfb.build(is);
        // 使用工厂创建SqlSession对象
        ss = sf.openSession();
        // 使用SqlSession对象创建dao接口的代理对象
        userDao = ss.getMapper(UserDao.class);
    }

    // after注解表示在方法执行之后执行
    @After
    public void destroy() throws IOException {
        // 提交事务
        ss.commit();
        // 关闭资源
        ss.close();
        is.close();
    }

    /**
     * 测试添加用户功能
     */
    @Test
    public void addUserTest() {
        User user = new User();
        user.setUsername("张三");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("武汉");
        userDao.addUser(user);
    }

    /**
     * 测试修改用户信息功能
     */
    @Test
    public void updateUserTest() {
        User user = new User();
        user.setUsername("迪丽热巴");
        user.setSex("女");
        user.setId(49);
        userDao.updateUser(user);
    }

    /**
     * 测试根据id删除一条记录的方法
     */
    @Test
    public void delUserTest() {
        userDao.delUser(48);
    }

    /**
     * 测试查询User中所有记录的方法,并遍历结果
     */
    @Test
    public void findAllTest() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试根据username查询User中记录的方法,并遍历结果
     */
    @Test
    public void findUserTest() {
        List<User> users = userDao.findUser("老王");
        for (User user : users) {
            System.out.println(user);
        }
    }



}
