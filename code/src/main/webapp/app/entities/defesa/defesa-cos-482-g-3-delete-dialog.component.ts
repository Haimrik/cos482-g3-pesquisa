import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { DefesaCos482G3 } from './defesa-cos-482-g-3.model';
import { DefesaCos482G3PopupService } from './defesa-cos-482-g-3-popup.service';
import { DefesaCos482G3Service } from './defesa-cos-482-g-3.service';

@Component({
    selector: 'jhi-defesa-cos-482-g-3-delete-dialog',
    templateUrl: './defesa-cos-482-g-3-delete-dialog.component.html'
})
export class DefesaCos482G3DeleteDialogComponent {

    defesa: DefesaCos482G3;

    constructor(
        private defesaService: DefesaCos482G3Service,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.defesaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'defesaListModification',
                content: 'Deleted an defesa'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-defesa-cos-482-g-3-delete-popup',
    template: ''
})
export class DefesaCos482G3DeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private defesaPopupService: DefesaCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.defesaPopupService
                .open(DefesaCos482G3DeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
