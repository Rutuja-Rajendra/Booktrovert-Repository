let selectedItems = JSON.parse(localStorage.getItem("billItems")) || [];

function loadBooks()
{
	const query = document.getElementById("search").value.toLowerCase();
	
	fetch("http://localhost:8081/api/books")
	.then(res => res.json())
	.then(data => {
		const table = document.getElementById("bookTable");
		
		if(!table)
			{
				console.error("bookTable not found");
				return;
			}
			
			table.innerHTML = "";
			
		//filter by search quesry if one exists
		const filtered = query
		? data.filter(book => book.name.toLowerCase(). includes(query))
		: data;
		
		if(filtered.length === 0)
			{
				table.innerHTML = `<tr><td colspan="5">No books found</td></tr>`;
				return;
			}
			
		
		filtered.forEach(book =>
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
	})
	.catch(err => console.error("Failed to load books:"+err));
}

function addBook(bookId)
{
	const qty = document.getElementById(`qty_${bookId}`).value;
	
	if(!qty || qty <= 0)
		{
			alert("Enter Quantity");
			return;
		}
		
		const existing = selectedItems.find(item => parseInt(item.bookId) === parseInt(bookId));
		
		if(existing)
			{
				existing.quantity += parseInt(qty);
				alert("Book quantity updates in cart.");
			}
			else
			{
				selectedItems.push({
					bookId: bookId,
					quantity: parseInt(qty)
				});
				alert("Book added to cart.");
			}
			
			localStorage.setItem("billItems", JSON.stringify(selectedItems));
		
		
		
		alert("Book added");
		localStorage.setItem("billItems", JSON.stringify(selectedItems));
}