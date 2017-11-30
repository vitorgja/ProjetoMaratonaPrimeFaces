/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

import maratona.dao.DAO;
import maratona.entity.Usuario;
/**
 *
 * @author vitor_gja_
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {
    private String login;
    private String senha;

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
    
    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }
    
    
    
    
    public String logar(){
       
        DAO dao = new DAO(Usuario.class);
        int size = dao.listarGenerico("Usuario.logar", login, senha).size();
        if( size == 0 ){
            return "/";
        }else if( size >= 1 ){
            return "/sistema/usuario";
        }else{
            return "/error/999";
        }
    }
}
