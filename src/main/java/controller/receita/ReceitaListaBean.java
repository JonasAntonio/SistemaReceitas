/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.receita;

import facade.ReceitaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import model.Receita;

/**
 *
 * @author Juan Ferreira Carlos
 */
@Named(value = "receitaListaBean")
@ViewScoped
public class ReceitaListaBean implements Serializable {
  
   @Inject
   private ReceitaFacade receitaFacade;
  
   private List<Receita> receitas;

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }
  
   @PostConstruct
   public void init(){
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
  
}
