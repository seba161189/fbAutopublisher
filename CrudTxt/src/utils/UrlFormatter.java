/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author seba1
 */
public class UrlFormatter {
    
    public String format(String url){
      		String remplazado=url.replaceFirst("^(http[s]?://www\\.|http[s]?://|www\\.)","");
                String ultimo=remplazado;
              if(remplazado.contains("?")){
                 ultimo=remplazado.substring(0, remplazado.indexOf('?')) ;
              }
              return ultimo;
              
    }
}
