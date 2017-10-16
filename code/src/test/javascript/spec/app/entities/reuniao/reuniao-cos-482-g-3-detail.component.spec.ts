/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PesquisaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ReuniaoCos482G3DetailComponent } from '../../../../../../main/webapp/app/entities/reuniao/reuniao-cos-482-g-3-detail.component';
import { ReuniaoCos482G3Service } from '../../../../../../main/webapp/app/entities/reuniao/reuniao-cos-482-g-3.service';
import { ReuniaoCos482G3 } from '../../../../../../main/webapp/app/entities/reuniao/reuniao-cos-482-g-3.model';

describe('Component Tests', () => {

    describe('ReuniaoCos482G3 Management Detail Component', () => {
        let comp: ReuniaoCos482G3DetailComponent;
        let fixture: ComponentFixture<ReuniaoCos482G3DetailComponent>;
        let service: ReuniaoCos482G3Service;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PesquisaTestModule],
                declarations: [ReuniaoCos482G3DetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ReuniaoCos482G3Service,
                    JhiEventManager
                ]
            }).overrideTemplate(ReuniaoCos482G3DetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ReuniaoCos482G3DetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReuniaoCos482G3Service);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ReuniaoCos482G3(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.reuniao).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
