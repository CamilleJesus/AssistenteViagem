package br.uefs.ecomp.av.view;


import br.uefs.ecomp.av.controller.ControllerUsuario;
import br.uefs.ecomp.av.model.Atracao;
import br.uefs.ecomp.av.model.exception.DadoInexistenteException;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Classe BuscarAtracao, responsável pela tela da interface gráfica de usuário ao
 * buscar atração no programa Assistente de Viagem.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class BuscarAtracao {
    private TelaLogin telaL = new TelaLogin();
    private JFrame janela = new JFrame("Assistente de Viagem©  -  Buscar Atração");
    private Container painelPrincipal = new JPanel(new GridLayout(3, 1));
    private final Container primeiro = new JPanel();
    private final Container segundo = new JPanel();
    private final Container terceiro = new JPanel();    
    private Label Atracao = new Label("Atração:");
    private JButton buscar = new JButton("Buscar");
    private JButton limpar = new JButton("Limpar");
    private JButton voltar = new JButton("Voltar");
    private JTextField atracao = new JTextField(null, 37);
    private final JTextArea area = new JTextArea(30, 30);
    private JScrollPane scroll = new JScrollPane(area);
    private LogadoUsuario logadoU = new LogadoUsuario();

    /** Método que prepara a janela, posicionando os objetos previamente inicializados
     * na tela.
     */
    void preparaTela() {
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setSize(540, 540);
        janela.setResizable(false);
        janela.setVisible(true);
        janela.add(painelPrincipal);
        janela.setLocationRelativeTo(null);
        painelPrincipal.add(primeiro);
        painelPrincipal.add(segundo);
        painelPrincipal.add(terceiro);
        primeiro.add(Atracao);
        primeiro.add(atracao);
        primeiro.add(buscar);
        primeiro.add(limpar);
        segundo.add(scroll);
        area.setEditable(false);
        terceiro.add(voltar);
        preparaBotaoBuscar();
        preparaBotaoLimpar();
        preparaBotaoVoltar();
    }
    
    /** Método que prepara o botão de buscar atração.
     */
     private void preparaBotaoBuscar() {
        buscar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    Atracao a = telaL.getThisUsuario().buscarAtracao(atracao.getText());
                    area.setText("Local: " + a.getNome() + "\nNome: " +a.getLocal().getNome()
                        + "\nEstado: " + a.getLocal().getEstado() + "\nPaís: "
                        + a.getLocal().getPais() + "\nBairro: " + a.getBairro() + "\n");
                } catch (DadoInexistenteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO DE BUSCA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    /** Método que prepara botão que limpa os campos de preenchimento de buscar
     * atração.
     */
    public void preparaBotaoLimpar() {
        limpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                atracao.setText(null);
                area.setText(null);
            }
        });
    }
    
    /** Método que prepara botão que volta à tela de usuário.
     */
     private void preparaBotaoVoltar() {
        voltar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (voltar.isShowing()) {
                    janela.setVisible(false);
                    logadoU.preparaTela();
                }
            }
        });
    }
}