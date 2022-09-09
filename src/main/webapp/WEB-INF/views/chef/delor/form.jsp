<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="false"> 
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<jstl:if test="${items.isEmpty()==false}">
				<acme:input-textbox code="chef.delor.list.label.subject" path="subject"/>
				<acme:input-textbox readonly="true" code="chef.delor.form.label.keylet" path="keylet"/>
				<acme:input-moment readonly="true" code="chef.delor.form.label.instantiationMoment" path="instantiationMoment"/>
				<acme:input-textarea code="chef.delor.form.label.explanation" path="explanation"/>
				<acme:input-moment code="chef.delor.form.label.initialPeriod" path="initialPeriod" />
				<acme:input-moment code="chef.delor.form.label.finalPeriod" path="finalPeriod" />
				<acme:input-money code="chef.delor.form.label.income" path="income"/>
				<acme:input-select code="chef.delor.list.label.item" path="itemId">
	   				<jstl:forEach items="${items}" var="i">
						<acme:input-option code="${i.getName()}" value="${i.getId()}" selected="${ i.getId() == itemId }"/>
					</jstl:forEach>
				</acme:input-select>
				<acme:input-textbox code="chef.delor.form.label.moreInfo" path="moreInfo"/>
				<acme:submit code="chef.delor.form.button.create" action="/chef/delor/create"/>
			</jstl:if>
			<jstl:if test="${items.isEmpty()==true}">
				<h3 style="color: red"><acme:message code="chef.item.no-item"/></h3>
				<acme:button code="chef.item.create-item-no-item" action="/chef/item/create"/>
			</jstl:if>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-textbox code="chef.delor.list.label.subject" path="subject"/>
			<acme:input-textbox readonly="true" code="chef.delor.form.label.keylet" path="keylet"/>
			<acme:input-moment readonly="true" code="chef.delor.form.label.instantiationMoment" path="instantiationMoment"/>
			<acme:input-textarea code="chef.delor.form.label.explanation" path="explanation"/>
			<acme:input-moment code="chef.delor.form.label.initialPeriod" path="initialPeriod" />
			<acme:input-moment code="chef.delor.form.label.finalPeriod" path="finalPeriod" />
			<acme:input-money code="chef.delor.form.label.income" path="income"/>
			<jstl:choose>
				<jstl:when test="${command =='show'}">
					<acme:input-money readonly="true" code="epicure.dish.form.label.money" path="money" />
				</jstl:when>
			</jstl:choose>
			<acme:input-textbox readonly="true" code="chef.delor.list.label.item" path="itemName"/>
			<acme:input-textbox code="chef.delor.form.label.moreInfo" path="moreInfo"/>
			<jstl:if test="${itemPublished==false}">
				<acme:submit code="chef.delor.form.button.update" action="/chef/delor/update"/>
				<acme:submit code="chef.delor.form.button.delete" action="/chef/delor/delete"/>
			</jstl:if>
		</jstl:when>
	</jstl:choose>	
</acme:form>

