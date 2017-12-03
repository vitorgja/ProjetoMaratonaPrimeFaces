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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import maratona.dao.DAO;
import maratona.entity.Usuario;

/**
 *
 * @author vitor_gja_
 */
@Named(value = "maratonaBean")
@SessionScoped
public class MaratonaBean implements Serializable {
    /**
     * Variaveis
     */
    private Usuario usuario = new Usuario();
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    private List<Usuario> usuarios = new ArrayList();
    public List<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }
    
    private List<Usuario> usuariosFiltrados = new ArrayList();
    public List<Usuario> getUsuariosFiltrados() { return usuariosFiltrados; }
    public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) { this.usuariosFiltrados = usuariosFiltrados; }
    /**
     * Creates a new instance of MaratonaBean
     */
    public MaratonaBean() {
        carregarUsuarios();
    }
    
    
    
    private void carregarUsuarios(){ 
        DAO dao = new DAO( Usuario.class);
        usuarios = dao.listarGenerico("Usuario.listarTodos");
    }
    
    
    
    
    
    
    
    
    //@Transactional
    public void excluir(Usuario u) {
        DAO dao = new DAO(Usuario.class);
        dao.excluir(u.getId());
        usuarios.remove(u);
        FacesMessage msg = new FacesMessage(
                "Conta excluída", u.getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String editar(Usuario u) {
        usuario = u;
        return "/editar_conta";
    }

    public String alterar() {
        DAO dao = new DAO(Usuario.class);
        dao.alterar(usuario);
        return "/index";
    }

    public String paginaInicial() {
        usuario = new Usuario();
        DAO dao = new DAO(Usuario.class);
        usuarios = dao.listarGenerico("Usuario.listarTodas");
        return "/index";
    }

}
