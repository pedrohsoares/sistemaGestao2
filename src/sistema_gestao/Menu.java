package sistema_gestao;

import java.util.ArrayList;
import java.util.Scanner;

import enums.DescricaoRecurso;
import enums.MaterialApoio;
import enums.StatusAlocacao;
import enums.TipoAtividade;
import enums.TipoUsuario;


public class Menu {
	
	public Usuario cadastrarUsuario(Usuario usuario) {
		if(usuario != null && usuario.getTipo() == TipoUsuario.ADMINISTRADOR) {
			Scanner input = new Scanner(System.in);
			String nome, curso, login, senha;
			int tipo;
			
			System.out.print("Digite o nome: ");
			nome = input.nextLine();
			System.out.print("Digite o curso: ");
			curso = input.nextLine();
			System.out.print("Digite um nome de usuario: ");
			login = input.nextLine();
			System.out.print("Digite uma senha: ");
			senha = input.nextLine();
			System.out.println();
			
			Usuario newUser = new Usuario(nome, curso, login, senha);
			
			do {
				System.out.println("1. Administrador");
				System.out.println("2. Professor");
				System.out.println("3. Pesquisador");
				System.out.println("4. Aluno Doutorado");
				System.out.println("5. Aluno Mestrado");
				System.out.println("6. Aluno Graduacao");
				System.out.print("Entre com o tipo do usuario: ");
				tipo = input.nextInt();
			} while(!newUser.setTipo(tipo));
			
			System.out.println("Usuario cadastrado com sucesso!");
			return newUser;
		}else {
			System.out.println("Voce precisa ser administrador pra cadastrar um novo usuario!");
			return null;
		}
	}
	
	public boolean continuarExecucao() {
		Scanner input = new Scanner(System.in);
		int opcao;
		boolean flag = false; // flag para a mensagem de erro 
		
		do {
			if (flag) System.out.println("Entrada invalida%n");
			flag = true;
			
			System.out.println("1. Sim");
			System.out.println("0. Nao");
			System.out.print("Deseja realiza mais alguma operacao? ");
			
			opcao = input.nextInt();
			
		} while(opcao != 0 && opcao != 1);
		
		if (opcao == 1)
			return true;
		
		return false;
	}
	
	public DescricaoRecurso menuDescricaoRecurso() {
		Scanner input = new Scanner(System.in);
		int opcao;
		boolean flag = true;
		
		do {
			if(!flag)
				System.out.println("Entrada invalida!");
			flag = false;
			
			System.out.println("Informe o tipo do recurso:");
			System.out.println("1. Laboratorio");
			System.out.println("2. Auditorio");
			System.out.println("3. Sala de Aula");
			System.out.println("4. Projetor");
			
			opcao = input.nextInt();
		}while(opcao < 1 || opcao > 4);
		
		switch(opcao) {
			case 1:
				return DescricaoRecurso.LABORATORIO;
			case 2:
				return DescricaoRecurso.AUDITORIO;
			case 3:
				return DescricaoRecurso.SALA_DE_AULA;
			default:
				return DescricaoRecurso.PROJETOR;	
		}
	}
	
	public Usuario login(UnidadeAcademica unidade) {
		Scanner input = new Scanner(System.in);
		String login,senha;
		
		System.out.print("Digite o nome de usuario: ");
		login = input.nextLine();
		System.out.print("Digite a senha: ");
		senha = input.nextLine();
		
		return unidade.getUsuario(login, senha);
	}
	
	public void consultar(UnidadeAcademica unidade) {
		Scanner input = new Scanner(System.in);
		int opcao;
		boolean flag = true;	
		
		do {
			if(!flag)
				System.out.println("Entrada invalida!");
			flag = false;
			
			System.out.println("Voce deseja consultar:");
			System.out.println("1. Usuario");
			System.out.println("2. Recurso");
			System.out.print("Informe o numero da opcao desejada:" );
			opcao = input.nextInt();
		}while(opcao > 2 || opcao < 1);
		
		if(opcao == 1)
			consultarUsuario(unidade);
		else
			consultarRecurso(unidade);
			
	}
	
	public void consultarUsuario(UnidadeAcademica unidade) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Informe o nome de usuario:");
		String identificacao = input.nextLine();
		
		Usuario usuario = unidade.getUsuario(identificacao);
		if(usuario == null) {
			System.out.println("Não foi encontrado nenhum usuario!");
		}else {
			System.out.println("Nome:" + usuario.getNome());
			System.out.println("Tipo de Usuario:" + usuario.getTipo());
			System.out.println("Curso:" + usuario.getCurso());
			
			System.out.println("\nHistorico de Recursos Alocados");
			ArrayList<Recurso> listRecurso = unidade.getRecursosByUser(usuario);
			for(Recurso recurso : listRecurso) {
				System.out.println("\tNome:" + recurso.getDescricao()
						+ "\n\tData de Inicio:" + recurso.getDataInicio() + "\n\tData de Termino: " + recurso.getDataFim());
			}
			
			System.out.println("\nHistorico de Atividades");
			for(Alocacao alocacao : unidade.getAlocacoes()) {
				if(alocacao.getResponsavel().equals(usuario)) {
					System.out.println("\tTipo de Atividade: " + alocacao.getAtividades().getTitulo());
					System.out.println("\tDescricao: " + alocacao.getAtividades().getDescricao());
					System.out.println("\tMaterial de Apoio: " + alocacao.getAtividades().getMaterialApoio() + "\n");
				}
			}
		}
	}
	
	public void cadastrarRecurso(UnidadeAcademica unidade,Usuario usuario) {
		Scanner input = new Scanner(System.in);
		if(usuario != null) {
			if(usuario.getTipo() == TipoUsuario.ADMINISTRADOR || 
			 usuario.getTipo() == TipoUsuario.PESQUISADOR ||
			 usuario.getTipo() == TipoUsuario.PROFESSOR) {
						
				boolean flag = unidade.checarRecurso(usuario, StatusAlocacao.EM_ANDAMENTO);

				if(!flag) {
					System.out.println("Nao e possivel criar um novo recurso porque voce ja possui um em andamento!");
				}else {
					
					DescricaoRecurso descricao = menuDescricaoRecurso();
					
					
					System.out.println("Informe a data de inicio do recurso no formato dd mm aaaa:");
					int dia = input.nextInt();
					int mes = input.nextInt();
					int ano = input.nextInt();
					
					System.out.println("Informe a hora de inicio do recurso no formato hh mm");
					int hora = input.nextInt();
					int minuto = input.nextInt();
					
					DataHora dataInicio = new DataHora(dia,mes,ano,hora,minuto);
					
					System.out.println("Informe a data de termino do recurso no formato dd mm aaaa:");
					dia = input.nextInt();
					mes = input.nextInt();
					ano = input.nextInt();
					
					System.out.println("Informe a hora de termino do recurso no formato hh mm");
					hora = input.nextInt();
					minuto = input.nextInt();
					DataHora dataTermino = new DataHora(dia,mes,ano,hora,minuto);
					
					unidade.cadastrarRecurso(descricao,dataInicio,dataTermino,usuario);
				}
			}
		}else {
			System.out.println("Voce precisa esta logado pra cadastrar um recurso!");
		}
	}
	
	public void consultarRecurso(UnidadeAcademica unidade) {
		Scanner input = new Scanner(System.in);
		int identificador;
		
		System.out.print("Informe o identificador do recurso: ");
		identificador = input.nextInt();
		Recurso recurso = unidade.getRecursoById(identificador);
		
		System.out.println("Tipo de Recurso: " + recurso.getDescricao());
		System.out.println("Data de Inicio: " + recurso.getDataInicio());
		System.out.println("Data de Termino: " + recurso.getDataFim());
		System.out.println("Responsavel pelo Recurso: " + recurso.getResponsavel().getNome());
		
		for(Alocacao alocacao : unidade.getAlocacoes()) {
			if(alocacao.getRecurso().equals(recurso)) {
				System.out.println("Atividade relacionada ao recurso: " + alocacao.getAtividades().getTitulo());
				System.out.println("Tipo da Atividade: " + alocacao.getAtividades().getTipoAtividade());
				System.out.println("Descrição: " + alocacao.getAtividades().getDescricao());
			}
		}
		
	}
	
	public void alocarRecurso(UnidadeAcademica unidade, Usuario usuario) {
		Scanner input = new Scanner(System.in);
		int identificador;
		String nomeAtividade;
		
		System.out.println("Informe o identificador do recurso a ser alocado:");
		identificador = input.nextInt();
		
		boolean flag = unidade.checarRecurso(identificador);
		
		if(flag) {
			Recurso recurso = unidade.getRecursoById(identificador);
			
			if(recurso != null) {
				input  = new Scanner(System.in);
				System.out.println("Informe o nome da atividade que deseja alocar o recurso: ");
				nomeAtividade = input.nextLine();
				Atividade atividade = unidade.getAtividadeByName(nomeAtividade);
				
				if(atividade != null) {
					unidade.adicionarAlocacao(recurso,atividade,usuario);
				}else {
					while(recurso == null) {
						System.out.println("Informe o nome da atividade que deseja alocar o recurso:");
						nomeAtividade = input.nextLine();
						atividade = unidade.getAtividadeByName(nomeAtividade);
					}
				}
			}else {
				while(recurso == null) {
					System.out.println("Informe o identificador do recurso a ser alocado:");
					identificador = input.nextInt();
					recurso = unidade.getRecursoById(identificador);
				}
			}
			
		}else {
			System.out.println("Recurso ja alocado!");
		}
	}
	
	public void cadastrarAtividade(UnidadeAcademica unidade) {
		Scanner input = new Scanner(System.in);
		int opcao;
		boolean flag = false;
		MaterialApoio material = null;
		
		System.out.println("Informe o nome da atividade:");
		String titulo = input.nextLine();
		System.out.println("Escreva uma breve descrição da atividade");
		String descricao = input.nextLine();
		System.out.println("Informe o material de apoio:\n1. Arquivo com apresentacao\n2. Codigo Fonte");
		opcao = input.nextInt();
		
		do{
			switch(opcao) {
				case 1:
					material = MaterialApoio.ARQUIVO_COM_APRESENTACAO;
					flag = true;
					break;
				case 2:
					material = MaterialApoio.CODIGO_FONTE;
					flag = true;
					break;
				default:
					System.out.println("Entrada invalida!\nInforme novamente o numero do material de apoio:");
					opcao = input.nextInt();
					break;
			}
		}while(!flag);
		
		Atividade novaAtividade = new Atividade(titulo,descricao,material);
		
		while(!flag) {
			input = new Scanner(System.in);
			System.out.println("Informe o nome de usuário do participante:");
			String identificacao = input.nextLine();
			
			Usuario participante = unidade.getUsuario(identificacao);
			
			if(participante != null) 
				novaAtividade.getParticipantes().add(participante);
			else
				System.out.println("Nao foi possivel encontrar nenhum usuario com o nome de usuario fornecido!");
			
			System.out.println("Deseja continuar adicionando novos participantes?\n\t1.SIM\n\t2.NÃO");
			opcao = input.nextInt();
			if(opcao == 2)
				flag = true;
		}
		
		flag = false;
		opcao = 0;
		
		while(opcao > 3 || opcao < 1) {
			if(flag)
				System.out.println("Entrada invalida");
			flag = true;
			
			System.out.println("Informe o tipo da atividade:");
			System.out.println("1. Aula tradicional");
			System.out.println("2. Apresentacao");
			System.out.println("3. Laboratorio");
	
			opcao = input.nextInt();
		}
		
		switch(opcao) {
			case 1:
				novaAtividade.setTipoAtividade(TipoAtividade.AULA_TRADICIONAL);
				break;
			case 2:
				novaAtividade.setTipoAtividade(TipoAtividade.APRESENTACAO);
				break;
			case 3:
				novaAtividade.setTipoAtividade(TipoAtividade.LABORATORIO);
				break;
		}
		
		unidade.getAtividades().add(novaAtividade);
		System.out.println("Atividade cadastrada com sucesso");
	}
	
	public void alterarStatus(UnidadeAcademica unidade,Usuario usuario) {
		Scanner input = new Scanner(System.in);
		boolean flag = false;
		
		System.out.println("Informe o titulo da atividade que você deseja alterar o status");
		String titulo = input.nextLine();
		
		flag = unidade.alterarStatusAlocacao(titulo, usuario);
		
		
	}
	
	public void relatorio(UnidadeAcademica unidade) {
		System.out.println("Relatorio");
		
		System.out.println("Quantidade total de usuarios: " + unidade.getNumUsuarios());
		System.out.println("Quantidade total de alocacoes: " + unidade.getAlocacoes().size());
		unidade.recursosPorTipo();
		unidade.atividadesPorTipo();
	}
	
	public int menuPrincipal(Usuario usuario) {
		Scanner input = new Scanner(System.in);
		boolean flag = false;
		int opcao;
		
		do {
			if (flag) System.out.println("Entrada invalida!\n");
			flag = true;
			
			System.out.println("1. Cadastrar Usuario");
			System.out.println("2. Cadastrar Recurso");
			System.out.println("3. Cadastrar Atividade");
			System.out.println("4. Consultar");
			System.out.println("5. Alocar Recurso");
			System.out.println("6. Alterar status da alocacao");
			System.out.println("7. Relatorio");
			if(usuario == null) System.out.println("8. Login");
			System.out.println("0. Sair\n");
			System.out.print("Entre com uma opcao: ");
			
			opcao = input.nextInt();
			
		} while(opcao < 0 || (usuario == null ? opcao > 8 : opcao > 7));
		
		return opcao;
	}
	
	
}
