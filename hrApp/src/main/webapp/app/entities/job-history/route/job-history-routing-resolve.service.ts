import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IJobHistory, JobHistory } from '../job-history.model';
import { JobHistoryService } from '../service/job-history.service';

@Injectable({ providedIn: 'root' })
export class JobHistoryRoutingResolveService implements Resolve<IJobHistory> {
  constructor(protected service: JobHistoryService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IJobHistory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((jobHistory: HttpResponse<JobHistory>) => {
          if (jobHistory.body) {
            return of(jobHistory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new JobHistory());
  }
}
