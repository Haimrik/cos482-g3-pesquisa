import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UsuarioCos482G3 } from './usuario-cos-482-g-3.model';
import { UsuarioCos482G3PopupService } from './usuario-cos-482-g-3-popup.service';
import { UsuarioCos482G3Service } from './usuario-cos-482-g-3.service';

@Component({
    selector: 'jhi-usuario-cos-482-g-3-delete-dialog',
    templateUrl: './usuario-cos-482-g-3-delete-dialog.component.html'
})
export class UsuarioCos482G3DeleteDialogComponent {

    usuario: UsuarioCos482G3;

    constructor(
        private usuarioService: UsuarioCos482G3Service,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.usuarioService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'usuarioListModification',
                content: 'Deleted an usuario'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-usuario-cos-482-g-3-delete-popup',
    template: ''
})
export class UsuarioCos482G3DeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private usuarioPopupService: UsuarioCos482G3PopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.usuarioPopupService
                .open(UsuarioCos482G3DeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
