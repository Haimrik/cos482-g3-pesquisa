import { BaseEntity } from './../../shared';

export class AlunoCos482G3 implements BaseEntity {
    constructor(
        public id?: number,
        public nome?: string,
        public dre?: string,
        public dataDeEntrada?: any,
        public usuarioId?: number,
        public publicacaos?: BaseEntity[],
        public defesas?: BaseEntity[],
        public reuniaos?: BaseEntity[],
        public orientadorId?: number,
        public alunos?: BaseEntity[],
    ) {
    }
}
