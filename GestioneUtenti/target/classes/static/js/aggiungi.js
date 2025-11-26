const aggiungi = document.getElementById("aggiungi")

aggiungi.addEventListener("click",add)

async function add(){
	const nome = document.getElementById("nome").value
	const cognome = document.getElementById("cognome").value
	const codice = document.getElementById("cf").value
	const data = document.getElementById("data").value
	if(nome==="" || cognome==="" || codice==="" || data===""){
		alert("Tutti i campi devono essere compilati")
	}
	else{
	
	const bodyJson = {
		nome: document.getElementById("nome").value,
		cognome : document.getElementById("cognome").value,
		codiceFiscale : document.getElementById("cf").value,
		dataDiNascita : document.getElementById("data").value
	}
	try{
		const response =await fetch(`http://localhost:8080/aggiungi`,{
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
			alert("Utente aggiunto con successo")
			location.href = "http://localhost:8080/gestione"
		}catch(err){
			console.log(err)
			alert("Impossibile aggiungere l'utente")
		}
	
	}
}