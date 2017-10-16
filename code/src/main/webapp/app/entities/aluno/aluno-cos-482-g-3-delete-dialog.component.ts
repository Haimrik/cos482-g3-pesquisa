import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AlunoCos482G3 } from './aluno-cos-482-g-3.model';
import { AlunoCos482G3PopupService } from './aluno-cos-482-g-3-popup.service';
import { AlunoCos482G3Service } from './aluno-cos-482-g-3.service';

@Component({
    selector: 'jhi-aluno-cos-482-g-3-delete-dialog',
    templateUrl: './aluno-cos-482-g-3-delete-dialog.component.html'
})
export class AlunoCos482G3DeleteDialogComponent {

    aluno: AlunoCos482G3;

    constructor(
        private alunoService: AlunoCos482G3Service,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.alunoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'alunoListModification',
                content: 'Deleted an aluno'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-aluno-cos-482-g-3-delete-popup',
    template: ''
})
export class AlunoCos482G3DeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private alunoPopupService: AlunoCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.alunoPopupService
                .open(AlunoCos482G3DeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
