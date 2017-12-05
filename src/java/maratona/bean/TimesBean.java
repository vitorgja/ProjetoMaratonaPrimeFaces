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
import maratona.entity.Times;

/**
 *
 * @author vitor_gja_
 */
@Named(value = "timesBean")
@SessionScoped
public class TimesBean implements Serializable {
    /**
     * Variable
     * Get
     * Set
     */
    private Times time = new Times();
    public Times getTimes() { return time; }
    public void setTimes(Times time) { this.time = time; }
    
    private List<Times> times = new ArrayList();
    public List<Times> getTimess() { return times; }
    public void setTimess(List<Times> times) { this.times = times; }
    
    private List<Times> timesFiltrados = new ArrayList();
    public List<Times> getTimessFiltrados() { return timesFiltrados; }
    public void setTimessFiltrados(List<Times> timesFiltrados) { this.timesFiltrados = timesFiltrados; }
    
    /**
     * Creates a new instance of MaratonaBean
     */
    public TimesBean() {
        carregarTimes();
    }
    
    private void carregarTimes(){ 
        DAO dao = new DAO( Times.class);
        times = dao.listarGenerico("Times.listarTodos");
    }
    
    
    public String gravar() {
        DAO dao = new DAO(Times.class);
        dao.adicionar(time);
        time = new Times();
        return null;
    }
    //@Transactional
    public void excluir(Times u) {
        DAO dao = new DAO(Times.class);
        dao.excluir(u.getId());
        times.remove(u);
        FacesMessage msg = new FacesMessage(
                "Times excluído", u.getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String editar(Times t) {
        time = t;
        return "/sistema/times_editar";
    }

    public String alterar() {
        DAO dao = new DAO(Times.class);
        dao.alterar(time);
        return "/sistema/index";
    }

    public String paginaInicial() {
        time = new Times();
        DAO dao = new DAO(Times.class);
        times = dao.listarGenerico("Times.listarTodas");
        return "/sistema/index";
    }

    public String paginaNovoTimes() {
        time = new Times();
        return "/sistema/times_novo";
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
