
package analizador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;

public class ventanaPrincipal extends javax.swing.JFrame {
    
             
    String linea;
    String[] leidas = new String[100];//array con las lineas de código
    String[][] matriz = null;//meter el archivo de texto en una matriz
    int lineas=0;//cantidad de lineas del codigo
    int contador =0;
    int tokenPr =0;
    //definiendo Tokens
    String palabra_reservada[] = {"entero","decimal","booleano","cadadena"
    ,"si","sino","mientras","hacer","verdadero","falso"};
    
    String operador[]= {"+","-","*","/","%","=","==","<",">","<=",">=","(",")"
    ,"{","}","“",";"};
    
    String lenguaje[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n",
    "o","p","q","r","s","t","u","v","w","y","z"};
    
    //clase que analice numeros
    //clase que analice los identificadores
   
    public ventanaPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btleerfichero = new javax.swing.JButton();
        btsalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtcodigo = new javax.swing.JTextArea();
        btllenar = new javax.swing.JButton();
        btseparar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Vamos a Cargar nuestro Fichero de Texto");

        btleerfichero.setText("Leer Fichero");
        btleerfichero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btleerficheroActionPerformed(evt);
            }
        });

        btsalir.setText("Salir");
        btsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsalirActionPerformed(evt);
            }
        });

        txtcodigo.setColumns(20);
        txtcodigo.setRows(5);
        jScrollPane1.setViewportView(txtcodigo);

        btllenar.setText("Llenar");
        btllenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btllenarActionPerformed(evt);
            }
        });

        btseparar.setText("Separar");
        btseparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsepararActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btseparar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btleerfichero)
                                .addGap(18, 18, 18)
                                .addComponent(btsalir)
                                .addGap(40, 40, 40)
                                .addComponent(btllenar)))))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btleerfichero)
                    .addComponent(btsalir)
                    .addComponent(btllenar)
                    .addComponent(btseparar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //boton salir
    private void btsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsalirActionPerformed
        
        this.dispose();
    }//GEN-LAST:event_btsalirActionPerformed
    //boton leer archivo
    private void btleerficheroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btleerficheroActionPerformed
        JFileChooser j = new JFileChooser();
        j.showOpenDialog(j);
        String ruta = j.getSelectedFile().getAbsolutePath();
        cargarfichero(ruta);
        //lo anterior tengo que dejarlo para cargar bien el archvio
        //cargarfichero("C:\\prueba 1.txt");
        
    }//GEN-LAST:event_btleerficheroActionPerformed

    private void btllenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btllenarActionPerformed
       //boton para llenar txt codigo
       for (int i=0; i<lineas; i++){
           txtcodigo.append(leidas[i]+"\n");
           System.out.println(i + " " + leidas[i]);
              
       }
        
    }//GEN-LAST:event_btllenarActionPerformed

    private void btsepararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsepararActionPerformed
        //tokenizer, que separa todo en tokens
        //tendrìa que tener una funcion para ver si es palabra reservada
        //otra para quitar el punto y coma del final
        String evaluador;
        StringTokenizer tokens=new StringTokenizer(leidas[0]);
	while(tokens.hasMoreTokens()){
            evaluador=tokens.nextToken().toString();//convierto el token a string para poder evaluarlo
            //System.out.println("token "+evaluador);
            for(int i=0; i<palabra_reservada.length; i++){
                if(palabra_reservada[i].equals(evaluador)){
                    System.out.println("Encontre una Palabra Reservada");
                    //System.out.println("Longitud palabra reservcda"+i+"prueba");
                }
            
            }
                 
            System.out.println(tokens.nextToken());
        }
             
        
    }//GEN-LAST:event_btsepararActionPerformed

   static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaPrincipal().setVisible(true);
            }
        });
   
   }
   
    
      public void cargarfichero( String direccion ){
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         //archivo = new File ("C:\\archivo.txt");
         archivo = new File (direccion);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         // Lectura del fichero
         while((linea=br.readLine())!=null)
            {
            leidas[lineas]=linea;
            lineas++;
            //System.out.println(linea);
            }
        }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // cerrar fichero
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
          System.out.println("lineas:  "+lineas);
      }
    }
      
      
	

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btleerfichero;
    private javax.swing.JButton btllenar;
    private javax.swing.JButton btsalir;
    private javax.swing.JButton btseparar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtcodigo;
    // End of variables declaration//GEN-END:variables
}
