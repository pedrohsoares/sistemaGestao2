package sistema_gestao;

public class SistemaGestao {
	static Menu menu = new Menu();
	static Usuario usuarioAtivo = null;
	
	// executa as funcoes do menu principal
	public static void executarFuncao(int opcao, UnidadeAcademica unidade) {
		
		switch(opcao) {
			case 1:
				unidade.cadastrarUsuario(menu.cadastrarUsuario(usuarioAtivo));
				break;
			case 2:
				menu.cadastrarRecurso(unidade, usuarioAtivo);
				break;
			case 3:
				menu.cadastrarAtividade(unidade);
				break;
			case 4:
				menu.consultar(unidade);
				break;
			case 5:
				menu.alocarRecurso(unidade, usuarioAtivo);
				break;
			case 6:
				menu.alterarStatus(unidade,usuarioAtivo);
				break;
			case 7:
				menu.relatorio(unidade);
				break;
			case 8:
				usuarioAtivo = menu.login(unidade);
				break;
			case 0:
				usuarioAtivo = null;
				break;
		}
	}
	
	public static void main(String[] args) {
		
		// instanciando uma unidade academica
		UnidadeAcademica ic = new UnidadeAcademica();
		
		boolean running = true; // flag que controla a execucao do sistema
		
		System.out.println("---------------------------");
		System.out.println("         BEM VINDO!        ");
		System.out.println("---------------------------");
		System.out.println();
		
		while (running) { //loop da execucao do sistema
			int opcao;
			
			opcao = menu.menuPrincipal(usuarioAtivo);
			executarFuncao(opcao, ic);
			running = menu.continuarExecucao();
		}
		
	}
}
