import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PesquisaSharedModule } from '../../shared';
import {
    SeminarioCos482G3Service,
    SeminarioCos482G3PopupService,
    SeminarioCos482G3Component,
    SeminarioCos482G3DetailComponent,
    SeminarioCos482G3DialogComponent,
    SeminarioCos482G3PopupComponent,
    SeminarioCos482G3DeletePopupComponent,
    SeminarioCos482G3DeleteDialogComponent,
    seminarioRoute,
    seminarioPopupRoute,
    SeminarioCos482G3ResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...seminarioRoute,
    ...seminarioPopupRoute,
];

@NgModule({
    imports: [
        PesquisaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        SeminarioCos482G3Component,
        SeminarioCos482G3DetailComponent,
        SeminarioCos482G3DialogComponent,
        SeminarioCos482G3DeleteDialogComponent,
        SeminarioCos482G3PopupComponent,
        SeminarioCos482G3DeletePopupComponent,
    ],
    entryComponents: [
        SeminarioCos482G3Component,
        SeminarioCos482G3DialogComponent,
        SeminarioCos482G3PopupComponent,
        SeminarioCos482G3DeleteDialogComponent,
        SeminarioCos482G3DeletePopupComponent,
    ],
    providers: [
        SeminarioCos482G3Service,
        SeminarioCos482G3PopupService,
        SeminarioCos482G3ResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PesquisaSeminarioCos482G3Module {}
