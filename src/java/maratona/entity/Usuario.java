package maratona.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author ciro
 */
@NamedQueries({
    @NamedQuery(name = "Usuario.listarTodas", query = "select c from Usuario c order by c.titular"),
    @NamedQuery(name = "Usuario.consultarPorNome",  query = "select c from Usuario c where c.titular like ?1 order by c.titular")
})
@Entity
public class Usuario extends AbstractEntity {
    private String nome;
    private String login;
    private String senha;
    private Integer nivel;
    // @OneToMany(cascade=CascadeType.REMOVE, mappedBy = "conta")
    // private List<Movimentacao> movimentacoes = new ArrayList<>();

    public Usuario() {
    }
    
    
    
/*
    public List<Movimentacao> getMovimentacoes() {;;;
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
*/      

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
}