import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { DefesaCos482G3Component } from './defesa-cos-482-g-3.component';
import { DefesaCos482G3DetailComponent } from './defesa-cos-482-g-3-detail.component';
import { DefesaCos482G3PopupComponent } from './defesa-cos-482-g-3-dialog.component';
import { DefesaCos482G3DeletePopupComponent } from './defesa-cos-482-g-3-delete-dialog.component';

@Injectable()
export class DefesaCos482G3ResolvePagingParams implements Resolve<any> {

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

export const defesaRoute: Routes = [
    {
        path: 'defesa-cos-482-g-3',
        component: DefesaCos482G3Component,
        resolve: {
            'pagingParams': DefesaCos482G3ResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.defesa.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'defesa-cos-482-g-3/:id',
        component: DefesaCos482G3DetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.defesa.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const defesaPopupRoute: Routes = [
    {
        path: 'defesa-cos-482-g-3-new',
        component: DefesaCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.defesa.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'defesa-cos-482-g-3/:id/edit',
        component: DefesaCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.defesa.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'defesa-cos-482-g-3/:id/delete',
        component: DefesaCos482G3DeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.defesa.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
