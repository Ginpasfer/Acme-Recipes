<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:choose>
	<jstl:when test="${command == 'create'}">
		<jstl:if test="${items.isEmpty()==false}">
			<acme:input-textbox code="chef.itemQuantity.form.label.amount" path="amount"/>	
				<acme:input-select path="items" code="chef.itemQuantity.form.label.items">
					<jstl:forEach items="${items}" var="item">
						<acme:input-option code="${item.name} - ${item.itemType} - ${item.retailPrice} " value="${item.id}" />
					</jstl:forEach>
				</acme:input-select>
			<acme:submit code="chef.itemQuantity.form.button.create" action="/chef/item-quantity/create?masterId=${masterId}"/>
		</jstl:if>
		<jstl:if test="${items.isEmpty()==true}">
				<h3 style="color: red"><acme:message code="chef.item.no-item"/></h3>
				<acme:button code="chef.item.create-item-no-item" action="/chef/item/create"/>
		</jstl:if>
	</jstl:when>
		
	<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
		<acme:input-textbox code="chef.itemQuantity.form.label.amount" path="amount" readonly="${published == true}"/>
	
		<acme:input-textbox code="chef.itemQuantity.form.label.item.name" path="item.name" readonly="${true}"/>
		<acme:input-textbox code="chef.itemQuantity.form.label.item.type" path="item.itemType" readonly="${true}"/>
		<acme:input-textbox code="chef.itemQuantity.form.label.item.code" path="item.code" readonly="${true}"/>
		<jstl:if test="${published == false}">
			<acme:submit code="chef.itemQuantity.form.button.update" action="/chef/item-quantity/update"/>
			<acme:submit code="chef.itemQuantity.form.button.delete" action="/chef/item-quantity/delete"/>			
		</jstl:if>
	</jstl:when>
	</jstl:choose>
</acme:form>
