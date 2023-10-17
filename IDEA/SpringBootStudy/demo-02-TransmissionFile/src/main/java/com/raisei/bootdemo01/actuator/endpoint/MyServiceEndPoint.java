package com.raisei.bootdemo01.actuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
@Endpoint(id = "myService")
public class MyServiceEndPoint{

    @ReadOperation
    public Map<String, String> getDocker(){
        return Collections.singletonMap("docker","docker is start.....");
    }

    @WriteOperation
    public void stopDocker(){
        System.out.println("docker stopped....");
    }
}
