import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UsuarioCos482G3Component } from './usuario-cos-482-g-3.component';
import { UsuarioCos482G3DetailComponent } from './usuario-cos-482-g-3-detail.component';
import { UsuarioCos482G3PopupComponent } from './usuario-cos-482-g-3-dialog.component';
import { UsuarioCos482G3DeletePopupComponent } from './usuario-cos-482-g-3-delete-dialog.component';

@Injectable()
export class UsuarioCos482G3ResolvePagingParams implements Resolve<any> {

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

export const usuarioRoute: Routes = [
    {
        path: 'usuario-cos-482-g-3',
        component: UsuarioCos482G3Component,
        resolve: {
            'pagingParams': UsuarioCos482G3ResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.usuario.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'usuario-cos-482-g-3/:id',
        component: UsuarioCos482G3DetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.usuario.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const usuarioPopupRoute: Routes = [
    {
        path: 'usuario-cos-482-g-3-new',
        component: UsuarioCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.usuario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'usuario-cos-482-g-3/:id/edit',
        component: UsuarioCos482G3PopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.usuario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'usuario-cos-482-g-3/:id/delete',
        component: UsuarioCos482G3DeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pesquisaApp.usuario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
