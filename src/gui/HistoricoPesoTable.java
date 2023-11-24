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
        Vector<Vector<Object>> data = new Vector<>();
        Vector<String> columnNames = new Vector<>();
        columnNames.add("CPF");
        columnNames.add("Peso (Kg)");
        columnNames.add("Data e Hora");

        try {
            Connection connection = new ConnectionAcademia().getConnection();

            String query = "SELECT * FROM historico WHERE alu_cpf =" + cpf;
            try (PreparedStatement pstmt = connection.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(rs.getString("alu_cpf"));
                    row.add(rs.getDouble("his_peso"));

                    // Formatando Timestamp para uma String
                    Timestamp dataHoraTimestamp = rs.getTimestamp("his_dataHora");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dataHora = dateFormat.format(dataHoraTimestamp);

                    row.add(dataHora);
                    data.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        historicoTable.setModel(model);
    }
         
    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(HistoricoPesoTable::new);
    }*/
}




