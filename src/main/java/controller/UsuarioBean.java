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

    private Usuario usuario;
    
    @Inject
    private UsuarioFacade usuarioFacade;
    
    @PostConstruct
    public void Init() {
        usuario = new Usuario();
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
        usuarioFacade.create(usuario);
        // Retorna para a página de login após o cadastro
        return "login";
    }

}
