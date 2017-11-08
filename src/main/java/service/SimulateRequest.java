package service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulateRequest {
    private static final Logger logger = LoggerFactory.getLogger(SimulateRequest.class);

    // private static  CloseableHttpClient httpClient = HttpClients.createDefault();

    private static String COOKIE = null;

    /**
     * 获取验证码
     */
    private static String getImageCode() throws TesseractException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://222.85.136.14/web/App_Common/getCode.aspx");
        get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        get.setHeader("Accept-Encoding", "gzip, deflate");
        get.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        get.setHeader("Connection", "keep-alive");
        get.setHeader("Host", "222.85.136.14");
        get.setHeader("Referer", "http://222.85.136.14/web/QueryScore.aspx");
        get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3236.0 Safari/537.36");
        CloseableHttpResponse response = null;
        String result = null;
        response = httpClient.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            //获得首部---当然也可以使用其他方法获取
            Header[] hs = response.getAllHeaders();
            for (Header h : hs) {
                // System.out.println(h.getName() + ":\t" + h.getValue() + "\n");

                //获取cookie
                if (h.getName().equals("Set-Cookie")) {
                    COOKIE = h.getValue();
                    //ASP.NET_SessionId=dvtya3453w2zbsac4tfnmj55; path=/; HttpOnly
                    COOKIE = COOKIE.substring(0, COOKIE.indexOf(";"));
                }
            }

            //解析验证码
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = entity.getContent();
                String fileName = "D://test/" + System.currentTimeMillis() + ".gif";
                FileUtils.copyToFile(inputStream, new File(fileName));

                //识别验证码
                File imageFile = new File(fileName);
                Tesseract instance = new Tesseract();
                //将验证码图片的内容识别为字符串
                result = instance.doOCR(imageFile);
                result = result.trim();
                if (result.length() > 4) {
                    result = result.substring(0, 4);
                }
                logger.info("验证码：{}", result);
            }
        } else {
            logger.info("请求异常statusCode：{}", statusCode);
        }
        response.close();
        httpClient.close();
        return result;
    }

    private static Integer postQueryScore(String imageCode) throws Exception {
        Integer code = 200;
        //post请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://222.85.136.14/web/QueryScore.aspx");
        post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        post.setHeader("Accept-Encoding", "gzip, deflate");
        post.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        post.setHeader("Cache-Control", "max-age=0");
        post.setHeader("Connection", "keep-alive");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setHeader("Cookie", COOKIE);
        post.setHeader("Host", "222.85.136.14");
        post.setHeader("Origin", "http://222.85.136.14");
        post.setHeader("Proxy-Connection", "keep-alive");
        post.setHeader("Referer", "http://222.85.136.14/web/QueryScore.aspx");
        post.setHeader("Upgrade-Insecure-Requests", "1");
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3236.0 Safari/537.36");
        List<NameValuePair> arrayList = new ArrayList<>();
        arrayList.add(new BasicNameValuePair("__VIEWSTATE", "/wEPDwUKMTM5OTI1OTczOWQYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgEFCUNoZWNrQm94MTgt76X8rRNpGaLLZq0PAkfMGM5n"));
        arrayList.add(new BasicNameValuePair("__EVENTVALIDATION", "/wEWBwLujI+5DQL/8f6fBwKklZejAgLEhISFCwKY2YWXBgKC5Ne7CQLvjry/BZBDS1u5kladxYSYSST/3FWxBqns"));
        arrayList.add(new BasicNameValuePair("txtStudentid", "210014115233"));
        arrayList.add(new BasicNameValuePair("txtIdentityNo", "431222199209291031"));
        arrayList.add(new BasicNameValuePair("txtName", "张棋林"));
        arrayList.add(new BasicNameValuePair("txtCheckCode", imageCode));
        arrayList.add(new BasicNameValuePair("btnQuery", "查询"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(arrayList, HTTP.DEF_CONTENT_CHARSET);
        post.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == 302) {
            logger.info("postQueryScore 状态码：302");
            code = 302;
        } else if (response.getStatusLine().getStatusCode() == 200) {
            logger.info("postQueryScore 状态码：200");
        }
        response.close();
        httpClient.close();
        return code;
    }

    private static void getQueryScore() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // URIBuilder uriBuilder = new URIBuilder("http://222.85.136.14/web/QueryScore.aspx");
        HttpGet get = new HttpGet("http://222.85.136.14/web/QueryScore.aspx");
        get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        get.setHeader("Accept-Encoding", "gzip, deflate");
        get.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        get.setHeader("Cache-Control", "max-age=0");
        get.setHeader("Connection", "keep-alive");
        get.setHeader("Host", "222.85.136.14");
        get.setHeader("Upgrade-Insecure-Requests", "1");
        get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3236.0 Safari/537.36");

        CloseableHttpResponse response = httpClient.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        logger.info("第一次 getQueryScore 状态码：{}", statusCode);
        //获得首部---当然也可以使用其他方法获取
        // Header[] hs = response.getAllHeaders();
        // for (Header h : hs) {
        //     System.out.println(h.getName() + ":\t" + h.getValue() + "\n");
        // }
        response.close();
        httpClient.close();

    }

    private static String getQueryScore(String imageCode) throws Exception {
        String html = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        URIBuilder uriBuilder = new URIBuilder("http://222.85.136.14/web/QueryScoreResult.aspx");
        uriBuilder.addParameter("Studentid", "210014115233");
        uriBuilder.addParameter("CertificateNo", "431222199209291031");
        uriBuilder.addParameter("Name", "张棋林");
        uriBuilder.addParameter("CheckCode", imageCode);
        uriBuilder.addParameter("Flag", "F");

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        get.setHeader("Accept-Encoding", "gzip, deflate");
        get.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        get.setHeader("Cache-Control", "max-age=0");
        get.setHeader("Connection", "keep-alive");
        get.setHeader("Cookie", COOKIE);
        get.setHeader("Host", "222.85.136.14");
        get.setHeader("Referer", "http://222.85.136.14/web/QueryScore.aspx");
        get.setHeader("Upgrade-Insecure-Requests", "1");
        get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3236.0 Safari/537.36");

        CloseableHttpResponse response = httpClient.execute(get);
        // HttpEntity entity = response.getEntity();


        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200) {
            html = EntityUtils.toString(response.getEntity());
        }
        response.close();
        httpClient.close();
        return html;
    }


    public static void main(String[] args) throws Exception {
        //存储结果
        Map<Integer, List<String>> map = new HashMap<>();

        //get请求
        getQueryScore();

        //获取验证码
        String imageCode = getImageCode();
        while (!StringUtils.isNumeric(imageCode)) {
            imageCode = getImageCode();
        }
        //Integer code = postQueryScore(imageCode);

        //if (code == 302){
        String html = getQueryScore(imageCode);
        //解析Html
        if (html != null) {
            Document root = Jsoup.parse(html);
            String title = root.title();
            if("成绩查询".equals(title.trim())){

            }else{
                Element body = root.body();
                Elements allElements = body.getAllElements();
                // Integer[] integers = {31,38,45,52,59,66,73,80,87,94};
                int start = 31 - 7;
                List<Integer> integers = new ArrayList<>();
                //最大值为功课数
                for (int i = 0; i < 17; i++) {
                    start += 7;
                    integers.add(start);
                }
                for (Integer index : integers) {
                    Element element = allElements.get(index);
                    List<String> elementList = getElementList(element);
                    map.put(index, elementList);
                }
            }

            //}
        }


        System.out.println(map.size());
    }

    private static List<String> getElementList(Element element) {
        List<String> list = new ArrayList<>();
        Elements tds = element.getElementsByTag("td");
        for (Element td : tds) {
            List<Node> nodes = td.childNodes();
            Node node = nodes.get(0);
            String text = node.attr("text");
            list.add(text.trim());
        }
        return list;
    }
}
