import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { PublicacaoCos482G3 } from './publicacao-cos-482-g-3.model';
import { PublicacaoCos482G3PopupService } from './publicacao-cos-482-g-3-popup.service';
import { PublicacaoCos482G3Service } from './publicacao-cos-482-g-3.service';

@Component({
    selector: 'jhi-publicacao-cos-482-g-3-delete-dialog',
    templateUrl: './publicacao-cos-482-g-3-delete-dialog.component.html'
})
export class PublicacaoCos482G3DeleteDialogComponent {

    publicacao: PublicacaoCos482G3;

    constructor(
        private publicacaoService: PublicacaoCos482G3Service,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.publicacaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'publicacaoListModification',
                content: 'Deleted an publicacao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-publicacao-cos-482-g-3-delete-popup',
    template: ''
})
export class PublicacaoCos482G3DeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private publicacaoPopupService: PublicacaoCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.publicacaoPopupService
                .open(PublicacaoCos482G3DeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
