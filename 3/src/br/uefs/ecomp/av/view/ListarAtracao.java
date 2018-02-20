package br.uefs.ecomp.av.view;

import br.uefs.ecomp.av.controller.ControllerUsuario;
import br.uefs.ecomp.av.model.Atracao;
import br.uefs.ecomp.av.model.Local;
import br.uefs.ecomp.av.model.exception.DadoInexistenteException;
import br.uefs.ecomp.av.util.Iterador;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Classe ListarAtracao, responsável pela tela de interface gráfica do usuário
 * ao listar atrações no programa Assistente de Viagem.
 *
 * @author Camille Jesus e Rodrigo Santos
 */
public class ListarAtracao {

    private TelaLogin telaL = new TelaLogin();
    private JFrame janela = new JFrame("Assistente de Viagem©  -  Listar Atrações");
    private Container painelPrincipal = new JPanel(new GridLayout(3, 1));
    private final Container primeiro = new JPanel();
    private final Container segundo = new JPanel();
    private Label Local = new Label("Local: ");
    private Label Ordem = new Label("Ordem:   ");
    private JTextField local = new JTextField(null, 37);
    private ButtonGroup tipoListar = new ButtonGroup();
    private JRadioButton alfabetica = new JRadioButton("Alfabética");
    private JRadioButton pontuacao = new JRadioButton("Pontuação");
    private JButton listar = new JButton("Listar");
    private JButton limpar = new JButton("Limpar");
    private JButton voltar = new JButton("Voltar");
    private LogadoUsuario logadoU = new LogadoUsuario();
    private JList lista = new JList();
    private DefaultListModel model = new DefaultListModel();
    private JScrollPane scroll = new JScrollPane(lista);

    /**
     * Método que prepara a janela, posicionando os objetos previamente
     * inicializados na tela.
     */
    public void preparaTela() {
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setSize(540, 540);
        janela.setResizable(false);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.add(painelPrincipal);
        painelPrincipal.add(primeiro);
        painelPrincipal.add(segundo);
        primeiro.add(Local);
        primeiro.add(local);
        primeiro.add(Ordem);
        tipoListar.add(alfabetica);
        primeiro.add(alfabetica);
        tipoListar.add(pontuacao);
        primeiro.add(pontuacao);
        primeiro.add(listar);
        primeiro.add(limpar);
        primeiro.add(voltar);
        segundo.add(scroll);
        preparaBotaoListar();
        preparaBotaoLimpar();
        preparaBotaoVoltar();
    }

    /**
     * Método que prepara o botão de listar atrações.
     */
    private void preparaBotaoListar() {
        listar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Local l = telaL.getThisUsuario().buscarLocal(local.getText());

                    if (alfabetica.isSelected()) {
                        Iterador it = l.listarAtracoesOrdemAlfabetica();

                        while (it.temProximo()) {
                            model.addElement(((Atracao) it.obterProximo()).getNome());
                        }
                    } else if (pontuacao.isSelected()) {
                        Iterador it = l.listarAtracoesOrdemPontuacaoMedia();

                        while (it.temProximo()) {
                            model.addElement(((Atracao) it.obterProximo()).getNome());
                        }
                    }
                    lista.setModel(model);

                } catch (DadoInexistenteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO DE BUSCA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Método que prepara botão que limpa os campos de preenchimento de listar
     * atrações.
     */
    public void preparaBotaoLimpar() {
        limpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                local.setText(null);
                tipoListar.clearSelection();
                model.removeAllElements();
            }
        });
    }

    /**
     * Método que prepara botão que volta à tela de usuário.
     */
    private void preparaBotaoVoltar() {
        voltar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                janela.setVisible(false);
                logadoU.preparaTela();
            }
        });
    }

    private void preparaDuploClique() {
        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int indice = lista.locationToIndex(e.getPoint());

                    JFrame j = new JFrame("Assistente de Viagem©  -  Listar Atrações");
                    Container p = new JPanel(new GridLayout(1, 1));
                    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    j.pack();
                    j.setSize(100, 100);
                    j.setVisible(true);
                    j.add(p);

                }
            }
        });

    }
}
