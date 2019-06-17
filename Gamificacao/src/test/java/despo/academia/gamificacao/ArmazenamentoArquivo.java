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
			Path file = Paths.get(nomeDoArquivo);
			List<String> registros = Files.lines(file, StandardCharsets.UTF_8).collect(Collectors.toList());
			
			for (String r : registros) {
				String[] usuarioPontuacao = r.split(":");
				String nomeUsuario = usuarioPontuacao[0];
				String pontuacao = usuarioPontuacao[1];
				String[] pontuacaoPorTipo = pontuacao.split(";");
				
				Usuario usuario = new Usuario(nomeUsuario);
				
				for (int i = 0; i < pontuacaoPorTipo.length; i++) {
					String[] tipoPonto = pontuacaoPorTipo[i].split("=");
					usuario.adicionarPontos(tipoPonto[0], Integer.valueOf(tipoPonto[1]));
				}
				
				
				usuarios.add(usuario);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

}
