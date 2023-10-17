package service10;

import org.springframework.stereotype.Repository;

@Repository()//标注为目标对象
public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("增加了一个用户");
    }
    @Override
    public void delete() {
        System.out.println("删除了一个用户");
    }
}
