package interfazxml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Factura extends javax.swing.JFrame {

    private String textoSql = "";
    private String RUC_Proveedor = "";

    private DefaultTableModel model;
    ConexionBase con = new ConexionBase();
    int n = 0;

    ///llenar tabla 
    public void formatot() {
        String datos[][] = {};
        String Col[] = {"Nro_Factura", "Fecha de Factura", "Valor Sin IVA", "IVA", "Valor Total",
            "RUC Proveedor", "CI Comprador"};
////            
//        String Col[] = {"Cod_Factura", "Fecha_Factura", "Valor_Sin_IVA", "IVA", "Valor_Con_IVA",
//            "RUC_Proveedor", "RUCoCI_Comprador", "Cod_Tipo_Gasto"};
        model = new DefaultTableModel(datos, Col);
        jTable1.setModel(model);
    }

    //llenar tabla en el inicio
    public void obtenerTabla() throws SQLException {
        formatot();
        ConexionBase cont = new ConexionBase();
        String sentencia = "SELECT * FROM organizador_facturas.factura;";
        ResultSet resultado = cont.SelectDB1(sentencia);
        int codigo = 0;
        while (resultado.next()) {
            //System.out.println(codigo);
            model.insertRow(codigo, new Object[]{});
            model.setValueAt(resultado.getString("Cod_Factura"), codigo, 0);
            model.setValueAt(resultado.getString("Fecha_Factura"), codigo, 1);
            model.setValueAt(resultado.getString("Valor_Sin_IVA"), codigo, 2);
            model.setValueAt(resultado.getString("IVA"), codigo, 3);
            model.setValueAt(resultado.getString("Valor_Con_IVA"), codigo, 4);
            model.setValueAt(resultado.getString("RUC_Proveedor"), codigo, 5);
            model.setValueAt(resultado.getString("RUCoCI_Comprador"), codigo, 6);
            codigo++;
        }
    }

    ///llenar el combobox
    public void llenarCombo() throws SQLException {

        ConexionBase cont = new ConexionBase();
        String sentencia = "SELECT * FROM organizador_facturas.proveedor;";
        ResultSet resultado = cont.SelectDB1(sentencia);
        int codigo = 0;
        while (resultado.next()) {
            jComboBox2.addItem(resultado.getString("Nombre_Proveedor"));
            codigo++;
        }
    }

//metodo calcula iva 14%
    public String iva(String valor) {
        double numeroiva = Double.parseDouble(valor);
        numeroiva = 0.14 * numeroiva;
        String iva = String.valueOf(numeroiva);
        return iva;
    }

    // setiar combo de tipo de gasto 
   /* public void setcombogasto(String combog) {
        if (combog.contains("1")) {
            jComboBox1.setSelectedIndex(0);
        }
        if (combog.contains("2")) {
            jComboBox1.setSelectedIndex(1);
        }
        if (combog.contains("3")) {
            jComboBox1.setSelectedIndex(2);
        }
        if (combog.contains("4")) {
            jComboBox1.setSelectedIndex(3);
        }
        if (combog.contains("5")) {
            jComboBox1.setSelectedIndex(4);
        }
        if (combog.contains("6")) {
            jComboBox1.setSelectedIndex(5);
        }
        if (combog.contains("7")) {
            jComboBox1.setSelectedIndex(6);
        }
    }
*/


    ///metodo para obtener el valor del combo box 
    public void setcompro(String proveedor) throws SQLException {

        String contenido[] = new String[jComboBox2.getItemCount()];
        //System.out.println(jComboBox2.getItemCount());
        int contador = 0;
        ConexionBase cont = new ConexionBase();
        String sentencia = "SELECT * FROM organizador_facturas.proveedor;";
        ResultSet resultado = cont.SelectDB1(sentencia);
        while (resultado.next()) {
            contenido[contador] = resultado.getString("RUC_Proveedor");
            //System.out.println("\n"+jComboBox2.getItemAt(contador));
            if (proveedor.contains(resultado.getString("RUC_Proveedor"))) {
                //System.out.println(resultado.getString("RUC_Proveedor")+"combo   "+ jComboBox2.getItemAt(contador));
                //System.out.println(contador);
                jComboBox2.setSelectedIndex(contador);
            }
            contador++;
        }
    }

    public String getcombopro(String nproveedor) throws SQLException {
        String contenido[] = new String[jComboBox2.getItemCount()];
        //System.out.println(jComboBox2.getItemCount());
        String rucpro = "";
        int contador = 0;
        ConexionBase cont = new ConexionBase();
        String sentencia = "SELECT * FROM organizador_facturas.proveedor where Nombre_Proveedor='" + nproveedor + "';";
        ResultSet resultado = cont.SelectDB1(sentencia);
       //rucpro=resultado.getString("RUC_Proveedor");
        while (resultado.next()) {
            rucpro=resultado.getString("RUC_Proveedor"); 
            //System.out.println(rucpro);
        }
       return rucpro;
    }

    public Factura(String pro) {
        initComponents();
        try {
            obtenerTabla();
            llenarCombo();
            jTextField1.enable(false);
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        RUC_Proveedor = pro;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<String>();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nro Factura");

        jLabel2.setText("Fecha de Factura");

        jLabel3.setText("Valor sin IVA");

        jLabel4.setText("Valor IVA");

        jLabel5.setText("Valor total");

        jTextField3.setText("0");
        jTextField3.setEnabled(false);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jTextField4.setText("0");
        jTextField4.setEnabled(false);

        jTextField5.setText("0");
        jTextField5.setEnabled(false);

        jLabel7.setText("Proveedor");

        jButton5.setText("cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setText("nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("grabar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton6.setText("Ingresar Detalles");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addGap(185, 185, 185))
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 38, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(jTextField2)
                            .addComponent(jTextField5)
                            .addComponent(jTextField3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTextField1.enable(true);
        jComboBox2.enable(true);
        LimpiarVentana();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         String variable= jComboBox2.getSelectedItem().toString();
        // System.out.println(variable);
        if (CamposLLenos()) {
             try {
                 textoSql = "update organizador_facturas.Factura set Fecha_Factura='" + jTextField2.getText()
                         + "', Valor_Sin_IVA='" + jTextField3.getText()
                         + "' , IVA='" + jTextField4.getText()
                         + "' , Valor_Con_IVA='" + jTextField5.getText()
                         +"',RUC_Proveedor='"+getcombopro(variable)  
                         + "' where Cod_Factura='" + jTextField1.getText() + "';";
             } catch (SQLException ex) {
                 Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
             }
            System.out.println(textoSql);
            con.UpdateDB(textoSql);
            LimpiarVentana();
             try {
                 obtenerTabla();
             } catch (SQLException ex) {
                 Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
             }
        } else {
            JOptionPane.showMessageDialog(null, "Llenar todos los campos");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String variable= jComboBox2.getSelectedItem().toString();
        if (!jTextField1.getText().equals("")) {
         
                textoSql = "delete From Factura where Cod_Factura='" + jTextField1.getText() + "';";
                         //and RUC_Proveedor='" + getcombopro(variable) + "'"
         
            System.out.println(textoSql);
            con.DeleteDB(textoSql);
            LimpiarVentana();
            try {
                obtenerTabla();
            } catch (SQLException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione que elemento desea eliminar");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String variable= jComboBox2.getSelectedItem().toString();
        // System.out.println(variable);
        if (CamposLLenos()) {
             try {
                 textoSql = "update organizador_facturas.Factura set Fecha_Factura='" + jTextField2.getText()
                         + "', Valor_Sin_IVA='" + jTextField3.getText()
                         + "' , IVA='" + jTextField4.getText()
                         + "' , Valor_Con_IVA='" + jTextField5.getText()
                         +"',RUC_Proveedor='"+getcombopro(variable)  
                         + "' where Cod_Factura='" + jTextField1.getText() + "';";
             } catch (SQLException ex) {
                 Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
             }
            System.out.println(textoSql);
            con.UpdateDB(textoSql);
            LimpiarVentana();
             try {
                 obtenerTabla();
             } catch (SQLException ex) {
                 Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
             }
        } else {
            JOptionPane.showMessageDialog(null, "Llenar todos los campos");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
//        char c = evt.getKeyChar();
//        System.out.println(c+"\n");
//          String num=evt.toString();
//        System.out.println("numero"+num);
//        System.out.println(jTextField3.getText().toString());
//        jTextField4.addKeyListener(setText(evt.setKeyCode(39))); 
//    jTextField4.setText(iva(jTextField3.getText().toString())); 
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int filas = jTable1.getSelectedRow();
        jTextField1.setText(jTable1.getValueAt(filas, 0).toString());
        jTextField2.setText(jTable1.getValueAt(filas, 1).toString());
        jTextField3.setText(jTable1.getValueAt(filas, 2).toString());
        jTextField4.setText(jTable1.getValueAt(filas, 3).toString());
        jTextField5.setText(jTable1.getValueAt(filas, 4).toString());
        try {
            //jTextField5.setText(jTable1.getValueAt(filas, 5).toString());
            setcompro(jTable1.getValueAt(filas, 5).toString());
            //System.out.println(jTable1.getValueAt(filas, 5).toString());
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        //setcombogasto(jTable1.getValueAt(filas, 7).toString());
        jTextField1.enable(false);
       // jComboBox2.enable(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String variable= jComboBox2.getSelectedItem().toString();
        if (CamposLLenos()) {
            try {
                textoSql = "insert into Factura values('" + jTextField1.getText() + "','" + jTextField2.getText() + "','"
                        + jTextField3.getText() + "','" + jTextField4.getText() + "','" + jTextField5.getText() + "','"
                        + getcombopro(variable) + "','" + RUC_Proveedor + "')";
                System.out.println(textoSql);
            con.InsertDB(textoSql);
                Detalle_Factura fac=new Detalle_Factura(jTextField1.getText(),getcombopro(variable));
                fac.setVisible(true);
                
                jTextField3.setText(String.valueOf(fac.getTotalSinIVA()));
                jTextField4.setText(String.valueOf(fac.getTotalIVA()));
                jTextField5.setText(String.valueOf(fac.getTotalConIVA())); 
            } catch (SQLException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                obtenerTabla();               
            } catch (SQLException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Llenar todos los campos");
        }  
    }//GEN-LAST:event_jButton6ActionPerformed
 
    private void LimpiarVentana() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("0");
        jTextField4.setText("0");
        jTextField5.setText("0");
        jComboBox2.setSelectedIndex(0);
    }

    private boolean CamposLLenos() {
        if (jTextField1.getText().equals("") || jTextField2.getText().equals("") || jTextField3.getText().equals("")
                || jTextField4.getText().equals("") || jTextField5.getText().equals("")) {
            return false;
        }
        return true;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
