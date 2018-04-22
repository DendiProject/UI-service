/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.beans.factory;

import com.netcracker.ui.service.beans.factory.basic.objects.interfaces.Product;
import com.netcracker.ui.service.components.MyTokenStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


/**
 *
 * @author ArtemShevelyukhin
 */
public class TokenStoreBean implements Product<MyTokenStore> {
    
    private MyTokenStore tokenStore;

    public TokenStoreBean() {
        setContent();
    }
    
    
    @Override
    public MyTokenStore getContent() {
        return tokenStore;
    }

    @Override
    public void setContent() {
        MyTokenStore store = new MyTokenStore();
        tokenStore = store;
    }
    
}
