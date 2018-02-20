package br.uefs.ecomp.av.view;


import br.uefs.ecomp.av.model.Avaliacao;
import br.uefs.ecomp.av.model.exception.DadoInexistenteException;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Classe ProcessaAvaliacao, responsável pela tela de interface gráfica do
 * operador ao processar avaliações no programa Assistente de Viagem.
 *
 * @author Camille Jesus e Rodrigo Santos
 */
public class ProcessarAvaliacao {

    private TelaLogin telaL = new TelaLogin();
    private JFrame janela = new JFrame("Assistente de Viagem©  -  Processar Avaliações");
    private Container painelPrincipal = new JPanel(new GridLayout(2, 1));
    private final Container primeiro = new JPanel();
    private final Container segundo = new JPanel();
    private Label Avaliacoes = new Label("Avaliações:");
    private JButton aceitar = new JButton("Aceitar");
    private JButton rejeitar = new JButton("Rejeitar");
    private JButton voltar = new JButton("Voltar");
    private LogadoOperador logadoO = new LogadoOperador();
    private JList lista = new JList();
    private DefaultListModel model = new DefaultListModel();
    private JScrollPane scroll = new JScrollPane(lista);
    private Avaliacao avaliacao;

    /**
     * Método que prepara a janela, posicionando os objetos previamente
     * inicializados na tela.
     */
    public void preparaTela() {;
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setSize(540, 540);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);
        janela.setVisible(true);
        janela.add(painelPrincipal);
        painelPrincipal.add(primeiro);
        painelPrincipal.add(segundo);
        primeiro.add(Avaliacoes);
        primeiro.add(scroll);
        segundo.add(aceitar);
        segundo.add(rejeitar);
        segundo.add(voltar);
        processa();
        preparaBotaoAceitar();
        preparaBotaoRecusar();
        preparaBotaoVoltar();

    }

    /**
     * Método que mostra as avaliações a serem processadas.
     */
    private void processa() {

        try {
            model.removeAllElements();
            avaliacao = telaL.getThisOperador().processarProximaAvaliacao();            
            model.addElement(avaliacao.getAvaliador().getNomeCompleto());            
            model.addElement(avaliacao.getData());
            model.addElement(avaliacao.getTitulo());
            model.addElement(avaliacao.getTexto());
            lista.setModel(model);
        } catch (DadoInexistenteException ex) {
            JOptionPane.showMessageDialog(null, "Sem avaliações a processar!");
        }
        
    }

    /**
     * Método que prepara botão que volta à tela de operador.
     */
    private void preparaBotaoVoltar() {
        voltar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    telaL.getThisOperador().salvarEstadoSistema();
                } catch (Exception ex) {
                    Logger.getLogger(ProcessarAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
                }
                janela.setVisible(false);
                logadoO.preparaTela();
            }
        });
    }

    private void preparaBotaoAceitar() {
        aceitar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                telaL.getThisOperador().aceitarAvaliacao();
                processa();
            }
        });
    }

    private void preparaBotaoRecusar() {
         rejeitar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                telaL.getThisOperador().rejeitarAvaliacao();
        processa();
            }
        });
        
    }
}
