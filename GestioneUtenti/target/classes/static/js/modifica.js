const modifica = document.getElementById("modifica")

modifica.addEventListener("click",aggiorna)

async function aggiorna(){
	const nome = document.getElementById("nome").value
	const cognome = document.getElementById("cognome").value
	const codice = document.getElementById("codice").value
	const data = document.getElementById("data").value
	if(nome==="" || cognome==="" || codice==="" || data===""){
		alert("Tutti i campi devono essere compilati")
	}
	else{
	const id = window.location.pathname.split("/").pop();
	
	const bodyJson = {
		nome: document.getElementById("nome").value,
		cognome : document.getElementById("cognome").value,
		codiceFiscale : document.getElementById("codice").value,
		dataDiNascita : document.getElementById("data").value
	}
	try{
		const response =await fetch(`http://localhost:8080/update/${id}`,{
			method: "POST",
			           headers: {
			               "Content-Type": "application/json",
			               "Accept": "application/json"
			           },
			           body: JSON.stringify(bodyJson)
			       });
			if(!response.ok){
				throw new Error("Errore nella richiesta")
			}
			alert("modifica riuscita")
			location.href = "http://localhost:8080/gestione"
		}catch(err){
			console.log(err)
			alert("Impossibile modificare l'utente")
		}
	
	}
}