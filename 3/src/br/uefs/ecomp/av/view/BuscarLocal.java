package br.uefs.ecomp.av.view;


import br.uefs.ecomp.av.controller.ControllerUsuario;
import br.uefs.ecomp.av.model.AssistenteViagem;
import br.uefs.ecomp.av.model.Local;
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
 * Classe BuscarLocal, responsável pela tela da interface gráfica de usuário ao
 * buscar local no programa Assistente de Viagem.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class BuscarLocal {
    private TelaLogin telaL = new TelaLogin();
    private JFrame janela = new JFrame("Assistente de Viagem©  -  Buscar Local");
    private Container painelPrincipal = new JPanel(new GridLayout(3, 1));
    private final Container primeiro = new JPanel();
    private final Container segundo = new JPanel();
    private final Container terceiro = new JPanel();    
    private Label Local = new Label("Local:");
    private JButton buscar = new JButton("Buscar");
    private JButton limpar = new JButton("Limpar");
    private JButton voltar = new JButton("Voltar");
    private JTextField local = new JTextField(null, 37);
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
        janela.setLocationRelativeTo(null);
        janela.add(painelPrincipal);
        painelPrincipal.add(primeiro);
        painelPrincipal.add(segundo);
        painelPrincipal.add(terceiro);
        primeiro.add(Local);
        primeiro.add(local);
        primeiro.add(buscar);
        primeiro.add(limpar);        
        segundo.add(scroll);
        terceiro.add(voltar);
        preparaBotaoBuscar();
        preparaBotaoLimpar();
        preparaBotaoVoltar();

    }

    /** Método que prepara o botão de buscar local.
     */
    private void preparaBotaoBuscar() {
        buscar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            
                try {   
                    Local l = telaL.getThisUsuario().buscarLocal(local.getText());
                    area.setText("Nome: " + l.getNome() + "\nLatitute: " + l.getLocalizacao().getLatitude()
                        + "\nLongitude: " + l.getLocalizacao().getLongitude()
                        + "\nEstado: " + l.getEstado() + "\nPais: " + l.getPais());
                } catch (DadoInexistenteException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO DE BUSCA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
  
    /** Método que prepara botão que limpa os campos de preenchimento de buscar
     * local.
     */
    public void preparaBotaoLimpar() {
        limpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                local.setText(null);
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