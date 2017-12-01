import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AlunoCos482G3 } from './aluno-cos-482-g-3.model';
import { AlunoCos482G3PopupService } from './aluno-cos-482-g-3-popup.service';
import { AlunoCos482G3Service } from './aluno-cos-482-g-3.service';
import { PublicacaoCos482G3, PublicacaoCos482G3Service } from '../publicacao';
import { ProfessorCos482G3, ProfessorCos482G3Service } from '../professor';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-aluno-cos-482-g-3-dialog',
    templateUrl: './aluno-cos-482-g-3-dialog.component.html'
})
export class AlunoCos482G3DialogComponent implements OnInit {

    aluno: AlunoCos482G3;
    isSaving: boolean;

    publicacaos: PublicacaoCos482G3[];

    professors: ProfessorCos482G3[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private alunoService: AlunoCos482G3Service,
        private publicacaoService: PublicacaoCos482G3Service,
        private professorService: ProfessorCos482G3Service,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.publicacaoService.query()
            .subscribe((res: ResponseWrapper) => { this.publicacaos = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.professorService.query()
            .subscribe((res: ResponseWrapper) => { this.professors = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.aluno.id !== undefined) {
            this.subscribeToSaveResponse(
                this.alunoService.update(this.aluno));
        } else {
            this.subscribeToSaveResponse(
                this.alunoService.create(this.aluno));
        }
    }

    private subscribeToSaveResponse(result: Observable<AlunoCos482G3>) {
        result.subscribe((res: AlunoCos482G3) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AlunoCos482G3) {
        this.eventManager.broadcast({ name: 'alunoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackPublicacaoById(index: number, item: PublicacaoCos482G3) {
        return item.id;
    }

    trackProfessorById(index: number, item: ProfessorCos482G3) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-aluno-cos-482-g-3-popup',
    template: ''
})
export class AlunoCos482G3PopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private alunoPopupService: AlunoCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.alunoPopupService
                    .open(AlunoCos482G3DialogComponent as Component, params['id']);
            } else {
                this.alunoPopupService
                    .open(AlunoCos482G3DialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
