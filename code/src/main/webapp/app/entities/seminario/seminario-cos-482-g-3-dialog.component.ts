import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SeminarioCos482G3 } from './seminario-cos-482-g-3.model';
import { SeminarioCos482G3PopupService } from './seminario-cos-482-g-3-popup.service';
import { SeminarioCos482G3Service } from './seminario-cos-482-g-3.service';
import { UsuarioCos482G3, UsuarioCos482G3Service } from '../usuario';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-seminario-cos-482-g-3-dialog',
    templateUrl: './seminario-cos-482-g-3-dialog.component.html'
})
export class SeminarioCos482G3DialogComponent implements OnInit {

    seminario: SeminarioCos482G3;
    isSaving: boolean;

    usuarios: UsuarioCos482G3[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private seminarioService: SeminarioCos482G3Service,
        private usuarioService: UsuarioCos482G3Service,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.usuarioService.query()
            .subscribe((res: ResponseWrapper) => { this.usuarios = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.seminario.id !== undefined) {
            this.subscribeToSaveResponse(
                this.seminarioService.update(this.seminario));
        } else {
            this.subscribeToSaveResponse(
                this.seminarioService.create(this.seminario));
        }
    }

    private subscribeToSaveResponse(result: Observable<SeminarioCos482G3>) {
        result.subscribe((res: SeminarioCos482G3) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: SeminarioCos482G3) {
        this.eventManager.broadcast({ name: 'seminarioListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackUsuarioById(index: number, item: UsuarioCos482G3) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-seminario-cos-482-g-3-popup',
    template: ''
})
export class SeminarioCos482G3PopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private seminarioPopupService: SeminarioCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.seminarioPopupService
                    .open(SeminarioCos482G3DialogComponent as Component, params['id']);
            } else {
                this.seminarioPopupService
                    .open(SeminarioCos482G3DialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
