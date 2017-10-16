import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ParticipacaoBancaCos482G3Component } from './participacao-banca-cos-482-g-3.component';
import { ParticipacaoBancaCos482G3DetailComponent } from './participacao-banca-cos-482-g-3-detail.component';
import { ParticipacaoBancaCos482G3PopupComponent } from './participacao-banca-cos-482-g-3-dialog.component';
import { ParticipacaoBancaCos482G3DeletePopupComponent } from './participacao-banca-cos-482-g-3-delete-dialog.component';

@Injectable()
export class ParticipacaoBancaCos482G3ResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const participacaoBancaRoute: Routes = [
    {
        path: 'participacao-banca-cos-482-g-3',
        component: ParticipacaoBancaCos482G3Component,
        resolve: {
            'pagingParams': ParticipacaoBancaCos482G3ResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.participacaoBanca.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'participacao-banca-cos-482-g-3/:id',
        component: ParticipacaoBancaCos482G3DetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.participacaoBanca.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const participacaoBancaPopupRoute: Routes = [
    {
        path: 'participacao-banca-cos-482-g-3-new',
        component: ParticipacaoBancaCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.participacaoBanca.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'participacao-banca-cos-482-g-3/:id/edit',
        component: ParticipacaoBancaCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.participacaoBanca.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'participacao-banca-cos-482-g-3/:id/delete',
        component: ParticipacaoBancaCos482G3DeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.participacaoBanca.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
