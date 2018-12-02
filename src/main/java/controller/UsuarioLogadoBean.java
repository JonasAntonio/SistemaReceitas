/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import model.Usuario;

/**
 *
 * @author Juan Ferreira Carlos
 */
@Named(value = "usuarioLogadoBean")
@SessionScoped
public class UsuarioLogadoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Usuario usuario;
    
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public boolean existe() {
        return usuario != null;
    }
    
    //Método que invalida a sessão
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
    
}
