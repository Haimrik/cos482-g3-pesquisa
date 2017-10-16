import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PesquisaSharedModule } from '../../shared';
import {
    ReuniaoCos482G3Service,
    ReuniaoCos482G3PopupService,
    ReuniaoCos482G3Component,
    ReuniaoCos482G3DetailComponent,
    ReuniaoCos482G3DialogComponent,
    ReuniaoCos482G3PopupComponent,
    ReuniaoCos482G3DeletePopupComponent,
    ReuniaoCos482G3DeleteDialogComponent,
    reuniaoRoute,
    reuniaoPopupRoute,
    ReuniaoCos482G3ResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...reuniaoRoute,
    ...reuniaoPopupRoute,
];

@NgModule({
    imports: [
        PesquisaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ReuniaoCos482G3Component,
        ReuniaoCos482G3DetailComponent,
        ReuniaoCos482G3DialogComponent,
        ReuniaoCos482G3DeleteDialogComponent,
        ReuniaoCos482G3PopupComponent,
        ReuniaoCos482G3DeletePopupComponent,
    ],
    entryComponents: [
        ReuniaoCos482G3Component,
        ReuniaoCos482G3DialogComponent,
        ReuniaoCos482G3PopupComponent,
        ReuniaoCos482G3DeleteDialogComponent,
        ReuniaoCos482G3DeletePopupComponent,
    ],
    providers: [
        ReuniaoCos482G3Service,
        ReuniaoCos482G3PopupService,
        ReuniaoCos482G3ResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PesquisaReuniaoCos482G3Module {}
