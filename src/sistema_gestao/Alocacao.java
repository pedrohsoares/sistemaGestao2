package sistema_gestao;

import enums.StatusAlocacao;

/* Status:
 * - Em processo de alocacao
 * - Alocado
 * - Em andamento
 * - Concluido
 */

public class Alocacao {
	
	private Usuario responsavel;
	private Recurso recurso;
	private StatusAlocacao status;
	private Atividade atividades;
	
	public Alocacao(Usuario responsavel, Recurso recurso, Atividade atividades) {
		
		this.responsavel = responsavel;
		this.recurso = recurso;
		this.atividades = atividades;
		this.status = StatusAlocacao.EM_PROCESSO_DE_ALOCACAO;
		
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
	public StatusAlocacao getStatus() {
		return status;
	}
	public void setStatus(StatusAlocacao status) {
		this.status = status;
	}
	public Atividade getAtividades() {
		return atividades;
	}
	public void setAtividades(Atividade atividades) {
		this.atividades = atividades;
	}
	
	
}
