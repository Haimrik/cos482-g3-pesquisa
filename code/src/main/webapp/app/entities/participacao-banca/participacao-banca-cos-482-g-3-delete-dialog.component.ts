import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ParticipacaoBancaCos482G3 } from './participacao-banca-cos-482-g-3.model';
import { ParticipacaoBancaCos482G3PopupService } from './participacao-banca-cos-482-g-3-popup.service';
import { ParticipacaoBancaCos482G3Service } from './participacao-banca-cos-482-g-3.service';

@Component({
    selector: 'jhi-participacao-banca-cos-482-g-3-delete-dialog',
    templateUrl: './participacao-banca-cos-482-g-3-delete-dialog.component.html'
})
export class ParticipacaoBancaCos482G3DeleteDialogComponent {

    participacaoBanca: ParticipacaoBancaCos482G3;

    constructor(
        private participacaoBancaService: ParticipacaoBancaCos482G3Service,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.participacaoBancaService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'participacaoBancaListModification',
                content: 'Deleted an participacaoBanca'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-participacao-banca-cos-482-g-3-delete-popup',
    template: ''
})
export class ParticipacaoBancaCos482G3DeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private participacaoBancaPopupService: ParticipacaoBancaCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.participacaoBancaPopupService
                .open(ParticipacaoBancaCos482G3DeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
