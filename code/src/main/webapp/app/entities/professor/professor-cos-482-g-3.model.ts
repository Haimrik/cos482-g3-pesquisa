import { BaseEntity } from './../../shared';

export class ProfessorCos482G3 implements BaseEntity {
    constructor(
        public id?: number,
        public nome?: string,
        public matricula?: string,
        public linkLattes?: string,
        public programa?: string,
        public linhaDePesquisa?: string,
        public areasDeInteresse?: string,
        public usuarioId?: number,
        public alunos?: BaseEntity[],
        public participacaoBancas?: BaseEntity[],
        public reuniaos?: BaseEntity[],
        public coorientadors?: BaseEntity[],
    ) {
    }
}
