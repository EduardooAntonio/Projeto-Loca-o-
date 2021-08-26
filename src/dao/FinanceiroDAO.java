/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.Formatacao;
import apoio.IDAOT;
import entidade.Financeiro;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Eduardo
 */
public class FinanceiroDAO implements IDAOT<Financeiro> {

    ResultSet ResultadoQ = null;
    Formatacao fmt = new Formatacao();

    @Override
    public String salvar(Financeiro o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO financeiro VALUES "
                    + "(DEFAULT, "
                    + "'" + o.getDataVencimento() + "',"
                    + "'" + o.getValorPrestacao() + "',"
                    +  o.getDataPagamento() + ","
                    + "'" + o.getValorPago() + "',"
                    + "'" + o.getSituacao() + "',"
                    + "'" + o.getLocacaoId() + "'"
                    + ")";

            System.out.println("Sql: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado == 0) {
                return "Erro ao inserir Financeiro";
            } else {
                return "Financeiro gerado com sucesso!";
            }

        } catch (PSQLException e) {
            return "Erro!" + e;

        } catch (Exception e) {
            System.out.println("Erro salvar dados = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Financeiro o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "UPDATE financeiro "
                    + "SET data_pagamento = '" + o.getDataPagamento() + "', "
                    + "valor_pago = '" + o.getValorPago()+ "', "
                    + "situacao = 'P' "
                    + "WHERE id = " + o.getId();

            System.out.println("Sql: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado == 0) {
                return "Erro ao atualizar!";
            } else {
                return "Usuário atualizado!";
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar as informações do usuário. \nContate um administrador do sistema informando a seguinte mensagem = " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Financeiro> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Financeiro> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Financeiro consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int retornaNumeroDeParcelas(int id){
       try {
        String sql = ""
                    + "SELECT COUNT(*) "
                    + " FROM financeiro f, locacao l "
                    + " WHERE f.locacao_id = l.id "
                    + " AND f.situacao = 'P' AND f.locacao_id = " + id +"";
        
        ResultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
        
        ResultadoQ.next();
        
           System.out.println("resultado" + ResultadoQ.getInt(1));
        
         return ResultadoQ.getInt(1);
         
        
       } catch (Exception e) {
            System.out.println("Erro");
        }
       return 0;
    }
    
     public int excluiParcelas(int id){
       try {
        String sql = ""
                    + " DELETE "
                    + " FROM financeiro f "
                    + " WHERE f.locacao_id = " + id +"";
        
           System.out.println("id =" + id);
        
        ResultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
        
        ResultadoQ.next();
        
           System.out.println("resultado" + ResultadoQ.getInt(1));
        
         return ResultadoQ.getInt(1);
         
        
       } catch (Exception e) {
            System.out.println("Erro");
        }
       return 0;
    }
    
       
    
    
    
    

    public void popularTabela(JTable tabela, String clientefinanceiro, String datainicio, String datafim, String situacao) {
// dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[8];
        cabecalho[0] = "Id";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Id Cliente";
        cabecalho[3] = "Valor Prestação";
        cabecalho[4] = "Data Vencimento";
        cabecalho[5] = "Data Pagamento";
        cabecalho[6] = "Valor Pago";
        cabecalho[7] = "Situação";

        // cria matriz de acordo com nº de registros da tabela
        try {
            String sql = ""
                    + "SELECT COUNT(*) "
                    + "FROM financeiro f, locacao l, pessoa p "
                    + "WHERE f.locacao_id = l.id AND l.pessoa_id = p.id "
                    + "AND f.situacao = '" + situacao + "'";
            

            if (clientefinanceiro != "") {
                sql += " AND p.nome ILIKE '%" + clientefinanceiro + "%'";
            }

            System.out.println(sql);// testar se a string tem digitos

           
            if (!datainicio.isEmpty() && !datafim.isEmpty() && datainicio.matches(".*\\d.*") && datafim.matches(".*\\d.*")) {
                sql += " AND f.data_vencimento BETWEEN '" + datainicio + "' AND '" + datafim + "'";
            } else {
                if (!datainicio.isEmpty() && datainicio.matches(".*\\d.*")) {
                    sql += " AND f.data_vencimento >= '" + datainicio + "'";
                }

                if (!datafim.isEmpty() && datafim.matches(".*\\d.*")) {
                    sql += " AND f.data_vencimento <= '" + datafim + "'";
                }
            } 
            
           ResultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            ResultadoQ.next();

            dadosTabela = new Object[ResultadoQ.getInt(1)][8];

        } catch (Exception e) {
            System.out.println("Erro ao consultar tabela: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            String sql = ""
                    + "SELECT f.id AS Id, p.nome AS Cliente, p.id AS IdCliente, f.valor_prestacao AS ValorPrestação, f.data_vencimento AS DataVencimento, "
                    + "f.data_pagamento AS DataPagamento, f.valor_pago AS ValorPago, f.situacao AS Situação "
                    + "FROM financeiro f, locacao l, pessoa p "
                    + "WHERE f.locacao_id = l.id AND l.pessoa_id = p.id "
                    + "AND f.situacao = '" + situacao + "'";

            if (clientefinanceiro != "") {
                sql += " AND p.nome ILIKE '%" + clientefinanceiro + "%'";
            }
            
            System.out.println(sql);// testar se a string tem digitos
            if (!datainicio.isEmpty() && !datafim.isEmpty() && datainicio.matches(".*\\d.*") && datafim.matches(".*\\d.*")) {
                System.out.println("Entrei no if");
                sql += " AND f.data_vencimento BETWEEN '" + datainicio + "' AND '" + datafim + "'";
            } else {
                if (!datainicio.isEmpty() && datainicio.matches(".*\\d.*")) {
                    sql += " AND f.data_vencimento >= '" + datainicio + "'";
                }

                if (!datafim.isEmpty() && datafim.matches(".*\\d.*")) {
                    sql += " AND f.data_vencimento <= '" + datafim + "'";
                }

                sql += " AND f.situacao = '" + situacao + "'";
            }
            
            ResultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            
            while (ResultadoQ.next()) {
                dadosTabela[lin][0] = ResultadoQ.getInt("Id");
                dadosTabela[lin][1] = ResultadoQ.getString("Cliente");
                dadosTabela[lin][2] = ResultadoQ.getString("IdCliente");
                dadosTabela[lin][3] = ResultadoQ.getString("ValorPrestação");
                dadosTabela[lin][4] = fmt.ajustaDataDMA(ResultadoQ.getString("DataVencimento"));
                dadosTabela[lin][5] = fmt.ajustaDataDMA(ResultadoQ.getString("DataPagamento"));
                dadosTabela[lin][6] = ResultadoQ.getString("ValorPago");
                dadosTabela[lin][7] = ResultadoQ.getString("Situação");

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

        // configuracoes adicionais no componente tabela
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
                    column.setPreferredWidth(140);
                    column.setMaxWidth(140);
                    break;

            }
        }
    }

}
