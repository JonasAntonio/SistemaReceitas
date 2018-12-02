/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.UsuarioFacade;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import model.Usuario;
import org.eclipse.persistence.jpa.jpql.Assert;
import util.Hash;

/**
 *
 * @author Juan Ferreira Carlos
 */
@Named(value = "usuarioBean")
@RequestScoped
public class UsuarioBean {

    private Usuario usuario;
    
    @Inject
    private UsuarioFacade usuarioFacade;
    
    @PostConstruct
    public void init(){
        usuario  = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }
    
    //Metódo de cadastro de usuário
    public String cadastrar() {
        usuario.setSenha(Hash.md5(usuario.getSenha()));
        try {
            usuarioFacade.create(usuario);
        } catch (Exception e) {
            //Esse código retorna erro de violações das regras do banco com ele consegui arrumar o problema
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);

            if (constraintViolations.size() > 0 ) {
                System.out.println("Constraint Violations occurred..");
                for (ConstraintViolation<Usuario> contraints : constraintViolations) {
                    System.out.println(contraints.getRootBeanClass().getSimpleName()+
                    "." + contraints.getPropertyPath() + " " + contraints.getMessage());
                }
            }
        }
        // Retorna para a página de login após o cadastro
        return "login";
    }
    
}
