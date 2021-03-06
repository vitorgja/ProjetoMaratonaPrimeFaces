package maratona.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ciro
 */
@NamedQueries({
    @NamedQuery(name = "Usuario.logar", query = "select c from Usuario c where c.login = ?1 and c.senha = ?2"),
    @NamedQuery(name = "Usuario.listarTodos", query = "select c from Usuario c order by c.nome"),
    @NamedQuery(name = "Usuario.consultarPorNome",  query = "select c from Usuario c where c.nome like ?1 order by c.nome")
})
@Entity
public class Usuario extends AbstractEntity {
    private String nome;
    private String login;
    private String senha;
    
    @Enumerated(EnumType.STRING)
    private NivelUsuario nivel;
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

    public NivelUsuario getNivel() {
        return nivel;
    }

    public void setNivel(NivelUsuario nivel) {
        this.nivel = nivel;
    }
}
