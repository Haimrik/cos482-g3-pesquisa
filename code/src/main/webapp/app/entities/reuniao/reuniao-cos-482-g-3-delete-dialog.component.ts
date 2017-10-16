import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ReuniaoCos482G3 } from './reuniao-cos-482-g-3.model';
import { ReuniaoCos482G3PopupService } from './reuniao-cos-482-g-3-popup.service';
import { ReuniaoCos482G3Service } from './reuniao-cos-482-g-3.service';

@Component({
    selector: 'jhi-reuniao-cos-482-g-3-delete-dialog',
    templateUrl: './reuniao-cos-482-g-3-delete-dialog.component.html'
})
export class ReuniaoCos482G3DeleteDialogComponent {

    reuniao: ReuniaoCos482G3;

    constructor(
        private reuniaoService: ReuniaoCos482G3Service,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.reuniaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'reuniaoListModification',
                content: 'Deleted an reuniao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-reuniao-cos-482-g-3-delete-popup',
    template: ''
})
export class ReuniaoCos482G3DeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private reuniaoPopupService: ReuniaoCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.reuniaoPopupService
                .open(ReuniaoCos482G3DeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
