function generateBill()
{
	const items = JSON.parse(localStorage.getItem("billItems"));
	
	if(!items || items === 0)
		{
			alert("No items seletcted!");
			return;
		}
	
	fetch("http://localhost:8081/api/billing", {
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
	
	.catch(err => alert("Error generating bill:"+err));
}