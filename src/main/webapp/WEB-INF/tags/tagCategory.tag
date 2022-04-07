<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<div class="text-center">
	<c:if test="${categoria.getId()!=null}">
		<h2 class="section-heading text-uppercase">Editar Categoria</h2>
	</c:if>
	<c:if test="${categoria.getId()==null}">
		<h2 class="section-heading text-uppercase">Nova Categoria</h2>
	</c:if>
</div>