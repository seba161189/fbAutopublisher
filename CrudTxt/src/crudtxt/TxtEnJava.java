package crudtxt;

import java.io.*;
import java.util.*;
public class TxtEnJava
{
     //Zona de variables 
     private      int    id;
     private      String descripcion;
     private      int    cantidad;   
                  File   FicherogruposFb= new File("gruposFb.txt");

                 ArrayList<TxtEnJava> cosas =new ArrayList<TxtEnJava>();
                 TxtEnJava             objeto = null;
    //zona de metodos
    
   public TxtEnJava(int id,String descripcion,int cantidad)
    {
         this.id=id;
         this.descripcion=descripcion;
         this.cantidad=cantidad; 
    }             
   public TxtEnJava(){} 
   
   //obtener el valor de las variables
   public  int getId()
   {
      return this.id;
       
    }
   public  String getDescripcion()
   {
      return this.descripcion;
       
    }

   public  int getCantidad()
   {
      return this.cantidad;
       
    }
    
   
    //Modificar variables
    public  int setId(int n)
   {
      return id=n;
       
    }
   public  String setDescripcion(String n)
   {
      return descripcion=n;
       
    }

   public  int setCantidad(int n )
   {
      return cantidad=n ;
       
    }
    
   //Zona de metodos importantes
   
   public void comprobarBd(){
        try
      {
        //Varialble con la ruta donde esta el archivo de la bd de los gruposFb  
        //File FicherogruposFb= new File("./Bd/gruposFb.txt");
        //crear el fichero de base de datos de gruposFb   
        if(!FicherogruposFb.exists())
        {
            FicherogruposFb.createNewFile();
            System.out.println("Base de datos de gruposFb creada se insertara el gruposFb");
        }else{System.out.println("La base de datos de gruposFb existe");}
       }catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
     }
   public void InsertargruposFb(int id,String descripcion,int cantidad)
      {
         try
      {  
         setId(id);
         setDescripcion(descripcion);
         setCantidad(cantidad);

           
        /* 
         * Abro el flujo de escritura, sobre el fichero con codificacion utf8
         * con la clase BufferedWriter
         */  
          BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FicherogruposFb,true), "utf-8"));  
          /*Escribe en el fichero la cadena que recibe la función.  
           *el string "\r\n" significa salto de linea  y el \t significa tabulacion  */  
          Fescribe.write(getId()+"\t"+getDescripcion()+"\t"+getCantidad()+"\r\n");  
           System.out.println("El gruposFb a sido insertado en la base de datos");           //Cierra el flujo de escritura  
          Fescribe.close();
          
        }
        catch (Exception ex) 
       {  
          //Captura un posible error le imprime en pantalla   
          System.out.println(ex.getMessage());  
       }
      } 
   public void DetxtAObjeto()
     {
        try
        {
         String linea = null;
         BufferedReader leerFichero = new BufferedReader (new FileReader(FicherogruposFb));    
         while( (linea = leerFichero.readLine()) != null)
         {
            //este bucle es para meter todos los datos leidos de archivo de texto separlo en atributos y convertirlos a objeto para poder trabajar con el 
            //en esta parte le digo que lo separe en tokens pero ojo estos tokens son solo Strings tengo que convertirlos para poder emterlos en el objeto y trabajar con ellos
            StringTokenizer mistokens = new StringTokenizer(linea, "\t");
            String           id =  mistokens.nextToken().trim();
            String  descripcion =  mistokens.nextToken().trim();
            String     cantidad =  mistokens.nextToken().trim();
           
            //transformo los tipo de String a los tipos que hace falta int double 
            int    idOperar=Integer.parseInt(id);
            int    cantidadOperar=Integer.parseInt(cantidad);
            
            
            //lo paso al constructor para que me cree los objetos
            objeto= new TxtEnJava(idOperar,descripcion,cantidadOperar);
            //lo añado al vecto para poder jugetear con el 
            cosas.add(objeto);
            
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
      //Este es el ArrayList declarado arriba 
      cosas.clear();
      DetxtAObjeto(); 
   }
      public void MostrarObjetos()
      {
     if( cosas.size()==0){DetxtAObjeto();}
     System.out.println("=========================== Articulo=========================================================================================================================================================================================");     
     for(TxtEnJava n:cosas)
     {
      System.out.println("El id es:"+n.getId()+"\t"+"La descripcion es:"+n.getDescripcion()+"\t"+ "La cantaidad es:"+ n.getCantidad());
     }  
     System.out.println("============================FIN==============================================================================================================================================================================================");
   }
   
   
   public void modificarFichero()
  {
    try{
        //lo primero es Buscar el dato si no como lo vas a modificar
        //lo segundo es mostrarlo por logica si no sabes lo que tienes como vas a modificarlo 
        //lo tercero es modificarlo para esto tienes que saber que dentro de este objeto tu quieres modificar un campo ahora hay que desplegar un switch case para saber que valor vas a modificar 
        //cuarto ya tienes el ArrayList, modficicalo,   
       if( cosas.size()==0){DetxtAObjeto();}
          //PASO 1 Y 2 FUNCION DE BUSQUEDA Y MOSTRADO 
        Scanner en =new Scanner(System.in).useDelimiter("\n");
             //PASO 3 CREAR UN SWIRH CASE CON LAS OPCIONES A MODIFICAR
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
                    for(TxtEnJava n:cosas)
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
                    for(TxtEnJava n:cosas)
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
                    for(TxtEnJava n:cosas)
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
                      for(TxtEnJava n:cosas)
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
                      for(TxtEnJava n:cosas)
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
     }  
  
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
     comprobarBd();
     MostrarObjetos();
     modificarFichero();
     
    } 
}
