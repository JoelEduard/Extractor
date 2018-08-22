package com.iqubica.lab.extractor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class Principal
{
  public Principal() {}
  
  public static void main(String[] arg)
  {
    System.out.println("Inicia el extractor");
    

    String cad01 = "";
    int noesnumero = 0;
    String perEmp = "P";
    String uniSepaNom = "S";
    java.io.File archivo = null;
    FileReader fr = null;
    
    String valor = "";
    String des00 = "";String des01 = "";String des02 = "";String des03 = "";String des04 = "";String des05 = "";
    String[][] movs = new String[6][10];
    int linvacia = 0;int numcomi = 0;
    int numEdoCuenta = 0;int numPagina = 0;int posCol = 0;int posFila = 0;int cuentaLineas = 1;int y = 0;int x = 0;int ze = 0;
    
    ArrayList<String> campoNombre = new ArrayList();
    ArrayList<String> prodBan = new ArrayList();
    java.util.List lista01 = new ArrayList();
    ArrayList<java.util.List> tabladina001 = new ArrayList();
    ArrayList<String> tablaproban = new ArrayList();
    
    ArrayList<String> valCol = new ArrayList();
    ArrayList<Integer> pagPro = new ArrayList();
    
    int pos00 = 0;int cuentaarray = 0;
    int k=0;
    Map<String, String> titular = new HashMap();
    Map<String, String> dataCuenta = new HashMap();
    Map<String, String> nombreMap = new HashMap();
    Map<Integer, String> lineasMap = new HashMap();
    
    String nom_inst = "Banco Nacional de México, S.A.";
    
    try
    {
    	Properties p = new Properties();
    if(k==0) {
      
    
      p.load(new FileReader("C:\\opt\\ondemand\\www\\edc2xml\\config\\layoutExtracV02.txt"));
    }
    else {p.load(new FileReader("C:\\opt\\ondemand\\www\\edc2xml\\config\\layoutExtracV03.txt"));}


      java.util.Enumeration<Object> keys = p.keys();
      


      int cregla = 0;
      
      while (keys.hasMoreElements()) {
        Object key = keys.nextElement();
        
        nombreMap.put(key.toString(), p.get(key).toString());
        cregla++;
      }
      






      archivo = new java.io.File("C:\\opt\\ondemand\\www\\edc2xml\\mgrprocess\\saltxt1.txt");
      fr = new FileReader(archivo);
      BufferedReader br = new BufferedReader(fr);
      

      int clin00 = 0;
      String linea;
      while ((linea = br.readLine()) != null) {
        lineasMap.put(Integer.valueOf(clin00), linea);
        clin00++;
      }
      



      int key1 = 0;int con1 = 0;
      


      Iterator it02 = nombreMap.keySet().iterator();
      while (it02.hasNext()) {
        String key0 = (String)it02.next();
        
        String valor000 = (String)nombreMap.get(key0);
        String[] valores0 = valor000.split(",");
        int valtama0 = valores0.length;
        String temcad000 = "";String temcad010 = "";
        
        if ((valtama0 > 2) && 
          (key0.startsWith("AA")))
        {
          int col2val = 0;
          int fini = Integer.parseInt(valores0[1]);
          int ffin = Integer.parseInt(valores0[2]);
          int cini = Integer.parseInt(valores0[3]);
          int cfin = Integer.parseInt(valores0[4]);
          int nelemen = Integer.parseInt(valores0[6]);
          String tiprocess = valores0[5].trim();
          

          int[][] colmax = new int[2][nelemen];
          int ki = 0;
          for (int q = valtama0 - nelemen; q <= valtama0 - 1; q++)
          {
            String[] sepa = valores0[q].split("-");
            colmax[0][ki] = Integer.parseInt(sepa[1].trim());
            colmax[1][ki] = Integer.parseInt(sepa[2].trim());
            if (ki == 1)
            {
              col2val = Integer.parseInt(sepa[2].trim());
            }
            ki++;
          }
          





          if (tiprocess.equals("ARRAY"))
          {
            String comienzaArray = valores0[7].trim();
            String finArray = valores0[8].trim();
            
            int colArray = Integer.parseInt(valores0[6]);
            linvacia = 0;key1 = 0;linvacia = 0;
            
            for (int un = 0; un <= lineasMap.size() - 1; un++) {
              String cad00 = (String)lineasMap.get(Integer.valueOf(un));
              

              if (cad00 != null)
              {
                cad01 = cad00.substring(cini, cfin);
                
                int y1 = -1;
                
                y1 = cad01.indexOf(comienzaArray);
                if (y1 >= 0)
                {

                  numcomi = 1;
                  des01 = "";des00 = "";
                  key1 = 1;
                  con1++;
                  linvacia = 0;
                }
                

                y1 = cad01.indexOf(finArray);
                if ((y1 >= 0) && (numcomi == 1))
                {

                  numcomi = 0;
                  des01 = "";des00 = "";
                  
                  con1++;
                  linvacia = 0;
                  break;
                }
              }
              

              if ((key1 >= 2) && (numcomi == 1) && (!cad01.trim().isEmpty())) {
                linvacia = 0;
                



                if (cad00.trim().length() > colmax[0][1])
                {
                  if (!cad00.substring(0, colmax[1][(nelemen - 1)]).trim().isEmpty()) {
                    temcad010 = temcad010.trim() + " " + cad00.substring(0, colmax[0][1]).trim();
                  }
                  

                  temcad000 = temcad000.trim() + cad00.substring(colmax[1][0], colmax[1][1]).trim() + " ";
                  

                  if (!temcad000.trim().isEmpty()) {
                    System.out.println("descripcion     ---->> " + temcad010);
                    System.out.println("contrato/cuenta ---->> " + temcad000);
                  }
                  System.out.println(" ------------------------------------- ");
                  



                  temcad000 = "";temcad010 = "";
                } else {
                  if (!cad00.substring(0, colmax[1][(nelemen - 1)]).trim().isEmpty()) {
                    temcad010 = cad00.substring(0, colmax[1][(nelemen - 1)]);
                  }
                  

                  temcad000 = temcad000 + cad00.substring(colmax[0][1], colmax[1][1]).trim() + " ";
                }
              }
              






              if (key1 == 1) {
                key1++;
              }
            }
          }
        }
      }
      






      System.out.println("fin de regla de negocio AA");
      


























































































      Iterator it03 = nombreMap.keySet().iterator();
      while (it03.hasNext()) {
        String key3 = (String)it03.next();
        
        String valor003 = (String)nombreMap.get(key3);
        String[] valores03 = valor003.split(",");
        int valtama03 = valores03.length;
        String temcad003 = "";String temcad013 = "";
        
        if ((valtama03 > 2) && 
          (key3.startsWith("MOV"))) {
          System.out.println("INICIO de REGLA MOV Numero de parametros para movimientos  " + valtama03);
          
          int fini = Integer.parseInt(valores03[1]);
          int ffin = Integer.parseInt(valores03[2]);
          int cini = Integer.parseInt(valores03[3]);
          int cfin = Integer.parseInt(valores03[4]);
          int nelemen = Integer.parseInt(valores03[6]);
          String tiprocess = valores03[5].trim();
          
          int[][] colmaxi = new int[2][nelemen];
          int ki = 0;
          for (int q = valtama03 - nelemen; q <= valtama03 - 1; q++)
          {
            String[] sepa = valores03[q].split("-");
            colmaxi[0][ki] = Integer.parseInt(sepa[1].trim());
            colmaxi[1][ki] = Integer.parseInt(sepa[2].trim());
            ki++;
          }
          

          if (tiprocess.equals("ARRAY"))
          {
            String comienzaArray = valores03[7].trim();
            String finArray = valores03[8].trim();
            
            int colArray = Integer.parseInt(valores03[6]);
            linvacia = 0;key1 = 0;linvacia = 0;
            


            int kpag = 0;
            for (int unr = 0; unr <= lineasMap.size() - 1; unr++)
            {

              String cad00 = (String)lineasMap.get(Integer.valueOf(unr));
              
              if (cad00.startsWith("0")) {
                kpag++;
              }
              
              int y11 = cad00.indexOf(comienzaArray);
              if (y11 >= 0)
              {
                pagPro.add(Integer.valueOf(kpag));
              }
              k++;
            }
            

            System.out.println("numero de paginas totales " + kpag);
            

            kpag = 0;
            int npagPro = 0;int canpag = pagPro.size();int procesapag = 0;
            for (int un = 0; un <= canpag - 1; un++) {
              System.out.println("Pagina numero :[" + pagPro.get(un) + "]");
              
              kpag = 0;numcomi = 0;
              for (int u = 0; u <= lineasMap.size() - 1; u++)
              {
                String cad00 = (String)lineasMap.get(Integer.valueOf(u));
                
                if (cad00.startsWith("0 ")) {
                  kpag++;
                }
                

                if (kpag == ((Integer)pagPro.get(un)).intValue())
                {
                  if (cad00 != null)
                  {

                    cad01 = cad00.substring(cini, cfin);
                    
                    if ((cad01.trim().isEmpty()) && (numcomi == 1)) {
                      linvacia++;
                    }
                    
                    int y1 = -1;
                    
                    y1 = cad01.indexOf(comienzaArray);
                    if (y1 >= 0) {
                      System.out.println(" -----  Comienza Tabla ----** ");
                      numcomi = 1;
                      des01 = "";des00 = "";
                      key1 = 1;
                      con1++;
                      linvacia = 0;
                    }
                    



                    y1 = -1;
                    
                    y1 = cad01.indexOf(finArray);
                    if ((y1 < 0) || (
                    



                      (finArray.equals("INDEFINIDO")) && 
                      (linvacia >= 3)))
                    {

                      System.out.println("Linea vacia igual [" + linvacia + "]");
                      linvacia = 0;
                      numcomi = 0;
                    }
                  }
                  





                  if ((key1 >= 2) && (numcomi == 1) && (!cad01.trim().isEmpty()) && (con1 <= 300)) {
                    linvacia = 0;
                    if (cad00.trim().length() > colmaxi[1][1]) {
                      if (!cad00.substring(0, colmaxi[0][1]).trim().isEmpty()) {
                        temcad013 = cad00.substring(0, colmaxi[0][1]).trim();
                      }
                      

                      temcad003 = temcad003 + cad00.substring(colmaxi[1][0], colmaxi[1][1]).trim() + " ";
                      
                      System.out.println("fecha ---->> " + temcad013);
                      System.out.println("concepto ---->> " + temcad003);
                      System.out.println("Retiros ---->> " + cad00.substring(colmaxi[0][(nelemen - 3)] + 1, colmaxi[1][(nelemen - 3)]));
                      System.out.println("depositos ---->> " + cad00.substring(colmaxi[0][(nelemen - 2)] + 1, colmaxi[1][(nelemen - 2)]));
                      System.out.println("saldo ---->> " + cad00.substring(colmaxi[0][(nelemen - 1)] + 1, colmaxi[1][(nelemen - 1)]));
                      System.out.println("-----------------------------------------------------------");
                      temcad003 = "";temcad013 = "";
                    }
                    else {
                      if (!cad00.substring(0, colmaxi[0][1]).trim().isEmpty())
                      {
                        temcad013 = cad00.substring(0, colmaxi[0][1]).trim();
                      }
                      
                      temcad003 = temcad003 + cad00.substring(colmaxi[0][1], colmaxi[1][1]).trim() + " ";
                    }
                  }
                  












                  if (key1 == 1) {
                    key1++;
                  }
                  
                  con1++;



                }
                else
                {



                  if (kpag > ((Integer)pagPro.get(un)).intValue()) {
                    break;
                  }
                }
              }
            }
          }
        }
      }
      




      System.out.println("fin de regla de negocio MOV");
      




      Iterator it1 = nombreMap.keySet().iterator();
      while (it1.hasNext()) {
        String key = (String)it1.next();
        


        String valor00 = (String)nombreMap.get(key);
        String[] valores0 = valor00.split(",");
        int valtama = valores0.length;
        String temcad00 = "";String temcad01 = "";
        



        if (valtama > 2)
        {



          int fini = Integer.parseInt(valores0[1]);
          int ffin = Integer.parseInt(valores0[2]);
          int cini = Integer.parseInt(valores0[3]);
          int cfin = Integer.parseInt(valores0[4]);
          String nomkey = valores0[0].trim();
          String tipo = valores0[5].trim();
          String campo = valores0[6].trim();
          if (tipo.equals("REFE"))
          {
            String cadtem = "";
            for (int u = 0; u <= lineasMap.size() - 1; u++) {
              String cad00 = (String)lineasMap.get(Integer.valueOf(u));
              if (cad00 != null)
              {
                y = cad00.indexOf(campo);
                if (y >= 0) {
                  cadtem = cad00.substring(cini, cfin).trim();
                  System.out.println(nomkey + " REFE ---> [" + cadtem + "]");
                  break;
                }
              }
            }
          }
          



          if (tipo.equals("TEXT")) {
            temcad01 = "";
            
            for (int fil = fini; fil <= ffin; fil++) {
              if (lineasMap.containsKey(Integer.valueOf(fil))) {
                temcad00 = (String)lineasMap.get(Integer.valueOf(fil));
                
                if ((cini <= temcad00.length()) && (cfin <= temcad00.length()))
                {
                  temcad01 = temcad01.trim() + temcad00.substring(cini, cfin);
                }
              }
            }
            
            System.out.println(nomkey + "  TEXT [" + temcad01.trim() + "]");
          }
        }
      }
      


















      System.out.println(nom_inst + " --- " + perEmp + " --- " + titular);

    }
    catch (Exception e)
    {
      e.printStackTrace();
      


      try
      {
        if (fr != null) {
          fr.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
  
    finally
    {
      try
      {
        if (fr != null) {
          fr.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
  }
}
