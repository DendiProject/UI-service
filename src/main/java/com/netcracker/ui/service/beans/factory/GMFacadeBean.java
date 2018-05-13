/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.beans.factory;

import com.netcracker.ui.service.beans.factory.basic.objects.interfaces.Product;
import com.netcracker.ui.service.buttonsClickListener.component.ButtonsClickListener;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;

/**
 *
 * @author Artem
 */
public class GMFacadeBean implements Product<GMFacade>{
    
    private GMFacade content;
    private String grafMenedgerPath;
    
    public GMFacadeBean(String grafMenedgerPath)
    {
        this.grafMenedgerPath = grafMenedgerPath;
        setContent();
    }
    
    @Override
    public void setContent() {
        GMFacade listener = new GMFacade(grafMenedgerPath);
        content = listener;
    }

    @Override
    public GMFacade getContent() {
        return content;
    }
    
}
