import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { UsuarioCos482G3 } from './usuario-cos-482-g-3.model';
import { UsuarioCos482G3Service } from './usuario-cos-482-g-3.service';

@Injectable()
export class UsuarioCos482G3PopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private usuarioService: UsuarioCos482G3Service

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
                this.usuarioService.find(id).subscribe((usuario) => {
                    this.ngbModalRef = this.usuarioModalRef(component, usuario);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.usuarioModalRef(component, new UsuarioCos482G3());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    usuarioModalRef(component: Component, usuario: UsuarioCos482G3): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.usuario = usuario;
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
