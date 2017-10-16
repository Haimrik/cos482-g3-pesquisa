import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PesquisaSharedModule } from '../../shared';
import {
    ParticipacaoBancaCos482G3Service,
    ParticipacaoBancaCos482G3PopupService,
    ParticipacaoBancaCos482G3Component,
    ParticipacaoBancaCos482G3DetailComponent,
    ParticipacaoBancaCos482G3DialogComponent,
    ParticipacaoBancaCos482G3PopupComponent,
    ParticipacaoBancaCos482G3DeletePopupComponent,
    ParticipacaoBancaCos482G3DeleteDialogComponent,
    participacaoBancaRoute,
    participacaoBancaPopupRoute,
    ParticipacaoBancaCos482G3ResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...participacaoBancaRoute,
    ...participacaoBancaPopupRoute,
];

@NgModule({
    imports: [
        PesquisaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ParticipacaoBancaCos482G3Component,
        ParticipacaoBancaCos482G3DetailComponent,
        ParticipacaoBancaCos482G3DialogComponent,
        ParticipacaoBancaCos482G3DeleteDialogComponent,
        ParticipacaoBancaCos482G3PopupComponent,
        ParticipacaoBancaCos482G3DeletePopupComponent,
    ],
    entryComponents: [
        ParticipacaoBancaCos482G3Component,
        ParticipacaoBancaCos482G3DialogComponent,
        ParticipacaoBancaCos482G3PopupComponent,
        ParticipacaoBancaCos482G3DeleteDialogComponent,
        ParticipacaoBancaCos482G3DeletePopupComponent,
    ],
    providers: [
        ParticipacaoBancaCos482G3Service,
        ParticipacaoBancaCos482G3PopupService,
        ParticipacaoBancaCos482G3ResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PesquisaParticipacaoBancaCos482G3Module {}
