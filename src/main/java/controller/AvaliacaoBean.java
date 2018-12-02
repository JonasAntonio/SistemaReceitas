/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.AvaliacaoFacade;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import model.Avaliacao;
import model.Receita;
import model.Usuario;
import util.Hash;

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
    
    @PostConstruct
    public void init() {
        avaliacao = new Avaliacao();
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    //Metódo de cadastro de usuário
    public String cadastrar(Usuario usuario, Receita receita) {
        avaliacao.setUsuario(usuario);
        avaliacao.setReceita(receita);
        try {
            avaliacaoFacade.create(avaliacao);
        } catch (Exception e) {
            //Esse código retorna erro de violações das regras do banco com ele consegui arrumar o problema
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Avaliacao>> constraintViolations = validator.validate(avaliacao);

            if (constraintViolations.size() > 0 ) {
                System.out.println("Constraint Violations occurred..");
                for (ConstraintViolation<Avaliacao> contraints : constraintViolations) {
                    System.out.println(contraints.getRootBeanClass().getSimpleName()+
                    "." + contraints.getPropertyPath() + " " + contraints.getMessage());
                }
            }
        }
        // Retorna para a página de login após o cadastro
        return "login";
    }
    
    //Para mudança de senha
    public String atualizar(Avaliacao avaliacao) {
        avaliacaoFacade.edit(avaliacao);
        init();
        return "verReceita";
    }
    
    //pertence ao admin
    public String excluir(Avaliacao avaliacao) {
       avaliacaoFacade.remove(avaliacao);
       init();
       //Volta para a lista de usuarios
       return "listaAvaliacoes"; 
    }
    
}
