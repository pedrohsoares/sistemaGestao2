package sistema_gestao;

import java.util.ArrayList;

import enums.DescricaoRecurso;
import enums.StatusAlocacao;
import enums.TipoAtividade;
import enums.TipoUsuario;

public class UnidadeAcademica {
	
	private String nome;
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Recurso> recursos = new ArrayList<Recurso>();
	private ArrayList<Alocacao> alocacoes = new ArrayList<Alocacao>();
	private ArrayList<Atividade> atividades = new ArrayList<Atividade>();
	
	private int numUsuarios = 0;
	private int numRecursos = 0;
	
	//---
	public UnidadeAcademica() {
		this.usuarios.add(new Usuario("Jonas", "Ciencia da Computacao", TipoUsuario.ADMINISTRADOR, "admin", "1234",0));
		this.usuarios.add(new Usuario("Baldoino", "Ciencia da Computacao", TipoUsuario.PROFESSOR, "badu", "badu0",1));
		this.usuarios.add(new Usuario("Jarbas", "Engenharia da Computacao", TipoUsuario.PESQUISADOR, "jarbas", "jarbas0",2));
		this.usuarios.add(new Usuario("Wadd", "Ciencia da Computacao", TipoUsuario.ALUNO_GRADUACAO, "wadd", "wadd0",3));
		this.usuarios.add(new Usuario("Sam", "Engenharia da Computacao", TipoUsuario.ALUNO_MESTRADO, "sam", "sam0",4));
		this.numUsuarios = 5;
		this.nome = "UFAL";
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public Usuario getUsuario(String login) {
		for(Usuario usuario : this.usuarios) {
			if(usuario.getLogin().equals(login))
				return usuario;
		}
		
		System.out.println("Nao foi possivel identificar nenhum usuario com esse nome de usuario!");
		return null;
	}
	
	public Usuario getUsuario(String login, String senha) {
		for(Usuario usuario : this.usuarios) {
			if(usuario.getLogin().equals(login)) {
				if(usuario.getSenha().equals(senha))
					return usuario;
				
				System.out.println("Senha incorreta!");
				return null;
			}
		}
		
		System.out.println("Nao foi possivel encontrar nenhum usuario com essas informacoes!");
		return null;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public ArrayList<Recurso> getRecursos() {
		return recursos;
	}
	
	public ArrayList<Recurso> getRecursosByUser(Usuario usuario){
		ArrayList<Recurso> listRecursos = new ArrayList<Recurso>();
		for(Recurso recurso : this.recursos) {
			if(recurso.getResponsavel().equals(usuario))
				listRecursos.add(recurso);
		}
		
		return listRecursos;
	}
	
	public boolean checarRecurso(Usuario usuario, StatusAlocacao status) {
		for(Alocacao alocacao : this.alocacoes) {
			if(alocacao.getResponsavel().equals(usuario) && alocacao.getStatus() == status)
				return false;
		}
			
		return true;
	}
	
	public boolean checarRecurso(int idRecurso) {
		for(Alocacao alocacao : this.alocacoes) {
			if(alocacao.getRecurso().getId() == idRecurso)
				return false;
		}
		
		return true;
	}
	
	public Recurso getRecursoById(int id) {
		for(Recurso recurso : this.recursos) {
			if(recurso.getId() == id)
				return recurso;
		}
		
		System.out.println("Nao foi possivel encontrar nenhum recurso com essa identificacao!");
		return null;
	}

	public void setRecursos(ArrayList<Recurso> recursos) {
		this.recursos = recursos;
	}

	public ArrayList<Alocacao> getAlocacoes() {
		return alocacoes;
	}

	public void setAlocacoes(ArrayList<Alocacao> alocacoes) {
		this.alocacoes = alocacoes;
	}

	public ArrayList<Atividade> getAtividades() {
		return atividades;
	}
	
	public Atividade getAtividadeByName(String name) {
		for(Atividade atividade : this.atividades) {
			if(atividade.getTitulo().equals(name))
				return atividade;
		}
		
		System.out.println("Nao foi possivel encontrar nenhuma atividade com este nome!");
		return null;
	}

	public void setAtividades(ArrayList<Atividade> atividades) {
		this.atividades = atividades;
	}

	public int getNumUsuarios() {
		return numUsuarios;
	}
	
	public void setNumUsuarios(int numUsuarios) {
		this.numUsuarios = numUsuarios;
	}
	
	public int getNumRecursos() {
		return numRecursos;
	}
	
	public void setNumRecursos(int numRecursos) {
		this.numRecursos = numRecursos;
	}
	
	public void cadastrarUsuario(Usuario novoUsuario) {
		novoUsuario.setId(this.numUsuarios);
		this.usuarios.add(novoUsuario);
		this.numUsuarios++;
		System.out.println("Usuario cadastrado com sucesso!");
	}
	
	public void cadastrarRecurso(DescricaoRecurso descricao, DataHora dataInicio, DataHora dataFim, Usuario usuario) {
		Recurso recurso = new Recurso(this.numRecursos++,descricao,dataInicio,dataFim,usuario);
		recursos.add(recurso);
		
		System.out.println("Recurso cadastrado com sucesso!");
	}
	
	public void adicionarAlocacao(Recurso recurso, Atividade atividade, Usuario usuario) {
		Alocacao alocacao = new Alocacao(usuario,recurso,atividade);
		this.alocacoes.add(alocacao);
		
		System.out.println("Alocacao adicionada com sucesso!");
	}
	
	public void recursosPorTipo() {
		int processoAlocacao = 0;
		int andamento = 0;
		int alocado = 0;
		int concluido = 0;
		
		for(Alocacao alocacao : this.alocacoes) {
			if(alocacao.getStatus() == StatusAlocacao.EM_PROCESSO_DE_ALOCACAO)
				processoAlocacao++;
			else if(alocacao.getStatus() == StatusAlocacao.EM_ANDAMENTO)
				andamento++;
			else if(alocacao.getStatus() == StatusAlocacao.ALOCADO)
				alocado++;
			else
				concluido++;
		}
		
		System.out.println("Recursos em processo de alocacao: " + processoAlocacao);
		System.out.println("Recursos em andamento: " + andamento);
		System.out.println("Recursos alocados: " + alocado);
		System.out.println("Recursos concluidos: " + concluido);
	}
	
	public void atividadesPorTipo() {
		int aulaTradicional = 0;
		int apresentacao = 0;
		int laboratorio = 0;
		
		for(Atividade atividade : this.atividades) {
			if(atividade.getTipoAtividade() == TipoAtividade.AULA_TRADICIONAL)
				aulaTradicional++;
			else if(atividade.getTipoAtividade() == TipoAtividade.APRESENTACAO)
				apresentacao++;
			else
				laboratorio++;
		}
		
		System.out.println("Atividades de aula tradicional: " + aulaTradicional);
		System.out.println("Atividades de apresentacao: " + apresentacao);
		System.out.println("Atividade de laboratorio: " + laboratorio);
		
	}
	
	public boolean alterarStatusAlocacao(String titulo,Usuario usuario) {
		for(Alocacao alocacao : this.getAlocacoes()) {
			if(alocacao.getAtividades().getTitulo().equals(titulo)) {
				if(alocacao.getStatus() == StatusAlocacao.EM_PROCESSO_DE_ALOCACAO) {
					if(usuario.getTipo() == TipoUsuario.ADMINISTRADOR && alocacao.getAtividades().getTitulo() != null) {
						alocacao.setStatus(StatusAlocacao.ALOCADO);
						return true;
					}
				}else if(alocacao.getStatus() == StatusAlocacao.ALOCADO) {
					if(alocacao.getResponsavel().equals(usuario)) {
						alocacao.setStatus(StatusAlocacao.EM_ANDAMENTO);
						return true;
					}
				}else if(alocacao.getStatus() == StatusAlocacao.EM_ANDAMENTO) {
					if(usuario.getTipo() == TipoUsuario.ADMINISTRADOR && alocacao.getAtividades().getDescricao().length() > 0) {
						alocacao.setStatus(StatusAlocacao.CONCLUIDO);
						return true;
					}
				}else {
					System.out.println("Essa atividade ja foi concluida");
					return false;
				}
				
				break;
			}
		}
		return false;
	}
	
}
