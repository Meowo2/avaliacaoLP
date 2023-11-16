package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Login extends JFrame {

    private JLabel jLabelTitle, jLabelCPF; 
    private JTextField jTextCPF;
    private JButton buttonUpdatePeso;
    
    public Login(){
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 

        jLabelTitle = new JLabel("Login");
        jLabelTitle.setBounds(150, 10, 100, 30);
        add(jLabelTitle);

        jLabelCPF = new JLabel("Informe seu CPF:");
        jLabelCPF.setBounds(50, 80, 100, 20);
        add(jLabelCPF);

        jTextCPF = new JTextField();
        jTextCPF.setBounds(150, 80, 100, 20);
        add(jTextCPF);

        buttonUpdatePeso = new JButton("Entrar");
        buttonUpdatePeso.setBounds(130, 150, 120, 30);

        buttonUpdatePeso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //condição de autenticação
                       
                AlterarPeso alterarPeso = new AlterarPeso();
       
                 throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                }
        });
        add(buttonUpdatePeso);

        setVisible(true);

    }
    
}
