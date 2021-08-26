/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import apoio.ConexaoBD;
import apoio.Validacao;
import dao.UsuarioDAO;
import entidade.Usuario;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author fabricio.pretto
 */
public class FrmJanelaPrincipal extends javax.swing.JFrame {

    private Usuario u;
    public static Usuario uLogado;

    public Usuario getUsuario() {
        return u;
    }

    public void setUsuario(Usuario u) {
        this.u = u;
    }

    public FrmJanelaPrincipal() {
        initComponents();
        ocultaBarraMenus();
        mostraLogin();
        u = new Usuario();
    }

    private void ocultaBarraMenus() {
        barraDeMenus.setVisible(false);
    }

    private void mostraBarraMenus() {
        barraDeMenus.setVisible(true);
    }

    private void ocultaLogin() {
        lblUsuario.setVisible(false);
        lblSenha.setVisible(false);
        pfdSenha.setVisible(false);
        tfdUsuario.setVisible(false);
        btnEntrar.setVisible(false);
        lblAviso.setVisible(false);
    }

    private void mostraLogin() {
        lblUsuario.setVisible(true);
        lblSenha.setVisible(true);
        pfdSenha.setVisible(true);
        tfdUsuario.setVisible(true);
        btnEntrar.setVisible(true);
        pfdSenha.setText("");
        tfdUsuario.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelPrincipal = new javax.swing.JDesktopPane();
        lblUsuario = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        tfdUsuario = new javax.swing.JTextField();
        pfdSenha = new javax.swing.JPasswordField();
        lblAviso = new javax.swing.JLabel();
        lblJeem = new javax.swing.JLabel();
        btnEntrar = new javax.swing.JButton();
        lblImagemCaminhao = new javax.swing.JLabel();
        lblImgEscavadeira = new javax.swing.JLabel();
        lblFundoAmarelo = new javax.swing.JLabel();
        barraDeMenus = new javax.swing.JMenuBar();
        MenuCadastro = new javax.swing.JMenu();
        jmiTipoEquipamento = new javax.swing.JMenuItem();
        jmiEquipamento = new javax.swing.JMenuItem();
        jmiPessoa = new javax.swing.JMenuItem();
        jMenuRelatorio = new javax.swing.JMenu();
        jmiRelatorioTipodeEquipamento = new javax.swing.JMenuItem();
        jmiRelatorioEquipamento = new javax.swing.JMenuItem();
        jmiLocCliente = new javax.swing.JMenuItem();
        jmiLocacaoPeriodo = new javax.swing.JMenuItem();
        jMenuLocacao = new javax.swing.JMenu();
        jmiLocacao = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jmiFinanceiro = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JEEM Locações");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setPreferredSize(new java.awt.Dimension(1920, 1080));
        setResizable(false);

        lblUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblUsuario.setText("Usuário:");
        painelPrincipal.add(lblUsuario);
        lblUsuario.setBounds(293, 196, 50, 17);

        lblSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSenha.setText("Senha:");
        painelPrincipal.add(lblSenha);
        lblSenha.setBounds(293, 236, 43, 17);

        tfdUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdUsuarioActionPerformed(evt);
            }
        });
        painelPrincipal.add(tfdUsuario);
        tfdUsuario.setBounds(361, 191, 178, 20);
        painelPrincipal.add(pfdSenha);
        pfdSenha.setBounds(361, 231, 178, 20);

        lblAviso.setText("*Digite usuário e senha existente para acessar a aplicação. ");
        painelPrincipal.add(lblAviso);
        lblAviso.setBounds(10, 730, 350, 14);

        lblJeem.setBackground(new java.awt.Color(255, 255, 255));
        lblJeem.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 100)); // NOI18N
        lblJeem.setText("JEEM Locações");
        painelPrincipal.add(lblJeem);
        lblJeem.setBounds(140, 40, 740, 114);

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        painelPrincipal.add(btnEntrar);
        btnEntrar.setBounds(359, 277, 180, 23);

        lblImagemCaminhao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/caminhao3.png"))); // NOI18N
        painelPrincipal.add(lblImagemCaminhao);
        lblImagemCaminhao.setBounds(0, 460, 400, 270);

        lblImgEscavadeira.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Escavadeira.png"))); // NOI18N
        painelPrincipal.add(lblImgEscavadeira);
        lblImgEscavadeira.setBounds(520, 350, 370, 410);

        lblFundoAmarelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFundoAmarelo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/fundo_amarelo.jpg"))); // NOI18N
        lblFundoAmarelo.setAlignmentY(0.0F);
        lblFundoAmarelo.setMinimumSize(new java.awt.Dimension(1024, 768));
        painelPrincipal.add(lblFundoAmarelo);
        lblFundoAmarelo.setBounds(1, -59, 1920, 1140);

        MenuCadastro.setText("Cadastros");
        MenuCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadastroActionPerformed(evt);
            }
        });

        jmiTipoEquipamento.setText("Tipo Equipamento");
        jmiTipoEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTipoEquipamentoActionPerformed(evt);
            }
        });
        MenuCadastro.add(jmiTipoEquipamento);

        jmiEquipamento.setText("Equipamento");
        jmiEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEquipamentoActionPerformed(evt);
            }
        });
        MenuCadastro.add(jmiEquipamento);

        jmiPessoa.setText("Pessoa");
        jmiPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPessoaActionPerformed(evt);
            }
        });
        MenuCadastro.add(jmiPessoa);

        barraDeMenus.add(MenuCadastro);

        jMenuRelatorio.setText("Relatórios");

        jmiRelatorioTipodeEquipamento.setText("Tipo de Equipamento");
        jmiRelatorioTipodeEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRelatorioTipodeEquipamentoActionPerformed(evt);
            }
        });
        jMenuRelatorio.add(jmiRelatorioTipodeEquipamento);

        jmiRelatorioEquipamento.setText("Equipamento");
        jmiRelatorioEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRelatorioEquipamentoActionPerformed(evt);
            }
        });
        jMenuRelatorio.add(jmiRelatorioEquipamento);

        jmiLocCliente.setText("Locações de Cliente");
        jmiLocCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLocClienteActionPerformed(evt);
            }
        });
        jMenuRelatorio.add(jmiLocCliente);

        jmiLocacaoPeriodo.setText("Locação Periodo");
        jmiLocacaoPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLocacaoPeriodoActionPerformed(evt);
            }
        });
        jMenuRelatorio.add(jmiLocacaoPeriodo);

        barraDeMenus.add(jMenuRelatorio);

        jMenuLocacao.setText("Locação");
        jMenuLocacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuLocacaoActionPerformed(evt);
            }
        });

        jmiLocacao.setText("Locação");
        jmiLocacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLocacaoActionPerformed(evt);
            }
        });
        jMenuLocacao.add(jmiLocacao);

        barraDeMenus.add(jMenuLocacao);

        jMenu1.setText("Financeiro");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jmiFinanceiro.setText("Financeiro");
        jmiFinanceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFinanceiroActionPerformed(evt);
            }
        });
        jMenu1.add(jmiFinanceiro);

        barraDeMenus.add(jMenu1);

        setJMenuBar(barraDeMenus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(947, 810));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jmiTipoEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTipoEquipamentoActionPerformed
        IfrTipoequipamento ifrTipoequipamento = new IfrTipoequipamento();
        painelPrincipal.add(ifrTipoequipamento);
        ifrTipoequipamento.setVisible(true);
    }//GEN-LAST:event_jmiTipoEquipamentoActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        try {
            u.setSenha(u.criptografaSenha(pfdSenha.getText()));
            u.setUsuario(tfdUsuario.getText());
            System.out.println(u.getUsuario());
            if (Validacao.validaSenha(u)) {
                UsuarioDAO usuarioLogado = new UsuarioDAO();
                u = usuarioLogado.consultarUsuario(tfdUsuario.getText());
                if (u.getSituacao() == 'A') {
                    ocultaLogin();
                    mostraBarraMenus();
                    FrmJanelaPrincipal.uLogado = this.u;
                    JOptionPane.showMessageDialog(null, "Bem vindo(a) " + tfdUsuario.getText());
                    u = null;
                } else {
                    JOptionPane.showMessageDialog(null, "O usuário que você está tentando usar não está ativo no momento.");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
                u = new Usuario();
                tfdUsuario.requestFocus();
            }

        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
            u = new Usuario();
            tfdUsuario.requestFocus();
            pfdSenha.setText("");
            tfdUsuario.setText("");
        } catch (Exception ex) {
            Logger.getLogger(FrmJanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);

        }

    }//GEN-LAST:event_btnEntrarActionPerformed

    private void tfdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfdUsuarioActionPerformed

    private void jmiEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEquipamentoActionPerformed
        IfrEquipamento ifrEquipamento = new IfrEquipamento();
        painelPrincipal.add(ifrEquipamento);
        ifrEquipamento.setVisible(true);
    }//GEN-LAST:event_jmiEquipamentoActionPerformed

    private void jmiRelatorioTipodeEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRelatorioTipodeEquipamentoActionPerformed
        try {
            // Compila o relatorio
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/list_tipoequipamento.jrxml"));

            // Mapeia campos de parametros para o relatorio, mesmo que nao existam
            Map parametros = new HashMap();

            // Executa relatorio
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, ConexaoBD.getInstance().getConnection());

            // Exibe resultado em video
            JasperViewer.viewReport(impressao, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e);
        }
    }//GEN-LAST:event_jmiRelatorioTipodeEquipamentoActionPerformed

    private void MenuCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadastroActionPerformed

    }//GEN-LAST:event_MenuCadastroActionPerformed

    private void jmiPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPessoaActionPerformed
        IfrUsuario ifrUsuario = new IfrUsuario();
        painelPrincipal.add(ifrUsuario);
        ifrUsuario.setVisible(true);
    }//GEN-LAST:event_jmiPessoaActionPerformed

    private void jmiRelatorioEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRelatorioEquipamentoActionPerformed
        try {
            // Compila o relatorio
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorios/list_equipamento.jrxml"));

            // Mapeia campos de parametros para o relatorio, mesmo que nao existam
            Map parametros = new HashMap();

            // Executa relatorio
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, ConexaoBD.getInstance().getConnection());

            // Exibe resultado em video
            JasperViewer.viewReport(impressao, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e);
        }
    }//GEN-LAST:event_jmiRelatorioEquipamentoActionPerformed

    private void jMenuLocacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuLocacaoActionPerformed

    }//GEN-LAST:event_jMenuLocacaoActionPerformed

    private void jmiLocacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLocacaoActionPerformed
        IfrLocacao ifrLocacao = new IfrLocacao();
        painelPrincipal.add(ifrLocacao);
        ifrLocacao.setVisible(true);
    }//GEN-LAST:event_jmiLocacaoActionPerformed

    private void jmiLocClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLocClienteActionPerformed
        DlgRelatorioClienteLocacao dlgRelatorioClienteLocacao = new DlgRelatorioClienteLocacao(this, true);
        dlgRelatorioClienteLocacao.setVisible(true);
    }//GEN-LAST:event_jmiLocClienteActionPerformed

    private void jmiLocacaoPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLocacaoPeriodoActionPerformed
        DlgRelatorioLocacao dlgRelatorioLocacao = new DlgRelatorioLocacao(this, true);
        dlgRelatorioLocacao.setVisible(true);
    }//GEN-LAST:event_jmiLocacaoPeriodoActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jmiFinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFinanceiroActionPerformed
        IfrFinanceiro ifrFinanceiro = new IfrFinanceiro();
        painelPrincipal.add(ifrFinanceiro);
        ifrFinanceiro.setVisible(true);
    }//GEN-LAST:event_jmiFinanceiroActionPerformed

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
            java.util.logging.Logger.getLogger(FrmJanelaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmJanelaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmJanelaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmJanelaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmJanelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuCadastro;
    private javax.swing.JMenuBar barraDeMenus;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenuLocacao;
    private javax.swing.JMenu jMenuRelatorio;
    private javax.swing.JMenuItem jmiEquipamento;
    private javax.swing.JMenuItem jmiFinanceiro;
    private javax.swing.JMenuItem jmiLocCliente;
    private javax.swing.JMenuItem jmiLocacao;
    private javax.swing.JMenuItem jmiLocacaoPeriodo;
    private javax.swing.JMenuItem jmiPessoa;
    private javax.swing.JMenuItem jmiRelatorioEquipamento;
    private javax.swing.JMenuItem jmiRelatorioTipodeEquipamento;
    private javax.swing.JMenuItem jmiTipoEquipamento;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JLabel lblFundoAmarelo;
    private javax.swing.JLabel lblImagemCaminhao;
    private javax.swing.JLabel lblImgEscavadeira;
    private javax.swing.JLabel lblJeem;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JDesktopPane painelPrincipal;
    private javax.swing.JPasswordField pfdSenha;
    private javax.swing.JTextField tfdUsuario;
    // End of variables declaration//GEN-END:variables
}