import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PesquisaSharedModule } from '../../shared';
import {
    AlunoCos482G3Service,
    AlunoCos482G3PopupService,
    AlunoCos482G3Component,
    AlunoCos482G3DetailComponent,
    AlunoCos482G3DialogComponent,
    AlunoCos482G3PopupComponent,
    AlunoCos482G3DeletePopupComponent,
    AlunoCos482G3DeleteDialogComponent,
    alunoRoute,
    alunoPopupRoute,
    AlunoCos482G3ResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...alunoRoute,
    ...alunoPopupRoute,
];

@NgModule({
    imports: [
        PesquisaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AlunoCos482G3Component,
        AlunoCos482G3DetailComponent,
        AlunoCos482G3DialogComponent,
        AlunoCos482G3DeleteDialogComponent,
        AlunoCos482G3PopupComponent,
        AlunoCos482G3DeletePopupComponent,
    ],
    entryComponents: [
        AlunoCos482G3Component,
        AlunoCos482G3DialogComponent,
        AlunoCos482G3PopupComponent,
        AlunoCos482G3DeleteDialogComponent,
        AlunoCos482G3DeletePopupComponent,
    ],
    providers: [
        AlunoCos482G3Service,
        AlunoCos482G3PopupService,
        AlunoCos482G3ResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PesquisaAlunoCos482G3Module {}
