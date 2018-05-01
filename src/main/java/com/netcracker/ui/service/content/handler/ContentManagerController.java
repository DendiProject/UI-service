/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.content.handler;

import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.filters.RestFilter;
import com.netcracker.ui.service.security.SecurityTokenHandler;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Artem
 */
@EnableZuulServer
public class ContentManagerController {

    private String connectionUrl;
    BeansFactory<SecurityTokenHandler> bfTK = BeansFactory.getInstance();
    SecurityTokenHandler tokenStore;
    
    public ContentManagerController(String connectionUrl)
    {
        this.connectionUrl = connectionUrl;
        //http://localhost:8082/
    }
    
     public String getImage(String id) {
    return connectionUrl + "/file/getfile/" + id;//"http://localhost:8082/file/getfile/41e9d7a1-966f-43ce-a860-71745e3d5fc9"
  }
    

  

 

  public String addImage(String filePath) throws NotFoundBean, FileNotFoundException, IOException {
    try {
      tokenStore = bfTK.getBean(SecurityTokenHandler.class);
      CookieHandler cookieHandler = new CookieHandler();
      File file = new File(filePath);
      String[] subString = filePath.split(Pattern.quote("\\"));
      subString = subString[subString.length - 1].split("\\.");
      String fileName = subString[0];
      String type = "image/" + subString[1];
      String size = String.valueOf(file.length());
      String extension = subString[1];

      MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
      parameters.add("fileName", fileName);
      parameters.add("type", type);
      parameters.add("size", size);
      parameters.add("extension", extension);
      //Запрос на получение id для новой картинки
      BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
      RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
      restTemplate.setInterceptors(Collections.singletonList(new RestFilter()));
      HttpHeaders headers = new HttpHeaders();
      headers.set("Accept=application/json", MediaType.APPLICATION_JSON_VALUE);
      headers.setContentType(MediaType.APPLICATION_JSON);

      UriComponentsBuilder builder = UriComponentsBuilder
              .fromHttpUrl(connectionUrl + "node/addnodeimg").queryParams(parameters);

      HttpEntity<?> entity = new HttpEntity<>(headers);
      HttpEntity<String> response = restTemplate.exchange(
              builder.build().encode().toUri(),
              HttpMethod.POST,
              entity,
              String.class);
      System.out.println("**нода успешно добавлена на UI**");
      JSONObject result = new JSONObject(response.getBody());
      String id = result.getString("id");

      HttpClient httpclient = new DefaultHttpClient();
      HttpPost httppost = new HttpPost(connectionUrl + "file/addfile/" + id);
      //"src\\main\\webapp\\WEB-INF\\images\\slide1.png"
      MultipartEntity mpEntity = new MultipartEntity();
      ContentBody cbFile = new FileBody(file, "multipart/form-data");
      mpEntity.addPart("file", cbFile);
      httppost.setEntity(mpEntity);
      httppost.addHeader("userCookie", cookieHandler.getCookieByName("userInfo").getValue());
      httppost.addHeader("service", "ui");
      httppost.addHeader("secureToken", tokenStore.getToken());
      HttpResponse response2 = httpclient.execute(httppost);
      httpclient.getConnectionManager().shutdown();
      System.out.println(id);
      return id;
    } catch (IOException ex) {
      System.out.println(ex);
      return "error";
    }
  }

//  public String getFile() throws IOException {
//    try {
//
//      HttpClient httpclient = new DefaultHttpClient();
//      HttpGet httppost = new HttpGet("http://localhost:8081/file/getfile/130dd111-8a67-4630-b082-07de12fed38a");
//      HttpResponse response2 = httpclient.execute(httppost);
//      httpclient.getConnectionManager().shutdown();
//
//      System.out.println(response2.getAllHeaders());
//    } catch (NotFoundBean ex) {
//      Logger.getLogger(ContentManagerController.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    return "getFile Class";
//  }

}
