/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.beans.factory;

import com.netcracker.ui.service.beans.factory.basic.objects.interfaces.Product;
import com.netcracker.ui.service.graf.component.ipsFacade.IpsFacade;

/**
 *
 * @author Artem
 */
public class IpsFacadeBean implements Product<IpsFacade>{
    
    private IpsFacade content;
    private String grafMenedgerPath;
    
    public IpsFacadeBean(String grafMenedgerPath)
    {
        this.grafMenedgerPath = grafMenedgerPath;
        setContent();
    }
    
    @Override
    public void setContent() {
        IpsFacade listener = new IpsFacade(grafMenedgerPath);
        content = listener;
    }

    @Override
    public IpsFacade getContent() {
        return content;
    }
    
}