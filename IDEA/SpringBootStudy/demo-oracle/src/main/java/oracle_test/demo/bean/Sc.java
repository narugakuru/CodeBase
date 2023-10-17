package oracle_test.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@TableName(value = "SCORE2")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sc {
    @TableId(value = "SID" ,type = IdType.AUTO)//在自增主键的变量加上即可
    private Integer sid;
    private Integer cid;
    private float score;
}
