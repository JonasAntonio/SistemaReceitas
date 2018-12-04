/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.ReceitaFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import model.Avaliacao;
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
    
    private String busca;
    
    @Inject
    private ReceitaFacade receitaFacade;
    
    @PostConstruct
    public void init() {
        receita = new Receita();
        receitas = receitaFacade.findAll();
        if(receitas == null){
            receitas = new ArrayList<Receita>();
        }else{
            for(Receita r : receitas){
                if(r.getPreparo().length() >= 80){
                    r.setPreparo(r.getPreparo().substring(0, 80) + "...");
                }                
            }
        }
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

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
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
    
    //Para edição da receita
    public String atualizar(Receita receita) {
        receitaFacade.edit(receita);
        init();
        return "listaReceitas?faces-redirect=true"; 
    }
    
    //Parte do admin remoção de receita
    public String excluir(Receita receita){
       receitaFacade.remove(receita);
       init();
       return "listaReceitas?faces-redirect=true"; 
    }
    
    //Método de busca da Receita pelo titulo
    public String buscarTitulo(){
        Optional<Receita> receitaOp = receitaFacade.findByTitulo(busca);
        if (receitaOp.isPresent()) {
            //vai para a pagina com os dados da receita
            return "verReceita?id="+receitaOp.get().getId();
        }

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Receita não Encontrada", "Título não Encontrado!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }
    
    public void abrirReceita(Long receitaId) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        try {
            response.sendRedirect("verReceita?id=" + receitaId);
        } catch (IOException e) {
            Logger.getLogger(ReceitaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
    

