/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.vaadin.ui.DateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 *
 * @author ArtemShevelyukhin
 */
public class UserPageFields {
  
  TextField name = new TextField();
  TextField SecondName = new TextField();
  TextField Mail = new TextField();
  DateField BirthDate = new DateField();
  TextArea area = new TextArea();
  String picture_id = null;
  
  public UserPageFields() {
  }
  
  public TextField getName() {
    return name;
  }

  public void setName(String name) {
   this.name.setValue(name);
  }

  public TextField getSecondName() {
    return SecondName;
  }

  public void setSecondName(String SecondName) {
      this.SecondName.setValue(SecondName);
  }

  public TextField getMail() {
    return Mail;
  }

  public void setMail(String Mail) {
    this.Mail.setValue(Mail); 
  }

  public DateField getBirthDate() {
    return BirthDate;
  }

  public void setBirthDate(DateField BirthDate) {
    this.BirthDate = BirthDate;
  }

  public TextArea getArea() {
    return area;
  }

  public void setArea(String text) {
    this.area.setValue(text);
  }

  public String getNameValue(){
    return name.getValue();
  }
  
  public String getSecondNameValue(){
    return SecondName.getValue();
  }
  
  public String getEmailValue(){
    return Mail.getValue();
  }
  
   public String getUserInfoValue(){
    return area.getValue();
   }

  public String getPicture_id() {
    return picture_id;
  }

  public void setPicture_id(String picture_id) {
    this.picture_id = picture_id;
  }
}
