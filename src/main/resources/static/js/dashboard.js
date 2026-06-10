const base_url = "http://localhost:8081/api/reports";

fetch(base_url+ "/revenue")
	.then(res => res.json())
	.then(data => document.getElementById("revenue").innerText = data)
	.catch(() => document.getElementById("revenue").innerText = "Error");
	
fetch(base_url + "/total-bills")
	.then(res => res.json())
	.then(data => document.getElementById("totalbills").innerText = data)
	.catch(() => document.getElementById("totalbills").innerText = "Error");
	
fetch(base_url + "/total-sales")
	.then(res => res.json())
	.then(data => document.getElementById("totalsales").innerText = data)
	.catch(() => document.getElementById("totalsales").innerText = "Error");
	
fetch(base_url + "/top-books")
	.then(res => res.json())
	.then(data => 
		{
			const ul = document.getElementById("topbooks");
			
			if(data.length === 0)
			{
				ul.innerHTML = "<li>No sales data yet</li>";
				return;
			}
			
			data.forEach(book => 
			{
				const li = document.createElement("li");
				li.innerText = book;
				ul.appendChild(li);		
			});
		})
		.catch(() => document.getElementById("topbooks").innerHTML = "<li>Error loading data</li>");
