package despo.academia.gamificacao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ArmazenamentoArquivo implements Armazenamento {
	private static final String EXTENSAO_ARQUIVO			= ".txt";
	private static final String SEPARADOR_USUARIO 			= ":";
	private static final String SEPARADOR_TIPO_PONTO 		= ";";
	private static final String SEPARADOR_QUANTIDADE_PONTOS = "=";
	
	private Path arquivo;
	
	public ArmazenamentoArquivo(String nomeDoArquivo) {
		this.arquivo = Paths.get(nomeDoArquivo + EXTENSAO_ARQUIVO);
	}
	
	@Override
	public void armazenarPontuacao(Usuario usuario) {				
		try {
			if (Files.exists(arquivo)) {
				List<String> dadosDoArquivo = new ArrayList<>(Files.readAllLines(arquivo, StandardCharsets.UTF_8));
				substituirRegistroSeUsuarioJaExiste(usuario, dadosDoArquivo);
				gravarArquivo(dadosDoArquivo);
			} else {
				gravarArquivo(Arrays.asList(usuario.toString()));
			}
		} catch (IOException e) {
			tratarErro(e);
		}
	}

	private void gravarArquivo(List<String> dadosDoArquivo) throws IOException {
		Files.write(this.arquivo, dadosDoArquivo, StandardCharsets.UTF_8);
	}

	private void substituirRegistroSeUsuarioJaExiste(Usuario usuario, List<String> dadosDoArquivo) {
		boolean novoUsuario = true;
		for (int i = 0; i < dadosDoArquivo.size() ; i++) {
			if (this.extrairUsuario(dadosDoArquivo.get(i)).equalsIgnoreCase(usuario.getNome())) {
				dadosDoArquivo.set(i, usuario.toString());
				novoUsuario = false;
				break;
			}
		}
		if (novoUsuario) {
			dadosDoArquivo.add(usuario.toString());
		}
	}

	@Override
	public Usuario recuperarUsuario(String nomeUsuario) {
		List<Usuario> usuarios = this.recuperarUsuarios();
		for (Usuario u : usuarios) {
			if (u.getNome().equalsIgnoreCase(nomeUsuario)) return u;
		}
		return new Usuario(nomeUsuario);
	}

	@Override
	public List<Usuario> recuperarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		if (Files.exists(arquivo)) {
			try {
				List<String> pontuacoesPorUsuario = Files.lines(arquivo, StandardCharsets.UTF_8).collect(Collectors.toList());
				for (String registro : pontuacoesPorUsuario) {				
					usuarios.add(criarUsuario(extrairUsuario(registro), separarTiposDePontuacao(registro)));
				}
			} catch (IOException e) {
				tratarErro(e);
			}
		}
		return usuarios;
	}
	
	private String[] separarUsuarioDePontuacao(String registro) {
		return registro.split(SEPARADOR_USUARIO);
	}

	private String extrairUsuario(String registro) {
		try {
			return separarUsuarioDePontuacao(registro)[0];
		} catch (Exception e) {
			e.printStackTrace();
			throw new ArquivoInvalidoException("Erro ler o arquivo.");
		}
	}

	private String extrairTodasPontuacoes(String registro) {
		try {
			return separarUsuarioDePontuacao(registro)[1];
		} catch (Exception e) {
			e.printStackTrace();
			throw new ArquivoInvalidoException("Erro ler o arquivo.");
		}
		
	}
	
	private String[] separarTiposDePontuacao(String registro) {
		try {
			return extrairTodasPontuacoes(registro).split(SEPARADOR_TIPO_PONTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ArquivoInvalidoException("Erro ler o arquivo.");
		}
	}
	
	private String recuperarTipoPonto(String tipoPontoQuantidade) {
		try {
			return tipoPontoQuantidade.split(SEPARADOR_QUANTIDADE_PONTOS)[0];
		} catch (Exception e) {
			e.printStackTrace();
			throw new ArquivoInvalidoException("Erro ler o arquivo.");
		}
	}
	
	private Integer recuperarQuantidade(String tipoPontoQuantidade) {
		try {
			return Integer.valueOf(tipoPontoQuantidade.split(SEPARADOR_QUANTIDADE_PONTOS)[1]);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ArquivoInvalidoException("Erro ler o arquivo.");
		}
		
	}
	
	private Usuario criarUsuario(String nomeUsuario, String[] pontuacaoPorTipo) {
		Usuario usuario = new Usuario(nomeUsuario);
		for (String item : pontuacaoPorTipo) {
			String tipoPonto = recuperarTipoPonto(item);
			Integer quantidadePontos = recuperarQuantidade(item);
			usuario.adicionarPontos(tipoPonto, quantidadePontos);
		}
		return usuario;
	}

	@Override
	public Integer recuperarPontos(String tipoPonto, String nomeUsuario) {
		Usuario usuario = this.recuperarUsuario(nomeUsuario);
		return usuario.getPontos(tipoPonto);
	}
	
	@Override
	public Set<String> recuperarTiposDePontosDoUsuario(String nomeUsuario) {
		Usuario usuario = this.recuperarUsuario(nomeUsuario);
		return usuario.getPontuacao().getTodosOsPontos().keySet();
		
	}

	private void tratarErro(IOException e) {
		e.printStackTrace();
		throw new RuntimeException("Erro ao manipular arquivo.");
	}
}