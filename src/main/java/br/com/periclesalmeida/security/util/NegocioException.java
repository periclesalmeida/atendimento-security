package br.com.periclesalmeida.security.util;

public class NegocioException extends RuntimeException {

	public NegocioException(String mensagemErro) {
		super(mensagemErro);
	}
}