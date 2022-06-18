import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDepartment, getDepartmentIdentifier } from '../department.model';

export type EntityResponseType = HttpResponse<IDepartment>;
export type EntityArrayResponseType = HttpResponse<IDepartment[]>;

@Injectable({ providedIn: 'root' })
export class DepartmentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/departments');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(department: IDepartment): Observable<EntityResponseType> {
    return this.http.post<IDepartment>(this.resourceUrl, department, { observe: 'response' });
  }

  update(department: IDepartment): Observable<EntityResponseType> {
    return this.http.put<IDepartment>(`${this.resourceUrl}/${getDepartmentIdentifier(department) as number}`, department, {
      observe: 'response',
    });
  }

  partialUpdate(department: IDepartment): Observable<EntityResponseType> {
    return this.http.patch<IDepartment>(`${this.resourceUrl}/${getDepartmentIdentifier(department) as number}`, department, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDepartment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDepartment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addDepartmentToCollectionIfMissing(
    departmentCollection: IDepartment[],
    ...departmentsToCheck: (IDepartment | null | undefined)[]
  ): IDepartment[] {
    const departments: IDepartment[] = departmentsToCheck.filter(isPresent);
    if (departments.length > 0) {
      const departmentCollectionIdentifiers = departmentCollection.map(departmentItem => getDepartmentIdentifier(departmentItem)!);
      const departmentsToAdd = departments.filter(departmentItem => {
        const departmentIdentifier = getDepartmentIdentifier(departmentItem);
        if (departmentIdentifier == null || departmentCollectionIdentifiers.includes(departmentIdentifier)) {
          return false;
        }
        departmentCollectionIdentifiers.push(departmentIdentifier);
        return true;
      });
      return [...departmentsToAdd, ...departmentCollection];
    }
    return departmentCollection;
  }
}
