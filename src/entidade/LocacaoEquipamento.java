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
public class LocacaoEquipamento {

    private int id;
    private int equipamentoid;
    private int locacaoid;
    private double valorlocacao;
    private int quantidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipamentoId() {
        return equipamentoid;
    }

    public void setEquipamentoId(int equipamentoid) {
        this.equipamentoid = equipamentoid;
    }

    public int getLocacaoid() {
        return locacaoid;
    }

    public void setLocacaoId(int locacaoid) {
        this.locacaoid = locacaoid;
    }

    public double getValorLocacao() {
        return valorlocacao;
    }

    public void setValorLocacao(double valorlocacao) {
        this.valorlocacao = valorlocacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
