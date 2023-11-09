/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package principal;

import academia.ConnectionAcademia;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Manhã
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Connection connection = new ConnectionAcademia().getConnection();
         System.out.println("Conexão aberta!");
         connection.close();
        
    }
    
}
