/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.content.handler;

import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.buttonsClickListener.component.SessionStorageHelper;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.forms.UserPageFields;
import com.netcracker.ui.service.security.SecurityTokenHandler;
import com.netcracker.ui.service.utilities.SaveUserImage;
import com.vaadin.server.Extension;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author ArtemShevelyukhin
 */
public class ImageReceiver implements Receiver, SucceededListener {
    public File file;
    public final Image image = new Image("Uploaded Image");
    public ByteArrayOutputStream stream;
    public String filename;
    public String fileType;
    public String imageID;//ID загруженной картинки 
    
    BeansFactory<ContentManagerController> bfCMC = BeansFactory.getInstance();
    ContentManagerController controller;

  
    
    
    public OutputStream receiveUpload(String filename, String mimeType) {
      System.out.println("receiveUpload");
      this.filename = filename;
      this.fileType = mimeType;
      stream = new ByteArrayOutputStream();
      return stream; // Return the output stream to write to
    }
    
    
    public void uploadSucceeded(Upload.SucceededEvent event) {
      
      String image;
      try {
        System.out.println("uploadSucceeded");
        controller = bfCMC.getBean(ContentManagerController.class);
        // Show the uploaded file in the image viewer
        ByteArrayOutputStream streamq = this.stream;
        System.out.println("uploadSucceeded" + "OK OK OK OK OK OK OK OK OK");
        
        sendFile(this.stream); 
        
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        UserPageFields info = new SessionStorageHelper().getUserPageFields(attr);
        info.setPicture_id(imageID);
        new SaveUserImage(info);
        Page.getCurrent().reload();
        
      } catch (Exception ex) {
         ExceptionHandler.getInstance().runExceptionhandling(ex);
      }
    }
    
    //Устанавливает знаение imageID
    public void sendFile (ByteArrayOutputStream stream) {
      try {
        controller = bfCMC.getBean(ContentManagerController.class);
        
        byte[] image = stream.toByteArray();
        int length = image.length;
        
        this.imageID = controller.addImageFromByte(image, filename, fileType, length);
        System.out.println("-----sendFile---->" + this.imageID);
        
      } catch (Exception ex) {
         ExceptionHandler.getInstance().runExceptionhandling(ex);
      }
    }
      
};