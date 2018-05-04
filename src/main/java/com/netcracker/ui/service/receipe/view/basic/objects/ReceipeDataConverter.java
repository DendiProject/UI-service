/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe.view.basic.objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.content.handler.ContentManagerController;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.navigator.InternalServerError;
import com.netcracker.ui.service.exception.receipe.view.ConvertDataException;
import com.netcracker.ui.service.receipe.view.basic.objects.interfaces.DataConverter;
import java.io.IOException;

/**
 *
 * @author Artem
 */
public class ReceipeDataConverter implements DataConverter{
    
    @Override
    public Receipe convert(Object object) throws InternalServerError{   
        try {
            BeansFactory<ObjectMapper> bfOM = BeansFactory.getInstance();
            ObjectMapper mapper = bfOM.getBean(ObjectMapper.class);
            Receipe receipe = mapper.readValue(object.toString(),Receipe.class);
            
            BeansFactory<ContentManagerController> bfCMC = BeansFactory.getInstance();
            ContentManagerController controller = bfCMC.getBean(ContentManagerController.class);
            //mapper = bf.getBean(ObjectMapper.class);
            for(int i=0; i<receipe.nodes.size();i++)
            {
                receipe.nodes.get(i).setPictureId(controller.getImage(receipe.nodes.get(i).getPictureId()));
            }
            return receipe;
        }
        catch (IOException ex) {
            ConvertDataException ex1 = new ConvertDataException(
                    "ReceipeDataConverter cannot convert the data.");
            ex1.initCause(ex);
            InternalServerError exception = new InternalServerError("Exception "
                    + "from IU-Service, Navigator. Internal server error");
            throw exception;
        }
    }

}
