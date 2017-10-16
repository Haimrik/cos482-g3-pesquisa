/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PesquisaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { SeminarioCos482G3DetailComponent } from '../../../../../../main/webapp/app/entities/seminario/seminario-cos-482-g-3-detail.component';
import { SeminarioCos482G3Service } from '../../../../../../main/webapp/app/entities/seminario/seminario-cos-482-g-3.service';
import { SeminarioCos482G3 } from '../../../../../../main/webapp/app/entities/seminario/seminario-cos-482-g-3.model';

describe('Component Tests', () => {

    describe('SeminarioCos482G3 Management Detail Component', () => {
        let comp: SeminarioCos482G3DetailComponent;
        let fixture: ComponentFixture<SeminarioCos482G3DetailComponent>;
        let service: SeminarioCos482G3Service;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PesquisaTestModule],
                declarations: [SeminarioCos482G3DetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    SeminarioCos482G3Service,
                    JhiEventManager
                ]
            }).overrideTemplate(SeminarioCos482G3DetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SeminarioCos482G3DetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SeminarioCos482G3Service);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new SeminarioCos482G3(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.seminario).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
