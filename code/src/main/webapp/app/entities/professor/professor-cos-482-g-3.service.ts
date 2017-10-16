import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { ProfessorCos482G3 } from './professor-cos-482-g-3.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ProfessorCos482G3Service {

    private resourceUrl = SERVER_API_URL + 'api/professors';

    constructor(private http: Http) { }

    create(professor: ProfessorCos482G3): Observable<ProfessorCos482G3> {
        const copy = this.convert(professor);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(professor: ProfessorCos482G3): Observable<ProfessorCos482G3> {
        const copy = this.convert(professor);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<ProfessorCos482G3> {
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
     * Convert a returned JSON object to ProfessorCos482G3.
     */
    private convertItemFromServer(json: any): ProfessorCos482G3 {
        const entity: ProfessorCos482G3 = Object.assign(new ProfessorCos482G3(), json);
        return entity;
    }

    /**
     * Convert a ProfessorCos482G3 to a JSON which can be sent to the server.
     */
    private convert(professor: ProfessorCos482G3): ProfessorCos482G3 {
        const copy: ProfessorCos482G3 = Object.assign({}, professor);
        return copy;
    }
}
