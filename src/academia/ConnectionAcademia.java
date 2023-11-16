/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Manhã
 */
public class ConnectionAcademia {
 
    public Connection getConnection() {
    
                 try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/academia","root","ju070205");
		 }         
		 catch(SQLException excecao) {
			throw new RuntimeException(excecao);
		 }
     }
    
}
