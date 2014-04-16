	$("#login").click(function() {
		
		var username = $('#username').val();
		$("#mustFrame").load("http://localhost:8080/home/"+username);
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
				 //alert("Response: " + response);
				 parent.document.getElementById("mustFrame").src="http://localhost:8080/home/"+username;
				    //$("#mustFrame",parent.document).load("http://localhost:8080/home/"+username);
			
				//alert("you are logged in!");
				//location.href = "http://localhost:8080/home/"+username;
			},
			error:function(response)
			{
				$.get("http://localhost:8080/home/"+username,function(data,status){
				    alert("Data: " + data + "\nStatus: " + status);
				    $(body).html(response);
				  });
				
				}
		});
		
	});
	