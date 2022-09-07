<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Epicure,acme.roles.Chef"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">	
			<acme:menu-suboption code="master.menu.any.item.ingredient" action="/any/item/list-ingredients"/>
			<acme:menu-suboption code="master.menu.any.item.kitchen_utensils" action="/any/item/list-kitchenUtensils"/>
			<acme:menu-suboption code="master.menu.any.recipe" action="/any/recipe/list"/>
			<acme:menu-suboption code="master.menu.anonymous.list-user-accounts" action="/any/user-account/list"/>
			<acme:menu-suboption code="master.menu.anonymous.list-peeps" action="/any/peep/list"/>
		</acme:menu-option>
		
		
		<acme:menu-option code="master.menu.anonymous.team" access="isAnonymous()">	
			<acme:menu-suboption code="master.menu.anonymous.alvparbor1" action="https://github.com/alvaro-us"/>
			<acme:menu-suboption code="master.menu.anonymous.pablo" action="https://github.com/Pabgiralv"/>
			<acme:menu-suboption code="master.menu.anonymous.ginpasfer" action="https://github.com/Ginpasfer"/>
			<acme:menu-suboption code="master.menu.anonymous.antonio" action="https://github.com/antsolismir"/>
			<acme:menu-suboption code="master.menu.anonymous.badayco" action="https://github.com/badrijher"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/administrator-dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.bulletin" action="/administrator/bulletin/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.configuration.show" action="/administrator/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>		
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
		<acme:menu-suboption code="master.menu.authenticated.systemConfiguration.show" action="/authenticated/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.authenticated.bulletin" action="/authenticated/bulletin/list"/>
			<acme:menu-suboption code="master.menu.user-account.chef.pimpam.list" action="/authenticated/pimpam/list"/>
		</acme:menu-option>
		
		
		<acme:menu-option code="master.menu.epicure" access="hasRole('Epicure')">
			<acme:menu-suboption code="master.menu.epicure.dashboard" action="/epicure/epicure-dashboard/show"/>
			<acme:menu-suboption code="master.menu.epicure.dish.list" action="/epicure/dish/list"/>
			<acme:menu-suboption code="master.menu.epicure.memorandum.list" action="/epicure/memorandum/list-all"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.chef" access="hasRole('Chef')">
			<acme:menu-suboption code="master.menu.chef.item.create" action="/chef/item/create"/>
			<acme:menu-suboption code="master.menu.chef.pimpam.create" action="/chef/pimpam/create"/>		
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.chef.dish-list" action="/chef/dish/list"/>
			<acme:menu-suboption code="master.menu.user-account.chef.item.list.ingredient" action="/chef/item/list-ingredients"/>
			<acme:menu-suboption code="master.menu.user-account.chef.item.list.kitchenUtensils" action="/chef/item/list-kitchen_utensils"/>
			<acme:menu-suboption code="master.menu.user-account.chef.pimpam.list" action="/chef/pimpam/list"/>
			<acme:menu-suboption code="master.menu.user-account.chef.item.list.recipe" action="/chef/recipe/list"/>
			<acme:menu-suboption code="master.menu.chef.memorandum-list" action="/chef/memorandum/list-all"/> 
			
		</acme:menu-option>
		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-epicure" action="/authenticated/epicure/create" access="!hasRole('Epicure')"/>
			<acme:menu-suboption code="master.menu.user-account.epicure" action="/authenticated/epicure/update" access="hasRole('Epicure')"/>
			<acme:menu-suboption code="master.menu.user-account.become-chef" action="/authenticated/chef/create" access="!hasRole('Chef')"/>
			<acme:menu-suboption code="master.menu.user-account.chef" action="/authenticated/chef/update" access="hasRole('Chef')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

