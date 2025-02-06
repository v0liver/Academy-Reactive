function getParagrafs() {
    let cont = 0;
    let listaParagrafi = document.getElementsByTagName("p");

    // listaParagrafi.forEach(element => {
    //     element.innerHTML="ciao";
    // });

    for (let i = 0; i < listaParagrafi.length; i++) {
        listaParagrafi[i].innerHTML = "blocco " + (++cont);
    }
}

function greetEveryone() {
    alert("Ciao a Tutti");
}

function showTime(id) {

    const time = new Date();
    const ore = time.getHours().toString().padStart(2,0);//padStart(2, '0'): Assicura che la stringa abbia una lunghezza di almeno 2 caratteri, aggiungendo 0 all'inizio se necessario.
    const minuti = time.getMinutes().toString().padStart(2,0);
    const secondi = time.getSeconds().toString().padStart(2,0);

    document.getElementById(id).textContent = `${ore}:${minuti}:${secondi}`;

}

function setIntervalloClock(id, millisecondi) {
    setInterval(() => showTime(id, millisecondi));
}


function changeBackground(className) {
    document.getElementsByClassName(className)[0].style.backgroundColor = 'black';
}

function validazioneDati(event) {
    event.preventDefault(); // Evita l'invio del form

    const form= document.forms['formMyhero'];
    const nome1= form.nome.value;
    
    const nome = document.getElementById('nome').value;
    const cognome = document.getElementById('cognome').value;
    const data_nascita = document.getElementById('data').value;
    const genere = document.getElementById('genere').value;
    const email = document.getElementById('email').value;

    alert(`
        Nome: ${nome} 
        Cognome: ${cognome}
        Data di Nascita: ${data_nascita}
        Genere: ${genere}
        Email: ${email}
        Nome1: ${nome1}
        `);
        
    
  }

  document.getElementById('formMyhero').addEventListener("submit", validazioneDati)
  



