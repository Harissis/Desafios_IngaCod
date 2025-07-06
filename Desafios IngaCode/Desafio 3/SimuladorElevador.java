import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;
import java.util.LinkedList;

public class SimuladorElevador {

    private static JLabel elevador;
    private static JLabel textoAndar;
    private static Queue<Integer> filaDestinos = new LinkedList<>();
    private static int andarAtual = 0;
    private static boolean elevadorMovendo = false;
    private static final int alturaAndar = 100;
    private static final int totalAndares = 6;

    public static void main(String[] args) {

        JFrame janela = new JFrame("Simulador");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(400, 650);
        janela.setLayout(new BorderLayout());

        elevador = new JLabel("Elevador");
        elevador.setOpaque(true);
        elevador.setBackground(Color.ORANGE);
        elevador.setHorizontalAlignment(SwingConstants.CENTER);
        elevador.setBounds(100, alturaAndar * (totalAndares - 1), 80, 30);

        textoAndar = new JLabel("Andar atual: 0");
        textoAndar.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel botoesPainel = new JPanel(new GridLayout(totalAndares, 1));
        botoesPainel.setBackground(Color.GRAY);

        for (int i = totalAndares - 1; i >= 0; i--) {
            JButton botao = new JButton("Chamar Andar " + i);
            botao.setActionCommand("andar:" + i);
            botao.addActionListener(ouvinteChamar);
            botoesPainel.add(botao);
        }

        JPanel painelCentro = new JPanel(null);
        painelCentro.setPreferredSize(new Dimension(200, totalAndares * alturaAndar));
        painelCentro.setBackground(Color.LIGHT_GRAY);
        painelCentro.add(elevador);

        janela.add(botoesPainel, BorderLayout.WEST);
        janela.add(painelCentro, BorderLayout.CENTER);
        janela.add(textoAndar, BorderLayout.SOUTH);

        janela.setVisible(true);
    }

    private static ActionListener ouvinteChamar = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();
            int andarChamado = Integer.parseInt(comando.split(":" )[1]);
            adicionarDestino(andarChamado);
        }
    };

    private static void adicionarDestino(int andar) {
        if (!filaDestinos.contains(andar)) {
            filaDestinos.add(andar);
            processarParada();
        }
    }

    private static void processarParada() {
        if (elevadorMovendo || filaDestinos.isEmpty()) return;

        int proximo = filaDestinos.poll();
        elevadorMovendo = true;

        Timer timer = new Timer(500, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (andarAtual < proximo) {
                    andarAtual++;
                } else if (andarAtual > proximo) {
                    andarAtual--;
                }

                moverElevador(andarAtual);
                textoAndar.setText("Andar atual: " + andarAtual);

                if (andarAtual == proximo) {
                    timer.stop();
                    Toolkit.getDefaultToolkit().beep();

                    new Timer(5000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            elevadorMovendo = false;
                            if (filaDestinos.isEmpty() && andarAtual != 0) {
                                filaDestinos.add(0);
                            }
                            processarParada();
                        }
                    }) {{ setRepeats(false); }}.start();
                }
            }
        });
        timer.start();
    }

    private static void moverElevador(int andar) {
        int y = alturaAndar * (totalAndares - 1 - andar);
        elevador.setLocation(100, y);
    }
}
