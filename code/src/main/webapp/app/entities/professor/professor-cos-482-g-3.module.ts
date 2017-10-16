import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PesquisaSharedModule } from '../../shared';
import {
    ProfessorCos482G3Service,
    ProfessorCos482G3PopupService,
    ProfessorCos482G3Component,
    ProfessorCos482G3DetailComponent,
    ProfessorCos482G3DialogComponent,
    ProfessorCos482G3PopupComponent,
    ProfessorCos482G3DeletePopupComponent,
    ProfessorCos482G3DeleteDialogComponent,
    professorRoute,
    professorPopupRoute,
    ProfessorCos482G3ResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...professorRoute,
    ...professorPopupRoute,
];

@NgModule({
    imports: [
        PesquisaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ProfessorCos482G3Component,
        ProfessorCos482G3DetailComponent,
        ProfessorCos482G3DialogComponent,
        ProfessorCos482G3DeleteDialogComponent,
        ProfessorCos482G3PopupComponent,
        ProfessorCos482G3DeletePopupComponent,
    ],
    entryComponents: [
        ProfessorCos482G3Component,
        ProfessorCos482G3DialogComponent,
        ProfessorCos482G3PopupComponent,
        ProfessorCos482G3DeleteDialogComponent,
        ProfessorCos482G3DeletePopupComponent,
    ],
    providers: [
        ProfessorCos482G3Service,
        ProfessorCos482G3PopupService,
        ProfessorCos482G3ResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PesquisaProfessorCos482G3Module {}
