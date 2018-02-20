package br.uefs.ecomp.av.view;


import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.controller.ControllerUsuario;
import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.model.enums.FaixaPreco;
import br.uefs.ecomp.av.model.enums.TipoCozinha;
import br.uefs.ecomp.av.model.exception.DadoExistenteException;
import br.uefs.ecomp.av.model.exception.DadoInexistenteException;
import br.uefs.ecomp.av.util.Coordenadas;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Classe CadastrarAtracao, responsável pela tela de interface gráfica do usuário
 * ao cadastrar atração no programa Assistente de Viagem.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class CadastrarAtracao {
    private TelaLogin telaL = new TelaLogin();
    private JFrame janela = new JFrame("Assistente de Viagem©  -  Cadastrar Atração");
    private Container painelPrincipal = new JPanel(new GridLayout(3, 1));
    private final Container primeiro = new JPanel();
    private final Container segundo = new JPanel(new GridLayout(7, 1));
    private final Container terceiro = new JPanel();
    private Label Local = new Label("Local:      ");
    private Label Nome = new Label("Nome:      ");
    private Label Latitude = new Label("Latitude:  ");
    private Label Longitude = new Label("Longitude:");
    private Label Bairro = new Label("Bairro:      ");
    private Label Categoria = new Label("Categoria: ");
    private Label Estrelas = new Label("                        Estrelas'':                 ");
    private Label Ar = new Label("Ar'':   ");
    private Label Tv = new Label("Tv'':   ");
    private Label Servico = new Label("Serviço'':   ");
    private Label Preco = new Label("Preço':     ");
    private Label Cozinha = new Label("Cozinha':   ");
    private JTextField local = new JTextField(null, 37);
    private JTextField nome = new JTextField(null, 37);
    private JTextField latitude = new JTextField(null, 37);
    private JTextField longitude = new JTextField(null, 37);
    private JTextField bairro = new JTextField(null, 37);  
    private JTextField estrelas = new JTextField(null, 3);
    private JButton cadastrar = new JButton("Cadastrar");
    private JButton limpar = new JButton("Limpar");
    private JButton voltar = new JButton("Voltar");
    private ButtonGroup categoria =  new ButtonGroup();
    private ButtonGroup ar =  new ButtonGroup();
    private ButtonGroup tv =  new ButtonGroup();
    private ButtonGroup servico =  new ButtonGroup();
    private ButtonGroup preco =  new ButtonGroup();    
    private ButtonGroup cozinha =  new ButtonGroup();
    private JRadioButton hotel = new JRadioButton("Hotel''");
    private JRadioButton restaurante = new JRadioButton("Restaurante'");
    private JRadioButton arS = new JRadioButton("Sim");
    private JRadioButton arN = new JRadioButton("Não");
    private JRadioButton tvS = new JRadioButton("Sim");
    private JRadioButton tvN = new JRadioButton("Não");
    private JRadioButton sS = new JRadioButton("Sim");
    private JRadioButton sN = new JRadioButton("Não");
    private JRadioButton barato = new JRadioButton("Barato");
    private JRadioButton caro = new JRadioButton("Caro");
    private JRadioButton nacional = new JRadioButton("Nacional");
    private JRadioButton internacional = new JRadioButton("Internacional");
    private LogadoUsuario logadoU = new LogadoUsuario();

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
        painelPrincipal.add(terceiro);
        primeiro.add(Local);
        primeiro.add(local);
        primeiro.add(Nome);
        primeiro.add(nome);
        primeiro.add(Latitude);
        primeiro.add(latitude);
        primeiro.add(Longitude);
        primeiro.add(longitude);
        primeiro.add(Bairro);
        primeiro.add(bairro);
        primeiro.add(Categoria);
        categoria.add(hotel);
        primeiro.add(hotel);
        categoria.add(restaurante);
        primeiro.add(restaurante);         
        primeiro.add(Estrelas);
        primeiro.add(estrelas);
        segundo.add(Ar);
        ar.add(arS);
        segundo.add(arS);
        ar.add(arN);
        segundo.add(arN);
        segundo.add(Tv);
        tv.add(tvS);
        segundo.add(tvS);
        tv.add(tvN);
        segundo.add(tvN);
        segundo.add(Servico);
        servico.add(sS);
        segundo.add(sS);
        servico.add(sN);
        segundo.add(sN);        
        segundo.add(Preco);
        preco.add(barato);
        segundo.add(barato);
        preco.add(caro);
        segundo.add(caro);
        segundo.add(Cozinha);
        cozinha.add(nacional);
        segundo.add(nacional);
        cozinha.add(internacional);
        segundo.add(internacional);
        terceiro.add(cadastrar);
        terceiro.add(limpar);
        terceiro.add(voltar);
        preparaBotaoCadastrar();
        preparaBotaoLimpar();
        preparaBotaoVoltar();
    }

    /** Método que prepara o botão de cadastro de atração, para deixá-la disponível
     * no programa.
     */
    private void preparaBotaoCadastrar() {
        cadastrar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    telaL.getThisUsuario().buscarAtracao(nome.getText());
                    JOptionPane.showMessageDialog(null, "Atração já existe!");
                } catch (DadoInexistenteException ex) {                    
                    CategoriaAtracao categoria;
                    
                    if (hotel.isSelected()) {
                        categoria = CategoriaAtracao.HOTEL;
                        boolean ar;
                        
                        if (arS.isSelected()) {
                            ar = true;
                        } else {
                            ar = false;
                        }
                        boolean tv;
                        
                        if (tvS.isSelected()) {
                            tv = true;
                        } else {
                            tv = false;
                        }
                        boolean servico;
                        
                        if (sS.isSelected()) {
                            servico = true;
                        } else {
                            servico = false;
                        }
                        
                        try {
                            telaL.getThisUsuario().cadastrarHotel(telaL.getThisUsuario().buscarLocal(local.getText()),
                                nome.getText(), new Coordenadas(Double.parseDouble(latitude.getText()),
                                Double.parseDouble(longitude.getText())), bairro.getText(),
                                categoria, Integer.parseInt(estrelas.getText()), ar, tv, servico);                            
                            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
                            limpaCadastro();
                        } catch (DadoExistenteException ex1) {
                            JOptionPane.showMessageDialog(null, ex1.getMessage(), "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
                        } catch (DadoInexistenteException ex2) {
                            JOptionPane.showMessageDialog(null, ex2.getMessage(), "ERRO DE BUSCA", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (restaurante.isSelected()) {
                        categoria = CategoriaAtracao.RESTAURANTE;
                        FaixaPreco preco;
                        
                        if (caro.isSelected()) {
                            preco = FaixaPreco.CARO;
                        } else {
                            preco = FaixaPreco.BARATO;
                        }
                        
                        TipoCozinha cozinha;
                        
                        if (internacional.isSelected()) {
                            cozinha = TipoCozinha.INTERNACIONAL;
                        } else {
                            cozinha = TipoCozinha.NACIONAL;
                        }                         
                        
                        try {
                            telaL.getThisUsuario().cadastrarRestaurante(telaL.getThisUsuario().buscarLocal(local.getText()),
                                nome.getText(), new Coordenadas(Double.parseDouble(latitude.getText()),
                                Double.parseDouble(longitude.getText())), bairro.getText(), categoria, preco, cozinha);                            
                            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
                            limpaCadastro();
                        } catch (DadoExistenteException ex3) {
                            JOptionPane.showMessageDialog(null, ex3.getMessage(), "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
                        } catch (DadoInexistenteException ex4) {
                            JOptionPane.showMessageDialog(null, ex4.getMessage(), "ERRO DE BUSCA", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                    JOptionPane.showMessageDialog(null, "Selecione categoria!");                   
                    }
                }                
            }
        });
    }
    
    /** Método que limpa os campos de preenchimento de cadastro de atração.
     */
    public void limpaCadastro() {
        local.setText(null);
        nome.setText(null);
        latitude.setText(null);
        longitude.setText(null);
        bairro.setText(null);
        categoria.clearSelection();
        estrelas.setText(null);
        ar.clearSelection();
        tv.clearSelection();
        servico.clearSelection();
        preco.clearSelection();
        cozinha.clearSelection();
    }
    
    /** Método que prepara botão que limpa os campos de preenchimento de cadastro
     * de atração.
     */    
    public void preparaBotaoLimpar() {
        limpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limpaCadastro();
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
                } catch (IOException ex) {
                    Logger.getLogger(CadastrarAtracao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(CadastrarAtracao.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    logadoU.preparaTela();
                }
            }
        });
    }
}