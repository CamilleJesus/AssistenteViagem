package br.uefs.ecomp.av.view;


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe LogadoUsuario, responsável pela tela da interface gráfica do usuário ao
 * logar no programa Assistente de Viagem.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class LogadoUsuario {
        
    private TelaLogin telaL = new TelaLogin();
    private JFrame janela = new JFrame("Assistente de Viagem©  -  Usuário");
    private Container painelPrincipal = new JPanel((new FlowLayout(FlowLayout.CENTER, 4500, 10)));
    private JButton cAtracao = new JButton("Cadastrar Atração");
    private JButton bAtracao = new JButton("  Buscar  Atração  ");
    private JButton bLocal = new JButton("     Buscar  Local     ");    
    private JButton lAtracao = new JButton("   Listar Atrações   ");
    private JButton aAtracao = new JButton("    Avaliar Atração   ");
    private JButton lAvaliacao = new JButton("  Listar Avaliações  ");
    private JButton sair = new JButton("Sair");
    
    /** Método que prepara a janela, posicionando os objetos previamente inicializados
     * na tela.
     */
    public void preparaTela(){
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setSize(540, 540);
        janela.setResizable(false);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        janela.add(painelPrincipal);
        painelPrincipal.add(cAtracao);
        painelPrincipal.add(bAtracao);
        painelPrincipal.add(bLocal);
        painelPrincipal.add(lAtracao);
        painelPrincipal.add(aAtracao);
        painelPrincipal.add(lAvaliacao);
        painelPrincipal.add(sair);      
        preparaBotaoCadastrarAtracao();        
        preparaBotaoBuscarAtracao();
        preparaBotaoBuscarLocal();
        preparaBotaoListarAtracoes();
        preparaBotaoAvaliarAtracao();
        preparaBotaoListarAvaliacoes();
        preparaBotaoSair();
    }
    
    /** Método que prepara o botão de cadastro de atração, para deixá-la disponível
     * no programa e chama a tela específica.
     */
    private void preparaBotaoCadastrarAtracao() {
        cAtracao.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            
                if (cAtracao.isShowing()) {
                    janela.setVisible(false);
                    new CadastrarAtracao().preparaTela();
                }
            }           
        });
    }

    /** Método que prepara o botão de buscar atração, para mostrar suas informações
     * e chama a tela específica.
     */
    private void preparaBotaoBuscarAtracao() {
        bAtracao.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            
                if (bAtracao.isShowing()){
                    janela.setVisible(false);
                    new BuscarAtracao().preparaTela();
                }
            }
        });
    }

    /** Método que prepara o botão de buscar local, para mostrar suas informações
     * e chama a tela específica.
     */
    private void preparaBotaoBuscarLocal() {
        bLocal.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            
                if (bLocal.isShowing()){
                    janela.setVisible(false);
                    new BuscarLocal().preparaTela();
                }
            }
        });
    }    

    /** Método que prepara o botão de listar atrações e chama a tela específica.
     */
    private void preparaBotaoListarAtracoes() {
        lAtracao.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
            
                if (lAtracao.isShowing()){
                    janela.setVisible(false);
                    new ListarAtracao().preparaTela();
                }
            }
        });
    }
    
    /** Método que prepara o botão de avaliar avaliações e chama a tela específica.
     */
    private void preparaBotaoAvaliarAtracao() {
        aAtracao.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            
                if (aAtracao.isShowing()){
                    janela.setVisible(false);
                    try {
                        new AvaliarAtracao().preparaTela();
                    } catch (ParseException ex) {
                        Logger.getLogger(LogadoUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    /** Método que prepara o botão de listar avaliações e chama a tela específica.
     */
    private void preparaBotaoListarAvaliacoes() {
        lAvaliacao.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            
                if (lAvaliacao.isShowing()){
                    janela.setVisible(false);
                    new ListarAvaliacao().preparaTela();
                }
            }
        });
    }
    
    /** Método que prepara botão que finaliza o login do usuário.
     */
     private void preparaBotaoSair() {
        sair.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.setVisible(false);
            
                try {                    
                    telaL.programa();
                } catch (IOException ex) {
                    Logger.getLogger(LogadoUsuario.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LogadoUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}