import { BaseEntity } from './../../shared';

export class PublicacaoCos482G3 implements BaseEntity {
    constructor(
        public id?: number,
        public url?: string,
        public pertenceAoPrograma?: boolean,
        public alunoId?: number,
        public coautors?: BaseEntity[],
    ) {
        this.pertenceAoPrograma = false;
    }
}
