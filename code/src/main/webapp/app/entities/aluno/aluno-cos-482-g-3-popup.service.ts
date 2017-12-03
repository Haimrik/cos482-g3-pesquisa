import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { AlunoCos482G3 } from './aluno-cos-482-g-3.model';
import { AlunoCos482G3Service } from './aluno-cos-482-g-3.service';

@Injectable()
export class AlunoCos482G3PopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private alunoService: AlunoCos482G3Service

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
                this.alunoService.find(id).subscribe((aluno) => {
                    aluno.dataDeEntrada = this.datePipe
                        .transform(new Date(), 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.alunoModalRef(component, aluno);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.alunoModalRef(component, new AlunoCos482G3());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    alunoModalRef(component: Component, aluno: AlunoCos482G3): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.aluno = aluno;
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
