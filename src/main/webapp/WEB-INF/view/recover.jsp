<%@include file="header.jsp"%>



<div class="fillform">
	<h1>Password Recovery</h1>
	<a class="homeadmin" href="/">Home</a>
</div>

<DIV>
	<form method="post" >
		<table class="table">
		
					<c:if test="${email_id==null}">
		
			<tr>
				<td>Enter your registered Email-id:</td>
			</tr>
			<tr>
							<td><input type="email" name="email_id" /></td>
			
			</tr>
			<tr>
				<td><button type="submit" id="submit">Get OTP</button></td>
			</tr>
			</c:if>
			<c:if test="${email_id!=null}">

				
					<tr>
						<td>Enter the OTP</td>
						<tr>
								<td><input type="text" name="OTP"/></td>
								</tr>
								<tr>
										<td><a
											href="validateOTP?&userOTP=${OTP}&email=${email_id}">VerifyOTP</a></td>
						<tr>
			</c:if>
			
		</table>
	</form>
	</DIV>



<%@include file="footer.jsp"%>
