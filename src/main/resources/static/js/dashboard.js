const BASE_URL = "http://localhost:8081/api/reports";

const revenueEl = document.getElementById("revenue");
const billsE2 = document.getElementById("bills");
const salesE3 = document.getElementById("totalsales");
const topbooksE4 = document.getElementById("totalsales");

if(revenueEl){
	fetch(BASE_URL + "/revenue")
	.then(res => res.json())
	.then(data => document.getElementById("revenue").innerText = data);
}

if(billsE2){
fetch(BASE_URL + "/total-bills")
	.then(res => res.json())
	.then(data => document.getElementById("bills").innerText = data);
}	

if(salesE3){
fetch(BASE_URL + "/total-sales")
	.then(res => res.json())
	.then(data => document.getElementById("totalsales").innerText = data);
}

if(topbooksE4){
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
}
	
function loadPage(page)
{
	const content = document.getElementById("content");
	
	if(page === "books")
		{
			fetch("/pages/getallbooks.html")
			.then(res => res.text())
			.then(html => {
				content.innerHTML = html;
				 if (typeof loadBooks === "function") loadBooks();
			});
		}
		
		else if(page === "billing")
			{
				fetch("/pages/billing.html")
				.then(res => res.text())
				.then(html => {content.innerHTML = html;
					if (typeof loadBilling === "function") loadBilling();

			});
			}
			
		else if(page === "inventory")
			{
				fetch("/pages/inventory.html")
				.then(res => res.text())
				.then(html => {content.innerHTML = html;
				if (typeof loadInventory === "function") loadInventory();
				});

			}
			
		else
		{
			content.innerHTML = `<h2>${page} Page coming soon </h2>`;
		}
}

