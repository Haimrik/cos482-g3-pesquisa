import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ProfessorCos482G3 } from './professor-cos-482-g-3.model';
import { ProfessorCos482G3PopupService } from './professor-cos-482-g-3-popup.service';
import { ProfessorCos482G3Service } from './professor-cos-482-g-3.service';

@Component({
    selector: 'jhi-professor-cos-482-g-3-delete-dialog',
    templateUrl: './professor-cos-482-g-3-delete-dialog.component.html'
})
export class ProfessorCos482G3DeleteDialogComponent {

    professor: ProfessorCos482G3;

    constructor(
        private professorService: ProfessorCos482G3Service,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.professorService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'professorListModification',
                content: 'Deleted an professor'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-professor-cos-482-g-3-delete-popup',
    template: ''
})
export class ProfessorCos482G3DeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private professorPopupService: ProfessorCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.professorPopupService
                .open(ProfessorCos482G3DeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
