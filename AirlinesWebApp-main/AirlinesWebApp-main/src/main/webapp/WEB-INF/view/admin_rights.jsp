<%@include file="header.jsp" %>

<a class="homerights" href="/logout">LogOut</a></div>

<% String message = request.getParameter("message");%>
<c:if test="${message!=null}">
<div class="message"><%=message%></div>
</c:if>

	<div class="bar"></div>
	<div class="description">
		<h1>
			AIRLINES<br></h1>Now Flying is Just Few Taps Away!<br>
		<h2>AIRLINES ADMINISTRATOR</h2>
			<ul>
					<li>As an admin you can Add flight details, Edit flight details and Delete flight details</li>
					<li>You can select the status option as active or inactive for the flights that are currently available or not</li>
					<li>Only active flights will be shown to the user</li>
				</ul><br><br>
				<h2>Happy Flying!</h2>
	</div>

<div class="anchors">
<div id="a1">
<a href="admin_edit_flight_details">Edit Flight Details</a>
</div>
</div>
<%@include file="footer.jsp" %>
