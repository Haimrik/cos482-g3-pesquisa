/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PesquisaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ProfessorCos482G3DetailComponent } from '../../../../../../main/webapp/app/entities/professor/professor-cos-482-g-3-detail.component';
import { ProfessorCos482G3Service } from '../../../../../../main/webapp/app/entities/professor/professor-cos-482-g-3.service';
import { ProfessorCos482G3 } from '../../../../../../main/webapp/app/entities/professor/professor-cos-482-g-3.model';

describe('Component Tests', () => {

    describe('ProfessorCos482G3 Management Detail Component', () => {
        let comp: ProfessorCos482G3DetailComponent;
        let fixture: ComponentFixture<ProfessorCos482G3DetailComponent>;
        let service: ProfessorCos482G3Service;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PesquisaTestModule],
                declarations: [ProfessorCos482G3DetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ProfessorCos482G3Service,
                    JhiEventManager
                ]
            }).overrideTemplate(ProfessorCos482G3DetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProfessorCos482G3DetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfessorCos482G3Service);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ProfessorCos482G3(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.professor).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
