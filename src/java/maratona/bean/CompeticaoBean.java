/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import maratona.dao.DAO;
import maratona.entity.Competicao;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author vitor_gja_
 */
@Named(value = "competicaoBean")
@SessionScoped
public class CompeticaoBean implements Serializable {
    /**
     * Variable
     * Get
     * Set
     */
    private Competicao competicao = new Competicao();
    public Competicao getCompeticao() { return competicao; }
    public void setCompeticao(Competicao competicao) { this.competicao = competicao; }
    
    private List<Competicao> competicoes = new ArrayList<>();
    public List<Competicao> getCompeticoes() { return competicoes; }
    public void setCompeticoes(List<Competicao> competicoes) { this.competicoes = competicoes; }
    
    private List<Competicao> competicoesFiltradas = new ArrayList();
    public List<Competicao> getCompeticoesFiltradas() { return competicoesFiltradas; }
    public void setCompeticoesFiltradas(List<Competicao> competicoesFiltradas) { this.competicoesFiltradas = competicoesFiltradas; }
    
    /**
     * Creates a new instance of MaratonaBean
     */
    public CompeticaoBean() {
        carregarCompeticoes();
    }
    
    private void carregarCompeticoes(){ 
        DAO dao = new DAO( Competicao.class);
        competicoes = dao.listarGenerico("Competicao.listarTodas");
    }
    
    
    public boolean consultarPorNome(Object value,
            Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        String valueText = (value == null) ? null : value.toString();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        if (valueText == null) {
            return false;
        }
        return valueText.matches("(?i).*" + filterText
                + ".*");
    }
    
    
    public String gravar() {
        DAO dao = new DAO(Competicao.class);
        dao.adicionar(competicao);
        competicao = new Competicao();
        // carregarCompeticaos();
        return "/sistema/competicao";
    }
    //@Transactional
    public void excluir(Competicao u) {
        DAO dao = new DAO(Competicao.class);
        dao.excluir(u.getId());
        competicoes.remove(u);
        FacesMessage msg = new FacesMessage(
                "Competicao excluído", u.getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String editar(Competicao c) {
        competicao = c;
        return "/sistema/competicao_editar";
    }

    public String alterar() {
        DAO dao = new DAO(Competicao.class);
        dao.alterar(competicao);
        return "/sistema/index";
    }

    public String paginaInicial() {
        competicao = new Competicao();
        DAO dao = new DAO(Competicao.class);
        competicoes = dao.listarGenerico("Competicao.listarTodas");
        return "/sistema/index";
    }

    public String paginaNovaCompeticao() {
        competicao = new Competicao();
        return "/sistema/competicao_nova";
    }
    
    public void onEdit(RowEditEvent event) {
        Competicao c = (Competicao) event.getObject();
        DAO<Competicao> dao = new DAO(Competicao.class);
        dao.alterar(c);
        FacesMessage msg = new FacesMessage(
                "Competição atualizada", c.getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        Competicao c = (Competicao) event.getObject();
        FacesMessage msg = new FacesMessage(
                "Atualização cancelada", c.getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
