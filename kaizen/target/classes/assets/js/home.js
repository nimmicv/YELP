
<<<<<<< HEAD
	$("#find").click(function() {
		alert("hi inside find");
=======
	$("#search").click(function() {
		var name = $('#name').val();
		
		$.ajax({
			type : 'GET',
			url : '/kaizen?name=name',
			contentType : 'application/json',
			dataType : 'json',
			data:{"name":name},
			success : function(response) {
				 alert("Response: " + response);
				 parent.document.getElementById("mustFrame").src="http://localhost:8080/business/"+name;
				    //$("#mustFrame",parent.document).load("http://localhost:8080/home/"+username);
			
				//alert("you are logged in!");
				//location.href = "http://localhost:8080/home/"+username;
			},
			error:function(response)
			{
			}
		});
>>>>>>> FETCH_HEAD
	});

