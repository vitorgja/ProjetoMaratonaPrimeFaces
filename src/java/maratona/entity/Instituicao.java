package maratona.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ciro
 */
@NamedQueries({
    @NamedQuery(name = "Instituicao.listarTodas", query = "select i from Instituicao i order by i.nome"),
    @NamedQuery(name = "Instituicao.consultarPorNome",  query = "select i from Instituicao i where i.nome like ?1 order by i.nome")
})
@Entity
public class Instituicao extends AbstractEntity {
    private String nome;
    private String cidade;    
    
    public Instituicao() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
