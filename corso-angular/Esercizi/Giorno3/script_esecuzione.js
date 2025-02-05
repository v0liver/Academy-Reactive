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
    const ore = time.getHours();
    const minuti = time.getMinutes();
    const secondi = time.getSeconds();

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
        Email: ${email}`);
    
  }

  document.getElementById('formMyhero').addEventListener("submit", validazioneDati);



