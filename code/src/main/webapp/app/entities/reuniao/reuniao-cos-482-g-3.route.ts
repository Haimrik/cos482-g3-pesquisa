import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ReuniaoCos482G3Component } from './reuniao-cos-482-g-3.component';
import { ReuniaoCos482G3DetailComponent } from './reuniao-cos-482-g-3-detail.component';
import { ReuniaoCos482G3PopupComponent } from './reuniao-cos-482-g-3-dialog.component';
import { ReuniaoCos482G3DeletePopupComponent } from './reuniao-cos-482-g-3-delete-dialog.component';

@Injectable()
export class ReuniaoCos482G3ResolvePagingParams implements Resolve<any> {

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

export const reuniaoRoute: Routes = [
    {
        path: 'reuniao-cos-482-g-3',
        component: ReuniaoCos482G3Component,
        resolve: {
            'pagingParams': ReuniaoCos482G3ResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.reuniao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'reuniao-cos-482-g-3/:id',
        component: ReuniaoCos482G3DetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.reuniao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const reuniaoPopupRoute: Routes = [
    {
        path: 'reuniao-cos-482-g-3-new',
        component: ReuniaoCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.reuniao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'reuniao-cos-482-g-3/:id/edit',
        component: ReuniaoCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.reuniao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'reuniao-cos-482-g-3/:id/delete',
        component: ReuniaoCos482G3DeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.reuniao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
