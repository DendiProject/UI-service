/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.beans.factory;

import com.netcracker.ui.service.beans.factory.basic.objects.interfaces.Product;
import com.netcracker.ui.service.forms.UserPageFields;

/**
 *
 * @author ArtemShevelyukhin
 */
public class UserPageFieldsBean implements Product<UserPageFields>{
  
  private UserPageFields userPageFields;

  public UserPageFieldsBean() {
    setContent();
  }
 
  @Override
  public UserPageFields getContent() {
    return new UserPageFields();
   
  }

  @Override
  public void setContent() {
    UserPageFields fields = new UserPageFields();
    userPageFields = fields; 
  }
}
