/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.FuncionarioController;
import controllers.PontoController;
import helpers.Checks;
import helpers.Config;
import helpers.Dialogs;
import helpers.Formats;
import helpers.Forms;
import java.awt.Color;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JDesktopPane;
import javax.swing.table.DefaultTableModel;
import models.Funcionario;
import models.Ponto;

/**
 *
 * @author qwerty
 */
public class infrmPonto extends javax.swing.JInternalFrame {

    private PontoController m_PontoC = null;
    private String PONTO_REGISTRADO, PONTO_EDITADO, PONTO_JA_CADASTRADO, BTN_NOVO, BTN_SALVAR, DELETAR_PNT, wk_day, st_day, sn_day;
    private Ponto m_objPonto = null;
    public Funcionario m_objFunc = null;
    private boolean ersHora = false;
    private JDesktopPane inDesktop = null;
    private boolean fillPercLabel = false;

    public void Traduz() {
        ResourceBundle rbl = null;
        rbl = Config.getResources();

        PONTO_JA_CADASTRADO = "Ponto ja cadastrado para o funcionario";
        PONTO_REGISTRADO = "Ponto inserido com Sucesso!";
        PONTO_EDITADO = "Ponto editado com Sucesso!";
        DELETAR_PNT = rbl.getString("deletar_pnt");

        BTN_NOVO = rbl.getString("btnNovo");
        BTN_SALVAR = rbl.getString("btnSalvar");

        btnDeletar.setText(rbl.getString("btndeletar"));
        btnCancelar.setText(rbl.getString("btncancelar"));

        lblFunc.setText(rbl.getString("cadFun"));
        lblData.setText(rbl.getString("data"));
        lblEA.setText(rbl.getString("entrada"));
        lblSA.setText(rbl.getString("saida"));
        lblEB.setText(rbl.getString("entrada"));
        lblSB.setText(rbl.getString("saida"));

        tbPonto.getColumnModel().getColumn(1).setHeaderValue(rbl.getString("data"));
        tbPonto.getColumnModel().getColumn(2).setHeaderValue(rbl.getString("cadFun"));
        tbPonto.getColumnModel().getColumn(3).setHeaderValue(rbl.getString("h_base"));
        tbPonto.getColumnModel().getColumn(4).setHeaderValue(rbl.getString("h_trab"));
        tbPonto.getColumnModel().getColumn(5).setHeaderValue(rbl.getString("h_ex"));
        tbPonto.getColumnModel().getColumn(7).setHeaderValue(rbl.getString("t_extra"));
        tbPonto.getColumnModel().getColumn(8).setHeaderValue(rbl.getString("t_dia"));

        wk_day = rbl.getString("wk_day");
        st_day = rbl.getString("st_day");
        sn_day = rbl.getString("sn_day");

    }

    private void defaultLayout(boolean dl) {

        btnCancelar.setEnabled(!dl);
        btnNovo.setText((dl) ? BTN_NOVO : BTN_SALVAR);
        btnSearch.setEnabled(!dl);
        txtFuncionario.setText("");
        txtData.setText("");
        txtEntrada1.setText("");
        txtEntrada2.setText("");
        txtSaida1.setText("");
        txtSaida2.setText("");
        txtFuncionario.setEnabled(!dl);
        txtData.setEnabled(!dl);
        txtEntrada1.setEnabled(!dl);
        txtEntrada2.setEnabled(!dl);
        txtSaida1.setEnabled(!dl);
        txtSaida2.setEnabled(!dl);
        btnDeletar.setEnabled(false);
        lblPerc.setVisible(false);
        Traduz();
        lblEA.setForeground(Color.black);
        lblSA.setForeground(Color.black);
        lblEB.setForeground(Color.black);
        lblSB.setForeground(Color.black);
        if (dl) {
            loadTable();
        }
    }

    private void loadTable() {
        try {
            m_PontoC = new PontoController();
            List<Ponto> lstPnt = m_PontoC.getAll();
            DefaultTableModel tablemd = (DefaultTableModel) tbPonto.getModel();
            tablemd.getDataVector().removeAllElements();
            if (lstPnt.size() > 0) {
                for (Ponto pnt : lstPnt) {

                    FuncionarioController ctlFnc = new FuncionarioController();
                    Funcionario objFunc = ctlFnc.getByID(pnt.getId_funcionario());

                    tablemd.addRow(new Object[]{
                        pnt.getId(),
                        Formats.Data.Format(pnt.getData()),
                        objFunc.getNome(),
                        Formats.Hora.Format(objFunc.gethora_dia() * 60), // valor vem do banco em horas
                        Formats.Hora.Format(pnt.getHoras_Trabalhadas()),
                        Formats.Hora.Format(pnt.getHoras_excedidas()),
                        Formats.Percent.Format(pnt.getPercent_aplicado()),
                        Formats.Valor.Format(pnt.getValor_extra()),
                        Formats.Valor.Format(pnt.getTotal_recebido())});
                }
                tbPonto.clearSelection();
            }
            tbPonto.updateUI();
        } catch (Exception ex) {
            Dialogs.showError(ex.getMessage());
        }
    }

    private void InserirPonto() throws Exception {
        m_PontoC = new PontoController();

        int id_fnc = (m_objFunc == null) ? 0 : m_objFunc.getId();

        m_PontoC.Add(
                Formats.Data.Unformat(txtData.getText()),
                id_fnc,
                Formats.Hora.Unformat(txtEntrada1.getText()),
                Formats.Hora.Unformat(txtSaida1.getText()),
                Formats.Hora.Unformat(txtEntrada2.getText()),
                Formats.Hora.Unformat(txtSaida2.getText()));
        defaultLayout(true);
        Dialogs.showInfo(PONTO_REGISTRADO);
    }

    private boolean PontoDuplicado() throws Exception {
        m_PontoC = new PontoController();
        if (m_objPonto == null) {
            Ponto objPnt = m_PontoC.SearchUserByFunDay(Formats.Data.Unformat(txtData.getText()), HIDE_ON_CLOSE);
            if (objPnt != null) {
                Dialogs.showWarning(PONTO_JA_CADASTRADO);
                return true;
            }
            return (objPnt != null);
        } else if (m_PontoC.DuplicatedPonto(m_objPonto.getId(), Formats.Data.Unformat(txtData.getText()), m_objPonto.getId_funcionario())) {
            Dialogs.showWarning(PONTO_JA_CADASTRADO);
            return true;
        } else {
            return false;
        }
    }

    public void setFuncionario(String func) {
        txtFuncionario.setText(func);
    }

    public infrmPonto(JDesktopPane inDesktop) {
        initComponents();
        this.inDesktop = inDesktop;
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

        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        lblData = new javax.swing.JLabel();
        lblFunc = new javax.swing.JLabel();
        txtData = new javax.swing.JFormattedTextField();
        txtFuncionario = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPonto = new javax.swing.JTable();
        btnDeletar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblEA = new javax.swing.JLabel();
        txtEntrada1 = new javax.swing.JFormattedTextField();
        lblSA = new javax.swing.JLabel();
        txtSaida1 = new javax.swing.JFormattedTextField();
        txtSaida2 = new javax.swing.JFormattedTextField();
        lblSB = new javax.swing.JLabel();
        txtEntrada2 = new javax.swing.JFormattedTextField();
        lblEB = new javax.swing.JLabel();
        lblPerc = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBorder(new javax.swing.border.SoftBevelBorder(0));
        setClosable(true);
        setTitle("SHX Funcionarios");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        lblData.setText("Data");

        lblFunc.setText("Funcionario");

        try {
            txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/2017")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDataFocusLost(evt);
            }
        });
        txtData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDataKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDataKeyPressed(evt);
            }
        });

        txtFuncionario.setEditable(false);

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tbPonto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Data", "Funcionario", "H. Base", "H. Trab.", "H. Ex.", "%", "T. Extra", "T. Dia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPonto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPontoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPonto);
        if (tbPonto.getColumnModel().getColumnCount() > 0) {
            tbPonto.getColumnModel().getColumn(0).setMinWidth(30);
            tbPonto.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbPonto.getColumnModel().getColumn(0).setMaxWidth(30);
            tbPonto.getColumnModel().getColumn(1).setMinWidth(80);
            tbPonto.getColumnModel().getColumn(1).setPreferredWidth(80);
            tbPonto.getColumnModel().getColumn(1).setMaxWidth(80);
            tbPonto.getColumnModel().getColumn(2).setMinWidth(250);
            tbPonto.getColumnModel().getColumn(2).setPreferredWidth(250);
            tbPonto.getColumnModel().getColumn(2).setMaxWidth(250);
            tbPonto.getColumnModel().getColumn(3).setMinWidth(60);
            tbPonto.getColumnModel().getColumn(3).setPreferredWidth(60);
            tbPonto.getColumnModel().getColumn(3).setMaxWidth(60);
            tbPonto.getColumnModel().getColumn(4).setMinWidth(60);
            tbPonto.getColumnModel().getColumn(4).setPreferredWidth(60);
            tbPonto.getColumnModel().getColumn(4).setMaxWidth(60);
            tbPonto.getColumnModel().getColumn(5).setMinWidth(60);
            tbPonto.getColumnModel().getColumn(5).setPreferredWidth(60);
            tbPonto.getColumnModel().getColumn(5).setMaxWidth(60);
            tbPonto.getColumnModel().getColumn(6).setMinWidth(60);
            tbPonto.getColumnModel().getColumn(6).setPreferredWidth(60);
            tbPonto.getColumnModel().getColumn(6).setMaxWidth(60);
            tbPonto.getColumnModel().getColumn(7).setMinWidth(80);
            tbPonto.getColumnModel().getColumn(7).setPreferredWidth(80);
            tbPonto.getColumnModel().getColumn(7).setMaxWidth(80);
            tbPonto.getColumnModel().getColumn(8).setMinWidth(130);
            tbPonto.getColumnModel().getColumn(8).setPreferredWidth(130);
            tbPonto.getColumnModel().getColumn(8).setMaxWidth(130);
        }

        btnDeletar.setText("Deletar");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        lblEA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEA.setText("Entrada");
        lblEA.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        try {
            txtEntrada1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtEntrada1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEntrada1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEntrada1KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEntrada1KeyPressed(evt);
            }
        });

        lblSA.setText("Saida");

        try {
            txtSaida1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtSaida1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSaida1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaida1ActionPerformed(evt);
            }
        });
        txtSaida1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSaida1KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSaida1KeyPressed(evt);
            }
        });

        try {
            txtSaida2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtSaida2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSaida2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSaida2KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSaida2KeyPressed(evt);
            }
        });

        lblSB.setText("Saida");

        try {
            txtEntrada2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtEntrada2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEntrada2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEntrada2KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEntrada2KeyPressed(evt);
            }
        });

        lblEB.setText("Entrada");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblEA)
                        .addGap(80, 80, 80)
                        .addComponent(lblEB))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEntrada1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSA)
                            .addComponent(txtSaida1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEntrada2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSB)
                            .addComponent(txtSaida2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEA)
                    .addComponent(lblEB))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtEntrada1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(lblSA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtSaida1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtEntrada2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(lblSB)
                        .addGap(3, 3, 3)
                        .addComponent(txtSaida2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        lblPerc.setText("Dom: 100% sobre hora trabalhada");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(220, 220, 220)
                                .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFunc)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(lblData)))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(lblPerc, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lblFunc)
                        .addGap(41, 41, 41)
                        .addComponent(lblData))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(lblPerc))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeletar)
                    .addComponent(btnCancelar)
                    .addComponent(btnNovo))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataKeyTyped
        Forms.OnlyNumbers(evt);
    }//GEN-LAST:event_txtDataKeyTyped

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        infrmConsFunc objConsF = new infrmConsFunc(this);
        Forms.showInternal(this.inDesktop, objConsF);

    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataKeyPressed
        Forms.goNextField(evt.getKeyCode(), txtEntrada1);
    }//GEN-LAST:event_txtDataKeyPressed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        defaultLayout(true);
        m_objFunc = null;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:   
        try {
            if (btnNovo.getText().equals(BTN_SALVAR)) {
                if (m_objPonto == null) {
                    if (!PontoDuplicado()) {
                        InserirPonto();
                    }
                } else if (!PontoDuplicado()) {
                    m_PontoC.Edit(
                            m_objPonto.getId(),
                            Formats.Data.Unformat(txtData.getText()),
                            m_objFunc.getId(),
                            Formats.Hora.Unformat(txtEntrada1.getText()),
                            Formats.Hora.Unformat(txtSaida1.getText()),
                            Formats.Hora.Unformat(txtEntrada2.getText()),
                            Formats.Hora.Unformat(txtSaida2.getText()));
                    Dialogs.showInfo(PONTO_EDITADO);
                    m_objFunc = null;
                    defaultLayout(true);
                }
            } else if (btnNovo.getText().equals(BTN_NOVO)) {
                defaultLayout(false);
                txtFuncionario.requestFocus();
            }
        } catch (Exception ex) {
            Dialogs.showError(ex.getMessage());
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void txtEntrada1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntrada1KeyTyped
        // TODO add your handling code here:
        Forms.OnlyNumbers(evt);
    }//GEN-LAST:event_txtEntrada1KeyTyped

    private void txtEntrada1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntrada1KeyPressed
        // TODO add your handling code here:
        Forms.goNextField(evt.getKeyCode(), txtSaida1);
    }//GEN-LAST:event_txtEntrada1KeyPressed

    private void txtEntrada2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntrada2KeyTyped
        // TODO add your handling code here:
        Forms.OnlyNumbers(evt);
    }//GEN-LAST:event_txtEntrada2KeyTyped

    private void txtEntrada2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntrada2KeyPressed
        // TODO add your handling code here:
        Forms.goNextField(evt.getKeyCode(), txtSaida2);
    }//GEN-LAST:event_txtEntrada2KeyPressed

    private void txtSaida1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaida1KeyTyped
        // TODO add your handling code here:
        Forms.OnlyNumbers(evt);
    }//GEN-LAST:event_txtSaida1KeyTyped

    private void txtSaida1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaida1KeyPressed
        // TODO add your handling code here:
        Forms.goNextField(evt.getKeyCode(), txtEntrada2);
    }//GEN-LAST:event_txtSaida1KeyPressed

    private void txtSaida2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaida2KeyTyped
        // TODO add your handling code here:
        Forms.OnlyNumbers(evt);
    }//GEN-LAST:event_txtSaida2KeyTyped

    private void txtSaida2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaida2KeyPressed
        // TODO add your handling code here:
        Forms.goNextField(evt.getKeyCode(), btnNovo);
    }//GEN-LAST:event_txtSaida2KeyPressed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        // TODO add your handling code here:

        if (Dialogs.showConfirm(DELETAR_PNT) == 0) {
            if (m_objPonto != null) {
                try {
                    m_PontoC.Delete(m_objPonto.getId(), m_objPonto.getData(), m_objPonto.getId_funcionario());
                    defaultLayout(true);
                } catch (Exception ex) {
                    Dialogs.showError(ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void tbPontoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPontoMouseClicked
        // TODO add your handling code here:
        try {
            m_objPonto = m_PontoC.getByID(((Integer) tbPonto.getModel().getValueAt(tbPonto.getSelectedRow(), 0)));
            if (m_objPonto != null) {
                defaultLayout(false);
                m_objFunc = new FuncionarioController().getByID(m_objPonto.getId_funcionario());
                txtFuncionario.setText(m_objFunc.getNome());
                txtData.setText(Formats.Data.Format(m_objPonto.getData()));

                txtEntrada1.setText(Formats.Hora.Format(m_objPonto.getEntrada_a()));
                txtSaida1.setText(Formats.Hora.Format(m_objPonto.getSaida_a()));
                txtEntrada2.setText(Formats.Hora.Format(m_objPonto.getEntrada_b()));
                txtSaida2.setText(Formats.Hora.Format(m_objPonto.getSaida_b()));

                txtData.selectAll();
                txtData.requestFocus();
                btnDeletar.setEnabled(true);

            }
        } catch (Exception e) {
            Dialogs.showError(e.getMessage());
        }

    }//GEN-LAST:event_tbPontoMouseClicked

    private void txtDataFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataFocusLost
        // TODO add your handling code here:
        try {
            if (txtData.getText().replaceAll("/", "").length() == 8) {
                java.sql.Date dt = Formats.Data.Unformat(txtData.getText());
                lblPerc.setVisible(true);
                lblEA.setText(lblEA.getText().replaceAll("\*", "") + "*");
                lblEA.setForeground(Color.red);
                lblSA.setText(lblSA.getText().replaceAll("\*", "") + "*");
                lblSA.setForeground(Color.red);

                if (Checks.Date.isWeekDay(dt)) {
                    lblPerc.setText(wk_day);
                    lblEB.setText(lblEB.getText().replaceAll("\*", "") + "*");
                    lblEB.setForeground(Color.red);
                    lblSB.setText(lblSB.getText().replaceAll("\*", "") + "*");
                    lblSB.setForeground(Color.red);

                } else if (Checks.Date.isSaturday(dt)) {
                    lblPerc.setText(st_day);
                    lblEB.setForeground(Color.blue);
                    lblSB.setForeground(Color.blue);
                } else if (Checks.Date.isSunday(dt)) {
                    lblPerc.setText(sn_day);
                    lblEB.setForeground(Color.blue);
                    lblSB.setForeground(Color.blue);

                }

            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_txtDataFocusLost

    private void txtSaida1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaida1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaida1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblEA;
    private javax.swing.JLabel lblEB;
    private javax.swing.JLabel lblFunc;
    private javax.swing.JLabel lblPerc;
    private javax.swing.JLabel lblSA;
    private javax.swing.JLabel lblSB;
    private javax.swing.JTable tbPonto;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JFormattedTextField txtEntrada1;
    private javax.swing.JFormattedTextField txtEntrada2;
    private javax.swing.JTextField txtFuncionario;
    private javax.swing.JFormattedTextField txtSaida1;
    private javax.swing.JFormattedTextField txtSaida2;
    // End of variables declaration//GEN-END:variables
}
