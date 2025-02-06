let array = [1, 2, 2, 10, 20, 15, 20, 75];

let json=[
    {
      "nome": "Mario",
      "cognome": "Rossi",
      "eta": 30,
      "data_di_nascita": "1994-03-15"
    },
    {
      "nome": "Anna",
      "cognome": "Bianchi",
      "eta": 25,
      "data_di_nascita": "1999-07-22"
    },
    {
      "nome": "Luca",
      "cognome": "Verdi",
      "eta": 35,
      "data_di_nascita": "1989-11-05"
    },
    {
      "nome": "Giulia",
      "cognome": "Neri",
      "eta": 28,
      "data_di_nascita": "1996-01-10"
    },
    {
      "nome": "Marco",
      "cognome": "Gialli",
      "eta": 40,
      "data_di_nascita": "1984-08-17"
    }
  ]
;

let stringJson=`[
  {
    "nome": "Mario",
    "cognome": "Rossi",
    "eta": 30,
    "data_di_nascita": "1994-03-15"
  },
  {
    "nome": "Anna",
    "cognome": "Bianchi",
    "eta": 25,
    "data_di_nascita": "1999-07-22"
  },
  {
    "nome": "Luca",
    "cognome": "Verdi",
    "eta": 35,
    "data_di_nascita": "1989-11-05"
  },
  {
    "nome": "Giulia",
    "cognome": "Neri",
    "eta": 28,
    "data_di_nascita": "1996-01-10"
  },
  {
    "nome": "Marco",
    "cognome": "Gialli",
    "eta": 40,
    "data_di_nascita": "1984-08-17"
  }
]
`;

function sum() {
    console.log(array);
    let sum = 0;
    for (let i = 0; i < array.length; i++) {
        const element = array[i];
        sum += element;
    }

    console.log("Somma: " + sum);

}

function maxValore() {
    console.log(array);
    let max = 0;
    for (const element of array) {
        if (element > max) {
            max = element;
        }
    }
    console.log("Il valore massimo è: " + max);

}

function removeDuplicates() {
    console.log(array);
    array.sort((a, b) => a - b);
    for (let i = 0; i < array.length; i++) {
        const element = array[i];
        if (element == array[i + 1]) {
            // delete array[i];
            array.splice(i, 1);//i indica la posizione dove inizia la modifica, 1 indica quanti elementi eliminare a partire da quella posizione inclusa
        }


    }
    console.log(array);
    array = [1, 2, 2, 10, 20, 15, 20, 75];
}

function filterByCriteria() {
    const result = array.filter(element=>element>10);
    console.log(result);
}


function jsonStrToObj(){
    const jsonFromString = JSON.parse(stringJson);
    console.log(jsonFromString);
    
}

function jsonObjToStr(){
    const stringFromJson= JSON.stringify(json);
    console.log(stringFromJson);
}

function manipulate(obj, property, value) {
    obj[property] = value;
    console.log(obj); 
  }

  
 
  