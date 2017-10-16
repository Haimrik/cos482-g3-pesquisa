import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { ReuniaoCos482G3 } from './reuniao-cos-482-g-3.model';
import { ReuniaoCos482G3Service } from './reuniao-cos-482-g-3.service';

@Component({
    selector: 'jhi-reuniao-cos-482-g-3-detail',
    templateUrl: './reuniao-cos-482-g-3-detail.component.html'
})
export class ReuniaoCos482G3DetailComponent implements OnInit, OnDestroy {

    reuniao: ReuniaoCos482G3;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private reuniaoService: ReuniaoCos482G3Service,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInReuniaos();
    }

    load(id) {
        this.reuniaoService.find(id).subscribe((reuniao) => {
            this.reuniao = reuniao;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInReuniaos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'reuniaoListModification',
            (response) => this.load(this.reuniao.id)
        );
    }
}
