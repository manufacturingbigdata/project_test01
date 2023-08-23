package com.example.test01;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.Charset;

@Controller
public class HelloSpringBoot {
    @ResponseBody
    @RequestMapping("/")
    public String hello() {
        return "Hello Spring Boot spring branch!!";
    }
    @ResponseBody
    @RequestMapping("/rest_call")
    public String rest_call() throws Exception{
        //호출 할 Flask의 URL 설정
        HttpPost httpPost = new HttpPost("http://192.168.91.101:3001");
        //Flask 에 접속해서 실행 결과를 가져올 객체 생성
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //Flask에 접속해서 실행 결과를 가져옴
        CloseableHttpResponse response2 = httpclient.execute(httpPost);
        //Flask 실행 결과를 가져옴
        String flaskResult =
                EntityUtils.toString(response2.getEntity(),
                        Charset.forName("UTF-8"));
        System.out.println("flaskResult=" + flaskResult);

        //Flask 서버와 연결 종료
        httpclient.close();
        return flaskResult;
    }

}
