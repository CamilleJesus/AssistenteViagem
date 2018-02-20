package br.uefs.ecomp.av.model;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.av.controller.ControllerOperador;
import br.uefs.ecomp.av.model.enums.*;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.Iterador;

public class AssistenteViagemTest {

    private Local local1 = null, local2 = null;
    private Usuario usuario1 = null, usuario2 = null;
    private Atracao atracao1 = null, atracao2 = null, atracao3 = null, atracao4 = null;
    private Avaliacao avaliacao1 = null, avaliacao2 = null, avaliacao3 = null, avaliacao4 = null, avaliacao5 = null, avaliacao6 = null, avaliacao7 = null, avaliacao8 = null;
    private AssistenteViagem assistente = null;
    private ControllerOperador operador = null;

    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Before
    public void setUp() throws Exception {
        usuario1 = new Usuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        usuario2 = new Usuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");
        
        local1 = new Local("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        local2 = new Local("Salvador", new Coordenadas(-12.971111, -38.510833), "Bahia", "Brasil");
        
        atracao1 = new Hotel(local1, "GoldPalace", new Coordenadas(-12.254778, -38.956394), "Centro", CategoriaAtracao.RESTAURANTE,5, false, true, false);
        atracao2 = new Restaurante(local1, "Vivas", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        atracao3 = new Restaurante(local2, "Los Pampas", new Coordenadas(-12.287416, -38.957770), "Queimadinha", CategoriaAtracao.RESTAURANTE, FaixaPreco.CARO, TipoCozinha.NACIONAL);
        atracao4 = new Hotel(local1, "Estrela", new Coordenadas(-12.254778, -38.956394), "Brasilia", CategoriaAtracao.HOTEL, 4, true, true, true);
        
        avaliacao1 = new AvaliacaoRestaurante(usuario2, atracao2, 5, "Otimo restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("17/05/2012"), TipoVisita.SOZINHO, TipoRefeicao.JANTAR, 9, 7, 5);
        avaliacao2 = new AvaliacaoRestaurante(usuario1, atracao3, 2, "Pessimo", "Nunca passei por uma experiencia tao constrangedora", format.parse("12/10/2014"), TipoVisita.AMIGOS, TipoRefeicao.ALMOCO, 4, 5, 3);
        avaliacao3 = new AvaliacaoRestaurante(usuario2, atracao3, 5, "Agradavel", "Um ambiente bem descontraido e amistoso, Bem legal", format.parse("13/10/2014"), TipoVisita.JANTAR_A_DOIS, TipoRefeicao.JANTAR, 7, 10, 9);
        avaliacao4 = new AvaliacaoHotel(usuario1, atracao1, 4, "Bom pra relaxar!", "O GoldPalace é oq é há!", format.parse("13/10/2014"), ObjetivoVisita.FAMILIA, 8, 8, 8);
        avaliacao5 = new AvaliacaoHotel(usuario2, atracao1, 4, "Bom pra passar o final de semana!", "Gostei, mas achei meio desconfortante o ambiente.", format.parse("14/10/2014"), ObjetivoVisita.NEGOCIOS, 7, 8, 6);
        avaliacao6 = new AvaliacaoRestaurante(usuario1, atracao2, 5, "Otimo Restaurante!", "Bom servico, as camas são uma delicia.", format.parse("14/10/2014"), TipoVisita.AMIGOS, TipoRefeicao.TIRA_GOSTO, 7, 10, 9);
        avaliacao7 = new AvaliacaoHotel(usuario1, atracao4, 5, "Nunca visitei um Hotel tão Maravilhoso", "Varios vamosos frequentão esse hotel", format.parse("13/10/2015"), ObjetivoVisita.TURISMO, 10, 8, 10);
        avaliacao8 = new AvaliacaoHotel(usuario2, atracao4, 4, "Oh My Good, esse hotel é tudo de bom", "Tem muita mulher gostosa", format.parse("12/04/2012"), ObjetivoVisita.FAMILIA, 9, 6, 10);
        
        operador = new ControllerOperador();
    }

    @Test
    public void testBasic() {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();
        
        assertNotNull(assistente);
    }

    @Test 
    public void testCadastrarListarLocais() {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();
        
        Iterador it = assistente.listarLocais();
        assertFalse(it.temProximo());
        
        assistente.cadastrarLocal("Salvador", new Coordenadas(-12.971111, -38.510833), "Bahia", "Brasil");
        it = assistente.listarLocais();
        assertTrue(it.temProximo());
        assertEquals(local2, it.obterProximo());
        assertFalse(it.temProximo());
        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        it = assistente.listarLocais();

        //Os locais devem ser listados em ordem alfabética:
        assertTrue(it.temProximo());
        assertEquals(local1, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(local2, it.obterProximo());
        assertFalse(it.temProximo());	
    }

    @Test
    public void testCadastrarLogarUsuario() {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");
        assertNull(assistente.fazerLogin("fulano@gmail.com", "123456"));
        assertNull(assistente.fazerLogin("sicrana@gmail.com", "qwerasdfzxcv"));
        assertEquals(usuario1, assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv"));
        assertEquals(usuario2, assistente.fazerLogin("sicrana@gmail.com", "uiophjklvbnm"));
    }

    @Test
    public void cadastrarBuscarHotelOuRestaurante() {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");
        assertNull(assistente.buscarAtracao("Praca do Borogodo"));
        assistente.cadastrarHotel(local1, "GoldPalace", new Coordenadas(-12.254778, -38.956394), "Centro", CategoriaAtracao.RESTAURANTE,5, false, true, false);
        assertEquals(atracao1, assistente.buscarAtracao("GoldPalace"));
        assistente.cadastrarRestaurante(local1, "Vivas", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        assistente.cadastrarRestaurante(local2, "Los Pampas", new Coordenadas(-12.287416, -38.957770), "Queimadinha", CategoriaAtracao.RESTAURANTE, FaixaPreco.CARO, TipoCozinha.NACIONAL);
        assistente.cadastrarHotel(local1, "Estrela", new Coordenadas(-12.254778, -38.956394), "Brasilia", CategoriaAtracao.HOTEL, 4, true, true, true);
        assertEquals(atracao3, assistente.buscarAtracao("Los Pampas"));
        assertEquals(atracao1, assistente.buscarAtracao("GoldPalace"));
        assertEquals(atracao2, assistente.buscarAtracao("Vivas"));
        assertEquals(atracao4, assistente.buscarAtracao("Estrela"));
        assertNull(assistente.buscarAtracao("Atracao nao cadastrada"));
    }

    @Test
    public void testCadastrarBuscarLocal() {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        assertEquals(local1, assistente.buscarLocal("Feira"));
        assertNull(assistente.buscarLocal("Salvador"));
        assistente.cadastrarLocal("Salvador", new Coordenadas(-12.971111, -38.510833), "Bahia", "Brasil");
        assertEquals(local1, assistente.buscarLocal("Feira"));
        assertEquals(local2, assistente.buscarLocal("Salvador"));
    }

    @Test 
    public void testCadastrarListarAtracoesOrdemAlfabetica() {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
               
        Iterador it = local1.listarAtracoesOrdemAlfabetica();
        
        assertFalse(it.temProximo());
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");	
        assistente.cadastrarRestaurante(local1, "Vivas", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        it = local1.listarAtracoesOrdemAlfabetica();
        assertTrue(it.temProximo());
        assertEquals(atracao2, it.obterProximo());
        assertFalse(it.temProximo());
        assistente.cadastrarHotel(local1, "GoldPalace", new Coordenadas(-12.254778, -38.956394), "Centro", CategoriaAtracao.RESTAURANTE,5, false, true, false);
        it = local1.listarAtracoesOrdemAlfabetica();
        assertTrue(it.temProximo());
        assertEquals(atracao1, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(atracao2, it.obterProximo());
        assertFalse(it.temProximo());
    }

    @Test
    public void testProcessarNovasAvaliacoes() throws ParseException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");        
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");        
        assistente.cadastrarHotel(local1, "GoldPalace", new Coordenadas(-12.254778, -38.956394), "Centro", CategoriaAtracao.RESTAURANTE,5, false, true, false);
        assistente.cadastrarRestaurante(local1, "Vivas", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        assistente.cadastrarRestaurante(local2, "Los Pampas", new Coordenadas(-12.287416, -38.957770), "Queimadinha", CategoriaAtracao.RESTAURANTE, FaixaPreco.CARO, TipoCozinha.NACIONAL);
        
        Atracao atracaoAvaliada1 = assistente.buscarAtracao("Vivas");
        Atracao atracaoAvaliada2 = assistente.buscarAtracao("Los Pampas");
        usuario1.avaliarAtracao(atracaoAvaliada1, 5, "Otimo restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("17/05/2012"), TipoVisita.AMIGOS, TipoRefeicao.ALMOCO, 9, 7, 5);
        usuario1.avaliarAtracao(atracaoAvaliada2, 2, "Pessimo", "Nunca passei por uma experiencia tao constrangedora", format.parse("12/10/2014"), TipoVisita.JANTAR_A_DOIS, TipoRefeicao.JANTAR, 4, 5, 3);
        usuario2.avaliarAtracao(atracaoAvaliada1, 4, "Bom pra relaxar!", "O GoldPalace é oq é há!", format.parse("13/10/2014"), ObjetivoVisita.FAMILIA, 8, 8, 8);

        Avaliacao avaliacaoAtual = assistente.processarProximaAvaliacao();
        assertEquals(avaliacao1, avaliacaoAtual);
        avaliacaoAtual = assistente.processarProximaAvaliacao();
        assertEquals(avaliacao2, avaliacaoAtual);
        avaliacaoAtual = assistente.processarProximaAvaliacao();
        assertEquals(avaliacao4, avaliacaoAtual);	
        assertNull(assistente.processarProximaAvaliacao());
    }

    @Test
    public void testCadastrarAceitarListarAvaliacoes() throws ParseException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");	        
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");
        assistente.cadastrarRestaurante(local1, "Vivas", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        assistente.cadastrarRestaurante(local2, "Los Pampas", new Coordenadas(-12.287416, -38.957770), "Queimadinha", CategoriaAtracao.RESTAURANTE, FaixaPreco.CARO, TipoCozinha.NACIONAL);
        assistente.cadastrarHotel(local1, "GoldPalace", new Coordenadas(-12.254778, -38.956394), "Centro", CategoriaAtracao.RESTAURANTE,5, false, true, false);

        Atracao atracaoAvaliada1 = assistente.buscarAtracao("Los Pampas");
        Atracao atracaoAvaliada2 = assistente.buscarAtracao("Vivas");
        Atracao atracaoAvaliada3 = assistente.buscarAtracao("GoldPalace");
        usuario1.avaliarAtracao(atracaoAvaliada1, 2, "Pessimo", "Nunca passei por uma experiencia tao constrangedora", format.parse("12/10/2014"), TipoVisita.AMIGOS, TipoRefeicao.ALMOCO, 4, 5, 3);
        usuario1.avaliarAtracao(atracaoAvaliada2,  5, "Otimo Restaurante!", "Bom servico, as camas são uma delicia.", format.parse("14/10/2014"), TipoVisita.JANTAR_A_DOIS, TipoRefeicao.JANTAR, 7, 10, 9);
        usuario1.avaliarAtracao(atracaoAvaliada3,  2, "Pessimo", "Nunca passei por uma experiencia tao constrangedora", format.parse("12/10/2014"), TipoVisita.SOZINHO, TipoRefeicao.TIRA_GOSTO, 4, 5, 3);
        usuario2.avaliarAtracao(atracaoAvaliada1,  5, "Agradavel", "Um ambiente bem descontraido e amistoso, Bem legal", format.parse("13/10/2014"), TipoVisita.AMIGOS, TipoRefeicao.ALMOCO, 7, 10, 9);
        usuario2.avaliarAtracao(atracaoAvaliada2, 5, "Otimo restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("17/05/2012"), TipoVisita.JANTAR_A_DOIS, TipoRefeicao.JANTAR, 9, 7, 5);
        usuario2.avaliarAtracao(atracaoAvaliada3,5, "Agradavel", "Um ambiente bem descontraido e amistoso, Bem legal", format.parse("13/10/2014"), TipoVisita.SOZINHO, TipoRefeicao.TIRA_GOSTO, 7, 10, 9);
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao();	  // rejeitar
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao();   // rejeitar
        assistente.processarProximaAvaliacao();   // rejeitar

        //Listar avaliações da mais recente para a mais antiga:
        Iterador it = atracaoAvaliada1.listarAvaliacoes();
        assertTrue(it.temProximo());
        assertEquals(avaliacao3, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(avaliacao2, it.obterProximo());
        assertFalse(it.temProximo());
        it = atracaoAvaliada2.listarAvaliacoes();
        assertTrue(it.temProximo());
        assertEquals(avaliacao6, it.obterProximo());
        assertFalse(it.temProximo());
        it = atracaoAvaliada3.listarAvaliacoes();
        assertFalse(it.temProximo());
    }

    @Test
    public void testRecalcularRankings() throws ParseException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");	        
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");
        assistente.cadastrarHotel(local1, "GoldPalace", new Coordenadas(-12.254778, -38.956394), "Centro", CategoriaAtracao.RESTAURANTE,5, false, true, false);
        assistente.cadastrarRestaurante(local1, "Vivas", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        assistente.cadastrarRestaurante(local2, "Los Pampas", new Coordenadas(-12.287416, -38.957770), "Queimadinha", CategoriaAtracao.RESTAURANTE, FaixaPreco.CARO, TipoCozinha.NACIONAL);
        
        Atracao atracaoAvaliada1 = assistente.buscarAtracao("GoldPalace");
        Atracao atracaoAvaliada2 = assistente.buscarAtracao("Vivas");
        Atracao atracaoAvaliada3 = assistente.buscarAtracao("Los Pampas");
        usuario1.avaliarAtracao(atracaoAvaliada1,4, "Bom pra relaxar!", "O GoldPalace é oq é há!", format.parse("13/10/2014"), ObjetivoVisita.FAMILIA, 8, 8, 8);
        usuario1.avaliarAtracao(atracaoAvaliada2, 5, "Otimo Restaurante!", "Bom servico, as camas são uma delicia.", format.parse("14/10/2014"), TipoVisita.AMIGOS, TipoRefeicao.ALMOCO, 7, 10, 9);
        usuario1.avaliarAtracao(atracaoAvaliada3, 2, "Pessimo", "Nunca passei por uma experiencia tao constrangedora", format.parse("12/10/2014"), TipoVisita.JANTAR_A_DOIS, TipoRefeicao.JANTAR, 4, 5, 3);
        usuario2.avaliarAtracao(atracaoAvaliada1,  4, "Bom pra passar o final de semana!", "Gostei, mas achei meio desconfortante o ambiente.", format.parse("14/10/2014"), ObjetivoVisita.NEGOCIOS, 7, 8, 6);
        usuario2.avaliarAtracao(atracaoAvaliada2, 5, "Otimo restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("17/05/2012"), TipoVisita.AMIGOS, TipoRefeicao.JANTAR, 9, 7, 5);
        usuario2.avaliarAtracao(atracaoAvaliada3, 5, "Agradavel", "Um ambiente bem descontraido e amistoso, Bem legal", format.parse("13/10/2014"), TipoVisita.SOZINHO, TipoRefeicao.ALMOCO, 7, 10, 9);
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.recalcularTodosRankingsAtracoes();
        assertEquals(5.0, atracaoAvaliada2.pontuacaoMedia(), 0.001);
        assertEquals(1, atracaoAvaliada2.getOrdem());
        assertEquals(4.0, atracaoAvaliada1.pontuacaoMedia(), 0.001);
        assertEquals(2, atracaoAvaliada1.getOrdem());
        assertEquals(3.5, atracaoAvaliada3.pontuacaoMedia(), 0.001);
        assertEquals(3, atracaoAvaliada3.getOrdem());
    }

    @Test
    public void testCadastrarListarAtracoesOrdemPontuacaoMedia() throws ParseException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");
        assistente.cadastrarHotel(local1, "GoldPalace", new Coordenadas(-12.254778, -38.956394), "Centro", CategoriaAtracao.RESTAURANTE,5, false, true, false);
        assistente.cadastrarRestaurante(local1, "Vivas", new Coordenadas(-12.254778, -38.956394), "Kalilandia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        assistente.cadastrarHotel(local1, "Estrela", new Coordenadas(-12.254778, -38.956394), "Brasilia", CategoriaAtracao.HOTEL, 4, true, true, true);
        
        Atracao atracaoAvaliada1 = assistente.buscarAtracao("GoldPalace");
        Atracao atracaoAvaliada2 = assistente.buscarAtracao("Vivas");
        Atracao atracaoAvaliada3 = assistente.buscarAtracao("Estrela");
        usuario1.avaliarAtracao(atracaoAvaliada1,4, "Bom pra relaxar!", "O GoldPalace é oq é há!", format.parse("13/10/2014"), ObjetivoVisita.FAMILIA, 8, 8, 8);
        usuario1.avaliarAtracao(atracaoAvaliada2, 5, "Otimo Restaurante!", "Bom servico, as camas são uma delicia.", format.parse("14/10/2014"), TipoVisita.AMIGOS, TipoRefeicao.JANTAR, 7, 10, 9);
        usuario1.avaliarAtracao(atracaoAvaliada3,5, "Nunca visitei um Hotel tão Maravilhoso", "Varios vamosos frequentão esse hotel", format.parse("13/10/2015"), ObjetivoVisita.TURISMO, 10, 8, 10);
        usuario2.avaliarAtracao(atracaoAvaliada1,  4, "Bom pra passar o final de semana!", "Gostei, mas achei meio desconfortante o ambiente.", format.parse("14/10/2014"), ObjetivoVisita.NEGOCIOS, 7, 8, 6);
        usuario2.avaliarAtracao(atracaoAvaliada2, 5, "Otimo restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("17/05/2012"), TipoVisita.SOZINHO, TipoRefeicao.JANTAR, 9, 7, 5);
        usuario2.avaliarAtracao(atracaoAvaliada3, 4, "Oh My Good, esse hotel é tudo de bom", "Tem muita mulher gostosa", format.parse("12/04/2012"), ObjetivoVisita.TURISMO, 9, 6, 10);
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.recalcularTodosRankingsAtracoes();

        Iterador it = local1.listarAtracoesOrdemPontuacaoMedia();
        assertTrue(it.temProximo());
        assertEquals(atracao2, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(atracao4, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(atracao1, it.obterProximo());
        assertFalse(it.temProximo());
    }

    @Test
    public void testSalvarCarregarSistema() throws Exception {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        assistente.cadastrarHotel(local1, "GoldPalace", new Coordenadas(-12.254778, -38.956394), "Centro", CategoriaAtracao.HOTEL,5, false, true, false);
        assistente.cadastrarRestaurante(local1, "Vivas", new Coordenadas(-12.254778, -38.956394), "Kalilândia", CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL);
        
        operador.salvarEstadoSistema();
        
        AssistenteViagem.zerarSingleton();
        operador.carregarEstadoSistema();
        
        assertEquals(local1, assistente.buscarLocal("Feira"));
        assertEquals(atracao1, assistente.buscarAtracao("GoldPalace"));
        assertEquals(atracao2, assistente.buscarAtracao("Vivas"));
    }
}