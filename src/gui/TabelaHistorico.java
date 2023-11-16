package gui;

import model.Historico;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class TabelaHistorico extends javax.swing.JFrame {

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;    
    private DefaultTableModel defalultTableModel;
    private Object[] colunas;
    private Object[] itens;
    
    private Historico historico;
    
	private TabelaHistorico tabelaHistorico;
	
    String cpf;
	
	public void setTabelaHistorico(TabelaHistorico tabelaHistorico) {
		this.tabelaHistorico = tabelaHistorico;
	}
    
    public void setHistorico(Historico historico) {
    	this.historico = historico;
    }
    
    public List<Historico> dados = new ArrayList<>();
    public TabelaHistorico() {
        initComponents();
        init(); 
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        JButton jBVoltar = new JButton("Voltar");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jScrollPane1.setViewportView(tabela);
        
        jBVoltar.setText("Voltar");
        jBVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
         
				tabelaHistorico.dispose();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jBVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                        .addContainerGap())))
        		);
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
    
        );

        pack();
 
	    	}
	    
		public void gerarTabela(){
		     colunas = new Object[]{"Data e hora", "CPF"};
		     itens = new Object[colunas.length];

		     // modelo
		     defalultTableModel = new DefaultTableModel(null, colunas);

			for (Historico dado : dados) {
				
		        //data e hora na primeira coluna
	             itens[0] = dado.getDataHora();
	            
	             // cpf na segunda coluna
	             itens[1] = dado.getAluId();
	             defalultTableModel.addRow(itens);
	        }

	        // adicionar modelo a tabela
	        tabela.setModel(defalultTableModel);
	        
		}

        private void init() {
            setLocationRelativeTo(null);
            setTitle("Tabela Historico");
        }

        public static void main(String[] args) {
 
    } 

}
