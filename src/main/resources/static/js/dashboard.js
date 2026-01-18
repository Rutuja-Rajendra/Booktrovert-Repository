const BASE_URL = "http://localhost:8081/api/reports";

fetch(BASE_URL + "/revenue")
	.then(res => res.json())
	.then(data => document.getElementById("revenue").innerText = data);
	
fetch(BASE_URL + "/total-bills")
	.then(res => res.json())
	.then(data => document.getElementById("bills").innerText = data);
	
fetch(BASE_URL + "/total-sales")
	.then(res => res.json())
	.then(data => document.getElementById("totalsales").innerText = data);
	
fetch(BASE_URL + "/top-books")
	.then(res => res.json())
	.then(data => {
		const ul = document.getElementById("topbooks");
		data.forEach(book => {
			const li = document.createElement("li");
			li.innerText = book;
			ul.appendChild(li);
		});
	});