package com.xueqiu.mapper;

import java.net.URL;
import java.rmi.RemoteException;

import org.decaywood.mapper.AbstractMapper;
import org.decaywood.timeWaitingStrategy.TimeWaitingStrategy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xueqiu.model.Article;

public class ArticleMapper extends AbstractMapper<URL, Article> {

    public ArticleMapper() throws RemoteException {
        this(null);
    }

    public ArticleMapper(TimeWaitingStrategy strategy) throws RemoteException {
        super(strategy);
    }

    @Override
    protected Article mapLogic(URL t) throws Exception {
        return processNode(t);
    }

    private Article processNode(URL t) {
        try {
            String html = request(t);
            Document document = Jsoup.parse(html);
            Element authorHead = document.select("div.status-single-hd").first();
            Element contentDiv = document.select("div.status-content").first();
            if (authorHead == null || contentDiv == null) {
                return null;
            }
            Article article = new Article();
            article.setOriginURL(t.toString());
            String authorName = authorHead.select("div.status-retweet-user > a").first().text();
            article.setAuthorName(authorName);
            String authorAvatar = authorHead.select("a > img").first().attr("src");
            article.setAuthorAvatar(authorAvatar);
            String date = authorHead.select("div.subtitle > a").first().text();
            article.setPublishDate(date);
            Elements titleEle = contentDiv.select("h1.status-title");
            if (titleEle == null || titleEle.isEmpty()) {
                return null;
            }
            article.setTitle(titleEle.first().text());
            String content = contentDiv.select("div.detail").first().text();
            article.setContent(content);
            return article;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
