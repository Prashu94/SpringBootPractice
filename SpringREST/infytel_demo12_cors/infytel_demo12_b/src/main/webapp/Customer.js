/**
 * 
 */
  
$(document).ready(function() {
	
	function tableCreate(el, data)
	{
	    var tbl  = document.createElement("table");
	    tbl.border =1;
	    tbl.style.width  = "70%";
	    var tr1 = tbl.insertRow();
	    tr1.bgColor="blue";
	   tr1.insertCell().appendChild(document.createTextNode("phoneNo"));
	    tr1.insertCell().appendChild(document.createTextNode("Name"));
	   tr1.insertCell().appendChild(document.createTextNode("Email"));
	    for (var i = 0; i < data.length; ++i)
	    {
	        var tr = tbl.insertRow();
	        for(var j = 0; j < data[i].length; ++j)
	        {
	            var td = tr.insertCell();
	            td.appendChild(document.createTextNode(data[i][j].toString()));
	        }
	    }
	    $('table').attr('border', '0');
	   $('.customer-id').append(tbl);
	  
	}
	
    $.ajax({
        url: "http://localhost:8090/infytel-12a/customers",
        headers: {
            "Authorization": "Basic " + btoa("admin"+ ":" + "infytel")
          }
    }).then(function(data, status, jqxhr) {
     
       let rows = [];
        
       for (var i = 0; i < data.length; ++i)
       {
           let cells = [];
           cells.push(data[i].phoneNo);
           cells.push(data[i].name);
           cells.push(data[i].email);
           rows.push(cells);
       }
      tableCreate($("#customer-id"), rows);
       console.log(jqxhr);
    });    
});
