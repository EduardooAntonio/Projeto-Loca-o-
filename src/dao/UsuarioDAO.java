/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.ConexaoBD;
import apoio.IDAOT;
import apoio.IDAOTuser;
import entidade.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Eduardo
 */
public class UsuarioDAO implements IDAOTuser<Usuario> {

    ResultSet ResultadoQ = null;

    @Override
    public String salvar(Usuario o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO pessoa VALUES "
                    + "(DEFAULT, ";
            if (o.getUsuario() != null) {
                sql += "'" + o.getUsuario() + "', ";
            } else {
                sql += "'" + o.getUsuario() + "',";
            }
            if (o.getSenha() != null) {
                sql += "'" + o.getSenha() + "', ";
            } else {
                sql += "'" + o.getSenha() + "',";
            }
            sql += "'" + o.getEndereco() + "',"
                    + "'" + o.getEmail() + "',"
                    + "'" + o.getTelefone() + "',"
                    + "'" + o.getNome() + "',"
                    + "'" + o.getClasse() + "',"
                    + "'A'"
                    + ")";

            System.out.println("Sql: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado == 0) {
                return "Erro ao inserir";
            } else {
                return "Pessoa cadastrado com sucesso!";
            }

        } catch (PSQLException e) {
            return "Erro! O Usuario Já esta cadastrado!";

        } catch (Exception e) {
            System.out.println("Erro salvar dados = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Usuario o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            System.out.println("usuario =" + o.getUsuario());
            String sql = ""
                    + "UPDATE pessoa SET ";
            if (o.getUsuario() != null) {
                sql += "usuario = '" + o.getUsuario() + "', ";
            }
            if (o.getSenha() != null) {
                sql += "senha = '" + o.getSenha() + "', ";
            }
            sql += "endereco = '" + o.getEndereco() + "', "
                    + "email = '" + o.getEmail() + "', "
                    + "telefone = '" + o.getTelefone() + "', "
                    + "nome = '" + o.getNome() + "', "
                    + "Classe = '" + o.getClasse() + "', "
                    + "situacao = 'A' "
                    + "WHERE id = " + o.getCod();

            System.out.println("Sql: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado == 0) {
                return "Erro ao atualizar!";
            } else {
                return "Pessoa atualizada!";
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar as informações do usuário. \nContate um administrador do sistema informando a seguinte mensagem = " + e);
            e.printStackTrace();
            return e.toString();
        }
    }

    public String excluir(int id, char situacao) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "UPDATE pessoa "
                    + "SET situacao ='" + situacao + "' "
                    + "WHERE id = " + id;

            System.out.println("Sql: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado == 0) {
                return "Erro ao atualizar!";
            } else {
                return "Usuário atualizado com sucesso!";
            }

        } catch (Exception e) {
            System.out.println("Erro inativar usuario = " + e);
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public ArrayList<Usuario> consultarTodos() {
        ArrayList array = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "SELECT * "
                    + "FROM pessoa";

            ResultadoQ = st.executeQuery(sql);

            while (ResultadoQ.next()) {
                array.add(ResultadoQ);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return array;
    }

    public Usuario consultarUsuario(String usuario) {
        Usuario user = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "SELECT * "
                    + "FROM pessoa "
                    + "WHERE usuario = '" + usuario + "'";

            System.out.println("Sql: " + sql);

            ResultadoQ = st.executeQuery(sql);

            while (ResultadoQ.next()) {
                user = new Usuario();
                user.setCod(ResultadoQ.getInt("id"));
                user.setNome(ResultadoQ.getString("usuario"));
                user.setSenha(ResultadoQ.getString("senha"));
                System.out.println(user.getSenha());
                user.setSituacao(ResultadoQ.getString("situacao").charAt(0));
            }

        } catch (Exception e) {
            System.out.println("Erro consultar usuario = " + e);
        }

        return user;
    }

    @Override
    public ArrayList<Usuario> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario consultarId(int codusuario) {
        Usuario user = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "SELECT * "
                    + "FROM pessoa "
                    + "WHERE id = " + codusuario;

            System.out.println("Sql: " + sql);

            ResultadoQ = st.executeQuery(sql);

            while (ResultadoQ.next()) {
                user = new Usuario();
                user.setCod(codusuario);
                user.setNome(ResultadoQ.getString("nome"));
                user.setEndereco(ResultadoQ.getString("endereco"));
                user.setEmail(ResultadoQ.getString("email"));
                user.setTelefone(ResultadoQ.getString("telefone"));
                user.setUsuario(ResultadoQ.getString("usuario"));
                user.setSenha(ResultadoQ.getString("senha"));
                user.setClasse(ResultadoQ.getString("classe").charAt(0));
                user.setSituacao(ResultadoQ.getString("situacao").charAt(0));
            }

        } catch (Exception e) {
            System.out.println("Erro consultar usuario = " + e);
        }

        return user;
    }

    public void popularTabela(JTable tabela, String criterio, String classe) {
// dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[7];
        cabecalho[0] = "Id";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Endereço";
        cabecalho[3] = "E-mail";
        cabecalho[4] = "Telefone";
        cabecalho[5] = "Classe";
        cabecalho[6] = "Situação";

        // cria matriz de acordo com nº de registros da tabela
        try {
            ResultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM pessoa WHERE nome ILIKE '%" + criterio + "%' "
                    + classe);

            ResultadoQ.next();

            dadosTabela = new Object[ResultadoQ.getInt(1)][7];

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuario: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            ResultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM pessoa "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%' "
                    + classe
                    + " ORDER BY situacao");

            while (ResultadoQ.next()) {

                dadosTabela[lin][0] = ResultadoQ.getInt("id");
                dadosTabela[lin][1] = ResultadoQ.getString("nome");
                dadosTabela[lin][2] = ResultadoQ.getString("endereco");
                dadosTabela[lin][3] = ResultadoQ.getString("email");
                dadosTabela[lin][4] = ResultadoQ.getString("telefone");
                dadosTabela[lin][5] = ResultadoQ.getString("classe");
                dadosTabela[lin][6] = ResultadoQ.getString("situacao");

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
                    column.setPreferredWidth(185);
                    column.setMaxWidth(185);
                    break;
                case 2:
                    column.setPreferredWidth(150);
                    column.setMaxWidth(150);
                    break;
                case 3:
                    column.setPreferredWidth(150);
                    column.setMaxWidth(150);
                    break;

                case 4:
                    column.setPreferredWidth(100);
                    column.setMaxWidth(100);
                    break;
                case 5:
                    column.setPreferredWidth(50);
                    column.setMaxWidth(50);
                    break;
                case 6:
                    column.setPreferredWidth(50);
                    column.setMaxWidth(50);
                    break;

            }
        }
    }

}
