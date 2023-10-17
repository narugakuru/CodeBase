package service10;

import org.springframework.stereotype.Repository;

@Repository("MyTest10")//标注为目标对象
public interface UserService {
    public void add();
    public void delete();
}
