package a000_SpringBasics.mapper;

import a000_SpringBasics.dao.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Chenyu Liu
 * @since 3/9/25 Sunday
 **/

@Mapper
public interface UserMapper {

    @Select("select user_id, name, age, gender from users")
    List<User> selectAllUsers();

    @Select("select user_id, name, age, gender from users where name = #{name}")
    List<User> selectByUsername(@Param("name") String name);

    @Select("select user_id, name, age, gender from users where name LIKE CONCAT('%' ,#{name}, '%')")
    List<User> selectByUsernameLike(@Param("name") String name);

    @Insert("insert into users (user_id,name,age,gender) values (#{userId},#{name},#{age},#{gender})")
    int insertUser(User user);
}
