async function fetchSearch() {

        try {
          const response = await fetch(`https://reqres.in/api/users?page=2`);
          const data = await response.json();
          createCards(data.data);
        } catch (error) {
          console.error("Errore nel recupero utenti:", error);
        }
      
   
}

function createCards(user){
    const cardContainer = document.getElementById("card__container");

   user.forEach(element => {
        const idUtente = element.id;

        const card = document.createElement("div");
        card.classList.add("card");

        const avatar = document.createElement("img");
        avatar.src = element.avatar;

        const nome = document.createElement("h3");
        nome.textContent = element.first_name;
        
        const cognome = document.createElement("h3");
        cognome.textContent = element.last_name;

        const email = document.createElement("h3");
        email.textContent = element.email;
        const modifica = document.createElement("button");
        modifica.id = "button-modifica";
        modifica.textContent = "Modifica";
        modifica.onclick = ()=>{ 
          
          // window.location.href = "modifica.html?id=" + idUtente;}
          sessionStorage.setItem("idUser", idUtente);
          window.location.href = "modifica.html";}

        card.appendChild(avatar);
        card.appendChild(nome);
        card.appendChild(cognome);
        card.appendChild(email);
        card.appendChild(modifica);

        cardContainer.appendChild(card);
     
   });


}

fetchSearch();