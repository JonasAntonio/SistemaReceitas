/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.UsuarioFacade;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.Usuario;
import util.Hash;

/**
 *
 * @author Juan Ferreira Carlos
 */
@Named(value = "usuarioBean")
@RequestScoped
public class UsuarioBean {

    private Usuario usuario = new Usuario();
    
    @Inject
    private UsuarioFacade usuarioFacade;
   
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
    
    public String Cadastrar() {
        String senha = usuario.getSenha();
        usuario.setSenha(Hash.md5(senha));
        usuarioFacade.create(usuario);
        return "login";
    }

}
