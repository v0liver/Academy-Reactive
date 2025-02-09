// const urlParams = new URLSearchParams(window.location.search);
// const idUtente = urlParams.get('id');
const idUtente = sessionStorage.getItem("idUser");
const nameUser = document.getElementById("last_name");
const surnameUser = document.getElementById("last_name");
const email = document.getElementById("email");

async function  InviaModifica(event){
    const form = document.getElementById('form_modifica');
    if (!form.checkValidity()) {
        return; 
    }
    event.preventDefault();
    const userNuovo = {
        first_name: nameUser.value,
        last_name : surnameUser.value,
        email: email.value,
      };

      const response = await fetch(`https://reqres.in/api/users/${idUtente}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userNuovo),
      });

      if(response.ok){
        const data = await response.json();
        alert("Utente Modificato" + JSON.stringify(data,null,2))
      }
}