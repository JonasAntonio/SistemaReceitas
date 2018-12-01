/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.UsuarioFacade;
import java.io.Serializable;
import java.util.Optional;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import model.Usuario;

/**
 *
 * @author Juan Ferreira Carlos
 */
@Named(value = "usuarioLoginBean")
@ViewScoped
public class UsuarioLoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;
    
    @Inject
    private UsuarioFacade usuarioFacade;
    
    private String usuario; 
    private String senha;
    
    /**
     * Creates a new instance of UsuarioLoginBean
     */
    public UsuarioLoginBean() {
    }
    
    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String Logar() {
        Optional<Usuario> user = usuarioFacade.findByCredenciais(this.usuario, senha);
        if (user.isPresent()) {
            this.usuarioLogadoBean.setUsuario(user.get());
            return "/index.xhtml";
        }
        FacesMessage msg = new FacesMessage("Usuário ou senha inválido!");
        return "/login.xhtml?erro="+msg;
    }
    
}
