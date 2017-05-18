package com.river.toutiao.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.decaywood.collector.HuShenNewsRefCollector;
import org.decaywood.collector.HuShenNewsRefCollector.Topic;
import org.decaywood.collector.StockScopeHotRankCollector.Scope;
import org.decaywood.entity.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.river.collector.XGBStockCollector;
import com.river.toutiao.util.RedisUtil;
import com.xueqiu.mapper.ArticleMapper;
import com.xueqiu.model.Article;

@Controller
public class IndexController {

    /**
     * 最热新闻
     * @author: cc
     * @Title: hotNewsList
     * @return
     * List<URL>    返回类型
     */
    private List<URL> hotNewsList() {
        try {
            RedisUtil redisUtil = RedisUtil.sharedUtil();
            String jsonArr = redisUtil.getString("news");
            if (jsonArr != null && jsonArr.length() > 0) {
                List<URL> urls = JSONArray.parseArray(jsonArr, URL.class);
                return urls;
            }
            HuShenNewsRefCollector collector = new HuShenNewsRefCollector(Topic.TOTAL, 1);
            List<URL> list = collector.get();
            redisUtil.setString("news", JSONArray.toJSONString(list));
            // 1小时后过期
            redisUtil.expire("news", 3600 * 1);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 返回新闻头条数据
     * @author: cc
     * @Title: index
     * @return
     * @throws RemoteException
     * ModelAndView    返回类型
     */
    @RequestMapping(value = "/t")
    public ModelAndView toutiaoListPage() throws RemoteException {
        List<URL> hotNewsList = hotNewsList();
        ArticleMapper mapper = new ArticleMapper();
        List<Article> articles = hotNewsList.parallelStream().map(mapper).filter(Objects::nonNull)
                .collect(Collectors.toList());
        ModelAndView view = new ModelAndView("toutiao");
        view.addObject("hotNewsArticles", articles);
        return view;
    }

    /**
     * 热门股票列表
     * @author: cc
     * @Title: hotStockList
     * @param scope 市场类别
     * @return
     * List<Stock>    返回类型
     * @throws RemoteException 
     */
    private List<Stock> hotStockList(Scope scope) {
        XGBStockCollector collector;
        try {
            collector = new XGBStockCollector();
            List<Stock> stocks = collector.get();
            return stocks;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 返回热门stock
     * @author: cc
     * @Title: stock
     * @return
     * ModelAndView    返回类型
     */
    @RequestMapping(value = "/s")
    public ModelAndView stockListPage() {
        List<Stock> stocks = hotStockList(Scope.SH_SZ_WITHIN_1_HOUR);
        ModelAndView view = new ModelAndView("stock");
        view.addObject("hotStocksList", stocks);
        return view;
    }
}
