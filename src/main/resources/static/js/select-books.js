let selectedItems = [];

function loadBooks()
{
	fetch("http://localhost:8081/api/books")
	.then(res => res.json)
	.then(data => {
		const table = document.getElementById(bookTable);
		table.innerHTML = "";
		
		data.forEach(book =>
			{
				const row = document.createElement("tr");
				
				row.innerHTML = `
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.quantity}</td>
					<td><input type = "number" min = "1" max = "${book.quantity}" id = "qty_${book.id}"></td>
					<td><button onClick = "addBook(${book.id})">Add</button></td>
				`;
				
				table.appendChild(row);
			});
	});
}

function addBook(bookId)
{
	const qty = document.getElementById(`qty_${bookId}`).value;
	
	if(!qty || qty <= 0)
		{
			alert("Enter Quantity");
			return;
		}
		
		
		selectedItems.push({
			bookId : bookId,
			quantity : parseInt(qty)
		});
		
		alert("Book added");
		localStorage.setItem("billItems", JSON.stringify(selectedItems));
}