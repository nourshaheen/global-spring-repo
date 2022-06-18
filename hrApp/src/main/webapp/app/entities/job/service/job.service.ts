import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IJob, getJobIdentifier } from '../job.model';

export type EntityResponseType = HttpResponse<IJob>;
export type EntityArrayResponseType = HttpResponse<IJob[]>;

@Injectable({ providedIn: 'root' })
export class JobService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/jobs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(job: IJob): Observable<EntityResponseType> {
    return this.http.post<IJob>(this.resourceUrl, job, { observe: 'response' });
  }

  update(job: IJob): Observable<EntityResponseType> {
    return this.http.put<IJob>(`${this.resourceUrl}/${getJobIdentifier(job) as number}`, job, { observe: 'response' });
  }

  partialUpdate(job: IJob): Observable<EntityResponseType> {
    return this.http.patch<IJob>(`${this.resourceUrl}/${getJobIdentifier(job) as number}`, job, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IJob>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IJob[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addJobToCollectionIfMissing(jobCollection: IJob[], ...jobsToCheck: (IJob | null | undefined)[]): IJob[] {
    const jobs: IJob[] = jobsToCheck.filter(isPresent);
    if (jobs.length > 0) {
      const jobCollectionIdentifiers = jobCollection.map(jobItem => getJobIdentifier(jobItem)!);
      const jobsToAdd = jobs.filter(jobItem => {
        const jobIdentifier = getJobIdentifier(jobItem);
        if (jobIdentifier == null || jobCollectionIdentifiers.includes(jobIdentifier)) {
          return false;
        }
        jobCollectionIdentifiers.push(jobIdentifier);
        return true;
      });
      return [...jobsToAdd, ...jobCollection];
    }
    return jobCollection;
  }
}
