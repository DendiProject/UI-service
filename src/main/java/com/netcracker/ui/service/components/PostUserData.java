/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.components;

import com.google.gson.Gson;
import com.netcracker.ui.service.UserDto;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ArtemShevelyukhin
 */
public class PostUserData {

    public URL url = null;
    public HttpURLConnection con = null;
    public OutputStreamWriter wr = null;

    public PostUserData(String postUrl, UserDto userDto, String secureToken) {
        try {
            Gson gson = new Gson();
            url = new URL(postUrl + "?access_token=" + secureToken);
            
//            Map<String, Object> params = new LinkedHashMap<>();
//            params.put("access_token", secureToken);
//
//            StringBuilder postData = new StringBuilder();
//            for (Map.Entry<String, Object> param : params.entrySet()) {
//                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
//                postData.append('=');
//                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
//            }
//            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setDoOutput(true);
            
            wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(gson.toJson(userDto));
            wr.flush();
        } catch (MalformedURLException ex) {
            Logger.getLogger(PostUserData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(PostUserData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PostUserData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
