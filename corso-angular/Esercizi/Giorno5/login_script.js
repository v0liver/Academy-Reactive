async function fetchLogin(event) {
   
    
    
    const form = document.getElementById('form-login');
    
    // Se il form non è valido, fermati qui
    if (!form.checkValidity()) {
        
        return; 
    }

    event.preventDefault();
    const email = document.getElementById('email-login').value;
    const password = document.getElementById('password-login').value;
    const json = {
        email,
        password
    }
    
    try {
        if(!!email && !!password  && email.includes("@")){
            const json2 = JSON.stringify(json)
            const response = await fetch('https://reqres.in/api/login',{
                method: 'POST',
                headers:{'Content-Type':'application/json'},
                body: json2
            })
            
        
        
        if(response){
            const data = await response.json();
            console.log(data.token);
            window.location.href="home.html"
        }
       
        
        return data;

        }else{
            alert("Password o Email non correti")
            throw error;
        } 
        
        
    } catch (error) {
        console.error('error',error);
        throw error;
        
        
    }
   
}



// fetchLogin()
// .then(data=>{
//     console.log('success: ',data)
// })
// .catch(error=>{
//     console.log('error:',error);
    
// })