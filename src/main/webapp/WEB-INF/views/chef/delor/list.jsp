<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.delor.list.label.subject" path="subject"/>
	<acme:list-column code="chef.delor.list.label.item" path="itemName"/>
</acme:list>
