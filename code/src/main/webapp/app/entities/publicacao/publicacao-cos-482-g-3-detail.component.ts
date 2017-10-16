import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { PublicacaoCos482G3 } from './publicacao-cos-482-g-3.model';
import { PublicacaoCos482G3Service } from './publicacao-cos-482-g-3.service';

@Component({
    selector: 'jhi-publicacao-cos-482-g-3-detail',
    templateUrl: './publicacao-cos-482-g-3-detail.component.html'
})
export class PublicacaoCos482G3DetailComponent implements OnInit, OnDestroy {

    publicacao: PublicacaoCos482G3;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private publicacaoService: PublicacaoCos482G3Service,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPublicacaos();
    }

    load(id) {
        this.publicacaoService.find(id).subscribe((publicacao) => {
            this.publicacao = publicacao;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPublicacaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'publicacaoListModification',
            (response) => this.load(this.publicacao.id)
        );
    }
}
