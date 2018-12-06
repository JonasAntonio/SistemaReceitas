/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.usuario;

import facade.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import model.Usuario;

/**
 *
 * @author Juan Ferreira Carlos
 */
@Named(value = "usuarioListaBean")
@Dependent
public class UsuarioListaBean implements Serializable {
  
    @Inject
    private UsuarioFacade usuarioFacade;

    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @PostConstruct
    public void init(){
        usuarios = usuarioFacade.findAll();
    }
}
