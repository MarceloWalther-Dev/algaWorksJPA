package com.algaworks.ecommerce.listener;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.service.NotaFiscalService;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class GerarNotaFiscalListener {

    private NotaFiscalService service;

    public GerarNotaFiscalListener() {
        this.service = new NotaFiscalService();
    }

    @PrePersist
    @PreUpdate
    public void gerar(Pedido pedido){
        if(pedido.isPago() && pedido.getNotaFiscal() == null){
            service.gerar(pedido);
        }
    }
}
