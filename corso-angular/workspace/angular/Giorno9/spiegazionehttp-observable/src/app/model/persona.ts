
export class Persona {
    private _id: number = 0;
    nome: string = '';
    cognome?: string;
  
    constructor(id: number, nome: string, cognome?: string) {
      this._id = id;
      this.nome = nome;
      this.cognome = cognome;
    }
  
    get id() {
      return this._id;
    }
  
    set id(id: number) {
      this._id = id;
    }
  
  }
  