package br.uefs.ecomp.av.view;


import br.uefs.ecomp.av.controller.*;
import br.uefs.ecomp.av.model.enums.Sexo;
import br.uefs.ecomp.av.model.exception.CampoVazioException;
import br.uefs.ecomp.av.model.exception.DadoInexistenteException;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Classe (Java Principal) TelaLogin, responsável pela primeira tela de interface
 * gráfica do programa Assistente de Viagem.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class TelaLogin {

    private static JFrame janela;
    private Container painelPrincipal;
    private final Container primeiro = new JPanel();
    private final Container segundo = new JPanel();
    private final Container terceiro = new JPanel();
    private Label Email, Senha, Tipo, Nome, Email2, Senha2, S;
    private JTextField email, nome, email2;
    private JPasswordField senha, senha2;
    private JButton entrar, limpar, cadastrar, limpar2, sair;    
    private ButtonGroup bg, sexo;
    private JRadioButton bo, bu, bf, bm;
    private static ControllerOperador operador;
    private static ControllerUsuario usuario;
    
    /** Método que retorna a referência do usuário atual.
     * 
     * @return ControllerUsuario usuario
     */
    protected ControllerUsuario getThisUsuario() {
        return usuario;
    }
    
    /** Método que retorna a referência do operador atual.
     * 
     * @return ControllerUsuario usuario
     */
    protected ControllerOperador getThisOperador() {
        return operador;
    }

    /** Método inicial que chama o método programa.
     * 
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Locale.setDefault(new Locale("pt", "BR"));
        new TelaLogin().programa();
    }

    /** Método que chama todos os outros métodos sequencialmente.
     * 
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void programa() throws IOException, ClassNotFoundException { 
        this.inicializa();
        this.preparaJanela();
        this.preparaBotaoEntrar();
        this.preparaBotaoCadastrar();
        this.preparaBotaoLimpar();
        this.preparaBotaoLimpar2();         
        this.preparaBotaoSair();
        this.salvarCarregar();
        
    }

    /** Método que inicializa os atributos da tela principal, de login e cadastro.
     */
    private void inicializa() {
        janela = new JFrame("Assistente de Viagem©  -  Entre ou Cadastre-se");
        painelPrincipal = new JPanel(new GridLayout(3, 1));
        Email = new Label("E-mail:");
        Senha = new Label("Senha:");
        Tipo = new Label("  Tipo:");
        Nome = new Label("Nome:");
        Email2 = new Label("E-mail:");
        Senha2 = new Label("Senha:");
        S = new Label("               Sexo:");
        email = new JTextField(null, 39);
        senha = new JPasswordField(null, 39);
        nome = new JTextField(null, 39);
        email2 = new JTextField(null, 39);
        senha2 = new JPasswordField(null, 39);
        entrar = new JButton("Entrar");
        limpar = new JButton("Limpar");
        cadastrar = new JButton("Cadastrar");
        limpar2 = new JButton("Limpar");
        sair = new JButton("Sair");
        bg = new ButtonGroup();
        sexo = new ButtonGroup();
        bo = new JRadioButton("Operador");
        bu = new JRadioButton("Usuário");        
        bf = new JRadioButton("Femenino");
        bm = new JRadioButton("Masculino");
        operador = new ControllerOperador();        
        usuario = new ControllerUsuario();
    }

    /** Método que prepara a janela inicial, posicionando os objetos previamente
     * inicializados na tela.
     */
    private void preparaJanela() {
        janela.pack();
        janela.setSize(540, 540);
        janela.setResizable(false);
        janela.setVisible(true);
        janela.add(painelPrincipal);
        janela.setLocationRelativeTo(null);
        painelPrincipal.add(primeiro);
        painelPrincipal.add(segundo);
        painelPrincipal.add(terceiro);
        primeiro.add(Email);
        primeiro.add(email);
        primeiro.add(Senha);
        primeiro.add(senha);
        senha.setEchoChar('*');
        primeiro.add(Tipo);
        bg.add(bo);
        primeiro.add(bo);
        bg.add(bu);
        primeiro.add(bu);
        primeiro.add(entrar);
        primeiro.add(limpar);
        segundo.add(Nome);
        segundo.add(nome);
        segundo.add(Email2);
        segundo.add(email2);
        segundo.add(Senha2);
        segundo.add(senha2);        
        senha2.setEchoChar('*');
        segundo.add(S);
        sexo.add(bf);
        segundo.add(bf);
        sexo.add(bm);
        segundo.add(bm);
        segundo.add(cadastrar);
        segundo.add(limpar2);
        terceiro.add(sair);
        janela.repaint();
        janela.validate();
    }

    /** Método que prepara o botão de entrar de login, que verifica a senha e o
     * e-mail digitados e loga, se o usuário tiver cadastro e houver selecionado
     * tipo (usuário ou operador).
     */
    private void preparaBotaoEntrar() {
        entrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {                    
                    usuario.fazerLogin(email.getText(), senha.getText());
                    
                    if (bu.isSelected()) {
                        janela.setVisible(false);
                        new LogadoUsuario().preparaTela();
                    } else if (bo.isSelected()) {
                        janela.setVisible(false);
                        new LogadoOperador().preparaTela();
                    } else {
                        JOptionPane.showMessageDialog(null, "Campo vazio!");
                    }
                } catch (DadoInexistenteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO DE LOGIN", JOptionPane.ERROR_MESSAGE);
                } finally {
                    limpaLogin();
                }
            }
        });        
    }
    
    /** Método que limpa os campos de preenchimento de login de usuário.
     */
    public void limpaLogin() {
        email.setText(null);
        senha.setText(null);
        bg.clearSelection();
    }
    
    /** Método que prepara o botão de entrar de cadastro, que entra com a senha,
     * e-mail, sexo e nome digitados e cria um novo usuário, caso ele ainda não
     * exista.
     */
    private void preparaBotaoCadastrar() {
        cadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    usuario.fazerLogin(email2.getText(), senha2.getText());
                    JOptionPane.showMessageDialog(null, "Usuário já existe!");
                } catch (DadoInexistenteException ex) {
                    Sexo sexo = null;
                    
                    if (bm.isSelected()) {
                    sexo = Sexo.MASCULINO;
                    } else if (bf.isSelected()) {
                        sexo = Sexo.FEMININO;
                    }
                    
                    try {
                        usuario.cadastrarUsuario(email2.getText(), senha2.getText(),sexo, nome.getText());
                        JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
                        operador.salvarEstadoSistema();
                        limpaCadastro();
                    } catch (CampoVazioException ex2) {
                        JOptionPane.showMessageDialog(null, "Campos vazios!");
                    } catch (Exception ex1) {
                        Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }              
            }
        });
    }

    /** Método que limpa os campos de preenchimento de cadastro de usuário.
     */
    public void limpaCadastro() {
        nome.setText(null);
        email2.setText(null);
        senha2.setText(null);
        sexo.clearSelection();
    }

    /** Método que prepara botão que limpa os campos de preenchimento de login de
     * usuário.
     */
    public void preparaBotaoLimpar() {
        limpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limpaLogin();
            }
        });
    }

    /** Método que prepara botão que limpa os campos de preenchimento de cadastro
     * de usuário.
     */
    public void preparaBotaoLimpar2() {
        limpar2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limpaCadastro();
            }
        });
    }
    
    /** Método que prepara botão que finaliza o programa.
     */
    private void preparaBotaoSair() {
        sair.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    operador.salvarEstadoSistema();
                } catch (Exception ex) {
                    Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
    }
   
    /** Método que carrega o sistema ao abrir a tela e o salva ao fechá-la.
     */
    private void salvarCarregar() {
        //Inutiliza o botão fechar do frame:
        janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        janela.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
                
                try {
                    operador.salvarEstadoSistema();   //Sempre que a janela for fechada, o sistema é salvado.
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar sistema!");
                } finally {
                    System.exit(0);   //Sendo ou não carregado com sucesso, o frame é fechado.
                }                
            }
            
            @Override
             public void windowOpened(WindowEvent e) {
                
                try {
                    operador.carregarEstadoSistema();   //Sempre que o frame for aberto, o sistema é carregado.
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO AO CARREGAR SISTEMA", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao carregar sistema!");
                }
            }            
        });
    }
}