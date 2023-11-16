package gui;

import javax.swing.*;
import dao.AlunoDAO;
import model.Aluno;
import model.Data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela extends JFrame {

    private JLabel labelTitle, labelName, labelDataNascimento, labelCPF, labelPeso, labelAltura;
    private JTextField textFieldName, textFieldDataNascimento, textFieldCPF, textFieldPeso, textFieldAltura;
    private JButton buttonUpdatePeso, buttonSubmit;

    public Tela() {
        setTitle("Registro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Using absolute positioning (not recommended for complex layouts)

        labelTitle = new JLabel("Registro");
        labelTitle.setBounds(150, 10, 100, 30);
        add(labelTitle);

        labelName = new JLabel("Nome:");
        labelName.setBounds(20, 50, 100, 20);
        add(labelName);

        textFieldName = new JTextField();
        textFieldName.setBounds(120, 50, 200, 20);
        add(textFieldName);

        labelDataNascimento = new JLabel("Data de Nascimento:");
        labelDataNascimento.setBounds(20, 80, 120, 20);
        add(labelDataNascimento);

        textFieldDataNascimento = new JTextField();
        textFieldDataNascimento.setBounds(150, 80, 170, 20);
        add(textFieldDataNascimento);

        labelCPF = new JLabel("CPF:");
        labelCPF.setBounds(20, 110, 100, 20);
        add(labelCPF);

        textFieldCPF = new JTextField();
        textFieldCPF.setBounds(120, 110, 200, 20);
        add(textFieldCPF);

        labelPeso = new JLabel("Peso:");
        labelPeso.setBounds(20, 140, 100, 20);
        add(labelPeso);

        textFieldPeso = new JTextField();
        textFieldPeso.setBounds(120, 140, 200, 20);
        add(textFieldPeso);

        labelAltura = new JLabel("Altura:");
        labelAltura.setBounds(20, 170, 100, 20);
        add(labelAltura);

        textFieldAltura = new JTextField();
        textFieldAltura.setBounds(120, 170, 200, 20);
        add(textFieldAltura);
        
        buttonUpdatePeso = new JButton("Alterar Peso");
        buttonUpdatePeso.setBounds(50, 220, 120, 30);
        add(buttonUpdatePeso);

        buttonSubmit = new JButton("Enviar dados");
        buttonSubmit.setBounds(200, 220, 120, 30);

        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nascimento = textFieldDataNascimento.getText();
                String nome = textFieldName.getText();
                String cpf = textFieldCPF.getText();
                String peso = textFieldPeso.getText();
                String altura = textFieldAltura.getText();

                    Data dat1 = new Data(Integer.parseInt(textFieldDataNascimento.getText().substring(0, 2)),  //dia
                    Integer.parseInt(textFieldDataNascimento.getText().substring(2, 4)),    //mes
                    Integer.parseInt(textFieldDataNascimento.getText().substring(4, 8)));   //ano
            
                    Aluno aluno1 = new Aluno(
                    textFieldName.getText(), 
                    textFieldCPF.getText(), 
                    dat1,       
                    Double.parseDouble(textFieldPeso.getText()), 
                    Double.parseDouble(textFieldPeso.getText())
            );
            
            AlunoDAO add = new AlunoDAO(); 
            add.adicionaAluno(aluno1);
                                          
                JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
            }
        });
        add(buttonSubmit);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tela());
    }
}
