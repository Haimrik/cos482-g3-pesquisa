import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AlunoCos482G3Component } from './aluno-cos-482-g-3.component';
import { AlunoCos482G3DetailComponent } from './aluno-cos-482-g-3-detail.component';
import { AlunoCos482G3PopupComponent } from './aluno-cos-482-g-3-dialog.component';
import { AlunoCos482G3DeletePopupComponent } from './aluno-cos-482-g-3-delete-dialog.component';

@Injectable()
export class AlunoCos482G3ResolvePagingParams implements Resolve<any> {

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

export const alunoRoute: Routes = [
    {
        path: 'aluno-cos-482-g-3',
        component: AlunoCos482G3Component,
        resolve: {
            'pagingParams': AlunoCos482G3ResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.aluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'aluno-cos-482-g-3/:id',
        component: AlunoCos482G3DetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.aluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const alunoPopupRoute: Routes = [
    {
        path: 'aluno-cos-482-g-3-new',
        component: AlunoCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.aluno.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'aluno-cos-482-g-3/:id/edit',
        component: AlunoCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.aluno.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'aluno-cos-482-g-3/:id/delete',
        component: AlunoCos482G3DeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.aluno.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
