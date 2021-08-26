/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import entidade.Equipamento;
import entidade.Tipoequipamento;
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
public class EquipamentoDAO implements IDAOT <Equipamento> {
    
    ResultSet resultadoQ = null;

    @Override
    public String salvar(Equipamento o) {
         try {
             
            //System.out.println("Dao=" + o.getTipoid());
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            
            String sql = "";
            
            if (o.getId() == 0) {
                sql = "INSERT into equipamento VALUES ( "
                        + "default,"
                        + "'" + o.getDescricao() + "',"
                        + "'" + o.getValor() + "',"
                        + "'" + o.getTipoid() + "'"
                        + ")";
            } else {
                sql = "UPDATE equipamento "
                        + "SET descricao = '" + o.getDescricao() + "', "
                        + "valor = '" + o.getValor() + "', "
                        + "tipo_id = '" + o.getTipoid() + "' "
                        + "WHERE id = " + o.getId();
            }
            
            System.out.println("SQL: " + sql);
            
            int resultado = st.executeUpdate(sql);
            if (resultado == 0) {
                return "Erro ao inserir";
            } else {
                return "Equipamento adicionado/atualizado com sucesso!";
            }
            
        
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e);
            return e.toString();
        }
    }
    

    @Override
    public String atualizar(Equipamento o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE FROM equipamento "
                    + "WHERE id = " + id;

            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);
            if (resultado == 0) {
                return "Erro ao atualizar";
            } else {
                return "Equipamento atualizado";
            }
            

        } catch (Exception e) {
            System.out.println("Erro ao excluir equipamento: " + e);
            return e.toString();
        }
        
    }

    @Override
    public ArrayList<Equipamento> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equipamento> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Equipamento consultarId(int id) {
             Equipamento equipamento = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM equipamento "
                    + "WHERE id = " + id;

            System.out.println("SQL: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                equipamento = new Equipamento();

                equipamento.setId(resultadoQ.getInt("id"));
                equipamento.setDescricao(resultadoQ.getString("descricao"));
                equipamento.setValor(resultadoQ.getInt("valor"));
                equipamento.setTipoid(resultadoQ.getInt("tipo_id"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar apresentação: " + e);
        }

        return equipamento;
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Código";
        cabecalho[1] = "Descrição";
        cabecalho[2] = "Valor";
        cabecalho[3] = "Tipo Equipamento";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) "
                    + "FROM equipamento "
                    + "WHERE descricao ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][4];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT e.id AS id, e.descricao AS descricao, e.valor AS valor , te.descricao AS tipo "
                    + "FROM equipamento e, tipo_equipamento te "
                    + "WHERE e.tipo_id = te.id AND e.descricao ILIKE '%" + criterio + "%'");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("descricao");
                dadosTabela[lin][2] = resultadoQ.getInt("valor");
                dadosTabela[lin][3] = resultadoQ.getString("tipo");

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
                    column.setPreferredWidth(1);
                    break;
                case 1:
                    column.setPreferredWidth(150);
                    break;
                case 2:
                    column.setPreferredWidth(50);
                    break;
                case 3:
                    column.setPreferredWidth(15);
                    break;
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
    
}
