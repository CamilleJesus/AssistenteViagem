package br.uefs.ecomp.av.view;


import br.uefs.ecomp.av.model.Atracao;
import br.uefs.ecomp.av.model.Avaliacao;
import br.uefs.ecomp.av.model.exception.DadoInexistenteException;
import br.uefs.ecomp.av.util.Iterador;

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
import javax.swing.JTextField;

/**
 * Classe ListarAvaliacao, responsável pela tela de interface gráfica do usuário
 * ao listar avaliações no programa Assistente de Viagem.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class ListarAvaliacao {

    
    private TelaLogin telaL = new TelaLogin();
    private JFrame janela = new JFrame("Assistente de Viagem©  -  Listar Avaliações");
    private Container painelPrincipal = new JPanel(new GridLayout(3, 1));
    private final Container primeiro = new JPanel();
    private final Container segundo = new JPanel();
    private Label Atracao = new Label("Atração: ");
    private JTextField atracao = new JTextField(null, 37);
    private JButton listar = new JButton("Listar");
    private JButton limpar = new JButton("Limpar");
    private JButton voltar = new JButton("Voltar");
    private JList lista = new JList();
    private DefaultListModel model = new DefaultListModel();
    private JScrollPane scroll = new JScrollPane(lista);
    private LogadoUsuario logadoU = new LogadoUsuario();
    
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
        primeiro.add(Atracao);
        primeiro.add(atracao);
        primeiro.add(listar);
        primeiro.add(limpar);
        primeiro.add(voltar);        
        segundo.add(scroll);
        preparaBotaoListar();
        preparaBotaoLimpar();
        preparaBotaoVoltar();
        
    }

    /** Método que prepara o botão de listar avaliações.
     */
    private void preparaBotaoListar() {
        listar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                lista.setModel(model);
                try{                   
                    Atracao a = telaL.getThisUsuario().buscarAtracao(atracao.getText());                        
                    Iterador it = a.listarAvaliacoes();                        
                    
                    while (it.temProximo()) {
                        Avaliacao avaliacao = (Avaliacao) it.obterProximo();
                        model.addElement("Usuário:  " + avaliacao.getAvaliador().getNomeCompleto());            
                        model.addElement("Data:  " + avaliacao.getData());
                        model.addElement("Título:  " + avaliacao.getTitulo());
                        model.addElement("Texto:  " + avaliacao.getTexto());
                        model.addElement(" ");
                    }
                    
                } catch(DadoInexistenteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO DE BUSCA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    /** Método que prepara botão que limpa os campos de preenchimento de listar
     * avaliações.
     */
    public void preparaBotaoLimpar() {
        limpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                atracao.setText(null);
                model.removeAllElements();
            }
        });
    }
                
    /** Método que prepara botão que volta à tela de usuário.
     */
    private void preparaBotaoVoltar() {
        voltar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.setVisible(false);
                try {
                    telaL.getThisOperador().salvarEstadoSistema();
                } catch (Exception ex) {
                    Logger.getLogger(ListarAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
                }
                logadoU.preparaTela();
            }
        });
    }
}