const form = document.getElementById("form__creaUtente");


form.addEventListener("submit",
    async (event) => {
        event.preventDefault();

        
        const nome = form.elements[0].value;
        const cognome = form.elements[1].value;
        const email = form.elements[2].value;

        const nuovoUtente = {
            first_name: nome,
            last_name: cognome,
            email: email,
        };

        const response = await fetch("https://reqres.in/api/users", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(nuovoUtente),
        });

        if (response.ok) {
            const data = await response.json();
            console.log("Utente creato:", data);
            alert("Utente creato: " + JSON.stringify(data,null,2))//il 2 serve per l indentazione
            window.location.href = "home.html";
        }
    }

);