import static org.junit.Assert.*;
import org.junit.Test;

public class TesteCarrinhoCompra {
	
	@Test
	public void totalCarrinho() {
		CarrinhoCompras c = new CarrinhoCompras();
		c.adicionarProduto(new Produto("tenis",100));
		c.adicionarProduto(new Produto("camiseta",50));
		c.adicionarProduto(new Produto("bermuda",70));
		assertEquals(220, c.total());
	}
	
	@Test
	public void escutaAdicaoDeProduto() {
		CarrinhoCompras c = new CarrinhoCompras();
		MockObservadorCarrinho mock = new MockObservadorCarrinho();
		c.adicionarObservador(mock);
		c.adicionarProduto(new Produto("tenis",100));
		mock.verificarProdutoRecebido("tenis",100);
	}
	
	@Test
	public void adicionarDoisObservadores() {
		CarrinhoCompras c = new CarrinhoCompras();
		MockObservadorCarrinho mock1 = new MockObservadorCarrinho();
		MockObservadorCarrinho mock2 = new MockObservadorCarrinho();
		c.adicionarObservador(mock1);
		c.adicionarObservador(mock2);
		c.adicionarProduto(new Produto("tenis",100));
		mock1.verificarProdutoRecebido("tenis",100);
		mock2.verificarProdutoRecebido("tenis",100);
	} 	
	
	@Test
	public void continuaNotificandoComErroEmObservador() {
		CarrinhoCompras c = new CarrinhoCompras();
		MockObservadorCarrinho mock1 = new MockObservadorCarrinho();
		MockObservadorCarrinho mock2 = new MockObservadorCarrinho();
		mock2.queroQueVoceDePau();
		MockObservadorCarrinho mock3 = new MockObservadorCarrinho();
		c.adicionarObservador(mock1);
		c.adicionarObservador(mock2);
		c.adicionarObservador(mock3);
		c.adicionarProduto(new Produto("tenis",100));
		mock1.verificarProdutoRecebido("tenis",100);
		mock3.verificarProdutoRecebido("tenis",100);
	} 
}

