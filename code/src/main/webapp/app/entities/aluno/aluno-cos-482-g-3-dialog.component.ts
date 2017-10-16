import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AlunoCos482G3 } from './aluno-cos-482-g-3.model';
import { AlunoCos482G3PopupService } from './aluno-cos-482-g-3-popup.service';
import { AlunoCos482G3Service } from './aluno-cos-482-g-3.service';
import { UsuarioCos482G3, UsuarioCos482G3Service } from '../usuario';
import { ProfessorCos482G3, ProfessorCos482G3Service } from '../professor';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-aluno-cos-482-g-3-dialog',
    templateUrl: './aluno-cos-482-g-3-dialog.component.html'
})
export class AlunoCos482G3DialogComponent implements OnInit {

    aluno: AlunoCos482G3;
    isSaving: boolean;

    usuarios: UsuarioCos482G3[];

    professors: ProfessorCos482G3[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private alunoService: AlunoCos482G3Service,
        private usuarioService: UsuarioCos482G3Service,
        private professorService: ProfessorCos482G3Service,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.usuarioService
            .query({filter: 'aluno-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.aluno.usuarioId) {
                    this.usuarios = res.json;
                } else {
                    this.usuarioService
                        .find(this.aluno.usuarioId)
                        .subscribe((subRes: UsuarioCos482G3) => {
                            this.usuarios = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
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

    trackUsuarioById(index: number, item: UsuarioCos482G3) {
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
