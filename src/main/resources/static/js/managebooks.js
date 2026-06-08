
function showAddForm()
{
	document.getElementById("addForm").style.display = "block";
}

function hideAddForm()
{
	document.getElementById("addForm").style.display = "none";
}

function addBooks()
{
	const author = document.getElementById("author").value;
	const name = document.getElementById("name").value;
	const price = document.getElementById("price").value;
	const quantity = document.getElementById("quantity").value;
	
	if(!author || !name || !price || !quantity)
		{
			alert("Please fill all the fields");
			return;
		}
		
	const book ={
		
		author : author,
		name : name,
		price : price,
		quantity : quantity
	};
	
	fetch("http://localhost:8081/api/books",{
		
		method : "POST",
		headers : {
			"Content-Type: application/json"
		},
		body: JSON.stringify(book)
	})
	.then(res => {
		if(res.ok)
			{
				alert("Book added!")
				hideAddForm();
				loadBooks();
			}
			else{
				alert("Failed to add book");
			}
	});

}

function deleteBook(id)
{
	fetch(`http://localhost:8081/api/books${id}`, {
		
	method: "DELETE"
	})
	.then(res => {
		
		if(res.ok)
			{
				alert("Deleted!");
			}
		else
		{
			alert("Failed to delete");
		}
	});
}


function editBook(id, currentQuantity)
{
	const newQty = prompt("Enter new quantity", currentQuantity);
	
	if(newQty === null)
		{
			return;
		}
		
	fetch(`http://localhost:8081/api/books/${id}?quantity=${newQty}`, {
		
		method: "PUT"
	})
	.then(res => {
		
		if(res.ok)
			{
				alert("Updated!");
			}
			else
			{
				alert("Failed to update");
			}
	});
}


