<%@ page import="org.rti.zcore.utils.DateUtils"%>
<%@ taglib uri='/WEB-INF/struts-tiles.tld' prefix='template'%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/zeprs.tld" prefix="zeprs"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="sidebar-a-print">
<div id="sidenavcontainer">
<ul id="navlist">
	<c:forEach var='item' items='${menuItemList}'>
		<c:choose>
			<c:when test="${!empty item.roleName && item.roleName != ' '}">
				<logic:present role="${item.roleName}">
					<c:choose>
						<c:when test="${empty item.url}">
						<c:choose>
							<c:when test="${template == 'patient'}">
								<li>&nbsp;</li>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${item.client == true}">
									</c:when>
									<c:when test="${empty item.client || item.client != true}">
										<li>&nbsp;</li>
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
						<c:when
							test="${!empty item.flowIdParam && item.patientIdParam == true && template == 'patient'}">
							<li><a
								href='/${appName}/<c:url value="${item.url}"><c:param name="flowId" value="${item.flowIdParam}"/><c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/></c:url>'>
							<bean:message key="${item.templateKey}" bundle="TemplateResources" /></a></li>
						</c:when>
						<c:when
							test="${!empty item.flowIdParam && item.patientIdParam != true && template != 'patient'}">
							<li><a
								href='/${appName}/<c:url value="${item.url}"><c:param name="flowId" value="${item.flowIdParam}"/></c:url>'>
							<bean:message key="${item.templateKey}" bundle="TemplateResources" /></a>
							</li>
						</c:when>
						<c:when
							test="${empty item.flowIdParam && item.patientIdParam == true && template == 'patient'}">
							<li><a
								href='/${appName}/<c:url value="${item.url}"><c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/></c:url>'>
							<bean:message key="${item.templateKey}" bundle="TemplateResources" /></a>
							</li>
						</c:when>
						<c:when
							test="${!empty item.flowIdParam && item.patientIdParam != true && template != 'patient'}">
							<li><a href='/${appName}/<c:url value="${item.url}"/>'> <bean:message
								key="${item.templateKey}" bundle="TemplateResources" /></a>
							</li>
						</c:when>
						<c:when
							test="${empty item.flowIdParam && item.patientIdParam != true && !empty item.url && empty item.formIdParam}">
							<li><a href='/${appName}/<c:url value="${item.url}"/>'> <bean:message
								key="${item.templateKey}" bundle="TemplateResources" /></a>
							</li>
						</c:when>
						<c:when
							test="${empty item.flowIdParam && item.patientIdParam != true && (item.client != true && template != 'patient') && !empty item.url && !empty item.formIdParam}">
							<li><a
								href='/${appName}/<c:url value="${item.url}"><c:param name="formId" value="${item.formIdParam}"/></c:url>'>
							<bean:message key="${item.templateKey}" bundle="TemplateResources" /></a>
							</li>

						</c:when>
			</c:choose>
					</c:otherwise>
				</c:choose>
			</logic:present>
		</c:when>
		<c:otherwise>
			<c:choose>
					<c:when test="${empty item.url}">
						<c:choose>
							<c:when test="${template == 'patient'}">
								<li>&nbsp;</li>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${item.client == true}">
									</c:when>
									<c:when test="${item.client != true}">
										<li>&nbsp;</li>
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
					<c:choose>
						<c:when
							test="${!empty item.flowIdParam && item.patientIdParam == true && template == 'patient'}">
							<li><a
								href='/${appName}/<c:url value="${item.url}"><c:param name="flowId" value="${item.flowIdParam}"/><c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/></c:url>'>
							<bean:message key="${item.templateKey}" bundle="TemplateResources" /></a></li>
						</c:when>
						<c:when
							test="${!empty item.flowIdParam && item.patientIdParam != true && template != 'patient'}">
							<li><a
								href='/${appName}/<c:url value="${item.url}"><c:param name="flowId" value="${item.flowIdParam}"/></c:url>'>
							<bean:message key="${item.templateKey}" bundle="TemplateResources" /></a>
							</li>
						</c:when>
						<c:when
							test="${empty item.flowIdParam && item.patientIdParam == true && template == 'patient'}">
							<li><a
								href='/${appName}/<c:url value="${item.url}"><c:param name="patientId" value="${zeprs_session.sessionPatient.id}"/></c:url>'>
							<bean:message key="${item.templateKey}" bundle="TemplateResources" /></a>
							</li>
						</c:when>
						<c:when
							test="${!empty item.flowIdParam && item.patientIdParam != true && template != 'patient'}">
							<li><a href='/${appName}/<c:url value="${item.url}"/>'> <bean:message
								key="${item.templateKey}" bundle="TemplateResources" /></a>
							</li>
						</c:when>
						<c:when
							test="${empty item.flowIdParam && item.patientIdParam != true && !empty item.url && empty item.formIdParam}">
							<li><a href='/${appName}/<c:url value="${item.url}"/>'> <bean:message
								key="${item.templateKey}" bundle="TemplateResources" /></a>
							</li>
						</c:when>
						<c:when
							test="${empty item.flowIdParam && item.patientIdParam != true && (item.client == true && template == 'patient') && !empty item.url && !empty item.formIdParam}">
							<li><a
								href='/${appName}/<c:url value="${item.url}"><c:param name="formId" value="${item.formIdParam}"/></c:url>'>
							<bean:message key="${item.templateKey}" bundle="TemplateResources" /></a>
							</li>
						</c:when>
						<c:when
							test="${empty item.flowIdParam && item.patientIdParam != true && (item.client != true && template != 'patient') && !empty item.url && !empty item.formIdParam}">
							<li><a
								href='/${appName}/<c:url value="${item.url}"><c:param name="formId" value="${item.formIdParam}"/></c:url>'>
							<bean:message key="${item.templateKey}" bundle="TemplateResources" /></a>
							</li>
						</c:when>
			</c:choose>
		</c:otherwise>
		</c:choose>
		</c:otherwise>
		</c:choose>
	</c:forEach>
	<li style="margin: 0px 0px 0px 5px;">&nbsp;</li>
	<li><html:link action="/logout.do">Logout</html:link></li>
</ul>
<!-- navlist --></div>
<!-- sidenavcontainer --></div>
<!-- sidebar-a -->