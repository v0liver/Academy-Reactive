export class Persona {
    private _nome: string ='';
    private _cognome: string = '';
    private _eta: number = 0;

    constructor(){}

    /**
     * Getter nome
     * @return {string }
     */
	public get nome(): string  {
		return this._nome;
	}

    /**
     * Getter cognome
     * @return {string }
     */
	public get cognome(): string  {
		return this._cognome;
	}

    /**
     * Getter eta
     * @return {number }
     */
	public get eta(): number  {
		return this._eta;
	}

    /**
     * Setter nome
     * @param {string } value
     */
	public set nome(value: string ) {
		this._nome = value;
	}

    /**
     * Setter cognome
     * @param {string } value
     */
	public set cognome(value: string ) {
		this._cognome = value;
	}

    /**
     * Setter eta
     * @param {number } value
     */
	public set eta(value: number ) {
		this._eta = value;
	}

 
    
}