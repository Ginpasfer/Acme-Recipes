<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="false"> 
	<acme:input-textbox code="chef.pimpam.list.label.titulo" path="titulo"/>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">	
    		<acme:input-textbox code="chef.pimpam.form.label.codigo" path="codigo"/>
    	</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-textbox readonly="true" code="chef.pimpam.form.label.codigo" path="codigo"/>
		</jstl:when>
	</jstl:choose>
	<acme:input-moment readonly="true" code="chef.pimpam.form.label.fechaCreacion" path="fechaCreacion"/>
	<acme:input-textarea code="chef.pimpam.form.label.descripcion" path="descripcion"/>
	<acme:input-moment code="chef.pimpam.form.label.periodoInicial" path="periodoInicial" />
	<acme:input-moment code="chef.pimpam.form.label.periodoFinal" path="periodoFinal" />
	<acme:input-money code="chef.pimpam.form.label.presupuesto" path="presupuesto"/>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-select code="chef.pimpam.list.label.item" path="itemId">
	   			<jstl:forEach items="${items}" var="i">
					<acme:input-option code="${i.getName()}" value="${i.getId()}" selected="${ i.getId() == itemId }"/>
				</jstl:forEach>
			</acme:input-select>
		</jstl:when>
	</jstl:choose>
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-textbox readonly="true" code="chef.pimpam.list.label.item" path="itemName"/>
		</jstl:when>
	</jstl:choose>
	<%-- <jstl:choose>
		<jstl:when test="${command =='show'}">
			<acme:input-money readonly="true" code="epicure.dish.form.label.money" path="money" />
		</jstl:when>
	</jstl:choose> --%>
	<acme:input-textbox code="chef.pimpam.form.label.enlace" path="enlace"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">		
			<acme:submit code="chef.pimpam.form.button.update" action="/chef/pimpam/update"/>
			<acme:submit code="chef.pimpam.form.button.delete" action="/chef/pimpam/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.pimpam.form.button.create" action="/chef/pimpam/create"/>
		</jstl:when>	
	</jstl:choose>
		
</acme:form>

