import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { DefesaCos482G3 } from './defesa-cos-482-g-3.model';
import { DefesaCos482G3Service } from './defesa-cos-482-g-3.service';

@Injectable()
export class DefesaCos482G3PopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private defesaService: DefesaCos482G3Service

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
                this.defesaService.find(id).subscribe((defesa) => {
                    defesa.dataEHora = this.datePipe
                        .transform(defesa.dataEHora, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.defesaModalRef(component, defesa);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.defesaModalRef(component, new DefesaCos482G3());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    defesaModalRef(component: Component, defesa: DefesaCos482G3): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.defesa = defesa;
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
