package br.uefs.ecomp.av.view;


import br.uefs.ecomp.av.model.enums.ObjetivoVisita;
import br.uefs.ecomp.av.model.enums.TipoRefeicao;
import br.uefs.ecomp.av.model.enums.TipoVisita;
import br.uefs.ecomp.av.model.exception.DadoInexistenteException;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
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
 * Classe AvaliarAtracao, responsável pela tela de interface gráfica do usuário
 * ao avaliar atração no programa Assistente de Viagem.
 * 
 * @author Camille Jesus e Rodrigo Santos
 */
public class AvaliarAtracao {

    private TelaLogin telaL = new TelaLogin();
    private JFrame janela = new JFrame("Assistente de Viagem©  -  Avaliar Atração");
    private Container painelPrincipal = new JPanel(new GridLayout(3, 1));
    private Container primeiro = new JPanel();
    private Container segundo = new JPanel();
    private Label Atracao = new Label("Atração:");
    private Label Categoria = new Label("Categoria:     ");
    private Label Titulo = new Label("Título:");
    private Label Texto = new Label(" Texto:");
    private Label Pontos = new Label("Pontos:");
    private Label Data = new Label("Data:");
    private Label Objetivo = new Label("Objetivo'':");
    private Label Visita = new Label("Visita':");
    private Label Refeicao = new Label("Refeição':");
    private Label Atendimento = new Label("Nota Atendimento:");
    private Label Quarto = new Label("Nota Quarto'':");
    private Label Qualidade = new Label("Nota Qualidade'':");
    private Label Comida = new Label("       Nota Comida':");
    private Label CustoBeneficio = new Label("Nota Custo-benefício':");
    private JTextField atracao = new JTextField(null, 37);
    private JTextField titulo = new JTextField(null, 40);
    private JTextField texto = new JTextField(null, 40);
    private JTextField pontos = new JTextField(null, 7);
    private JTextField atendimento = new JTextField(null, 7);
    private JTextField quarto = new JTextField(null, 3);
    private JTextField qualidade = new JTextField(null, 2);
    private JTextField comida = new JTextField(null, 3);
    private JTextField custoBeneficio = new JTextField(null, 3);
    private JTextField data = new JTextField(null, 7);
    private ButtonGroup categoria = new ButtonGroup();
    private ButtonGroup objetivo = new ButtonGroup();
    private ButtonGroup visita = new ButtonGroup();
    private ButtonGroup refeicao = new ButtonGroup();
    private JRadioButton hotel = new JRadioButton("Hotel''");
    private JRadioButton restaurante = new JRadioButton("Restaurante'");
    private JRadioButton negocios = new JRadioButton("Negócios");
    private JRadioButton turismo = new JRadioButton("Turismo");
    private JRadioButton amigos = new JRadioButton("Amigos");
    private JRadioButton sozinho = new JRadioButton("Sozinho");
    private JRadioButton almoco = new JRadioButton("Almoço");
    private JRadioButton jantar = new JRadioButton("Jantar");
    private JButton avaliar = new JButton("Avaliar");
    private JButton limpar = new JButton("Limpar");
    private JButton voltar = new JButton("Voltar");
    private LogadoUsuario logadoU = new LogadoUsuario();
  

    /** Método que prepara a janela, posicionando os objetos previamente inicializados
     * na tela.
     */
    void preparaTela() throws ParseException {
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setSize(540, 540);
        janela.setResizable(false);
        janela.setVisible(true);
        janela.add(painelPrincipal);
        janela.setLocationRelativeTo(null);
        painelPrincipal.add(primeiro);
        painelPrincipal.add(segundo);
        primeiro.add(Atracao);
        primeiro.add(atracao);
        primeiro.add(Categoria);
        categoria.add(hotel);
        primeiro.add(hotel);
        categoria.add(restaurante);
        primeiro.add(restaurante);
        primeiro.add(avaliar);
        primeiro.add(limpar);
        primeiro.add(voltar);
        segundo.add(Titulo);
        segundo.add(titulo);
        segundo.add(Texto);
        segundo.add(texto);
        segundo.add(Pontos);
        segundo.add(pontos);
        segundo.add(Data);
        segundo.add(data);
        segundo.add(Atendimento);
        segundo.add(atendimento);
        segundo.add(Objetivo);
        objetivo.add(negocios);
        segundo.add(negocios);
        objetivo.add(turismo);
        segundo.add(turismo);
        segundo.add(Quarto);
        segundo.add(quarto);
        segundo.add(Qualidade);
        segundo.add(qualidade);
        segundo.add(Visita);
        visita.add(amigos);
        segundo.add(amigos);
        visita.add(sozinho);
        segundo.add(sozinho);
        segundo.add(Refeicao);
        refeicao.add(almoco);
        segundo.add(almoco);
        refeicao.add(jantar);
        segundo.add(jantar);
        segundo.add(Comida);
        segundo.add(comida);
        segundo.add(CustoBeneficio);
        segundo.add(custoBeneficio);
        preparaBotaoAvaliar();
        preparaBotaoLimpar();
        preparaBotaoVoltar();
    }

    /** Método que prepara o botão de avaliar atração.
     */
    private void preparaBotaoAvaliar() {
        avaliar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    telaL.getThisUsuario().buscarAtracao(atracao.getText());
                    
                    if (hotel.isSelected()) {
                        ObjetivoVisita ov;

                        if (negocios.isSelected()) {
                            ov = ObjetivoVisita.NEGOCIOS;
                        } else {
                            ov = ObjetivoVisita.TURISMO;
                        }
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date dt = (java.util.Date) formatter.parse(data.getText());
                        telaL.getThisUsuario().avaliarHotel(Integer.parseInt(pontos.getText()),
                                titulo.getText(), texto.getText(), dt, ov, Integer.parseInt(atendimento.getText()),
                                Integer.parseInt(quarto.getText()), Integer.parseInt(qualidade.getText()));
                        JOptionPane.showMessageDialog(null, "Avaliada com sucesso!");
                    } else if (restaurante.isSelected()) {
                        TipoVisita tv;

                        if (negocios.isSelected()) {
                            tv = TipoVisita.AMIGOS;
                        } else {
                            tv = TipoVisita.SOZINHO;
                        }
                        TipoRefeicao tf;

                        if (almoco.isSelected()) {
                            tf = TipoRefeicao.ALMOCO;
                        } else {
                            tf = TipoRefeicao.JANTAR;
                        }
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date dt = (java.util.Date) formatter.parse(data.getText());
                        telaL.getThisUsuario().avaliarRestaurante(Integer.parseInt(pontos.getText()),
                            titulo.getText(), texto.getText(), dt, tv, tf, Integer.parseInt(atendimento.getText()),
                            Integer.parseInt(comida.getText()), Integer.parseInt(custoBeneficio.getText()));
                        JOptionPane.showMessageDialog(null, "Avaliada com sucesso!");
                    }
                } catch (DadoInexistenteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO DE BUSCA", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Data inválida!", JOptionPane.ERROR_MESSAGE);
                }finally{
                    limpa();
                }
            }
        });
    }
    
    /** Método que limpa os campos de avaliação de atração.
     */
    public void limpa(){
         atracao.setText(null);
                categoria.clearSelection();
                titulo.setText(null);
                texto.setText(null);
                pontos.setText(null);
                data.setText(null);
                atendimento.setText(null);
                objetivo.clearSelection();
                quarto.setText(null);
                qualidade.setText(null);
                visita.clearSelection();
                refeicao.clearSelection();
                comida.setText(null);
                custoBeneficio.setText(null);
    }


    /** Método que prepara botão que limpa os campos de avaliação de atração.
     */
    public void preparaBotaoLimpar() {
        limpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                atracao.setText(null);
                categoria.clearSelection();
                titulo.setText(null);
                texto.setText(null);
                pontos.setText(null);
                data.setText(null);
                atendimento.setText(null);
                objetivo.clearSelection();
                quarto.setText(null);
                qualidade.setText(null);
                visita.clearSelection();
                refeicao.clearSelection();
                comida.setText(null);
                custoBeneficio.setText(null);
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
                    try {
                        telaL.getThisOperador().salvarEstadoSistema();
                    } catch (Exception ex) {
                        Logger.getLogger(AvaliarAtracao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    janela.setVisible(false);
                    logadoU.preparaTela();
                }
            }
        });
    }
}