var avaliador = {};

avaliador.componentes = function() {
	avaliador.password = $('#password');
	avaliador.porcentagem = $('#porcentagem');
	avaliador.complexidade = $('#complexidade');
};

avaliador.init = function() {
	avaliador.componentes();
	avaliador.password.on('keyup', avaliador.avaliar);
};

avaliador.avaliar = function(){
	$.ajax({
		url: 'avaliadorController',
		data: {"password": avaliador.password.val()},
		type: 'post',
		success: avaliador.success
	});
}

avaliador.success = function(data){
	if(data) {
		var resposta = $.parseJSON(data);
		avaliador.porcentagem.html(resposta.porcentagem);
		avaliador.complexidade.html(resposta.complexidade);
		avaliador.complexidade.removeClass();
		avaliador.complexidade.addClass(resposta.classe);
	}
}

$(document).ready(function() {
	avaliador.init();
});