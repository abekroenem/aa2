/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.FuncionarioController;
import helpers.Config;
import helpers.Dialogs;
import helpers.Formats;
import helpers.Forms;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.objects.Global;
import models.Funcionario;

/**
 *
 * @author qwerty
 */
public class infrmCadFun extends javax.swing.JInternalFrame {

    private FuncionarioController m_FuncC = null;
    private String DELETAR_FUNC, CPF_CADASTRADO, FUNCIONARIO_INSERIDO_SUCESS, FUNCINOARIO_EDITADO_SUCESS, BTN_NOVO, BTN_SALVAR, DIA, MES, SELEC;
    private Funcionario m_objFunc = null;
    private final boolean ersHora = false;
    private int hora_base = 0;

    public void Traduz() {

        ResourceBundle rbl = null;
        rbl = Config.getResources();

        CPF_CADASTRADO = rbl.getString("cpf_cadastrado");
        FUNCIONARIO_INSERIDO_SUCESS = rbl.getString("func_sucess");
        FUNCINOARIO_EDITADO_SUCESS = rbl.getString("func_editado");
        BTN_NOVO = rbl.getString("btnNovo");
        BTN_SALVAR = rbl.getString("btnSalvar");
        DELETAR_FUNC = rbl.getString("deletar_func");

        lblNome.setText(rbl.getString("nome"));
        lblCPF.setText(rbl.getString("cpf"));
        lblSalario.setText(rbl.getString("salario"));
        lblJornada.setText(rbl.getString("jornada"));
        lblValorHora.setText(rbl.getString("valor_hora"));

        btnDeletar.setText(rbl.getString("btndeletar"));
        btnCancelar.setText(rbl.getString("btncancelar"));

        tbFunc.getColumnModel().getColumn(1).setHeaderValue(rbl.getString("cadFun"));
        tbFunc.getColumnModel().getColumn(2).setHeaderValue(rbl.getString("cpf"));
        tbFunc.getColumnModel().getColumn(3).setHeaderValue(rbl.getString("salario"));
        tbFunc.getColumnModel().getColumn(4).setHeaderValue(rbl.getString("v_hora"));

        DIA = rbl.getString("dia");
        MES = rbl.getString("mes");
        SELEC = rbl.getString("selecione");

    }

    private void preencherCombo() {

        String[] jornadas = {
            SELEC,
            "8h/" + DIA + " - " + "220h/" + MES,
            "7h/" + DIA + " - " + "210h/" + MES,
            "6h/" + DIA + " - " + "180h/" + MES,
            "5h/" + DIA + " - " + "150h/" + MES,
            "4h/" + DIA + " - " + "120h/" + MES};

        cbJornada.removeAllItems();
        for (String obj : jornadas) {
            cbJornada.addItem(obj);
        }
    }

    private void defaultLayout(boolean dl) {
        btnCancelar.setEnabled(!dl);
        btnNovo.setText((dl) ? BTN_NOVO : BTN_SALVAR);
        txtNome.setText("");
        txtCPF.setText("");
        txtSalario.setText("");
        lblValorHoraT.setText("0,00 R$/h");
        txtNome.setEnabled(!dl);
        txtCPF.setEnabled(!dl);
        txtSalario.setEnabled(!dl);
        cbJornada.setEnabled(!dl);
        btnDeletar.setEnabled(false);
        if (dl) {
            loadTable();
            preencherCombo();
        }

    }

    private void loadTable() {
        try {
            m_FuncC = new FuncionarioController();
            List<Funcionario> lstFor = m_FuncC.getAll();
            DefaultTableModel tablemd = (DefaultTableModel) tbFunc.getModel();
            tablemd.getDataVector().removeAllElements();
            if (lstFor.size() > 0) {
                for (Funcionario forn : lstFor) {
                    tablemd.addRow(new Object[]{forn.getId(),
                        forn.getNome(),
                        Formats.CPF.Format(forn.getCPF()),
                        Formats.Valor.Format(forn.getSalario()),
                        Formats.Valor.Format(forn.getValor_hora())});
                }
                tbFunc.clearSelection();
            }
            tbFunc.updateUI();
        } catch (Exception ex) {
            Dialogs.showError(ex.getMessage());
        }
    }

    private int getHoraCombo(int selectedCombo) {

        return 9 - selectedCombo;
    }

    private void InserirFuncionario() throws Exception {
        m_FuncC = new FuncionarioController();
        m_FuncC.Add(txtNome.getText(), txtCPF.getText(),
                Double.parseDouble((txtSalario.getText().isEmpty()) ? "0" : txtSalario.getText()),
                hora_base);
        defaultLayout(true);
        Dialogs.showInfo(FUNCIONARIO_INSERIDO_SUCESS);
    }

    private boolean FuncionarioDuplicado() throws Exception {
        m_FuncC = new FuncionarioController();
        if (m_objFunc == null) {
            Funcionario objFunc = m_FuncC.SearchFuncionarioByCPF(Formats.CPF.Unformat(txtCPF.getText()));
            if (objFunc != null) {
                Dialogs.showWarning(CPF_CADASTRADO);
                return true;
            }
            return (objFunc != null);
        } else if (m_FuncC.DuplicatedFuncionario(m_objFunc.getId(), Formats.CPF.Unformat(txtCPF.getText()))) {
            Dialogs.showWarning(CPF_CADASTRADO);
            return true;
        } else {
            return false;
        }
    }

    public infrmCadFun() {
        initComponents();
        Traduz();
        defaultLayout(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNome = new javax.swing.JLabel();
        lblCPF = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblSalario = new javax.swing.JLabel();
        lblValorHora = new javax.swing.JLabel();
        lblJornada = new javax.swing.JLabel();
        lblValorHoraT = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFunc = new javax.swing.JTable();
        txtCPF = new javax.swing.JFormattedTextField();
        btnNovo = new javax.swing.JButton();
        txtSalario = new javax.swing.JTextField();
        btnDeletar = new javax.swing.JButton();
        cbJornada = new javax.swing.JComboBox<>();

        setBorder(new javax.swing.border.SoftBevelBorder(0));
        setClosable(true);
        setTitle("SHX Funcionarios");

        lblNome.setText("Nome");

        lblCPF.setText("CPF");

        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
        });

        lblSalario.setText("Salario");

        lblValorHora.setText("Valor Hora");

        lblJornada.setText("Jornada");

        lblValorHoraT.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        lblValorHoraT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValorHoraT.setText("Valor Hora");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tbFunc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Salario", "V. Hora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbFunc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFuncMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbFunc);
        if (tbFunc.getColumnModel().getColumnCount() > 0) {
            tbFunc.getColumnModel().getColumn(0).setMinWidth(35);
            tbFunc.getColumnModel().getColumn(0).setPreferredWidth(35);
            tbFunc.getColumnModel().getColumn(0).setMaxWidth(35);
            tbFunc.getColumnModel().getColumn(1).setMinWidth(180);
            tbFunc.getColumnModel().getColumn(1).setPreferredWidth(180);
            tbFunc.getColumnModel().getColumn(1).setMaxWidth(180);
            tbFunc.getColumnModel().getColumn(2).setMinWidth(110);
            tbFunc.getColumnModel().getColumn(2).setPreferredWidth(110);
            tbFunc.getColumnModel().getColumn(2).setMaxWidth(110);
            tbFunc.getColumnModel().getColumn(3).setMinWidth(90);
            tbFunc.getColumnModel().getColumn(3).setPreferredWidth(90);
            tbFunc.getColumnModel().getColumn(3).setMaxWidth(90);
            tbFunc.getColumnModel().getColumn(4).setMinWidth(70);
            tbFunc.getColumnModel().getColumn(4).setPreferredWidth(70);
            tbFunc.getColumnModel().getColumn(4).setMaxWidth(70);
        }

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCPFKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCPFKeyPressed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        txtSalario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSalario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSalarioKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSalarioKeyPressed(evt);
            }
        });

        btnDeletar.setText("Deletar");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        cbJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbJornadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNome)
                            .addComponent(lblCPF))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSalario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtNome)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(txtSalario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblValorHora)
                                .addGap(55, 55, 55)
                                .addComponent(lblValorHoraT, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblJornada)
                                .addGap(18, 18, 18)
                                .addComponent(cbJornada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCPF)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblJornada)
                    .addComponent(cbJornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblValorHoraT)
                        .addComponent(lblValorHora))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSalario)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnNovo)
                    .addComponent(btnDeletar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
        Forms.goNextField(evt.getKeyCode(), txtCPF);
    }//GEN-LAST:event_txtNomeKeyPressed

    private void txtCPFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPFKeyTyped
        Forms.OnlyNumbers(evt);
    }//GEN-LAST:event_txtCPFKeyTyped

    private void txtSalarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalarioKeyTyped
        Forms.OnlyNumbers(evt);
    }//GEN-LAST:event_txtSalarioKeyTyped

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        try {
            if (btnNovo.getText().equals(BTN_SALVAR)) {
                if (m_objFunc == null) {
                    if (!FuncionarioDuplicado()) {
                        InserirFuncionario();
                    }
                } else if (!FuncionarioDuplicado()) {
                    m_FuncC.Edit(m_objFunc.getId(), txtNome.getText(), txtCPF.getText(),
                            Double.parseDouble((txtSalario.getText().isEmpty()) ? "0" : txtSalario.getText()),
                            hora_base);
                    Dialogs.showInfo(FUNCINOARIO_EDITADO_SUCESS);
                    m_objFunc = null;
                    defaultLayout(true);
                }
            } else if (btnNovo.getText().equals(BTN_NOVO)) {
                defaultLayout(false);
                txtNome.requestFocus();
            }
        } catch (Exception ex) {
            Dialogs.showError(ex.getMessage());
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void txtCPFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPFKeyPressed
        Forms.goNextField(evt.getKeyCode(), txtSalario);
    }//GEN-LAST:event_txtCPFKeyPressed

    private void txtSalarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSalarioKeyPressed
        Forms.goNextField(evt.getKeyCode(), cbJornada);

    }//GEN-LAST:event_txtSalarioKeyPressed

    private void tbFuncMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFuncMouseClicked
        // TODO add your handling code here:
        try {
            m_objFunc = m_FuncC.getByID(((Integer) tbFunc.getModel().getValueAt(tbFunc.getSelectedRow(), 0)));
            if (m_objFunc != null) {
                defaultLayout(false);
                txtNome.setText(m_objFunc.getNome());
                txtCPF.setText(m_objFunc.getCPF());
                txtSalario.setText(String.valueOf(m_objFunc.getSalario()));
                hora_base = m_objFunc.gethora_dia();
                cbJornada.setSelectedIndex(9 - hora_base);
                lblValorHoraT.setText(Formats.Valor.Format(m_objFunc.getValor_hora()));
                txtNome.selectAll();
                txtNome.requestFocus();
                btnDeletar.setEnabled(true);

            }
        } catch (Exception e) {
            Dialogs.showError(e.getMessage());
        }
    }//GEN-LAST:event_tbFuncMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        defaultLayout(true);
        m_objFunc = null;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        // TODO add your handling code here:
        if (Dialogs.showConfirm(DELETAR_FUNC)) {
            if (m_objFunc != null) {
                try {
                    m_FuncC.Delete(m_objFunc.getId(), m_objFunc.getNome());
                    defaultLayout(true);
                } catch (Exception ex) {
                    Dialogs.showError(ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void cbJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbJornadaActionPerformed
        // TODO add your handling code here:
        if (cbJornada.getSelectedIndex() > 0) {
            hora_base = getHoraCombo(cbJornada.getSelectedIndex());
            double salario = Double.parseDouble((txtSalario.getText().isEmpty()) ? "0" : txtSalario.getText());
            salario = Formats.Decimal.Format((salario / Env.Constants.getJornada(hora_base)));
            if ((salario > 0) && (salario != Global.Infinity)) {
                lblValorHoraT.setText(String.format("%.2f R$/h", salario));
            } else {
                lblValorHoraT.setText("0,00 R$/h");
            }
        }
    }//GEN-LAST:event_cbJornadaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox<String> cbJornada;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblJornada;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSalario;
    private javax.swing.JLabel lblValorHora;
    private javax.swing.JLabel lblValorHoraT;
    private javax.swing.JTable tbFunc;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSalario;
    // End of variables declaration//GEN-END:variables
}
