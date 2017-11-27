package maratona.dao;

import javax.persistence.EntityManager;
import maratona.util.jpa.JPAEntityManager;
import java.util.List;
import javax.persistence.Query;

public class DAO<T> {

    private final Class<T> classe;
    private EntityManager manager;

    public DAO(Class<T> classe) {
        this.classe = classe;
    }

    public void adicionar(T t) {
        manager = JPAEntityManager.getEntityManager();
        manager.getTransaction().begin();
        manager.persist(t);
        manager.getTransaction().commit();
        manager.close();
    }

    public T consultar(Long id) {
        manager = JPAEntityManager.getEntityManager();
        T instancia = manager.find(classe, id);
        manager.close();
        return instancia;
    }

    public void alterar(T t) {
        manager = JPAEntityManager.getEntityManager();
        manager.getTransaction().begin();
        manager.merge(t);
        manager.getTransaction().commit();
        manager.close();
    }

    public void excluir(Long id) {
        manager = JPAEntityManager.getEntityManager();
        T t = manager.find(classe, id);
        manager.getTransaction().begin();
        manager.remove(t);
        manager.getTransaction().commit();
        manager.close();
    }

    public List<T> listarGenerico(String query, Object... params) {
        manager = JPAEntityManager.getEntityManager();
        Query q = manager.createNamedQuery(query);
        for (int i = 0; i < params.length; i++) {
            q.setParameter(i + 1, params[i]);
        }
        List<T> todos = q.getResultList();
        manager.close();
        return todos;
    }
}
