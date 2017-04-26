package com.river.toutiao.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.decaywood.collector.HuShenNewsRefCollector;
import org.decaywood.collector.HuShenNewsRefCollector.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.river.toutiao.util.RedisUtil;
import com.xueqiu.mapper.ArticleMapper;
import com.xueqiu.model.Article;

@Controller
public class IndexController {

    private List<URL> hotNewsList() {
        try {
            RedisUtil redisUtil = RedisUtil.sharedUtil();
            String jsonArr = redisUtil.getString("news");
            if (jsonArr != null && jsonArr.length() > 0) {
                List<URL> urls = JSONArray.parseArray(jsonArr, URL.class);
                return urls;
            }
            HuShenNewsRefCollector collector = new HuShenNewsRefCollector(Topic.TOTAL, 5);
            List<URL> list = collector.get();
            redisUtil.setString("news", JSONArray.toJSONString(list));
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @RequestMapping(value = "/c")
    public ModelAndView index() throws RemoteException {
        List<URL> hotNewsList = hotNewsList();
        ArticleMapper mapper = new ArticleMapper();
        List<Article> articles = hotNewsList.parallelStream().map(mapper).collect(Collectors.toList());
        ModelAndView view = new ModelAndView("toutiao");
        view.addObject("hotNewsArticles", articles);
        return view;
    }
}
