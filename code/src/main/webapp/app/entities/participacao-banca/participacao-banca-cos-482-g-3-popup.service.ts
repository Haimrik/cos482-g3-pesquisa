import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ParticipacaoBancaCos482G3 } from './participacao-banca-cos-482-g-3.model';
import { ParticipacaoBancaCos482G3Service } from './participacao-banca-cos-482-g-3.service';

@Injectable()
export class ParticipacaoBancaCos482G3PopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private participacaoBancaService: ParticipacaoBancaCos482G3Service

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.participacaoBancaService.find(id).subscribe((participacaoBanca) => {
                    this.ngbModalRef = this.participacaoBancaModalRef(component, participacaoBanca);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.participacaoBancaModalRef(component, new ParticipacaoBancaCos482G3());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    participacaoBancaModalRef(component: Component, participacaoBanca: ParticipacaoBancaCos482G3): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.participacaoBanca = participacaoBanca;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
