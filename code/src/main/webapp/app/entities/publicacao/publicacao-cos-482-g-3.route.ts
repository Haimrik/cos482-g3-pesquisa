import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { PublicacaoCos482G3Component } from './publicacao-cos-482-g-3.component';
import { PublicacaoCos482G3DetailComponent } from './publicacao-cos-482-g-3-detail.component';
import { PublicacaoCos482G3PopupComponent } from './publicacao-cos-482-g-3-dialog.component';
import { PublicacaoCos482G3DeletePopupComponent } from './publicacao-cos-482-g-3-delete-dialog.component';

@Injectable()
export class PublicacaoCos482G3ResolvePagingParams implements Resolve<any> {

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

export const publicacaoRoute: Routes = [
    {
        path: 'publicacao-cos-482-g-3',
        component: PublicacaoCos482G3Component,
        resolve: {
            'pagingParams': PublicacaoCos482G3ResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.publicacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'publicacao-cos-482-g-3/:id',
        component: PublicacaoCos482G3DetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.publicacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const publicacaoPopupRoute: Routes = [
    {
        path: 'publicacao-cos-482-g-3-new',
        component: PublicacaoCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.publicacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'publicacao-cos-482-g-3/:id/edit',
        component: PublicacaoCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.publicacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'publicacao-cos-482-g-3/:id/delete',
        component: PublicacaoCos482G3DeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.publicacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
