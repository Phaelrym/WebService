<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<c:url var="home" value="/" scope="request" />
<spring:url value="/resources/core/img/favicon.ico" var="favicon" />
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<spring:url value="/resources/core/js/jquery-3.1.1.min.js"
	var="jqueryJs" />
<link rel="shortcut icon" href="${favicon}">
<link href="${bootstrapCss}" rel="stylesheet" />
<script src="${jqueryJs}"></script>
</head>

<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Team Name</a>
		</div>
	</div>
</nav>

<div class="container">
	<div>
		<h1 class="text-center">Team Member</h1>
		<br>
		<div id="feedbackFullTeam"></div>
		<div id="feedback"></div>

		<form class="form-horizontal" id="search-teamMember-form">
			<div class="form-group form-group-lg">
				<label class="col-sm-2 control-label">Prénom</label>
				<div class="col-sm-10">
					<input type=text class="form-control" id="firstname">
				</div>
			</div>
			<div class="form-group form-group-lg">
				<label class="col-sm-2 control-label">Nom</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="lastname">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" id="bth-search"
						class="btn btn-primary btn-lg pull-right">Rechercher</button>
				</div>
			</div>
		</form>

	</div>

</div>


<script>
	jQuery(document).ready(function($) {
		getFullTeam();
		$("#search-teamMember-form").submit(function(event) {
			enableSearchButton(false);
			event.preventDefault();
			searchTeamMember();
		});
	});

	function getFullTeam() {
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}search/api/getSearchFullTeam",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				displayResultSearchFullTeam(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				displayResultSearchFullTeam(e);
			},
			done : function() {
				console.log("DONE");
				enableSearchButton(true);
			}
		});
	}

	function searchTeamMember() {
		var search = {}
		search["firstName"] = $("#firstname").val();
		search["lastName"] = $("#lastname").val();
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}search/api/getSearchTeamMember",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				displayResultSearchTeamMember(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				displayResultSearchTeamMember(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});

	}

	function enableSearchButton(flag) {
		$("#btn-search").prop("disabled", flag);
	}

	function displayResultSearchTeamMember(data) {
		var json = "<h4>Résultat de la recherche</h4><pre>"
				+ JSON.stringify(data, null, 2) + "</pre>";
		$('#feedback').html(json);
	}

	function displayResultSearchFullTeam(data) {

		var finalResult = "<div class=\"row\">";
		$.each(data.result, function(i, item) {
			finalResult += "<div class=\"col-sm-6 col-md-3\">"
					+ "<div class=\"thumbnail\">"
					+ "<img src=\""+item.imageLink+"\">"
					+ "<div class=\"caption text-center\">" + "<h4>" + item.firstName + " "
					+ item.lastName + "</h4>" + "<p>"+item.presentation+"</p>"
					+ "</div></div></div>";
		});
		finalResult += "</div>";

		$('#feedbackFullTeam').html(finalResult);
	}
</script>

</body>
</html>