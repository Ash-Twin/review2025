package a000_SpringBasics.service;

import a000_SpringBasics.dao.Transaction;
import a000_SpringBasics.dao.User;
import a000_SpringBasics.mapper.TransactionMapper;
import a000_SpringBasics.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chenyu Liu
 * @since 3/10/25 Monday
 **/

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public int createUser(User.Dto userDto){
        return userMapper.insertUser(userDto.toUser());
    }

    public List<User> getAllUsers(){
        return userMapper.selectAllUsers();
    }

}
