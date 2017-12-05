package maratona.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ciro
 */
@NamedQueries({
    @NamedQuery(name = "Times.listarTodos", query = "select c from Times c order by c.nome"),
    @NamedQuery(name = "Times.consultarPorNome",  query = "select c from Times c where c.nome like ?1 order by c.nome")
})
@Entity
public class Times extends AbstractEntity {
    private String nome;
      
    
    public Times() {
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }  
}
