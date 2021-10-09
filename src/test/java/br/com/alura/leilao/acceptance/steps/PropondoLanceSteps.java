package br.com.alura.leilao.acceptance.steps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;


public class PropondoLanceSteps {

	private Lance lance;
	private Leilao leilao;
	private ArrayList<Lance> lista;

	@Before
	public void setup() {
		this.lista = new ArrayList<Lance>();
		leilao = new Leilao("Mouse Gamer");
	}
	
	@Dado("um lance valido")
	public void dado_um_lance_valido() {
		Usuario usuario = new Usuario("fulano");
		lance = new Lance(usuario, BigDecimal.TEN);
	}

	@Quando("propoe um leilao")
	public void quando_propoe_um_leilao() {		
		leilao.propoe(lance);
	}

	@Entao("o lance e aceito")
	public void entao_o_lance_e_aceito() {
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
	}
	
	// ================================================================================================ //	
	
//	@Dado("varios lances validos")
//	public void varios_lances_validos() {
//		Usuario usuario1 = new Usuario("fulano");
//		lanceUser1 = new Lance(usuario1, BigDecimal.TEN);		
//		
//		Usuario usuario2 = new Usuario("beltrano");
//		lanceUser2 = new Lance(usuario2, new BigDecimal("20.00"));
//		
//		leilao = new Leilao("Teclado Gamer");
//	}	
	
	@Dado("um lance de R$ {double} do usuario {string}")
	public void um_lance_de_r$_do_usuario_fulano(Double valor, String nomeUsuario) {
		Lance lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valor));
		lista.add(lance);		
	}

	@Quando("propoe varios lances ao leilao")
	public void propoe_varios_lances_ao_leilao() {		
		this.lista.forEach(lance -> leilao.propoe(lance));
	}

	@Entao("os lances sao aceitos")
	public void os_lances_sao_aceitos() {
		Assert.assertEquals(this.lista.size(), leilao.getLances().size());
		Assert.assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
		Assert.assertEquals(this.lista.get(1).getValor(), leilao.getLances().get(1).getValor());
	}
	
	// ================================================================================================ //	
	
	@Dado("um lance invalido de R$ {double} do usuario {string}")
	public void um_lance_invalido_de_r$(Double valor, String usuario) {
		this.lance = new Lance(new BigDecimal(valor));
	}
	
	@Entao("o lance nao e aceito")
	public void o_lance_nao_e_aceito() {
		Assert.assertEquals(0, leilao.getLances().size());
	}
	
	// ================================================================================================ //
	
	@Dado("dois lances")
	public void dois_lances(DataTable dataTable) {
		List<Map<String, String>> valores = dataTable.asMaps();
		
		for (Map<String, String> mapa : valores) {
			String valor = mapa.get("valor");
			String usuario = mapa.get("usuario");
			
			Lance lance = new Lance(new Usuario(usuario), new BigDecimal(valor));
			lista.add(lance);
		}
	}
	
	@Entao("o segundo lance nao e aceito")
	public void o_segundo_lance_nao_e_aceito() {
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
	}


}