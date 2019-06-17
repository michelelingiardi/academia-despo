package despo.academia.gamificacao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
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
//		Path file = Paths.get(nomeDoArquivo);
//		try {
//			Stream<String> registros = Files.lines(file, StandardCharsets.UTF_8);
//			registros.forEach( r -> {
//				String[] usuarioPontuacao = r.split(":");
//				String nomeUsuario = usuarioPontuacao[0];
//				String pontuacao = usuarioPontuacao[1];
//				Usuario usuario = new Usuario(nomeUsuario);
//			});
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return null;
	}

}
