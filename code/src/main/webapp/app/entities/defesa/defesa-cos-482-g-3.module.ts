import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PesquisaSharedModule } from '../../shared';
import {
    DefesaCos482G3Service,
    DefesaCos482G3PopupService,
    DefesaCos482G3Component,
    DefesaCos482G3DetailComponent,
    DefesaCos482G3DialogComponent,
    DefesaCos482G3PopupComponent,
    DefesaCos482G3DeletePopupComponent,
    DefesaCos482G3DeleteDialogComponent,
    defesaRoute,
    defesaPopupRoute,
    DefesaCos482G3ResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...defesaRoute,
    ...defesaPopupRoute,
];

@NgModule({
    imports: [
        PesquisaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DefesaCos482G3Component,
        DefesaCos482G3DetailComponent,
        DefesaCos482G3DialogComponent,
        DefesaCos482G3DeleteDialogComponent,
        DefesaCos482G3PopupComponent,
        DefesaCos482G3DeletePopupComponent,
    ],
    entryComponents: [
        DefesaCos482G3Component,
        DefesaCos482G3DialogComponent,
        DefesaCos482G3PopupComponent,
        DefesaCos482G3DeleteDialogComponent,
        DefesaCos482G3DeletePopupComponent,
    ],
    providers: [
        DefesaCos482G3Service,
        DefesaCos482G3PopupService,
        DefesaCos482G3ResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PesquisaDefesaCos482G3Module {}
