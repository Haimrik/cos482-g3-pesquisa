import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ParticipacaoBancaCos482G3 } from './participacao-banca-cos-482-g-3.model';
import { ParticipacaoBancaCos482G3PopupService } from './participacao-banca-cos-482-g-3-popup.service';
import { ParticipacaoBancaCos482G3Service } from './participacao-banca-cos-482-g-3.service';
import { DefesaCos482G3, DefesaCos482G3Service } from '../defesa';
import { ProfessorCos482G3, ProfessorCos482G3Service } from '../professor';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-participacao-banca-cos-482-g-3-dialog',
    templateUrl: './participacao-banca-cos-482-g-3-dialog.component.html'
})
export class ParticipacaoBancaCos482G3DialogComponent implements OnInit {

    participacaoBanca: ParticipacaoBancaCos482G3;
    isSaving: boolean;

    defesas: DefesaCos482G3[];

    professors: ProfessorCos482G3[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private participacaoBancaService: ParticipacaoBancaCos482G3Service,
        private defesaService: DefesaCos482G3Service,
        private professorService: ProfessorCos482G3Service,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.defesaService.query()
            .subscribe((res: ResponseWrapper) => { this.defesas = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.professorService.query()
            .subscribe((res: ResponseWrapper) => { this.professors = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.participacaoBanca.id !== undefined) {
            this.subscribeToSaveResponse(
                this.participacaoBancaService.update(this.participacaoBanca));
        } else {
            this.subscribeToSaveResponse(
                this.participacaoBancaService.create(this.participacaoBanca));
        }
    }

    private subscribeToSaveResponse(result: Observable<ParticipacaoBancaCos482G3>) {
        result.subscribe((res: ParticipacaoBancaCos482G3) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: ParticipacaoBancaCos482G3) {
        this.eventManager.broadcast({ name: 'participacaoBancaListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackDefesaById(index: number, item: DefesaCos482G3) {
        return item.id;
    }

    trackProfessorById(index: number, item: ProfessorCos482G3) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-participacao-banca-cos-482-g-3-popup',
    template: ''
})
export class ParticipacaoBancaCos482G3PopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private participacaoBancaPopupService: ParticipacaoBancaCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.participacaoBancaPopupService
                    .open(ParticipacaoBancaCos482G3DialogComponent as Component, params['id']);
            } else {
                this.participacaoBancaPopupService
                    .open(ParticipacaoBancaCos482G3DialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
