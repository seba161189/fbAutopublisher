package crudtxt.controller;

import crudtxt.modelo.Grupo;
import crudtxt.modelo.Url;
import crudtxt.services.UrlService;
import java.util.ArrayList;
import javax.swing.JTable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author seba1
 */
public class UrlsController {

    private ArrayList<Url> urls = new ArrayList<>();
    private UrlService urlService=new UrlService();

    public UrlsController() {
        this.urls=urlService.getAll();
    }
    
    
    
    
        public ArrayList<Url> getUrls() {
    return this.urls;
            }
        

    public void updateUrls(JTable urlField) {
        
        
              if (!urls.isEmpty()){
               this.urlService.limpiarTxt();   
            }
          
            for(int i=0;i<urlField.getRowCount();i++){
                 this.insertarUrl(urlField.getValueAt(i,0).toString(),
                         urlField.getValueAt(i,1).toString() );
            } 
  
    }

    public void insertarUrl(String url, String descripcion) {
        Url newUrl= new Url(url, descripcion);
        this.urlService.insertUrl(newUrl);   
    }
    
}
