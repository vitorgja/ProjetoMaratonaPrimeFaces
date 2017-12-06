/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package maratona.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vitor_gja_
 */

@NamedQueries({
    @NamedQuery(name = "Competicao.listarTodas", query = "select c from Competicao c order by c.nome"),
    @NamedQuery(name = "Competicao.listarAtiva", query = "select c from Competicao c where c.ativa = ?1"),
    @NamedQuery(name = "Competicao.consultarPorNome",  query = "select c from Competicao c where c.nome like ?1 order by c.nome")
})
@Entity
public class Competicao extends AbstractEntity {
    private String nome;
    @Temporal(TemporalType.DATE)
    private Date data_hora;
    private int duracao;
    private boolean ativa;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData_hora() {
        return data_hora;
    }

    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
    
}
