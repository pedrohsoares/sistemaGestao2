package sistema_gestao;

/* Status:
 * - Em processo de alocação
 * - Alocado
 * - Em andamento
 * - Concluído
 */

public class Alocacao {
	
	private Usuario responsavel;
	private Recurso recurso;
	private String status;
	private Atividade atividades;
	
	public Alocacao(Usuario responsavel, Recurso recurso, int status, Atividade atividades) {
		
		this.responsavel = responsavel;
		this.recurso = recurso;
		this.atividades = atividades;
		
		switch (status) {
			case 1:
				this.status = "Em processo de alocação";
				break;
			case 2:
				this.status = "Alocado";
				break;
			case 3:
				this.status = "Em andamento";
				break;
			case 4:
				this.status = "Concluído";
				break;
		}
	}
	
	
	
	
	
	

	public Usuario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
	public Recurso getRecurso() {
		return recurso;
	}
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Atividade getAtividades() {
		return atividades;
	}
	public void setAtividades(Atividade atividades) {
		this.atividades = atividades;
	}
	
	
}
