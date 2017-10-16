import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ProfessorCos482G3 } from './professor-cos-482-g-3.model';
import { ProfessorCos482G3PopupService } from './professor-cos-482-g-3-popup.service';
import { ProfessorCos482G3Service } from './professor-cos-482-g-3.service';
import { UsuarioCos482G3, UsuarioCos482G3Service } from '../usuario';
import { AlunoCos482G3, AlunoCos482G3Service } from '../aluno';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-professor-cos-482-g-3-dialog',
    templateUrl: './professor-cos-482-g-3-dialog.component.html'
})
export class ProfessorCos482G3DialogComponent implements OnInit {

    professor: ProfessorCos482G3;
    isSaving: boolean;

    usuarios: UsuarioCos482G3[];

    alunos: AlunoCos482G3[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private professorService: ProfessorCos482G3Service,
        private usuarioService: UsuarioCos482G3Service,
        private alunoService: AlunoCos482G3Service,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.usuarioService
            .query({filter: 'professor-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.professor.usuarioId) {
                    this.usuarios = res.json;
                } else {
                    this.usuarioService
                        .find(this.professor.usuarioId)
                        .subscribe((subRes: UsuarioCos482G3) => {
                            this.usuarios = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.alunoService.query()
            .subscribe((res: ResponseWrapper) => { this.alunos = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.professor.id !== undefined) {
            this.subscribeToSaveResponse(
                this.professorService.update(this.professor));
        } else {
            this.subscribeToSaveResponse(
                this.professorService.create(this.professor));
        }
    }

    private subscribeToSaveResponse(result: Observable<ProfessorCos482G3>) {
        result.subscribe((res: ProfessorCos482G3) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: ProfessorCos482G3) {
        this.eventManager.broadcast({ name: 'professorListModification', content: 'OK'});
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

    trackAlunoById(index: number, item: AlunoCos482G3) {
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
    selector: 'jhi-professor-cos-482-g-3-popup',
    template: ''
})
export class ProfessorCos482G3PopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private professorPopupService: ProfessorCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.professorPopupService
                    .open(ProfessorCos482G3DialogComponent as Component, params['id']);
            } else {
                this.professorPopupService
                    .open(ProfessorCos482G3DialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
