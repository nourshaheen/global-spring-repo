import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRegion, getRegionIdentifier } from '../region.model';

export type EntityResponseType = HttpResponse<IRegion>;
export type EntityArrayResponseType = HttpResponse<IRegion[]>;

@Injectable({ providedIn: 'root' })
export class RegionService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/regions');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(region: IRegion): Observable<EntityResponseType> {
    return this.http.post<IRegion>(this.resourceUrl, region, { observe: 'response' });
  }

  update(region: IRegion): Observable<EntityResponseType> {
    return this.http.put<IRegion>(`${this.resourceUrl}/${getRegionIdentifier(region) as number}`, region, { observe: 'response' });
  }

  partialUpdate(region: IRegion): Observable<EntityResponseType> {
    return this.http.patch<IRegion>(`${this.resourceUrl}/${getRegionIdentifier(region) as number}`, region, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRegion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRegion[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addRegionToCollectionIfMissing(regionCollection: IRegion[], ...regionsToCheck: (IRegion | null | undefined)[]): IRegion[] {
    const regions: IRegion[] = regionsToCheck.filter(isPresent);
    if (regions.length > 0) {
      const regionCollectionIdentifiers = regionCollection.map(regionItem => getRegionIdentifier(regionItem)!);
      const regionsToAdd = regions.filter(regionItem => {
        const regionIdentifier = getRegionIdentifier(regionItem);
        if (regionIdentifier == null || regionCollectionIdentifiers.includes(regionIdentifier)) {
          return false;
        }
        regionCollectionIdentifiers.push(regionIdentifier);
        return true;
      });
      return [...regionsToAdd, ...regionCollection];
    }
    return regionCollection;
  }
}
