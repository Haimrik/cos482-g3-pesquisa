import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SeminarioCos482G3 } from './seminario-cos-482-g-3.model';
import { SeminarioCos482G3PopupService } from './seminario-cos-482-g-3-popup.service';
import { SeminarioCos482G3Service } from './seminario-cos-482-g-3.service';

@Component({
    selector: 'jhi-seminario-cos-482-g-3-delete-dialog',
    templateUrl: './seminario-cos-482-g-3-delete-dialog.component.html'
})
export class SeminarioCos482G3DeleteDialogComponent {

    seminario: SeminarioCos482G3;

    constructor(
        private seminarioService: SeminarioCos482G3Service,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.seminarioService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'seminarioListModification',
                content: 'Deleted an seminario'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-seminario-cos-482-g-3-delete-popup',
    template: ''
})
export class SeminarioCos482G3DeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private seminarioPopupService: SeminarioCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.seminarioPopupService
                .open(SeminarioCos482G3DeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
