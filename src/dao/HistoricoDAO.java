package dao;

import academia.ConnectionAcademia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Historico;

public class HistoricoDAO {
    private Connection connection;
   
    public HistoricoDAO(){ 
        this.connection = new ConnectionAcademia().getConnection();
    } 
    
    public void adicionaHistorico(Historico historico){ 
        String sql = "INSERT INTO historico(alu_cpf, his_peso, his_dataHora) VALUE(?,?,?)";
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, historico.getAluCpf());
            stmt.setDouble(2, historico.getPeso());
            stmt.setString(3, historico.getDataHora());
            stmt.execute();
            stmt.close();
        } 
        catch (SQLException u) { 
            JOptionPane.showMessageDialog(null, "Não foi possível salvar dados no histórico");
            throw new RuntimeException(u);
        } 
        
    }
    
    /*
    public void excluirHistoricoPorCpf(String cpf){
        String sql = "DELETE FROM historico WHERE alu_cpf = ?";
        
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, cpf);
            stmt.execute();
            stmt.close();
            
        } 
        catch (SQLException u) { 
            JOptionPane.showMessageDialog(null, "Não foi possível excluir cadastro do Aluno");
            throw new RuntimeException(u);
        }         
    }
    */
}
