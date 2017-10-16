import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { SeminarioCos482G3 } from './seminario-cos-482-g-3.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class SeminarioCos482G3Service {

    private resourceUrl = SERVER_API_URL + 'api/seminarios';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(seminario: SeminarioCos482G3): Observable<SeminarioCos482G3> {
        const copy = this.convert(seminario);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(seminario: SeminarioCos482G3): Observable<SeminarioCos482G3> {
        const copy = this.convert(seminario);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<SeminarioCos482G3> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to SeminarioCos482G3.
     */
    private convertItemFromServer(json: any): SeminarioCos482G3 {
        const entity: SeminarioCos482G3 = Object.assign(new SeminarioCos482G3(), json);
        entity.dataEHora = this.dateUtils
            .convertDateTimeFromServer(json.dataEHora);
        return entity;
    }

    /**
     * Convert a SeminarioCos482G3 to a JSON which can be sent to the server.
     */
    private convert(seminario: SeminarioCos482G3): SeminarioCos482G3 {
        const copy: SeminarioCos482G3 = Object.assign({}, seminario);

        copy.dataEHora = this.dateUtils.toDate(seminario.dataEHora);
        return copy;
    }
}
