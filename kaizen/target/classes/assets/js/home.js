
	$("#search").click(function() {
		$( "#businessTable" ).css('display', 'block');
		var name = $('#name').val();
		
		$.ajax({
			type : 'GET',
			url : '/kaizen/business',
			contentType : 'application/json',
			dataType : 'json',
			data:{"name":name},
			success : function(response) {
				 alert("Response: " + response);
				 //parent.document.getElementById("mustFrame").src="http://localhost:8080/business?name="+name;
				    //$("#mustFrame",parent.document).load("http://localhost:8080/home/"+username);
			
				//alert("you are logged in!");
				//location.href = "http://localhost:8080/home/"+username;
			},
			error:function(response)
			{
			}
		});
	});

