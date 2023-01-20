package oracle_test.demo.service;

import lombok.RequiredArgsConstructor;
import oracle_test.demo.bean.Sc;
import oracle_test.demo.mapper.ScoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService{

//    @Autowired
    @Resource
    private ScoreMapper scoreMapper;

    public int insert(Sc sc){
        return scoreMapper.insert(sc);
    }

}
