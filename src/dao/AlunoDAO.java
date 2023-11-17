/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import academia.ConnectionAcademia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Aluno;
import model.Historico;

/**
 *
 * @author Manhã
 */
public class AlunoDAO {
    
    private Connection connection;
   
    public AlunoDAO(){ 
        this.connection = new ConnectionAcademia().getConnection();
    } 
    
    public void adicionaAluno(Aluno aluno){ 
        String sql = "INSERT INTO cadastro(alu_nome, alu_cpf, alu_data_nascimento, alu_peso, alu_altura) VALUE(?,?,?,?,?)";
        String sql2 = "INSERT INTO historico(alu_cpf, his_peso, his_dataHora) VALUE(?, ?, ?)";
        
        Historico his = new Historico(aluno.getCpf(), aluno.getPeso());
        
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getDataSQL());
            stmt.setDouble(4, aluno.getPeso());
            stmt.setDouble(5, aluno.getAltura());
            stmt.execute();
            stmt.close();
            
            PreparedStatement stmt2 = connection.prepareStatement(sql);
            
            stmt2.setString(1, his.getAluCpf());
            stmt2.setDouble(2, his.getPeso());
            stmt2.setString(3, his.getDataHora());
            stmt2.execute();
            stmt2.close();
            
        } 
        catch (SQLException u) { 
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o Aluno");
            throw new RuntimeException(u);
        }         
        
    } 
    
    public boolean cpfExiste(String cpf){
        String sql = "SELECT * FROM cadastro WHERE alu_cpf = ?";
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, cpf);
            stmt.execute();
            stmt.close();
            ResultSet resultSet = stmt.getResultSet();
            
            return resultSet.next();
        } 
        catch (SQLException u) { 
            JOptionPane.showMessageDialog(null, "CPF não cadastrado");
            return false;
            //throw new RuntimeException(u);
        } 
    }
    
}
