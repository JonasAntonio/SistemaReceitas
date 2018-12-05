/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.UsuarioFacade;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import model.Usuario;
import util.Hash;

/**
 *
 * @author Juan Ferreira Carlos
 */
@Named(value = "usuarioBean")
@RequestScoped
public class UsuarioBean {

    private Usuario usuario;
    
    private List<Usuario> usuarios;
    
    @Inject
    private UsuarioFacade usuarioFacade;
    
    @PostConstruct
    public void init(){
        usuario  = new Usuario();
        this.usuarios = usuarioFacade.findAll();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    
    //Para mudança de senha
    public String atualizar(Usuario usuario) {
        usuarioFacade.edit(usuario);
        init();
        return "login";
    }
    
    //pertence ao admin
    public String excluir(Usuario usuario) {
       usuarioFacade.remove(usuario);
       init();
       //Volta para a lista de usuarios
       return "listaUsuarios"; 
    }
    
}
