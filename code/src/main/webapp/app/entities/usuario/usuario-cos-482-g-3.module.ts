import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PesquisaSharedModule } from '../../shared';
import {
    UsuarioCos482G3Service,
    UsuarioCos482G3PopupService,
    UsuarioCos482G3Component,
    UsuarioCos482G3DetailComponent,
    UsuarioCos482G3DialogComponent,
    UsuarioCos482G3PopupComponent,
    UsuarioCos482G3DeletePopupComponent,
    UsuarioCos482G3DeleteDialogComponent,
    usuarioRoute,
    usuarioPopupRoute,
    UsuarioCos482G3ResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...usuarioRoute,
    ...usuarioPopupRoute,
];

@NgModule({
    imports: [
        PesquisaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        UsuarioCos482G3Component,
        UsuarioCos482G3DetailComponent,
        UsuarioCos482G3DialogComponent,
        UsuarioCos482G3DeleteDialogComponent,
        UsuarioCos482G3PopupComponent,
        UsuarioCos482G3DeletePopupComponent,
    ],
    entryComponents: [
        UsuarioCos482G3Component,
        UsuarioCos482G3DialogComponent,
        UsuarioCos482G3PopupComponent,
        UsuarioCos482G3DeleteDialogComponent,
        UsuarioCos482G3DeletePopupComponent,
    ],
    providers: [
        UsuarioCos482G3Service,
        UsuarioCos482G3PopupService,
        UsuarioCos482G3ResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PesquisaUsuarioCos482G3Module {}
