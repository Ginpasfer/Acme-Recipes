<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.pimpam.list.label.titulo" path="titulo"/>
	<acme:list-column code="chef.pimpam.list.label.item" path="itemName"/>
</acme:list>
