import { BaseEntity } from './../../shared';

export class SeminarioCos482G3 implements BaseEntity {
    constructor(
        public id?: number,
        public titulo?: string,
        public dataEHora?: any,
        public local?: string,
        public organizadorAlunoId?: number,
        public organizadorProfessorId?: number,
    ) {
    }
}
