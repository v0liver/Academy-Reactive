export class SearchPersona {
    private _nome: string = '';
    private _cognome: string = '';
    private _dataNascitaMin: string = '';
    private _dataNascitaMax: string = '';
    private _citta: string = '';
    private _page: number = 0;
    private _pageSize: number = 5;
    private _sortBy: string = '';
    private _sortDirection: string = '';



    constructor() {
    }


    /**
     * Getter nome
     * @return {string}
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
     * Getter dataNascitaMin
     * @return {string}
     */
    public get dataNascitaMin(): string {
        return this._dataNascitaMin;
    }

    /**
     * Getter dataNascitaMax
     * @return {string}
     */
    public get dataNascitaMax(): string {
        return this._dataNascitaMax;
    }

    /**
     * Getter citta
     * @return {string}
     */
    public get citta(): string {
        return this._citta;
    }

    /**
     * Getter page
     * @return {number}
     */
    public get page(): number {
        return this._page;
    }

    /**
     * Getter pageSize
     * @return {number}
     */
    public get pageSize(): number {
        return this._pageSize;
    }

    /**
     * Getter sortBy
     * @return {string}
     */
    public get sortBy(): string {
        return this._sortBy;
    }

    /**
     * Getter sortDirection
     * @return {string}
     */
    public get sortDirection(): string {
        return this._sortDirection;
    }

    /**
     * Setter nome
     * @param {string} value
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
     * Setter dataNascitaMin
     * @param {string} value
     */
    public set dataNascitaMin(value: string) {
        this._dataNascitaMin = value;
    }

    /**
     * Setter dataNascitaMax
     * @param {string} value
     */
    public set dataNascitaMax(value: string) {
        this._dataNascitaMax = value;
    }

    /**
     * Setter citta
     * @param {string} value
     */
    public set citta(value: string) {
        this._citta = value;
    }

    /**
     * Setter page
     * @param {number} value
     */
    public set page(value: number) {
        this._page = value;
    }

    /**
     * Setter pageSize
     * @param {number} value
     */
    public set pageSize(value: number) {
        this._pageSize = value;
    }

    /**
     * Setter sortBy
     * @param {string} value
     */
    public set sortBy(value: string) {
        this._sortBy = value;
    }

    /**
     * Setter sortDirection
     * @param {string} value
     */
    public set sortDirection(value: string) {
        this._sortDirection = value;
    }

}
