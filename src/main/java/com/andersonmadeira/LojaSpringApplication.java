package com.andersonmadeira;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andersonmadeira.domain.Categoria;
import com.andersonmadeira.domain.Cidade;
import com.andersonmadeira.domain.Cliente;
import com.andersonmadeira.domain.Endereco;
import com.andersonmadeira.domain.Estado;
import com.andersonmadeira.domain.ItemPedido;
import com.andersonmadeira.domain.Pagamento;
import com.andersonmadeira.domain.PagamentoComBoleto;
import com.andersonmadeira.domain.PagamentoComCartao;
import com.andersonmadeira.domain.Pedido;
import com.andersonmadeira.domain.Produto;
import com.andersonmadeira.domain.enums.EstadoPagamento;
import com.andersonmadeira.domain.enums.TipoCliente;
import com.andersonmadeira.repositories.CategoriaRepository;
import com.andersonmadeira.repositories.CidadeRepository;
import com.andersonmadeira.repositories.ClienteRepository;
import com.andersonmadeira.repositories.EnderecoRepository;
import com.andersonmadeira.repositories.EstadoRepository;
import com.andersonmadeira.repositories.ItemPedidoRepository;
import com.andersonmadeira.repositories.PagamentoRepository;
import com.andersonmadeira.repositories.PedidoRepository;
import com.andersonmadeira.repositories.ProdutoRepository;

@SpringBootApplication
public class LojaSpringApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRepo;
	
	@Autowired
	private ProdutoRepository prodRepo;
	
	@Autowired
	private EstadoRepository estRepo;
	
	@Autowired
	private CidadeRepository cidRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderRepo;
	
	@Autowired
	private PedidoRepository pedRepo;
	
	@Autowired
	private PagamentoRepository pagRepo;
	
	@Autowired
	private ItemPedidoRepository ipRepo;

	public static void main(String[] args) {
		SpringApplication.run(LojaSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2)); 
		
		p1.getCategorias().addAll(Arrays.asList(cat1));	
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		catRepo.saveAll(Arrays.asList(cat1, cat2));
		prodRepo.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estRepo.saveAll(Arrays.asList(est1, est2));
		cidRepo.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12312312332", TipoCliente.PESSOA_FISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("23435467", "89897652"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "8932332", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Dois", "350", "Apto 32", "Centro", "8932332", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepo.saveAll(Arrays.asList(cli1));
		enderRepo.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedRepo.saveAll(Arrays.asList(ped1, ped2));
		pagRepo.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		ipRepo.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
