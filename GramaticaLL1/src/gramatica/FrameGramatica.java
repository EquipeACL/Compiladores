package gramatica;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class FrameGramatica extends javax.swing.JFrame {
    private Gramatica g ;
        public FrameGramatica() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaProducoes = new javax.swing.JTextArea();
        jtfterminais = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfNterminais = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaFirstFollow = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaPreditiva = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboxStart = new javax.swing.JComboBox<>();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtaProducoes.setColumns(20);
        jtaProducoes.setRows(5);
        jtaProducoes.setText("A->BAA\nA->C\nB->+\nB->-\nB->*\nB->/\nC->x\nC->y\nC->z");
        jScrollPane1.setViewportView(jtaProducoes);

        jtfterminais.setText("+,-,*,/,x,y,z");

        jLabel1.setText("Simbolos terminais:");

        jLabel2.setText("Simbolos Não terminais:");

        jtfNterminais.setText("A,B,C");
        jtfNterminais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfNterminaisKeyTyped(evt);
            }
        });

        jLabel3.setText("Produções:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("GRAMATICA");

        jtaFirstFollow.setEditable(false);
        jtaFirstFollow.setColumns(20);
        jScrollPane2.setViewportView(jtaFirstFollow);

        jLabel5.setText("First's & Follow's:");

        jButton1.setText("Calcular");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Limpar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tabelaPreditiva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tabelaPreditiva);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("TABELA:");

        jLabel7.setText("Simbolo inicial:");

        comboxStart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfNterminais, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfterminais, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7)
                                        .addGap(3, 3, 3)
                                        .addComponent(comboxStart, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addGap(3, 3, 3)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtfterminais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jtfNterminais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(comboxStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)))
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Criando arraylist de não terminais
        String Nterminais = jtfNterminais.getText();
        String[] arrayNterminais = Nterminais.split(",");
        ArrayList<String> listaNterminais = new ArrayList<String>();
        for(int i=0;i<arrayNterminais.length;i++){
            listaNterminais.add(arrayNterminais[i]);
        }
        //Criando arraylist de terminais
        String terminais = jtfterminais.getText();
        String[] arrayterminais = terminais.split(",");
        ArrayList<String> listaterminais = new ArrayList<String>();
        for(int i=0;i<arrayterminais.length;i++){
            listaterminais.add(arrayterminais[i]);
        }
        //Criando arraylist de produções
        String producoes = jtaProducoes.getText();
        String[] arrayProducoes = producoes.split("\n");
        ArrayList<String> listaProducoes = new ArrayList<String>();
        for(int i=0;i<arrayProducoes.length;i++){
            listaProducoes.add(arrayProducoes[i]);
        }
        System.out.print("Terminais: ");
        for(String temp:listaterminais){
            System.out.print(temp+", ");
        }
        System.out.println();
        System.out.print("Não-Terminais: ");
        for(String temp:listaNterminais){
            System.out.print(temp+", ");
        }
        System.out.println();
        System.out.print("Producoes: ");
        for(String temp:listaProducoes){
            System.out.print(temp+", ");
        }
        String start = ""+comboxStart.getSelectedItem();
        g = new Gramatica(listaNterminais,listaterminais,listaProducoes,start);
        calcularFirstFollow();
        gerarTabela();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtfNterminaisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNterminaisKeyTyped
        String Nterminais = jtfNterminais.getText();
        String[] vetorNterminais = Nterminais.split(",");
        comboxStart.setModel(new javax.swing.DefaultComboBoxModel<>(vetorNterminais));
    }//GEN-LAST:event_jtfNterminaisKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jtfNterminais.setText("");
        jtfterminais.setText("");
        jtaProducoes.setText("");
        jtaFirstFollow.setText("");
        comboxStart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{}));
        DefaultTableModel modelo = (DefaultTableModel) tabelaPreditiva.getModel();
        modelo.setNumRows(0);
        tabelaPreditiva.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, true
            };
        });
    }//GEN-LAST:event_jButton3ActionPerformed

  
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
            java.util.logging.Logger.getLogger(FrameGramatica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGramatica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGramatica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGramatica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameGramatica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboxStart;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtaFirstFollow;
    private javax.swing.JTextArea jtaProducoes;
    private javax.swing.JTextField jtfNterminais;
    private javax.swing.JTextField jtfterminais;
    private javax.swing.JTable tabelaPreditiva;
    // End of variables declaration//GEN-END:variables

    private void gerarTabela() {
        String terminais = jtfterminais.getText();
        terminais = " ,"+terminais+",$";
        String[] teste = terminais.split(",");
        tabelaPreditiva.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                teste
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, true
            };
        });
        String[][] tabela = g.tabela();
        String[][] tabelaaux = new String[tabela.length][tabela[0].length];
        String Nterminais = jtfNterminais.getText();
        String[] arrayNterminais = Nterminais.split(",");
        for(int i=0;i<arrayNterminais.length;i++){
            tabelaaux[i][0] = arrayNterminais[i];
        }
        for(int i=0;i<tabela.length;i++){
            for(int j=0;j<tabela[i].length-1;j++){
                tabelaaux[i][j+1] = tabela[i][j];
            }
        }    
        DefaultTableModel modelo = (DefaultTableModel) tabelaPreditiva.getModel();
        modelo.setNumRows(0);
        for(int i=0;i<tabelaaux.length;i++){
             modelo.addRow(tabelaaux[i]);
        }
    }

    private void calcularFirstFollow() {
        jtaFirstFollow.setText(" - - - - - - - - - - Gramatica - - - - - - - - - -\n");
        jtaFirstFollow.setText(jtaFirstFollow.getText()+g.listarGramatica());
        jtaFirstFollow.setText(jtaFirstFollow.getText()+" - - - - - - - - - - Lista de First's - - - - - - - - - -\n");
        jtaFirstFollow.setText(jtaFirstFollow.getText()+g.listarFirst());
        jtaFirstFollow.setText(jtaFirstFollow.getText()+" - - - - - - - - - - Lista de Follow's - - - - - - - - - -\n");
        jtaFirstFollow.setText(jtaFirstFollow.getText()+g.listarFollow()); 
    }
}
