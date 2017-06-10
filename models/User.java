/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Objects;

/**
 *
 * @author qwerty
 */
public class User {

    int Id;
    String Name;
    String Password;
    boolean isAdmin;

    public User() {
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
            throw new IllegalArgumentException("Nome do usuario deve ser informado!");
        } else {
            this.Name = Name.toUpperCase();
        }
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) throws IllegalArgumentException {
        if (Password.isEmpty()) {
            throw new IllegalArgumentException("Senha do usuario deve ser informada!");
        } else {
            this.Password = Password;
        }

    }
}
