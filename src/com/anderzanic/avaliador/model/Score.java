package com.anderzanic.avaliador.model;

public class Score {

	private Integer porcentagem;
	
	public Score(Integer porcentagem) {
		
		if(porcentagem > 100) {
			porcentagem = 100;
		} else if(porcentagem < 0) {
			porcentagem = 0;
		}
		
		this.porcentagem = porcentagem;
	}
	
	/**
	 * 
	 * @return JSON com os valores {"porcentagem": "%", "complexidade":"complexidade", "classe":"classe css"}
	 */
	public String calcularPontuacao() {
		
		if(this.porcentagem >= 0 && this.porcentagem < 20) {
			return "{\"porcentagem\":\"" + this.porcentagem + "%\", \"complexidade\":\"Muito Fraca\", \"classe\":\"label label-danger\"}";
		} else if(this.porcentagem >= 20 && this.porcentagem < 40){
			return "{\"porcentagem\":\"" + this.porcentagem + "%\", \"complexidade\":\"Fraca\", \"classe\":\"label label-warning\"}";
		} else if(this.porcentagem >= 40 && this.porcentagem < 60){
			return "{\"porcentagem\":\"" + this.porcentagem + "%\", \"complexidade\":\"Boa\", \"classe\":\"label label-info\"}";
		} else if(this.porcentagem >= 60 && this.porcentagem < 80){
			return "{\"porcentagem\":\"" + this.porcentagem + "%\", \"complexidade\":\"Forte\", \"classe\":\"label label-info\"}";
		} else {
			return "{\"porcentagem\":\"" + this.porcentagem + "%\", \"complexidade\":\"Muito Forte\", \"classe\":\"label label-success\"}";
		}
	}
	
	@Override
	public String toString() {
		return calcularPontuacao();
	}
}
