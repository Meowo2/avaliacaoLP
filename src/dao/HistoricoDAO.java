package dao;

import academia.ConnectionAcademia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import model.Historico;
import java.sql.ResultSet;


public class HistoricoDAO {
    private Connection connection;
    private String dataHora;
   
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
    
    
    public void excluirHistoricoPorCpf(String cpf){
        String sql = "DELETE FROM historico WHERE alu_cpf = ?";
        
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, cpf);
            stmt.execute();
            stmt.close();
            
        } 
        catch (SQLException u) { 
            JOptionPane.showMessageDialog(null, "Não foi possível excluir histórico do Aluno");
            throw new RuntimeException(u);
        }         
    }
    
    public String consultarHistoricoPorCpf(String cpf) {
        ResultSet rs;
        try {
            String query = "SELECT * FROM historico WHERE alu_cpf = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, cpf);
                rs = pstmt.executeQuery();
    
                StringBuilder historico = new StringBuilder();
    
                while (rs.next()) {
                    String id = Integer.toString(rs.getInt("alu_id"));
                    String peso = Double.toString(rs.getDouble("his_peso"));
                    Timestamp dataHoraTimestamp = rs.getTimestamp("his_dataHora");
    
                    // Formatando Timestamp para uma String
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dataHora = dateFormat.format(dataHoraTimestamp);
    
                    historico.append("Id: ").append(id).append("\n")
                            .append("Peso: ").append(peso).append("Kg").append("\n")
                            .append("Data e Hora: ").append(dataHora).append("\n\n");
                }
    
                return historico.toString();
            }
        } catch (SQLException u) {
            JOptionPane.showMessageDialog(null, "Não foi possível concluir a consulta");
            return "";
        }
    }
    
    
    
}
