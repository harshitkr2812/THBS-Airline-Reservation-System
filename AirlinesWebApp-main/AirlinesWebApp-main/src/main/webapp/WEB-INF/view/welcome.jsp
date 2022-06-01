<%@include file="header.jsp"%>
<c:if test="${message!=null}">
<div class="message">${message}</div>
</c:if>
<div class="bar"></div>

<div class="description">
	<h1>
		AIRLINES<br>
	</h1>
	Now Flying is Just Few Taps Away!<br>

	<p>
		Airlines allows you to book airticket from anywhere and anytime.<br>
		If you are a new user first create your account by registering
		yourself.<br>Once registered, you can simply login and book an
		airticket.
	</p>
	<br> <br> <br>
	<h2>Happy Flying!</h2>
</div>

<div class="anchors">
	<div id=a1>
		<a href="user_register">New to Airlines? Click Here to register.</a>
	</div>
	<br>
	<div id=a2>
		<a href="user_login">User Login.</a>
	</div>
	<div id="a3">
		<a href="admin_login">Admin Login</a>
	</div>
</div>

<%@include file="footer.jsp"%>

