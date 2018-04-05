/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author seba1
 */
public class TxtExistsOrCreateChecker {
    
    public TxtExistsOrCreateChecker(){
        
    }
    
    public void checkIfExistOrCreate(File file){
    
        
           try
      {
        //Varialble con la ruta donde esta el archivo de la bd de los gruposFb  
        //File FicherogruposFb= new File("./Bd/gruposFb.txt");
        //crear el fichero de base de datos de gruposFb   
        if(!file.exists())
        {
            file.createNewFile();
            System.out.println("Txt "+ file.getName()+ " creado exitosamente");
        }else{System.out.println("El txt" + file.getName()+ " ya existe");}
       }catch (IOException ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
    }
}
