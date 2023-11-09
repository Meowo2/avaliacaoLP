/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import academia.ConnectionAcademia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Cliente;

/**
 *
 * @author Manhã
 */
public class ClienteDAO {
    
    private Connection connection;
   
    public ClienteDAO(){ 
        this.connection = new ConnectionAcademia().getConnection();
    } 
    
    public void adiciona(Cliente cliente){ 
        String sql = "INSERT INTO cadastro(cli_nome, cli_cpf, cli_data_nascimento, cli_peso, cli_altura) VALUE(?,?,?,?,?)";
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
           // String id_aux=Integer.toString(cliente.getId());
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getCpf());
            stmt.setString(3, cliente.get);
            stmt.setString(4, cliente.getNome());
            stmt.setString(5, cliente.getNome());
            stmt.execute();
            stmt.close();
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
        
    } 
    
}
