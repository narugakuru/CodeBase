package com.raisei.bootdemo01.mapper;

import com.raisei.bootdemo01.bean.AccountBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


public interface AccountMapper {

    public AccountBean getAcct(@Param("id") Integer id);


    public Integer insertAcct(AccountBean accountBean);

}
