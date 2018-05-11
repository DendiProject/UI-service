/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade;

import com.netcracker.ui.service.graf.component.gmfacade.addition.facades.*;

/**
 *
 * @author Artem
 */
public class GMFacade {
    private GMEdgeFacade gmEdgeFacade;
    private GMNodeFacade gmNodeFacade;
    private GMGrafFacade gmGrafFacade;
    private GMCatalogFacade gmCatalogFacade;
    private GMResourceFacade gmResourceFacade;
    private GMTagFacade gmTagFacade;
    
    public GMFacade(String connectionUrl) {
        gmEdgeFacade = new GMEdgeFacade(connectionUrl);
        gmNodeFacade = new GMNodeFacade(connectionUrl);
        gmGrafFacade = new GMGrafFacade(connectionUrl);
        gmCatalogFacade = new GMCatalogFacade(connectionUrl);
        gmResourceFacade = new GMResourceFacade(connectionUrl);
        gmTagFacade = new GMTagFacade(connectionUrl);
        //http://localhost:8083/
    }

    public GMTagFacade getGmTagFacade() {
        return gmTagFacade;
    }

    public GMResourceFacade getGmResourceFacade() {
        return gmResourceFacade;
    }

    public GMEdgeFacade getGmEdgeFacade() {
        return gmEdgeFacade;
    }

    public GMNodeFacade getGmNodeFacade() {
        return gmNodeFacade;
    }

    public GMGrafFacade getGmGrafFacade() {
        return gmGrafFacade;
    }
    
    public GMCatalogFacade getGmCatalogFacade() {
        return gmCatalogFacade;
    }
}
