/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.AvaliacaoFacade;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.Avaliacao;

/**
 *
 * @author Juan Ferreira Carlos
 */
@Named(value = "avaliacaoBean")
@RequestScoped
public class AvaliacaoBean {

    private Avaliacao avaliacao;
    
    @Inject
    private AvaliacaoFacade avaliacaoFacade;
    
    /**
     * Creates a new instance of AvaliacaoBean
     */
    public AvaliacaoBean() {
    }
    
    @PostConstruct
    public void Init() {
        avaliacao = new Avaliacao();
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public AvaliacaoFacade getAvaliacaoFacade() {
        return avaliacaoFacade;
    }

    public void setAvaliacaoFacade(AvaliacaoFacade avaliacaoFacade) {
        this.avaliacaoFacade = avaliacaoFacade;
    }
    
    
    
}
