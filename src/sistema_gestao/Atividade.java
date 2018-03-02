package sistema_gestao;

import java.util.ArrayList;

import enums.MaterialApoio;
import enums.TipoAtividade;

/* Material de Apoio:
 * - Arquivo com apresentacao
 * - Codigo fonte
 * 
 * Tipo Atividade:
 * - Aula tradicinal
 * - Apresentacao
 * - Laboratorio
 */

public class Atividade {
	private String titulo;
	private TipoAtividade tipoAtividade;
	private String descricao;
	private ArrayList<Usuario> participantes = new ArrayList<Usuario>();
	private MaterialApoio materialApoio;
	
	public Atividade(String titulo, String descricao, MaterialApoio materialApoio) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.materialApoio = materialApoio;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}
	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ArrayList<Usuario> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(ArrayList<Usuario> participantes) {
		this.participantes = participantes;
	}
	public MaterialApoio getMaterialApoio() {
		return materialApoio;
	}
	public void setMaterialApoio(MaterialApoio materialApoio) {
		this.materialApoio = materialApoio;
	}
}
