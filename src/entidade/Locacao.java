/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author Eduardo
 */
public class Locacao {

    private int id;
    private String descricao;
    private String datainicio;
    private String datafim;
    private String endereco;
    private String pessoaid;
    private String profissional;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return datainicio;
    }

    public void setDataInicio(String datainicio) {
        this.datainicio = datainicio;
    }

    public String getDataFim() {
        return datafim;
    }

    public void setDataFim(String datafim) {
        this.datafim = datafim;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPessoaId() {
        return pessoaid;
    }

    public void setPessoaId(String pessoaid) {
        this.pessoaid = pessoaid;
    }

    public void setProfissional(String profissional) {
        this.profissional = profissional;
    }

    public String getProfissional() {
        return profissional;
    }
}
