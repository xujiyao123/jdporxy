package com.xujiyao.jdporxy.service.impl;

import com.xujiyao.jdporxy.dao.JDDao;
import com.xujiyao.jdporxy.eniity.JDEntity;
import com.xujiyao.jdporxy.exception.DataNoteFoundException;
import com.xujiyao.jdporxy.service.JDProxyService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.apache.http.client.HttpClient;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 42224 on 2017/6/17.
 */
@Service
public class JDProxyServiceImpl implements JDProxyService {


    public static final Log logger = LogFactory.getLog(JDProxyServiceImpl.class);

    @Resource
    private JDDao jdDao;


    public Object doPorxy(String keyword ,Integer page) throws  Exception {
        HttpClient client = new DefaultHttpClient();
        String url="http://search.jd.com/Search?keyword="+ keyword+"&enc=utf-8&qrst=1&rt=1&stop=1&book=y&vt=2&wq=Python&page="+ page +"&s=481&click=0";
        List<JDEntity> books= urlParser(client, url);
         batchInsertBooks(books);
        return books;
    }



    private List<JDEntity> urlParser (HttpClient client, String url) throws Exception {
        List<JDEntity>  books = new ArrayList<JDEntity>();
        HttpResponse response = getRawHtml(client, url);
        int StatusCode = response.getStatusLine().getStatusCode();
        if(StatusCode == 200){
            String entity = EntityUtils.toString (response.getEntity(),"utf-8");
            books = getData(entity);
            EntityUtils.consume(response.getEntity());
        }else {
            EntityUtils.consume(response.getEntity());
        }
        return books;
    }

    private HttpResponse getRawHtml(HttpClient client, String personalUrl) {
        HttpGet getMethod = new HttpGet(personalUrl);
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                HttpStatus.SC_OK, "OK");
        try {
            response = client.execute(getMethod);
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            // getMethod.abort();
        }
        return response;
    }
    @Transactional
    private Integer batchInsertBooks(List<JDEntity> books) throws DataNoteFoundException {

        for (JDEntity jd:books) {
            logger.info("bookID:"+jd.getBookID()+"\t"+"bookPrice:"+jd.getBookPrice()+"\t"+"bookName:"+jd.getBookName());
        }
        Integer d =  jdDao.batchInsertBook(books);
        if (d == books.size()) {
            return d;
        }else {
            throw new DataNoteFoundException("插入失败！");
        }
    }

    private  List<JDEntity> getData (String html) throws Exception{
        List<JDEntity> data = new ArrayList<JDEntity>();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
        for (Element ele:elements) {
            String bookID = ele.attr("data-sku");
            String bookPrice = ele.select("div[class=p-price]").select("strong").select("i").text();
            String bookName = ele.select("div[class=p-name]").select("em").text();
            String img = ele.select("div[class=p-img]").select("img").attr("src");
            if (img.equals("")){
                img = ele.select("div[class=p-img]").select("img").attr("data-lazy-img");
            }
            JDEntity jdModel=new JDEntity();
            jdModel.setBookID(bookID);
            jdModel.setBookName(bookName);
            jdModel.setBookPrice(bookPrice);
            jdModel.setImgsrc(img);
            data.add(jdModel);
        }
        return data;
    }





}
