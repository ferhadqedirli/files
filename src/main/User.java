/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class User implements Serializable{
    public String name;
    public transient String password;
    public int i;
    
    private static final long serialVersionUID = 1L;
}
