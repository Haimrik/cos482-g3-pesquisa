import { BaseEntity } from './../../shared';

export class ReuniaoCos482G3 implements BaseEntity {
    constructor(
        public id?: number,
        public dataEHora?: any,
        public local?: string,
        public professorId?: number,
        public alunoId?: number,
    ) {
    }
}
