package com.river.toutiao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class IndexController {
	@RequestMapping(value="/c/v1/index")
	@ResponseBody
	public String index() {
		Map<String, Object> map= new HashMap<>();
		map.put("check", "OK");
		map.put("code", 0);
		return JSONObject.toJSONString(map);
	}
}
