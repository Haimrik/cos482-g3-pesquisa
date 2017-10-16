import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { ReuniaoCos482G3 } from './reuniao-cos-482-g-3.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ReuniaoCos482G3Service {

    private resourceUrl = SERVER_API_URL + 'api/reuniaos';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(reuniao: ReuniaoCos482G3): Observable<ReuniaoCos482G3> {
        const copy = this.convert(reuniao);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(reuniao: ReuniaoCos482G3): Observable<ReuniaoCos482G3> {
        const copy = this.convert(reuniao);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<ReuniaoCos482G3> {
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
     * Convert a returned JSON object to ReuniaoCos482G3.
     */
    private convertItemFromServer(json: any): ReuniaoCos482G3 {
        const entity: ReuniaoCos482G3 = Object.assign(new ReuniaoCos482G3(), json);
        entity.dataEHora = this.dateUtils
            .convertDateTimeFromServer(json.dataEHora);
        return entity;
    }

    /**
     * Convert a ReuniaoCos482G3 to a JSON which can be sent to the server.
     */
    private convert(reuniao: ReuniaoCos482G3): ReuniaoCos482G3 {
        const copy: ReuniaoCos482G3 = Object.assign({}, reuniao);

        copy.dataEHora = this.dateUtils.toDate(reuniao.dataEHora);
        return copy;
    }
}
