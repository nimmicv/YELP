$(document).ready(function () {
    $(document).on('click', ".table .btn", function (e) {
        e.preventDefault();
        id = $(this).attr('id')
        alert(id);
    });
});
$("#searchB").click(function() {
		$( "#businessTable" ).css('display', 'block');
		var name = $('#name').val();
		$.ajax({
			
			type : 'GET',
			url : '/kaizen/business',
			contentType : 'application/json',
			dataType : 'json',
			data:{"name":name},
<<<<<<< HEAD
			success : function(response) {
				 //alert("Response: " + response);

				 var arr = new Array(), j = -1;
				 arr[++j] = '<tr><th>Business ID</th><th>Business Name</th><th>Address</th><th>View</th></tr>';
				 for(i=0;i<response.businesses.length;i++)
					 {
					// alert(response.businesses[i].name);
					 ++j;
					 arr[j] ='<tr><td>'+response.businesses[i].businessId+'</td><td>'+response.businesses[i].name+'</td><td>'+response.businesses[i].fullAddress+'</td>';
					 arr[j] = arr[j] + '<td><button id="tableBut" type="button" class="btn btn-primary" onclick="redirect()">Go</button></td></tr>';
					 }
				 $('#businessTableData').html(arr.join('')); 
=======
			success : function(data,jqXHR,status) {
				 alert("Response: " + jqXHR+ " "+status+" "+data);
>>>>>>> FETCH_HEAD
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

function redirect()
{
	alert("Inside");
}
