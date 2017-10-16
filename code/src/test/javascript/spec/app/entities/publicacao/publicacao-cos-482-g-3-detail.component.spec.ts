/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PesquisaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { PublicacaoCos482G3DetailComponent } from '../../../../../../main/webapp/app/entities/publicacao/publicacao-cos-482-g-3-detail.component';
import { PublicacaoCos482G3Service } from '../../../../../../main/webapp/app/entities/publicacao/publicacao-cos-482-g-3.service';
import { PublicacaoCos482G3 } from '../../../../../../main/webapp/app/entities/publicacao/publicacao-cos-482-g-3.model';

describe('Component Tests', () => {

    describe('PublicacaoCos482G3 Management Detail Component', () => {
        let comp: PublicacaoCos482G3DetailComponent;
        let fixture: ComponentFixture<PublicacaoCos482G3DetailComponent>;
        let service: PublicacaoCos482G3Service;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PesquisaTestModule],
                declarations: [PublicacaoCos482G3DetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    PublicacaoCos482G3Service,
                    JhiEventManager
                ]
            }).overrideTemplate(PublicacaoCos482G3DetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PublicacaoCos482G3DetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PublicacaoCos482G3Service);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new PublicacaoCos482G3(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.publicacao).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
