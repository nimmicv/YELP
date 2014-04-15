	$("#login").click(function() {
		var username = $('#username').val();
		var password = $('#password').val();
		alert(username);
		var strData={"username":username,"password":password};
		
		$.ajax({
			type : 'POST',
			url : '/kaizen/validate',
			contentType : 'application/json',
			dataType : 'json',
			data:JSON.stringify(strData),
			success : function(response) {
				alert("you are logged in!");
				location.href = "http://localhost:8080/home/"+username;
			},
			error:function(response)
			{
				alert("error");
				location.href = "http://localhost:8080/home/"+username;
			}
		});
		
	});
	