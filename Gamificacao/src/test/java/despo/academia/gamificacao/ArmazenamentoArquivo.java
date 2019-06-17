package despo.academia.gamificacao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArmazenamentoArquivo implements Armazenamento {
	private String nomeDoArquivo;
	
	public ArmazenamentoArquivo(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
	}
	
	@Override
	public void armazenarPontuacaoUsuario(Usuario usuario) {				
		Path file = Paths.get(nomeDoArquivo);
		try {
			Files.write(file, Arrays.asList(usuario.toString()), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Usuario recuperarUsuario(String nomeUsuario) {
		// TODO Auto-generated method stub
		return null;		
	}

	@Override
	public List<Usuario> recuperarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		try {
			Path arquivo = Paths.get(nomeDoArquivo);
			List<String> pontuacoesPorUsuario = Files.lines(arquivo, StandardCharsets.UTF_8).collect(Collectors.toList());
			
			for (String registro : pontuacoesPorUsuario) {				
				usuarios.add(criarUsuario(extrairUsuario(registro), separarTiposDePontuacao(registro)));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	private String[] separarUsuarioDePontuacao(String registro) {
		return registro.split(":");
	}

	private String extrairUsuario(String registro) {
		return separarUsuarioDePontuacao(registro)[0];
	}

	private String extrairTodasPontuacoes(String registro) {
		return separarUsuarioDePontuacao(registro)[1];
	}
	
	private String[] separarTiposDePontuacao(String registro) {
		return extrairTodasPontuacoes(registro).split(";");
	}
	
	private Usuario criarUsuario(String nomeUsuario, String[] pontuacaoPorTipo) {
		Usuario usuario = new Usuario(nomeUsuario);

		for (String item : pontuacaoPorTipo) {
			String tipoPonto = item.split("=")[0];
			Integer quantidadePontos = Integer.valueOf(item.split("=")[1]);
			usuario.adicionarPontos(tipoPonto, quantidadePontos);
		}
		
		return usuario;
	}

}
