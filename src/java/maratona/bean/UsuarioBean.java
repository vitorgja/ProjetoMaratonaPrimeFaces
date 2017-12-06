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
import javax.faces.model.SelectItem;
import maratona.dao.DAO;
import maratona.entity.NivelUsuario;
import maratona.entity.Usuario;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author vitor_gja_
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {
    /**
     * Variable
     * Get
     * Set
     */
    private Usuario usuario = new Usuario();
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    private List<Usuario> usuarios = new ArrayList<>();
    public List<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }
    
    private List<Usuario> usuariosFiltrados = new ArrayList();
    public List<Usuario> getUsuariosFiltrados() { return usuariosFiltrados; }
    public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) { this.usuariosFiltrados = usuariosFiltrados; }
    
    /**
     * Creates a new instance of MaratonaBean
     */
    public UsuarioBean() {
        carregarUsuarios();
    }
    
    private void carregarUsuarios(){ 
        DAO dao = new DAO( Usuario.class);
        usuarios = dao.listarGenerico("Usuario.listarTodos");
    }
    
    public List<SelectItem> niveis(){
        List<SelectItem> niveis = new ArrayList<SelectItem>();
        niveis.add( new SelectItem(NivelUsuario.USUARIO) );
        niveis.add( new SelectItem(NivelUsuario.ADMINISTRADOR) );
        return niveis;
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
    
    
    public String gravar() {
        DAO dao = new DAO(Usuario.class);
        dao.adicionar(usuario);
        usuario = new Usuario();
        // carregarUsuarios();
        return "/sistema/usuario";
    }
    //@Transactional
    public void excluir(Usuario u) {
        DAO dao = new DAO(Usuario.class);
        dao.excluir(u.getId());
        usuarios.remove(u);
        FacesMessage msg = new FacesMessage(
                "Usuario excluído", u.getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String editar(Usuario u) {
        usuario = u;
        return "/sistema/usuario_editar";
    }

    public String alterar() {
        DAO dao = new DAO(Usuario.class);
        dao.alterar(usuario);
        return "/sistema/index";
    }

    public String paginaInicial() {
        usuario = new Usuario();
        DAO dao = new DAO(Usuario.class);
        usuarios = dao.listarGenerico("Usuario.listarTodas");
        return "/sistema/index";
    }

    public String paginaNovoUsuario() {
        usuario = new Usuario();
        return "/sistema/usuario_novo";
    }
    
    public void onEdit(RowEditEvent event) {
        Usuario c = (Usuario) event.getObject();
        DAO<Usuario> dao = new DAO(Usuario.class);
        dao.alterar(c);
        FacesMessage msg = new FacesMessage(
                "Usuário atualizado", c.getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        Usuario c = (Usuario) event.getObject();
        FacesMessage msg = new FacesMessage(
                "Atualização cancelada", c.getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
