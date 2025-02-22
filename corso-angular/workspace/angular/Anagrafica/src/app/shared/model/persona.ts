export class Persona {

    private _id?: number;
    private _nome: string;
    private _cognome: string;
    private _dataNascita: Date;
    private _citta: string;
   

    constructor(nome: string, cognome: string,citta: string, dataNascita: Date, id?: number) {
        this._id = id;
        this._nome = nome;
        this._cognome = cognome;
        this._citta=citta;
        this._dataNascita = dataNascita;
    }


    /**
     * Getter citta
     * @return {string}
     */
	public get citta(): string {
		return this._citta;
	}

    /**
     * Setter citta
     * @param {string} value
     */
	public set citta(value: string) {
		this._citta = value;
	}


	
    

    public get id(): number | undefined {
        return this._id;
    }

    public set id(id: number) {
        this._id = id;
    }

    /**
     * Getter nome
     * @return {string }
     */
    public get nome(): string {
        return this._nome;
    }

    /**
     * Getter cognome
     * @return {string}
     */
    public get cognome(): string {
        return this._cognome;
    }

    /**
     * Getter dataNascita
     * @return {Date}
     */
    public get dataNascita(): Date {
        return this._dataNascita;
    }

    /**
     * Setter nome
     * @param {string } value
     */
    public set nome(value: string) {
        this._nome = value;
    }

    /**
     * Setter cognome
     * @param {string} value
     */
    public set cognome(value: string) {
        this._cognome = value;
    }

    /**
     * Setter dataNascita
     * @param {Date} value
     */
    public set dataNascita(value: Date) {
        this._dataNascita = value;
    }


}