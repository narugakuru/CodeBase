package com.raisei.hello.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*
@PathVariable（路径变量,单个或map所有）
@RequestHeader（获取请求头，单个或map所有）
@RequestParam（获取请求参数，单个或map所有）
@CookieValue（获取cookie值）
@RequestBody（获取请求体[POST]，单个或map所有）
@RequestAttribute（获取request域属性）
@MatrixVariable（矩阵变量）
*/

@RestController
public class ParameterTestController {

    @RequestMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("username") String name,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader Map<String,String> header
                                      ) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("all",pv);
        map.put("header",header);
        return map;
    }

    @RequestMapping("/user")
    public Map<String,Object> getParam(@RequestParam("username") String name){
        Map<String, Object> map = new HashMap<>();
        map.put("username",name);
        return map;
    }

    @PostMapping("/save")
    public Map<String,Object> getBody(@RequestBody String from){
        Map<String, Object> map = new HashMap<>();
        map.put("form",from);
        return map;
    }

}
