package gui;

import academia.ConnectionAcademia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Historico;


public class AlterarPeso extends JFrame {

    private String aluCpf;
    private JLabel jLabelTitle, jLabelNovoPeso; 
    private JTextField jTextNovoPeso;
    private JButton buttonSavePeso, buttonHistorico;

    
    public AlterarPeso(String cpf){
        this.aluCpf = cpf;
        
        setTitle("Alterar Peso");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 

        jLabelTitle = new JLabel("Alterar Peso");
        jLabelTitle.setBounds(150, 10, 100, 30);
        add(jLabelTitle);

        jLabelNovoPeso = new JLabel("Informe o novo peso:");
        jLabelNovoPeso.setBounds(90, 80, 150, 20);
        add(jLabelNovoPeso);

        jTextNovoPeso = new JTextField();
        jTextNovoPeso.setBounds(90, 100, 200, 20);
        add(jTextNovoPeso);

        buttonSavePeso = new JButton("Salvar");
        buttonSavePeso.setBounds(130, 150, 120, 30);
        buttonSavePeso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Connection connection = new ConnectionAcademia().getConnection();
                Historico registrar = new Historico(aluCpf,Double.parseDouble(jTextNovoPeso.getText()));
                
                String sql = "INSERT INTO historico(alu_cpf, his_peso, his_dataHora) VALUE(?,?,?)";
                String updateCadastro = "UPDATE cadastro SET alu_peso = ? WHERE alu_cpf = ?";
                try { 
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    stmt.setString(1, registrar.getAluCpf());
                    stmt.setDouble(2, registrar.getPeso());
                    stmt.setString(3, registrar.getDataHora());
                    stmt.execute();
                    stmt.close();
                    
                    PreparedStatement stmt2 = connection.prepareStatement(updateCadastro);
                    stmt2.setDouble(1, registrar.getPeso());
                    stmt2.setString(2, registrar.getAluCpf());
                    stmt2.execute();
                    stmt2.close();
                    
                    JOptionPane.showMessageDialog(null, "Peso alterado com sucesso!");
                } 
                catch (SQLException u) { 
                    JOptionPane.showMessageDialog(null, "Não foi possível registrar dados no histórico");
                    throw new RuntimeException(u);
                }  
                
            }
        });
        add(buttonSavePeso);

        buttonHistorico = new JButton("Ver Histórico");
        buttonHistorico.setBounds(130, 200, 120, 30);
        buttonHistorico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
               TabelaHistorico tabelaHistorico = new TabelaHistorico();

               throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");


            }
        });
        add(buttonHistorico);

        setVisible(true);

    }
}
