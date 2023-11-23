/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package principal;

import academia.ConnectionAcademia;
import java.sql.Connection;
import java.sql.SQLException;
import model.Aluno;
import model.Data;
import dao.AlunoDAO;
import gui.AlterarPeso;
import gui.Login;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Manhã
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException{   //Teste adicionando no banco
        // TODO code application logic here
        /*
        Connection connection = new ConnectionAcademia().getConnection();
        System.out.println("Conexão aberta!");
        connection.close();*/
        
        /*Data dat1 = new Data(25, 07, 2004);
        Aluno aluno1 = new Aluno("Arthur", "53101976804", dat1, 45.5, 1.75);
        
        AlunoDAO add = new AlunoDAO(); 
        add.adicionaAluno(aluno1);*/
        
        //new Login();
        
        new AlterarPeso("53101976804");
        
        
    }
    
}
