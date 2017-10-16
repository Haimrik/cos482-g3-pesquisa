import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { ProfessorCos482G3 } from './professor-cos-482-g-3.model';
import { ProfessorCos482G3Service } from './professor-cos-482-g-3.service';

@Component({
    selector: 'jhi-professor-cos-482-g-3-detail',
    templateUrl: './professor-cos-482-g-3-detail.component.html'
})
export class ProfessorCos482G3DetailComponent implements OnInit, OnDestroy {

    professor: ProfessorCos482G3;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private professorService: ProfessorCos482G3Service,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProfessors();
    }

    load(id) {
        this.professorService.find(id).subscribe((professor) => {
            this.professor = professor;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProfessors() {
        this.eventSubscriber = this.eventManager.subscribe(
            'professorListModification',
            (response) => this.load(this.professor.id)
        );
    }
}
