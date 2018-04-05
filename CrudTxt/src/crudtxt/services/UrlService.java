/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudtxt.services;


import crudtxt.modelo.Url;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.TxtExistsOrCreateChecker;

/**
 *
 * @author seba1
 */
public class UrlService {
    
                File   file= new File("urls.txt");
                private ArrayList<Url> urls = new ArrayList<Url>();
                
public UrlService(){       
                }
                
 public void comprobarTxt(){
       TxtExistsOrCreateChecker txtChecker=new TxtExistsOrCreateChecker();
       txtChecker.checkIfExistOrCreate(file);
       
    
     }
                
public void insertUrl(Url url){
                    
      try
      {  

        /* 
         * Abro el flujo de escritura, sobre el fichero con codificacion utf8
         * con la clase BufferedWriter
         */  
          BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true), "utf-8"));  
          /*Escribe en el fichero la cadena que recibe la función.  
           *el string "\r\n" significa salto de linea  y el \t significa tabulacion  */  
          Fescribe.write(url.getUrl()+"\t"+url.getDescripcion()+"\r\n");  
          System.out.println("El grupos a sido insertado en la base de datos"+url.getUrl() + url.getDescripcion());           //Cierra el flujo de escritura  
          Fescribe.close();
          
        }
        catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }         
                    
                }
                
public void updateFile(ArrayList<Url> list){
                
                    PrintWriter writer;
                    
         try {
             writer = new PrintWriter(file);
             for(Url url: list){
                writer.print(url.getUrl()+"\t"+url.getDescripcion()+"\r\n"); 
             }
             
             
           
            
         } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
         }
                }

    public ArrayList<Url> getAll() {
        System.err.println("getAll controller");
             if( urls.size()==0){deTxtAObjeto();}
             System.err.println(urls.size()==0);
          return urls;   
    }
                
public void deTxtAObjeto()
     {
        try
        {
         String linea = null;
         BufferedReader leerFichero = new BufferedReader (new FileReader(file));    
         while( (linea = leerFichero.readLine()) != null)
         {
            //este bucle es para meter todos los datos leidos de archivo de texto separlo en atributos y convertirlos a objeto para poder trabajar con el 
            //en esta parte le digo que lo separe en tokens pero ojo estos tokens son solo Strings tengo que convertirlos para poder emterlos en el objeto y trabajar con ellos
            StringTokenizer mistokens = new StringTokenizer(linea, "\t");
            String  url =  mistokens.nextToken().trim();
            String  descripcion =  mistokens.nextToken().trim();
                   
            
            //lo paso al constructor para que me cree los objetos
            urls.add(new Url(url,descripcion));
            
            }
         leerFichero.close();
       
       }catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
      }      

    public void limpiarTxt() {
      PrintWriter writer;
         try {
             writer = new PrintWriter(file);
             writer.print("");
            writer.close();
         } catch (FileNotFoundException ex) {
             Logger.getLogger(GupoService.class.getName()).log(Level.SEVERE, null, ex);
         }  }

}
