/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudtxt.controller;

import crudtxt.modelo.Grupo;
import java.util.ArrayList;
import crudtxt.services.GupoService;
import javax.swing.JTable;
/**
 *
 * @author seba1
 */
public class GruposController {
   
    private ArrayList<Grupo> grupos = new ArrayList<>();
    
    private GupoService grupoService=new GupoService();
    
    public GruposController() {
       this.grupos=grupoService.getAll();
    }
    
    public void add(String nombre,int cant){
        Grupo grupo= new Grupo(nombre, cant);
        
        this.grupoService.insertarGrupo(grupo);
    }
    

    public ArrayList<Grupo> getGrupos() {
    return this.grupos;
            }

    public void updateGrupos(JTable groupField) {
        
              if (!grupos.isEmpty()){
               this.grupoService.limpiarTxt();   
            }
          
            for(int i=0;i<groupField.getRowCount();i++){
                 this.add(groupField.getValueAt(i,0).toString(),
                         Integer.parseInt(groupField.getValueAt(i,1).toString()));
            }   }


 
    
    
}
