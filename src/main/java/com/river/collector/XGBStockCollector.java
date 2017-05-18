package com.river.collector;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.decaywood.collector.AbstractCollector;
import org.decaywood.entity.Stock;
import org.decaywood.utils.RequestParaBuilder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 选股宝股票收集器
 * @author cc
 *
 */
public class XGBStockCollector extends AbstractCollector<List<Stock>> {

    public static final String URL = "http://bao.wallstreetcn.com";

    public XGBStockCollector() throws RemoteException {
        super(null, URL);
    }

    @Override
    public List<Stock> collectLogic() throws Exception {

        String target = URL + "/api/discover/subjects";
        RequestParaBuilder builder = new RequestParaBuilder(target);
        URL url = new URL(builder.build());
        String json = request(url);
        JSONObject node = JSON.parseObject(json);
        return processNode(node);

    }

    private List<Stock> processNode(JSONObject node) {
        List<Stock> stocks = new ArrayList<>();
        JSONArray section4 = node.getJSONArray("Section4");
        for (int i = 0; i < section4.size(); i++) {
            JSONObject temp = section4.getJSONObject(i);
            JSONArray featuredMsgStocks = temp.getJSONArray("FeaturedMsgStocks");
            String comment = temp.getString("Title") + "-" + temp.getString("LatestMsgTitle");
            String originURL = temp.getString("ShareUrl");
            for (int j = 0; j < featuredMsgStocks.size(); j++) {
                temp = featuredMsgStocks.getJSONObject(j);
                String stockName = temp.getString("Name");
                String stockSymbol = temp.getString("Symbol");
                Stock stock = new Stock(stockName, stockSymbol);
                stock.setComment(comment);
                stock.setOriginURL(originURL);
                stocks.add(stock);
            }
        }
        return stocks;
    }

}
