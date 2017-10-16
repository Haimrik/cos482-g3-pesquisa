import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { PesquisaUsuarioCos482G3Module } from './usuario/usuario-cos-482-g-3.module';
import { PesquisaAlunoCos482G3Module } from './aluno/aluno-cos-482-g-3.module';
import { PesquisaProfessorCos482G3Module } from './professor/professor-cos-482-g-3.module';
import { PesquisaPublicacaoCos482G3Module } from './publicacao/publicacao-cos-482-g-3.module';
import { PesquisaSeminarioCos482G3Module } from './seminario/seminario-cos-482-g-3.module';
import { PesquisaParticipacaoBancaCos482G3Module } from './participacao-banca/participacao-banca-cos-482-g-3.module';
import { PesquisaDefesaCos482G3Module } from './defesa/defesa-cos-482-g-3.module';
import { PesquisaReuniaoCos482G3Module } from './reuniao/reuniao-cos-482-g-3.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        PesquisaUsuarioCos482G3Module,
        PesquisaAlunoCos482G3Module,
        PesquisaProfessorCos482G3Module,
        PesquisaPublicacaoCos482G3Module,
        PesquisaSeminarioCos482G3Module,
        PesquisaParticipacaoBancaCos482G3Module,
        PesquisaDefesaCos482G3Module,
        PesquisaReuniaoCos482G3Module,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PesquisaEntityModule {}
