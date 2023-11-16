package model;



public class Historico {

        private String cpf;
        private String dataEHora;
        
        public Historico(String cpf, String dataEHora) {
            this.cpf = cpf;
            this.dataEHora = dataEHora;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getDataEHora() {
            return dataEHora;
        }

        public void setDataEHora(String dataEHora) {
            this.dataEHora = dataEHora;
        }
    
    
       
    
    }


