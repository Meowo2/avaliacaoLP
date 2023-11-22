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
        
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        String dataFormatada = formatterData.format(LocalDateTime.now());  //formata data atual

        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = formatterHora.format(LocalDateTime.now());   //formata hora atual
        
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getDataSQL());
            stmt.setDouble(4, aluno.getPeso());
            stmt.setDouble(5, aluno.getAltura());
            stmt.execute();
            stmt.close();
            
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            
            stmt2.setString(1, aluno.getCpf());
            stmt2.setDouble(2, aluno.getPeso());
            stmt2.setString(3, dataFormatada + " " + horaFormatada);
            stmt2.execute();
            stmt2.close();
            
        } 
        catch (SQLException u) { 
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o Aluno");
            throw new RuntimeException(u);
        }         
        
    } 
    
    public boolean cpfExiste(String cpf){
        ResultSet rs;
        try { 
            rs = connection.prepareStatement("SELECT * FROM cadastro WHERE alu_cpf = "+ cpf).executeQuery();
            
            return rs.next();
        } 
        catch (SQLException u) { 
            JOptionPane.showMessageDialog(null, "CPF não cadastrado");
            return false;
            //throw new RuntimeException(u);
        } 
    }
    
    public void excluirAluno(Aluno aluno){
        String sql = "DELETE FROM historico WHERE alu_cpf = ?";
        String sql2 = "DELETE FROM cadastro WHERE alu_cpf = ?;";
        
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, aluno.getCpf());
            stmt.execute();
            stmt.close();
            
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            
            stmt2.setString(1, aluno.getCpf());
            stmt2.execute();
            stmt2.close();
            
        } 
        catch (SQLException u) { 
            JOptionPane.showMessageDialog(null, "Não foi possível excluir cadastro do Aluno");
            throw new RuntimeException(u);
        }         
    }
    
    public void excluirAlunoPorCopf(String cpfExcluir){
        String sql = "DELETE FROM historico WHERE alu_cpf = ?";
        String sql2 = "DELETE FROM cadastro WHERE alu_cpf = ?;";
        
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, cpfExcluir);
            stmt.execute();
            stmt.close();
            
            PreparedStatement stmt2 = connection.prepareStatement(sql2);
            
            stmt2.setString(1, cpfExcluir);
            stmt2.execute();
            stmt2.close();
            
        } 
        catch (SQLException u) { 
            JOptionPane.showMessageDialog(null, "Não foi possível excluir cadastro do Aluno");
            throw new RuntimeException(u);
        }         
    }
    
}
