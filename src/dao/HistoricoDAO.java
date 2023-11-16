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
        String sql = "INSERT INTO historico(alu_id, his_peso, his_dataHora) VALUE(?,?,?)";
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            //stmt.setString(1, aluno.getNome());
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
}
