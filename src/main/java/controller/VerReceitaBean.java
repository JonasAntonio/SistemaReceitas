/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.ReceitaFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.Avaliacao;
import model.Receita;

/**
 *
 * @author Juan Ferreira Carlos
 */
@Named(value = "verReceitaBean")
@RequestScoped
public class VerReceitaBean {

    private Receita receita;
    
    @Inject
    private ReceitaFacade receitaFacade;
    
    //Buscar os comentarios da receita
    private List<Avaliacao> avaliacoes;
    
    @PostConstruct
    public void Init() {
        Receita receitaRecuperada = recuperarReceita();
        if(receitaRecuperada != null) {
            this.receita = receitaRecuperada;
        } else {
            this.receita = new Receita();
        }
        this.avaliacoes = (List<Avaliacao>) this.receita.getAvaliacaoCollection();
    }

    public Receita recuperarReceita() {
        Receita receitaRecuperada = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        if (ec != null) {
            String receitaIdString = ec.getRequestParameterMap().get("receitaId");
            if(receitaIdString != null) {
                receitaRecuperada = this.receitaFacade.find(Long.parseLong(receitaIdString));
            }
        }
        return receitaRecuperada;
    }
    
}
