package sistema_gestao;

import java.util.Scanner;

public class SistemaGestao {
	
	// executa as funções do menu principal
	public static void executeFunction(int opcao, UnidadeAcademica unidade) {
		
		switch(opcao) {
			case 1:
				cadastrarUsuario(unidade);
				break;
			case 2:
				// code
				break;
			case 3:
				// code
				break;
			case 4:
				// code
				break;
			case 5:
				// code
				break;
			case 0:
				break;
		}
	}
	
	// verifica se o tipo de usuário é válido
	private static boolean isValidType(int opcao) {
		return (opcao >= 1 && opcao <= 6); 
	}
	
	private static boolean isValidOption(int opcao) {
		return (opcao >= 0 && opcao <= 5);
	}
	
	private static boolean isValidOptionContinue(int opcao) {
		return (opcao == 1 || opcao == 0);
	}
	
	// cadastra um usuário na unidade academica
	public static void cadastrarUsuario(UnidadeAcademica unidade) {
		
		Scanner input = new Scanner(System.in);
		String nome;
		String curso;
		String login;
		String senha;
		int tipo;
		
		boolean flag = false; // flag para exibir a mensagem de erro
		
		System.out.print("Digite o nome do usuário: ");
		nome = input.nextLine();
		System.out.print("Digite o curso: ");
		curso = input.nextLine();
		System.out.print("Digite um user name: ");
		login = input.nextLine();
		System.out.print("Digite uma senha: ");
		senha = input.nextLine();
		System.out.println();
		
		do {
			if (flag) {
				System.out.println("Entrada errada. Digite uma entrada válida!");
				System.out.println();
			}
			flag = true;
			
			System.out.println("1. Administrador");
			System.out.println("2. Professor");
			System.out.println("3. Pesquisador");
			System.out.println("4. Aluno Doutorado");
			System.out.println("5. Aluno Mestrado");
			System.out.println("6. Aluno Graduação");
			System.out.print("Entre com o tipo do usuário: > ");
			tipo = input.nextInt();
		} while(!isValidType(tipo));
		
		// instancia um novo usuario
		Usuario new_user = new Usuario(nome, curso, tipo, login, senha);
		
		// seta o id do usuario baseado no num de usuarios cadastrados na unidade
		new_user.setId(unidade.getNum_usuarios());
		
		// adiciona o novo usuário cadastrado no array de usuários
		unidade.usuarios.add(new_user);
		
		// incrementa o numero de usuarios da unidade
		unidade.setNum_usuarios(unidade.getNum_usuarios() + 1);
		
		System.out.printf("%nUsuário cadastrado com sucesso!%n");
	}
	
	// Exibe o menu principal 
	public static int menuPrincipal() {
		
		Scanner input = new Scanner(System.in);
		boolean flag = false;
		int opcao;
		
		do { // loop da validação da entrada
		
			if (flag) System.out.println("Entrada inválida!%n");
			flag = true; // flag para exibir a mensagem de erro
			
			System.out.println("1. Cadastrar Usuário");
			System.out.println("2. Login");
			System.out.println("3. Consultar");
			System.out.println("4. Alocar Recurso");
			System.out.println("5. Cadastrar Recurso");
			System.out.println("0. Sair");
			System.out.println("");
			System.out.print("Entre com uma opção: > ");
			
			opcao = input.nextInt();
			
		} while(!isValidOption(opcao));
		flag = false;
		
		return opcao;
	}
	
	public static boolean continuarExecucao() {
		
		Scanner input = new Scanner(System.in);
		int opcao;
		boolean flag = false; // flag para a mensagem de erro 
		
		do {
			if (flag) System.out.println("Entrada inválida%n");
			flag = true;
			
			System.out.printf("%n1. Sim");
			System.out.printf("%n0. Não%n%n");
			System.out.print("Deseja realiza mais alguma operação? > ");
			
			opcao = input.nextInt();
			
		} while(!isValidOptionContinue(opcao));
		
		if (opcao == 1) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		// instanciando uma unidade academica
		UnidadeAcademica ic = new UnidadeAcademica();
		
		// cadastrando alguns usuários
		ic.usuarios.add(new Usuario("Jonas", "Ciência da Computação", 1, "admin", "1234"));
		ic.usuarios.add(new Usuario("Baldoino", "Ciência da Computação", 2, "badu", "badu0"));
		ic.usuarios.add(new Usuario("Jarbas", "Engenharia da Computação", 3, "jarbas", "jarbas0"));
		ic.usuarios.add(new Usuario("Wadd", "Ciência da Computação", 6, "wadd", "wadd0"));
		ic.usuarios.add(new Usuario("Sam", "Engenharia da Computação", 5, "sam", "sam0"));
		
		// cadastrando alguns recursos
		
		
		boolean running = true; // flag que controla a execução do sistema
		
		System.out.println("---------------------------");
		System.out.println("         BEM VINDO!        ");
		System.out.println("---------------------------");
		System.out.println();
		
		Scanner input = new Scanner(System.in);
		
		while (running) { //loop da execução do sistema
			
			int opcao;
			
			opcao = menuPrincipal();
			executeFunction(opcao, ic);
			running = continuarExecucao();
		}
		
	}
}
