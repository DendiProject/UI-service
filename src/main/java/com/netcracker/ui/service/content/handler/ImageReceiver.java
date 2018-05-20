/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.content.handler;

import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.security.SecurityTokenHandler;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author ArtemShevelyukhin
 */
public class ImageReceiver implements Receiver, SucceededListener {
    public File file;
    public final Image image = new Image("Uploaded Image");
    
    public OutputStream receiveUpload(String filename,
                                      String mimeType) {
        // Create upload stream
        FileOutputStream fos = null; // Stream to write to
        try {
            // Open the file for writing.
            file = new File(filename);
            fos = new FileOutputStream(file);
        } catch (final java.io.FileNotFoundException e) {
            new Notification("Could not open file<br/>",
                             e.getMessage(),
                             Notification.Type.ERROR_MESSAGE)
                .show(Page.getCurrent());
            return null;
        }
        return fos; // Return the output stream to write to
    }
    
    public void uploadSucceeded(Upload.SucceededEvent event) {
        // Show the uploaded file in the image viewer
        image.setVisible(true);
        image.setSource(new FileResource(file));
    }
    
    public void sendFile (File file){
      try {
        String filePath = file.getAbsolutePath();
        new ContentManagerController().addImage(filePath);
      } catch (Exception ex) {
        Logger.getLogger(ImageReceiver.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
};