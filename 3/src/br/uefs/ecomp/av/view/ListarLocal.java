package br.uefs.ecomp.av.view;


import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.model.Local;
import br.uefs.ecomp.av.util.Iterador;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Classe ListarLocal, responsável pela tela de interface gráfica do operador ao
 * listar locais no programa Assistente de Viagem.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class ListarLocal {
    
    private TelaLogin telaL = new TelaLogin();
    private JFrame janela = new JFrame("Assistente de Viagem©  -  Listar Locais");
    private Container painelPrincipal = new JPanel(new GridLayout(2, 1));
    private final Container primeiro = new JPanel();
    private final Container segundo = new JPanel();
    private JLabel Locais = new JLabel("Locais:");
    private JButton voltar = new JButton("Voltar");
    private LogadoOperador logadoO = new LogadoOperador();
    private JList lista = new JList();
    private DefaultListModel model = new DefaultListModel();
    private JScrollPane scroll = new JScrollPane(lista);
 
    /** Método que prepara a janela, posicionando os objetos previamente inicializados
     * na tela.
     */
    public void preparaTela() {;
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setSize(540, 540);
        janela.setResizable(false);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.add(painelPrincipal);
        painelPrincipal.add(primeiro);
        painelPrincipal.add(segundo);
        primeiro.add(scroll);
        segundo.add(voltar);
        preparaBotaoListar();
        preparaBotaoVoltar();
        
    }

    /** Método que prepara o botão de listar locais.
     */
    private void preparaBotaoListar() {
        Iterador it = telaL.getThisOperador().listarLocais();

        while (it.temProximo()) {
            model.addElement(((Local) it.obterProximo()).getNome());
        }
        lista.setModel(model);
    }

    /** Método que prepara botão que volta à tela de operador.
     */
    private void preparaBotaoVoltar() {
        voltar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.setVisible(false);
                logadoO.preparaTela();
            }
        });
    }
}