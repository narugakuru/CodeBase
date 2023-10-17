package oracle_test.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import oracle_test.demo.bean.Sc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScoreMapper extends BaseMapper<Sc> {

    @Insert("insert into SCORE2 values(#{sid},#{cid},#{score})")
    int insertAll(Sc score);
}
