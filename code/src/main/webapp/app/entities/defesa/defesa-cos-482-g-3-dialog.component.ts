import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { DefesaCos482G3 } from './defesa-cos-482-g-3.model';
import { DefesaCos482G3PopupService } from './defesa-cos-482-g-3-popup.service';
import { DefesaCos482G3Service } from './defesa-cos-482-g-3.service';
import { AlunoCos482G3, AlunoCos482G3Service } from '../aluno';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-defesa-cos-482-g-3-dialog',
    templateUrl: './defesa-cos-482-g-3-dialog.component.html'
})
export class DefesaCos482G3DialogComponent implements OnInit {

    defesa: DefesaCos482G3;
    isSaving: boolean;

    alunos: AlunoCos482G3[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private defesaService: DefesaCos482G3Service,
        private alunoService: AlunoCos482G3Service,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.alunoService.query()
            .subscribe((res: ResponseWrapper) => { this.alunos = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.defesa.id !== undefined) {
            this.subscribeToSaveResponse(
                this.defesaService.update(this.defesa));
        } else {
            this.subscribeToSaveResponse(
                this.defesaService.create(this.defesa));
        }
    }

    private subscribeToSaveResponse(result: Observable<DefesaCos482G3>) {
        result.subscribe((res: DefesaCos482G3) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: DefesaCos482G3) {
        this.eventManager.broadcast({ name: 'defesaListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackAlunoById(index: number, item: AlunoCos482G3) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-defesa-cos-482-g-3-popup',
    template: ''
})
export class DefesaCos482G3PopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private defesaPopupService: DefesaCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.defesaPopupService
                    .open(DefesaCos482G3DialogComponent as Component, params['id']);
            } else {
                this.defesaPopupService
                    .open(DefesaCos482G3DialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
