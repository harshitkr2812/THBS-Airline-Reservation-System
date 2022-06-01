<%@include file="header.jsp"%>

<div class="fillform">
	<h1>Fill the details for registration</h1>
	<a class="homeadmin" href="/">Home</a>
</div>

<div class="message">${message}</div>

<div class="message">${Update_success}</div>


<div class="reg">
	<form:form modelAttribute="user" method="post">
		<table class="table">
			<tr>
				<td>Name:</td>
				<td><form:input path="u_name" /></td>
			</tr>
			<tr>
				<td>Gender:</td>
				<td><form:select path="u_gender">
						<form:option value="male" />
						<form:option value="female" />
						<form:option value="other" />
					</form:select></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><form:textarea path="u_address" rows="3" cols="20" /></td>
			</tr>
			<tr>
				<td>Email-Id:</td>
				<td><form:input type="email" path="u_email_id" /></td>
			</tr>
			<tr>
				<td>Contact:</td>
				<td><form:input path="u_contact" pattern="[7-9]{1}[0-9]{9}" /></td>
			</tr>
			<tr>
				<td>Username:</td>
				<td><form:input path="u_username" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="u_password" /></td>
			</tr>
			<tr>
				<td></td>
				<td><button type="submit" id="submit">Register</button></td>
			</tr>
		</table>
	</form:form>
</div>
<%@include file="footer.jsp"%>
