package oracle_test.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import oracle_test.demo.bean.SalGrade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalGradeMapper extends BaseMapper<SalGrade> {

}
