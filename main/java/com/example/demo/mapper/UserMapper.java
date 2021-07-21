package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    //tianjia
    public int addUser(User user);

    public User getUserById(int id);

    public User getUserByNameAndPassword( String username,
                                         String password);
    public List<User> getAll();

    public int delByIdInt(int id);

    public User getFullUser(int id);
}
