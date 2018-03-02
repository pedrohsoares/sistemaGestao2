package sistema_gestao;

import enums.DescricaoRecurso;

/* Descricao:
 * - Laboratorio
 * - Sala de Aula
 * - Auditorio
 * - Projetor
 */

public class Recurso {
	
	private int id;
	private DescricaoRecurso descricao;
	private DataHora dataInicio;
	private DataHora dataFim;
	private Usuario responsavel;
	
	public Recurso(int id,DescricaoRecurso descricao, DataHora dataInicio, DataHora dataFim, Usuario responsavel) {
		this.id = id;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.responsavel = responsavel;
	}
	
	
	public DescricaoRecurso getDescricao() {
		return descricao;
	}
	public void setDescricao(DescricaoRecurso descricao) {
		this.descricao = descricao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DataHora getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(DataHora dataInicio) {
		this.dataInicio = dataInicio;
	}
	public DataHora getDataFim() {
		return dataFim;
	}
	public void setDataFim(DataHora dataFim) {
		this.dataFim = dataFim;
	}
	public Usuario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	
}
