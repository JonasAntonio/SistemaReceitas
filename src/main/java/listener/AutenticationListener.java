/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import controller.UsuarioLogadoBean;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Juan Ferreira Carlos
 */
public class AutenticationListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        ExternalContext context = event.getFacesContext().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        String requestedURL = request.getRequestURI();
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (!requestedURL.equals("/login.xhtml")) {
            Application app = ctx.getApplication();
            UsuarioLogadoBean usuario = app.evaluateExpressionGet(ctx, "#{usuarioLogadoBean}", UsuarioLogadoBean.class);
            if (!usuario.existe()) {
                NavigationHandler handler = app.getNavigationHandler();
                handler.handleNavigation(ctx, "", "login");
                ctx.renderResponse();
            }
            
        }

    }

    @Override
    public void beforePhase(PhaseEvent event) {  }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
