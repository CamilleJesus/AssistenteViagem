package br.uefs.ecomp.av.view;


import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.model.exception.CampoVazioException;
import br.uefs.ecomp.av.util.Coordenadas;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Classe CadastrarLocal, responsável pela tela de interface gráfica do operador
 * ao cadastrar local no programa Assistente de Viagem.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class CadastrarLocal {
    
    private TelaLogin telaL = new TelaLogin();
    private JFrame janela = new JFrame("Assistente de Viagem©  -  Cadastrar Local");
    private Container painelPrincipal = new JPanel(new GridLayout(3, 1));
    private final Container primeiro = new JPanel();
    private final Container segundo = new JPanel((new FlowLayout(FlowLayout.CENTER, 4500, 10)));
    private Label Nome = new Label("Nome:      ");
    private Label Latitude = new Label("Latitude:   ");
    private Label Longitude = new Label("Longitude:");    
    private Label Estado = new Label("Estado:     ");
    private Label Pais = new Label("País:         ");
    private JTextField nome = new JTextField(null, 37);
    private JTextField latitude = new JTextField(null, 37);
    private JTextField longitude = new JTextField(null, 37);
    private JTextField estado = new JTextField(null, 37);
    private JTextField pais = new JTextField(null, 37); 
    private JButton cadastrar = new JButton("Cadastrar");
    private JButton limpar = new JButton("   Limpar   ");
    private JButton voltar = new JButton("    Voltar    ");
    private LogadoOperador logadoU = new LogadoOperador();    

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
        janela.add(painelPrincipal);
        painelPrincipal.add(primeiro);
        painelPrincipal.add(segundo);
        primeiro.add(Nome);
        primeiro.add(nome);
        primeiro.add(Latitude);
        primeiro.add(latitude);
        primeiro.add(Longitude);
        primeiro.add(longitude);
        primeiro.add(Estado);
        primeiro.add(estado);
        primeiro.add(Pais);
        primeiro.add(pais);
        segundo.add(cadastrar);
        segundo.add(limpar);
        segundo.add(voltar);
        prepararBotaoCadastrar();
        preparaBotaoLimpar();
        preparaBotaoVoltar();
    }

    /** Método que prepara o botão de cadastro de local, para deixá-lo disponível
     * no programa.
     */
    private Object prepararBotaoCadastrar() {
        cadastrar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                    try {
                        telaL.getThisOperador().cadastrarLocal(nome.getText(), new Coordenadas(Double.parseDouble(latitude.getText()),
                                   Double.parseDouble(longitude.getText())), estado.getText(), pais.getText());
                        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
                    } catch (CampoVazioException ex) {
                       JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        limpaCadastro();
                    }              
            }
        });
        return null;
    }
    
    /** Método que limpa os campos de preenchimento de cadastro de local.
     */
    public void limpaCadastro() {
        nome.setText(null);
        latitude.setText(null);
        longitude.setText(null);
        estado.setText(null);
        pais.setText(null);
    }
    
    /** Método que prepara botão que limpa os campos de preenchimento de cadastro
     * de local.
     */
    public void preparaBotaoLimpar() {
        limpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limpaCadastro();
            }
        });
    }

    /** Método que prepara botão que volta à tela de operador.
     */
    private void preparaBotaoVoltar() {
        voltar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (voltar.isShowing()) {
                    
                    try {
                        telaL.getThisOperador().salvarEstadoSistema();
                    } catch (Exception ex) {
                        Logger.getLogger(CadastrarLocal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    janela.setVisible(false);
                    logadoU.preparaTela();
                }
            }
        });
    }
}