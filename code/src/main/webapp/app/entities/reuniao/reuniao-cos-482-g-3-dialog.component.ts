import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ReuniaoCos482G3 } from './reuniao-cos-482-g-3.model';
import { ReuniaoCos482G3PopupService } from './reuniao-cos-482-g-3-popup.service';
import { ReuniaoCos482G3Service } from './reuniao-cos-482-g-3.service';
import { ProfessorCos482G3, ProfessorCos482G3Service } from '../professor';
import { AlunoCos482G3, AlunoCos482G3Service } from '../aluno';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-reuniao-cos-482-g-3-dialog',
    templateUrl: './reuniao-cos-482-g-3-dialog.component.html'
})
export class ReuniaoCos482G3DialogComponent implements OnInit {

    reuniao: ReuniaoCos482G3;
    isSaving: boolean;

    professors: ProfessorCos482G3[];

    alunos: AlunoCos482G3[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private reuniaoService: ReuniaoCos482G3Service,
        private professorService: ProfessorCos482G3Service,
        private alunoService: AlunoCos482G3Service,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.professorService.query()
            .subscribe((res: ResponseWrapper) => { this.professors = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.alunoService.query()
            .subscribe((res: ResponseWrapper) => { this.alunos = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.reuniao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.reuniaoService.update(this.reuniao));
        } else {
            this.subscribeToSaveResponse(
                this.reuniaoService.create(this.reuniao));
        }
    }

    private subscribeToSaveResponse(result: Observable<ReuniaoCos482G3>) {
        result.subscribe((res: ReuniaoCos482G3) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: ReuniaoCos482G3) {
        this.eventManager.broadcast({ name: 'reuniaoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackProfessorById(index: number, item: ProfessorCos482G3) {
        return item.id;
    }

    trackAlunoById(index: number, item: AlunoCos482G3) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-reuniao-cos-482-g-3-popup',
    template: ''
})
export class ReuniaoCos482G3PopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private reuniaoPopupService: ReuniaoCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.reuniaoPopupService
                    .open(ReuniaoCos482G3DialogComponent as Component, params['id']);
            } else {
                this.reuniaoPopupService
                    .open(ReuniaoCos482G3DialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
