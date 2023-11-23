package gui;

import academia.ConnectionAcademia;
import com.mysql.cj.xdevapi.Statement;
import dao.AlunoDAO;
import dao.HistoricoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Aluno;
import model.Data;
import model.Historico;


public class AlterarPeso extends JFrame {

    private String aluCpf;
    private JLabel jLabelTitle, jLabelNovoPeso; 
    private JTextField jTextNovoPeso;
    private JButton buttonSavePeso, buttonHistorico, bottonCalcularImc, bottonExcluir, bottonConsultar, bottonVoltar;

    
    public AlterarPeso(String cpf){
        this.aluCpf = cpf;
        
        setLocationRelativeTo(null);
        setTitle("Alterar Peso");
        setSize(400, 350);
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

        
        bottonVoltar = new JButton("Voltar");
        bottonVoltar.setBounds(10, 10, 70, 30);
        bottonVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
            }
        });
        add(bottonVoltar);
        
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
        buttonHistorico.setBounds(200, 200, 120, 30);
        buttonHistorico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HistoricoPesoTable();
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");

            }
        });
        add(buttonHistorico);
        
        
        bottonExcluir = new JButton("Excluir cadastro");
        bottonExcluir.setBounds(35, 250, 150, 30);
        bottonExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                AlunoDAO dao = new AlunoDAO();
                dao.excluirAlunoPorCpf(cpf);
                JOptionPane.showMessageDialog(null, "Aluno excluido com sucesso!");
                dispose();
                new Login();
            }
        });
        add(bottonExcluir);
        
        
        bottonConsultar = new JButton("Consultar Cadastro");
        bottonConsultar.setBounds(195, 250, 150, 30);
        bottonConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                AlunoDAO dao = new AlunoDAO();
                JOptionPane.showMessageDialog(null, dao.consultarPorCpf(cpf));
                
            }
        });
        add(bottonConsultar);
        
        
        bottonCalcularImc = new JButton("Calcular IMC");
        bottonCalcularImc.setBounds(60, 200, 120, 30);
        bottonCalcularImc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd-MM-uuuu");
                String dataFormatada = formatterData.format(LocalDateTime.now());  //formata data atual
                DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
                String horaFormatada = formatterHora.format(LocalDateTime.now());   //formata hora atual
                
                
                DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("uuuu");
                String anoAtualString = formatterYear.format(LocalDateTime.now());  //formata ano
                
                int anoAtual = Integer.parseInt(anoAtualString); 
                Double peso = 0.0;
                Double altura = 0.0;
                int ano = 0000;
                String interpretacao;
                
                Connection connection = new ConnectionAcademia().getConnection();
                ResultSet rs;
                try {
                    rs = connection.prepareStatement("SELECT alu_peso, alu_data_nascimento, alu_altura FROM cadastro WHERE alu_cpf = "+ cpf).executeQuery();

                    rs.next();
                    peso = rs.getDouble(1);
                    String dataNascimento = rs.getString(2);
                    altura = rs.getDouble(3);
                    //System.out.println(dataNascimento);
                    ano = Integer.parseInt(dataNascimento.substring(0,4));
                    

                } catch (SQLException ex) {
                    Logger.getLogger(AlterarPeso.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                int idade = anoAtual - ano;
                Double imc = peso/Math.pow((altura),2);

                //O switch não suporta conparaçoes
                if(imc<16){
                    interpretacao = "Magreza Grau 3";
                }else if(imc<=16.9){
                    interpretacao = "Magreza Grau 2";
                }else if(imc<=18.4){
                    interpretacao = "Magreza Grau 1";
                }else if(imc<=24.9){
                    interpretacao = "Eutrofia";
                }else if(imc<=29.9){
                    interpretacao = "Pré-Obesidade";
                }else if(imc<=34.9){
                    interpretacao = "Obesidade Grau 1";
                }else if(imc<=39.9){
                    interpretacao = "Obesidade Grau 2";
                }else{
                    interpretacao = "Obesidade Grau 3";
                }
                
                
                JFileChooser explorador_arq = new JFileChooser();
                //int resposta = explorador_arq.showOpenDialog(null);
                explorador_arq.setCurrentDirectory(new java.io.File("."));
                explorador_arq.setDialogTitle("Selecione Diretório");
                explorador_arq.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                explorador_arq.setAcceptAllFileFilterUsed(false);
                
                if(explorador_arq.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    File arquivoImc = new File(explorador_arq.getSelectedFile() + "\\CalculoIMC.txt");
                    

                    FileWriter fw;
                    try {
                        fw = new FileWriter(arquivoImc, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write("\n"+"\n"+"O Seu IMC(índice de massa corporal) com base na sua idade("+ idade +") "
                                + "e seu ultimo registro de peso("+ peso +"kg) esta em: "+ Double.toString(imc).substring(0,4) +"("+ interpretacao +")"
                                + ", Calculado em "+ dataFormatada + " " + horaFormatada );
                        bw.close();
                        PrintWriter out = new PrintWriter(bw);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(AlterarPeso.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }
            }
        });
        add(bottonCalcularImc);
        
        
        

        
        setVisible(true);

    }

}
