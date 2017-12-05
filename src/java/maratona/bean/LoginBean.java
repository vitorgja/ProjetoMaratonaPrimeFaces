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
import maratona.session.SessionContext;


import maratona.dao.DAO;
import maratona.entity.Usuario;
/**
 *
 * @author vitor_gja_
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    /* Global Vars */
    private Usuario usuario;
    private String login;
    private String senha;
  
    /* Getters and Setters */
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    /**
     * Creates a new instance of UsuarioBean
     */
    public LoginBean() { }
    
    
    /* Metodo para fazer a autencicação na aplicacao */
    public String logar(){
        
        SessionContext session = SessionContext.getInstance();
        DAO dao = new DAO( Usuario.class);
        int size = dao.listarGenerico("Usuario.logar", login, senha).size();
        if( size == 0 ){
            session.setAttribute("message", "Login/senha inválidos");
            return null;
        }else if( size >= 1 ){
            session.setAttribute("usuario", usuario);
            return "/sistema/index";
        }else{
            session.setAttribute("message", "Um erro desconhecido aconteceu ao Logar!");
            return "/error/999";
        }
    }
    
    /* Metodo para fazer o Logout da aplicação */
    public String sair() {
        SessionContext session = SessionContext.getInstance();
        session.encerrarSessao();
        return "/index";
    }
    
    /* Recupera as mensagens de sessão */
    public String getMessage() {
        SessionContext session = SessionContext.getInstance();
        String message = (String) session.getAttribute("message");
        return message;
    }
}
