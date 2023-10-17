package oracle_test.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.function.IntToDoubleFunction;

@TableName(value = "salgrade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalGrade {
    @TableId(value = "grade",type = IdType.AUTO)//在自增主键的变量加上即可
    private Integer grade;
    private Integer losal;
    private Integer hisal;
}
