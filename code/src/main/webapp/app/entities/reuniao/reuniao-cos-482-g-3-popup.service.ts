import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { ReuniaoCos482G3 } from './reuniao-cos-482-g-3.model';
import { ReuniaoCos482G3Service } from './reuniao-cos-482-g-3.service';

@Injectable()
export class ReuniaoCos482G3PopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private reuniaoService: ReuniaoCos482G3Service

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
                this.reuniaoService.find(id).subscribe((reuniao) => {
                    reuniao.dataEHora = this.datePipe
                        .transform(reuniao.dataEHora, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.reuniaoModalRef(component, reuniao);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.reuniaoModalRef(component, new ReuniaoCos482G3());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    reuniaoModalRef(component: Component, reuniao: ReuniaoCos482G3): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.reuniao = reuniao;
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
