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
import javax.faces.context.FacesContext;
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

    @Inject UsuarioFacade facade;

    private String login;
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
  
    //Método de acesso ao sistema
    public String efetuarLogin(){
        Optional<Usuario> usuario = facade.findByCredenciais(login, senha);
        if (usuario.isPresent()) {
            this.usuarioLogadoBean.setUsuario(usuario.get());
            return "adicionarReceita";
        }

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao Autenticar", "Usuário ou senha inválido!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }
}
