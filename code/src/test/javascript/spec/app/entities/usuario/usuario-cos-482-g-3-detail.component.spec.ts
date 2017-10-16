/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { PesquisaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { UsuarioCos482G3DetailComponent } from '../../../../../../main/webapp/app/entities/usuario/usuario-cos-482-g-3-detail.component';
import { UsuarioCos482G3Service } from '../../../../../../main/webapp/app/entities/usuario/usuario-cos-482-g-3.service';
import { UsuarioCos482G3 } from '../../../../../../main/webapp/app/entities/usuario/usuario-cos-482-g-3.model';

describe('Component Tests', () => {

    describe('UsuarioCos482G3 Management Detail Component', () => {
        let comp: UsuarioCos482G3DetailComponent;
        let fixture: ComponentFixture<UsuarioCos482G3DetailComponent>;
        let service: UsuarioCos482G3Service;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [PesquisaTestModule],
                declarations: [UsuarioCos482G3DetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    UsuarioCos482G3Service,
                    JhiEventManager
                ]
            }).overrideTemplate(UsuarioCos482G3DetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UsuarioCos482G3DetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UsuarioCos482G3Service);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new UsuarioCos482G3(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.usuario).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
