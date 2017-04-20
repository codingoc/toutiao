package com.river.toutiao.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.decaywood.collector.HuShenNewsRefCollector;
import org.decaywood.collector.HuShenNewsRefCollector.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class IndexController {
	
	private List<URL> hotNewsList() {
		try {
			HuShenNewsRefCollector collector = new HuShenNewsRefCollector(Topic.TOTAL, 5);
			List<URL> list = collector.get();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
    @RequestMapping(value = "/c")
    public ModelAndView index() {
    	List<URL> hotNewsList = hotNewsList();
    	ModelAndView view = new ModelAndView("toutiao");
    	view.addObject("hotNewsURLList", hotNewsList);
        return view;
    }
}
