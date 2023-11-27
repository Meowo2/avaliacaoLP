package gui;

import academia.ConnectionAcademia;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class HistoricoPesoTable extends JFrame {

    private JTable historicoTable;
    private JScrollPane scrollPane;
    private JButton jButtonVoltar;
    private String cpf;

    public HistoricoPesoTable(String cpf) {
        super("Histórico de Pesos");

        this.cpf = cpf;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        historicoTable = new JTable();
        scrollPane = new JScrollPane(historicoTable);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        add(scrollPane);
        
        jButtonVoltar = new JButton("Voltar");
        jButtonVoltar.setBounds(10, 10, 10, 10);
        jButtonVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

                new AlterarPeso(cpf);

                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });
        add(jButtonVoltar, BorderLayout.SOUTH);

        preencherTabela();

        

    }

    private void preencherTabela() {
        Vector<Vector<Object>> data = new Vector<>(); //armazenar dados na tabela
        Vector<String> columnNames = new Vector<>(); //armazenar nomes das colunas na tabela
        columnNames.add("CPF");
        columnNames.add("Peso (Kg)");
        columnNames.add("Data e Hora"); //adiciona colunas

        try {
            Connection connection = new ConnectionAcademia().getConnection();

            String query = "SELECT * FROM historico WHERE alu_cpf =" + cpf;
            try (PreparedStatement pstmt = connection.prepareStatement(query); //executa instruções sql
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Vector<Object> row = new Vector<>(); //vetor para representar as linhas
                    row.add(rs.getString("alu_cpf")); //adicionando linhas
                    row.add(rs.getDouble("his_peso"));

                    // formatando Timestamp para uma String
                    Timestamp dataHoraTimestamp = rs.getTimestamp("his_dataHora");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //formato no historico
                    String dataHora = dateFormat.format(dataHoraTimestamp);

                    row.add(dataHora);
                    data.add(row); //adicionando as colunas no vetor data
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames); //modelo de tabela padrão
        historicoTable.setModel(model); 
    }
         
    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(HistoricoPesoTable::new);
    }*/
}




