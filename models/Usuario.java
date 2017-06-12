/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import helpers.Config;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 *
 * @author qwerty
 */
public class Usuario {

    int Id;
    String Name;
    String Password;
    boolean isAdmin;

    private String user_nm, pass_u, pass_dif;

    private void Traduz() {
        ResourceBundle rbl = null;
        rbl = Config.getResources();
        
        user_nm = rbl.getString("user_nm");
        pass_u = rbl.getString("pass_u");
        pass_dif = rbl.getString("pass_dif");

    }

    public Usuario() {
        Traduz();
        this.Id = 0;
        this.Name = "";
        this.Password = "";
        this.isAdmin = false;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    public boolean getAdmin() {
        return this.isAdmin;
    }

    public void setName(String Name) throws IllegalArgumentException {
        if (Name.isEmpty()) {
            throw new IllegalArgumentException(user_nm);
        } else {
            this.Name = Name.toUpperCase();
        }
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) throws IllegalArgumentException {
        if (Password.isEmpty()) {
            throw new IllegalArgumentException(pass_u);
        } else {
            this.Password = Password;
        }

    }

    public void ConfirmaSenha(String passwordConfirma) throws Exception {
        if (!this.Password.equals(passwordConfirma)) {
            throw new Exception(pass_dif);
        }

    }
}
