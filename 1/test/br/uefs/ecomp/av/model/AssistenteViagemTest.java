package br.uefs.ecomp.av.model;


import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.av.model.enums.CategoriaAtracao;
import br.uefs.ecomp.av.model.enums.FaixaPreco;
import br.uefs.ecomp.av.model.enums.Sexo;
import br.uefs.ecomp.av.model.enums.TipoCozinha;
import br.uefs.ecomp.av.util.Coordenadas;
import br.uefs.ecomp.av.util.Iterador;

public class AssistenteViagemTest {
	
    private Local local1 = null, local2 = null;
    private Usuario usuario1 = null, usuario2 = null;
    private Atracao atracao1 = null, atracao2 = null, atracao3 = null;
    private Avaliacao avaliacao1 = null, avaliacao2 = null, avaliacao3 = null, avaliacao4 = null, avaliacao5 = null, avaliacao6 = null;
    private AssistenteViagem assistente = null;

    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Before
    public void setUp() throws Exception {
        usuario1 = new Usuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        usuario2 = new Usuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");
        local1 = new Local("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        local2 = new Local("Salvador", new Coordenadas(-12.971111, -38.510833), "Bahia", "Brasil");
        atracao1 = new Atracao(local1, "Praca do Borogodo", new Coordenadas(-12.199689, -38.971118), CategoriaAtracao.PRACA);
        atracao2 = new Restaurante(local1, "Vivas", new Coordenadas(-12.254778, -38.956394), CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL, "Kalilandia");
        atracao3 = new Atracao(local1, "Caixa D'agua do Tomba :-)", new Coordenadas(-12.287416, -38.957770), CategoriaAtracao.MONUMENTO);
        avaliacao1 = new Avaliacao(usuario1, atracao1, 2, "Dureza!", "Parece mais o O do Borodogo!", format.parse("12/10/2014"));
        avaliacao2 = new Avaliacao(usuario1, atracao2, 5, "Excelente restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("12/10/2014"));
        avaliacao3 = new Avaliacao(usuario1, atracao3, 5, "Melhor que a Torre Eiffel!", "Eu, como legitimo morador do Tomba, me arrepio todos os dias ao ver esta obra.", format.parse("13/10/2014"));
        avaliacao4 = new Avaliacao(usuario2, atracao1, 4, "Bom pra relaxar!", "Praca do Borogodo eh o que ha!", format.parse("13/10/2014"));
        avaliacao5 = new Avaliacao(usuario2, atracao2, 4, "Otimo restaurante!", "Gostei, mas achei os gar�ons meio desatenciosos.", format.parse("14/10/2014"));
        avaliacao6 = new Avaliacao(usuario2, atracao3, 5, "Melhor que o Elevador Lacerda!", "Nao tem coisa mais bela na Feira de Santana, ja dizia Feira da Depressao.", format.parse("15/10/2014"));
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

        // os locais devem ser listados em ordem alfabetica
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
    public void cadastrarBuscarAtracaoOuRestaurante() {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");

        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");	

        assertNull(assistente.buscarAtracao("Praca do Borogodo"));

        assistente.cadastrarAtracao(local1, "Praca do Borogodo", new Coordenadas(-12.199689, -38.971118), CategoriaAtracao.PRACA);
        assertEquals(atracao1, assistente.buscarAtracao("Praca do Borogodo"));

        assistente.cadastrarRestaurante(local1, "Vivas", new Coordenadas(-12.254778, -38.956394), CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL, "Kalilandia");
        assistente.cadastrarAtracao(local1, "Caixa D'agua do Tomba :-)", new Coordenadas(-12.287416, -38.957770), CategoriaAtracao.MONUMENTO);
        assertEquals(atracao3, assistente.buscarAtracao("Caixa D'agua do Tomba :-)"));
        assertEquals(atracao1, assistente.buscarAtracao("Praca do Borogodo"));
        assertEquals(atracao2, assistente.buscarAtracao("Vivas"));
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
        AssistenteViagem assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");
        Local local = assistente.buscarLocal("Feira");

        Iterador it = local.listarAtracoesOrdemAlfabetica();
        assertFalse(it.temProximo());

        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");	
        assistente.cadastrarAtracao(local, "Praca do Borogodo", new Coordenadas(-12.199689, -38.971118), CategoriaAtracao.PRACA);
        it = local.listarAtracoesOrdemAlfabetica();
        assertTrue(it.temProximo());
        assertEquals(atracao1, it.obterProximo());
        assertFalse(it.temProximo());

        assistente.cadastrarAtracao(local, "Caixa D'agua do Tomba :-)", new Coordenadas(-12.287416, -38.957770), CategoriaAtracao.MONUMENTO);
        it = local.listarAtracoesOrdemAlfabetica();
        assertTrue(it.temProximo());
        assertEquals(atracao3, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(atracao1, it.obterProximo());
        assertFalse(it.temProximo());
    }

    @Test
    public void testProcessarNovasAvaliacoes() throws ParseException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");	
        Local local = assistente.buscarLocal("Feira");
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");

        Usuario usuario1 = assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");
        assistente.cadastrarAtracao(local, "Praca do Borogodo", new Coordenadas(-12.199689, -38.971118), CategoriaAtracao.PRACA);
        assistente.cadastrarRestaurante(local, "Vivas", new Coordenadas(-12.254778, -38.956394), CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL, "Kalilandia");
        assistente.cadastrarAtracao(local, "Caixa D'agua do Tomba :-)", new Coordenadas(-12.287416, -38.957770), CategoriaAtracao.MONUMENTO);
        Atracao atracaoAvaliada1 = assistente.buscarAtracao("Praca do Borogodo");
        Atracao atracaoAvaliada2 = assistente.buscarAtracao("Vivas");

        usuario1.avaliarAtracao(atracaoAvaliada1, 2, "Dureza!", "Parece mais o O do Borodogo!", format.parse("12/10/2014"));
        usuario1.avaliarAtracao(atracaoAvaliada2, 5, "Excelente restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("12/10/2014"));

        Usuario usuario2 = assistente.fazerLogin("sicrana@gmail.com", "uiophjklvbnm");
        usuario2.avaliarAtracao(atracaoAvaliada1, 4, "Bom pra relaxar!", "Praca do Borogodo eh o que ha!", format.parse("13/10/2014"));

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
        Local local = assistente.buscarLocal("Feira");
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");

        Usuario usuario1 = assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");
        assistente.cadastrarAtracao(local, "Praca do Borogodo", new Coordenadas(-12.199689, -38.971118), CategoriaAtracao.PRACA);
        assistente.cadastrarRestaurante(local, "Vivas", new Coordenadas(-12.254778, -38.956394), CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL, "Kalilandia");
        assistente.cadastrarAtracao(local, "Caixa D'agua do Tomba :-)", new Coordenadas(-12.287416, -38.957770), CategoriaAtracao.MONUMENTO);
        Atracao atracaoAvaliada1 = assistente.buscarAtracao("Praca do Borogodo");
        Atracao atracaoAvaliada2 = assistente.buscarAtracao("Vivas");
        Atracao atracaoAvaliada3 = assistente.buscarAtracao("Caixa D'agua do Tomba :-)");


        usuario1.avaliarAtracao(atracaoAvaliada1, 2, "Dureza!", "Parece mais o O do Borodogo!", format.parse("12/10/2014"));
        usuario1.avaliarAtracao(atracaoAvaliada2, 5, "Excelente restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("12/10/2014"));
        usuario1.avaliarAtracao(atracaoAvaliada3, 5, "Melhor que a Torre Eiffel!", "Eu, como legitimo morador do Tomba, me arrepio todos os dias ao ver esta obra.", format.parse("13/10/2014"));

        Usuario usuario2 = assistente.fazerLogin("sicrana@gmail.com", "uiophjklvbnm");
        usuario2.avaliarAtracao(atracaoAvaliada1, 4, "Bom pra relaxar!", "Praca do Borogodo eh o que ha!", format.parse("13/10/2014"));
        usuario2.avaliarAtracao(atracaoAvaliada2, 4, "Otimo restaurante!", "Gostei, mas achei os gar�ons meio desatenciosos.", format.parse("14/10/2014"));
        usuario2.avaliarAtracao(atracaoAvaliada3, 5, "Melhor que o Elevador Lacerda!", "Nao tem coisa mais bela na Feira de Santana, ja dizia Feira da Depressao.", format.parse("15/10/2014"));

        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao();	// rejeitar
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao();	// rejeitar
        assistente.processarProximaAvaliacao();	// rejeitar

        // listar avaliacoes da mais recente para a mais antiga
        Iterador it = atracaoAvaliada1.listarAvaliacoes();
        assertTrue(it.temProximo());
        assertEquals(avaliacao4, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(avaliacao1, it.obterProximo());
        assertFalse(it.temProximo());

        it = atracaoAvaliada2.listarAvaliacoes();
        assertTrue(it.temProximo());
        assertEquals(avaliacao2, it.obterProximo());
        assertFalse(it.temProximo());

        it = atracaoAvaliada3.listarAvaliacoes();
        assertFalse(it.temProximo());
    }

    @Test
    public void testRecalcularRankings() throws ParseException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");	
        Local local = assistente.buscarLocal("Feira");
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");

        Usuario usuario1 = assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");
        assistente.cadastrarAtracao(local, "Praca do Borogodo", new Coordenadas(-12.199689, -38.971118), CategoriaAtracao.PRACA);
        assistente.cadastrarRestaurante(local, "Vivas", new Coordenadas(-12.254778, -38.956394), CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL, "Kalilandia");
        assistente.cadastrarAtracao(local, "Caixa D'agua do Tomba :-)", new Coordenadas(-12.287416, -38.957770), CategoriaAtracao.MONUMENTO);
        Atracao atracaoAvaliada1 = assistente.buscarAtracao("Praca do Borogodo");
        Atracao atracaoAvaliada2 = assistente.buscarAtracao("Vivas");
        Atracao atracaoAvaliada3 = assistente.buscarAtracao("Caixa D'agua do Tomba :-)");


        usuario1.avaliarAtracao(atracaoAvaliada1, 2, "Dureza!", "Parece mais o O do Borodogo!", format.parse("12/10/2014"));
        usuario1.avaliarAtracao(atracaoAvaliada2, 5, "Excelente restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("12/10/2014"));
        usuario1.avaliarAtracao(atracaoAvaliada3, 5, "Melhor que a Torre Eiffel!", "Eu, como legitimo morador do Tomba, me arrepio todos os dias ao ver esta obra.", format.parse("13/10/2014"));

        Usuario usuario2 = assistente.fazerLogin("sicrana@gmail.com", "uiophjklvbnm");
        usuario2.avaliarAtracao(atracaoAvaliada1, 4, "Bom pra relaxar!", "Praca do Borogodo eh o que ha!", format.parse("13/10/2014"));
        usuario2.avaliarAtracao(atracaoAvaliada2, 4, "Otimo restaurante!", "Gostei, mas achei os gar�ons meio desatenciosos.", format.parse("14/10/2014"));
        usuario2.avaliarAtracao(atracaoAvaliada3, 5, "Melhor que o Elevador Lacerda!", "Nao tem coisa mais bela na Feira de Santana, ja dizia Feira da Depressao.", format.parse("15/10/2014"));

        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();

        assistente.recalcularTodosRankingsAtracoes();

        assertEquals(5.0, atracaoAvaliada3.pontuacaoMedia(), 0.001);
        assertEquals(1, atracaoAvaliada3.getOrdem());
        assertEquals(4.5, atracaoAvaliada2.pontuacaoMedia(), 0.001);
        assertEquals(2, atracaoAvaliada2.getOrdem());
        assertEquals(3.0, atracaoAvaliada1.pontuacaoMedia(), 0.001);
        assertEquals(3, atracaoAvaliada1.getOrdem());
    }

    @Test
    public void testCadastrarListarAtracoesOrdemPontuacaoMedia() throws ParseException {
        AssistenteViagem.zerarSingleton();
        assistente = AssistenteViagem.getInstance();

        assistente.cadastrarLocal("Feira", new Coordenadas(-12.266944, -38.966944), "Bahia", "Brasil");	
        Local local = assistente.buscarLocal("Feira");
        assistente.cadastrarUsuario("fulano@gmail.com", "qwerasdfzxcv", Sexo.MASCULINO, "Fulano de Tal");
        assistente.cadastrarUsuario("sicrana@gmail.com", "uiophjklvbnm", Sexo.FEMININO, "Sicrana de Qual");

        Usuario usuario1 = assistente.fazerLogin("fulano@gmail.com", "qwerasdfzxcv");
        assistente.cadastrarAtracao(local, "Praca do Borogodo", new Coordenadas(-12.199689, -38.971118), CategoriaAtracao.PRACA);
        assistente.cadastrarRestaurante(local, "Vivas", new Coordenadas(-12.254778, -38.956394), CategoriaAtracao.RESTAURANTE, FaixaPreco.SOFISTICADO, TipoCozinha.INTERNACIONAL, "Kalilandia");
        assistente.cadastrarAtracao(local, "Caixa D'agua do Tomba :-)", new Coordenadas(-12.287416, -38.957770), CategoriaAtracao.MONUMENTO);
        Atracao atracaoAvaliada1 = assistente.buscarAtracao("Praca do Borogodo");
        Atracao atracaoAvaliada2 = assistente.buscarAtracao("Vivas");
        Atracao atracaoAvaliada3 = assistente.buscarAtracao("Caixa D'agua do Tomba :-)");


        usuario1.avaliarAtracao(atracaoAvaliada1, 2, "Dureza!", "Parece mais o O do Borodogo!", format.parse("12/10/2014"));
        usuario1.avaliarAtracao(atracaoAvaliada2, 5, "Excelente restaurante!", "Bom servico, pratos bem saborosos, preco um pouco alto.", format.parse("12/10/2014"));
        usuario1.avaliarAtracao(atracaoAvaliada3, 5, "Melhor que a Torre Eiffel!", "Eu, como legitimo morador do Tomba, me arrepio todos os dias ao ver esta obra.", format.parse("13/10/2014"));

        Usuario usuario2 = assistente.fazerLogin("sicrana@gmail.com", "uiophjklvbnm");
        usuario2.avaliarAtracao(atracaoAvaliada1, 4, "Bom pra relaxar!", "Praca do Borogodo eh o que ha!", format.parse("13/10/2014"));
        usuario2.avaliarAtracao(atracaoAvaliada2, 4, "Otimo restaurante!", "Gostei, mas achei os gar�ons meio desatenciosos.", format.parse("14/10/2014"));
        usuario2.avaliarAtracao(atracaoAvaliada3, 5, "Melhor que o Elevador Lacerda!", "Nao tem coisa mais bela na Feira de Santana, ja dizia Feira da Depressao.", format.parse("15/10/2014"));

        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();
        assistente.processarProximaAvaliacao().aceitar();

        assistente.recalcularTodosRankingsAtracoes();

        Iterador it = local.listarAtracoesOrdemPontuacaoMedia();
        assertTrue(it.temProximo());
        assertEquals(atracao3, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(atracao2, it.obterProximo());
        assertTrue(it.temProximo());
        assertEquals(atracao1, it.obterProximo());
        assertFalse(it.temProximo());
    }	
}