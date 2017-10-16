/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PesquisaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ParticipacaoBancaCos482G3DetailComponent } from '../../../../../../main/webapp/app/entities/participacao-banca/participacao-banca-cos-482-g-3-detail.component';
import { ParticipacaoBancaCos482G3Service } from '../../../../../../main/webapp/app/entities/participacao-banca/participacao-banca-cos-482-g-3.service';
import { ParticipacaoBancaCos482G3 } from '../../../../../../main/webapp/app/entities/participacao-banca/participacao-banca-cos-482-g-3.model';

describe('Component Tests', () => {

    describe('ParticipacaoBancaCos482G3 Management Detail Component', () => {
        let comp: ParticipacaoBancaCos482G3DetailComponent;
        let fixture: ComponentFixture<ParticipacaoBancaCos482G3DetailComponent>;
        let service: ParticipacaoBancaCos482G3Service;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PesquisaTestModule],
                declarations: [ParticipacaoBancaCos482G3DetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ParticipacaoBancaCos482G3Service,
                    JhiEventManager
                ]
            }).overrideTemplate(ParticipacaoBancaCos482G3DetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ParticipacaoBancaCos482G3DetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParticipacaoBancaCos482G3Service);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ParticipacaoBancaCos482G3(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.participacaoBanca).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
