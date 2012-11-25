	 $(document).ready(function() {
	 	$("input").change(function() {
	 		$.ajax({url:"/au/kk",success:function(result){
	 			alert(result);
				$("#nif").value(result);
			  }});	 	
		});
	 });
