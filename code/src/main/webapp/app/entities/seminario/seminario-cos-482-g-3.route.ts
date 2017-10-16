import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { SeminarioCos482G3Component } from './seminario-cos-482-g-3.component';
import { SeminarioCos482G3DetailComponent } from './seminario-cos-482-g-3-detail.component';
import { SeminarioCos482G3PopupComponent } from './seminario-cos-482-g-3-dialog.component';
import { SeminarioCos482G3DeletePopupComponent } from './seminario-cos-482-g-3-delete-dialog.component';

@Injectable()
export class SeminarioCos482G3ResolvePagingParams implements Resolve<any> {

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

export const seminarioRoute: Routes = [
    {
        path: 'seminario-cos-482-g-3',
        component: SeminarioCos482G3Component,
        resolve: {
            'pagingParams': SeminarioCos482G3ResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.seminario.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'seminario-cos-482-g-3/:id',
        component: SeminarioCos482G3DetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.seminario.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const seminarioPopupRoute: Routes = [
    {
        path: 'seminario-cos-482-g-3-new',
        component: SeminarioCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.seminario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'seminario-cos-482-g-3/:id/edit',
        component: SeminarioCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.seminario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'seminario-cos-482-g-3/:id/delete',
        component: SeminarioCos482G3DeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.seminario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
