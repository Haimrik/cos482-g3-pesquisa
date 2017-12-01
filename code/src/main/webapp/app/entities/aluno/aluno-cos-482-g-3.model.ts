import { BaseEntity } from './../../shared';

export class AlunoCos482G3 implements BaseEntity {
    constructor(
        public id?: number,
        public nome?: string,
        public dre?: string,
        public dataDeEntrada?: any,
        public publicacaos?: BaseEntity[],
        public seminarios?: BaseEntity[],
        public defesas?: BaseEntity[],
        public reuniaos?: BaseEntity[],
        public copublicacaos?: BaseEntity[],
        public orientadorId?: number,
        public coorientadors?: BaseEntity[],
    ) {
    }
}
