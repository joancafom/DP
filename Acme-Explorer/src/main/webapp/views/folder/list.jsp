<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<a href="message/create.do">Send a new message</a><br>
<br>
<display:table name="folders" id="folder" requestURI="folder/list.do" class="displaytag">
	<display:column title="Name">
		<a href="folder/list.do?folderId=${folder.id}"><jstl:out value="${folder.name}"/></a>
	</display:column>
	<display:column>
		<jstl:if test="${folder.isSystem == false}">
			<a href="folder/edit.do?folderId=${folder.id}">Edit</a>
		</jstl:if>
	</display:column>
</display:table>

<a href="folder/create.do?folderId=${folderId}">Create a new folder</a>

<jstl:if test="${folderId!=null}">
	<p>Messages:</p>
	<display:table name="messages" id="message" requestURI="message/list.do?folderId=${folderId}" class="displaytag">
	<display:column title="Date">
		<jstl:out value="${message.sentMoment}"/>
	</display:column>
	<display:column title="Sender">
		<jstl:out value="${message.sender.name}"/>
	</display:column>
	<display:column title="Subject">
		<jstl:out value="${ message.subject}"/>
	</display:column>
	<display:column title="Priority">
		<jstl:out value="${message.priority}"/>
	</display:column>
	<display:column >
		<a href="message/display.do?messageId=${message.id}">Details</a>
		<a href="message/edit.do?messageId=${message.id}">Edit</a>
	</display:column>
</display:table>
</jstl:if>


