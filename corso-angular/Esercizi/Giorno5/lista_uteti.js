let paginaCorrente = 1;

async function fetchSearch(paginaCorrente) {

  try {
    const response = await fetch(`https://reqres.in/api/users?page=${paginaCorrente}`);
    const data = await response.json();
    createCards(data.data);
    const buttonNotExsist = document.getElementsByClassName("pageButton").length === 0;

    if (buttonNotExsist) {
      createPageButton(data);
    }

  } catch (error) {
    console.error("Errore nel recupero utenti:", error);
  }


}

function createCards(user) {
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
    modifica.onclick = () => {

      // window.location.href = "modifica.html?id=" + idUtente;}
      sessionStorage.setItem("idUser", idUtente);
      window.location.href = "modifica.html";
    }

    card.appendChild(avatar);
    card.appendChild(nome);
    card.appendChild(cognome);
    card.appendChild(email);
    card.appendChild(modifica);

    cardContainer.appendChild(card);

  });


}

function createPageButton(data) {

  const mainUtenti = document.getElementsByTagName("main")
  const cardContainer = document.getElementById("card__container")
  //attributi che troverò in un json se il backend ha utilizato paginazione con Pageable
  const totalPages = data.total_pages;
  const currentPage = data.page;
  const totalElement = data.total;
  const elemetXPage = data.per_page;
  
  for (let i = 0; i < totalPages; i++) {
    const pageButton = document.createElement("button");
    pageButton.classList.add("pageButton");
    pageButton.id = "pageButton" + i + 1;
    pageButton.textContent = i + 1;

    pageButton.addEventListener("click", function () {
      cardContainer.innerHTML = "";
      fetchSearch(i + 1);
    });

    mainUtenti[0].appendChild(pageButton)

  }
}



fetchSearch();