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
public class Financeiro {
    private int id;
    private String data_vencimento;
    private double valor_prestacao;
    private String data_pagamento;
    private double valor_pago;
    private String situacao;
    private int locacao_id;
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
        public String getDataVencimento() {
        return data_vencimento;
    }

    public void setDataVencimento(String data_vencimento) {
        this.data_vencimento = data_vencimento;
    }
     
    
     public double getValorPrestacao() {
        return valor_prestacao;
    }

    public void setValorPrestacao(double valor_prestacao) {
        this.valor_prestacao = valor_prestacao;
    }
    
    public String getDataPagamento() {
        return data_pagamento;
    }

    public void setDataPagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }    
    

     public double getValorPago() {
        return valor_pago;
    }

    public void setValorPago(double valor_pago) {
        this.valor_pago = valor_pago;
    }


    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }


     public int getLocacaoId() {
        return locacao_id;
    }

    public void setLocacaoid(int locacao_id) {
        this.locacao_id = locacao_id;
    }
}