
	$("#searchB").click(function() {
		$( "#businessTable" ).css('display', 'block');
		var name = $('#name').val();
		$.ajax({
			
			type : 'POST',
			url : '/kaizen/business',
			contentType : 'application/json',
			dataType : 'json',
			data:{"name":name},
			success : function(data,jqXHR,status) {
				 alert("Response: " + jqXHR+ " "+status+" "+data);
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

