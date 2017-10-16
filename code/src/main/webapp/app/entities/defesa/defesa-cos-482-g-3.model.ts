import { BaseEntity } from './../../shared';

export const enum TipoDefesa {
    'QUALIFICACAO',
    'DEFESADETESE'
}

export class DefesaCos482G3 implements BaseEntity {
    constructor(
        public id?: number,
        public dataEHora?: any,
        public local?: string,
        public arquivoTexto?: string,
        public tipoDefesa?: TipoDefesa,
        public participacaoBancas?: BaseEntity[],
        public alunoId?: number,
    ) {
    }
}
