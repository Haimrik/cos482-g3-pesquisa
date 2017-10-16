import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { UsuarioCos482G3 } from './usuario-cos-482-g-3.model';
import { UsuarioCos482G3PopupService } from './usuario-cos-482-g-3-popup.service';
import { UsuarioCos482G3Service } from './usuario-cos-482-g-3.service';
import { PublicacaoCos482G3, PublicacaoCos482G3Service } from '../publicacao';
import { AlunoCos482G3, AlunoCos482G3Service } from '../aluno';
import { ProfessorCos482G3, ProfessorCos482G3Service } from '../professor';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-usuario-cos-482-g-3-dialog',
    templateUrl: './usuario-cos-482-g-3-dialog.component.html'
})
export class UsuarioCos482G3DialogComponent implements OnInit {

    usuario: UsuarioCos482G3;
    isSaving: boolean;

    publicacaos: PublicacaoCos482G3[];

    alunos: AlunoCos482G3[];

    professors: ProfessorCos482G3[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private usuarioService: UsuarioCos482G3Service,
        private publicacaoService: PublicacaoCos482G3Service,
        private alunoService: AlunoCos482G3Service,
        private professorService: ProfessorCos482G3Service,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.publicacaoService.query()
            .subscribe((res: ResponseWrapper) => { this.publicacaos = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
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
        if (this.usuario.id !== undefined) {
            this.subscribeToSaveResponse(
                this.usuarioService.update(this.usuario));
        } else {
            this.subscribeToSaveResponse(
                this.usuarioService.create(this.usuario));
        }
    }

    private subscribeToSaveResponse(result: Observable<UsuarioCos482G3>) {
        result.subscribe((res: UsuarioCos482G3) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: UsuarioCos482G3) {
        this.eventManager.broadcast({ name: 'usuarioListModification', content: 'OK'});
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
    selector: 'jhi-usuario-cos-482-g-3-popup',
    template: ''
})
export class UsuarioCos482G3PopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private usuarioPopupService: UsuarioCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.usuarioPopupService
                    .open(UsuarioCos482G3DialogComponent as Component, params['id']);
            } else {
                this.usuarioPopupService
                    .open(UsuarioCos482G3DialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
