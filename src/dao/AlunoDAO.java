/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import academia.ConnectionAcademia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Aluno;

/**
 *
 * @author Manhã
 */
public class AlunoDAO {
    
    private Connection connection;
   
    public AlunoDAO(){ 
        this.connection = new ConnectionAcademia().getConnection();
    } 
    
    public void adiciona(Aluno aluno){ 
        String sql = "INSERT INTO cadastro(alu_nome, alu_cpf, alu_data_nascimento, alu_peso, alu_altura) VALUE(?,?,?,?,?)";
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
           // String id_aux=Integer.toString(cliente.getId());
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getCpf());
            stmt.setString(3, aluno.getDataSQL());
            stmt.setFloat(4, aluno.getPeso());
            stmt.setFloat(5, aluno.getAltura());
            stmt.execute();
            stmt.close();
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
        
    } 
    
}
