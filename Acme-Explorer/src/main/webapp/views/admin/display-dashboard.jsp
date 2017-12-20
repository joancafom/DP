<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>

<p>
<spring:message code="statistic.avg" /> <spring:message code="statistic.numberApplicationsTrip" />: <jstl:out value="${tripApplicationsPerTripStatistics[0]}" />
</p>
<p> 
<spring:message code="statistic.min" /> <spring:message code="statistic.numberApplicationsTrip" />: <jstl:out value="${tripApplicationsPerTripStatistics[1]}" /> 
</p>
<p>
<spring:message code="statistic.max" /> <spring:message code="statistic.numberApplicationsTrip" />: <jstl:out value="${tripApplicationsPerTripStatistics[2]}" /> 
</p>
<p>
<spring:message code="statistic.std" /> <spring:message code="statistic.numberApplicationsTrip" />: <jstl:out value="${tripApplicationsPerTripStatistics[3]}" /> 
</p>

<p>
<spring:message code="statistic.avg" /> <spring:message code="statistic.numberTripsPerManager" />: <jstl:out value="${tripsPerManagerStatistics[0]}" />
</p>
<p> 
<spring:message code="statistic.min" /> <spring:message code="statistic.numberTripsPerManager" />: <jstl:out value="${tripsPerManagerStatistics[1]}" /> 
</p>
<p>
<spring:message code="statistic.max" /> <spring:message code="statistic.numberTripsPerManager" />: <jstl:out value="${tripsPerManagerStatistics[2]}" /> 
</p>
<p>
<spring:message code="statistic.std" /> <spring:message code="statistic.numberTripsPerManager" />: <jstl:out value="${tripsPerManagerStatistics[3]}" /> 
</p>

<p>
<spring:message code="statistic.avg" /> <spring:message code="statistic.tripPrice" />: <jstl:out value="${tripsPriceStatistics[0]}" />
</p>
<p> 
<spring:message code="statistic.min" /> <spring:message code="statistic.tripPrice" />: <jstl:out value="${tripsPriceStatistics[1]}" /> 
</p>
<p>
<spring:message code="statistic.max" /> <spring:message code="statistic.tripPrice" />: <jstl:out value="${tripsPriceStatistics[2]}" /> 
</p>
<p>
<spring:message code="statistic.std" /> <spring:message code="statistic.tripPrice" />: <jstl:out value="${tripsPriceStatistics[3]}" /> 
</p>

<p>
<spring:message code="statistic.avg" /> <spring:message code="statistic.numberTripsPerRanger" />: <jstl:out value="${tripsPerRangerStatistics[0]}" />
</p>
<p> 
<spring:message code="statistic.min" /> <spring:message code="statistic.numberTripsPerRanger" />: <jstl:out value="${tripsPerRangerStatistics[1]}" /> 
</p>
<p>
<spring:message code="statistic.max" /> <spring:message code="statistic.numberTripsPerRanger" />: <jstl:out value="${tripsPerRangerStatistics[2]}" /> 
</p>
<p>
<spring:message code="statistic.std" /> <spring:message code="statistic.numberTripsPerRanger" />: <jstl:out value="${tripsPerRangerStatistics[3]}" /> 
</p>

<p>
<spring:message code="ratioTA" /> <spring:message code="status.pending" />: <jstl:out value="${pendingRatio}" />
</p>
<p> 
<spring:message code="ratioTA" /> <spring:message code="status.due" />: <jstl:out value="${dueRatio}" /> 
</p>
<p>
<spring:message code="ratioTA" /> <spring:message code="status.accepted" />: <jstl:out value="${acceptedRatio}" /> 
</p>
<p>
<spring:message code="ratioTA" /> <spring:message code="status.cancelled" />: <jstl:out value="${cancelledRatio}" /> 
</p>

<p>
<spring:message code="ratioT" />: <jstl:out value="${cancelledVsOrganisedRatio}" /> 
</p>

<p>
<spring:message code="statistic.min" /> <spring:message code="statistic.numberNotesPerTrip" />: <jstl:out value="${notesPerTripStatistics[0]}" />
</p>
<p> 
<spring:message code="statistic.max" /> <spring:message code="statistic.numberNotesPerTrip" />: <jstl:out value="${notesPerTripStatistics[1]}" /> 
</p>
<p>
<spring:message code="statistic.avg" /> <spring:message code="statistic.numberNotesPerTrip" />: <jstl:out value="${notesPerTripStatistics[2]}" /> 
</p>
<p>
<spring:message code="statistic.std" /> <spring:message code="statistic.numberNotesPerTrip" />: <jstl:out value="${notesPerTripStatistics[3]}" /> 
</p>

<p>
<spring:message code="statistic.min" /> <spring:message code="statistic.numberAuditPerTrip" />: <jstl:out value="${auditsPerTripStatistics[0]}" />
</p>
<p> 
<spring:message code="statistic.max" /> <spring:message code="statistic.numberAuditPerTrip" />: <jstl:out value="${auditsPerTripStatistics[1]}" /> 
</p>
<p>
<spring:message code="statistic.avg" /> <spring:message code="statistic.numberAuditPerTrip" />: <jstl:out value="${auditsPerTripStatistics[2]}" /> 
</p>
<p>
<spring:message code="statistic.std" /> <spring:message code="statistic.numberAuditPerTrip" />: <jstl:out value="${auditsPerTripStatistics[3]}" /> 
</p>

<p>
<spring:message code="ratioTripsAudit" />: <jstl:out value="${tripsWithAuditRatio}" /> 
</p>

<p>
<spring:message code="ratioRangerCurriculum" />: <jstl:out value="${rangersCurriculumRatio}" /> 
</p>

<p>
<spring:message code="ratioRangerCurriculumEndorsed" />: <jstl:out value="${rangersERRatio}" /> 
</p>

<p>
<spring:message code="ratioSuspiciousManagers" />: <jstl:out value="${suspiciousManagersRatio}" /> 
</p>

<p>
<spring:message code="ratioSuspiciousRangers" />: <jstl:out value="${suspiciousRangersRatio}" /> 
</p>

<p>
<spring:message code="trips.moreTA" />:
</p>

<display:table name="tripsMoreApplications" id="trip" class="displaytag">
	<display:column titleKey="trip.ticker">
		<jstl:out value="${trip.ticker}"/>	
	</display:column>
		
	<display:column titleKey="trip.nTa" sortable="true">
		<jstl:out value="${fn:length(trip.tripApplications)}"/>	
	</display:column>
</display:table>

<p>
<spring:message code="legaltext.references" />:

<display:table name="legalTexts" id="legt" class="displaytag">
	<display:column titleKey="legaltext.title">
		<jstl:out value="${legt.title}"/>	
	</display:column>
		<display:column titleKey="legaltext.nref">
		<jstl:out value="${references[legt.id]}"/>	
	</display:column>
</display:table>

</p>