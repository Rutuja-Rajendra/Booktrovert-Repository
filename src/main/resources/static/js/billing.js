generateBill()
{
	const items = JSON.parse(localStorage.getItem("billItems"));
	
	fetch("http://localhost:8081/api/billing/generate", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body:JSON.stringify({items: items})
	})
	.then(res => res.json())
	.then(data => {
		document.getElementById("result").innerText=
		JSON.stringify(data,null,2);
		localStorage.removeItem("billItems");
		
	})
	
	.catch(err => alert("Error generating bill"));
}