
import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.*;
import javax.swing.table.DefaultTableModel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Radhesh Sarma
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    
    Directory d;
    int globaldepth,bfr;
    boolean b1=false,d1=false;
    
    public GUI() {
        initComponents();
         this.setExtendedState(MAXIMIZED_BOTH);
       Object[] cname={"Directory Number","Bucket ID","Local Depth" ,"Content of Bucket pointed by it",};
            DefaultTableModel model=new DefaultTableModel(cname,16);
            table.setModel(model);
            
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userinput = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Insert = new javax.swing.JButton();
        user_input = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        global_depth = new javax.swing.JTextField();
        gd_input = new javax.swing.JButton();
        BFR = new javax.swing.JButton();
        bfr_input = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        userinput.setBackground(java.awt.Color.black);
        userinput.setPreferredSize(new java.awt.Dimension(2000, 1200));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("We Assume initital BFR = 3, hash function as k%17");

        Insert.setBackground(java.awt.Color.darkGray);
        Insert.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        Insert.setForeground(java.awt.Color.white);
        Insert.setText("Insert");
        Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertActionPerformed(evt);
            }
        });

        user_input.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        user_input.setToolTipText("");
        user_input.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                user_inputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                user_inputFocusLost(evt);
            }
        });
        user_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_inputActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("DBMS MODULE 2");

        table.setBackground(java.awt.Color.darkGray);
        table.setFont(new java.awt.Font("Caviar Dreams", 1, 20)); // NOI18N
        table.setForeground(java.awt.Color.white);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Directory Number", "Bucket ID", "Local Depth", "Content of Bucket pointed by it"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setEnabled(false);
        table.setGridColor(java.awt.Color.black);
        table.setRowHeight(30);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(3).setResizable(false);
        }

        global_depth.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        gd_input.setBackground(java.awt.Color.darkGray);
        gd_input.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        gd_input.setForeground(new java.awt.Color(255, 255, 255));
        gd_input.setText("Enter GD");
        gd_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gd_inputActionPerformed(evt);
            }
        });

        BFR.setBackground(java.awt.Color.darkGray);
        BFR.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        BFR.setForeground(new java.awt.Color(255, 255, 255));
        BFR.setText("Enter BFR");
        BFR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BFRActionPerformed(evt);
            }
        });

        bfr_input.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout userinputLayout = new javax.swing.GroupLayout(userinput);
        userinput.setLayout(userinputLayout);
        userinputLayout.setHorizontalGroup(
            userinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userinputLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(userinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1932, Short.MAX_VALUE)
                    .addGroup(userinputLayout.createSequentialGroup()
                        .addGroup(userinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userinputLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(288, 288, 288))
                            .addGroup(userinputLayout.createSequentialGroup()
                                .addComponent(Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(user_input, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(171, 171, 171)))
                        .addGroup(userinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(BFR, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                            .addComponent(gd_input, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(userinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(userinputLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(global_depth, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userinputLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(bfr_input, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(85, 85, 85))
            .addGroup(userinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(userinputLayout.createSequentialGroup()
                    .addGap(93, 93, 93)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1373, Short.MAX_VALUE)))
        );
        userinputLayout.setVerticalGroup(
            userinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userinputLayout.createSequentialGroup()
                .addGroup(userinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userinputLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addGroup(userinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gd_input, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(userinputLayout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(global_depth, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(userinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(userinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Insert)
                        .addComponent(user_input, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bfr_input, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(userinputLayout.createSequentialGroup()
                        .addComponent(BFR, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGap(108, 108, 108)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(371, Short.MAX_VALUE))
            .addGroup(userinputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(userinputLayout.createSequentialGroup()
                    .addGap(68, 68, 68)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1035, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userinput, javax.swing.GroupLayout.PREFERRED_SIZE, 2046, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userinput, javax.swing.GroupLayout.DEFAULT_SIZE, 1161, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    boolean ok = false;
    
    private void InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertActionPerformed
        
   
           int value;
       if(b1 == false & !ok)
        {
            JOptionPane.showMessageDialog(null, "Enter BFR");
            b1=false;
            d1=false;
            return;
        }
        
        if(d1 == false & !ok)
        {
            JOptionPane.showMessageDialog(null, "Enter Global Depth");
            b1=false;
            d1=false;
            return;
        }
        
        if(b1&d1 & (!ok))
        {
            d=new Directory(bfr,globaldepth);
            ok = true;
            b1=false;
            d1=false;
        }
        

        try
        {
                 value = Integer.parseInt(user_input.getText());
                d.insert(value);


                ArrayList<Bucket> buckets = d.getBuckets();
                
                HashMap<Integer,Integer> index = d.getIndex();
                
                int len = (int) Math.pow(2, d.getDepth());
                for(int i = 0; i < len; i++)
                {
                    String str = Integer.toBinaryString(BitUtility.getRightMostBits(i,d.getDepth()));
                 StringBuilder sb = new StringBuilder();

                   for (int toPrepend=d.getDepth()-str.length(); toPrepend>0; toPrepend--)  sb.append('0');
                   
                 sb.append(str);
                  str = sb.toString();
                  
                   table.setValueAt((Object)(str),i,0);
                   
                    if(index.get(i) != -1)
                    {
                        Bucket b = buckets.get(index.get(i));
                        table.setValueAt((Object)(b.id),i,1);
                        table.setValueAt((Object)(b.getLocalDepth()),i,2);
                        table.setValueAt((Object)(b.print()),i,3);
                    }
                }
            
        
        
        }
        catch( NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Enter an integer");
        }
        
        
        
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_InsertActionPerformed

    private void user_inputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_inputFocusGained
        // TODO add your handling code here:
        if(user_input.getText().equals("Enter Integer")){
            user_input.setText(null);
            user_input.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_user_inputFocusGained

    private void user_inputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_inputFocusLost
        // TODO add your handling code here:
        if(user_input.getText().equals("")){
            user_input.setForeground(new Color(204,204,204));
            user_input.setText("Enter Integer");
        }
    }//GEN-LAST:event_user_inputFocusLost

    private void user_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_inputActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_user_inputActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked

    private void gd_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gd_inputActionPerformed
        // TODO add your handling code here:
          try
        {
        globaldepth = Integer.parseInt(global_depth.getText());
        d1=true;
        }
      catch( NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Enter an integer");
        }
          
//d = new Directory(3,val);
    }//GEN-LAST:event_gd_inputActionPerformed

    private void BFRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BFRActionPerformed
        // TODO add your handling code here:
        try
        {
        bfr = Integer.parseInt(bfr_input.getText());
        b1=true;
        }
       catch( NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Enter an integer");
        }
//d = new Directory(val,gd);
    }//GEN-LAST:event_BFRActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BFR;
    private javax.swing.JButton Insert;
    private javax.swing.JTextField bfr_input;
    private javax.swing.JButton gd_input;
    private javax.swing.JTextField global_depth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField user_input;
    private javax.swing.JPanel userinput;
    // End of variables declaration//GEN-END:variables
}
