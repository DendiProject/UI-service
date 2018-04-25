/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.beans.factory;

import com.netcracker.ui.service.beans.factory.basic.objects.interfaces.Factory;
import com.netcracker.ui.service.beans.factory.basic.objects.interfaces.Product;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import java.util.ArrayList;

/**
 *
 * @author Artem
 * @param <T>
 */
public class BeansFactory <T> implements Factory<T>{

    private static ArrayList<Product> beans;
    
    private static BeansFactory instance = null;
    
    private BeansFactory()
    {
        beans = new ArrayList<>();
    }
    
    public static synchronized BeansFactory getInstance()
    {
        if(instance == null)
        {
            instance = new BeansFactory();
        }
        return instance;
    }
    
    @Override
    public T getBean(Class beansClass) throws NotFoundBean{
        for(int i=0;i<beans.size();i++)
        {
            if(beans.get(i).getContent().getClass() == beansClass)
            {
                Product<T> bean = beans.get(i);
                return bean.getContent();
            }
        }
        throw new NotFoundBean("Bean class "+beansClass.getName()+" not found");
    }

    @Override
    public void addBean(Product newBean) {
        for(int i=0;i<beans.size();i++)
        {
            if(beans.get(i).getContent().getClass() == newBean.getContent().getClass())
            {
                throw new UnsupportedOperationException("Уже есть такой");
            }
        }
        
        beans.add(newBean);
    }
    
}
