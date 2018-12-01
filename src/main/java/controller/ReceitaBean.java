/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.ReceitaFacade;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.Receita;

/**
 *
 * @author Juan Ferreira Carlos
 */
@Named(value = "receitaBean")
@RequestScoped
public class ReceitaBean {

    private Receita receita;
    
    @Inject
    private ReceitaFacade receitaFacade;
    
    /**
     * Creates a new instance of ReceitaBean
     */
    public ReceitaBean() {
    }
    
    @PostConstruct
    public void Init() {
        receita = new Receita();
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public ReceitaFacade getReceitaFacade() {
        return receitaFacade;
    }

    public void setReceitaFacade(ReceitaFacade receitaFacade) {
        this.receitaFacade = receitaFacade;
    }
    
}
