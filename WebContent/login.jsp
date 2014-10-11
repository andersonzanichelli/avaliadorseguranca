<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="web/css/bootstrap.min.css">
	<link rel="stylesheet" href="web/css/layout.css">
	<script src="web/js/jquery-1.11.1.min.js"></script>
	<script src="web/js/bootstrap.min.js"></script>
	<script src="web/js/avaliador.js"></script>
	<title>Avaliador de Segurança</title>
	</head>
	<body>
		<div id="externa" class="panel panel-default">
			<h3>Avaliador de Segurança de Senha</h3>
 	 		
 	 		<div id="interna" class="panel-body breadcrumb">
				<input type="password" id="password" name="password" placeholder="Senha" />
				<br />
				<span id="porcentagem" class="label label-default" >0%</span>
				<span id="complexidade" class="label label-danger" >Muito curta</span>
			</div>
		</div>
	</body>
</html>