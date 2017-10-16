import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { SeminarioCos482G3 } from './seminario-cos-482-g-3.model';
import { SeminarioCos482G3Service } from './seminario-cos-482-g-3.service';

@Component({
    selector: 'jhi-seminario-cos-482-g-3-detail',
    templateUrl: './seminario-cos-482-g-3-detail.component.html'
})
export class SeminarioCos482G3DetailComponent implements OnInit, OnDestroy {

    seminario: SeminarioCos482G3;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private seminarioService: SeminarioCos482G3Service,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSeminarios();
    }

    load(id) {
        this.seminarioService.find(id).subscribe((seminario) => {
            this.seminario = seminario;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSeminarios() {
        this.eventSubscriber = this.eventManager.subscribe(
            'seminarioListModification',
            (response) => this.load(this.seminario.id)
        );
    }
}
