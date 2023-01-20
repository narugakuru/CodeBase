package oracle_test.demo.bean;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value = "dept")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {

    @TableId(value = "DEPTNO",type = IdType.AUTO)//在自增主键的变量加上即可
    private Integer deptno;
    private String dname;
    private String loc;
}
