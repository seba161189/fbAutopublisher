package crudtxt.services;

import utils.TxtExistsOrCreateChecker;
import crudtxt.modelo.Grupo;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class GupoService 
{

                File   groupsTxt= new File("gruposFb.txt");
                private ArrayList<Grupo> grupos = new ArrayList<Grupo>();

    //zona de metodos
    
            
   public GupoService(){
  
   } 
   
  
   //Zona de metodos importantes
   
   public void comprobarTxt(){
       TxtExistsOrCreateChecker txtChecker=new TxtExistsOrCreateChecker();
       txtChecker.checkIfExistOrCreate(groupsTxt);
       
    
     }
   public void insertarGrupo(Grupo grupo)
      {
         try
      {  

        /* 
         * Abro el flujo de escritura, sobre el fichero con codificacion utf8
         * con la clase BufferedWriter
         */  
          BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(groupsTxt,true), "utf-8"));  
          /*Escribe en el fichero la cadena que recibe la función.  
           *el string "\r\n" significa salto de linea  y el \t significa tabulacion  */  
          Fescribe.write(grupo.getDescripcion()+"\t"+grupo.getCantidad()+"\n");  
          System.out.println("El grupos a sido insertado en la base de datos");           //Cierra el flujo de escritura  
          Fescribe.close();
          
        }
        catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
      } 
   public void deTxtAObjeto()
     {
        try
        {
         String linea = null;
         BufferedReader leerFichero = new BufferedReader (new FileReader(groupsTxt));    
         while( (linea = leerFichero.readLine()) != null)
         {
            //este bucle es para meter todos los datos leidos de archivo de texto separlo en atributos y convertirlos a objeto para poder trabajar con el 
            //en esta parte le digo que lo separe en tokens pero ojo estos tokens son solo Strings tengo que convertirlos para poder emterlos en el objeto y trabajar con ellos
            StringTokenizer mistokens = new StringTokenizer(linea, "\t");
            String  descripcion =  mistokens.nextToken().trim();
            String     cantidad =  mistokens.nextToken().trim();
           
            //transformo los tipo de String a los tipos que hace falta int double 

            int    cantidadOperar=Integer.parseInt(cantidad);
            
            
            //lo paso al constructor para que me cree los objetos
            grupos.add(new Grupo(descripcion, cantidadOperar));
            
            }
         leerFichero.close();
       
       }catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
      }     
   public void ActualizarArraList()
   {
      grupos.clear();
      getAll(); 
   }
   
      public void showGrupos()
      {
     if( grupos.size()==0){getAll();}
     System.out.println("=========================== Articulo=========================================================================================================================================================================================");     
     for(Grupo n:grupos)
     {
      System.out.println("La descripcion es:"+n.getDescripcion()+"\t"+ "La cantaidad es:"+ n.getCantidad());
     }  
     System.out.println("============================FIN==============================================================================================================================================================================================");
   }
      
      public ArrayList<Grupo> getAll(){
          if( grupos.size()==0){deTxtAObjeto();}
          return grupos;
      }
   
   /*
   public void modificarFichero()
  {
    try{
        //lo primero es Buscar el dato si no como lo vas a modificar
        //lo segundo es mostrarlo por logica si no sabes lo que tienes como vas a modificarlo 
        //lo tercero es modificarlo para esto tienes que saber que dentro de este objeto tu quieres modificar un campo ahora hay que desplegar un switch case para saber que valor vas a modificar 
        //cuarto ya tienes el ArrayList, modficicalo,   
       if( grupos.isEmpty()){deTxtAObjeto();}
          //PASO 1 Y 2 FUNCION DE BUSQUEDA Y MOSTRADO 
        Scanner en =new Scanner(System.in).useDelimiter("\n");
             //PASO 3 CREAR UN SWICH CASE CON LAS OPCIONES A MODIFICAR
             //creo una variable para escanear lo que entra por teclado para asi poder modificar mas facilmente
             
        int opc=10;
             
        while(opc!=8)
             {
               menu();
               opc=en.nextInt();
               switch(opc)
               {
                    case 1: System.out.println("Introducir el id del gruposFb y la nueva id");
                            
                    //n.getId()==idNumero
                    
                   // String      cadena=en.next(); 
                   int    idNumero=en.nextInt(); 
                   int    numero=en.nextInt();        
                    for(Controlador1 n:cosas)
                    {
                       
                      if(n.getId()==idNumero){
                          
                        n.id =numero;     
                        System.out.println("=========================== Articulo=========================================================================================================================================================================================");
                        System.out.println("El id es:"+n.getId()+"\t"+"La descripcion es:"+n.getDescripcion()+"\t"+ "La cantaidad es:"+ n.getCantidad());
                        System.out.println("============================FIN==============================================================================================================================================================================================");
                        break;
                      }else{
                          System.out.println("el gruposFb no ha sido encontrado");
                                         }  
                    }
                    break;
                    
                    
                    
                    
                    case 2: System.out.println("Inserte la descripcion del gruposFb y la  nueva descripcion del mismo");
                    String  cadena=en.next();        
                    String cadenaNueva=en.next();        
                    for(Controlador1 n:cosas)
                    {
                       
                      if(n.getDescripcion().equals(cadena)){
                          
                        n.descripcion=cadenaNueva;     
                        System.out.println("=========================== Articulo=========================================================================================================================================================================================");
                        System.out.println("El id es:"+n.getId()+"\t"+"La descripcion es:"+n.getDescripcion()+"\t"+"\t"+ "La cantaidad es:"+ n.getCantidad());
                        System.out.println("============================FIN==============================================================================================================================================================================================");
                        break;
                      }else{
                          System.out.println("el gruposFb no ha sido encontrado");
                       }  
                    }
                    
                    
                    
                    
                    
                    break;

                    case 3:
                        System.out.println("Inserte el id, la descripcion y la cantidad nueva ");
                        id=en.nextInt();
                        descripcion=en.next();
                        cantidad=en.nextInt();
                        InsertargruposFb(id, descripcion, cantidad);
                        
                        break;
                        
                    
                    case 4: System.out.println("Inserte la descripcion y la cantidad nueva ");
                   
                           cadena=en.next();        
                          numero=en.nextInt();        
                    for(Controlador1 n:cosas)
                    {
                       
                      if(n.getDescripcion().equals(cadena)){
                          
                        n.cantidad =numero;     
                        System.out.println("=========================== Articulo=========================================================================================================================================================================================");
                        System.out.println("El id es:"+n.getId()+"\t"+"La descripcion es:"+n.getDescripcion()+"\t"+ "La cantaidad es:"+ n.getCantidad());
                        System.out.println("============================FIN==============================================================================================================================================================================================");
                        break;
                      }else{
                          System.out.println("el gruposFb no ha sido encontrado");
                      }  
                    }
                    
                    
                    
                    
                    
                    break;
                    case 5: System.out.println("Guardar");
                    try{
                      BufferedWriter bw = new BufferedWriter(new FileWriter(FicherogruposFb));
                      for(Controlador1 n:cosas)
                      {
                          bw.write(n.id+"\t"+n.descripcion+"\t"+ n.cantidad+"\r\n");   
                      }
                      bw.close();
                     }catch (Exception ex) 
                    {  
                      //Captura un posible error le imprime en pantalla   
                      System.out.println(ex.getMessage());  
                    }
                    
                    break;  
                    
                    
                    case 6: System.out.println("inserte el ID del gruposFb que va borrar de la bd");
                            int v=en.nextInt();
                            
                    try{
                      BufferedWriter bw = new BufferedWriter(new FileWriter(FicherogruposFb));
                      for(Controlador1 n:cosas)
                      {  
                          if(n.getId()!=v){
                           bw.write(n.id+ "\t"+n.descripcion+ "\t"+ n.cantidad+"\r\n");
                        }else{
                          System.out.println("el gruposFb ha sido eliminado");
                                         }
                      }
                      bw.close();
                      cosas.clear();
                      DetxtAObjeto();
                      
                     }catch (Exception ex) 
                    {  
                      //Captura un posible error le imprime en pantalla   
                      System.out.println(ex.getMessage());  
                    }
                    
                    break;  
                    case 7: 
                        ActualizarArraList();
                        MostrarObjetos();
                    break;
                }
            } 
        } catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }     
     }*/  
  
  private void menu()
   {
     System.out.println("--------Menu de modificaciones de gruposFb -------");
     System.out.println("1. Modificar id");
     System.out.println("2. Modificar descripcion ");
     System.out.println("3. Insertar gruposFb");
     System.out.println("4. Modificar cantidad");
     System.out.println("5. Guardar");
     System.out.println("6. Dar de bajar un proudcto existente");
     System.out.println("7. Mostrar listado");
     System.out.println("8. Salir");
   }
    //Main   

    /**
     *
     * @param args
     */
     public void main(String[] args) 
   {
     comprobarTxt();
     showGrupos();
     //modificarFichero();
     
    } 

    public void limpiarTxt() {
    PrintWriter writer;
         try {
             writer = new PrintWriter(groupsTxt);
             writer.print("");
            writer.close();
         } catch (FileNotFoundException ex) {
             Logger.getLogger(GupoService.class.getName()).log(Level.SEVERE, null, ex);
         }
  }


}
