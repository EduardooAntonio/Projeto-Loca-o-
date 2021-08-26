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
public class Equipamento {
    private int id;
    private String descricao;
    private int valor;
    private int tipoid;

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
    
    public int getValor() {
        return valor;
    }
    
   public void setValor(int valor) {
       this.valor = valor;
   }
   
   public int getTipoid() {
       return tipoid;
   }
   
   public void setTipoid(int tipo_id){
       this.tipoid = tipo_id;
   }
   
   
}