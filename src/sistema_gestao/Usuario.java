package sistema_gestao;

import enums.TipoUsuario;

/* Tipo:
 * Aluno Graduação
 * Aluno Mestrado
 * Aluno Doutorado
 * Professor
 * Pesquisador
 * Administrador
 */

public class Usuario {
	
	private String nome;
	private int id;
	private String curso;
	private TipoUsuario tipo;
	private String login;
	private String senha;
	
	public Usuario(String nome, String curso, String login, String senha) {
		this.nome = nome;
		this.curso = curso;
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario(String nome, String curso, TipoUsuario tipoUsuario, String login, String senha,int id) {
		this.nome = nome;
		this.curso = curso;
		this.tipo = tipoUsuario;
		this.login = login;
		this.senha = senha;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	public boolean setTipo(int tipo) {
		switch (tipo) {
			case 1:
				this.tipo = TipoUsuario.ADMINISTRADOR;
				break;
			case 2:
				this.tipo = TipoUsuario.PROFESSOR;
				break;
			case 3:
				this.tipo = TipoUsuario.PESQUISADOR;
				break;
			case 4:
				this.tipo = TipoUsuario.ALUNO_DOUTORADO;
				break;
			case 5:
				this.tipo = TipoUsuario.ALUNO_MESTRADO;
				break;
			case 6:
				this.tipo = TipoUsuario.ALUNO_GRADUACAO;
				break;
			default:
				System.out.println("Entrada errada. Digite uma entrada valida!\n");
				return false;
		}
		
		return true;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
