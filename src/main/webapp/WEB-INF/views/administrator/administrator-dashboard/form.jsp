<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h1>
	<acme:message code="administrator.dashboard.form.label.title" />
</h1>

<h2>
	<acme:message
		code="administrator.dashboard.form.label.totalNumberOfItemWithDelor" />
	<acme:print value="${ totalNumberOfItemWithDelor }" />
</h2>

<table class="table table-sm">
	<jstl:forEach items="${ currencyP }" var="currency">

		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ averageBudgetOfDelorGroupedByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row">
						<acme:message code="administrator.dashboard.form.label.averageBudgetOfDelorGroupedByCurrency" />
						<acme:print value="${ entry.getValue() }" />
					</th>

				</jstl:forEach>
			</jstl:if>
		</tr>
		
		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ deviationBudgetOfDelorGroupedByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row">
						<acme:message code="administrator.dashboard.form.label.deviationBudgetOfDelorGroupedByCurrency"/>
						<acme:print value="${ entry.getValue() }" />
					</th>

				</jstl:forEach>
			</jstl:if>
		</tr>
		
		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ minimunBudgetOfDelorGroupedByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row">
						<acme:message code="administrator.dashboard.form.label.minimunBudgetOfDelorGroupedByCurrency"/>
						<acme:print value="${ entry.getValue() }" />
					</th>

				</jstl:forEach>
			</jstl:if>
		</tr>
		
		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ maximunBudgetOfDelorGroupedByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row">
						<acme:message code="administrator.dashboard.form.label.maximunBudgetOfDelorGroupedByCurrency"/>
						<acme:print value="${ entry.getValue() }" />
					</th>

				</jstl:forEach>
			</jstl:if>
		</tr>
		<tr><th></th><th></th></tr>
	</jstl:forEach>
</table>




<h2>
	<acme:message
		code="administrator.dashboard.form.label.totalNumberOfKitchenUtensils" />
	<acme:print value="${ totalNumberOfKitchenUtensils }" />
</h2>

<table class="table table-sm">
	<jstl:forEach items="${ currencyU }" var="currency">

		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ averageRetailPriceOfKitchenUtensilsGroupedByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row">
						<acme:message code="administrator.dashboard.form.label.averageRetailPriceOfKitchenUtensilsGroupedByCurrency" />
						<acme:print value="${ entry.getValue() }" />
					</th>

				</jstl:forEach>
			</jstl:if>
		</tr>
		
		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ deviationRetailPriceOfKitchenUtensilsGroupedByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row">
						<acme:message code="administrator.dashboard.form.label.deviationRetailPriceOfKitchenUtensilsGroupedByCurrency"/>
						<acme:print value="${ entry.getValue() }" />
					</th>

				</jstl:forEach>
			</jstl:if>
		</tr>
		
		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ minimunRetailPriceOfKitchenUtensilsGroupedByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row">
						<acme:message code="administrator.dashboard.form.label.minimunRetailPriceOfKitchenUtensilsGroupedByCurrency"/>
						<acme:print value="${ entry.getValue() }" />
					</th>

				</jstl:forEach>
			</jstl:if>
		</tr>
		
		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ maximunRetailPriceOfKitchenUtensilsGroupedByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row">
						<acme:message code="administrator.dashboard.form.label.maximunRetailPriceOfKitchenUtensilsGroupedByCurrency"/>
						<acme:print value="${ entry.getValue() }" />
					</th>

				</jstl:forEach>
			</jstl:if>
		</tr>
		<tr><th></th><th></th></tr>
	</jstl:forEach>
</table>

<h2>
	<acme:message
		code="administrator.dashboard.form.label.totalNumberOfIngredients" />
	<acme:print value="${ totalNumberOfIngredients }" />
</h2>

<table class="table table-sm">
	<jstl:forEach items="${ currencyI }" var="currency">

		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ averageRetailPriceOfIngredientsGroupedByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row">
						<acme:message code="administrator.dashboard.form.label.averageRetailPriceOfIngredientsGroupedByCurrency" />
						<acme:print value="${ entry.getValue() }" />
					</th>

				</jstl:forEach>
			</jstl:if>
		</tr>
		
		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ deviationRetailPriceOfIngredientsGroupedByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row">
						<acme:message code="administrator.dashboard.form.label.deviationRetailPriceOfIngredientsGroupedByCurrency"/>
						<acme:print value="${ entry.getValue() }" />
					</th>

				</jstl:forEach>
			</jstl:if>
		</tr>
		
		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ minimunRetailPriceOfIngredientsGroupedByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row">
						<acme:message code="administrator.dashboard.form.label.minimunRetailPriceOfIngredientsGroupedByCurrency"/>
						<acme:print value="${ entry.getValue() }" />
					</th>

				</jstl:forEach>
			</jstl:if>
		</tr>
		
		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ maximunRetailPriceOfIngredientsGroupedByCurrency.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row">
						<acme:message code="administrator.dashboard.form.label.maximunRetailPriceOfIngredientsGroupedByCurrency"/>
						<acme:print value="${ entry.getValue() }" />
					</th>

				</jstl:forEach>
			</jstl:if>
		</tr>
		<tr><th></th><th></th></tr>
	</jstl:forEach>
</table>


<h2>
	<acme:message code="administrator.dashboard.form.title.about-proposed-dish"/>
</h2>
<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageBudgetOfFineDishesProposed"/>
		</th>
		<td>
			<acme:print value="${averageBudgetOfFineDishesProposed}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviationBudgetOfFineDishesProposed"/>
		</th>
		<td>
			<acme:print value="${deviationBudgetOfFineDishesProposed}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimunBudgetOfFineDishesProposed"/>
		</th>
		<td>
			<acme:print value="${minimunBudgetOfFineDishesProposed}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximunBudgetOfFineDishesProposed"/>
		</th>
		<td>
			<acme:print value="${maximunBudgetOfFineDishesProposed}"/>
		</td>
	</tr>
</table>
<h2>
	<acme:message code="administrator.dashboard.form.title.about-accepted-dish"/>
</h2>
<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageBudgetOfFineDishesAccepted"/>
		</th>
		<td>
			<acme:print value="${averageBudgetOfFineDishesAccepted}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviationBudgetOfFineDishesAccepted"/>
		</th>
		<td>
			<acme:print value="${deviationBudgetOfFineDishesAccepted}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimunBudgetOfFineDishesAccepted"/>
		</th>
		<td>
			<acme:print value="${minimunBudgetOfFineDishesAccepted}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximunBudgetOfFineDishesAccepted"/>
		</th>
		<td>
			<acme:print value="${maximunBudgetOfFineDishesAccepted}"/>
		</td>
	</tr>
</table>
<h2>
	<acme:message code="administrator.dashboard.form.title.about-denied-dish"/>
</h2>
<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageBudgetOfFineDishesDenied"/>
		</th>
		<td>
			<acme:print value="${averageBudgetOfFineDishesDenied}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviationBudgetOfFineDishesDenied"/>
		</th>
		<td>
			<acme:print value="${deviationBudgetOfFineDishesDenied}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimunBudgetOfFineDishesDenied"/>
		</th>
		<td>
			<acme:print value="${minimunBudgetOfFineDishesDenied}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximunBudgetOfFineDishesDenied"/>
		</th>
		<td>
			<acme:print value="${maximunBudgetOfFineDishesDenied}"/>
		</td>
	</tr>
</table>
<h2>
	<acme:message code="administrator.dashboard.form.title.dish-by-status"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"PROPOSED", "ACCEPTED", "DENIED"
			],
			datasets : [
				{
					data : [
						<jstl:out value="${totalNumberOfProposedFineDishes}"/>, 
						<jstl:out value="${totalNumberOfAcceptedFineDishes}"/>, 
						<jstl:out value="${totalNumberOfDeniedFineDishes}"/>
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0,
							suggestedMax : 100
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<acme:return/>
