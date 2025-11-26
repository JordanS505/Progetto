const data = document.getElementById("data")
const nome = document.getElementById("nome")
const cf = document.getElementById("cf")
const cognome = document.getElementById("cognome")
const add= document.getElementById("add")

add.addEventListener("click",()=>{
	location.href=`http://localhost:8080/nuovo`
})

nome.addEventListener("input",controllo)
cf.addEventListener("input",controllo)
cognome.addEventListener("input",controllo)

function controllo(){
	if(nome.value!="" && cf.value!="" && cognome.value!=""){
		data.removeAttribute("disabled")
	}
	else{
		if(!data.hasAttribute("disabled")){
			data.setAttribute("disabled","disabled")
		}
	}
}

function aggiorna(event){
	const id = event.target.value;
	location.href=`http://localhost:8080/modifica/${id}`
}

async function cercaUtenti() {

    const bodyJson = {
        nome: document.getElementById("nome").value,
        cognome: document.getElementById("cognome").value,
        codiceFiscale: document.getElementById("cf").value,
        dataDiNascita: document.getElementById("data").value
    };

    try {
        const response = await fetch("http://localhost:8080/cerca", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(bodyJson)
        });

        if (!response.ok) {
            throw new Error("Errore nella richiesta");
        }

        const utenti = await response.json();
        generaTabella(utenti);

    } catch (err) {
        console.error(err);
        alert("Impossibile recuperare gli utenti");
    }
}

function generaTabella(utenti) {
    const div = document.getElementById("risultati");

    if (!utenti || utenti.length === 0) {
        div.innerHTML = "<p>Nessun utente trovato.</p>";
        return;
    }

    let html = `
        <table border="1" cellpadding="8" cellspacing="0" style="width:100%; border-collapse: collapse;">
            <thead>
                <tr style="background-color:#4A90E2; color:white;">
                    <th>Nome</th>
                    <th>Cognome</th>
                    <th>Codice Fiscale</th>
                    <th>Data di nascita</th>
					<th>Azioni</th>
                </tr>
            </thead>
            <tbody>
    `;

    utenti.forEach(u => {
        html += `
            <tr>
                <td>${u.nome}</td>
                <td>${u.cognome}</td>
                <td>${u.codiceFiscale}</td>
                <td>${u.dataDiNascita}</td>
				<td><button class="rimuovi" value=${u.id}><i class="fa-solid fa-trash"></i></button>
				<button class="update" value=${u.id}><i class="fa-solid fa-pen"></i></button></td>
            </tr>
        `;
    });

    html += `
            </tbody>
        </table>
    `;

    div.innerHTML = html;
	document.querySelectorAll(".update").forEach(btn=>{
		btn.addEventListener("click",aggiorna)
	})
	
	
	document.querySelectorAll(".rimuovi").forEach(btn => {
	        btn.addEventListener("click", rimuoviUtente);
	    });
}

	function rimuoviUtente(event) {
	    const id = event.target.value;

	    fetch(`http://localhost:8080/rimuovi/${id}`, {
	        method: "POST", 
	    })
	    .then(response => {
	        if (!response.ok) {
	            throw new Error("Errore durante la rimozione");
	        }
	        return response.text();
	    })
	    .then(msg => {
	        alert("Utente rimosso");
	        event.target.closest("tr").remove();
	    })
	    .catch(err => {
	        console.error(err);
	        alert("Errore");
	    });
	}
