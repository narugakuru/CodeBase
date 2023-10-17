package com.demo.mybatisdemo.service.impl;

import com.demo.mybatisdemo.entity.User;
import com.demo.mybatisdemo.mapper.UserMapper;
import com.demo.mybatisdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Raisei
 * @since 2022-12-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
