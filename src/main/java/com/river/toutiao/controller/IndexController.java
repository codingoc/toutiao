package com.river.toutiao.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.decaywood.collector.MostProfitableCubeCollector;
import org.decaywood.entity.Cube;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class IndexController {
    @RequestMapping(value = "/c/v1/index")
    @ResponseBody
    public String index() throws RemoteException {
        MostProfitableCubeCollector collector = new MostProfitableCubeCollector();
        List<Cube> cubes = collector.get();
        return JSONObject.toJSONString(cubes, true);
    }

    public static void main(String[] args) throws RemoteException {
        MostProfitableCubeCollector collector = new MostProfitableCubeCollector();
        List<Cube> cubes = collector.get();
        cubes.forEach((Cube cube) -> System.out.println(JSONObject.toJSONString(cube)));
    }
}
