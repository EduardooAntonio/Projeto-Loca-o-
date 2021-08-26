/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidade.LocacaoEquipamento;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Eduardo
 */
public class LocacaoEquipamentoDAO implements IDAOT<LocacaoEquipamento> {

    ResultSet resultadoQ = null;

    @Override
    public String salvar(LocacaoEquipamento o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "";

            if (o.getId() == 0) {
                sql = "INSERT into locacao_equipamento VALUES ( "
                        + "default,"
                        + "'" + o.getEquipamentoId() + "',"
                        + "'" + o.getLocacaoid() + "',"
                        + "'" + o.getValorLocacao() + "',"
                        + "'" + o.getQuantidade() + "'"
                        + ")";
            } else {
                sql = "UPDATE locacao_equipamento "
                        + "SET equipamento_id = '" + o.getEquipamentoId() + "' "
                        + "locacao_id = '" + o.getLocacaoid() + "', "
                        + "quantidade = '" + o.getValorLocacao() + "', "
                        + "valor_locacao = '" + o.getQuantidade() + "', "
                        + "WHERE id = " + o.getId();
            }

            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);
            if (resultado == 0) {
                return "Erro ao inserir";
            } else {
                return "Locação cadastrada!";
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(LocacaoEquipamento o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE FROM locacao_equipamento "
                    + "WHERE id = " + id;

            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);
            if (resultado == 0) {
                return "Erro ao excluir!";
            } else {
                return "Equipamento excluido!";
            }

        } catch (Exception e) {
            System.out.println("Erro ao excluir equipamento: " + e);
            return e.toString();
        }
    }

    @Override
    public ArrayList<LocacaoEquipamento> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<LocacaoEquipamento> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocacaoEquipamento consultarId(int id) {
        LocacaoEquipamento idz = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "SELECT id "
                    + "FROM locacao_equipamento "
                    + "WHERE id = " + id;

            System.out.println("Sql: " + sql);

            resultadoQ = st.executeQuery(sql);

        } catch (Exception e) {
            System.out.println("Erro consultar usuario = " + e);
        }

        return idz;
    }

    public String excluirEquipamento(LocacaoEquipamento o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE FROM locacao_equipamento "
                    + "WHERE id = " + o.getId();

            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);
            if (resultado == 0) {
                return "Erro ao excluir!";
            } else {
                return "Equipamento excluido!";
            }

        } catch (Exception e) {
            System.out.println("Erro ao excluir equipamento: " + e);
            return e.toString();
        }
    }

    public void popularTabela(JTable tabela, String id) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[5];
        cabecalho[0] = "Id";
        cabecalho[1] = "Id Equipamento";
        cabecalho[2] = "Descrição";
        cabecalho[3] = "Quantidade";
        cabecalho[4] = "Valor";

        // cria matriz de acordo com nº de registros da tabela
        try {
            String sql = ""
                    + "SELECT count(*) "
                    + "FROM locacao_equipamento le, equipamento e "
                    + "WHERE le.equipamento_id = e.id AND le.locacao_id = " + id + "";

            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][5];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            String sql = ""
                    + "SELECT le.id AS IdLocacaoEquipamento, le.equipamento_id AS IdEquipamento, e.descricao AS Descricao,  "
                    + "(e.valor * le.quantidade) AS Valor, le.quantidade AS quantidade  "
                    + "FROM locacao_equipamento le, equipamento e "
                    + "WHERE le.equipamento_id = e.id  AND le.locacao_id = " + id + "";

            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("IdLocacaoEquipamento");
                dadosTabela[lin][1] = resultadoQ.getInt("IdEquipamento");
                dadosTabela[lin][2] = resultadoQ.getString("descricao");
                dadosTabela[lin][3] = resultadoQ.getString("quantidade");
                dadosTabela[lin][4] = resultadoQ.getString("valor");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }

        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(20);
                    break;
                case 1:
                    column.setPreferredWidth(20);

                    break;
                case 2:
                    column.setPreferredWidth(150);

                    break;
                case 3:
                    column.setPreferredWidth(20);
                    break;
                case 4:
                    column.setPreferredWidth(50);

            }
        }
        // renderizacao das linhas da tabela = mudar a cor
//        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value,
//                    boolean isSelected, boolean hasFocus, int row, int column) {
//                super.getTableCellRendererComponent(table, value, isSelected,
//                        hasFocus, row, column);
//                if (row % 2 == 0) {
//                    setBackground(Color.GREEN);
//                } else {
//                    setBackground(Color.LIGHT_GRAY);
//                }
//                return this;
//            }
//        });
    }

    public int ultimoIDInserido() {
        try {
            String sql = ""
                    + " SELECT max(id)"
                    + " FROM locacao_equipamento";

            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            resultadoQ.next();

            return resultadoQ.getInt(1);
        } catch (Exception e) {
        }
        return 0;

    }

}
