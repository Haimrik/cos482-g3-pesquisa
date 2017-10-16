import { BaseEntity } from './../../shared';

export class UsuarioCos482G3 implements BaseEntity {
    constructor(
        public id?: number,
        public seminarios?: BaseEntity[],
        public publicacaos?: BaseEntity[],
        public alunoId?: number,
        public professorId?: number,
    ) {
    }
}
