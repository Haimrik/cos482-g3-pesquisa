import { BaseEntity } from './../../shared';

export const enum EstadoAprovacaoDefesa {
    'PENDENTE',
    'APROVADO',
    'REPROVADO'
}

export class ParticipacaoBancaCos482G3 implements BaseEntity {
    constructor(
        public id?: number,
        public confirmado?: boolean,
        public estadoAprovacaoDefesa?: EstadoAprovacaoDefesa,
        public defesaId?: number,
        public professorId?: number,
    ) {
        this.confirmado = false;
    }
}
