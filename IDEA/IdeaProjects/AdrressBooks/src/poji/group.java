package poji;

/**
 * @ProjectName: AdrressBooks
 * @Package: dao
 * @ClassName: group
 * @Author: JN
 * @Description: 1
 * @Date: 2020/11/27 8:43
 * @Version: 1.0
 */
public class group {

    private String name;

    public group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "group{" +
                "name='" + name + '\'' +
                '}';
    }
}
