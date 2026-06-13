let selectedItems = JSON.parse(localStorage.getItem("billItems")) || [];

function renderCart() {
    const cartDiv = document.getElementById("cartItems");

    if (!selectedItems || selectedItems.length === 0) {
        cartDiv.innerHTML = `No items in cart. <a href="select-books.html">Go select books first.</a>`;
        document.getElementById("billBtn").disabled = true;
        return;
    }

    document.getElementById("billBtn").disabled = false;

    let html = `<table border="1" cellpadding="8">
        <thead>
            <tr>
                <th>Book ID</th>
                <th>Quantity</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>`;

    selectedItems.forEach((item, index) => {
        html += `
            <tr>
                <td>${item.bookId}</td>
                <td>${item.quantity}</td>
                <td>
                    <button onclick="increaseQty(${index})">+</button>
                    <button onclick="decreaseQty(${index})">-</button>
                    <button onclick="removeItem(${index})" style="color:red;">Remove</button>
                </td>
            </tr>
        `;
    });

    html += `</tbody></table>`;
    cartDiv.innerHTML = html;
}

window.onload = function() {
    renderCart();
}

function increaseQty(index) {
    selectedItems[index].quantity += 1;
    localStorage.setItem("billItems", JSON.stringify(selectedItems));
    renderCart();
}

function decreaseQty(index) {
    if (selectedItems[index].quantity <= 1) {
        if (confirm("Remove this book from cart?")) {
            selectedItems.splice(index, 1);
        }
    } else {
        selectedItems[index].quantity -= 1;
    }
    localStorage.setItem("billItems", JSON.stringify(selectedItems));
    renderCart();
}

function removeItem(index) {
    if (confirm("Remove this book from cart?")) {
        selectedItems.splice(index, 1);
        localStorage.setItem("billItems", JSON.stringify(selectedItems));
        renderCart();
    }
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


function clearCart()
{
	if(confirm("Are you sure you want to clear the cart?"))
		{
			localStorage.removeItem("billItems");
			selectedItems = [];
			document.getElementById("cartItems").innerHTML = `No items in cart. <a href="select-books.html"> Go select books first </a>`;
			document.getElementById("billBtn").disabled = true;
			document.getElementById("clearBtn").disabled = true;
			document.getElementById("errorMessage").style.display = "none";
		}
}