// show cart items on page load
window.onload = function() {
	const items = JSON.parse(localStorage.getItem("billItems"));
	const cartDiv = document.getElementById("cartItems");

	if (!items || items.length === 0) {
		cartDiv.innerHTML = `No items in cart. <a href="select-books.html">Go select books first.</a>`;
		document.getElementById("billBtn").disabled = true;
		return;
	}

	let html = `<table border="1" cellpadding="8">
		<thead>
			<tr><th>Book ID</th><th>Quantity</th></tr>
		</thead>
		<tbody>`;

	items.forEach(item => {
		html += `<tr><td>${item.bookId}</td><td>${item.quantity}</td></tr>`;
	});

	html += `</tbody></table>`;
	cartDiv.innerHTML = html;
}

function generateBill() {
	const items = JSON.parse(localStorage.getItem("billItems"));

	if (!items || items.length === 0) {
		alert("No items in cart! Go select books first.");
		return;
	}

	fetch("http://localhost:8081/api/billing", {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({ items: items })
	})
	.then(res => 
		{
			if(res.status === 400)
				{
					return res.text().then(msg => 
						{
							throw new Error(msg);
						});
				}
				if(!res.ok)
					{
						throw new Error("Failed to generate bill");
					}
					return res.json();
		})
	.then(bill => {
		// fetch full bill details with items
		return fetch(`http://localhost:8081/api/billing/${bill.id}`)
		.then(res => res.json());
	})
	.then(data => {
		// show receipt
		document.getElementById("receiptSection").style.display = "block";
		document.getElementById("billId").innerText = data.billID;
		document.getElementById("billDate").innerText = new Date(data.billDate).toLocaleString();
		document.getElementById("totalAmount").innerText = data.totalPrice;

		const tbody = document.getElementById("receiptItems");
		tbody.innerHTML = "";

		data.items.forEach(item => {
			tbody.innerHTML += `
				<tr>
					<td>${item.bookName}</td>
					<td>${item.quantity}</td>
					<td>Rs. ${item.price}</td>
					<td>Rs. ${item.price * item.quantity}</td>
				</tr>
			`;
		});

		// clear cart
		localStorage.removeItem("billItems");
		document.getElementById("cartSection").style.display = "none";
		document.getElementById("billBtn").style.display = "none";
	})
	.catch(err => 
		{
			const errorDiv = document.getElementById("erroMessage");
			errorDiv.style.display = "block";
			errorDiv.innerText = err.message;
		}
	)
}