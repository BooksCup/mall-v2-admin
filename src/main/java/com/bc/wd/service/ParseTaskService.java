package com.bc.wd.service;

import com.bc.wd.utils.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @program: whl-project
 * @description:
 * @author: Mr.Wang
 * @create: 2020-04-23 10:49
 **/
@Service
public class ParseTaskService {

    @Autowired
    private HttpUtils httpUtils;

    /**
     * 用来转化金额操作
     */
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 主程序编写,
     *
     * @throws Exception 列如京东手机爬取 修改page 即可,wq,keyword 是 搜索关键字 我写的是鞋子 urlencode 编码
     * https://search.jd.com/Search?keyword=%E9%9E%8B%E5%AD%90&enc=utf-8&wq=%E9%9E%8B%E5%AD%90&page=11
     */
//    @Scheduled(fixedDelay = 1000 * 100)  //间隔100秒执行一次
    public void processJd(int start, int end, String keyword) throws Exception {
        String url = "https://search.jd.com/Search";
        for (int i = start; i < end; i++) {
            String newUrl = url+"?enc=utf-8&keyword="+keyword+"&wq="+keyword+"&page="+i;
            String html = httpUtils.getHtml(newUrl);
            this.parserJdHTML(html);
        }
    }

    private void parserJdHTML(String html) throws IOException {
        //使用Jsoup进行解析页面操作
        Document parse = Jsoup.parse(html);
        //根据页面的标签属性进行获取数据
        Elements select = parse.select("div#J_goodsList > ul > li");
        for (Element element : select) {
//            // spu
//            long spu = Long.parseLong(element.attr("data-spu"));
//            // sku
//            long sku = Long.parseLong(element.attr("data-sku"));
//            // pid
//            long pid = Long.parseLong(element.attr("data-pid"));

            Elements skuEles = element.select("li.ps-item");

            for (Element skuEle : skuEles) {
                //  获取sku
                long sku = Long.parseLong(skuEle.select("[data-sku]").attr("data-sku"));
                //  根据sku查询商品数据
//                Item item = new Item();
//                item.setSku(sku);
//                List<Item> list = this.itemService.findAll(item);
//                if (list.size()>0){
//                    //如果商品存在，就进行下一个循环，该商品不保存，因为已存在
//                    continue;
//                }
                //  设置商品的spu
//                item.setSpu(spu);

                //  获取商品的详情信息
                String itemUrl = "https://item.jd.com/"+sku+".html";
//                item.setUrl(itemUrl);

                //  商品图片
                String picUrl = skuEle.select("img[data-sku]").first().attr("data-lazy-img");
                //	图片路径可能会为空的情况
                if(StringUtils.isEmpty(picUrl)){
                    picUrl =skuEle.select("img[data-sku]").first().attr("data-lazy-img-slave");
                }
                picUrl ="https:"+picUrl.replace("/n9/","/n1/");	//	替换图片格式
                String picName = this.httpUtils.getImage(picUrl);
//                item.setPic(picName);

                //  商品价格
                String priceJson = this.httpUtils.getHtml("https://p.3.cn/prices/mgets?skuIds=J_" + sku);
                double price = OBJECT_MAPPER.readTree(priceJson).get(0).get("p").asDouble();
//                item.setPrice(price);

                //  商品标题
                String itemInfo = this.httpUtils.getHtml(itemUrl);
                String title = Jsoup.parse(itemInfo).select("div.sku-name").text();
                System.out.println(title);
//                item.setTitle(title);

                //  商品创建时间
//                item.setCreated(new Date());
                //  商品修改时间
//                item.setUpdated(item.getCreated());

                //  保存商品数据到数据库中
//                this.itemService.save(item);
            }

//            //商品url
//            jdItem.setUrl("https://item.jd.com/"+sku+".html");
//            //创建时间
//            jdItem.setCreated((java.util.Date) new Date());
//            //更新时间
//            jdItem.setUpdated((java.util.Date) new Date());
//            //获取商品标题
//            String html1 = this.httpUtils.getHtml(jdItem.getUrl());
//            String title = Jsoup.parse(html1).select("div.sku-name").text();
//            jdItem.setTitle(title);
//
//            //获取商品价格
//            String priceurl = "https://p.3.cn/prices/mgets?skuIds=J_"+sku;
//            String html2 = this.httpUtils.getHtml(priceurl);
//            double price = objectMapper.readTree(html2).get(0).get("p").asDouble();
//            jdItem.setPrice(price);
//
//
//            //获取图片价格信息
//            String replace ="https:"+ element1.attr("data-lazy-img").replace("/n9/", "/n1/");
//            String image = this.httpUtils.getImage(replace);
//            System.out.println(image);
//            jdItem.setPic(image);
        }
    }
}
