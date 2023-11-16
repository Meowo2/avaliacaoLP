package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class AlterarPeso extends JFrame {

    private JLabel jLabelTitle, jLabelNovoPeso; 
    private JTextField jTextNovoPeso;
    private JButton buttonSavePeso, buttonHistorico;

    
    public AlterarPeso(){
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
