/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Konto;
import data.KontoBenutzer;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class KontoGui extends javax.swing.JFrame implements obs.Observer
{
    private Konto konto = new Konto();
    
    private LinkedList<Thread> threads = new LinkedList();
    private LinkedList<ThreadState> tsList = new LinkedList();
    private LinkedList<JFrame> frames = new LinkedList();

    public KontoGui()
    {
        initComponents();
 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        miAddUser = new javax.swing.JMenuItem();
        miPerformAccountTest = new javax.swing.JMenuItem();
        miKillUserThreads = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        miNewKonto = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        lbAmount = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        liUser = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDisplay = new javax.swing.JTextArea();

        miAddUser.setText("add user");
        miAddUser.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addUser(evt);
            }
        });
        jPopupMenu1.add(miAddUser);

        miPerformAccountTest.setText("perform account test");
        miPerformAccountTest.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                miPerformAccountTestActionPerformed(evt);
            }
        });
        jPopupMenu1.add(miPerformAccountTest);

        miKillUserThreads.setText("Kill User-Threads");
        miKillUserThreads.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                miKillUserThreadsActionPerformed(evt);
            }
        });
        jPopupMenu1.add(miKillUserThreads);

        miNewKonto.setText("new Konto");
        miNewKonto.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                newKonto(evt);
            }
        });
        jPopupMenu2.add(miNewKonto);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setToolTipText("");
        jPanel1.setLayout(new java.awt.BorderLayout());

        lbAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAmount.setText("       ");
        jPanel1.add(lbAmount, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(47, 153));
        jPanel2.setLayout(new java.awt.BorderLayout());

        liUser.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(liUser);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Log-output", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel3.setLayout(new java.awt.BorderLayout());

        taDisplay.setEditable(false);
        taDisplay.setColumns(20);
        taDisplay.setRows(5);
        taDisplay.setComponentPopupMenu(jPopupMenu2);
        jScrollPane2.setViewportView(taDisplay);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newKonto(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newKonto
    {//GEN-HEADEREND:event_newKonto
        konto = new Konto();
        taDisplay.append("Created new Konto\n");
        liUser.setModel(konto);
        konto.register(this);
        
        if(threads.size() > 0)
        {
            
            killThreads();
            
            taDisplay.append("Terminated all old unused Threads\n");
        }
        update();
    }//GEN-LAST:event_newKonto

    private void addUser(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addUser
    {//GEN-HEADEREND:event_addUser
        String name = JOptionPane.showInputDialog("Username: ");
        
        KontoBenutzer u = new KontoBenutzer(name,konto,taDisplay);
        konto.addUser(u);
    }//GEN-LAST:event_addUser

    private void miPerformAccountTestActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miPerformAccountTestActionPerformed
    {//GEN-HEADEREND:event_miPerformAccountTestActionPerformed
        
        JFrame frame = new JFrame();
        
        for(JFrame f : frames)
        {
            if(f.isVisible())
                f.setVisible(false);
        }
        for(int i : liUser.getSelectedIndices())
        {
            ThreadState st = new ThreadState(konto.getUserAt(i).getUsername());
            tsList.add(st);
            konto.getUserAt(i).setThreadState(st);
        }
        if(tsList.size() > 3)
            frame.setLayout(new GridLayout(tsList.size()%3 == 0 ? tsList.size()/3 : tsList.size()/3+1, 3));
        else
            frame.setLayout(new GridLayout(1, tsList.size()));
        
        for(ThreadState st: tsList)
        {
            frame.add(st);
            System.out.println("add");
        }
        
        frames.add(frame)
        frame.setSize(600,400);
        frame.setVisible(true);
        
        for(int i: liUser.getSelectedIndices())
        {
            Thread t = new Thread(konto.getUserAt(i),konto.getUserAt(i).getUsername());
            t.setPriority(Thread.currentThread().getPriority()+1);
            threads.add(t);
            t.start();
        }
    }//GEN-LAST:event_miPerformAccountTestActionPerformed

    private void miKillUserThreadsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_miKillUserThreadsActionPerformed
    {//GEN-HEADEREND:event_miKillUserThreadsActionPerformed
        killThreads();
        taDisplay.append("Killed all User-Threads\n");
    }//GEN-LAST:event_miKillUserThreadsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(KontoGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(KontoGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(KontoGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(KontoGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new KontoGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbAmount;
    private javax.swing.JList liUser;
    private javax.swing.JMenuItem miAddUser;
    private javax.swing.JMenuItem miKillUserThreads;
    private javax.swing.JMenuItem miNewKonto;
    private javax.swing.JMenuItem miPerformAccountTest;
    private javax.swing.JTextArea taDisplay;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update()
    {
        lbAmount.setText(String.format("%.2f Euro",konto.getAmount()));
    }
    
    public void killThreads()
    {
        for(JFrame f : frames)
        {
            f.setVisible(false);
        }
        
        frames.clear();

        for (Thread t : threads)
        {
            t.stop();
        }
    }
}
