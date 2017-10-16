import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ProfessorCos482G3Component } from './professor-cos-482-g-3.component';
import { ProfessorCos482G3DetailComponent } from './professor-cos-482-g-3-detail.component';
import { ProfessorCos482G3PopupComponent } from './professor-cos-482-g-3-dialog.component';
import { ProfessorCos482G3DeletePopupComponent } from './professor-cos-482-g-3-delete-dialog.component';

@Injectable()
export class ProfessorCos482G3ResolvePagingParams implements Resolve<any> {

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

export const professorRoute: Routes = [
    {
        path: 'professor-cos-482-g-3',
        component: ProfessorCos482G3Component,
        resolve: {
            'pagingParams': ProfessorCos482G3ResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.professor.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'professor-cos-482-g-3/:id',
        component: ProfessorCos482G3DetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.professor.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const professorPopupRoute: Routes = [
    {
        path: 'professor-cos-482-g-3-new',
        component: ProfessorCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.professor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'professor-cos-482-g-3/:id/edit',
        component: ProfessorCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.professor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'professor-cos-482-g-3/:id/delete',
        component: ProfessorCos482G3DeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.professor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
