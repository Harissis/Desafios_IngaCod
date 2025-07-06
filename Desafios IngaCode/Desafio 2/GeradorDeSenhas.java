import javax.swing.*;
import java.util.Random;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

public class GeradorDeSenhas {

    public static void main(String[] args) {

        JFrame janela = new JFrame("Gerador de Senhas");
        janela.setSize(400, 450);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null); // Centraliza a janela


        JLabel User = new JLabel("<html>Bem-vindo ao Gerador de Senhas!<br>Digite o Tamanho da Senha:<html> ");
        User.setBounds(90, 10, 300, 30);


        JTextField TamanhoSenha = new JTextField();
        TamanhoSenha.setBounds(100, 50, 200, 30);


        JCheckBox cbMaiusculas = new JCheckBox("Incluir letras maiúsculas");
        cbMaiusculas.setBounds(100, 90, 200, 20);

        JCheckBox cbMinusculas = new JCheckBox("Incluir letras minúsculas");
        cbMinusculas.setBounds(100, 115, 200, 20);

        JCheckBox cbNumeros = new JCheckBox("Incluir números");
        cbNumeros.setBounds(100, 140, 200, 20);

        JCheckBox cbSimbolos = new JCheckBox("Incluir símbolos");
        cbSimbolos.setBounds(100, 165, 200, 20);

        JCheckBox cbEmojis = new JCheckBox("Incluir emojis");
        cbEmojis.setBounds(100, 190, 200, 20);


        JTextField campoSenha = new JTextField();
        campoSenha.setEditable(false);
        campoSenha.setBounds(100, 270, 200, 30);

        JButton Botao = new JButton("Gerar Senha");
        Botao.setBounds(130, 230, 130, 30);

        Botao.addActionListener(e -> {
            int Tamanho;
            try {
                Tamanho = Integer.parseInt(TamanhoSenha.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(janela, "Digite um número válido!");
                return;
            }

            String ConjuntoFinal = "";

            if (cbMaiusculas.isSelected()) ConjuntoFinal += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            if (cbMinusculas.isSelected()) ConjuntoFinal += "abcdefghijklmnopqrstuvwxyz";
            if (cbNumeros.isSelected()) ConjuntoFinal += "0123456789";
            if (cbSimbolos.isSelected()) ConjuntoFinal += "!@#$%&*";
            if (cbEmojis.isSelected()) ConjuntoFinal += "😀😁😂😎🔥❤️";

            if (ConjuntoFinal.isEmpty()) {
                JOptionPane.showMessageDialog(janela, "Selecione pelo menos uma opção!");
                return;
            }

            Random random = new Random();
            StringBuilder senha = new StringBuilder();

            for (int i = 0; i < Tamanho; i++) {
                int index = random.nextInt(ConjuntoFinal.length());
                senha.append(ConjuntoFinal.charAt(index));
            }

            campoSenha.setText(senha.toString());
        });


        JButton botaoCopiar = new JButton("Copiar Senha");
        botaoCopiar.setBounds(130, 310, 130, 30);

        botaoCopiar.addActionListener(e -> {
            String senha = campoSenha.getText();
            if (!senha.isEmpty()) {
                StringSelection selecao = new StringSelection(senha);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selecao, null);
                JOptionPane.showMessageDialog(janela, "Senha copiada para a área de transferência!");
            } else {
                JOptionPane.showMessageDialog(janela, "Nenhuma senha foi gerada ainda.");
            }
        });


        janela.add(User);
        janela.add(TamanhoSenha);
        janela.add(cbMaiusculas);
        janela.add(cbMinusculas);
        janela.add(cbNumeros);
        janela.add(cbSimbolos);
        janela.add(cbEmojis);
        janela.add(Botao);
        janela.add(campoSenha);
        janela.add(botaoCopiar);

        janela.setVisible(true);
    }
}