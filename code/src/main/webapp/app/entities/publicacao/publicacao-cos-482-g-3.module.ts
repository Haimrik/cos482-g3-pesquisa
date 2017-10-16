import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PesquisaSharedModule } from '../../shared';
import {
    PublicacaoCos482G3Service,
    PublicacaoCos482G3PopupService,
    PublicacaoCos482G3Component,
    PublicacaoCos482G3DetailComponent,
    PublicacaoCos482G3DialogComponent,
    PublicacaoCos482G3PopupComponent,
    PublicacaoCos482G3DeletePopupComponent,
    PublicacaoCos482G3DeleteDialogComponent,
    publicacaoRoute,
    publicacaoPopupRoute,
    PublicacaoCos482G3ResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...publicacaoRoute,
    ...publicacaoPopupRoute,
];

@NgModule({
    imports: [
        PesquisaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PublicacaoCos482G3Component,
        PublicacaoCos482G3DetailComponent,
        PublicacaoCos482G3DialogComponent,
        PublicacaoCos482G3DeleteDialogComponent,
        PublicacaoCos482G3PopupComponent,
        PublicacaoCos482G3DeletePopupComponent,
    ],
    entryComponents: [
        PublicacaoCos482G3Component,
        PublicacaoCos482G3DialogComponent,
        PublicacaoCos482G3PopupComponent,
        PublicacaoCos482G3DeleteDialogComponent,
        PublicacaoCos482G3DeletePopupComponent,
    ],
    providers: [
        PublicacaoCos482G3Service,
        PublicacaoCos482G3PopupService,
        PublicacaoCos482G3ResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PesquisaPublicacaoCos482G3Module {}
