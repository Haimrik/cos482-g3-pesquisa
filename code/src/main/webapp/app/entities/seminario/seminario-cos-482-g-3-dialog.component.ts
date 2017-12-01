import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SeminarioCos482G3 } from './seminario-cos-482-g-3.model';
import { SeminarioCos482G3PopupService } from './seminario-cos-482-g-3-popup.service';
import { SeminarioCos482G3Service } from './seminario-cos-482-g-3.service';
import { AlunoCos482G3, AlunoCos482G3Service } from '../aluno';
import { ProfessorCos482G3, ProfessorCos482G3Service } from '../professor';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-seminario-cos-482-g-3-dialog',
    templateUrl: './seminario-cos-482-g-3-dialog.component.html'
})
export class SeminarioCos482G3DialogComponent implements OnInit {

    seminario: SeminarioCos482G3;
    isSaving: boolean;

    alunos: AlunoCos482G3[];

    professors: ProfessorCos482G3[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private seminarioService: SeminarioCos482G3Service,
        private alunoService: AlunoCos482G3Service,
        private professorService: ProfessorCos482G3Service,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.alunoService.query()
            .subscribe((res: ResponseWrapper) => { this.alunos = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.professorService.query()
            .subscribe((res: ResponseWrapper) => { this.professors = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
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

    trackAlunoById(index: number, item: AlunoCos482G3) {
        return item.id;
    }

    trackProfessorById(index: number, item: ProfessorCos482G3) {
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
