import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { ParticipacaoBancaCos482G3 } from './participacao-banca-cos-482-g-3.model';
import { ParticipacaoBancaCos482G3Service } from './participacao-banca-cos-482-g-3.service';

@Component({
    selector: 'jhi-participacao-banca-cos-482-g-3-detail',
    templateUrl: './participacao-banca-cos-482-g-3-detail.component.html'
})
export class ParticipacaoBancaCos482G3DetailComponent implements OnInit, OnDestroy {

    participacaoBanca: ParticipacaoBancaCos482G3;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private participacaoBancaService: ParticipacaoBancaCos482G3Service,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInParticipacaoBancas();
    }

    load(id) {
        this.participacaoBancaService.find(id).subscribe((participacaoBanca) => {
            this.participacaoBanca = participacaoBanca;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInParticipacaoBancas() {
        this.eventSubscriber = this.eventManager.subscribe(
            'participacaoBancaListModification',
            (response) => this.load(this.participacaoBanca.id)
        );
    }
}
