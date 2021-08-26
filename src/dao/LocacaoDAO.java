/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.IDAOT;
import apoio.Validacao;
import entidade.Locacao;
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
public class LocacaoDAO implements IDAOT<Locacao> {

    ResultSet resultadoQ = null;
    Formatacao fmt = new Formatacao();

    @Override
    public String salvar(Locacao o) {
        try {

            //System.out.println("Dao=" + o.getTipoid());
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            System.out.println("data -" + o.getDataFim() + "-");
            String sql = "";

            if (o.getId() == 0) {
                sql = "INSERT into locacao VALUES ( "
                        + "default,"
                        + "'" + o.getDataInicio() + "',";
                if (o.getDataFim() == "") {
                    sql += "null, ";
                } else {
                    sql += "'" + o.getDataFim() + "',";
                }
                sql += "'" + o.getDescricao() + "',"
                        + "'" + o.getEndereco() + "',"
                        + "'" + o.getPessoaId() + "',"
                        + "'" + o.getProfissional() + "'"
                        + ")";
            } else {
                sql = "UPDATE locacao "
                        + "SET data_inicio = '" + o.getDataInicio() + "', ";
                      //  + "data_fim = '" + o.getDataFim() + "', "
                if (o.getDataFim() == "") {
                    sql += " data_fim = null ";
                } else {
                    sql += " data_fim = '" + o.getDataFim() + "'";
                }
                sql += ", descricao = '" + o.getDescricao() + "', "
                        + "endereço = '" + o.getEndereco() + "', "
                        + "pessoa_id = '" + o.getPessoaId() + "', "
                        + "profissional_id = '" + o.getProfissional() + "' "
                        + "WHERE id = " + o.getId();
            }

            sql += " RETURNING id";
            System.out.println("SQL: " + sql);

            ResultSet result = st.executeQuery(sql);
            result.next();
            System.out.println(result.getString("id"));
            int resultado = Integer.parseInt(result.getString("id"));
            if (resultado == 0) {
                return "Erro ao inserir";
            } else {
                return resultado + "";
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Locacao o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE FROM locacao "
                    + "WHERE id = " + id;

            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);
            if (resultado == 0) {
                return "Erro ao excluir!";
            } else {
                return "Locação excluida!";
            }

        } catch (Exception e) {
            System.out.println("Erro ao excluir equipamento: " + e);
            return e.toString();
        }
    }

    @Override
    public ArrayList<Locacao> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Locacao> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Locacao consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void popularTabela(JTable tabela, String nome, String datainicio, String datafim) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[9];
        cabecalho[0] = "Id";
        cabecalho[1] = "Cliente";
        cabecalho[2] = "Id Cliente";
        cabecalho[3] = "Descrição";
        cabecalho[4] = "Endereço";
        cabecalho[5] = "Data Inicio";
        cabecalho[6] = "Data Fim";
        cabecalho[7] = "Id Profissional";
        cabecalho[8] = "Nome Profissional";
        // cria matriz de acordo com nº de registros da tabela
        try {
            String sql = ""
                    + "SELECT count(*) "
                    + "FROM locacao l, pessoa p "
                    + "WHERE l.pessoa_id = p.id";

            if (nome != "") {
                sql += " AND p.nome ILIKE '%" + nome + "%'";
            }
            System.out.println("datainicio = " + datainicio + "datafim = " + datafim);
            System.out.println(sql);// testar se a string tem digitos
            if (!datainicio.isEmpty() && !datafim.isEmpty() && datainicio.matches(".*\\d.*") && datafim.matches(".*\\d.*")) {
                System.out.println("Entrei no if");
                sql += " AND l.data_inicio BETWEEN '" + datainicio + "' AND '" + datafim + "'";
            } else {
                if (!datainicio.isEmpty() && datainicio.matches(".*\\d.*")) {
                    sql += " AND l.data_inicio >= '" + datainicio + "'";
                }

                if (!datafim.isEmpty() && datafim.matches(".*\\d.*")) {
                    sql += " AND l.datainicio <= '" + datafim + "'";
                }
            }

// AND p.nome ILIKE '%" + nome + "%'"
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][9];

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            String sql = ""
                    + "SELECT l.id AS Id,(select p.nome from pessoa p WHERE p.id = l.pessoa_id) AS Cliente, p.id AS ClienteId, "
                    + " l.data_inicio AS DataInicio, l.data_fim AS DataFim, l.descricao AS Descrição, "
                    + "l.endereço AS Endereço, l.profissional_id AS Profissionalid, (select p.nome from pessoa p WHERE p.id = l.profissional_id) AS Profissionalnome "
                    + "FROM locacao l, pessoa p "
                    + "WHERE l.pessoa_id = p.id "; // AND p.nome ILIKE '%" + criterio + "%'");

            if (nome != "") {
                sql += " AND p.nome ILIKE '%" + nome + "%'";
            }
            System.out.println("datainicio = " + datainicio + "datafim = " + datafim);
            System.out.println(sql);// testar se a string tem digitos
            if (!datainicio.isEmpty() && !datafim.isEmpty() && datainicio.matches(".*\\d.*") && datafim.matches(".*\\d.*")) {
                System.out.println("Entrei no if");
                sql += " AND l.data_inicio BETWEEN '" + datainicio + "' AND '" + datafim + "'";
            } else {
                if (!datainicio.isEmpty() && datainicio.matches(".*\\d.*")) {
                    sql += " AND l.data_inicio >= '" + datainicio + "'";
                }

                if (!datafim.isEmpty() && datafim.matches(".*\\d.*")) {
                    sql += " AND l.datainicio <= '" + datafim + "'";
                }
            }
            System.out.println(sql);
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("Cliente");
                dadosTabela[lin][2] = resultadoQ.getString("clienteid");
                dadosTabela[lin][3] = resultadoQ.getString("descrição");
                dadosTabela[lin][4] = resultadoQ.getString("endereço");
                dadosTabela[lin][5] = fmt.ajustaDataDMA(resultadoQ.getString("datainicio"));
                dadosTabela[lin][6] = fmt.ajustaDataDMA(resultadoQ.getString("datafim"));
                dadosTabela[lin][7] = resultadoQ.getString("profissionalid");
                dadosTabela[lin][8] = resultadoQ.getString("profissionalnome");

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
                    column.setPreferredWidth(25);
                    column.setMaxWidth(25);
                    break;
                case 1:
                    column.setPreferredWidth(200);
                    column.setMaxWidth(200);
                    break;
                case 2:
                    column.setPreferredWidth(45);
                    column.setMaxWidth(45);
                    break;
                case 3:
                    column.setPreferredWidth(175);
                    column.setMaxWidth(175);
                    break;

                case 4:
                    column.setPreferredWidth(175);
                    column.setMaxWidth(175);
                    break;

                case 5:
                    column.setPreferredWidth(80);
                    column.setMaxWidth(80);
                    break;

                case 6:
                    column.setPreferredWidth(80);
                    column.setMaxWidth(80);
                    break;

                case 7:
                    column.setPreferredWidth(45);
                    column.setMaxWidth(45);
                    break;

            }
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

    public int ultimoIDInserido() {
        try {
            String sql = ""
                    + " SELECT max(id)"
                    + " FROM locacao";

            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            resultadoQ.next();

            return resultadoQ.getInt(1);
        } catch (Exception e) {
        }
        return 0;

    }

}
