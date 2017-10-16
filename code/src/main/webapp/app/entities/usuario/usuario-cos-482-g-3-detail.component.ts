import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { UsuarioCos482G3 } from './usuario-cos-482-g-3.model';
import { UsuarioCos482G3Service } from './usuario-cos-482-g-3.service';

@Component({
    selector: 'jhi-usuario-cos-482-g-3-detail',
    templateUrl: './usuario-cos-482-g-3-detail.component.html'
})
export class UsuarioCos482G3DetailComponent implements OnInit, OnDestroy {

    usuario: UsuarioCos482G3;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private usuarioService: UsuarioCos482G3Service,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUsuarios();
    }

    load(id) {
        this.usuarioService.find(id).subscribe((usuario) => {
            this.usuario = usuario;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUsuarios() {
        this.eventSubscriber = this.eventManager.subscribe(
            'usuarioListModification',
            (response) => this.load(this.usuario.id)
        );
    }
}
