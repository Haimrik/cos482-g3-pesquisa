import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { PublicacaoCos482G3 } from './publicacao-cos-482-g-3.model';
import { PublicacaoCos482G3PopupService } from './publicacao-cos-482-g-3-popup.service';
import { PublicacaoCos482G3Service } from './publicacao-cos-482-g-3.service';
import { AlunoCos482G3, AlunoCos482G3Service } from '../aluno';
import { ProfessorCos482G3, ProfessorCos482G3Service } from '../professor';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-publicacao-cos-482-g-3-dialog',
    templateUrl: './publicacao-cos-482-g-3-dialog.component.html'
})
export class PublicacaoCos482G3DialogComponent implements OnInit {

    publicacao: PublicacaoCos482G3;
    isSaving: boolean;

    alunos: AlunoCos482G3[];

    professors: ProfessorCos482G3[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private publicacaoService: PublicacaoCos482G3Service,
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
        if (this.publicacao.id !== undefined) {
            this.subscribeToSaveResponse(
                this.publicacaoService.update(this.publicacao));
        } else {
            this.subscribeToSaveResponse(
                this.publicacaoService.create(this.publicacao));
        }
    }

    private subscribeToSaveResponse(result: Observable<PublicacaoCos482G3>) {
        result.subscribe((res: PublicacaoCos482G3) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: PublicacaoCos482G3) {
        this.eventManager.broadcast({ name: 'publicacaoListModification', content: 'OK'});
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
    selector: 'jhi-publicacao-cos-482-g-3-popup',
    template: ''
})
export class PublicacaoCos482G3PopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private publicacaoPopupService: PublicacaoCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.publicacaoPopupService
                    .open(PublicacaoCos482G3DialogComponent as Component, params['id']);
            } else {
                this.publicacaoPopupService
                    .open(PublicacaoCos482G3DialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
