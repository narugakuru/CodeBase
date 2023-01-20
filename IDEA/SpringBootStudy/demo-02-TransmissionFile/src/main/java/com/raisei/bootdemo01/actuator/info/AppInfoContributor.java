package com.raisei.bootdemo01.actuator.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.Timer;

@Component
public class AppInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Date now = new Date();
        String nowTime = DateFormat.getDateInstance().format(now);
        builder.withDetail("time", nowTime).
                withDetail("hello","world");
    }
}
