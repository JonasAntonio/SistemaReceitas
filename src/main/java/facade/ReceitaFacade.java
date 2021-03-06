/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Receita;

/**
 *
 * @author Juan Ferreira Carlos
 */
@Stateless
public class ReceitaFacade extends AbstractFacade<Receita> {

    @PersistenceContext(unitName = "com.mycompany_SistemaReceitas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReceitaFacade() {
        super(Receita.class);
    }

    public Optional<Receita> findByTitulo(String busca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
