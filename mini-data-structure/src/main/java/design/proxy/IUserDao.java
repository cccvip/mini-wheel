package design.proxy;

public interface IUserDao {
    @Select("select count(*) from sys_user")
    String selectUser(String userId);
}
