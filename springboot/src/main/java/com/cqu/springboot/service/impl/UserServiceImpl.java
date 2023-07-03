package com.cqu.springboot.service.impl;

import com.cqu.springboot.dto.LoginUserDTo;
import com.cqu.springboot.entity.User;
import com.cqu.springboot.dao.UserDao;
import com.cqu.springboot.service.UserService;
import com.cqu.springboot.utils.VerifyUtil;
import com.cqu.springboot.utils.result.DataResult;
import com.cqu.springboot.utils.result.code.Code;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-06-29 11:17:53
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    HttpSession session;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public DataResult loginUser(User user){
        if(VerifyUtil.isNull(user.getAccount())||VerifyUtil.isNull(user.getPassword())){
            return DataResult.errByErrCode(Code.LOGIN_ERROR);
        }
        User loginUser = userDao.loginUser(user);
        //将用户信息存入session
        if(loginUser == null){
            return DataResult.errByErrCode(Code.NOT_EXIT);
        }
        session.setAttribute("user",loginUser);
        session.setMaxInactiveInterval(60 * 60 * 24);
        LoginUserDTo loginUserDTo = new LoginUserDTo();
        loginUserDTo.setUser(loginUser);
        return DataResult.successByData(loginUserDTo);
    }
}
