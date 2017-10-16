import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { DefesaCos482G3 } from './defesa-cos-482-g-3.model';
import { DefesaCos482G3Service } from './defesa-cos-482-g-3.service';

@Component({
    selector: 'jhi-defesa-cos-482-g-3-detail',
    templateUrl: './defesa-cos-482-g-3-detail.component.html'
})
export class DefesaCos482G3DetailComponent implements OnInit, OnDestroy {

    defesa: DefesaCos482G3;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private defesaService: DefesaCos482G3Service,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDefesas();
    }

    load(id) {
        this.defesaService.find(id).subscribe((defesa) => {
            this.defesa = defesa;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDefesas() {
        this.eventSubscriber = this.eventManager.subscribe(
            'defesaListModification',
            (response) => this.load(this.defesa.id)
        );
    }
}
