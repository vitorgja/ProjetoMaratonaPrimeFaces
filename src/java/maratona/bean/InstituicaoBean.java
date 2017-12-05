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
import maratona.entity.Instituicao;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author vitor_gja_
 */
@Named(value = "instituicaoBean")
@SessionScoped
public class InstituicaoBean implements Serializable {
    /**
     * Variable
     * Get
     * Set
     */
    private Instituicao Instituicao = new Instituicao();
    public Instituicao getInstituicao() { return Instituicao; }
    public void setInstituicao(Instituicao Instituicao) { this.Instituicao = Instituicao; }
    
    private List<Instituicao> Instituicoes = new ArrayList();
    public List<Instituicao> getInstituicoes() { return Instituicoes; }
    public void setInstituicoes(List<Instituicao> Instituicoes) { this.Instituicoes = Instituicoes; }
    
    private List<Instituicao> InstituicoesFiltrados = new ArrayList();
    public List<Instituicao> getInstituicoesFiltrados() { return InstituicoesFiltrados; }
    public void setInstituicoesFiltrados(List<Instituicao> InstituicoesFiltrados) { this.InstituicoesFiltrados = InstituicoesFiltrados; }
    
    /**
     * Creates a new instance of MaratonaBean
     */
    public InstituicaoBean() {
        carregarInstituicaos();
    }
    
    private void carregarInstituicaos(){ 
        DAO dao = new DAO( Instituicao.class);
        Instituicoes = dao.listarGenerico("Instituicao.listarTodas");
    }
    
    public String gravar() {
        DAO dao = new DAO(Instituicao.class);
        dao.adicionar(Instituicao);
        Instituicao = new Instituicao();
        return null;
    }
    //@Transactional
    public void excluir(Instituicao u) {
        DAO dao = new DAO(Instituicao.class);
        dao.excluir(u.getId());
        Instituicoes.remove(u);
        FacesMessage msg = new FacesMessage(
                "Conta excluída", u.getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String editar(Instituicao u) {
        Instituicao = u;
        return "/sistema/Instituicao_editar";
    }

    public String alterar() {
        DAO dao = new DAO(Instituicao.class);
        dao.alterar(Instituicao);
        return "/sistema/index";
    }

    public String paginaInicial() {
        Instituicao = new Instituicao();
        DAO dao = new DAO(Instituicao.class);
        Instituicoes = dao.listarGenerico("Instituicao.listarTodas");
        return "/sistema/index";
    }

    public String paginaNovaInstituicao() {
        Instituicao = new Instituicao();
        return "/sistema/instituicao_nova";
    }
    
    public void onEdit(RowEditEvent event) {
        Instituicao i = (Instituicao) event.getObject();
        DAO<Instituicao> dao = new DAO(Instituicao.class);
        dao.alterar(i);
        FacesMessage msg = new FacesMessage(
                "Conta atualizada", i.getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        Instituicao i = (Instituicao) event.getObject();
        FacesMessage msg = new FacesMessage(
                "Atualização cancelada", i.getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
     public boolean consultarPorNome(Object value,
            Object filter, Locale locale) {
        String filterText = (filter == null) ? null
                : filter.toString().trim();
        String valueText = (value == null) ? null
                : value.toString();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        if (valueText == null) {
            return false;
        }
        return valueText.matches("(?i).*" + filterText
                + ".*");
    }
}
