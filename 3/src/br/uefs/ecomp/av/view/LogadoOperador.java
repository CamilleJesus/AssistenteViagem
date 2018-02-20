package br.uefs.ecomp.av.view;


import br.uefs.ecomp.av.controller.ControllerOperador;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe LogaOperador, responsável pela tela de interface gráfica do operador ao
 * logar no programa Assistente de Viagem.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class LogadoOperador {
    
    private TelaLogin telaL = new TelaLogin();
    private JFrame janela = new JFrame("Assistente de Viagem©  -  Operador");
    private Container painelPrincipal = new JPanel((new FlowLayout(FlowLayout.CENTER, 4500, 10)));
    private JButton cLocal = new JButton    ("     Cadastrar Local    ");
    private JButton lLocal = new JButton    ("        Listar Locais       ");
    private JButton pAvaliacao = new JButton("Processar Avaliações");
    private JButton rBatch = new JButton    ("        Rodar Batch         ");
    private JButton salvar = new JButton    ("      Salvar Sistema      ");
    private JButton carregar = new JButton  ("    Carregar Sistema    ");
    private JButton sair = new JButton("Sair");
    private ControllerOperador operador = new ControllerOperador();

    /** Método que prepara a janela, posicionando os objetos previamente inicializados
     * na tela.
     */
    public void preparaTela() {
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setSize(540, 540);
        janela.setResizable(false);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.setContentPane(painelPrincipal);
        painelPrincipal.add(cLocal);
        painelPrincipal.add(lLocal);
        painelPrincipal.add(pAvaliacao);
        painelPrincipal.add(rBatch);
        painelPrincipal.add(salvar);
        painelPrincipal.add(carregar);
        painelPrincipal.add(sair);
        preparaBotaoSair();
        preparaBotaoCadastrar();
        preparaBotaoListarLocais();
        preparaBotaoProcessarAvaliacoes();
        prepararBotaoRodarBatch();
        preparaBotaoSalvar();
        preparaBotaoCarregar();        
    }

    /** Método que prepara o botão de cadastro de local, para deixá-lo disponível
     * no programa e chama a tela específica.
     */
    private void preparaBotaoCadastrar() {
        cLocal.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                if (cLocal.isShowing()) {
                    janela.setVisible(false);
                    new CadastrarLocal().preparaTela();
                }
            }
        });
    }
    
    /** Método que prepara o botão de listar locais e chama a tela específica.
     */
    private void preparaBotaoListarLocais() {
        lLocal.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
            
                if (lLocal.isShowing()){
                    janela.setVisible(false);
                    new ListarLocal().preparaTela();
                }
            }
        });
    }
    
    /** Método que prepara o botão de listar locais e chama a tela específica.
     */
    private void preparaBotaoProcessarAvaliacoes() {
        pAvaliacao.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
            
                if (pAvaliacao.isShowing()){
                    janela.setVisible(false);
                    new ProcessarAvaliacao().preparaTela();
                }
            }
        });
    }
    
    /** Método que prepara o botão de rodar batch, que (re)calcula ranking das
     * atrações de acordo com a média de pontos avaliados.
     */
    private void prepararBotaoRodarBatch() {
        rBatch.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                operador.recalcularRankingsAtracoes();
                JOptionPane.showMessageDialog(null, "Ranking calculado!");
            }
        });
    }

    /** Método que prepara o botão de salvar, que salva o sistema em arquivo.
     */
    private void preparaBotaoSalvar() {
        salvar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                if (salvar.isShowing()) {
                    
                    try {
                        operador.salvarEstadoSistema();
                        JOptionPane.showMessageDialog(null, "Salvado com sucesso!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao salvar sistema!");
                    }
                }            
           }
        });
    }
    
    /** Método que prepara o botão de carregar, que carrega o sistema que está em
     * arquivo.
     */
    private void preparaBotaoCarregar() {
        carregar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                if (carregar.isShowing()) {                    
                    
                    try {
                        operador.carregarEstadoSistema();
                        JOptionPane.showMessageDialog(null, "Carregado com sucesso!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao carregar sistema!");
                    }
                }            
           }
        });
    }

    /** Método que prepara botão que finaliza o login do operador.
     */
    private void preparaBotaoSair() {
        sair.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.setVisible(false);
                
                try {
                    telaL.programa();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(LogadoOperador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}