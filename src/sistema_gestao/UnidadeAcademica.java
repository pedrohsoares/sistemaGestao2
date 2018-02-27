package sistema_gestao;

import java.util.ArrayList;

public class UnidadeAcademica {
	
	public String nome = "UFAL";
	public ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	public ArrayList<Recurso> recursos = new ArrayList<Recurso>();
	public ArrayList<Alocacao> alocacoes = new ArrayList<Alocacao>();
	public ArrayList<Atividade> atividades = new ArrayList<Atividade>();
	
	private int num_usuarios = 0;
	private int num_recursos = 0;
	
	//--- 
	
	public void addUsuario(Usuario usuario) {
		
		this.usuarios.add(usuario);
		this.num_usuarios += 1;
	}
	
	public int getNum_usuarios() {
		return num_usuarios;
	}
	
	public void setNum_usuarios(int num_usuarios) {
		this.num_usuarios = num_usuarios;
	}
	
	public int getNum_recursos() {
		return num_recursos;
	}
	
	public void setNum_recursos(int num_recursos) {
		this.num_recursos = num_recursos;
	}
	
}
