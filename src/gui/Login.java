package gui;

import dao.AlunoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Login extends JFrame {

    private JLabel jLabelTitle, jLabelCPF; 
    private JTextField jTextCPF;
    private JButton buttonLogar, buttonCadastrar;
    
    public Login(){
        
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 
        setLocationRelativeTo(null);


        jLabelTitle = new JLabel("Login");
        jLabelTitle.setBounds(150, 10, 100, 30);
        add(jLabelTitle);

        jLabelCPF = new JLabel("Informe seu CPF:");
        jLabelCPF.setBounds(50, 80, 100, 20);
        add(jLabelCPF);

        jTextCPF = new JTextField();
        jTextCPF.setBounds(150, 80, 100, 20);
        add(jTextCPF);

        buttonLogar = new JButton("Entrar");
        buttonLogar.setBounds(130, 150, 120, 30);
        
        buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.setBounds(130, 200, 120, 30);

        buttonLogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //condição de autenticação
                AlunoDAO aluno = new AlunoDAO();
                
                if (aluno.cpfExiste(jTextCPF.getText())) {
                    dispose();
                    new AlterarPeso(jTextCPF.getText());
                }else{      
                JOptionPane.showMessageDialog(null, "CPF não cadastrado");
                }
                 throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                }
        });
        
        buttonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //condição de autenticação
                       
                dispose();
                new Tela();
       
                 throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                }
        });
        
        add(buttonLogar);
        add(buttonCadastrar);
        setVisible(true);

    }
    
}
