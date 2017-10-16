import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AlunoCos482G3 } from './aluno-cos-482-g-3.model';
import { AlunoCos482G3Service } from './aluno-cos-482-g-3.service';

@Component({
    selector: 'jhi-aluno-cos-482-g-3-detail',
    templateUrl: './aluno-cos-482-g-3-detail.component.html'
})
export class AlunoCos482G3DetailComponent implements OnInit, OnDestroy {

    aluno: AlunoCos482G3;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private alunoService: AlunoCos482G3Service,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAlunos();
    }

    load(id) {
        this.alunoService.find(id).subscribe((aluno) => {
            this.aluno = aluno;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAlunos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'alunoListModification',
            (response) => this.load(this.aluno.id)
        );
    }
}
