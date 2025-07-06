import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ConversorRomano {

    public static void main(String[] args) {

        JFrame janela = new JFrame("Conversor Romano");
        janela.setSize(400, 300);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);


        JLabel resultado = new JLabel();
        resultado.setBounds(100, 180, 200, 30);

        JLabel User = new JLabel("<html>Bem-vindo ao Conversor Romano!<br>Digite um número ou símbolo romano:</html>");
        User.setBounds(50, 20, 300, 40);


        JTextField Campo = new JTextField();
        Campo.setBounds(100, 80, 200, 30);
        JTextField resultadoCampo = new JTextField();
        resultadoCampo.setBounds(100, 220, 200, 30);

        JButton Botao = new JButton("Converter");
        Botao.setBounds(150, 130, 100, 30);


        janela.add(User);
        janela.add(Campo);
        janela.add(resultado);
        janela.add(resultadoCampo);


        janela.setVisible(true);

        Campo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                converterAutomaticamente();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                converterAutomaticamente();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

            public void converterAutomaticamente() {
                String entrada = Campo.getText();

                try {
                    int numero = Integer.parseInt(entrada);

                    if (numero == 1) {
                        resultadoCampo.setText("I");
                    } else if (numero == 5) {
                        resultadoCampo.setText("V");
                    } else if (numero == 10) {
                        resultadoCampo.setText("X");
                    } else if (numero == 50) {
                        resultadoCampo.setText("G");
                    } else if (numero == 100) {
                        resultadoCampo.setText("C");
                    } else if (numero == 500) {
                        resultadoCampo.setText("D");
                    } else if (numero == 1000) {
                        resultadoCampo.setText("M");
                    } else {
                        resultadoCampo.setText("Número não suportado");
                    }

                } catch (NumberFormatException ex) {
                    if (entrada.equalsIgnoreCase("I")) {
                        resultadoCampo.setText("1");
                    } else if (entrada.equalsIgnoreCase("V")) {
                        resultadoCampo.setText("5");
                    } else if (entrada.equalsIgnoreCase("X")) {
                        resultadoCampo.setText("10");
                    } else if (entrada.equalsIgnoreCase("G")) {
                        resultadoCampo.setText("50");
                    } else if (entrada.equalsIgnoreCase("C")) {
                        resultadoCampo.setText("100");
                    } else if (entrada.equalsIgnoreCase("D")) {
                        resultadoCampo.setText("500");
                    } else if (entrada.equalsIgnoreCase("M")) {
                        resultadoCampo.setText("1000");
                    } else {
                        resultadoCampo.setText("Símbolo inválido");
                    }
                }
            }
        });




    }
}
