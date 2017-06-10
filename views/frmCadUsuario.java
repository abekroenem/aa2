/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UsuarioController;
import helpers.Dialogs;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Usuario;

/**
 *
 * @author qwerty
 */
public class frmCadUsuario extends javax.swing.JFrame {

    private boolean m_showGrid = true;
    private String USUARIO_DUPLICADO, USUARIO_EM_BRANCO, SENHAS_DIFERENTES, SENHA_EM_BRANCO, USUARIO_INSERIDO_SUCESS, BTN_NOVO, BTN_SALVAR;
    private UsuarioController m_UserC;
    private Usuario m_objUser = null;

    public void Traduz() {
        SENHAS_DIFERENTES = "As Senhas devem ser identicas!";
        USUARIO_EM_BRANCO = "Usuario deve ser informado!";
        SENHA_EM_BRANCO = "Senhas devem ser informadas!";
        USUARIO_DUPLICADO = "Usuario ja cadastrado!";
        USUARIO_INSERIDO_SUCESS = "Usuario inserido com Sucesso!";
        BTN_NOVO = "Novo";
        BTN_SALVAR = "Salvar";
    }

    public frmCadUsuario(boolean showGrid) {
        initComponents();
        Traduz();
        defaultLayout(true);
        m_showGrid = showGrid;
        if (!showGrid) {
            defaultLayout(false);
            setSize(292, 200);
        }
    }

    private void defaultLayout(boolean dl) {
        btnCancelar.setEnabled(!dl);
        btnNovo.setText((dl) ? BTN_NOVO : BTN_SALVAR);
        txtUser.setText("");
        txtPass.setText("");
        txtConf.setText("");
        txtUser.setEnabled(!dl);
        txtPass.setEnabled(!dl);
        txtConf.setEnabled(!dl);
        chkAdmin.setEnabled(!dl);
        chkAdmin.setSelected(!dl);
        if (dl) {
            loadTable();
        }
    }

    private void loadTable() {
        if (m_showGrid) {
            try {
                m_UserC = new UsuarioController();
                List<Usuario> lstUsers = m_UserC.getAll();
                DefaultTableModel tablemd = (DefaultTableModel) tbUsers.getModel();
                tablemd.getDataVector().removeAllElements();
                if (lstUsers.size() > 0) {
                    for (Usuario user : lstUsers) {
                        tablemd.addRow(new Object[]{user.getId(), user.getName(), user.getAdmin()});
                    }
                    tbUsers.clearSelection();
                }
                tbUsers.updateUI();
            } catch (Exception ex) {
                Dialogs.showError(ex.getMessage());
            }
        }

    }

    private void InserirUsuario() throws Exception {
        m_UserC = new UsuarioController();
        m_UserC.Add(txtUser.getText(), txtPass.getText(), txtConf.getText(), chkAdmin.isSelected());
        defaultLayout(true);
        Dialogs.showInfo(USUARIO_INSERIDO_SUCESS);
    }

    private boolean UsuarioDuplicado() throws Exception {
        m_UserC = new UsuarioController();
        if (m_objUser == null) {
            Usuario objUser = m_UserC.SearchUserByName(txtUser.getText());
            if (objUser != null) {
                Dialogs.showWarning(USUARIO_DUPLICADO);
            }
            return (objUser != null);
        } else if (m_UserC.DuplicatedUser(m_objUser.getId(), txtUser.getText())) {
            Dialogs.showWarning(USUARIO_DUPLICADO);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jTextField2 = new javax.swing.JTextField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jPasswordField4 = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        chkAdmin = new javax.swing.JCheckBox();
        txtConf = new javax.swing.JPasswordField();
        btnNovo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbUsers = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SHX Usuarios");
        setResizable(false);

        jPasswordField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField4ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Administrador");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuario:");

        jLabel2.setText("Senha:");

        jLabel3.setText("Confirmar Senha:");

        jButton1.setText("Novo");

        jButton2.setText("Cancelar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Usuario", "ADM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        org.jdesktop.layout.GroupLayout jInternalFrame1Layout = new org.jdesktop.layout.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSeparator1)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                        .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1)
                            .add(jLabel2)
                            .add(jLabel3)
                            .add(jInternalFrame1Layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(jButton2)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(jTextField2)
                                .add(jPasswordField3)
                                .add(jPasswordField4)
                                .add(jCheckBox1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                            .add(jInternalFrame1Layout.createSequentialGroup()
                                .add(21, 21, 21)
                                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jPasswordField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jPasswordField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .add(9, 9, 9)
                .add(jCheckBox1)
                .add(3, 3, 3)
                .add(jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jButton2))
                .add(18, 18, 18)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 182, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10))
        );

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel4.setText("Confirmar Senha:");

        jLabel5.setText("Senha:");

        jLabel6.setText("Usuario:");

        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserKeyPressed(evt);
            }
        });

        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        chkAdmin.setText("Administrador");

        txtConf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConfKeyPressed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        tbUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Usuario", "ADM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUsersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbUsers);
        if (tbUsers.getColumnModel().getColumnCount() > 0) {
            tbUsers.getColumnModel().getColumn(0).setMinWidth(50);
            tbUsers.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbUsers.getColumnModel().getColumn(0).setMaxWidth(50);
            tbUsers.getColumnModel().getColumn(1).setMinWidth(170);
            tbUsers.getColumnModel().getColumn(1).setPreferredWidth(170);
            tbUsers.getColumnModel().getColumn(1).setMaxWidth(170);
        }

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel6)
                    .add(jLabel5)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 31, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(txtPass, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .add(txtUser))
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(txtConf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 143, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(chkAdmin)
                        .add(20, 20, 20))))
            .add(layout.createSequentialGroup()
                .add(26, 26, 26)
                .add(btnCancelar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(36, 36, 36)
                .add(btnNovo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(0, 155, Short.MAX_VALUE)
                    .add(jInternalFrame1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 155, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtUser, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtPass, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtConf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chkAdmin)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnNovo)
                    .add(btnCancelar))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(0, 149, Short.MAX_VALUE)
                    .add(jInternalFrame1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 149, Short.MAX_VALUE)))
        );

        setSize(new java.awt.Dimension(320, 328));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField4ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        if (!m_showGrid) {
            System.exit(0);
        } else {
            defaultLayout(true);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here
        try {
            if (btnNovo.getText().equals(BTN_SALVAR)) {
                if (m_objUser == null) {
                    if (m_showGrid) {
                        if (!UsuarioDuplicado()) {
                            InserirUsuario();
                        }
                    } else if (!m_showGrid) {
                        String uName = txtUser.getText();
                        InserirUsuario();
                        this.dispose();
                        new frmPrincipal(uName.toUpperCase()).setVisible(true);
                    }
                } else if (!UsuarioDuplicado()) {
                    m_UserC.Edit(m_objUser.getId(), txtUser.getText(), txtConf.getText(), txtPass.getText(), chkAdmin.isSelected());
                    Dialogs.showInfo(USUARIO_INSERIDO_SUCESS);
                    m_objUser = null;
                    defaultLayout(true);
                }
            } else if (btnNovo.getText().equals(BTN_NOVO)) {
                defaultLayout(false);
                txtUser.requestFocus();
            }
        } catch (Exception ex) {
            Dialogs.showError(ex.getMessage());
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void tbUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsersMouseClicked
        // TODO add your handling code here:
        try {
            m_objUser = m_UserC.getByID(((Integer) tbUsers.getModel().getValueAt(tbUsers.getSelectedRow(), 0)));
            if (m_objUser != null) {
                defaultLayout(false);
                txtUser.setText(m_objUser.getName());
                txtPass.setText(m_objUser.getPassword());
                txtConf.setText(m_objUser.getPassword());
                chkAdmin.setSelected(m_objUser.getAdmin());
                txtUser.selectAll();
                txtUser.requestFocus();
            }
        } catch (Exception e) {
            Dialogs.showError(e.getMessage());
        }

    }//GEN-LAST:event_tbUsersMouseClicked

    private void txtUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            txtPass.requestFocus();
        }
    }//GEN-LAST:event_txtUserKeyPressed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            txtConf.requestFocus();
        }
    }//GEN-LAST:event_txtPassKeyPressed

    private void txtConfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            chkAdmin.requestFocus();
        }
    }//GEN-LAST:event_txtConfKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JCheckBox chkAdmin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JPasswordField jPasswordField4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tbUsers;
    private javax.swing.JPasswordField txtConf;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
