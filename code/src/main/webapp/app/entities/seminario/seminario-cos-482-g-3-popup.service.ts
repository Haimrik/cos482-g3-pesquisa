import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { SeminarioCos482G3 } from './seminario-cos-482-g-3.model';
import { SeminarioCos482G3Service } from './seminario-cos-482-g-3.service';

@Injectable()
export class SeminarioCos482G3PopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private seminarioService: SeminarioCos482G3Service

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
                this.seminarioService.find(id).subscribe((seminario) => {
                    seminario.dataEHora = this.datePipe
                        .transform(seminario.dataEHora, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.seminarioModalRef(component, seminario);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.seminarioModalRef(component, new SeminarioCos482G3());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    seminarioModalRef(component: Component, seminario: SeminarioCos482G3): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.seminario = seminario;
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
