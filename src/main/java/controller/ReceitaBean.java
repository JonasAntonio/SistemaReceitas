/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.ReceitaFacade;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import model.Receita;
import model.Usuario;

/**
 *
 * @author Juan Ferreira Carlos
 */
@Named(value = "receitaBean")
@RequestScoped
public class ReceitaBean {

    private Receita receita;
    
    private List<Receita> receitas;
  
    @Inject
    private ReceitaFacade receitaFacade;
    
    @PostConstruct
    public void Init() {
        receita = new Receita();
        receitas = receitaFacade.findAll();
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }

    public ReceitaFacade getReceitaFacade() {
        return receitaFacade;
    }

    public void setReceitaFacade(ReceitaFacade receitaFacade) {
        this.receitaFacade = receitaFacade;
    }
    
    //Método de Cadastro da receita
    public String cadastrar(Usuario usuario) {
        //Pega o usuario da receita
        receita.setUsuario(usuario);
        try {      
            receitaFacade.create(receita);
        } catch (Exception e) {
            //Esse código retorna erro de violações das regras do banco com ele consegui arrumar o problema
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Receita>> constraintViolations = validator.validate(receita);

            if (constraintViolations.size() > 0 ) {
                System.out.println("Constraint Violations occurred..");
                for (ConstraintViolation<Receita> contraints : constraintViolations) {
                    System.out.println(contraints.getRootBeanClass().getSimpleName()+
                    "." + contraints.getPropertyPath() + " " + contraints.getMessage());
                }
            }
        }
        return "listaReceitas";
    }
    
    public String excluir(Receita receita){
       receitaFacade.remove(receita);
       Init();
       return "listaReceitas?faces-redirect=true"; 
    }
}
    

